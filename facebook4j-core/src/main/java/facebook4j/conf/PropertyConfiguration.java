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

package facebook4j.conf;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 *   <li>Changed for Facebook4J</li>
 * </ul>
 */
public final class PropertyConfiguration extends ConfigurationBase implements Serializable {
    private static final long serialVersionUID = -6424033561218975631L;

    public static final String DEBUG = "debug";
    public static final String HTTP_USER_AGENT = "http.userAgent";

    public static final String HTTP_USE_SSL = "http.useSSL";
    public static final String HTTP_PRETTY_DEBUG = "http.prettyDebug";
    public static final String HTTP_GZIP = "http.gzip";
    public static final String HTTP_PROXY_HOST = "http.proxyHost";
    public static final String HTTP_PROXY_HOST_FALLBACK = "http.proxyHost";
    public static final String HTTP_PROXY_USER = "http.proxyUser";
    public static final String HTTP_PROXY_PASSWORD = "http.proxyPassword";
    public static final String HTTP_PROXY_PORT = "http.proxyPort";
    public static final String HTTP_PROXY_PORT_FALLBACK = "http.proxyPort";
    public static final String HTTP_CONNECTION_TIMEOUT = "http.connectionTimeout";
    public static final String HTTP_READ_TIMEOUT = "http.readTimeout";

    public static final String HTTP_RETRY_COUNT = "http.retryCount";
    public static final String HTTP_RETRY_INTERVAL_SECS = "http.retryIntervalSecs";

    public static final String HTTP_MAX_TOTAL_CONNECTIONS = "http.maxTotalConnections";
    public static final String HTTP_DEFAULT_MAX_PER_ROUTE = "http.defaultMaxPerRoute";

    public static final String OAUTH_APP_ID = "oauth.appId";
    public static final String OAUTH_APP_SECRET = "oauth.appSecret";
    public static final String OAUTH_ACCESS_TOKEN = "oauth.accessToken";
    public static final String OAUTH_PERMISSIONS = "oauth.permissions";
    public static final String OAUTH_CALLBACK_URL = "oauth.callbackURL";

    public static final String SECURITY_APP_SECRET_PROOF_ENABLED = "security.appSecretProofEnabled";
    public static final String SECURITY_APP_SECRET_PROOF_CACHE_SIZE = "security.appSecretProofCacheSize";

    public static final String OAUTH_AUTHORIZATION_URL = "oauth.authorizationURL";
    public static final String OAUTH_ACCESS_TOKEN_URL = "oauth.accessTokenURL";
    public static final String OAUTH_ACCESS_TOKEN_INFO_URL = "oauth.accessTokenInfoURL";
    public static final String OAUTH_DEVICE_TOKEN_URL = "oauth.deviceTokenURL";

    public static final String REST_BASE_URL = "restBaseURL";
    public static final String VIDEO_BASE_URL = "videoBaseURL";

    public static final String JSON_STORE_ENABLED = "jsonStoreEnabled";
    public static final String MBEAN_ENABLED = "mbeanEnabled";

    // hidden portion
    public static final String CLIENT_VERSION = "clientVersion";
    public static final String CLIENT_URL = "clientURL";

    public PropertyConfiguration(InputStream is) {
        super();
        Properties props = new Properties();
        loadProperties(props, is);
        setFieldsWithTreePath(props, "/");
    }

    public PropertyConfiguration(Properties props) {
        this(props, "/");
    }

    public PropertyConfiguration(Properties props, String treePath) {
        super();
        setFieldsWithTreePath(props, treePath);
    }

    PropertyConfiguration(String treePath) {
        super();
        Properties props;
        // load from system properties
        try {
            props = (Properties) System.getProperties().clone();
            try {
                Map<String, String> envMap = System.getenv();
                for (String key : envMap.keySet()) {
                    props.setProperty(key, envMap.get(key));
                }
            } catch (SecurityException ignore) {}
            normalize(props);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
            props = new Properties();
        }
        final String FACEBOOK4J_PROPERTIES = "facebook4j.properties";
        // override System properties with ./facebook4j.properties in the classpath
        loadProperties(props, "." + File.separatorChar + FACEBOOK4J_PROPERTIES);
        // then, override with /facebook4j.properties in the classpath
        loadProperties(props, Configuration.class.getResourceAsStream("/" + FACEBOOK4J_PROPERTIES));
        // then, override with /WEB/INF/facebook4j.properties in the classpath
        loadProperties(props, Configuration.class.getResourceAsStream("/WEB-INF/" + FACEBOOK4J_PROPERTIES));
        // for Google App Engine
        try {
            loadProperties(props, new FileInputStream("WEB-INF/" + FACEBOOK4J_PROPERTIES));
        } catch (SecurityException ignore) {
        } catch (FileNotFoundException ignore) {
        }

        setFieldsWithTreePath(props, treePath);
    }

