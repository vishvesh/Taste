package com.taste.aop;

import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.log4j.Logger;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;

/**
 * Extends {@link CustomizableTraceInterceptor} to provide custom logging levels
 */
public class TraceInterceptor extends CustomizableTraceInterceptor {

	private static final long serialVersionUID = 287162721460370957L;
	protected static Logger logger4J = Logger.getLogger("aop");

	@Override
	protected void writeToLog(Log logger, String message, Throwable ex) {
		if (ex != null) {
			logger4J.info(message, ex);
		} else {
			logger4J.info(message);
		}
	}

	@Override
	protected boolean isInterceptorEnabled(MethodInvocation invocation, Log logger) {
		return true;
	}
}