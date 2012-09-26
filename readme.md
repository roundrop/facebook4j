# Facebook4J
Facebook4J is a Facebook Graph API binding library for the Java language licensed under Apache License 2.0.

## System Requirements
Java 5 or later

## How To Install
### Maven Integration
    <dependency>
      <groupId>org.facebook4j</groupId>
      <artifactId>facebook4j-core</artifactId>
      <version>[1.0,)</version>
    </dependency>
### Download
Download jar file from [here](http://facebook4j.org/download/facebook4j-core-latest-jar.php).  

## How To Use
Create a standard properties file named "facebook4j.properties". Place it to either the current directory, root of the classpath directory. 

    debug=true
    oauth.appId=****************
    oauth.appSecret=********************************
    oauth.permissions=email,publish_stream

If you are familiar with Java language, looking into the [JavaDoc](http://facebook4j.org/en/javadoc/index.html "JavaDoc") should be the shortest way for you to get started.
[facebook4j.Facebook](http://facebook4j.org/en/javadoc/facebook4j/Facebook.html "facebook4j.Facebook") interface is the one you may want to look at first.

### Code Examples
Code examples of Server-Side Authentication and post status message implementation is available at [https://github.com/roundrop/sign-in-with-facebook](https://github.com/roundrop/sign-in-with-facebook)

## Supported
* [Server-Side Authentication](https://developers.facebook.com/docs/authentication/server-side/ "Server-Side Authentication")
* [User](https://developers.facebook.com/docs/reference/api/user/ "User")
* [Achievement(Instance)](https://developers.facebook.com/docs/reference/api/achievement/ "Achievement(Instance)")
* [Album](https://developers.facebook.com/docs/reference/api/album/ "Album")
* [Checkin](https://developers.facebook.com/docs/reference/api/checkin/ "Checkin")
* [Comment](https://developers.facebook.com/docs/reference/api/Comment/ "Comment")
* [Domain](https://developers.facebook.com/docs/reference/api/domain/ "Domain")
* [Event](https://developers.facebook.com/docs/reference/api/event/ "Event")
* [FriendList](https://developers.facebook.com/docs/reference/api/FriendList/ "FriendList")
* [Group](https://developers.facebook.com/docs/reference/api/group/ "Group")
* [Insights](https://developers.facebook.com/docs/reference/api/insights/ "Insights")
* [Link](https://developers.facebook.com/docs/reference/api/link/ "Link")
* [Message](https://developers.facebook.com/docs/reference/api/message/ "Message")
* [Note](https://developers.facebook.com/docs/reference/api/note/ "Note")
* [Photo](https://developers.facebook.com/docs/reference/api/photo/ "Photo")
* [Post](https://developers.facebook.com/docs/reference/api/post/ "Post")
* [Question](https://developers.facebook.com/docs/reference/api/question/ "Question")
* [Status message](https://developers.facebook.com/docs/reference/api/status/ "Status message")
* [Video](https://developers.facebook.com/docs/reference/api/video/ "Video")
* [Search](https://developers.facebook.com/docs/reference/api/#searching)
* [FQL](https://developers.facebook.com/docs/reference/fql/)

## NOT Supported yet
* [Application](https://developers.facebook.com/docs/reference/api/application/ "Application")
* [Page](https://developers.facebook.com/docs/reference/api/page/ "Page")
* [Offer](https://developers.facebook.com/docs/reference/api/offer/ "Offer")
* [Order ](https://developers.facebook.com/docs/reference/api/order/ "Order ")
* [Review](https://developers.facebook.com/docs/reference/api/Review/ "Review")
* [Thread](https://developers.facebook.com/docs/reference/api/thread/ "Thread")
* [Batch Requests](https://developers.facebook.com/docs/reference/api/batch/ "Batch Requests")
* [Real-time Updates](https://developers.facebook.com/docs/reference/api/realtime/ "Real-time Updates")

## License
Facebook4J is released under Apache License 2.0.

Facebook4J includes software from Twitter4J to handle HTTP request/response and greatly internal logic. You can see the license term at [http://twitter4j.org/en/index.html#license](http://twitter4j.org/en/index.html#license)

\--------  
Twitter4J's readme.txt

> Twitter4J is a Twitter API binding library for the Java language licensed under Apache License 2.0.

> Twitter4J includes software from JSON.org to parse JSON response from the Twitter API. You can see the license term at http://www.JSON.org/license.html

> LICENSE.txt - the terms of license of this software  
pom.xml - maven parent pom  
powered-by-badge - badge  
readme.txt - this file  
twitter4j-core - core component : support REST and Search API  
twitter4j-apache-httpclient-support - optional component adds Apache HttpClient support  
twitter4j-examples - examples  
twitter4j-media-support - media API support  
twitter4j-async - Async API support : depending on twitter4j-core  
twitter4j-stream - Streaming API support : depending on twitter4j-core and twitter4j-async  

> Contributors  
> \------------  
Adam Samet <asamet at twitter.com> @damnitsamet  
Adrian Petrescu <apetresc at gmail.com> @apetresc  
Alan Gutierrez <alan at blogometer.com>  
Alessandro Bahgat <ale.bahgat at gmail.com> @abahgat  
Anton Evane <antonevane at gmail.com> @anton_evane  
Blake Barnes <blake.barnes at gmail.com>  
Bruno Torres Goyanna <bgoyanna at gmail.com> @bgoyanna  
Ciaran Jessup <ciaranj at gmail.com> @ciaran_j  
Cole Wen <wennnnke at gmail.com> @Pigwen  
Dan Checkoway <dcheckoway at gmail.com>  
Dong Wang <dong at twitter.com> @dongwang218  
Eric Jensen <ej at twitter.com> @ej  
Fiaz Hossain <fiaz at twitter.com> @fiazhossain  
Hitoshi Kunimatsu <hkhumanoid at gmail.com>  
Jacob S. Hoffman-Andrews <jsha at twitter.com> @j4cob  
Jenny Loomis <jenny at rockmelt.com>  
John Corwin <jcorwin at twitter.com> @johnxorz  
John Sirois <jsirois at twitter.com> @johnsirois  
Julien Letrouit <julien.letrouit at gmail.com> @jletroui  
Ludovico Fischer @ludovicofischer  
marr-masaaki <marr fiveflavors at gmail.com> @marr  
Mocel <docel77 at gmail.com> @Mocel  
Nobutoshi Ogata <n-ogata at cnt.biglobe.co.jp> @nobu666  
Nicholas Dellamaggiore <nick.dellamaggiore at gmail.com> @nickdella  
Perry Sakkaris <psakkaris at gmail.com>  
Roberto Estrada <robestradac at gmail.com>  
Roy Reshef <royreshef at gmail.com> @tsipo  
Rui Silva  
Sam Pullara <sam at sampullara.com> @sampullara  
Steve Lhomme <slhomme at matroska.org> @robux4  
Rémy Rakic <remy.rakic at gmail.com> @lqd  
Takao Nakaguchi <takao.nakaguchi at gmail.com> @takawitter  
Tomohisa Igarashi <tm.igarashi at gmail.com>  
Will Glozer <will at glozer.net> @ar3te  
William Morgan <william at twitter.com> @wm  
withgod <noname at withgod.jp> @withgod  
Yusuke Yamamoto <yusuke at mac.com> @yusuke
