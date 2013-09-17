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

import facebook4j.Achievement;
import facebook4j.FacebookException;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Score;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface GameMethods {
    /**
     * Returns the achievements for the current user.
     * @return achievements
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    ResponseList<Achievement> getAchievements() throws FacebookException;

    /**
     * Returns the achievements for the current user.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return achievements
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    ResponseList<Achievement> getAchievements(Reading reading) throws FacebookException;

    /**
     * Returns the achievements for a user.
     * @param userId the ID of a user
     * @return achievements
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    ResponseList<Achievement> getAchievements(String userId) throws FacebookException;

    /**
     * Returns the achievements for a user.
     * @param userId the ID of a user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return achievements
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    ResponseList<Achievement> getAchievements(String userId, Reading reading) throws FacebookException;


    /**
     * Posts the achievement for the current user.
     * @param achievementURL the unique URL of the achievement which the current user achieved
     * @return achievement (instance) ID for the current user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    String postAchievement(URL achievementURL) throws FacebookException;

    /**
     * Posts the achievement for a user.
     * @param userId the ID of a user
     * @param achievementURL the unique URL of the achievement which the user achieved
     * @return achievement (instance) ID for the user
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    String postAchievement(String userId, URL achievementURL) throws FacebookException;

    /**
     * Deletes the achievement for the current user.
     * @param achievementURL the unique URL of the achievement to delete
     * @return true if delete is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    boolean deleteAchievement(URL achievementURL) throws FacebookException;

    /**
     * Deletes the achievement for a user.
     * @param userId the ID of a user
     * @param achievementURL the unique URL of the achievement to delete
     * @return true if delete is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#achievements">User#achievements - Facebook Developers</a>
     */
    boolean deleteAchievement(String userId, URL achievementURL) throws FacebookException;


    /**
     * Returns the current scores for a user in games.
     * @return scores
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    ResponseList<Score> getScores() throws FacebookException;

    /**
     * Returns the current scores for a user in games.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return scores
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    ResponseList<Score> getScores(Reading reading) throws FacebookException;

    /**
     * Returns the current scores for the current user in games.
     * @param userId the ID of the user
     * @return scores
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    ResponseList<Score> getScores(String userId) throws FacebookException;

    /**
     * Returns the current scores for the current user in games.
     * @param userId the ID of the user
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return scores
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    ResponseList<Score> getScores(String userId, Reading reading) throws FacebookException;
    
    /**
     * Posts the score for the current user.
     * @param scoreValue numeric score with value
     * @return true if post is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    boolean postScore(int scoreValue) throws FacebookException;

    /**
     * Posts the score for a user.
     * @param userId the ID of a user
     * @param scoreValue numeric score with value
     * @return true if post is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    boolean postScore(String userId, int scoreValue) throws FacebookException;
    
    /**
     * Deletes the current score for the current user.
     * @return true if delete is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    boolean deleteScore() throws FacebookException;

    /**
     * Deletes the current score for a user.
     * @param userId the ID of a user
     * @return true if delete is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#scores">User#scores - Facebook Developers</a>
     */
    boolean deleteScore(String userId) throws FacebookException;

}
