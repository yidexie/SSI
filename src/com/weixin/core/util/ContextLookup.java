package com.weixin.core.util;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: ContextLookup.java
 * @n Function:
 */
public class ContextLookup {
	private static ApplicationContext ctx = null;

	private static final Object LOCK = new Object();

	public static final Object getBean(ServletContext context, String beanName) {
		if (ctx == null) {
			synchronized (LOCK) {
				if (ctx == null) {
					ctx = WebApplicationContextUtils
							.getRequiredWebApplicationContext(context);
				}
			}
		}
		return ctx.getBean(beanName);
	}

	public static final ApplicationContext getCtx() {
		return ctx;
	}

}
