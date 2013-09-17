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
import facebook4j.Offer;
import facebook4j.ResponseList;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONArray;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;

import java.net.URL;
import java.util.Date;

import static facebook4j.internal.util.z_F4JInternalParseUtil.*;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class OfferJSONImpl extends FacebookResponseImpl implements Offer, java.io.Serializable {
    private static final long serialVersionUID = -1552332355107913815L;

    private String id;
    private Category from;
    private String title;
    private Date createdTime;
    private Date expirationTime;
    private String terms;
    private URL imageURL;
    private Integer claimLimit;
    private String couponType;
    private URL redemptionLink;
    private String redemptionCode;
    private Boolean isPublished;
    private Integer scheduledPublishTime;
    private Date reminderTime;

    /*package*/OfferJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        super(res);
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/OfferJSONImpl(JSONObject json) throws FacebookException {
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
            title = getRawString("title", json);
            createdTime = getISO8601Datetime("created_time", json);
            expirationTime = getISO8601Datetime("expiration_time", json);
            terms = getRawString("terms", json);
            imageURL = getURL("image_url", json);
            claimLimit = getInt("claim_limit", json);
            couponType = getRawString("coupon_type", json);
            redemptionLink = getURL("redemption_link", json);
            redemptionCode = getRawString("redemption_code", json);
            isPublished = getBoolean("published", json);
            scheduledPublishTime = getInt("scheduled_publish_time", json);
            reminderTime = getISO8601Datetime("reminder_time", json);
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

    public String getTitle() {
        return title;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public String getTerms() {
        return terms;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public Integer getClaimLimit() {
        return claimLimit;
    }

    public String getCouponType() {
        return couponType;
    }

    public URL getRedemptionLink() {
        return redemptionLink;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public Boolean isPublished() {
        return isPublished;
    }

    public Integer getScheduledPublishTime() {
        return scheduledPublishTime;
    }

    public Date getReminderTime() {
        return reminderTime;
    }

    /*package*/
    static ResponseList<Offer> createOfferList(HttpResponse res, Configuration conf) throws FacebookException {
        try {
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.clearThreadLocalMap();
            }
            JSONObject json = res.asJSONObject();
            JSONArray list = json.getJSONArray("data");
            final int size = list.length();
            ResponseList<Offer> offers = new ResponseListImpl<Offer>(size, json);
            for (int i = 0; i < size; i++) {
                JSONObject offerJSONObject = list.getJSONObject(i);
                Offer offer = new OfferJSONImpl(offerJSONObject);
                if (conf.isJSONStoreEnabled()) {
                    DataObjectFactoryUtil.registerJSONObject(offer, offerJSONObject);
                }
                offers.add(offer);
            }
            if (conf.isJSONStoreEnabled()) {
                DataObjectFactoryUtil.registerJSONObject(offers, list);
            }
            return offers;
        } catch (JSONException jsone) {
            throw new FacebookException(jsone);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferJSONImpl)) return false;

        OfferJSONImpl offerJSON = (OfferJSONImpl) o;

        if (id != null ? !id.equals(offerJSON.id) : offerJSON.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "OfferJSONImpl{" +
                "id='" + id + '\'' +
                ", from=" + from +
                ", title='" + title + '\'' +
                ", createdTime=" + createdTime +
                ", expirationTime=" + expirationTime +
                ", terms='" + terms + '\'' +
                ", imageURL=" + imageURL +
                ", claimLimit=" + claimLimit +
                ", couponType='" + couponType + '\'' +
                ", redemptionLink=" + redemptionLink +
                ", redemptionCode='" + redemptionCode + '\'' +
                ", isPublished=" + isPublished +
                ", scheduledPublishTime=" + scheduledPublishTime +
                ", reminderTime=" + reminderTime +
                '}';
    }
}
