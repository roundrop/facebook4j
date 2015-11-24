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

import facebook4j.Version;

import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Configuration base class with default settings.
 *  
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 *   <li>Changed for Facebook4J</li>
 * </ul>
 */
public class ConfigurationBase implements Configuration, Serializable {
    private static final long serialVersionUID = -4618524490519309627L;

    private boolean debug;
    private String userAgent;
    private boolean useSSL;
    private boolean prettyDebug;
    private boolean gzipEnabled;
    private String httpProxyHost;
    private String httpProxyUser;
    private String httpProxyPassword;
    private int httpProxyPort;
    private int httpConnectionTimeout;
    private int httpReadTimeout;

    private int httpStreamingReadTimeout;
    private int httpRetryCount;
    private int httpRetryIntervalSeconds;
    private int maxTotalConnections;
    private int defaultMaxPerRoute;

    private String oAuthAppId;
    private String oAuthAppSecret;
    private String oAuthPermissions;
    private String oAuthAccessToken;
    private String oAuthCallbackURL;

    private boolean appSecretProofEnabled;
    private int appSecretProofCacheSize;

    private String oAuthAuthorizationURL;
    private String oAuthAccessTokenURL;
    private String oAuthAccessTokenInfoURL;
    private String oAuthDeviceTokenURL;

    private String restBaseURL;
    private String videoBaseURL;

    private boolean jsonStoreEnabled;

    private boolean mbeanEnabled;

    // hidden portion
    private String clientVersion;
    private String clientURL;

    public static final String DALVIK = "facebook4j.dalvik";
    public static final String GAE = "facebook4j.gae";
    
    private static final String DEFAULT_OAUTH_AUTHORIZATION_URL = "http://www.facebook.com/dialog/oauth";
    private static final String DEFAULT_OAUTH_ACCESS_TOKEN_URL = "http://graph.facebook.com/oauth/access_token";
    private static final String DEFAULT_OAUTH_ACCESS_TOKEN_INFO_URL = "http://graph.facebook.com/oauth/access_token_info";
    private static final String DEFAULT_OAUTH_DEVICE_TOKEN_URL = "http://graph.facebook.com/oauth/device";

    private static final String DEFAULT_REST_BASE_URL = "http://graph.facebook.com/";
    private static final String DEFAULT_VIDEO_BASE_URL = "http://graph-video.facebook.com/";

    private boolean IS_DALVIK;
    private boolean IS_GAE;

    static String dalvikDetected;
    static String gaeDetected;

    static {
        // detecting dalvik (Android platform)
        try {
            // dalvik.system.VMRuntime class should be existing on Android platform.
            // @see http://developer.android.com/reference/dalvik/system/VMRuntime.html
            Class.forName("dalvik.system.VMRuntime");
            dalvikDetected = "true";
        } catch (ClassNotFoundException cnfe) {
            dalvikDetected = "false";
        }

        // detecting Google App Engine
        try {
            Class.forName("com.google.appengine.api.urlfetch.URLFetchService");
            gaeDetected = "true";
        } catch (ClassNotFoundException cnfe) {
            gaeDetected = "false";
        }
    }

