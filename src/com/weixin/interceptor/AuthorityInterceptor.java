package com.weixin.interceptor;

import java.util.Map;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.weixin.core.pojo.UserSession;
import com.weixin.core.util.Constants;
import com.weixin.core.util.MyUtil;
import com.weixin.core.util.Struts2Utils;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: AuthorityInterceptor.java
 * @n Function: struts2 权限验证
 */
public class AuthorityInterceptor extends AbstractInterceptor {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();
		Map session = ctx.getSession();
		UserSession userSession = (UserSession) session
				.get(Constants.USERSESSION);

		if (userSession != null) // 若sesson不为空则执行
		{
			return invocation.invoke();
		} else { // 若session为空，则只允许执行登录操作

			String url = ServletActionContext.getRequest().getRequestURL()
					.toString();
			int url_loginIndex = url
					.indexOf("sys/actions/sys-users!login.action");
			int url_ssoIndex = url.indexOf("sys/actions/sys-users!sso.action");
			int url_verifyIndex = url
					.indexOf("sys/actions/sys-users!verifyUser.action");

			if (url_loginIndex == -1 && url_ssoIndex == -1
					&& url_verifyIndex == -1)
				url_loginIndex = url
						.indexOf("sys/actions/sys-users!loginOut.action");
			// if(url_loginIndex == -1) url_loginIndex =
			// url.indexOf("ShowClueRelation.jsp");
			if (url_loginIndex >= 0 || url_ssoIndex >= 0
					|| url_verifyIndex >= 0) {
				return invocation.invoke();
			}

		}
		ctx.put("tip", "您没有执行该操作的权限，请先登录系统！");
		return "sessionOutTime";
	}
}
