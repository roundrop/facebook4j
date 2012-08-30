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

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;
import facebook4j.Application;
import facebook4j.FacebookException;
import facebook4j.IdNameEntity;
import facebook4j.ResponseList;
import facebook4j.Score;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class ScoreJSONImpl implements Score, java.io.Serializable {
    private static final long serialVersionUID = 4667076189606464769L;

    private IdNameEntity user;
    private Integer score;
    private Application application;
    private String type;

    /*package*/ScoreJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/ScoreJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            user = new IdNameEntityJSONImpl(json.getJSONObject("user"));
            score = getPrimitiveInt("score", json);
            application = new ApplicationJSONImpl(json.getJSONObject("application"));
            type = getRawString("type", json);
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }

    public IdNameEntity getUser() {
        return user;
    }

    public Integer getScore() {
        return score;
    }

    public Application getApplication() {
        return application;
    }

    public String getType() {
        return type;
    }

    /*package*/
    static ResponseList<Score> createScoreList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            int size = list.length();
            ResponseList<Score> scores = new ResponseListImpl<Score>(size, json);
            for (int i = 0; i < size; i++) {
                Score score = new ScoreJSONImpl(list.getJSONObject(i));
                scores.add(score);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(scores, json);
            }
            return scores;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public String toString() {
        return "ScoreJSONImpl [user=" + user + ", score=" + score
                + ", application=" + application + ", type=" + type + "]";
    }


}
