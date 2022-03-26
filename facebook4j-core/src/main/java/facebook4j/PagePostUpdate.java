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

import facebook4j.internal.http.HttpParameter;

import java.net.URL;
import java.util.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @author Dimitry Kudryavtsev dk8996 at gmail.com
 */
public class PagePostUpdate extends PostUpdate {
    private static final long serialVersionUID = 549559699324208520L;

    private FeedTargetingParameter feedTargeting;
    private TargetingParameter targeting;

    public PagePostUpdate(String message) {
    	super(message);
    }

    public PagePostUpdate(URL link) {
        super(link);
    }

    public TargetingParameter getTargeting() {
        return targeting;
    }

    public void setTargeting(TargetingParameter targeting) {
        this.targeting = targeting;
    }

    public PagePostUpdate targeting(TargetingParameter targetingParameter) {
        setTargeting(targetingParameter);
        return this;
    }

    public FeedTargetingParameter getFeedTargeting() {
        return feedTargeting;
    }

    public void setFeedTargeting(FeedTargetingParameter feedTargeting) {
        this.feedTargeting = feedTargeting;
    }

    public PagePostUpdate feedTargeting(FeedTargetingParameter feedTargeting) {
        setFeedTargeting(feedTargeting);
        return this;
    }

    /*package*/
    public HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>(Arrays.asList(super.asHttpParameterArray()));
        if (feedTargeting != null) {
            try {
                params.add(new HttpParameter("feed_targeting", feedTargeting.asJSONString()));
            } catch (Exception e) {
                new FacebookException(e.getMessage(), e);
            }
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PagePostUpdate that = (PagePostUpdate) o;

        if (feedTargeting != null ? !feedTargeting.equals(that.feedTargeting) : that.feedTargeting != null)
            return false;
        return targeting != null ? targeting.equals(that.targeting) : that.targeting == null;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (feedTargeting != null ? feedTargeting.hashCode() : 0);
        result = 31 * result + (targeting != null ? targeting.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "PagePostUpdate{" +
                "feedTargeting=" + feedTargeting +
                ", targeting=" + targeting +
                "} " + super.toString();
    }
}