    protected ConfigurationBase() {
        setDebug(false);
        setUseSSL(true);
        setPrettyDebugEnabled(false);
        setGZIPEnabled(true);
        setHttpProxyHost(null);
        setHttpProxyUser(null);
        setHttpProxyPassword(null);
        setHttpProxyPort(-1);
        setHttpConnectionTimeout(20000);
        setHttpReadTimeout(120000);
        setHttpStreamingReadTimeout(40 * 1000);
        setHttpRetryCount(0);
        setHttpRetryIntervalSeconds(5);
        setHttpMaxTotalConnections(20);
        setHttpDefaultMaxPerRoute(2);
        setOAuthAppId(null);
        setOAuthAppSecret(null);
        setOAuthAccessToken(null);
        setOAuthCallbackURL(null);
        setAppSecretProofEnabled(false);
        setAppSecretProofCacheSize(10);
        setClientVersion(Version.getVersion());
        setClientURL("http://facebook4j.org/en/facebook4j-" + Version.getVersion() + ".xml");
        setUserAgent("facebook4j http://facebook4j.org/ /" + Version.getVersion());

        setJSONStoreEnabled(false);

        setMBeanEnabled(false);

        setOAuthAuthorizationURL(DEFAULT_OAUTH_AUTHORIZATION_URL);
        setOAuthAccessTokenURL(DEFAULT_OAUTH_ACCESS_TOKEN_URL);
        setOAuthAccessTokenInfoURL(DEFAULT_OAUTH_ACCESS_TOKEN_INFO_URL);
        setOAuthDeviceTokenURL(DEFAULT_OAUTH_DEVICE_TOKEN_URL);

        setRestBaseURL(DEFAULT_REST_BASE_URL);
        setVideoBaseURL(DEFAULT_VIDEO_BASE_URL);

        String isDalvik;
        try {
            isDalvik = System.getProperty(DALVIK, dalvikDetected);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
            isDalvik = dalvikDetected;
        }
        IS_DALVIK = Boolean.valueOf(isDalvik);

        String isGAE;
        try {
            isGAE = System.getProperty(GAE, gaeDetected);
        } catch (SecurityException ignore) {
            // Unsigned applets are not allowed to access System properties
            isGAE = gaeDetected;
        }
        IS_GAE = Boolean.valueOf(isGAE);

    }

    public final boolean isDalvik() {
        return IS_DALVIK;
    }

    public boolean isGAE() {
        return IS_GAE;
    }

    public final boolean isDebugEnabled() {
        return debug;
    }

    protected final void setDebug(boolean debug) {
        this.debug = debug;
    }

    public final String getUserAgent() {
        return this.userAgent;
    }

