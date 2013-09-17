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

package facebook4j.internal.json;

import facebook4j.Category;
import facebook4j.FacebookException;
import facebook4j.PagableList;
import facebook4j.Question;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class QuestionJSONImpl extends FacebookResponseImpl implements Question, java.io.Serializable {
    private static final long serialVersionUID = 1143276967891229953L;

    private String id;
    private Category from;
    private String question;
    private Date createdTime;
    private Date updatedTime;
    private PagableList<Question.Option> options;

    /*package*/QuestionJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/QuestionJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("from")) {
                JSONObject fromJSONObject = json.getJSONObject("from");
                from = new CategoryJSONImpl(fromJSONObject);
            }
            question = getRawString("question", json);
            createdTime = getISO8601Datetime("created_time", json);
            updatedTime = getISO8601Datetime("updated_time", json);
            if (!json.isNull("options")) {
                JSONObject optionsJSONObject = json.getJSONObject("options");
                options = createOptionList(optionsJSONObject);
            } else {
                options = new PagableListImpl<Question.Option>(0);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public Category getFrom() {
        return from;
    }

    public String getQuestion() {
        return question;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getUpdatedTime() {
        return updatedTime;
    }

    public PagableList<Question.Option> getOptions() {
        return options;
    }

    /*package*/
    static ResponseList<Question> createQuestionList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Question> questions = new ResponseListImpl<Question>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject questionJSONObject = list.getJSONObject(i);
                Question question = new QuestionJSONImpl(questionJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(question, questionJSONObject);
                }
                questions.add(question);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(questions, list);
            }
            return questions;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        QuestionJSONImpl other = (QuestionJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QuestionJSONImpl [id=" + id + ", from=" + from + ", question="
                + question + ", createdTime=" + createdTime + ", updatedTime="
                + updatedTime + ", options=" + options + "]";
    }




    /*package*/ static class OptionJSONImpl implements Question.Option, java.io.Serializable {
        private static final long serialVersionUID = -6022359823763655064L;

        private String id;
        private Category from;
        private String name;
        private Integer voteCount;
        private Date createdTime;
        private Integer votes;

        /*package*/OptionJSONImpl(JSONObject json) throws FacebookException {
            try {
                id = getRawString("id", json);
                if (!json.isNull("from")) {
                    JSONObject fromJSONObject = json.getJSONObject("from");
                    from = new CategoryJSONImpl(fromJSONObject);
                }
                name = getRawString("name", json);
                voteCount = getPrimitiveInt("vote_count", json);
                createdTime = getISO8601Datetime("created_time", json);
                votes = getPrimitiveInt("votes", json);
            } catch (JSONException jsone) {
                throw new FacebookException(jsone.getMessage(), jsone);
            }
        }

        public String getId() {
            return id;
        }

        public Category getFrom() {
            return from;
        }

        public String getName() {
            return name;
        }

        public Integer getVoteCount() {
            return voteCount;
        }

        public Date getCreatedTime() {
            return createdTime;
        }

        public Integer getVotes() {
            return votes;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((id == null) ? 0 : id.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            OptionJSONImpl other = (OptionJSONImpl) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "OptionJSONImpl [id=" + id + ", from=" + from + ", name="
                    + name + ", voteCount=" + voteCount + ", createdTime="
                    + createdTime + ", votes=" + votes + "]";
        }
    }

    /*package*/
    static PagableList<Question.Option> createOptionList(JSONObject json)
    throws FacebookException {
        try {
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            PagableList<Question.Option> options = new PagableListImpl<Question.Option>(size, json);
            for (int i = 0; i < size; i++) {
                Question.Option option = new OptionJSONImpl(list.getJSONObject(i));
                options.add(option);
            }
            return options;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    /*package*/
    static ResponseList<Question.Option> createOptionList(HttpResponse res, Configuration conf)
    throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Question.Option> options = new ResponseListImpl<Question.Option>(size, json);
            for (int i = 0; i < size; i++) {
                Question.Option option = new OptionJSONImpl(list.getJSONObject(i));
                options.add(option);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(options, json);
            }
            return options;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

}
