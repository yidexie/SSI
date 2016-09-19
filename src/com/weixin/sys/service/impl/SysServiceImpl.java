package com.weixin.sys.service.impl;

import java.lang.reflect.Proxy;
import java.net.URL;
import java.security.cert.X509Certificate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.json.JSONObject;

import com.weixin.core.pojo.UserSession;
import com.weixin.core.service.impl.BaseServiceImpl;
import com.weixin.core.util.CertificateUtils;
import com.weixin.core.util.Constants;
import com.weixin.core.util.DateManager;
import com.weixin.core.util.Des;
import com.weixin.core.util.MyUtil;
import com.weixin.core.util.StringUtil;
import com.weixin.core.util.Struts2Utils;
import com.weixin.sys.pojo.AdminUser;
import com.weixin.sys.pojo.SysLog;
import com.weixin.sys.service.SysService;

/**
 * @function
 * @author
 */
//  继承BaseServiceImpl， 接口SysService
//  Service 类提供服务，包括数据库操作，还有登录验证
public class SysServiceImpl extends BaseServiceImpl implements SysService {

	private static Logger logger = Logger.getLogger(SysServiceImpl.class);

	/**
	 * 
	 * @function 登录验证
	 * @param session
	 *            session对象
	 * @param sysUsers
	 *            用户登录数据
	 * @param rand
	 *            随机验证码
	 * @return String 返回是否登录成功与错误提示
	 */
	@SuppressWarnings("unchecked")
	public String login(HttpServletRequest request, AdminUser adminUser,
			String rand, String sign) {
		if (StringUtils.isEmpty(adminUser.getUsername()))
			return "用户名不能为空";
		String FLAG = StringUtils.trimToEmpty(request.getParameter("FLAG"));
		//  查看下request
		//  FLAG::  ""
		UserSession user = (UserSession) request.getSession().getAttribute(
				Constants.USERSESSION);
		
		//  user::  null
		if (!rand.equals("noCodeImage")) {// 没有验证码的页面，验证码为noCodeImage
			if (!StringUtils.equalsIgnoreCase(sign, "xxx")) {
				if (!StringUtils.equalsIgnoreCase(FLAG, "PICO")) {
					String rand_session = StringUtils.trimToEmpty(
							(String) request.getSession().getAttribute("rand"))
							.toLowerCase();
					//  根据string rand 和 request的rand 属性，确定验证码是否正确。
					if (!rand_session.equals(rand.toLowerCase())) {
						return "验证码错误！";
					}
				}
			}
		}

		adminUser.setPassword(MyUtil.encryptPWD(StringUtils
				.trimToEmpty(adminUser.getPassword())));
		// trimToEmpty 去除字符串中的控制符
		//  这里进行加密了。 在SysServiceImpl 中加密，为什么action 也会更改
		// 查询符合条件的用户
		
		AdminUser temp_user = null;
		// 获取对象，ibatis 中sql 语句自己写在配置文件中
		temp_user = (AdminUser) getObject(   // 既然这里设置了sqlMap的语句，返回的结果也已经确定了，是不是不用再强转了？
				"AdminUser.getUserByloginAndPassword", adminUser);   // 这里的sql 映射写在 AdminUser.xml 中
		//  sqlName 为AdminUser.getUserByloginAndPassword， 在sqlMap 配置文件中找到getUserByloginAndPassword 的sql 映射

		if (temp_user != null) {
			if (temp_user.getValid() == 0) {
				return "该账号已删除！";
			} else { // 登录成功
				HttpSession session = request.getSession();
				UserSession userSession = new UserSession();
				userSession.setId(temp_user.getId());
				userSession.setUsername(temp_user.getUsername());
				String privilege = temp_user.getPrivilege();
				if (privilege == null)
					privilege = "";
				userSession.setPrivilege(privilege);
				session.setAttribute(Constants.USERSESSION, userSession);   // 这里
			}
			return "";
		}
		return "用户名或密码错误！";

	}

	public String editPass(AdminUser adminUser) {
		String tips = "";
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.USERSESSION);
		adminUser.setPassword(MyUtil.encryptPWD(StringUtils
				.trimToEmpty(adminUser.getPassword())));
		adminUser.setNewPassword(MyUtil.encryptPWD(StringUtils
				.trimToEmpty(adminUser.getNewPassword())));

		// 验证输入的旧密码是否正确
		AdminUser temp = (AdminUser) this.getObject("AdminUser.getUserById",
				adminUser.getId());
		if (temp.getPassword().equals(adminUser.getPassword())) {
			// 更新密码
			try {
				this.updateObject("AdminUser.editPass", adminUser);
				tips = "修改密码成功！";

			} catch (Exception e) {
				tips = "修改密码失败！";
				logger.error("修改密码异常", e);
			}
		} else {
			tips = "您输入的旧密码不正确！";
		}
		return tips;
	}

}
