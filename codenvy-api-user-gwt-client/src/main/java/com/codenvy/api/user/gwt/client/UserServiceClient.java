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

import com.codenvy.api.user.shared.dto.UserDescriptor;
import com.codenvy.ide.rest.AsyncRequestCallback;

import javax.annotation.Nonnull;

/**
 * GWT Client for User Service.
 *
 * @author Ann Shumilova
 */
public interface UserServiceClient {

    /**
     * Create new user.
     *
     * @param token
     *         user's token
     * @param isTemporary
     *         if <code>true</code> - is temporary user
     * @param callback
     */
    public void createUser(@Nonnull String token, boolean isTemporary, AsyncRequestCallback<UserDescriptor> callback);

    /**
     * Get current user's information.
     *
     * @param callback
     */
    public void getCurrentUser(AsyncRequestCallback<UserDescriptor> callback);

    /**
     * Update user's password.
     *
     * @param password
     *         new password
     * @param callback
     */
    public void updatePassword(@Nonnull String password, AsyncRequestCallback<Void> callback);

    /**
     * Get user's information by its id.
     *
     * @param id
     *         user's id
     * @param callback
     */
    public void getUserById(@Nonnull String id, AsyncRequestCallback<UserDescriptor> callback);

    /**
     * Get user's information by its email.
     *
     * @param email
     *         user's email
     * @param callback
     */
    public void getUserByEmail(@Nonnull String email, AsyncRequestCallback<UserDescriptor> callback);

    /**
     * Remove user.
     *
     * @param id
     *         user's id to remove
     * @param callback
     */
    public void removeUser(@Nonnull String id, AsyncRequestCallback<Void> callback);
}
