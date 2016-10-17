package facebook4j.internal.json;

import facebook4j.FacebookException;
import facebook4j.Reaction;
import facebook4j.ReactionType;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.io.Serializable;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

public class ReactionJSONImpl extends CategoryJSONImpl implements Reaction, Serializable{
    private static final long serialVersionUID = 4813956293820850547L;

    private ReactionType type;

    ReactionJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res, conf);
        init(res.asJSONObject());
    }

    ReactionJSONImpl(JSONObject json) throws FacebookException {
        super(json);
        init(json);
    }


    private void init(JSONObject json) throws FacebookException {
        type = ReactionType.of(getRawString("type", json));
    }

    public static ResponseList<Reaction> createReactionsList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            
            if (json.has("reactions")) {
                json = json.getJSONObject("reactions");
            }
            
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Reaction> reactions = new ResponseListImpl<Reaction>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject reactionJSONObject = list.getJSONObject(i);
                Reaction reaction = new ReactionJSONImpl(reactionJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(reaction, reactionJSONObject);
                }
                reactions.add(reaction);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(reactions, list);
            }
            return reactions;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        } catch (FacebookException e) {
            throw new FacebookException(e.getMessage());
        }
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ReactionType getType() {
        return type;
    }

}
