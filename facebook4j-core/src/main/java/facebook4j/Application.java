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

import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface Application {
    String getId();
    String getName();
    String getDescription();
    String getCategory();
    String getCompany();
    URL getIconUrl();
    String getSubcategory();
    URL getLink();
    URL getLogoUrl();
    String getDailyActiveUsers();
    String getWeeklyActiveUsers();
    String getMonthlyActiveUsers();
    Map<String, Boolean> getMigrations();
    String getNamespace();
    Map<String, String> getRestrictions();
    List<String> getAppDomains();
    URL getAuthDialogDataHelpUrl();
    String getAuthDialogDescription();
    String getAuthDialogHeadline();
    String getAuthDialogPermsExplanation();
    List<String> getAuthReferralUserPerms();
    List<String> getAuthReferralFriendPerms();
    String getAuthReferralDefaultActivityPrivacy();
    Boolean authReferralEnabled();
    List<String> getAuthReferralExtendedPerms();
    String getAuthReferralResponseType();
    Boolean canvasFluidHeight();
    Boolean canvasFluidWidth();
    URL getCanvasUrl();
    String getContactEmail();
    Long getCreatedTime();
    Long getCreatorUid();
    URL getDeauthCallbackUrl();
    String getIphoneAppStoreId();
    URL getHostingUrl();
    URL getMobileWebUrl();
    String getPageTabDefaultName();
    URL getPageTabUrl();
    URL getPrivacyPolicyUrl();
    URL getSecureCanvasUrl();
    URL getSecurePageTabUrl();
    String getServerIpWhitelist();
    Boolean socialDiscovery();
    URL getTermsOfServiceUrl();
    String getUserSupportEmail();
    URL getUserSupportUrl();
    URL getWebsiteUrl();
    
    String getCanvasName();
}
