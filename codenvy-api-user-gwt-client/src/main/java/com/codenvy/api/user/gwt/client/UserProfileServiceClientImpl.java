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
import com.codenvy.ide.dto.DtoFactory;
import com.codenvy.ide.json.JsonHelper;
import com.codenvy.ide.rest.AsyncRequestCallback;
import com.codenvy.ide.rest.AsyncRequestFactory;
import com.codenvy.ide.rest.AsyncRequestLoader;
import com.google.inject.Inject;
import com.google.inject.name.Named;

import java.util.Map;

import static com.codenvy.ide.rest.HTTPHeader.ACCEPT;
import static com.codenvy.ide.rest.HTTPHeader.CONTENT_TYPE;
import static com.codenvy.ide.MimeType.APPLICATION_JSON;


/**
 * Implementation for {@link UserProfileServiceClient}.
 *
 * @author Ann Shumilova
 */
public class UserProfileServiceClientImpl implements UserProfileServiceClient {
    private final String              PROFILE;
    private final String              PREFS;
    private final AsyncRequestLoader  loader;
    private final AsyncRequestFactory asyncRequestFactory;
    private final DtoFactory          dtoFactory;

    @Inject
    protected UserProfileServiceClientImpl(@Named("restContext") String restContext,
                                           AsyncRequestLoader loader,
                                           AsyncRequestFactory asyncRequestFactory, DtoFactory dtoFactory) {
        this.loader = loader;
        this.asyncRequestFactory = asyncRequestFactory;
        this.dtoFactory = dtoFactory;
        PROFILE = restContext + "/profile/";
        PREFS = PROFILE + "prefs";
    }

    /** {@inheritDoc} */
    @Override
    public void getCurrentProfile(AsyncRequestCallback<ProfileDescriptor> callback) {
        asyncRequestFactory.createGetRequest(PROFILE)
                           .header(ACCEPT, APPLICATION_JSON)
                           .loader(loader, "Retrieving current user's profile...")
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateCurrentProfile(Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback) {
        asyncRequestFactory.createPostRequest(PROFILE, null)
                           .header(ACCEPT, APPLICATION_JSON)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .data(JsonHelper.toJson(updates))
                           .loader(loader, "Updating current user's profile...")
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void getProfileById(String id, AsyncRequestCallback<ProfileDescriptor> callback) {
        String requestUrl = PROFILE + id;

        asyncRequestFactory.createGetRequest(requestUrl)
                           .header(ACCEPT, APPLICATION_JSON)
                           .loader(loader, "Getting user's profile...")
                           .send(callback);
    }

    @Override
    public void getPreferences(String filter, AsyncRequestCallback<Map<String, String>> callback) {
        asyncRequestFactory.createGetRequest(PREFS)
                           .header(ACCEPT, APPLICATION_JSON)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .loader(loader, "Getting user's preferences...")
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updateProfile(String id, Map<String, String> updates, AsyncRequestCallback<ProfileDescriptor> callback) {
        String requestUrl = PROFILE + id;

        asyncRequestFactory.createPostRequest(requestUrl, null)
                           .header(ACCEPT, APPLICATION_JSON)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .data(JsonHelper.toJson(updates))
                           .loader(loader, "Updating user's profile...")
                           .send(callback);
    }

    /** {@inheritDoc} */
    @Override
    public void updatePreferences(Map<String, String> update, AsyncRequestCallback<ProfileDescriptor> callback) {
        final String data = JsonHelper.toJson(update);
        asyncRequestFactory.createPostRequest(PREFS, null)
                           .header(ACCEPT, APPLICATION_JSON)
                           .header(CONTENT_TYPE, APPLICATION_JSON)
                           .data(data)
                           .loader(loader)
                           .send(callback);
    }

}
