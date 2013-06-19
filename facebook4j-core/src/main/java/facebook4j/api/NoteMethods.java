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

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.Like;
import facebook4j.Note;
import facebook4j.Reading;
import facebook4j.ResponseList;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface NoteMethods {
    /**
     * Returns the current user's/page's notes.
     * @return notes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    ResponseList<Note> getNotes() throws FacebookException;

    /**
     * Returns the current user's/page's notes.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return notes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    ResponseList<Note> getNotes(Reading reading) throws FacebookException;

    /**
     * Returns a user's/page's notes.
     * @param id the ID of a user/page
     * @return notes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    ResponseList<Note> getNotes(String id) throws FacebookException;

    /**
     * Returns a user's/page's notes.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return notes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    ResponseList<Note> getNotes(String id, Reading reading) throws FacebookException;


    /**
     * Creates the current user's/page's note.
     * @param subject the subject of the note
     * @param message note content
     * @return The new note ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    String createNote(String subject, String message) throws FacebookException;

    /**
     * Creates the note on behalf of a user/page.
     * @param id the ID of a user/page
     * @param subject the subject of the note
     * @param message note content
     * @return The new note ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#notes">User#notes - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#notes">Page#notes - Facebook Developers</a>
     */
    String createNote(String id, String subject, String message) throws FacebookException;


    /**
     * Returns a note.
     * @param noteId the ID of the note
     * @return note
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/">Note - Facebook Developers</a>
     */
    Note getNote(String noteId) throws FacebookException;

    /**
     * Returns a note.
     * @param noteId the ID of the note
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return note
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/">Note - Facebook Developers</a>
     */
    Note getNote(String noteId, Reading reading) throws FacebookException;


    /**
     * Returns all of the comments on a note.
     * @param noteId the ID of a note
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#comments">Note#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getNoteComments(String noteId) throws FacebookException;

    /**
     * Returns all of the comments on a note.
     * @param noteId the ID of a note
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return comments
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#comments">Note#comments - Facebook Developers</a>
     */
    ResponseList<Comment> getNoteComments(String noteId, Reading reading) throws FacebookException;

    /**
     * Comments on the note.
     * @param noteId the ID of the note
     * @param message comment text
     * @return The new comment ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#comments">Note#comments - Facebook Developers</a>
     */
    String commentNote(String noteId, String message) throws FacebookException;


    /**
     * Returns the likes made on a note.
     * @param noteId the ID of a note
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#likes">Note#likes - Facebook Developers</a>
     */
    ResponseList<Like> getNoteLikes(String noteId) throws FacebookException;

    /**
     * Returns the likes made on a note.
     * @param noteId the ID of a note
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a> see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return likes
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#likes">Note#likes - Facebook Developers</a>
     */
    ResponseList<Like> getNoteLikes(String noteId, Reading reading) throws FacebookException;

    /**
     * Likes the note.
     * @param noteId the ID of the note
     * @return true if like is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#likes">Note#likes - Facebook Developers</a>
     */
    boolean likeNote(String noteId) throws FacebookException;

    /**
     * Unlikes the note.
     * @param noteId the ID of the note
     * @return true if unlike is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/note/#likes">Note#likes - Facebook Developers</a>
     */
    boolean unlikeNote(String noteId) throws FacebookException;
    
}
