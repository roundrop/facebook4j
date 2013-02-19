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

package facebook4j;


import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import facebook4j.auth.AccessToken;
import facebook4j.auth.Authorization;
import facebook4j.auth.AuthorizationFactory;
import facebook4j.auth.OAuthAuthorization;
import facebook4j.conf.Configuration;
import facebook4j.conf.ConfigurationContext;

/**
 * A factory class for Facebook.
 * <br>An instance of this class is completely thread safe and can be re-used and used concurrently.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @since Twitter4J 2.1.0
 * @author Ryuji Yamashita - roundrop at gmail.com
 * <ul>
 * <li>Changed for Facebook4J</li>
 * </ul>
 */
public final class FacebookFactory implements java.io.Serializable {
    private static final Constructor<Facebook> FACEBOOK_CONSTRUCTOR;
    /*AsyncFacebookFactory and TWitterStream will access this field*/
    static final Authorization DEFAULT_AUTHORIZATION = AuthorizationFactory.getInstance(ConfigurationContext.getInstance());
    private static final Facebook SINGLETON;
    private static final long serialVersionUID = 5193900138477709155L;
    private final Configuration conf;

    static {
        String className = null;
        if (ConfigurationContext.getInstance().isGAE()) {
            final String APP_ENGINE_TWITTER_IMPL = "facebook4j.AppEngineFacebookImpl";
            try {
                Class.forName(APP_ENGINE_TWITTER_IMPL);
                className = APP_ENGINE_TWITTER_IMPL;
            } catch (ClassNotFoundException ignore) {
            }
        }
        if (className == null) {
            className = "facebook4j.FacebookImpl";
        }
        Constructor<Facebook> constructor;
        Class clazz;
        try {
            clazz = Class.forName(className);
            constructor = clazz.getDeclaredConstructor(Configuration.class, Authorization.class);
        } catch (NoSuchMethodException e) {
            throw new AssertionError(e);
        } catch (ClassNotFoundException e) {
            throw new AssertionError(e);
        }
        FACEBOOK_CONSTRUCTOR = constructor;

        try {
            SINGLETON = FACEBOOK_CONSTRUCTOR.newInstance(ConfigurationContext.getInstance(), DEFAULT_AUTHORIZATION);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Creates a FacebookFactory with the root configuration.
     */
    public FacebookFactory() {
        this(ConfigurationContext.getInstance());
    }

    /**
     * Creates a FacebookFactory with the given configuration.
     *
     * @param conf the configuration to use
     * @since Twitter4J 2.1.1
     */
    public FacebookFactory(Configuration conf) {
        if (conf == null) {
            throw new NullPointerException("configuration cannot be null");
        }
        this.conf = conf;
    }

    /**
     * Creates a FacebookFactory with a specified config tree
     *
     * @param configTreePath the path
     */
    public FacebookFactory(String configTreePath) {
        this(ConfigurationContext.getInstance(configTreePath));
    }

    /**
     * Returns a instance associated with the configuration bound to this factory.
     *
     * @return default singleton instance
     */
    public Facebook getInstance() {
        return getInstance(AuthorizationFactory.getInstance(conf));
    }

    /**
     * Returns a OAuth Authenticated instance.<br>
     * app Id and app Secret must be provided by facebook4j.properties, or system properties.<br>
     * Unlike {@link Facebook#setOAuthAccessToken(facebook4j.auth.AccessToken)}, this factory method potentially returns a cached instance.
     *
     * @param accessToken access token
     * @return an instance
     * @since Twitter4J 2.1.9
     */
    public Facebook getInstance(AccessToken accessToken) {
        String appId = conf.getOAuthAppId();
        String appSecret = conf.getOAuthAppSecret();
        if (null == appId && null == appSecret) {
            throw new IllegalStateException("App id and App secret not supplied.");
        }
        OAuthAuthorization oauth = new OAuthAuthorization(conf);
        oauth.setOAuthAccessToken(accessToken);
        return getInstance(oauth);
    }

    public Facebook getInstance(Authorization auth) {
        try {
            return FACEBOOK_CONSTRUCTOR.newInstance(conf, auth);
        } catch (InstantiationException e) {
            throw new AssertionError(e);
        } catch (IllegalAccessException e) {
            throw new AssertionError(e);
        } catch (InvocationTargetException e) {
            throw new AssertionError(e);
        }
    }

    /**
     * Returns default singleton Facebook instance.
     *
     * @return default singleton Facebook instance
     * @since Twitter4J 2.2.4
     */
    public static Facebook getSingleton() {
        return SINGLETON;
    }
}
