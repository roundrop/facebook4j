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



/**
 * A builder that can be used to construct a twitter4j configuration with desired settings.  This
 * builder has sensible defaults such that {@code new ConfigurationBuilder().build()} would create a
 * usable configuration.  This configuration builder is useful for clients that wish to configure
 * twitter4j in unit tests or from command line flags for example.
 * 
 * @author John Sirois - john.sirois at gmail.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 *   <li>Changed for Facebook4J</li>
 * </ul>
 */
public final class ConfigurationBuilder {

    private ConfigurationBase configurationBean = new PropertyConfiguration();

    public ConfigurationBuilder setUseSSL(boolean useSSL) {
        checkNotBuilt();
        configurationBean.setUseSSL(useSSL);
        return this;
    }

    public ConfigurationBuilder setPrettyDebugEnabled(boolean prettyDebugEnabled) {
        checkNotBuilt();
        configurationBean.setPrettyDebugEnabled(prettyDebugEnabled);
        return this;
    }

    public ConfigurationBuilder setGZIPEnabled(boolean gzipEnabled) {
        checkNotBuilt();
        configurationBean.setGZIPEnabled(gzipEnabled);
        return this;
    }

    public ConfigurationBuilder setDebugEnabled(boolean debugEnabled) {
        checkNotBuilt();
        configurationBean.setDebug(debugEnabled);
        return this;
    }

    public ConfigurationBuilder setHttpProxyHost(String httpProxyHost) {
        checkNotBuilt();
        configurationBean.setHttpProxyHost(httpProxyHost);
        return this;
    }

    public ConfigurationBuilder setHttpProxyUser(String httpProxyUser) {
        checkNotBuilt();
        configurationBean.setHttpProxyUser(httpProxyUser);
        return this;
    }

    public ConfigurationBuilder setHttpProxyPassword(String httpProxyPassword) {
        checkNotBuilt();
        configurationBean.setHttpProxyPassword(httpProxyPassword);
        return this;
    }

    public ConfigurationBuilder setHttpProxyPort(int httpProxyPort) {
        checkNotBuilt();
        configurationBean.setHttpProxyPort(httpProxyPort);
        return this;
    }

    public ConfigurationBuilder setHttpConnectionTimeout(int httpConnectionTimeout) {
        checkNotBuilt();
        configurationBean.setHttpConnectionTimeout(httpConnectionTimeout);
        return this;
    }

    public ConfigurationBuilder setHttpReadTimeout(int httpReadTimeout) {
        checkNotBuilt();
        configurationBean.setHttpReadTimeout(httpReadTimeout);
        return this;
    }

    public ConfigurationBuilder setHttpStreamingReadTimeout(int httpStreamingReadTimeout) {
        checkNotBuilt();
        configurationBean.setHttpStreamingReadTimeout(httpStreamingReadTimeout);
        return this;
    }

    public ConfigurationBuilder setHttpRetryCount(int httpRetryCount) {
        checkNotBuilt();
        configurationBean.setHttpRetryCount(httpRetryCount);
        return this;
    }


    public ConfigurationBuilder setHttpMaxTotalConnections(int httpMaxConnections) {
        checkNotBuilt();
        configurationBean.setHttpMaxTotalConnections(httpMaxConnections);
        return this;
    }

    public ConfigurationBuilder setHttpDefaultMaxPerRoute(int httpDefaultMaxPerRoute) {
        checkNotBuilt();
        configurationBean.setHttpDefaultMaxPerRoute(httpDefaultMaxPerRoute);
        return this;
    }

    public ConfigurationBuilder setHttpRetryIntervalSeconds(int httpRetryIntervalSeconds) {
        checkNotBuilt();
        configurationBean.setHttpRetryIntervalSeconds(httpRetryIntervalSeconds);
        return this;
    }

    public ConfigurationBuilder setOAuthAppId(String oAuthAppId) {
        checkNotBuilt();
        configurationBean.setOAuthAppId(oAuthAppId);
        return this;
    }

    public ConfigurationBuilder setOAuthAppSecret(String oAuthAppSecret) {
        checkNotBuilt();
        configurationBean.setOAuthAppSecret(oAuthAppSecret);
        return this;
    }

    public ConfigurationBuilder setOAuthAccessToken(String oAuthAccessToken) {
        checkNotBuilt();
        configurationBean.setOAuthAccessToken(oAuthAccessToken);
        return this;
    }

    public ConfigurationBuilder setOAuthPermissions(String oAuthPermissions) {
        checkNotBuilt();
        configurationBean.setOAuthPermissions(oAuthPermissions);
        return this;
    }

    public ConfigurationBuilder setOAuthCallbackURL(String callbackURL) {
        checkNotBuilt();
        configurationBean.setOAuthCallbackURL(callbackURL);
        return this;
    }

    public ConfigurationBuilder setAppSecretProofEnabled(boolean appSecretProofEnabled) {
        checkNotBuilt();
        configurationBean.setAppSecretProofEnabled(appSecretProofEnabled);
        return this;
    }

    public ConfigurationBuilder setAppSecretProofCacheSize(int appSecretProofCacheSize) {
        checkNotBuilt();
        configurationBean.setAppSecretProofCacheSize(appSecretProofCacheSize);
        return this;
    }

    public ConfigurationBuilder setOAuthAuthorizationURL(String oAuthAuthorizationURL) {
        checkNotBuilt();
        configurationBean.setOAuthAuthorizationURL(oAuthAuthorizationURL);
        return this;
    }

    public ConfigurationBuilder setOAuthAccessTokenURL(String oAuthAccessTokenURL) {
        checkNotBuilt();
        configurationBean.setOAuthAccessTokenURL(oAuthAccessTokenURL);
        return this;
    }

    public ConfigurationBuilder setOAuthDeviceTokenURL(String oAuthDeviceTokenURL) {
        checkNotBuilt();
        configurationBean.setOAuthDeviceTokenURL(oAuthDeviceTokenURL);
        return this;
    }

    public ConfigurationBuilder setRestBaseURL(String restBaseURL) {
        checkNotBuilt();
        configurationBean.setRestBaseURL(restBaseURL);
        return this;
    }

    public ConfigurationBuilder setVideoBaseURL(String videoBaseURL) {
        checkNotBuilt();
        configurationBean.setVideoBaseURL(videoBaseURL);
        return this;
    }
    
    public ConfigurationBuilder setClientVersion(String clientVersion) {
        checkNotBuilt();
        configurationBean.setClientVersion(clientVersion);
        return this;
    }

    public ConfigurationBuilder setClientURL(String clientURL) {
        checkNotBuilt();
        configurationBean.setClientURL(clientURL);
        return this;
    }

    public ConfigurationBuilder setJSONStoreEnabled(boolean enabled) {
        checkNotBuilt();
        configurationBean.setJSONStoreEnabled(enabled);
        return this;
    }

    public ConfigurationBuilder setMBeanEnabled(boolean enabled) {
        checkNotBuilt();
        configurationBean.setMBeanEnabled(enabled);
        return this;
    }

    public Configuration build() {
        checkNotBuilt();
        configurationBean.cacheInstance();
        try {
            return configurationBean;
        } finally {
            configurationBean = null;
        }
    }

    private void checkNotBuilt() {
        if (configurationBean == null) {
            throw new IllegalStateException("Cannot use this builder any longer, build() has already been called");
        }
    }
}
