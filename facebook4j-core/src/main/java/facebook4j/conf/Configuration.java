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

package facebook4j.conf;


import facebook4j.auth.AuthorizationConfiguration;
import facebook4j.internal.http.HttpClientConfiguration;
import facebook4j.internal.http.HttpClientWrapperConfiguration;

/**
 * An interface represents configuration.
 * 
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Configuration extends HttpClientConfiguration
        , HttpClientWrapperConfiguration
        , AuthorizationConfiguration
        , java.io.Serializable {

    boolean isDalvik();

    boolean isGAE();

    boolean isDebugEnabled();

    String getUserAgent();


    String getClientVersion();

    String getClientURL();

    String getRestBaseURL();

    String getVideoBaseURL();
    
    String getOAuthAuthorizationURL();

    String getOAuthAccessTokenURL();

    String getOAuthAccessTokenInfoURL();

    String getOAuthDeviceTokenURL();

    boolean isJSONStoreEnabled();

    boolean isMBeanEnabled();

}
