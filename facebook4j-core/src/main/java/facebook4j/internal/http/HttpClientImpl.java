/*
 * Copyright 2007 Yusuke Yamamoto
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package facebook4j.internal.http;

import facebook4j.FacebookException;
import facebook4j.conf.ConfigurationContext;
import facebook4j.internal.logging.Logger;
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.PasswordAuthentication;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static facebook4j.internal.http.RequestMethod.*;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.2
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 * <li>Changed for Facebook</li>
 * </ul>
 */
public class HttpClientImpl extends HttpClientBase implements HttpClient, HttpResponseCode, java.io.Serializable {
    private static final long serialVersionUID = -1349112176863288573L;

    private static final Logger logger = Logger.getLogger(HttpClientImpl.class);

    static {
        // disable keepAlive (Android 2.1 or earlier)
        // quick and dirty workaround for TFJ-296
        // @see http://stackoverflow.com/questions/1440957/
        try {
            // Integer.parseInt(Build.VERSION.SDK) < Build.VERSION_CODES.FROYO
            if (Integer.parseInt((String) Class.forName("android.os.Build$VERSION").getField("SDK").get(null)) < 8) {
                System.setProperty("http.keepAlive", "false");
            }
        } catch (Exception ignore) {
        }
    }

    public HttpClientImpl() {
        super(ConfigurationContext.getInstance());
    }

    public HttpClientImpl(HttpClientConfiguration conf) {
        super(conf);
    }

    private static final Map<HttpClientConfiguration, HttpClient> instanceMap = new HashMap<HttpClientConfiguration, HttpClient>(1);

    public static HttpClient getInstance(HttpClientConfiguration conf) {
        HttpClient client = instanceMap.get(conf);
        if (null == client) {
            client = new HttpClientImpl(conf);
            instanceMap.put(conf, client);
        }
        return client;
    }

    public HttpResponse get(String url) throws FacebookException {
        return request(new HttpRequest(RequestMethod.GET, url, null, null, null));
    }

    public HttpResponse post(String url, HttpParameter[] params) throws FacebookException {
        return request(new HttpRequest(RequestMethod.POST, url, params, null, null));
    }

