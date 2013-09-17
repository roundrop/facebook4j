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
import facebook4j.internal.org.json.JSONArray;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class QuestionUpdate implements java.io.Serializable {
    private static final long serialVersionUID = 6854301390218101794L;

    private String question;
    private List<String> options;
    private Boolean allowNewOptions;
    private Boolean published;
    private Integer scheduledPublishTime;

    public QuestionUpdate() {
        super();
    }

    public QuestionUpdate(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public QuestionUpdate options(List<String> options) {
        setOptions(options);
        return this;
    }

    public QuestionUpdate option(String option) {
        if (options == null) {
            options = new ArrayList<String>();
        }
        options.add(option);
        return this;
    }

    public Boolean getAllowNewOptions() {
        return allowNewOptions;
    }

    public void setAllowNewOptions(Boolean allowNewOptions) {
        this.allowNewOptions = allowNewOptions;
    }

    public QuestionUpdate allowNewOptions(boolean allowNewOptions) {
        setAllowNewOptions(allowNewOptions);
        return this;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public QuestionUpdate published(boolean published) {
        setPublished(published);
        return this;
    }

    public Integer getScheduledPublishTime() {
        return scheduledPublishTime;
    }

    public void setScheduledPublishTime(Integer scheduledPublishTime) {
        this.scheduledPublishTime = scheduledPublishTime;
    }

    public void setScheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        setScheduledPublishTime(Long.valueOf(time).intValue());
    }

    public QuestionUpdate scheduledPublishTime(Integer scheduledPublishTime) {
        setScheduledPublishTime(scheduledPublishTime);
        return this;
    }

    public QuestionUpdate scheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        return scheduledPublishTime(Long.valueOf(time).intValue());
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("question", question));
        if (options != null && options.size() != 0) {
            params.add(new HttpParameter("options", new JSONArray(options).toString()));
        }
        if (allowNewOptions != null) {
            params.add(new HttpParameter("allow_new_options", allowNewOptions));
        }
        if (published != null) {
            params.add(new HttpParameter("published", published));
        }
        if (scheduledPublishTime != null) {
            params.add(new HttpParameter("scheduled_publish_time", scheduledPublishTime));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof QuestionUpdate)) return false;

        QuestionUpdate that = (QuestionUpdate) o;

        if (allowNewOptions != null ? !allowNewOptions.equals(that.allowNewOptions) : that.allowNewOptions != null)
            return false;
        if (options != null ? !options.equals(that.options) : that.options != null) return false;
        if (published != null ? !published.equals(that.published) : that.published != null) return false;
        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime) : that.scheduledPublishTime != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (options != null ? options.hashCode() : 0);
        result = 31 * result + (allowNewOptions != null ? allowNewOptions.hashCode() : 0);
        result = 31 * result + (published != null ? published.hashCode() : 0);
        result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "QuestionUpdate{" +
                "question='" + question + '\'' +
                ", options=" + options +
                ", allowNewOptions=" + allowNewOptions +
                ", published=" + published +
                ", scheduledPublishTime=" + scheduledPublishTime +
                '}';
    }
}
