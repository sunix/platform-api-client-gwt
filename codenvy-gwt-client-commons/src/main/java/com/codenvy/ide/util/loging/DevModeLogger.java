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
package com.codenvy.ide.util.loging;

import com.codenvy.ide.util.ExceptionUtils;
import com.codenvy.ide.util.loging.LogConfig.LogLevel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;


/**
 * @author <a href="mailto:evidolob@exoplatform.com">Evgen Vidolob</a>
 * @version $Id:
 */
class DevModeLogger implements Logger {

    /** @see com.codenvy.ide.util.loging.Logger#debug(java.lang.Class, java.lang.Object[]) */
    @Override
    public void debug(Class<?> clazz, Object... args) {
        // DEBUG is the lowest log level, but we use <= for consistency, and in
        // case we ever decide to introduce a SPAM level.
        if (LogConfig.getLogLevel().ordinal() <= LogLevel.DEBUG.ordinal()) {
            log(clazz, LogLevel.DEBUG, args);
        }

    }

    /** @see com.codenvy.ide.util.loging.Logger#error(java.lang.Class, java.lang.Object[]) */
    @Override
    public void error(Class<?> clazz, Object... args) {
        log(clazz, LogLevel.ERROR, args);
    }

    /** @see com.codenvy.ide.util.loging.Logger#info(java.lang.Class, java.lang.Object[]) */
    @Override
    public void info(Class<?> clazz, Object... args) {
        if (LogConfig.getLogLevel().ordinal() <= LogLevel.INFO.ordinal()) {
            log(clazz, LogLevel.INFO, args);
        }
    }

    /** @see com.codenvy.ide.util.loging.Logger#isLoggingEnabled() */
    @Override
    public boolean isLoggingEnabled() {
        return true;
    }

    /** @see com.codenvy.ide.util.loging.Logger#warn(java.lang.Class, java.lang.Object[]) */
    @Override
    public void warn(Class<?> clazz, Object... args) {
        if (LogConfig.getLogLevel().ordinal() <= LogLevel.WARNING.ordinal()) {
            log(clazz, LogLevel.WARNING, args);
        }
    }

    private static native void invokeBrowserLogger(String logFuncName, Object o) /*-{
        try {
            if ($wnd.console && $wnd.console[logFuncName]) {
                $wnd.console[logFuncName](o);
            }
        } catch (e) {
            console.log("EXCEPTION : " + e.message);
        }
    }-*/;

    private static void log(Class<?> clazz, LogLevel logLevel, Object... args) {
        String prefix =
                new StringBuilder(logLevel.toString()).append(" (").append(clazz.getName()).append("): ").toString();

        for (Object o : args) {
            if (o instanceof String) {
                logToDevMode(prefix + (String)o);
                logToBrowser(logLevel, prefix + (String)o);
            } else if (o instanceof Throwable) {
                Throwable t = (Throwable)o;
                logToDevMode(prefix + "(click for stack)", t);
                logToBrowser(logLevel, prefix + ExceptionUtils.getStackTraceAsString(t));
            } else if (o instanceof JavaScriptObject) {
                logToDevMode(prefix + "(JSO, see browser's console log for details)");
                logToBrowser(logLevel, prefix + "(JSO below)");
                logToBrowser(logLevel, o);
            } else {
                logToDevMode(prefix + (o != null ? o.toString() : "(null)"));
                logToBrowser(logLevel, prefix + (o != null ? o.toString() : "(null)"));
            }
        }
    }

    private static void logToBrowser(LogLevel logLevel, Object o) {
        switch (logLevel) {
            case DEBUG:
                invokeBrowserLogger("debug", o);
                break;
            case INFO:
                invokeBrowserLogger("info", o);
                break;
            case WARNING:
                invokeBrowserLogger("warn", o);
                break;
            case ERROR:
                invokeBrowserLogger("error", o);
                break;
            default:
                invokeBrowserLogger("log", o);
        }
    }

    private static void logToDevMode(String msg) {
        if (!GWT.isScript()) {
            GWT.log(msg);
        }
    }

    private static void logToDevMode(String msg, Throwable t) {
        if (!GWT.isScript()) {
            GWT.log(msg, t);
        }
    }

}
