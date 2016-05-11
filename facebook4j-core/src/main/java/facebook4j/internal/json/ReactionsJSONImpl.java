package facebook4j.internal.json;

import static facebook4j.internal.util.z_F4JInternalParseUtil.getRawString;

import java.io.Serializable;

import facebook4j.FacebookException;
import facebook4j.Reaction;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

public class ReactionsJSONImpl extends CategoryJSONImpl implements Reaction, Serializable{


	private static final long serialVersionUID = 4813956293820850547L;

	private String type;
	
	ReactionsJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
		super(res, conf);
		init(res.asJSONObject());
	}
	
	ReactionsJSONImpl(JSONObject json) throws FacebookException {
		super(json);
		init(json);
	}
	

	private void init(JSONObject json) throws FacebookException {
		type = getRawString("type", json);
    }

	public static ResponseList<Reaction> createReactionsList(HttpResponse res, Configuration conf) throws FacebookException {
		try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            
            if (json.has("reactions"))
            	json = json.getJSONObject("reactions");
            
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Reaction> reactions = new ResponseListImpl<Reaction>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject reactionJSONObject = list.getJSONObject(i);
                Reaction reaction = new ReactionsJSONImpl(reactionJSONObject);
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

	public String getType() {
		return type;
	}

}
