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
import org.junit.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * @author Yusuke Yamamoto - yusuke at mac.com
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public class ConfigurationTest {

    @Test
    public void getInstance() {
        Configuration conf = ConfigurationContext.getInstance();
        assertThat(conf, is(notNullValue()));
    }
    
    @Test
    public void fixURL() {
        assertThat(ConfigurationBase.fixURL(false, "http://www.facebook.com"), is("http://www.facebook.com"));
        assertThat(ConfigurationBase.fixURL(false, "https://www.facebook.com"), is("http://www.facebook.com"));
        assertThat(ConfigurationBase.fixURL(true, "http://www.facebook.com"), is("https://www.facebook.com"));
        assertThat(ConfigurationBase.fixURL(true, "https://www.facebook.com"), is("https://www.facebook.com"));
        assertThat(ConfigurationBase.fixURL(false, null), is(nullValue()));
        assertThat(ConfigurationBase.fixURL(true, null), is(nullValue()));
    }

    @Test
    public void configuration() throws Exception {
        ConfigurationBase conf;

        String test = "f4j";
        String override = "system property";

        System.getProperties().remove("facebook4j.clientVersion");
        conf = new PropertyConfiguration();
        assertThat(conf.getClientVersion(), is(Version.getVersion()));

        conf.setClientVersion(test);
        assertThat(conf.getClientVersion(), is(test));
        System.setProperty("facebook4j.clientVersion", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getClientVersion(), is(override));
        conf.setClientVersion(test);
        assertThat(conf.getClientVersion(), is(test));
        System.getProperties().remove("facebook4j.clientVersion");

        System.getProperties().remove("facebook4j.clientURL");
        conf = new PropertyConfiguration();
        assertThat(conf.getClientURL(), is("http://facebook4j.org/en/facebook4j-" + Version.getVersion() + ".xml"));
        conf.setClientURL(test);
        assertThat(conf.getClientURL(), is(test));
        System.setProperty("facebook4j.clientURL", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getClientURL(), is(override));
        conf.setClientURL(test);
        assertThat(conf.getClientURL(), is(test));
        System.getProperties().remove("facebook4j.clientURL");

        System.getProperties().remove("facebook4j.http.userAgent");
        conf = new PropertyConfiguration();
        assertThat(conf.getUserAgent(), is("facebook4j http://facebook4j.org/ /" + Version.getVersion()));
        conf.setUserAgent(test);
        assertThat(conf.getUserAgent(), is(test));
        System.setProperty("facebook4j.http.userAgent", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getUserAgent(), is(override));
        conf.setUserAgent(test);
        assertThat(conf.getUserAgent(), is(test));
        System.getProperties().remove("facebook4j.http.userAgent");

        System.getProperties().remove("facebook4j.http.proxyHost");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyHost(), is(nullValue()));
        System.setProperty("facebook4j.http.proxyHost", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyHost(), is(override));
        System.getProperties().remove("facebook4j.http.proxyHost");

        System.getProperties().remove("facebook4j.http.proxyPort");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyPort(), is(-1));
        System.setProperty("facebook4j.http.proxyPort", "100");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyPort(), is(100));
        System.getProperties().remove("facebook4j.http.proxyPort");

        System.getProperties().remove("facebook4j.http.proxyUser");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyUser(), is(nullValue()));
        assertEquals(null, conf.getHttpProxyUser());
        System.setProperty("facebook4j.http.proxyUser", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyUser(), is(override));
        System.getProperties().remove("facebook4j.http.proxyUser");

        System.getProperties().remove("facebook4j.http.proxyPassword");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyPassword(), is(nullValue()));
        System.setProperty("facebook4j.http.proxyPassword", override);
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpProxyPassword(), is(override));
        System.getProperties().remove("facebook4j.http.proxyPassword");

        System.getProperties().remove("facebook4j.http.connectionTimeout");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpConnectionTimeout(), is(20000));
        conf.setHttpConnectionTimeout(10);
        assertThat(conf.getHttpConnectionTimeout(), is(10));
        System.setProperty("facebook4j.http.connectionTimeout", "100");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpConnectionTimeout(), is(100));
        conf.setHttpConnectionTimeout(10);
        assertThat(conf.getHttpConnectionTimeout(), is(10));
        System.getProperties().remove("facebook4j.http.connectionTimeout");

        System.getProperties().remove("facebook4j.http.readTimeout");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpReadTimeout(), is(120000));
        conf.setHttpReadTimeout(10);
        assertThat(conf.getHttpReadTimeout(), is(10));
        System.setProperty("facebook4j.http.readTimeout", "100");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpReadTimeout(), is(100));
        conf.setHttpReadTimeout(10);
        assertThat(conf.getHttpReadTimeout(), is(10));
        System.getProperties().remove("facebook4j.http.readTimeout");

        assertFalse(conf.isDalvik());

        writeFile("./facebook4j.properties", "facebook4j.http.readTimeout=1234");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpReadTimeout(), is(1234));
        writeFile("./facebook4j.properties", "facebook4j.http.readTimeout=4321");
        conf = new PropertyConfiguration();
        assertThat(conf.getHttpReadTimeout(), is(4321));

        writeFile("./facebook4j.properties", "oauth.callbackURL=http://localhost:8080/callback");
        conf = new PropertyConfiguration();
        assertThat(conf.getOAuthCallbackURL(), is("http://localhost:8080/callback"));

        writeFile("./facebook4j.properties", "oauth.accessTokenInfoURL=https://graph.facebook.com/oauth/access_token_info2");
        conf = new PropertyConfiguration();
        assertThat(conf.getOAuthAccessTokenInfoURL(), is("https://graph.facebook.com/oauth/access_token_info2"));

        writeFile("./facebook4j.properties", "oauth.deviceTokenURL=https://graph.facebook.com/oauth/deviceA");
        conf = new PropertyConfiguration();
        assertThat(conf.getOAuthDeviceTokenURL(), is("https://graph.facebook.com/oauth/deviceA"));

        conf = new PropertyConfiguration();
        assertThat(conf.isAppSecretProofEnabled(), is(false));
        writeFile("./facebook4j.properties", "security.appSecretProofEnabled=true");
        conf = new PropertyConfiguration();
        assertThat(conf.isAppSecretProofEnabled(), is(true));
        writeFile("./facebook4j.properties", "security.appSecretProofEnabled=false");
        conf = new PropertyConfiguration();
        assertThat(conf.isAppSecretProofEnabled(), is(false));

        conf = new PropertyConfiguration();
        assertThat(conf.getAppSecretProofCacheSize(), is(10));
        writeFile("./facebook4j.properties", "security.appSecretProofCacheSize=50");
        conf = new PropertyConfiguration();
        assertThat(conf.getAppSecretProofCacheSize(), is(50));

        deleteFile("./facebook4j.properties");
    }

    @Test
    public void environmentVariableBasedConfiguration() throws Exception{
        Configuration conf = ConfigurationContext.getInstance();
        // TODO
    }

    private void writeFile(String path, String content) throws IOException {
        File file = new File(path);
        file.delete();
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        bw.write(content);
        bw.close();
    }

    private void deleteFile(String path) throws IOException {
        File file = new File(path);
        file.delete();
    }
}