    protected final void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        initRequestHeaders();
    }

    public boolean isPrettyDebugEnabled() {
        return prettyDebug;
    }

    protected final void setUseSSL(boolean useSSL) {
        this.useSSL = useSSL;
        fixRestBaseURL();
    }

    protected final void setPrettyDebugEnabled(boolean prettyDebug) {
        this.prettyDebug = prettyDebug;
    }

    protected final void setGZIPEnabled(boolean gzipEnabled) {
        this.gzipEnabled = gzipEnabled;
        initRequestHeaders();
    }

    public boolean isGZIPEnabled() {
        return gzipEnabled;
    }

    // method for HttpRequestFactoryConfiguration
    Map<String, String> requestHeaders;

    private void initRequestHeaders() {
        requestHeaders = new HashMap<String, String>();

        requestHeaders.put("User-Agent", getUserAgent());
        if (gzipEnabled) {
            requestHeaders.put("Accept-Encoding", "gzip");
        }
        if (IS_DALVIK) {
            requestHeaders.put("Connection", "close");
        }
    }

    public Map<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    // methods for HttpClientConfiguration

    public final String getHttpProxyHost() {
        return httpProxyHost;
    }

    protected final void setHttpProxyHost(String proxyHost) {
        this.httpProxyHost = proxyHost;
    }

    public final String getHttpProxyUser() {
        return httpProxyUser;
    }

    protected final void setHttpProxyUser(String proxyUser) {
        this.httpProxyUser = proxyUser;
    }

    public final String getHttpProxyPassword() {
        return httpProxyPassword;
    }

    protected final void setHttpProxyPassword(String proxyPassword) {
        this.httpProxyPassword = proxyPassword;
    }

    public final int getHttpProxyPort() {
        return httpProxyPort;
    }

    protected final void setHttpProxyPort(int proxyPort) {
        this.httpProxyPort = proxyPort;
    }

    public final int getHttpConnectionTimeout() {
        return httpConnectionTimeout;
    }

    protected final void setHttpConnectionTimeout(int connectionTimeout) {
        this.httpConnectionTimeout = connectionTimeout;
    }

    public final int getHttpReadTimeout() {
        return httpReadTimeout;
    }

    protected final void setHttpReadTimeout(int readTimeout) {
        this.httpReadTimeout = readTimeout;
    }

    public int getHttpStreamingReadTimeout() {
        return httpStreamingReadTimeout;
    }

    protected final void setHttpStreamingReadTimeout(int httpStreamingReadTimeout) {
        this.httpStreamingReadTimeout = httpStreamingReadTimeout;
    }


    public final int getHttpRetryCount() {
        return httpRetryCount;
    }

    protected final void setHttpRetryCount(int retryCount) {
        this.httpRetryCount = retryCount;
    }

    public final int getHttpRetryIntervalSeconds() {
        return httpRetryIntervalSeconds;
    }

    protected final void setHttpRetryIntervalSeconds(int retryIntervalSeconds) {
        this.httpRetryIntervalSeconds = retryIntervalSeconds;
    }

    public final int getHttpMaxTotalConnections() {
        return maxTotalConnections;
    }

    protected final void setHttpMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public final int getHttpDefaultMaxPerRoute() {
        return defaultMaxPerRoute;
    }

    protected final void setHttpDefaultMaxPerRoute(int defaultMaxPerRoute) {
        this.defaultMaxPerRoute = defaultMaxPerRoute;
    }

    // oauth related setter/getters

    public final String getOAuthAppId() {
        return oAuthAppId;
    }

    protected final void setOAuthAppId(String oAuthAppId) {
        this.oAuthAppId = oAuthAppId;
        fixRestBaseURL();
    }

    public final String getOAuthAppSecret() {
        return oAuthAppSecret;
    }

    protected final void setOAuthAppSecret(String oAuthAppSecret) {
        this.oAuthAppSecret = oAuthAppSecret;
        fixRestBaseURL();
    }

    public String getOAuthAccessToken() {
        return oAuthAccessToken;
    }

    protected final void setOAuthAccessToken(String oAuthAccessToken) {
        this.oAuthAccessToken = oAuthAccessToken;
    }

    public final String getClientVersion() {
        return clientVersion;
    }

    protected final void setClientVersion(String clientVersion) {
        this.clientVersion = clientVersion;
        initRequestHeaders();
    }

    public final String getClientURL() {
        return clientURL;
    }

    protected final void setClientURL(String clientURL) {
        this.clientURL = clientURL;
        initRequestHeaders();
    }

    public String getRestBaseURL() {
        return restBaseURL;
    }

    public String getVideoBaseURL() {
        return videoBaseURL;
    }
    
    protected final void setRestBaseURL(String restBaseURL) {
        this.restBaseURL = restBaseURL;
        fixRestBaseURL();
    }

    private void fixRestBaseURL() {
        if (DEFAULT_REST_BASE_URL.equals(fixURL(false, restBaseURL))) {
            this.restBaseURL = fixURL(useSSL, restBaseURL);
        }
        if (DEFAULT_OAUTH_ACCESS_TOKEN_URL.equals(fixURL(false, oAuthAccessTokenURL))) {
            this.oAuthAccessTokenURL = fixURL(useSSL, oAuthAccessTokenURL);
        }
        if (DEFAULT_OAUTH_ACCESS_TOKEN_INFO_URL.equals(fixURL(false, oAuthAccessTokenInfoURL))) {
            this.oAuthAccessTokenInfoURL = fixURL(useSSL, oAuthAccessTokenInfoURL);
        }
        if (DEFAULT_OAUTH_DEVICE_TOKEN_URL.equals(fixURL(false, oAuthDeviceTokenURL))) {
            this.oAuthDeviceTokenURL = fixURL(useSSL, oAuthDeviceTokenURL);
        }
        if (DEFAULT_OAUTH_AUTHORIZATION_URL.equals(fixURL(false, oAuthAuthorizationURL))) {
            this.oAuthAuthorizationURL = fixURL(useSSL, oAuthAuthorizationURL);
        }
    }
    
    protected final void setVideoBaseURL(String videoBaseURL) {
        this.videoBaseURL = videoBaseURL;
        fixVideoBaseURL();
    }
    
    private void fixVideoBaseURL() {
        if (DEFAULT_VIDEO_BASE_URL.equals(fixURL(false, this.videoBaseURL))) {
            this.videoBaseURL = fixURL(useSSL, this.videoBaseURL);
        }
    }

    public String getOAuthAuthorizationURL() {
        return oAuthAuthorizationURL;
    }

    protected final void setOAuthAuthorizationURL(String oAuthAuthorizationURL) {
        this.oAuthAuthorizationURL = oAuthAuthorizationURL;
        fixRestBaseURL();
    }

    public String getOAuthAccessTokenURL() {
        return oAuthAccessTokenURL;
    }

    protected final void setOAuthAccessTokenURL(String oAuthAccessTokenURL) {
        this.oAuthAccessTokenURL = oAuthAccessTokenURL;
        fixRestBaseURL();
    }

    public String getOAuthAccessTokenInfoURL() {
        return oAuthAccessTokenInfoURL;
    }

    protected final void setOAuthAccessTokenInfoURL(String oAuthAccessTokenInfoURL) {
        this.oAuthAccessTokenInfoURL = oAuthAccessTokenInfoURL;
        fixRestBaseURL();
    }

    public String getOAuthDeviceTokenURL() {
        return oAuthDeviceTokenURL;
    }

    protected final void setOAuthDeviceTokenURL(String oAuthDeviceTokenURL) {
        this.oAuthDeviceTokenURL = oAuthDeviceTokenURL;
        fixRestBaseURL();
    }

    public boolean isJSONStoreEnabled() {
        return this.jsonStoreEnabled;
    }

    protected final void setJSONStoreEnabled(boolean enabled) {
        this.jsonStoreEnabled = enabled;
    }

    public boolean isMBeanEnabled() {
        return this.mbeanEnabled;
    }

    protected final void setMBeanEnabled(boolean enabled) {
        this.mbeanEnabled = enabled;
    }

    public String getOAuthPermissions() {
        return oAuthPermissions;
    }

    protected void setOAuthPermissions(String oAuthPermissions) {
        this.oAuthPermissions = oAuthPermissions;
    }

    public String getOAuthCallbackURL() {
        return this.oAuthCallbackURL;
    }

    public void setOAuthCallbackURL(String oAuthCallbackURL) {
        this.oAuthCallbackURL = oAuthCallbackURL;
    }

    public boolean isAppSecretProofEnabled() {
        return this.appSecretProofEnabled;
    }

    public void setAppSecretProofEnabled(boolean enabled) {
        this.appSecretProofEnabled = enabled;
    }

    public int getAppSecretProofCacheSize() {
        return this.appSecretProofCacheSize;
    }

    public void setAppSecretProofCacheSize(int appSecretProofCacheSize) {
        this.appSecretProofCacheSize = appSecretProofCacheSize;
    }

    static String fixURL(boolean useSSL, String url) {
        if (null == url) {
            return null;
        }
        int index = url.indexOf("://");
        if (-1 == index) {
            throw new IllegalArgumentException("url should contain '://'");
        }
        String hostAndLater = url.substring(index + 3);
        if (useSSL) {
            return "https://" + hostAndLater;
        } else {
            return "http://" + hostAndLater;
        }
    }

    @Override
    public int hashCode() {
        int result = (debug ? 1 : 0);
        result = 31 * result + (userAgent != null ? userAgent.hashCode() : 0);
        result = 31 * result + (useSSL ? 1 : 0);
        result = 31 * result + (prettyDebug ? 1 : 0);
        result = 31 * result + (gzipEnabled ? 1 : 0);
        result = 31 * result + (httpProxyHost != null ? httpProxyHost.hashCode() : 0);
        result = 31 * result + (httpProxyUser != null ? httpProxyUser.hashCode() : 0);
        result = 31 * result + (httpProxyPassword != null ? httpProxyPassword.hashCode() : 0);
        result = 31 * result + httpProxyPort;
        result = 31 * result + httpConnectionTimeout;
        result = 31 * result + httpReadTimeout;
        result = 31 * result + httpStreamingReadTimeout;
        result = 31 * result + httpRetryCount;
        result = 31 * result + httpRetryIntervalSeconds;
        result = 31 * result + maxTotalConnections;
        result = 31 * result + defaultMaxPerRoute;
        result = 31 * result + (oAuthAppId != null ? oAuthAppId.hashCode() : 0);
        result = 31 * result + (oAuthAppSecret != null ? oAuthAppSecret.hashCode() : 0);
        result = 31 * result + (oAuthPermissions != null ? oAuthPermissions.hashCode() : 0);
        result = 31 * result + (oAuthAccessToken != null ? oAuthAccessToken.hashCode() : 0);
        result = 31 * result + (oAuthCallbackURL != null ? oAuthCallbackURL.hashCode() : 0);
        result = 31 * result + (appSecretProofEnabled ? 1 : 0);
        result = 31 * result + appSecretProofCacheSize;
        result = 31 * result + (oAuthAuthorizationURL != null ? oAuthAuthorizationURL.hashCode() : 0);
        result = 31 * result + (oAuthAccessTokenURL != null ? oAuthAccessTokenURL.hashCode() : 0);
        result = 31 * result + (oAuthAccessTokenInfoURL != null ? oAuthAccessTokenInfoURL.hashCode() : 0);
        result = 31 * result + (oAuthDeviceTokenURL != null ? oAuthDeviceTokenURL.hashCode() : 0);
        result = 31 * result + (restBaseURL != null ? restBaseURL.hashCode() : 0);
        result = 31 * result + (videoBaseURL != null ? videoBaseURL.hashCode() : 0);
        result = 31 * result + (jsonStoreEnabled ? 1 : 0);
        result = 31 * result + (mbeanEnabled ? 1 : 0);
        result = 31 * result + (clientVersion != null ? clientVersion.hashCode() : 0);
        result = 31 * result + (clientURL != null ? clientURL.hashCode() : 0);
        result = 31 * result + (IS_DALVIK ? 1 : 0);
        result = 31 * result + (IS_GAE ? 1 : 0);
        result = 31 * result + (requestHeaders != null ? requestHeaders.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationBase)) return false;

        ConfigurationBase that = (ConfigurationBase) o;

        if (IS_DALVIK != that.IS_DALVIK) return false;
        if (IS_GAE != that.IS_GAE) return false;
        if (debug != that.debug) return false;
        if (defaultMaxPerRoute != that.defaultMaxPerRoute) return false;
        if (gzipEnabled != that.gzipEnabled) return false;
        if (httpConnectionTimeout != that.httpConnectionTimeout) return false;
        if (httpProxyPort != that.httpProxyPort) return false;
        if (httpReadTimeout != that.httpReadTimeout) return false;
        if (httpRetryCount != that.httpRetryCount) return false;
        if (httpRetryIntervalSeconds != that.httpRetryIntervalSeconds) return false;
        if (httpStreamingReadTimeout != that.httpStreamingReadTimeout) return false;
        if (jsonStoreEnabled != that.jsonStoreEnabled) return false;
        if (maxTotalConnections != that.maxTotalConnections) return false;
        if (mbeanEnabled != that.mbeanEnabled) return false;
        if (prettyDebug != that.prettyDebug) return false;
        if (useSSL != that.useSSL) return false;
        if (clientURL != null ? !clientURL.equals(that.clientURL) : that.clientURL != null) return false;
        if (clientVersion != null ? !clientVersion.equals(that.clientVersion) : that.clientVersion != null)
            return false;
        if (httpProxyHost != null ? !httpProxyHost.equals(that.httpProxyHost) : that.httpProxyHost != null)
            return false;
        if (httpProxyPassword != null ? !httpProxyPassword.equals(that.httpProxyPassword) : that.httpProxyPassword != null)
            return false;
        if (httpProxyUser != null ? !httpProxyUser.equals(that.httpProxyUser) : that.httpProxyUser != null)
            return false;
        if (oAuthAccessToken != null ? !oAuthAccessToken.equals(that.oAuthAccessToken) : that.oAuthAccessToken != null)
            return false;
        if (oAuthAccessTokenURL != null ? !oAuthAccessTokenURL.equals(that.oAuthAccessTokenURL) : that.oAuthAccessTokenURL != null)
            return false;
        if (oAuthAccessTokenInfoURL != null ? !oAuthAccessTokenInfoURL.equals(that.oAuthAccessTokenInfoURL) : that.oAuthAccessTokenInfoURL != null)
            return false;
        if (oAuthDeviceTokenURL != null ? !oAuthDeviceTokenURL.equals(that.oAuthDeviceTokenURL) : that.oAuthDeviceTokenURL != null)
            return false;
        if (oAuthAppId != null ? !oAuthAppId.equals(that.oAuthAppId) : that.oAuthAppId != null) return false;
        if (oAuthAppSecret != null ? !oAuthAppSecret.equals(that.oAuthAppSecret) : that.oAuthAppSecret != null)
            return false;
        if (oAuthAuthorizationURL != null ? !oAuthAuthorizationURL.equals(that.oAuthAuthorizationURL) : that.oAuthAuthorizationURL != null)
            return false;
        if (oAuthCallbackURL != null ? !oAuthCallbackURL.equals(that.oAuthCallbackURL) : that.oAuthCallbackURL != null)
            return false;
        if (appSecretProofEnabled != that.appSecretProofEnabled)
            return false;
        if (appSecretProofCacheSize != that.appSecretProofCacheSize) return false;
        if (oAuthPermissions != null ? !oAuthPermissions.equals(that.oAuthPermissions) : that.oAuthPermissions != null)
            return false;
        if (requestHeaders != null ? !requestHeaders.equals(that.requestHeaders) : that.requestHeaders != null)
            return false;
        if (restBaseURL != null ? !restBaseURL.equals(that.restBaseURL) : that.restBaseURL != null) return false;
        if (userAgent != null ? !userAgent.equals(that.userAgent) : that.userAgent != null) return false;
        if (videoBaseURL != null ? !videoBaseURL.equals(that.videoBaseURL) : that.videoBaseURL != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return "ConfigurationBase{" +
                "debug=" + debug +
                ", userAgent='" + userAgent + '\'' +
                ", useSSL=" + useSSL +
                ", prettyDebug=" + prettyDebug +
                ", gzipEnabled=" + gzipEnabled +
                ", httpProxyHost='" + httpProxyHost + '\'' +
                ", httpProxyUser='" + httpProxyUser + '\'' +
                ", httpProxyPassword='" + httpProxyPassword + '\'' +
                ", httpProxyPort=" + httpProxyPort +
                ", httpConnectionTimeout=" + httpConnectionTimeout +
                ", httpReadTimeout=" + httpReadTimeout +
                ", httpStreamingReadTimeout=" + httpStreamingReadTimeout +
                ", httpRetryCount=" + httpRetryCount +
                ", httpRetryIntervalSeconds=" + httpRetryIntervalSeconds +
                ", maxTotalConnections=" + maxTotalConnections +
                ", defaultMaxPerRoute=" + defaultMaxPerRoute +
                ", oAuthAppId='" + oAuthAppId + '\'' +
                ", oAuthAppSecret='" + oAuthAppSecret + '\'' +
                ", oAuthPermissions='" + oAuthPermissions + '\'' +
                ", oAuthAccessToken='" + oAuthAccessToken + '\'' +
                ", oAuthCallbackURL='" + oAuthCallbackURL + '\'' +
                ", appSecretProofEnabled=" + appSecretProofEnabled +
                ", appSecretProofCacheSize=" + appSecretProofCacheSize +
                ", oAuthAuthorizationURL='" + oAuthAuthorizationURL + '\'' +
                ", oAuthAccessTokenURL='" + oAuthAccessTokenURL + '\'' +
                ", oAuthAccessTokenInfoURL='" + oAuthAccessTokenInfoURL + '\'' +
                ", oAuthDeviceTokenURL='" + oAuthDeviceTokenURL + '\'' +
                ", restBaseURL='" + restBaseURL + '\'' +
                ", videoBaseURL='" + videoBaseURL + '\'' +
                ", jsonStoreEnabled=" + jsonStoreEnabled +
                ", mbeanEnabled=" + mbeanEnabled +
                ", clientVersion='" + clientVersion + '\'' +
                ", clientURL='" + clientURL + '\'' +
                ", IS_DALVIK=" + IS_DALVIK +
                ", IS_GAE=" + IS_GAE +
                ", requestHeaders=" + requestHeaders +
                '}';
    }

    private static final List<ConfigurationBase> instances = new ArrayList<ConfigurationBase>();

    private static void cacheInstance(ConfigurationBase conf) {
        if (!instances.contains(conf)) {
            instances.add(conf);
        }
    }

    protected void cacheInstance() {
        cacheInstance(this);
    }

    private static ConfigurationBase getInstance(ConfigurationBase configurationBase) {
        int index;
        if ((index = instances.indexOf(configurationBase)) == -1) {
            instances.add(configurationBase);
            return configurationBase;
        } else {
            return instances.get(index);
        }
    }

    // assures equality after deserializedation
    protected Object readResolve() throws ObjectStreamException {
        return getInstance(this);
    }

}
