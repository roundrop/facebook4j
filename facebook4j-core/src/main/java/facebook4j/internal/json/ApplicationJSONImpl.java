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

import java.net.URL;
import java.util.List;
import java.util.Map;

import facebook4j.Application;
import facebook4j.FacebookException;
import facebook4j.conf.Configuration;
import facebook4j.internal.http.HttpResponse;
import facebook4j.internal.org.json.JSONObject;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
/*package*/ final class ApplicationJSONImpl implements Application, java.io.Serializable {
    private static final long serialVersionUID = -3668474100111405868L;

    private String id;
    private String name;
    private String description;
    private String category;
    private String company;
    private URL iconUrl;
    private String subcategory;
    private URL link;
    private URL logoUrl;
    private String dailyActiveUsers;
    private String weeklyActiveUsers;
    private String monthlyActiveUsers;
    private Map<String, Boolean> migrations;
    private String namespace;
    private Map<String, String> restrictions;
    private List<String> appDomains;
    private URL authDialogDataHelpUrl;
    private String authDialogDescription;
    private String authDialogHeadline;
    private String authDialogPermsExplanation;
    private List<String> authReferralUserPerms;
    private List<String> authReferralFriendPerms;
    private String authReferralDefaultActivityPrivacy;
    private Boolean authReferralEnabled;
    private List<String> authReferralExtendedPerms;
    private String authReferralResponseType;
    private Boolean canvasFluidHeight;
    private Boolean canvasFluidWidth;
    private URL canvasUrl;
    private String contactEmail;
    private Long createdTime;
    private Long creatorUid;
    private URL deauthCallbackUrl;
    private String iphoneAppStoreId;
    private URL hostingUrl;
    private URL mobileWebUrl;
    private String pageTabDefaultName;
    private URL pageTabUrl;
    private URL privacyPolicyUrl;
    private URL secureCanvasUrl;
    private URL securePageTabUrl;
    private String serverIpWhitelist;
    private Boolean socialDiscovery;
    private URL termsOfServiceUrl;
    private String userSupportEmail;
    private URL userSupportUrl;
    private URL websiteUrl;

    private String canvasName;

    /*package*/ApplicationJSONImpl(HttpResponse res, Configuration conf) throws FacebookException {
        JSONObject json = res.asJSONObject();
        init(json);
        if (conf.isJSONStoreEnabled()) {
            DataObjectFactoryUtil.clearThreadLocalMap();
            DataObjectFactoryUtil.registerJSONObject(this, json);
        }
    }

    /*package*/ApplicationJSONImpl(JSONObject json) throws FacebookException {
        super();
        init(json);
    }

    private void init(JSONObject json) throws FacebookException {
        id = getRawString("id", json);
        name = getRawString("name", json);
        description = getRawString("description", json);
        category = getRawString("category", json);
        company = getRawString("company", json);
        iconUrl = getURL("icon_url", json);
        subcategory = getRawString("subcategory", json);
        link = getURL("link", json);
        logoUrl = getURL("logo_url", json);
        dailyActiveUsers = getRawString("daily_active_users", json);
        weeklyActiveUsers = getRawString("weekly_active_users", json);
        monthlyActiveUsers = getRawString("monthly_active_users", json);
        migrations = getBooleanMap("migrations", json);
        namespace = getRawString("namespace", json);
        restrictions = getStringMap("restrictions", json);
        appDomains = getStringList("app_domains", json);
        authDialogDataHelpUrl = getURL("auth_dialog_data_help_url", json);
        authDialogDescription = getRawString("auth_dialog_description", json);
        authDialogHeadline = getRawString("auth_dialog_headline", json);
        authDialogPermsExplanation = getRawString("auth_dialog_perms_explanation", json);
        authReferralUserPerms = getStringList("auth_referral_user_perms", json);
        authReferralFriendPerms = getStringList("auth_referral_friend_perms", json);
        authReferralDefaultActivityPrivacy = getRawString("auth_referral_default_activity_privacy", json);
        authReferralEnabled = getFlag("auth_referral_enabled", json);
        authReferralExtendedPerms = getStringList("auth_referral_extended_perms", json);
        authReferralResponseType = getRawString("auth_referral_response_type", json);
        canvasFluidHeight = getBoolean("canvas_fluid_height", json);
        canvasFluidWidth = getFlag("canvas_fluid_width", json);
        canvasUrl = getURL("canvas_url", json);
        contactEmail = getRawString("contact_email", json);
        createdTime = getLong("created_time", json);
        creatorUid = getLong("craeted_uid", json);
        deauthCallbackUrl = getURL("deauth_callback_url", json);
        iphoneAppStoreId = getRawString("iphone_app_store_id", json);
        hostingUrl = getURL("hosting_url", json);
        mobileWebUrl = getURL("mobile_web_url", json);
        pageTabDefaultName = getRawString("page_tab_default_name", json);
        pageTabUrl = getURL("page_tab_url", json);
        privacyPolicyUrl = getURL("privacy_policy_url", json);
        secureCanvasUrl = getURL("secure_canvas_url", json);
        securePageTabUrl = getURL("secure_page_tab_url", json);
        serverIpWhitelist = getRawString("server_ip_whitelist", json);
        socialDiscovery = getFlag("social_discovery", json);
        termsOfServiceUrl = getURL("terms_of_service_url", json);
        userSupportEmail = getRawString("user_support_email", json);
        userSupportUrl = getURL("user_support_url", json);
        websiteUrl = getURL("website_url", json);

        canvasName = getRawString("canvas_name", json);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public String getCompany() {
        return company;
    }

    public URL getIconUrl() {
        return iconUrl;
    }

    public String getSubcategory() {
        return subcategory;
    }

    public URL getLink() {
        return link;
    }

    public URL getLogoUrl() {
        return logoUrl;
    }

    public String getDailyActiveUsers() {
        return dailyActiveUsers;
    }

    public String getWeeklyActiveUsers() {
        return weeklyActiveUsers;
    }

    public String getMonthlyActiveUsers() {
        return monthlyActiveUsers;
    }

    public Map<String, Boolean> getMigrations() {
        return migrations;
    }

    public String getNamespace() {
        return namespace;
    }

    public Map<String, String> getRestrictions() {
        return restrictions;
    }

    public List<String> getAppDomains() {
        return appDomains;
    }

    public URL getAuthDialogDataHelpUrl() {
        return authDialogDataHelpUrl;
    }

    public String getAuthDialogDescription() {
        return authDialogDescription;
    }

    public String getAuthDialogHeadline() {
        return authDialogHeadline;
    }

    public String getAuthDialogPermsExplanation() {
        return authDialogPermsExplanation;
    }

    public List<String> getAuthReferralUserPerms() {
        return authReferralUserPerms;
    }

    public List<String> getAuthReferralFriendPerms() {
        return authReferralFriendPerms;
    }

    public String getAuthReferralDefaultActivityPrivacy() {
        return authReferralDefaultActivityPrivacy;
    }

    public Boolean authReferralEnabled() {
        return authReferralEnabled;
    }

    public List<String> getAuthReferralExtendedPerms() {
        return authReferralExtendedPerms;
    }

    public String getAuthReferralResponseType() {
        return authReferralResponseType;
    }

    public Boolean canvasFluidHeight() {
        return canvasFluidHeight;
    }

    public Boolean canvasFluidWidth() {
        return canvasFluidWidth;
    }

    public URL getCanvasUrl() {
        return canvasUrl;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public Long getCreatorUid() {
        return creatorUid;
    }

    public URL getDeauthCallbackUrl() {
        return deauthCallbackUrl;
    }

    public String getIphoneAppStoreId() {
        return iphoneAppStoreId;
    }

    public URL getHostingUrl() {
        return hostingUrl;
    }

    public URL getMobileWebUrl() {
        return mobileWebUrl;
    }

    public String getPageTabDefaultName() {
        return pageTabDefaultName;
    }

    public URL getPageTabUrl() {
        return pageTabUrl;
    }

    public URL getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public URL getSecureCanvasUrl() {
        return secureCanvasUrl;
    }

    public URL getSecurePageTabUrl() {
        return securePageTabUrl;
    }

    public String getServerIpWhitelist() {
        return serverIpWhitelist;
    }

    public Boolean socialDiscovery() {
        return socialDiscovery;
    }

    public URL getTermsOfServiceUrl() {
        return termsOfServiceUrl;
    }

    public String getUserSupportEmail() {
        return userSupportEmail;
    }

    public URL getUserSupportUrl() {
        return userSupportUrl;
    }

    public URL getWebsiteUrl() {
        return websiteUrl;
    }

    public String getCanvasName() {
        return canvasName;
    }

    @Override
    public String toString() {
        return "ApplicationJSONImpl [id=" + id + ", name=" + name
                + ", description=" + description + ", category=" + category
                + ", company=" + company + ", iconUrl=" + iconUrl
                + ", subcategory=" + subcategory + ", link=" + link
                + ", logoUrl=" + logoUrl + ", dailyActiveUsers="
                + dailyActiveUsers + ", weeklyActiveUsers=" + weeklyActiveUsers
                + ", monthlyActiveUsers=" + monthlyActiveUsers
                + ", migrations=" + migrations + ", namespace=" + namespace
                + ", restrictions=" + restrictions + ", appDomains="
                + appDomains + ", authDialogDataHelpUrl="
                + authDialogDataHelpUrl + ", authDialogDescription="
                + authDialogDescription + ", authDialogHeadline="
                + authDialogHeadline + ", authDialogPermsExplanation="
                + authDialogPermsExplanation + ", authReferralUserPerms="
                + authReferralUserPerms + ", authReferralFriendPerms="
                + authReferralFriendPerms
                + ", authReferralDefaultActivityPrivacy="
                + authReferralDefaultActivityPrivacy + ", authReferralEnabled="
                + authReferralEnabled + ", authReferralExtendedPerms="
                + authReferralExtendedPerms + ", authReferralResponseType="
                + authReferralResponseType + ", canvasFluidHeight="
                + canvasFluidHeight + ", canvasFluidWidth=" + canvasFluidWidth
                + ", canvasUrl=" + canvasUrl + ", contactEmail=" + contactEmail
                + ", createdTime=" + createdTime + ", creatorUid=" + creatorUid
                + ", deauthCallbackUrl=" + deauthCallbackUrl
                + ", iphoneAppStoreId=" + iphoneAppStoreId + ", hostingUrl="
                + hostingUrl + ", mobileWebUrl=" + mobileWebUrl
                + ", pageTabDefaultName=" + pageTabDefaultName
                + ", pageTabUrl=" + pageTabUrl + ", privacyPolicyUrl="
                + privacyPolicyUrl + ", secureCanvasUrl=" + secureCanvasUrl
                + ", securePageTabUrl=" + securePageTabUrl
                + ", serverIpWhitelist=" + serverIpWhitelist
                + ", socialDiscovery=" + socialDiscovery
                + ", termsOfServiceUrl=" + termsOfServiceUrl
                + ", userSupportEmail=" + userSupportEmail
                + ", userSupportUrl=" + userSupportUrl + ", websiteUrl="
                + websiteUrl + ", canvasName=" + canvasName + "]";
    }

}
