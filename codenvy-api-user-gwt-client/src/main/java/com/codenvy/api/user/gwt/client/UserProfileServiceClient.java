/*******************************************************************************
 * Copyright (c) 2012-2014 Codenvy, S.A.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Codenvy, S.A. - initial API and implementation
 *******************************************************************************/
package com.codenvy.api.user.gwt.client;

import com.codenvy.api.user.shared.dto.ProfileDescriptor;
import com.codenvy.ide.rest.AsyncRequestCallback;

import javax.annotation.Nonnull;
import java.util.Map;

/**
 * GWT Client for User Profile Service.
 *
 * @author Ann Shumilova
 */
public interface UserProfileServiceClient {

    /**
     * Get current user's profile.
     *
     * @param callback
     */
    void getCurrentProfile(AsyncRequestCallback<ProfileDescriptor> callback);

    /**
     * Update current user's profile.
     *
     * @param updates
     *         attributes to update
     * @param callback
     */
    void updateCurrentProfile(@Nonnull Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback);

    /**
     * Get profile by id.
     *
     * @param id
     *         profile's id
     * @param callback
     */
    void getProfileById(@Nonnull String id, AsyncRequestCallback<ProfileDescriptor> callback);

    void getPreferences(String filter, AsyncRequestCallback<Map<String, String>> callback);

    /**
     * Update profile.
     *
     * @param id
     *         profile's id
     * @param updates
     *         attributes to update
     * @param callback
     */
    void updateProfile(@Nonnull String id, Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback);

    /**
     * Update preferences.
     *
     * @param prefsToUpdate
     *         preferences to update
     * @param callback
     */
    void updatePreferences(@Nonnull Map<String, String> prefsToUpdate, AsyncRequestCallback<ProfileDescriptor> callback);
}
