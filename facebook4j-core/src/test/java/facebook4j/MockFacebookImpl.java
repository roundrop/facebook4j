/*
 * Copyright 2012 Ryuji Yamashita
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

package facebook4j;

import facebook4j.auth.Authorization;
import facebook4j.auth.MockAuthorization;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpParameter;
import facebook4j.internal.http.MockHttpClientWrapper;
import facebook4j.internal.http.RequestMethod;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
class MockFacebookImpl extends FacebookImpl implements MockFacebook {
    private static final long serialVersionUID = -215346849507336373L;

    MockFacebookImpl(Configuration conf, Authorization auth) {
        super(conf, auth);
    }

    @Override
    protected void setHttp() {
        http = new MockHttpClientWrapper(conf);
    }

    @Override
    public void setOAuthAppId(String appId, String appSecret) {
        super.setOAuthAppId(appId, appSecret);
        if (auth instanceof MockAuthorization) {
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            oauth.setOAuthAppId(appId, appSecret);
            this.auth = oauth;
        }
    }

    public void setAppSecretProofEnabled(boolean appSecretProofEnabled) {
        if (auth instanceof MockAuthorization) {
            OAuthAuthorization oauth = new OAuthAuthorization(conf);
            oauth.setAppSecretProofEnabled(appSecretProofEnabled);
            this.auth = oauth;
        } else
        if (auth instanceof OAuthAuthorization) {
            ((OAuthAuthorization) auth).setAppSecretProofEnabled(appSecretProofEnabled);
        }
    }

    public void setMockJSON(String resourceName) {
        ((MockHttpClientWrapper) http).setMockJSON(resourceName);
    }

    public RequestMethod getHttpMethod() {
        return ((MockHttpClientWrapper) http).getRequest().getMethod();
    }

    public URL getEndpointURL() {
        try {
            return new URL(((MockHttpClientWrapper) http).getRequest().getURL());
        } catch (MalformedURLException e) {
            throw new AssertionError();
        }
    }

    public HttpParameter[] getHttpParameters() {
        return ((MockHttpClientWrapper) http).getRequest().getParameters();
    }

}
