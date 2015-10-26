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

package facebook4j.api;

import facebook4j.FacebookException;
import facebook4j.Permission;

import java.util.List;

/**
 * @author Ryuji Yamashita - roundrop at gmail.com
 */
public interface PermissionMethods {
    /**
     * Returns the permissions granted the current user to the current application.
     * @return permission names
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    List<Permission> getPermissions() throws FacebookException;

    /**
     * Returns the permissions granted a user to the current application.
     * @param userId the ID of a user
     * @return permission names
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    List<Permission> getPermissions(String userId) throws FacebookException;


    /**
     * Revokes all permissions from the current application.
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean revokeAllPermissions() throws FacebookException;

    /**
     * Revokes all permissions from the current application.
     * @param userId the ID of a user
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean revokeAllPermissions(String userId) throws FacebookException;

    /**
     * Revokes a specific permission from the current application.
     * @param permissionName permission name
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean revokePermission(String permissionName) throws FacebookException;

    /**
     * Revokes a specific permission from the current application.
     * @param userId the ID of a user
     * @param permissionName permission name
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean revokePermission(String userId, String permissionName) throws FacebookException;
    
    /**
     * This method is an alias of: {@link facebook4j.api.PermissionMethods#revokeAllPermissions()} .
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean deleteAllPermissions() throws FacebookException;

    /**
     * This method is an alias of: {@link facebook4j.api.PermissionMethods#revokeAllPermissions()} .
     * @param userId the ID of a user
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean deleteAllPermissions(String userId) throws FacebookException;

    /**
     * This method is an alias of: {@link facebook4j.api.PermissionMethods#revokePermission(String)} .
     * @param permissionName permission name
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean deletePermission(String permissionName) throws FacebookException;

    /**
     * This method is an alias of: {@link facebook4j.api.PermissionMethods#revokePermission(String, String)} .
     * @param userId the ID of a user
     * @param permissionName permission name
     * @return true if revoke is succressful
     * @throws FacebookException when Facebook service or network is unavailable
     * @see <a href="https://developers.facebook.com/docs/reference/api/user/#permissions">User#permissions - Facebook Developers</a>
     */
    boolean deletePermission(String userId, String permissionName) throws FacebookException;
    
}
