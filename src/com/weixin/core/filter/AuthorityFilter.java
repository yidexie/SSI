package com.weixin.core.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.weixin.core.pojo.UserSession;
import com.weixin.core.util.Constants;

public class AuthorityFilter implements Filter {
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) session
				.getAttribute(Constants.USERSESSION);

		if (userSession == null || userSession.getId() <= 0) {
			// System.out.println("userSession null");
			response.sendRedirect("/Weixin/sys/actions/sys-users!login.action");// 如果未登录话那么跳转到登录界面
		} else {
			// System.out.println(userSession.getId());
			chain.doFilter(request, response);// 如果已登录可以对相关页面进行访问
		}
	}

	public void destroy() {
		// TODO Auto-generated method stub

	}

	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
