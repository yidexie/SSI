package com.weixin.sys.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.weixin.core.service.BaseService;
import com.weixin.sys.pojo.AdminUser;

public interface SysService extends BaseService {

	/**
	 * 
	 * @function 登录验证
	 * @param request
	 * @param sysUsers
	 *            用户实体类
	 * @param rand
	 *            验证码
	 * @param sign
	 * @return String 返回是否登录成功与错误提示
	 */
	String login(HttpServletRequest request, AdminUser adminUser, String rand,
			String sign);

	String editPass(AdminUser adminUser);

}
