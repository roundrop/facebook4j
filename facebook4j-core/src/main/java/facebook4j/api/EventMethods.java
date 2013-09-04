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

import facebook4j.Event;
import facebook4j.EventUpdate;
import facebook4j.FacebookException;
import facebook4j.Media;
import facebook4j.Photo;
import facebook4j.PictureSize;
import facebook4j.RSVPStatus;
import facebook4j.Reading;
import facebook4j.ResponseList;
import facebook4j.Video;

import java.net.URL;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface EventMethods {
    /**
     * Returns the events the current user/page is attending.
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - events
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - events
     */
    ResponseList<Event> getEvents() throws FacebookException;

    /**
     * Returns the events the current user/page is attending.
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - events
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - events
     */
    ResponseList<Event> getEvents(Reading reading) throws FacebookException;

    /**
     * Returns the events a user/event/page is attending.
     * @param id the ID of a user
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - events
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - events
     */
    ResponseList<Event> getEvents(String id) throws FacebookException;

    /**
     * Returns the events a user/event/page is attending.
     * @param id the ID of a user/page
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return events
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/">User - Facebook Developers</a> - Connections - events
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/">Page - Facebook Developers</a> - Connections - events
     */
    ResponseList<Event> getEvents(String id, Reading reading) throws FacebookException;
    

    /**
     * Creates the event for the current user/page.
     * @return The new event ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#events">User#events - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#events">Page#events - Facebook Developers</a> - Connections - events
     * @param eventUpdate
     */
    String createEvent(EventUpdate eventUpdate) throws FacebookException;

    /**
     * Creates the event for a user/event/page.
     * @param id the ID of a user/page
     * @param eventUpdate a event to be created
     * @return The new event ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#events">User#events - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#events">Page#events - Facebook Developers</a> - Connections - events
     */
    String createEvent(String id, EventUpdate eventUpdate) throws FacebookException;
    
    /**
     * Updates the event.
     * @param eventId the ID of a event
     * @param eventUpdate the event to be updated
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#events">User#events - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#events">Page#events - Facebook Developers</a> - Connections - events
     */
    boolean editEvent(String eventId, EventUpdate eventUpdate) throws FacebookException;
    
    /**
     * Deletes the event.
     * @param eventId the ID of a event
     * @return true if delete is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#events">User#events - Facebook Developers</a>
     * @see <a href="https://developers.facebook.com/docs/reference/api/page/#events">Page#events - Facebook Developers</a> - Connections - events
     */
    boolean deleteEvent(String eventId) throws FacebookException;


    /**
     * Returns a single event.
     * @param eventId the ID of a event
     * @return event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/">Event - Facebook Developers</a>
     */
    Event getEvent(String eventId) throws FacebookException;

    /**
     * Returns a single event.
     * @param eventId the ID of a event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @return event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/">Event - Facebook Developers</a>
     */
    Event getEvent(String eventId, Reading reading) throws FacebookException;


    /**
     * Posts the link on a event's wall.
     * @param eventId the ID of a event
     * @param link link URL
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#links">Event#links - Facebook Developers</a>
     */
    String postEventLink(String eventId, URL link) throws FacebookException;

    /**
     * Posts the link on a event's wall.
     * @param eventId the ID of a event
     * @param link link URL
     * @param message link message
     * @return The new link ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#links">Event#links - Facebook Developers</a>
     */
    String postEventLink(String eventId, URL link, String message) throws FacebookException;


    /**
     * Posts the status message on a event's wall.
     * @param eventId the ID of a event
     * @param message status message content
     * @return The new status message ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#statuses">Event#statuses - Facebook Developers</a>
     */
    String postEventStatusMessage(String eventId, String message) throws FacebookException;
    

    /**
     * Returns a list of all users who have not replied to the event.
     * @param eventId the ID of a event
     * @return a list of all users who have not replied to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#noreply">Event#noreply - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusAsNoreply(String eventId) throws FacebookException;

    /**
     * Returns a single user who have not replied to the event.
     * @param eventId the ID of a event
     * @return a single users who have not replied to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#noreply">Event#noreply - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusAsNoreply(String eventId, String userId) throws FacebookException;


    /**
     * Returns a list of invitees for the event.
     * @param eventId the ID of a event
     * @return a list of invitees for the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#invited">Event#invited - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusAsInvited(String eventId) throws FacebookException;

    /**
     * Checks a specific user has been invited to the event.
     * @param eventId the ID of a event
     * @param userId the ID of a user
     * @return the user has been invited to this event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#invited">Event#invited - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusAsInvited(String eventId, String userId) throws FacebookException;
    
    /**
     * Invites a user to the event.
     * @param eventId the ID of a event
     * @param userId the ID of the user
     * @return true if the invite is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#invited">Event#invited - Facebook Developers</a>
     */
    boolean inviteToEvent(String eventId, String userId) throws FacebookException;

    /**
     * Invites users to the event.
     * @param eventId the ID of a event
     * @param userIds the IDs of users
     * @return true if the invite is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#invited">Event#invited - Facebook Developers</a>
     */
    boolean inviteToEvent(String eventId, String[] userIds) throws FacebookException;
    
    /**
     * Un-invites user from the event.
     * @param eventId the ID of a event
     * @param userId the ID of a user
     * @return true if the un-invite is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#invited">Event#invited - Facebook Developers</a>
     */
    boolean uninviteFromEvent(String eventId, String userId) throws FacebookException;


    /**
     * Returns a list of all users who responded 'yes' to the event.
     * @param eventId the ID of a event
     * @return a list of all users who responded 'yes' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#attending">Event#attending - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInAttending(String eventId) throws FacebookException;

    /**
     * Checks a specific user responded 'yes' to the event.
     * @param eventId the ID of a event
     * @param userId the ID of a user
     * @return a specific user responded 'yes' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#attending">Event#attending - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInAttending(String eventId, String userId) throws FacebookException;
    
    /**
     * RSVPs the current user as 'attending' the event.
     * @param eventId the ID of a event
     * @return true if the RSVP is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#attending">Event#attending - Facebook Developers</a>
     */
    boolean rsvpEventAsAttending(String eventId) throws FacebookException;


    /**
     * Returns a list of all users who responded 'maybe' to the event.
     * @param eventId the ID of a event
     * @return a list of all users who responded 'maybe' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#maybe">Event#maybe - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInMaybe(String eventId) throws FacebookException;

    /**
     * Checks a specific user responded 'maybe' to the event.
     * @param eventId the ID of a event
     * @param userId the ID of a user
     * @return a specific user responded 'maybe' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#maybe">Event#maybe - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInMaybe(String eventId, String userId) throws FacebookException;

    /**
     * RSVPs the current user as 'maybe' the event.
     * @param eventId the ID of a event
     * @return true if the RSVP is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#maybe">Event#maybe - Facebook Developers</a>
     */
    boolean rsvpEventAsMaybe(String eventId) throws FacebookException;


    /**
     * Returns a list of all users who responded 'no' to the event.
     * @param eventId the ID of a event
     * @return a list of all users who responded 'no' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#declined">Event#declined - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInDeclined(String eventId) throws FacebookException;

    /**
     * Checks a specific user responded 'no' to the event.
     * @param eventId the ID of a event
     * @param userId the ID of a user
     * @return a specific user responded 'no' to the event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#declined">Event#declined - Facebook Developers</a>
     */
    ResponseList<RSVPStatus> getRSVPStatusInDeclined(String eventId, String userId) throws FacebookException;

    /**
     * RSVPs the current user as 'declined' the event.
     * @param eventId the ID of a event
     * @return true if the RSVP is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#declined">Event#declined - Facebook Developers</a>
     */
    boolean rsvpEventAsDeclined(String eventId) throws FacebookException;


    /**
     * Returns url of the event's profile picture.
     * @param eventId the ID of a event
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#picture">Event#picture - Facebook Developers</a>
     */
    URL getEventPictureURL(String eventId) throws FacebookException;

    /**
     * Returns url of the event's profile picture.
     * @param eventId the ID of a event
     * @param size picture size
     * @return url
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#picture">Event#picture - Facebook Developers</a>
     */
    URL getEventPictureURL(String eventId, PictureSize size) throws FacebookException;
    
    /**
     * Updates the event's profile picture.
     * @param eventId the ID of a event
     * @param source picture resource
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#picture">Event#picture - Facebook Developers</a>
     */
    boolean updateEventPicture(String eventId, Media source) throws FacebookException;

    /**
     * Deletes the event's profile picture.
     * @param eventId the ID of a event
     * @return true if update is successful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#picture">Event#picture - Facebook Developers</a>
     */
    boolean deleteEventPicture(String eventId) throws FacebookException;


    /**
     * Returns the event's photos.
     * @param eventId the ID of a event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getEventPhotos(String eventId) throws FacebookException;

    /**
     * Returns the event's photos.
     * @param eventId the ID of a event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    ResponseList<Photo> getEventPhotos(String eventId, Reading reading) throws FacebookException;
    
    /**
     * Posts the photo on a event's wall.
     * @param eventId the ID of a event
     * @param source photo resource
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    String postEventPhoto(String eventId, Media source) throws FacebookException;

    /**
     * Posts the photo on a event's wall.
     * @param eventId the ID of a event
     * @param source photo resource
     * @param message photo description
     * @return The new photo ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#photos">Event#photos - Facebook Developers</a>
     */
    String postEventPhoto(String eventId, Media source, String message) throws FacebookException;
    

    /**
     * Returns the event's videos.
     * @param eventId the ID of a event
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#videos">Event#videos - Facebook Developers</a>
     */
    ResponseList<Video> getEventVideos(String eventId) throws FacebookException;

    /**
     * Returns the event's videos.
     * @param eventId the ID of a event
     * @param reading optional reading parameters. see <a href="https://developers.facebook.com/docs/reference/api/#reading">Graph API#reading - Facebook Developers</a>
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#videos">Event#videos - Facebook Developers</a>
     */
    ResponseList<Video> getEventVideos(String eventId, Reading reading) throws FacebookException;
    
    /**
     * Posts the video on a event's wall.
     * @param eventId the ID of a event
     * @param source video resource
     * @return The new video ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#videos">Event#videos - Facebook Developers</a>
     */
    String postEventVideo(String eventId, Media source) throws FacebookException;

    /**
     * Posts the video on a event's wall.
     * @param eventId the ID of a event
     * @param source video resource
     * @param title video title
     * @param description video description
     * @return The new video ID
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/event/#videos">Event#videos - Facebook Developers</a>
     */
    String postEventVideo(String eventId, Media source, String title, String description) throws FacebookException;

}
