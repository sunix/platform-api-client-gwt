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
package com.codenvy.api.runner.gwt.client;

import com.codenvy.api.core.rest.shared.dto.Link;
import com.codenvy.api.project.shared.dto.RunnerEnvironmentTree;
import com.codenvy.api.runner.dto.ApplicationProcessDescriptor;
import com.codenvy.api.runner.dto.ResourcesDescriptor;
import com.codenvy.api.runner.dto.RunOptions;
import com.codenvy.api.runner.dto.RunnerDescriptor;
import com.codenvy.ide.collections.Array;
import com.codenvy.ide.rest.AsyncRequestCallback;

/**
 * Client for Runner service.
 *
 * @author Artem Zatsarynnyy
 */
public interface RunnerServiceClient {
    /**
     * Run an app on the application server.
     *
     * @param projectName
     *         name of the project to run
     * @param runOptions
     *         options to configure run process
     * @param callback
     *         the callback to use for the response
     */
    public void run(String projectName, RunOptions runOptions, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Get status of app.
     *
     * @param link
     *         link to get application's status
     * @param callback
     *         the callback to use for the response
     */
    public void getStatus(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Get runner processes by project name.
     *
     * @param project
     *         name of the project to get an appropriate runner processes
     * @param callback
     *         the callback to use for the response
     */
    public void getRunningProcesses(String project, AsyncRequestCallback<Array<ApplicationProcessDescriptor>> callback);

    /**
     * Retrieve logs from application server where app is launched.
     *
     * @param link
     *         link to retrieve logs
     * @param callback
     *         the callback to use for the response
     */
    public void getLogs(Link link, AsyncRequestCallback<String> callback);

    /**
     * Stop application server where app is launched.
     *
     * @param link
     *         link to stop an app
     * @param callback
     *         the callback to use for the response
     */
    public void stop(Link link, AsyncRequestCallback<ApplicationProcessDescriptor> callback);

    /**
     * Get available runners.
     *
     * @param callback
     *         the callback to use for the response
     */
    public void getRunners(AsyncRequestCallback<RunnerEnvironmentTree> callback);

    /**
     * Get available runners.
     *
     * @param projectName
     *         name of the project
     * @param callback
     *         the callback to use for the response
     */
    public void getRunners(String projectName, AsyncRequestCallback<Array<RunnerDescriptor>> callback);

    /**
     * Get resources for user in current workspace.
     *
     * @param callback
     *         the callback to use for the response
     */
    public void getResources(AsyncRequestCallback<ResourcesDescriptor> callback);
}
