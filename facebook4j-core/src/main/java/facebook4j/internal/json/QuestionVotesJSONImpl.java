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

import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.PagableList;
import facebook4j.QuestionVotes;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class QuestionVotesJSONImpl extends FacebookResponseImpl implements QuestionVotes, java.io.Serializable {
    private static final long serialVersionUID = -1051921085759549828L;

    private String id;
    private PagableList<IdNameEntity> votes;

    /*package*/QuestionVotesJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/QuestionVotesJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            if (!json.isNull("votes")) {
                JSONObject votesJSONObject = json.getJSONObject("votes");
                JSONArray votesArray = votesJSONObject.getJSONArray("data");
                final int size = votesArray.length();
                votes = new PagableListImpl<IdNameEntity>(size, votesJSONObject);
                for (int i = 0; i < size; i++) {
                    IdNameEntityJSONImpl vote = new IdNameEntityJSONImpl(votesArray.getJSONObject(i));
                    votes.add(vote);
                }
            } else {
                votes = new PagableListImpl<IdNameEntity>(0);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public String getId() {
        return id;
    }

    public PagableList<IdNameEntity> getVotes() {
        return votes;
    }

    /*package*/
    static ResponseList<QuestionVotes> createQuestionVotesList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<QuestionVotes> questionVotesList = new ResponseListImpl<QuestionVotes>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject questionVotesJSONObject = list.getJSONObject(i);
                QuestionVotes questionVotes = new QuestionVotesJSONImpl(questionVotesJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(questionVotes, questionVotesJSONObject);
                }
                questionVotesList.add(questionVotes);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(questionVotesList, list);
            }
            return questionVotesList;
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
        QuestionVotesJSONImpl other = (QuestionVotesJSONImpl) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "QuestionVotesJSONImpl [id=" + id + ", votes=" + votes + "]";
    }

}
