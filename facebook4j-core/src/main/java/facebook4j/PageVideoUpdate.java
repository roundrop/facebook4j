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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @author Dimitry Kudryavtsev dk8996 at gmail.com
 */
public class PageVideoUpdate extends VideoUpdate {
	private static final long serialVersionUID = -7572933117144435735L;

    private FeedTargetingParameter feedTargeting;
    private TargetingParameter targeting;
    
	public PageVideoUpdate(Media source) {
		super(source);
	}

    public TargetingParameter getTargeting() {
        return targeting;
    }

    public void setTargeting(TargetingParameter targeting) {
        this.targeting = targeting;
    }

    public PageVideoUpdate targeting(TargetingParameter targetingParameter) {
        setTargeting(targetingParameter);
        return this;
    }

    public FeedTargetingParameter getFeedTargeting() {
        return feedTargeting;
    }

    public void setFeedTargeting(FeedTargetingParameter feedTargeting) {
        this.feedTargeting = feedTargeting;
    }

    public PageVideoUpdate feedTargeting(FeedTargetingParameter feedTargeting) {
        setFeedTargeting(feedTargeting);
        return this;
    }

	/* package */HttpParameter[] asHttpParameterArray() {
		List<HttpParameter> params = new ArrayList<HttpParameter>(Arrays.asList(super.asHttpParameterArray()));
        if (targeting != null) {
            params.add(new HttpParameter("targeting", targeting.asJSONString()));
        }
        if (feedTargeting != null) {
            params.add(new HttpParameter("feed_targeting", feedTargeting.asJSONString()));
        }
		return params.toArray(new HttpParameter[params.size()]);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		if (!super.equals(o)) return false;

		PageVideoUpdate that = (PageVideoUpdate) o;

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
		return "PageVideoUpdate{" +
				"feedTargeting=" + feedTargeting +
				", targeting=" + targeting +
				"} " + super.toString();
	}
}