    /**
     * Creates a root PropertyConfiguration. This constructor is equivalent to new PropertyConfiguration("/").
     */
    PropertyConfiguration() {
        this("/");
    }

    private boolean notNull(Properties props, String prefix, String name) {
        return props.getProperty(prefix + name) != null;
    }

    private boolean loadProperties(Properties props, String path) {
        FileInputStream fis = null;
        try {
            File file = new File(path);
            if (file.exists() && file.isFile()) {
                fis = new FileInputStream(file);
                props.load(fis);
                normalize(props);
                return true;
            }
        } catch (Exception ignore) {
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
            } catch (IOException ignore) {

            }
        }
        return false;
    }

    private boolean loadProperties(Properties props, InputStream is) {
        try {
            props.load(is);
            normalize(props);
            return true;
        } catch (Exception ignore) {
        }
        return false;
    }

    private void normalize(Properties props) {
        Set<Object> keys = props.keySet();
        List<String> toBeNormalized = new ArrayList<String>(10);
        for (Object key : keys) {
            String keyStr = (String) key;
            if (-1 != (keyStr.indexOf("facebook4j."))) {
                toBeNormalized.add(keyStr);
            }
        }
        for (String keyStr : toBeNormalized) {
            String property = props.getProperty(keyStr);
            int index = keyStr.indexOf("facebook4j.");
            String newKey = keyStr.substring(0, index) + keyStr.substring(index + 11);
            props.setProperty(newKey, property);
        }
    }

    /**
     * passing "/foo/bar" as treePath will result:<br>
     * 1. load [facebook4j.]restBaseURL<br>
     * 2. override the value with foo.[facebook4j.]restBaseURL<br>
     * 3. override the value with foo.bar.[facebook4j.]restBaseURL<br>
     *
     * @param props    properties to be loaded
     * @param treePath the path
     */
    private void setFieldsWithTreePath(Properties props, String treePath) {
        setFieldsWithPrefix(props, "");
        String[] splitArray = treePath.split("/");
        String prefix = null;
        for (String split : splitArray) {
            if (!"".equals(split)) {
                if (null == prefix) {
                    prefix = split + ".";
                } else {
                    prefix += split + ".";
                }
                setFieldsWithPrefix(props, prefix);
            }
        }
    }

    private void setFieldsWithPrefix(Properties props, String prefix) {
        if (notNull(props, prefix, DEBUG)) {
            setDebug(getBoolean(props, prefix, DEBUG));
        }

        if (notNull(props, prefix, HTTP_USE_SSL)) {
            setUseSSL(getBoolean(props, prefix, HTTP_USE_SSL));
        }
        if (notNull(props, prefix, HTTP_PRETTY_DEBUG)) {
            setPrettyDebugEnabled(getBoolean(props, prefix, HTTP_PRETTY_DEBUG));
        }
        if (notNull(props, prefix, HTTP_GZIP)) {
            setGZIPEnabled(getBoolean(props, prefix, HTTP_GZIP));
        }
        if (notNull(props, prefix, HTTP_PROXY_HOST)) {
            setHttpProxyHost(getString(props, prefix, HTTP_PROXY_HOST));
        } else if (notNull(props, prefix, HTTP_PROXY_HOST_FALLBACK)) {
            setHttpProxyHost(getString(props, prefix, HTTP_PROXY_HOST_FALLBACK));
        }
        if (notNull(props, prefix, HTTP_PROXY_USER)) {
            setHttpProxyUser(getString(props, prefix, HTTP_PROXY_USER));
        }
        if (notNull(props, prefix, HTTP_PROXY_PASSWORD)) {
            setHttpProxyPassword(getString(props, prefix, HTTP_PROXY_PASSWORD));
        }
        if (notNull(props, prefix, HTTP_PROXY_PORT)) {
            setHttpProxyPort(getIntProperty(props, prefix, HTTP_PROXY_PORT));
        } else if (notNull(props, prefix, HTTP_PROXY_PORT_FALLBACK)) {
            setHttpProxyPort(getIntProperty(props, prefix, HTTP_PROXY_PORT_FALLBACK));
        }
        if (notNull(props, prefix, HTTP_CONNECTION_TIMEOUT)) {
            setHttpConnectionTimeout(getIntProperty(props, prefix, HTTP_CONNECTION_TIMEOUT));
        }
        if (notNull(props, prefix, HTTP_READ_TIMEOUT)) {
            setHttpReadTimeout(getIntProperty(props, prefix, HTTP_READ_TIMEOUT));
        }
        if (notNull(props, prefix, HTTP_RETRY_COUNT)) {
            setHttpRetryCount(getIntProperty(props, prefix, HTTP_RETRY_COUNT));
        }
        if (notNull(props, prefix, HTTP_RETRY_INTERVAL_SECS)) {
            setHttpRetryIntervalSeconds(getIntProperty(props, prefix, HTTP_RETRY_INTERVAL_SECS));
        }
        if (notNull(props, prefix, HTTP_MAX_TOTAL_CONNECTIONS)) {
            setHttpMaxTotalConnections(getIntProperty(props, prefix, HTTP_MAX_TOTAL_CONNECTIONS));
        }
        if (notNull(props, prefix, HTTP_DEFAULT_MAX_PER_ROUTE)) {
            setHttpDefaultMaxPerRoute(getIntProperty(props, prefix, HTTP_DEFAULT_MAX_PER_ROUTE));
        }
        if (notNull(props, prefix, OAUTH_APP_ID)) {
            setOAuthAppId(getString(props, prefix, OAUTH_APP_ID));
        }
        if (notNull(props, prefix, OAUTH_APP_SECRET)) {
            setOAuthAppSecret(getString(props, prefix, OAUTH_APP_SECRET));
        }
        if (notNull(props, prefix, OAUTH_ACCESS_TOKEN)) {
            setOAuthAccessToken(getString(props, prefix, OAUTH_ACCESS_TOKEN));
        }
        if (notNull(props, prefix, OAUTH_PERMISSIONS)) {
            setOAuthPermissions(getString(props, prefix, OAUTH_PERMISSIONS));
        }
        if (notNull(props, prefix, OAUTH_CALLBACK_URL)) {
            setOAuthCallbackURL(getString(props, prefix, OAUTH_CALLBACK_URL));
        }
        if (notNull(props, prefix, SECURITY_APP_SECRET_PROOF_ENABLED)) {
            setAppSecretProofEnabled(getBoolean(props, prefix, SECURITY_APP_SECRET_PROOF_ENABLED));
        }
        if (notNull(props, prefix, SECURITY_APP_SECRET_PROOF_CACHE_SIZE)) {
            setAppSecretProofCacheSize(getIntProperty(props, prefix, SECURITY_APP_SECRET_PROOF_CACHE_SIZE));
        }
        if (notNull(props, prefix, CLIENT_VERSION)) {
            setClientVersion(getString(props, prefix, CLIENT_VERSION));
        }
        if (notNull(props, prefix, CLIENT_URL)) {
            setClientURL(getString(props, prefix, CLIENT_URL));
        }
        if (notNull(props, prefix, HTTP_USER_AGENT)) {
            setUserAgent(getString(props, prefix, HTTP_USER_AGENT));
        }

        if (notNull(props, prefix, OAUTH_AUTHORIZATION_URL)) {
            setOAuthAuthorizationURL(getString(props, prefix, OAUTH_AUTHORIZATION_URL));
        }

        if (notNull(props, prefix, OAUTH_ACCESS_TOKEN_URL)) {
            setOAuthAccessTokenURL(getString(props, prefix, OAUTH_ACCESS_TOKEN_URL));
        }

        if (notNull(props, prefix, OAUTH_ACCESS_TOKEN_INFO_URL)) {
            setOAuthAccessTokenInfoURL(getString(props, prefix, OAUTH_ACCESS_TOKEN_INFO_URL));
        }

        if (notNull(props, prefix, OAUTH_DEVICE_TOKEN_URL)) {
            setOAuthDeviceTokenURL(getString(props, prefix, OAUTH_DEVICE_TOKEN_URL));
        }

        if (notNull(props, prefix, REST_BASE_URL)) {
            setRestBaseURL(getString(props, prefix, REST_BASE_URL));
        }

        if (notNull(props, prefix, VIDEO_BASE_URL)) {
            setVideoBaseURL(getString(props, prefix, VIDEO_BASE_URL));
        }

        if (notNull(props, prefix, JSON_STORE_ENABLED)) {
            setJSONStoreEnabled(getBoolean(props, prefix, JSON_STORE_ENABLED));
        }
        if (notNull(props, prefix, MBEAN_ENABLED)) {
            setMBeanEnabled(getBoolean(props, prefix, MBEAN_ENABLED));
        }
        cacheInstance();
    }

    protected boolean getBoolean(Properties props, String prefix, String name) {
        String value = props.getProperty(prefix + name);
        return Boolean.valueOf(value);
    }

    protected int getIntProperty(Properties props, String prefix, String name) {
        String value = props.getProperty(prefix + name);
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            return -1;
        }
    }

    protected String getString(Properties props, String prefix, String name) {
        return props.getProperty(prefix + name);
    }

    // assures equality after deserialization
    @Override
    protected Object readResolve() throws ObjectStreamException {
        return super.readResolve();
    }
}