    public HttpResponse request(HttpRequest req) throws FacebookException {
        int retriedCount;
        int retry = CONF.getHttpRetryCount() + 1;
        HttpResponse res = null;
        for (retriedCount = 0; retriedCount < retry; retriedCount++) {
            int responseCode = -1;
            try {
                HttpURLConnection con;
                OutputStream os = null;
                try {
                    con = getConnection(req.getURL());
                    con.setDoInput(true);
                    setHeaders(req, con);
                    con.setRequestMethod(req.getMethod().name());
                    if (req.getMethod() == POST) {
                        if (HttpParameter.containsFile(req.getParameters())) {
                            String boundary = "----Facebook4J-upload" + System.currentTimeMillis();
                            con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
                            boundary = "--" + boundary;
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            DataOutputStream out = new DataOutputStream(os);
                            for (HttpParameter param : req.getParameters()) {
                                if (param.isFile()) {
                                    write(out, boundary + "\r\n");
                                    write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"; filename=\"" + param.getFile().getName() + "\"\r\n");
                                    write(out, "Content-Type: " + param.getContentType() + "\r\n\r\n");
                                    BufferedInputStream in = new BufferedInputStream(
                                            param.hasFileBody() ? param.getFileBody() : new FileInputStream(param.getFile())
                                    );
                                    int buff;
                                    while ((buff = in.read()) != -1) {
                                        out.write(buff);
                                    }
                                    write(out, "\r\n");
                                    in.close();
                                } else {
                                    write(out, boundary + "\r\n");
                                    write(out, "Content-Disposition: form-data; name=\"" + param.getName() + "\"\r\n");
                                    write(out, "Content-Type: text/plain; charset=UTF-8\r\n\r\n");
                                    logger.debug(param.getValue());
                                    out.write(param.getValue().getBytes("UTF-8"));
                                    write(out, "\r\n");
                                }
                            }
                            write(out, boundary + "--\r\n");
                            write(out, "\r\n");

                        } else {
                            con.setRequestProperty("Content-Type",
                                    "application/x-www-form-urlencoded");
                            String postParam = HttpParameter.encodeParameters(req.getParameters());
                            logger.debug("Post Params: ", postParam);
                            byte[] bytes = postParam.getBytes("UTF-8");
                            con.setRequestProperty("Content-Length",
                                    Integer.toString(bytes.length));
                            con.setDoOutput(true);
                            os = con.getOutputStream();
                            os.write(bytes);
                        }
                        os.flush();
                        os.close();
                    }
                    res = new HttpResponseImpl(con, CONF);
                    responseCode = con.getResponseCode();
                    if (logger.isDebugEnabled()) {
                        logger.debug("Response: ");
                        Map<String, List<String>> responseHeaders = con.getHeaderFields();
                        for (String key : responseHeaders.keySet()) {
                            List<String> values = responseHeaders.get(key);
                            for (String value : values) {
                                if (key != null) {
                                    logger.debug(key + ": " + value);
                                } else {
                                    logger.debug(value);
                                }
                            }
                        }
                    }
                    if (responseCode < OK || (responseCode != FOUND && MULTIPLE_CHOICES <= responseCode)) {
                        if (responseCode == ENHANCE_YOUR_CLAIM ||
                                responseCode == BAD_REQUEST ||
                                responseCode < INTERNAL_SERVER_ERROR ||
                                retriedCount == CONF.getHttpRetryCount()) {
                            throw new FacebookException(res.asString(), res);
                        }
                        // will retry if the status code is INTERNAL_SERVER_ERROR
                    } else {
                        break;
                    }
                } finally {
                    try {
                        os.close();
                    } catch (Exception ignore) {
                    }
                }
            } catch (IOException ioe) {
                // connection timeout or read timeout
                if (retriedCount == CONF.getHttpRetryCount()) {
                    throw new FacebookException(ioe.getMessage(), ioe, responseCode);
                }
            }
            try {
                if (logger.isDebugEnabled() && res != null) {
                    res.asString();
                }
                logger.debug("Sleeping " + CONF.getHttpRetryIntervalSeconds() + " seconds until the next retry.");
                Thread.sleep(CONF.getHttpRetryIntervalSeconds() * 1000);
            } catch (InterruptedException ignore) {
                //nothing to do
            }
        }
        return res;
    }

    public static String encode(String str) {
        try {
            return URLEncoder.encode(str, "UTF-8");
        } catch (java.io.UnsupportedEncodingException neverHappen) {
            throw new AssertionError("will never happen");
        }
    }

    /**
     * sets HTTP headers
     *
     * @param req        The request
     * @param connection HttpURLConnection
     */
    private void setHeaders(HttpRequest req, HttpURLConnection connection) {
        if (logger.isDebugEnabled()) {
            logger.debug("Request: ");
            logger.debug(req.getMethod().name() + " ", req.getURL());
        }

        if (req.getRequestHeaders() != null) {
            for (String key : req.getRequestHeaders().keySet()) {
                connection.addRequestProperty(key, req.getRequestHeaders().get(key));
                logger.debug(key + ": " + req.getRequestHeaders().get(key));
            }
        }
    }

    protected HttpURLConnection getConnection(String url) throws IOException {
        HttpURLConnection con;
        if (isProxyConfigured()) {
            if (CONF.getHttpProxyUser() != null && !CONF.getHttpProxyUser().equals("")) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Proxy AuthUser: " + CONF.getHttpProxyUser());
                    logger.debug("Proxy AuthPassword: " + z_F4JInternalStringUtil.maskString(CONF.getHttpProxyPassword()));
                }
                Authenticator.setDefault(new Authenticator() {
                    @Override
                    protected PasswordAuthentication
                    getPasswordAuthentication() {
                        //respond only to proxy auth requests
                        if (getRequestorType().equals(RequestorType.PROXY)) {
                            return new PasswordAuthentication(CONF.getHttpProxyUser(),
                                    CONF.getHttpProxyPassword().toCharArray());
                        } else {
                            return null;
                        }
                    }
                });
            }
            final Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress
                    .createUnresolved(CONF.getHttpProxyHost(), CONF.getHttpProxyPort()));
            if (logger.isDebugEnabled()) {
                logger.debug("Opening proxied connection(" + CONF.getHttpProxyHost() + ":" + CONF.getHttpProxyPort() + ")");
            }
            con = (HttpURLConnection) new URL(url).openConnection(proxy);
        } else {
            con = (HttpURLConnection) new URL(url).openConnection();
        }
        if (CONF.getHttpConnectionTimeout() > 0) {
            con.setConnectTimeout(CONF.getHttpConnectionTimeout());
        }
        if (CONF.getHttpReadTimeout() > 0) {
            con.setReadTimeout(CONF.getHttpReadTimeout());
        }
        con.setInstanceFollowRedirects(false);
        return con;
    }
}
