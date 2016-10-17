package facebook4j.internal.json;

import facebook4j.FacebookException;
import facebook4j.Place;
import facebook4j.PlaceTag;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

public class PlaceTagJSONImpl extends FacebookResponseImpl implements PlaceTag, java.io.Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2432739417789258923L;
    private String id;
    private Date createdTime;
    private Place place;

     /*package*/PlaceTagJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/PlaceTagJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        try {
            id = getRawString("id", json);
            createdTime = getISO8601Datetime("created_time", json);
            
            if (isJSONObject("place", json)) {
                JSONObject placeJSONObject = json.getJSONObject("place");
                place = new PlaceJSONImpl(placeJSONObject);
            }
        } catch (JSONException jsone) {
            throw new FacebookException(jsone.getMessage(), jsone);
        }
    }


    public String getId() {
        return id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Place getPlaceTag() {
        return place;
    }

    /*package*/
    static ResponseList<PlaceTag> createPlaceTagList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<PlaceTag> places = new ResponseListImpl<PlaceTag>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject placeJSONObject = list.getJSONObject(i);
                PlaceTag placeTag = new PlaceTagJSONImpl(placeJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(placeTag, placeJSONObject);
                }
                places.add(placeTag);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(places, list);
            }
            return places;
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
            PlaceTagJSONImpl other = (PlaceTagJSONImpl) obj;
            if (id == null) {
                if (other.id != null)
                    return false;
            } else if (!id.equals(other.id))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return "PlaceTagJSONImpl{" +
                    "id='" + id + '\'' +
                    ", created_time='" + createdTime + '\'' +
                    ", place=" + place +
                    '}';
        }

}
