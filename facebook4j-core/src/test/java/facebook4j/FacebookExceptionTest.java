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

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class FacebookExceptionTest {
    public static class encodedMessage {
        @Test
        public void withoutSubcode() throws Exception {
            FacebookException fe = new FacebookException(
                    "{" +
                        "\"error\": {" +
                            "\"message\": \"Invalid OAuth access token.\", " +
                            "\"type\": \"OAuthException\", " +
                            "\"code\": 190" +
                        "}" +
                    "}");
            assertThat(fe.getErrorMessage(), is("Invalid OAuth access token."));
            assertThat(fe.getErrorType(), is("OAuthException"));
            assertThat(fe.getErrorCode(), is(190));
            assertThat(fe.getErrorSubcode(), is(-1));

            assertThat(fe.toString().startsWith(
                        "message - Invalid OAuth access token.\n" +
                        "code - 190\n" +
                        "Relevant"),
                        is(true));
        }

        @Test
        public void withSubcode() throws Exception {
            FacebookException fe = new FacebookException(
                    "{" +
                        "\"error\": {" +
                            "\"message\": \"Message describing the error\", " +
                            "\"type\": \"OAuthException\", " +
                            "\"code\": 190 , " +
                            "\"error_subcode\": 460" +
                        "}" +
                    "}");
            assertThat(fe.getErrorMessage(), is("Message describing the error"));
            assertThat(fe.getErrorType(), is("OAuthException"));
            assertThat(fe.getErrorCode(), is(190));
            assertThat(fe.getErrorSubcode(), is(460));

            assertThat(fe.toString().startsWith(
                    "message - Message describing the error\n" +
                            "code - 190\n" +
                            "subcode - 460\n" +
                            "Relevant"),
                    is(true));
        }
    }

    public static class withCause {
        @Test
        public void nested() throws Exception {
            FacebookException fe = new FacebookException(new IllegalStateException("test"));
            assertThat(fe.getCause(), instanceOf(IllegalStateException.class));
            assertThat(fe.getMessage(), is("test"));
        }

        @Test
        public void withMessage() throws Exception {
            FacebookException fe = new FacebookException(
                    "{" +
                        "\"error\": {" +
                            "\"message\": \"Invalid OAuth access token.\", " +
                            "\"type\": \"OAuthException\", " +
                            "\"code\": 190" +
                        "}" +
                    "}",
                    new IllegalStateException("test"));
            assertThat(fe.getMessage().startsWith(
                        "message - Invalid OAuth access token.\n" +
                        "code - 190\n" +
                        "Relevant"),
                        is(true));
            assertThat(fe.getCause(), instanceOf(IllegalStateException.class));
            assertThat(fe.getCause().getMessage(), is("test"));
            assertThat(fe.getErrorMessage(), is("Invalid OAuth access token."));
        }
    }

}
