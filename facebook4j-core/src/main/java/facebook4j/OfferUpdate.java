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
import facebook4j.internal.util.z_F4JInternalStringUtil;

import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 * @since Facebook4J 2.0.0
 */
public class OfferUpdate implements java.io.Serializable {
    private static final long serialVersionUID = -6990777853567019761L;

    private String title;
    private Calendar expirationTime;
    private String terms;
    private URL imageURL;
    private Media image;
    private Integer claimLimit;
    private String couponType;
    private String qrcode;
    private String barcode;
    private URL redemptionLink;
    private String redemptionCode;
    private Boolean isPublished;
    private Integer scheduledPublishTime;
    private Calendar reminderTime;

    public OfferUpdate() {
        super();
    }

    public OfferUpdate(String title, Calendar expirationTime, URL imageURL) {
        this.title = title;
        this.expirationTime = expirationTime;
        this.imageURL = imageURL;
    }

    public OfferUpdate(String title, Calendar expirationTime, Media image) {
        this.title = title;
        this.expirationTime = expirationTime;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public OfferUpdate title(String title) {
        setTitle(title);
        return this;
    }

    public Calendar getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Calendar expirationTime) {
        this.expirationTime = expirationTime;
    }

    public OfferUpdate expirationTime(Calendar expirationTime) {
        setExpirationTime(expirationTime);
        return this;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public OfferUpdate terms(String terms) {
        setTerms(terms);
        return this;
    }

    public URL getImageURL() {
        return imageURL;
    }

    public void setImageURL(URL imageURL) {
        this.imageURL = imageURL;
    }

    public OfferUpdate imageURL(URL imageURL) {
        setImageURL(imageURL);
        return this;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }

    public OfferUpdate image(Media image) {
        setImage(image);
        return this;
    }

    public Integer getClaimLimit() {
        return claimLimit;
    }

    public void setClaimLimit(Integer claimLimit) {
        this.claimLimit = claimLimit;
    }

    public OfferUpdate claimLimit(Integer claimLimit) {
        setClaimLimit(claimLimit);
        return this;
    }

    public String getCouponType() {
        return couponType;
    }

    public void setCouponType(String couponType) {
        this.couponType = couponType;
    }

    public OfferUpdate couponType(String couponType) {
        setCouponType(couponType);
        return this;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public OfferUpdate qrcode(String qrcode) {
        setQrcode(qrcode);
        return this;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public OfferUpdate barcode(String barcode) {
        setBarcode(barcode);
        return this;
    }

    public URL getRedemptionLink() {
        return redemptionLink;
    }

    public void setRedemptionLink(URL redemptionLink) {
        this.redemptionLink = redemptionLink;
    }

    public OfferUpdate redemptionLink(URL redemptionLink) {
        setRedemptionLink(redemptionLink);
        return this;
    }

    public String getRedemptionCode() {
        return redemptionCode;
    }

    public void setRedemptionCode(String redemptionCode) {
        this.redemptionCode = redemptionCode;
    }

    public OfferUpdate redemptionCode(String redemptionCode) {
        setRedemptionCode(redemptionCode);
        return this;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public OfferUpdate isPublished(Boolean published) {
        setPublished(published);
        return this;
    }

    public Integer getScheduledPublishTime() {
        return scheduledPublishTime;
    }

    public void setScheduledPublishTime(Integer scheduledPublishTime) {
        this.scheduledPublishTime = scheduledPublishTime;
    }

    public OfferUpdate scheduledPublishTime(Integer scheduledPublishTime) {
        setScheduledPublishTime(scheduledPublishTime);
        return this;
    }

    public OfferUpdate scheduledPublishTime(Date scheduledPublishTime) {
        long time = scheduledPublishTime.getTime() / 1000L;
        return scheduledPublishTime(Long.valueOf(time).intValue());
    }

    public Calendar getReminderTime() {
        return reminderTime;
    }

    public void setReminderTime(Calendar reminderTime) {
        this.reminderTime = reminderTime;
    }

    public OfferUpdate reminderTime(Calendar reminderTime) {
        setReminderTime(reminderTime);
        return this;
    }

    /*package*/ HttpParameter[] asHttpParameterArray() {
        List<HttpParameter> params = new ArrayList<HttpParameter>();
        params.add(new HttpParameter("title", title));
        params.add(new HttpParameter("expiration_time", z_F4JInternalStringUtil.formatISO8601Datetime(expirationTime)));
        if (terms != null) {
            params.add(new HttpParameter("terms", terms));
        }
        if (imageURL != null) {
            params.add(new HttpParameter("image_url", imageURL.toString()));
        }
        if (image != null) {
            params.add(new HttpParameter("image", image.getMediaFile()));
        }
        if (claimLimit != null) {
            params.add(new HttpParameter("claim_limit", claimLimit));
        }
        if (couponType != null) {
            params.add(new HttpParameter("coupon_type", couponType));
        }
        if (qrcode != null) {
            params.add(new HttpParameter("qrcode", qrcode));
        }
        if (barcode != null) {
            params.add(new HttpParameter("barcode", barcode));
        }
        if (redemptionLink != null) {
            params.add(new HttpParameter("redemption_link", redemptionLink.toString()));
        }
        if (redemptionCode != null) {
            params.add(new HttpParameter("redemption_code", redemptionCode));
        }
        if (isPublished != null) {
            params.add(new HttpParameter("published", isPublished));
        }
        if (scheduledPublishTime != null) {
            params.add(new HttpParameter("scheduled_publish_time", scheduledPublishTime));
        }
        if (reminderTime != null) {
            params.add(new HttpParameter("reminder_time", z_F4JInternalStringUtil.formatISO8601Datetime(reminderTime)));
        }
        return params.toArray(new HttpParameter[params.size()]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OfferUpdate)) return false;

        OfferUpdate that = (OfferUpdate) o;

        if (barcode != null ? !barcode.equals(that.barcode) : that.barcode != null) return false;
        if (claimLimit != null ? !claimLimit.equals(that.claimLimit) : that.claimLimit != null) return false;
        if (couponType != null ? !couponType.equals(that.couponType) : that.couponType != null) return false;
        if (expirationTime != null ? !expirationTime.equals(that.expirationTime) : that.expirationTime != null)
            return false;
        if (image != null ? !image.equals(that.image) : that.image != null) return false;
        if (imageURL != null ? !imageURL.equals(that.imageURL) : that.imageURL != null) return false;
        if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
        if (qrcode != null ? !qrcode.equals(that.qrcode) : that.qrcode != null) return false;
        if (redemptionCode != null ? !redemptionCode.equals(that.redemptionCode) : that.redemptionCode != null)
            return false;
        if (redemptionLink != null ? !redemptionLink.equals(that.redemptionLink) : that.redemptionLink != null)
            return false;
        if (reminderTime != null ? !reminderTime.equals(that.reminderTime) : that.reminderTime != null) return false;
        if (scheduledPublishTime != null ? !scheduledPublishTime.equals(that.scheduledPublishTime) : that.scheduledPublishTime != null)
            return false;
        if (terms != null ? !terms.equals(that.terms) : that.terms != null) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (expirationTime != null ? expirationTime.hashCode() : 0);
        result = 31 * result + (terms != null ? terms.hashCode() : 0);
        result = 31 * result + (imageURL != null ? imageURL.hashCode() : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (claimLimit != null ? claimLimit.hashCode() : 0);
        result = 31 * result + (couponType != null ? couponType.hashCode() : 0);
        result = 31 * result + (qrcode != null ? qrcode.hashCode() : 0);
        result = 31 * result + (barcode != null ? barcode.hashCode() : 0);
        result = 31 * result + (redemptionLink != null ? redemptionLink.hashCode() : 0);
        result = 31 * result + (redemptionCode != null ? redemptionCode.hashCode() : 0);
        result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
        result = 31 * result + (scheduledPublishTime != null ? scheduledPublishTime.hashCode() : 0);
        result = 31 * result + (reminderTime != null ? reminderTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "OfferUpdate{" +
                "title='" + title + '\'' +
                ", expirationTime=" + expirationTime +
                ", terms='" + terms + '\'' +
                ", imageURL=" + imageURL +
                ", image=" + image +
                ", claimLimit=" + claimLimit +
                ", couponType='" + couponType + '\'' +
                ", qrcode='" + qrcode + '\'' +
                ", barcode='" + barcode + '\'' +
                ", redemptionLink=" + redemptionLink +
                ", redemptionCode='" + redemptionCode + '\'' +
                ", isPublished=" + isPublished +
                ", scheduledPublishTime=" + scheduledPublishTime +
                ", reminderTime=" + reminderTime +
                '}';
    }
}
