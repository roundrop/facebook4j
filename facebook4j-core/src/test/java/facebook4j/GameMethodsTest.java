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

import facebook4j.internal.http.RequestMethod;
import facebook4j.junit.FacebookAPIVersion;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.net.URL;

import static facebook4j.junit.F4JHttpParameterMatchers.*;
import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class GameMethodsTest {

    public static class getAchievements extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/game/achievements.json");
            ResponseList<Achievement> actuals = facebook.getAchievements();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/achievements")));

            assertThat(actuals.size(), is(21));
            Achievement actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("507789189268254"));
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getAchievement().getId(), is("10150316120761112"));
            assertThat(actual1.getAchievement().getTitle(), is("Level 71 Badge"));
            assertThat(actual1.getAchievement().getType(), is("game.achievement"));
            assertThat(actual1.getAchievement().getUrl().toString(), is("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=71"));
            assertThat(actual1.getPublishTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
            assertThat(actual1.getImportance(), is(0));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
//            assertThat(actual1.getLikes().isUserLikes(), is(false));
//            assertThat(actual1.getLikes().canLike(), is(true));
            assertThat(actual1.getLikes().getCount(), is(0));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
//            assertThat(actual1.isNoFeedStory(), is(false));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
//            assertThat(actual1.getType(), is("games.achieves"));
//            assertThat(actual1.getComments().getCommentOrder(), is("chronological"));
            assertThat(actual1.getComments().getCount(), is(0));
//            assertThat(actual1.getComments().canComment(), is(true));
            Achievement actual21 = actuals.get(20);
            assertThat(actual21.getId(), is("341031135944061"));
            assertThat(actual21.getApplication().getId(), is("291549705119"));
            assertThat(actual21.getApplication().getName(), is("CityVille"));
            assertThat(actual21.getApplication().getNamespace(), is("cityville"));
            assertThat(actual21.getAchievement().getId(), is("10150369542425266"));
            assertThat(actual21.getAchievement().getTitle(), is("Level 53 Badge"));
            assertThat(actual21.getAchievement().getType(), is("game.achievement"));
            assertThat(actual21.getAchievement().getUrl().toString(), is("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=53"));
            assertThat(actual21.getPublishTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
            assertThat(actual21.getImportance(), is(0));
            assertThat(actual21.getEndTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
//            assertThat(actual21.getLikes().isUserLikes(), is(false));
//            assertThat(actual21.getLikes().canLike(), is(true));
            assertThat(actual21.getLikes().getCount(), is(0));
            assertThat(actual21.getStartTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
//            assertThat(actual21.isNoFeedStory(), is(false));
            assertThat(actual21.getFrom().getId(), is("1234567890123456"));
            assertThat(actual21.getFrom().getName(), is("Name Name1"));
//            assertThat(actual21.getType(), is("games.achieves"));
//            assertThat(actual21.getComments().getCommentOrder(), is("chronological"));
            assertThat(actual21.getComments().getCount(), is(0));
//            assertThat(actual21.getComments().canComment(), is(true));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/game/achievement.json");
            ResponseList<Achievement> actuals = facebook.getAchievements(new Reading().fields("application").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/achievements")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "application"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Achievement actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("507789189268254"));
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/game/achievements.json");
            ResponseList<Achievement> actuals = facebook.getAchievements("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/achievements")));

            assertThat(actuals.size(), is(21));
            Achievement actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("507789189268254"));
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getAchievement().getId(), is("10150316120761112"));
            assertThat(actual1.getAchievement().getTitle(), is("Level 71 Badge"));
            assertThat(actual1.getAchievement().getType(), is("game.achievement"));
            assertThat(actual1.getAchievement().getUrl().toString(), is("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=71"));
            assertThat(actual1.getPublishTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
            assertThat(actual1.getImportance(), is(0));
            assertThat(actual1.getEndTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
//            assertThat(actual1.getLikes().isUserLikes(), is(false));
//            assertThat(actual1.getLikes().canLike(), is(true));
            assertThat(actual1.getLikes().getCount(), is(0));
            assertThat(actual1.getStartTime(), is(iso8601DateOf("2013-05-20T07:57:09+0000")));
//            assertThat(actual1.isNoFeedStory(), is(false));
            assertThat(actual1.getFrom().getId(), is("1234567890123456"));
            assertThat(actual1.getFrom().getName(), is("Name Name1"));
//            assertThat(actual1.getType(), is("games.achieves"));
//            assertThat(actual1.getComments().getCommentOrder(), is("chronological"));
            assertThat(actual1.getComments().getCount(), is(0));
//            assertThat(actual1.getComments().canComment(), is(true));
            Achievement actual21 = actuals.get(20);
            assertThat(actual21.getId(), is("341031135944061"));
            assertThat(actual21.getApplication().getId(), is("291549705119"));
            assertThat(actual21.getApplication().getName(), is("CityVille"));
            assertThat(actual21.getApplication().getNamespace(), is("cityville"));
            assertThat(actual21.getAchievement().getId(), is("10150369542425266"));
            assertThat(actual21.getAchievement().getTitle(), is("Level 53 Badge"));
            assertThat(actual21.getAchievement().getType(), is("game.achievement"));
            assertThat(actual21.getAchievement().getUrl().toString(), is("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=53"));
            assertThat(actual21.getPublishTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
            assertThat(actual21.getImportance(), is(0));
            assertThat(actual21.getEndTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
//            assertThat(actual21.getLikes().isUserLikes(), is(false));
//            assertThat(actual21.getLikes().canLike(), is(true));
            assertThat(actual21.getLikes().getCount(), is(0));
            assertThat(actual21.getStartTime(), is(iso8601DateOf("2012-04-11T07:48:10+0000")));
//            assertThat(actual21.isNoFeedStory(), is(false));
            assertThat(actual21.getFrom().getId(), is("1234567890123456"));
            assertThat(actual21.getFrom().getName(), is("Name Name1"));
//            assertThat(actual21.getType(), is("games.achieves"));
//            assertThat(actual21.getComments().getCommentOrder(), is("chronological"));
            assertThat(actual21.getComments().getCount(), is(0));
//            assertThat(actual21.getComments().canComment(), is(true));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/game/achievement.json");
            ResponseList<Achievement> actuals = facebook.getAchievements("100001115315101", new Reading().fields("application").limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/100001115315101/achievements")));
            assertThat(facebook.getEndpointURL(), hasParameter("fields", "application"));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Achievement actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("507789189268254"));
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
        }
    }

    public static class postAchievement extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.postAchievement(new URL("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/achievements")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("achievement", "http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));

            assertThat(actual, is("1234567890123456"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/id.json");
            String actual = facebook.postAchievement("1234567890123456", new URL("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/achievements")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("achievement", "http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));

            assertThat(actual, is("1234567890123456"));
        }
    }

    public static class deleteAchievement extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteAchievement(new URL("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/achievements")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteAchievement("1234567890123456", new URL("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/achievements")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteAchievement("1234567890123456", new URL("http://fb-client-0.cityville.zynga.com/fbAchievement.php?id=70"));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/1234567890123456/achievements")));

            assertThat(actual, is(true));
        }
    }

    public static class getScores extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/game/scores.json");
            ResponseList<Score> actuals = facebook.getScores();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/scores")));

            assertThat(actuals.size(), is(2));
            Score actual1 = actuals.get(0);
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getScore(), is(256));
            assertThat(actual1.getType(), is("score"));
            assertThat(actual1.getUser().getId(), is("1234567890123456"));
            assertThat(actual1.getUser().getName(), is("Name Name1"));
            Score actual2 = actuals.get(1);
            assertThat(actual2.getApplication().getId(), is("266989143414"));
            assertThat(actual2.getApplication().getName(), is("Pioneer Trail"));
            assertThat(actual2.getApplication().getNamespace(), is("pioneertrail"));
            assertThat(actual2.getScore(), is(65535));
            assertThat(actual2.getType(), is("score"));
            assertThat(actual2.getUser().getId(), is("1234567890123456"));
            assertThat(actual2.getUser().getName(), is("Name Name1"));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/game/score.json");
            ResponseList<Score> actuals = facebook.getScores(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/scores")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Score actual1 = actuals.get(0);
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getScore(), is(256));
            assertThat(actual1.getType(), is("score"));
            assertThat(actual1.getUser().getId(), is("1234567890123456"));
            assertThat(actual1.getUser().getName(), is("Name Name1"));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/game/scores.json");
            ResponseList<Score> actuals = facebook.getScores("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/scores")));

            assertThat(actuals.size(), is(2));
            Score actual1 = actuals.get(0);
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getScore(), is(256));
            assertThat(actual1.getType(), is("score"));
            assertThat(actual1.getUser().getId(), is("1234567890123456"));
            assertThat(actual1.getUser().getName(), is("Name Name1"));
            Score actual2 = actuals.get(1);
            assertThat(actual2.getApplication().getId(), is("266989143414"));
            assertThat(actual2.getApplication().getName(), is("Pioneer Trail"));
            assertThat(actual2.getApplication().getNamespace(), is("pioneertrail"));
            assertThat(actual2.getScore(), is(65535));
            assertThat(actual2.getType(), is("score"));
            assertThat(actual2.getUser().getId(), is("1234567890123456"));
            assertThat(actual2.getUser().getName(), is("Name Name1"));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/game/score.json");
            ResponseList<Score> actuals = facebook.getScores("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/scores")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Score actual1 = actuals.get(0);
            assertThat(actual1.getApplication().getId(), is("291549705119"));
            assertThat(actual1.getApplication().getName(), is("CityVille"));
            assertThat(actual1.getApplication().getNamespace(), is("cityville"));
            assertThat(actual1.getScore(), is(256));
            assertThat(actual1.getType(), is("score"));
            assertThat(actual1.getUser().getId(), is("1234567890123456"));
            assertThat(actual1.getUser().getName(), is("Name Name1"));
        }
    }

    public static class postScore extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.postScore(65535);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/scores")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("score", "65535"));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.postScore("1234567890123456", 65535);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/scores")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("score", "65535"));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.postScore("1234567890123456", 65535);
            assertThat(facebook.getHttpMethod(), is(RequestMethod.POST));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/1234567890123456/scores")));
            assertThat(facebook.getHttpParameters(), hasPostParameter("score", "65535"));

            assertThat(actual, is(true));
        }
    }

    public static class deleteScore extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteScore();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/scores")));

            assertThat(actual, is(true));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/true.json");
            boolean actual = facebook.deleteScore("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/scores")));

            assertThat(actual, is(true));
        }

        @Test
        @FacebookAPIVersion("v2.3")
        public void id_v23() throws Exception {
            facebook.setMockJSON("mock_json/success.json");
            boolean actual = facebook.deleteScore("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.DELETE));
            assertThat(facebook.getEndpointURL(), is(pathOf("/v2.3/1234567890123456/scores")));

            assertThat(actual, is(true));
        }
    }

}
