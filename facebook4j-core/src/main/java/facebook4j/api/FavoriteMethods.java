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

package facebook4j.api;

import facebook4j.Book;
import facebook4j.FacebookException;
import facebook4j.Game;
import facebook4j.Interest;
import facebook4j.Movie;
import facebook4j.Music;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Television;

public interface FavoriteMethods {
    /**
     * Returns the books listed on the current user's profile.
     * @return books
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - books
     */
    ResponseList<Book> getBooks() throws FacebookException;

    /**
     * Returns the books listed on the current user's profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return books
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - books
     */
    ResponseList<Book> getBooks(Reading reading) throws FacebookException;

    /**
     * Returns the books listed on a user's profile.
     * @param userId the ID of a user
     * @return books
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - books
     */
    ResponseList<Book> getBooks(String userId) throws FacebookException;

    /**
     * Returns the books listed on a user's profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return books
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - books
     */
    ResponseList<Book> getBooks(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the games the current user has added to the Arts and Entertainment section of their profile.
     * @return games
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - games
     */
    ResponseList<Game> getGames() throws FacebookException;

    /**
     * Returns the games the current user has added to the Arts and Entertainment section of their profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return games
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - games
     */
    ResponseList<Game> getGames(Reading reading) throws FacebookException;

    /**
     * Returns the games a user has added to the Arts and Entertainment section of their profile.
     * @param userId the ID of a user
     * @return games
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - games
     */
    ResponseList<Game> getGames(String userId) throws FacebookException;

    /**
     * Returns the games a user has added to the Arts and Entertainment section of their profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return games
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - games
     */
    ResponseList<Game> getGames(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the movies listed on the current user's profile.
     * @return movies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - movies
     */
    ResponseList<Movie> getMovies() throws FacebookException;

    /**
     * Returns the movies listed on the current user's profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return movies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - movies
     */
    ResponseList<Movie> getMovies(Reading reading) throws FacebookException;

    /**
     * Returns the movies listed on a user's profile.
     * @param userId the ID of a user
     * @return movies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - movies
     */
    ResponseList<Movie> getMovies(String userId) throws FacebookException;

    /**
     * Returns the movies listed on a user's profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return movies
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - movies
     */
    ResponseList<Movie> getMovies(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the music listed on the current user's profile.
     * @return music
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - music
     */
    ResponseList<Music> getMusic() throws FacebookException;

    /**
     * Returns the music listed on the current user's profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return music
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - music
     */
    ResponseList<Music> getMusic(Reading reading) throws FacebookException;

    /**
     * Returns the music listed on a user's profile.
     * @param userId the ID of a user
     * @return music
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - music
     */
    ResponseList<Music> getMusic(String userId) throws FacebookException;

    /**
     * Returns the music listed on a user's profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return music
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - music
     */
    ResponseList<Music> getMusic(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the television listed on the current user's profile.
     * @return television
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - television
     */
    ResponseList<Television> getTelevision() throws FacebookException;

    /**
     * Returns the television listed on the current user's profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return television
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - television
     */
    ResponseList<Television> getTelevision(Reading reading) throws FacebookException;

    /**
     * Returns the television listed on a user's profile.
     * @param userId the ID of a user
     * @return television
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - television
     */
    ResponseList<Television> getTelevision(String userId) throws FacebookException;

    /**
     * Returns the television listed on a user's profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return television
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - television
     */
    ResponseList<Television> getTelevision(String userId, Reading reading) throws FacebookException;


    /**
     * Returns the interests listed on the current user's profile.
     * @return interests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - interests
     */
    ResponseList<Interest> getInterests() throws FacebookException;

    /**
     * Returns the interests listed on the current user's profile.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return interests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - interests
     */
    ResponseList<Interest> getInterests(Reading reading) throws FacebookException;

    /**
     * Returns the interests listed on a user's profile.
     * @param userId the ID of a user
     * @return interests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - interests
     */
    ResponseList<Interest> getInterests(String userId) throws FacebookException;

    /**
     * Returns the interests listed on a user's profile.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return interests
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - interests
     */
    ResponseList<Interest> getInterests(String userId, Reading reading) throws FacebookException;

}
