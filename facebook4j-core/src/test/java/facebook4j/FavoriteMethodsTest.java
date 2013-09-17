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
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import static facebook4j.junit.ISO8601DateMatchers.*;
import static facebook4j.junit.URLMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

@RunWith(Enclosed.class)
public class FavoriteMethodsTest {

    public static class getBooks extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/books.json");
            ResponseList<Book> actuals = facebook.getBooks();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/books")));

            assertThat(actuals.size(), is(2));
            Book actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("222861334397059"));
            assertThat(actual1.getCategory(), is("Book"));
            assertThat(actual1.getName(), is("Refactoring: improving the design of existing code"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:37:20+0000")));
            Book actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("416221618453649"));
            assertThat(actual2.getCategory(), is("Book"));
            assertThat(actual2.getName(), is("Effective Java"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:33:20+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/book.json");
            ResponseList<Book> actuals = facebook.getBooks(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/books")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Book actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("222861334397059"));
            assertThat(actual1.getCategory(), is("Book"));
            assertThat(actual1.getName(), is("Refactoring: improving the design of existing code"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:37:20+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/books.json");
            ResponseList<Book> actuals = facebook.getBooks("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/books")));

            assertThat(actuals.size(), is(2));
            Book actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("222861334397059"));
            assertThat(actual1.getCategory(), is("Book"));
            assertThat(actual1.getName(), is("Refactoring: improving the design of existing code"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:37:20+0000")));
            Book actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("416221618453649"));
            assertThat(actual2.getCategory(), is("Book"));
            assertThat(actual2.getName(), is("Effective Java"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:33:20+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/book.json");
            ResponseList<Book> actuals = facebook.getBooks("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/books")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Book actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("222861334397059"));
            assertThat(actual1.getCategory(), is("Book"));
            assertThat(actual1.getName(), is("Refactoring: improving the design of existing code"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T04:37:20+0000")));
        }
    }

    public static class getGames extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/games.json");
            ResponseList<Game> actuals = facebook.getGames();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/games")));

            assertThat(actuals.size(), is(8));
            Game actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("130565650316009"));
            assertThat(actual1.getCategory(), is("Games/toys"));
            assertThat(actual1.getName(), is("We Rule"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-04-03T12:29:07+0000")));
            Game actual8 = actuals.get(7);
            assertThat(actual8.getId(), is("185143404835907"));
            assertThat(actual8.getCategory(), is("Games/toys"));
            assertThat(actual8.getName(), is("Cafe World"));
            assertThat(actual8.getCreatedTime(), is(iso8601DateOf("2011-02-02T14:23:49+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/game.json");
            ResponseList<Game> actuals = facebook.getGames(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/games")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Game actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("130565650316009"));
            assertThat(actual1.getCategory(), is("Games/toys"));
            assertThat(actual1.getName(), is("We Rule"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-04-03T12:29:07+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/games.json");
            ResponseList<Game> actuals = facebook.getGames("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/games")));

            assertThat(actuals.size(), is(8));
            Game actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("130565650316009"));
            assertThat(actual1.getCategory(), is("Games/toys"));
            assertThat(actual1.getName(), is("We Rule"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-04-03T12:29:07+0000")));
            Game actual8 = actuals.get(7);
            assertThat(actual8.getId(), is("185143404835907"));
            assertThat(actual8.getCategory(), is("Games/toys"));
            assertThat(actual8.getName(), is("Cafe World"));
            assertThat(actual8.getCreatedTime(), is(iso8601DateOf("2011-02-02T14:23:49+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/game.json");
            ResponseList<Game> actuals = facebook.getGames("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/games")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Game actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("130565650316009"));
            assertThat(actual1.getCategory(), is("Games/toys"));
            assertThat(actual1.getName(), is("We Rule"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-04-03T12:29:07+0000")));
        }
    }

    public static class getMovies extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/movies.json");
            ResponseList<Movie> actuals = facebook.getMovies();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/movies")));

            assertThat(actuals.size(), is(3));
            Movie actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("147252438687925"));
            assertThat(actual1.getCategory(), is("Movie"));
            assertThat(actual1.getName(), is("The Shawshank Redemption"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:24:11+0000")));
            Movie actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("217075251669293"));
            assertThat(actual3.getCategory(), is("Movie"));
            assertThat(actual3.getName(), is("Blade Runner"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:20:11+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/movie.json");
            ResponseList<Movie> actuals = facebook.getMovies(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/movies")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Movie actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("147252438687925"));
            assertThat(actual1.getCategory(), is("Movie"));
            assertThat(actual1.getName(), is("The Shawshank Redemption"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:24:11+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/movies.json");
            ResponseList<Movie> actuals = facebook.getMovies("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/movies")));

            assertThat(actuals.size(), is(3));
            Movie actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("147252438687925"));
            assertThat(actual1.getCategory(), is("Movie"));
            assertThat(actual1.getName(), is("The Shawshank Redemption"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:24:11+0000")));
            Movie actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("217075251669293"));
            assertThat(actual3.getCategory(), is("Movie"));
            assertThat(actual3.getName(), is("Blade Runner"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:20:11+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/movie.json");
            ResponseList<Movie> actuals = facebook.getMovies("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/movies")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Movie actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("147252438687925"));
            assertThat(actual1.getCategory(), is("Movie"));
            assertThat(actual1.getName(), is("The Shawshank Redemption"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-07-22T14:24:11+0000")));
        }
    }

    public static class getMusic extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/musics.json");
            ResponseList<Music> actuals = facebook.getMusic();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/music")));

            assertThat(actuals.size(), is(5));
            Music actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("138311299573076"));
            assertThat(actual1.getCategory(), is("Musician/band"));
            assertThat(actual1.getName(), is("Boom Boom Satellites"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-10-05T15:12:20+0000")));
            Music actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("178725115782"));
            assertThat(actual5.getCategory(), is("Musician/band"));
            assertThat(actual5.getName(), is("Ken Ishii"));
            assertThat(actual5.getCreatedTime(), is(iso8601DateOf("2011-02-21T16:18:28+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/music.json");
            ResponseList<Music> actuals = facebook.getMusic(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/music")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Music actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("138311299573076"));
            assertThat(actual1.getCategory(), is("Musician/band"));
            assertThat(actual1.getName(), is("Boom Boom Satellites"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-10-05T15:12:20+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/musics.json");
            ResponseList<Music> actuals = facebook.getMusic("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/music")));

            assertThat(actuals.size(), is(5));
            Music actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("138311299573076"));
            assertThat(actual1.getCategory(), is("Musician/band"));
            assertThat(actual1.getName(), is("Boom Boom Satellites"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-10-05T15:12:20+0000")));
            Music actual5 = actuals.get(4);
            assertThat(actual5.getId(), is("178725115782"));
            assertThat(actual5.getCategory(), is("Musician/band"));
            assertThat(actual5.getName(), is("Ken Ishii"));
            assertThat(actual5.getCreatedTime(), is(iso8601DateOf("2011-02-21T16:18:28+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/music.json");
            ResponseList<Music> actuals = facebook.getMusic("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/music")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Music actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("138311299573076"));
            assertThat(actual1.getCategory(), is("Musician/band"));
            assertThat(actual1.getName(), is("Boom Boom Satellites"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2011-10-05T15:12:20+0000")));
        }
    }

    public static class getTelevision extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/televisions.json");
            ResponseList<Television> actuals = facebook.getTelevision();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/television")));

            assertThat(actuals.size(), is(3));
            Television actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("103594929727422"));
            assertThat(actual1.getCategory(), is("Tv network"));
            assertThat(actual1.getName(), is("WOWOW"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-19T02:07:52+0000")));
            Television actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("101914609847998"));
            assertThat(actual3.getCategory(), is("Tv"));
            assertThat(actual3.getName(), is("Wbs"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2011-04-04T14:55:49+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/television.json");
            ResponseList<Television> actuals = facebook.getTelevision(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/television")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Television actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("103594929727422"));
            assertThat(actual1.getCategory(), is("Tv network"));
            assertThat(actual1.getName(), is("WOWOW"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-19T02:07:52+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/televisions.json");
            ResponseList<Television> actuals = facebook.getTelevision("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/television")));

            assertThat(actuals.size(), is(3));
            Television actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("103594929727422"));
            assertThat(actual1.getCategory(), is("Tv network"));
            assertThat(actual1.getName(), is("WOWOW"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-19T02:07:52+0000")));
            Television actual3 = actuals.get(2);
            assertThat(actual3.getId(), is("101914609847998"));
            assertThat(actual3.getCategory(), is("Tv"));
            assertThat(actual3.getName(), is("Wbs"));
            assertThat(actual3.getCreatedTime(), is(iso8601DateOf("2011-04-04T14:55:49+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/television.json");
            ResponseList<Television> actuals = facebook.getTelevision("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/television")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Television actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("103594929727422"));
            assertThat(actual1.getCategory(), is("Tv network"));
            assertThat(actual1.getName(), is("WOWOW"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2012-11-19T02:07:52+0000")));
        }
    }

    public static class getInterests extends MockFacebookTestBase {
        @Test
        public void me() throws Exception {
            facebook.setMockJSON("mock_json/favorite/interests.json");
            ResponseList<Interest> actuals = facebook.getInterests();
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/interests")));

            assertThat(actuals.size(), is(2));
            Interest actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("108712535819960"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open-source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T05:07:24+0000")));
            Interest actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("101882226520576"));
            assertThat(actual2.getCategory(), is("Interest"));
            assertThat(actual2.getName(), is("Programming"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-30T03:39:31+0000")));
        }

        @Test
        public void me_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/interest.json");
            ResponseList<Interest> actuals = facebook.getInterests(new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/me/interests")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Interest actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("108712535819960"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open-source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T05:07:24+0000")));
        }

        @Test
        public void id() throws Exception {
            facebook.setMockJSON("mock_json/favorite/interests.json");
            ResponseList<Interest> actuals = facebook.getInterests("1234567890123456");
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/interests")));

            assertThat(actuals.size(), is(2));
            Interest actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("108712535819960"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open-source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T05:07:24+0000")));
            Interest actual2 = actuals.get(1);
            assertThat(actual2.getId(), is("101882226520576"));
            assertThat(actual2.getCategory(), is("Interest"));
            assertThat(actual2.getName(), is("Programming"));
            assertThat(actual2.getCreatedTime(), is(iso8601DateOf("2013-07-30T03:39:31+0000")));
        }

        @Test
        public void id_reading() throws Exception {
            facebook.setMockJSON("mock_json/favorite/interest.json");
            ResponseList<Interest> actuals = facebook.getInterests("1234567890123456", new Reading().limit(1));
            assertThat(facebook.getHttpMethod(), is(RequestMethod.GET));
            assertThat(facebook.getEndpointURL(), is(pathOf("/1234567890123456/interests")));
            assertThat(facebook.getEndpointURL(), hasParameter("limit", "1"));

            assertThat(actuals.size(), is(1));
            Interest actual1 = actuals.get(0);
            assertThat(actual1.getId(), is("108712535819960"));
            assertThat(actual1.getCategory(), is("Interest"));
            assertThat(actual1.getName(), is("Open-source software"));
            assertThat(actual1.getCreatedTime(), is(iso8601DateOf("2013-08-12T05:07:24+0000")));
        }
    }

}
