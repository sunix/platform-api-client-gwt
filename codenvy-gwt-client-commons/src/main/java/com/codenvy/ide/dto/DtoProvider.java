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
package com.codenvy.ide.dto;

/**
 * Provides implementation of DTO interface.
 *
 * @param <DTO>
 *         the type of DTO interface which implementation this provider provides
 * @author Artem Zatsarynnyy
 */
public interface DtoProvider<DTO> {
    /** Get type of interface which implementation this provider provides. */
    Class<? extends DTO> getImplClass();

    /** Provides implementation of DTO interface from the specified JSON string. */
    DTO fromJson(String json);

    /** Get new implementation of DTO interface. */
    DTO newInstance();
}
