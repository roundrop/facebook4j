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

import facebook4j.FacebookException;
import facebook4j.Question;
import facebook4j.QuestionUpdate;
import facebook4j.QuestionVotes;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface QuestionMethods {
    /**
     * Returns the current user's/page's questions.
     * @return questions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    ResponseList<Question> getQuestions() throws FacebookException;

    /**
     * Returns the current user's/page's questions.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return questions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    ResponseList<Question> getQuestions(Reading reading) throws FacebookException;

    /**
     * Returns a user's/page's questions.
     * @param id the ID of a user/page
     * @return questions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    ResponseList<Question> getQuestions(String id) throws FacebookException;

    /**
     * Returns a user's/page's questions.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return questions
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    ResponseList<Question> getQuestions(String id, Reading reading) throws FacebookException;


    /**
     * Creates a current page's question.
     * @param questionUpdate the question to be created
     * @return The new question ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    String createQuestion(QuestionUpdate questionUpdate) throws FacebookException;

    /**
     * Creates the question.
     * @param id the ID of a page
     * @param questionUpdate the question to be created
     * @return The new question ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    String createQuestion(String id, QuestionUpdate questionUpdate) throws FacebookException;

    /**
     * Returns a single question.
     * @param questionId the ID of the question
     * @return question
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question/">Question - Facebook Developers</a>
     */
    Question getQuestion(String questionId) throws FacebookException;

    /**
     * Returns a single question.
     * @param questionId the ID of the question
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return question
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question/">Question - Facebook Developers</a>
     */
    Question getQuestion(String questionId, Reading reading) throws FacebookException;
    

    /**
     * Deletes the question.
     * @param questionId the ID of the question
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#questions">User#questions - Facebook Developers</a>
     */
    boolean deleteQuestion(String questionId) throws FacebookException;


    /**
     * Returns the options available as answers to the question.
     * @param questionId the ID of the question
     * @return the options available as answers to the question
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question/#options">Question#options - Facebook Developers</a>
     */
    ResponseList<Question.Option> getQuestionOptions(String questionId) throws FacebookException;

    /**
     * Returns the options available as answers to the question.
     * @param questionId the ID of the question
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return the options available as answers to the question
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question/#options">Question#options - Facebook Developers</a>
     */
    ResponseList<Question.Option> getQuestionOptions(String questionId, Reading reading) throws FacebookException;

    /**
     * Adds the option to a question.
     * @param questionId the ID of the question
     * @param optionDescription description
     * @return option ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question/#options">Question#options - Facebook Developers</a>
     */
    String addQuestionOption(String questionId, String optionDescription) throws FacebookException;


    /**
     * Returns the votes a particular option to a question has received.
     * @param questionId the ID of the question
     * @return option of users who have voted for this specific option
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/question_option/">QuestionOption - Facebook Developers</a>
     */
    ResponseList<QuestionVotes> getQuestionOptionVotes(String questionId) throws FacebookException;

}
