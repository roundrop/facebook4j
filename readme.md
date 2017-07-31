# Facebook4J [![Build Status](https://travis-ci.org/roundrop/facebook4j.svg?branch=develop)](https://travis-ci.org/roundrop/facebook4j)
Facebook4J is a Facebook Graph API binding library for the Java language licensed under Apache License 2.0.

## Version
2.4.10

## Install

```xml
<dependency>
  <groupId>org.facebook4j</groupId>
  <artifactId>facebook4j-core</artifactId>
  <version>[2.4,)</version>
</dependency>
```

## Code Examples

Please see [http://facebook4j.org/en/code-examples.html](http://facebook4j.org/en/code-examples.html) for complete documentation.

### Getting Facebook Instance
At first it is necessary to acquire Facebook instance to use Facebook4J.  
You can get Facebook instance in FacebookFactory.getInstance().

```java
Facebook facebook = new FacebookFactory().getInstance();
```

If App ID / App Secret / access token / access permission are listed in facebook4j.properties then, they are set in Facebook instance given back.  
See [Configuration | Facebook4J - A Java library for the Facebook Graph API](http://facebook4j.org/en/configuration.html) for the detail.  
When they are not listed, it is setable later as follows:

```java
facebook.setOAuthAppId(appId, appSecret);
facebook.setOAuthPermissions(commaSeparetedPermissions);
facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
```

- - -

### OAuth support

#### Getting User Access Token

It is possible to authenticate users using Facebook accounts with your web application.  
An example implementation is available at [https://github.com/roundrop/facebook4j-oauth-example](https://github.com/roundrop/facebook4j-oauth-example) .

#### Getting App Access Token
You can get App Access Token via Facebook.getOAuthAppAccessToken() method.

```java
facebook.getOAuthAppAccessToken();
```

#### Getting Page Access Token
You can get Page Access Token as below:

```java
ResponseList<Account> accounts = facebook.getAccounts();
Account yourPageAccount = accounts.get(0);  // if index 0 is your page account.
String pageAccessToken = yourPageAccount.getAccessToken();
```

#### Getting Device Access Token
With Facebook Login for Devices people can easily and safely log into your apps and services with their Facebook account on devices with limited input or display capabilities.  
(See Facebook's Documentation: [Facebook Login for Devices](https://developers.facebook.com/docs/facebook-login/for-devices) )  
An example implementation is available at [https://github.com/roundrop/facebook4j-oauth-example](https://github.com/roundrop/facebook4j-oauth-example) .

#### Extending expiration of an Access Token
(See Facebook's Documentation: [Expiration and Extension of Access Tokens](https://developers.facebook.com/docs/facebook-login/access-tokens/expiration-and-extension)  
You can extend Access Token's expiration as below:

```java
String shortLivedToken = "your-short-lived-token";
AccessToken extendedToken = facebook.extendTokenExpiration(shortLivedToken);
```

- - -

### Publishing a message
You can publish a message via Facebook.postStatusMessage() method.

```java
facebook.postStatusMessage("Hello World from Facebook4J.");
```

- - -

### Publishing a link
You can publish a link via Facebook.postFeed() method.

```java
PostUpdate post = new PostUpdate(new URL("http://facebook4j.org"))
                    .picture(new URL("http://facebook4j.org/images/hero.png"))
                    .name("Facebook4J - A Java library for the Facebook Graph API")
                    .caption("facebook4j.org")
                    .description("Facebook4J is a Java library for the Facebook Graph API.");
facebook.postFeed(post);
```

Facebook.postLink() method is simple way to post.

```java
facebook.postLink(new URL("http://facebook4j.org"));
facebook.postLink(new URL("http://facebook4j.org"), "A Java library for the Facebook Graph API");
```

- - -

### Getting News Feed
Facebook.getHome() returns a List of user's latest News Feed.

```java
ResponseList<Post> feed = facebook.getHome();
```

- - -

### Like
You can like a Post, Photo, ... via Facebook.like\*\*\*\*() methods.

```java
facebook.likePost(postId);
```

Also, You can unlike a Post, Photo, ... via Facebook.unlike\*\*\*\*() methods.

```java
facebook.unlikePost(postId);
```

- - -

### Publising a comment
You can comment a Post, Photo, ... via Facebook.comment\*\*\*\*() methods.

```java
facebook.commentPhoto(photoId, "It's a nice photo!");
```

- - -

### Searching
You can search for Posts, Users, ... via Facebook.search\*\*\*\*() methods.

#### Search for public Posts

```java
ResponseList<Post> results = facebook.searchPosts("watermelon");
```

#### Search for Users

```java
ResponseList<User> results = facebook.searchUsers("mark");
```

#### Search for Events

```java
ResponseList<Event> results = facebook.searchEvents("conference");
```

#### Search for Groups

```java
ResponseList<Group> results = facebook.searchGroups("programming");
```

#### Search for Places

```java
// Search by name
ResponseList<Place> results = facebook.searchPlaces("coffee");

// You can narrow your search to a specific location and distance
GeoLocation center = new GeoLocation(37.76, -122.4.8);
int distance = 1000;
ResponseList<Place> searchPlaces("coffee", center, distance);
```

#### Search for Checkins

```java
// you or your friend's latest checkins, or checkins where you or your friends have been tagged
ResponseList<Checkin> results = facebook.searchCheckins();
```

#### Search for Locations

```java
// To search for objects near a geographical location
GeoLocation center = new GeoLocation(37.76, -122.4.8);
int distance = 1000;
ResponseList<Location> searchLocations(center, distance);

// To search for objects at a particular place
String placeId = "166793820034304";
ResponseList<Location> locations = facebookBestFriend1.searchLocations(placeId);
```

- - -

### Executing FQL
You can execute FQL via Facebook.executeFQL() method.  
Also you can execute multiple FQL in one call via Facebook.executeMultiFQL() method.

```java
// Single FQL
String query = "SELECT uid2 FROM friend WHERE uid1=me()";
JSONArray jsonArray = facebook.executeFQL(query);
for (int i = 0; i < jsonArray.length(); i++) {
    JSONObject jsonObject = jsonArray.getJSONObject(i);
    System.out.println(jsonObject.get("uid2"));
}

// Multiple FQL
Map<String, String> queries = new HashMap<String, String>();
queries.put("all friends", "SELECT uid2 FROM friend WHERE uid1=me()");
queries.put("my name", "SELECT name FROM user WHERE uid=me()");
Map<String, JSONArray> result = facebook.executeMultiFQL(queries);
JSONArray allFriendsJSONArray = result.get("all friends");
for (int i = 0; i < allFriendsJSONArray.length(); i++) {
    JSONObject jsonObject = allFriendsJSONArray.getJSONObject(i);
    System.out.println(jsonObject.get("uid2"));
}
JSONArray myNameJSONArray = result.get("my name");
System.out.println(myNameJSONArray.getJSONObject(0).get("name"));
```

- - -

### Executing Batch Requests
You can execute Batch Requests via Facebook.executeBatch() method.  

```java
// Executing "me" and "me/friends?limit=50" endpoints
BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
batch.add(new BatchRequest(RequestMethod.GET, "me"));
batch.add(new BatchRequest(RequestMethod.GET, "me/friends?limit=50"));
List<BatchResponse> results = facebook.executeBatch(batch);

BatchResponse result1 = results.get(0);
BatchResponse result2 = results.get(1);

// You can get http status code or headers
int statusCode1 = result1.getStatusCode();
String contentType = result1.getResponseHeader("Content-Type");

// You can get body content via as****() method
String jsonString = result1.asString();
JSONObject jsonObject = result1.asJSONObject();
ResponseList<JSONObject> responseList = result2.asResponseList();

// You can map json to java object using DataObjectFactory#create****()
User user = DataObjectFactory.createUser(jsonString);
Friend friend1 = DataObjectFactory.createFriend(responseList.get(0).toString());
Friend friend2 = DataObjectFactory.createFriend(responseList.get(1).toString());
:
```

You can attach a binary data to batch request as follows:

```java
BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
Media file = new Media(new File("...image.png"));
BatchAttachment attachment = new BatchAttachment("file", file);
batch.add(new BatchRequest(RequestMethod.POST, "me/photos")
              .body("message=My photo")
              .attachedFile(attachment));
```

- - -

### Executing Raw API (setting the endpoint on your own)
You can execute the API endpoint that you want to run via Facebook.call****() method.

```java
// GET
RawAPIResponse res = facebook.callGetAPI("me");
JSONObject jsonObject = actual.asJSONObject();
String id = jsonObject.getString("id");

// POST
Map<String, String> params = new HashMap<String, String>();
params.put("message", "hello");
RawAPIResponse res = facebook.callPostAPI("me/feed", params);

// DELETE
RawAPIResponse res = facebook.callDeleteAPI("123456/likes");
if (res.isBoolean()) {
  System.out.println(res.asBoolean());
}
```

You can execute the API endpoint that is not supported by Facebook4J via Facebook.call****() method.


- - -

### Reading options
You can set various reading options to the method that Reading object includes in arguments.

#### Selecting specific fields
You can choose the fields you want returned via Reading.fields("fieldName1,fieldName2,...") .

```java
// Getting user's email address only
User user = facebook1.getUser(id1.getId(), new Reading().fields("email"));
```

#### limit/offset

```java
// Getting 1st-10th results
ResponseList<Post> results = facebook.searchPosts("watermelon", new Reading().limit(10));

// Getting 11th-20th results
ResponseList<Post> results = facebook.searchPosts("watermelon", new Reading().limit(10).offset(10));
```

#### until/since
until/since values can be a unix timestamp or any date accepted by [PHP's strtotime](http://php.net/manual/en/function.strtotime.php) format.

```java
ResponseList<Post> results = facebook.searchPosts("watermelon", new Reading().until("yesterday"));
```

- - -

### Pagination
You can get next/previous page with Paging object in results via Facebook.fetchNext() / Facebook.fetchPrevious() methods.

```java
ResponseList<Option> page1 = facebook.getQuestionOptions(questionId);

// Getting Next page
Paging<Option> paging1 = page1.getPaging();
ResponseList<Option> page2 = facebook.fetchNext(paging1);

// Getting Previous page
Paging<Option> paging2 = page2.getPaging();
page1 = facebook.fetchPrevious(paging2);
```

## Official Web Site
see: [http://facebook4j.org](http://facebook4j.org)

## License
Facebook4J is released under Apache License 2.0.

Facebook4J includes software from Twitter4J to handle HTTP request/response and greatly internal logic. You can see the license term at [http://twitter4j.org/en/index.html#license](http://twitter4j.org/en/index.html#license)
