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

import static facebook4j.junit.ISO8601DateMatchers.iso8601DateOf;
import static facebook4j.junit.URLMatchers.pathOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import facebook4j.internal.http.RequestMethod;

@RunWith(Enclosed.class)
public class ConversationMethodsTest {

    public static class getMessage extends MockFacebookTestBase {
        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/conversation/conversation.json");
            Conversation actual = facebook.getConversation("t_mid.1234567890123:abcdef1234567890ab");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/t_mid.1234567890123:abcdef1234567890ab")));
            assertThat(actual.getId(), is("t_mid.1234567890123:abcdef1234567890ab"));
            assertThat(actual.getMessageCount(), is(1));
            assertThat(actual.getUpdatedTime(), iso8601DateOf("2013-09-27T12:10:35+0000"));
            assertThat(actual.getMessages().size(), is(1));
            assertThat(actual.getMessages().get(0).getId(), is("m_mid.2345678901234:abcdef1234567890ab"));
            assertThat(actual.getMessages().get(0).getMessage(), is("Message 1"));
            assertThat(actual.getMessages().get(0).getFrom().getId(), is("3456789012345"));
            assertThat(actual.getMessages().get(0).getFrom().getName(), is("Name Name1"));
            assertThat(actual.getMessages().get(0).getCreatedTime(), iso8601DateOf("2013-09-27T12:10:35+0000"));
            assertThat(actual.getSenders().size(), is(2));
            assertThat(actual.getSenders().get(0).getId(), is("34567890123456789"));
            assertThat(actual.getSenders().get(1).getId(), is("45678901234567890"));
        }

        @Test
        public void equal() throws Exception {
            facebook.setMockJSON("mock_json/conversation/conversation.json");
            Conversation conversation1 = facebook.getConversation("t_mid.1234567890123:abcdef1234567890ab");
            assertThat(conversation1, is(conversation1));

            Conversation conversation2 = facebook.getConversation("t_mid.1234567890123:abcdef1234567890ab");
            assertThat(conversation1, not(sameInstance(conversation2)));
            assertThat(conversation1, is(conversation2));
        }
    }
}
