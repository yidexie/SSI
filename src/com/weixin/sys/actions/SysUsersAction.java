package com.weixin.sys.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ModelDriven;
import com.weixin.core.actions.BaseAction;
import com.weixin.core.pojo.UserSession;
import com.weixin.core.util.Base64;
import com.weixin.core.util.Constants;
import com.weixin.core.util.DateManager;
import com.weixin.core.util.HttpsClient;
import com.weixin.core.util.JavaUtil;
import com.weixin.core.util.MyUtil;
import com.weixin.core.util.StringUtil;
import com.weixin.core.util.Struts2Utils;
import com.weixin.sys.pojo.AdminUser;
import com.weixin.sys.pojo.SysLog;
import com.weixin.sys.service.SysService;

/**
 * @function 系统设置模块
 * @author administrator
 */
//  admin,  123456
public class SysUsersAction extends BaseAction implements
		ModelDriven<AdminUser> {
	// 这里使用了ModelDriven，jsp 里的数据可以直接进到 action

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SysUsersAction.class);

	private static final long serialVersionUID = 1L;

	private SysService sysService;

	private AdminUser adminUser = new AdminUser();

	private List<AdminUser> adminUserList = new ArrayList<AdminUser>();

	@SuppressWarnings("unchecked")
	private List SysUserList;

	String codeImg = "";

	String sign = "";

	String tips = "";

	long id;

	/**
	 * 保存添加用户
	 * 
	 * @return
	 */
	/**
	 * @return
	 */
	public String add() {
		try {
			/*
			 * tips = checkAccount(adminUser.getUsername()); if
			 * (tips.indexOf("删除") > -1) { tips = "所添加的用户已存在，是否恢复使用已存在的用户？";
			 * return JSON; } else if (tips.indexOf("存在") > -1) { return JSON; }
			 */
			adminUser.setPrivilege("");
			adminUser.setValid(1);
			adminUser.setPassword(MyUtil.encryptPWD(StringUtils
					.trimToEmpty(adminUser.getPassword())));
			adminUser.setCreateTime(DateManager.getNowTime());
			adminUser.setUpdateTime(DateManager.getNowTime());
			HttpServletRequest request = ServletActionContext.getRequest();
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.USERSESSION);
			String remark1 = "用户" + userSession.getUsername() + "添加系统用户："
					+ adminUser.getUsername();

			sysService.saveObject("AdminUser.addUser", adminUser);    //   保存用户
			tips = "添加成功";
			request.getSession().setAttribute(Constants.USERSESSION,
					userSession);
			// 记录登录日志
			SysLog sysLog = new SysLog();
			sysLog.setType(3);// 1表示登录2表示任务3表示管理
			sysLog.setUserid(userSession.getId());
			sysLog.setRemark(remark1);
			sysLog.setSystime(DateManager.getNowTime());
			sysLog.setIp(request.getRemoteAddr());
			sysService.saveObject("SysLog.addSysLog", sysLog);
		} catch (Exception e) {
			e.printStackTrace();
			tips = "添加用户失败,请返回重试!";
			logger.error("添加用户失败", e);
		}

		return JSON;
	}

	public String recoverUser() {
		try {
			if (!StringUtils.isEmpty(adminUser.getUsername())) {
				String userName = adminUser.getUsername();
				userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
				adminUser.setUsername(userName);
				// adminUser.setLogic_del(0);
				adminUser.setPassword(MyUtil.encryptPWD(StringUtils
						.trimToEmpty("123456")));
				adminUser.setUpdateTime(DateManager.getNowTime());
				sysService.updateObject("AdminUser.recoverUserByUserName",
						adminUser);
				tips = "用户【" + userName + "】已恢复使用，初始密码：123456，请及时修改密码！";
			}
		} catch (Exception e) {
			tips = "恢复用户:【" + adminUser.getUsername() + "】失败";
			logger.error("恢复用户异常", e);
		}
		return JSON;
	}

	public String checkAccount(String userName) {
		try {
			if (!StringUtils.isEmpty(userName)) {
				userName = new String(userName.getBytes("iso-8859-1"), "utf-8");
				adminUser.setUsername(userName);
				AdminUser user = (AdminUser) sysService.getObject(
						"AdminUser.getUserByUsername_", adminUser);
				if (user != null) {
					// if (user.getLogic_del() != null && user.getLogic_del() ==
					// 1) {
					// tips = "该用户已物理删除！";
					// } else {
					tips = "您添加的用户已存在";
					// }
				} else {
					tips = "新用户";
				}
			}
		} catch (Exception e) {
			logger.error("checkAccount()方法体异常", e);
		}
		return tips;
	}

	/**
	 * 删除用户
	 * 
	 * @return
	 */
	public String delete() {
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.USERSESSION);
			String ids = request.getParameter("ids");
			if (!StringUtil.isNullOrEmpty(ids)) {
				AdminUser user = new AdminUser();
				// user.setIds(ids);
				// user.setLogic_del(1);
				sysService.updateObject("AdminUser.deleteByIDS", ids);
				tips = "删除成功";
				// 记录登录日志
				String[] idStr = new String[] {};
				if (ids.indexOf(",") > 0) {
					idStr = ids.split(",");
				} else {
					idStr = new String[] { ids };
				}
				int length = idStr.length;
				SysLog sysLog = new SysLog();
				sysLog.setType(3);// 1表示登录2表示任务3表示管理
				sysLog.setUserid(userSession.getId());
				String remark = "用户" + userSession.getUsername() + "删除"
						+ length + "个系统用户";
				sysLog.setRemark(remark);
				sysLog.setSystime(DateManager.getNowTime());
				sysLog.setIp(request.getRemoteAddr());
				// 添加日志
				sysService.saveObject("SysLog.addSysLog", sysLog);
			}
		} catch (Exception e) {
			tips = "删除失败";
			logger.error("删除用户失败", e);
		}
		return JSON;
	}

	public String editPass() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.USERSESSION);
		adminUser.setId(userSession.getId());
		try {
			tips = sysService.editPass(adminUser);
			if (tips.indexOf("成功") > 0) {

				// 记录登录日志
				SysLog sysLog = new SysLog();
				sysLog.setType(3);// 1表示登录2表示任务3表示管理
				sysLog.setUserid(userSession.getId());
				String remark = "用户" + userSession.getUsername() + "修改密码";
				sysLog.setRemark(remark);
				sysLog.setSystime(DateManager.getNowTime());
				sysLog.setIp(request.getRemoteAddr());
				sysService.saveObject("SysLog.addSysLog", sysLog);
			}
			JSONArray uJson = new JSONArray();
			JSONObject o = new JSONObject();
			o.put("tips", tips);
			uJson.add(o);
			getResponse().getWriter().write(uJson.toJSONString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String preEdit() {
		try {
			adminUser = (AdminUser) sysService.getObject(
					"AdminUser.getUserById", id);

		} catch (Exception e) {
			logger.error("编辑用户异常", e);
		}
		return "preEdit";
	}

	/**
	 * 修改用户
	 * 
	 * @return
	 */
	public String editUser() {
		UserSession userSession = this.getUser();
		HttpServletRequest request = this.getRequest();
		if (!StringUtil.isNullOrEmpty(adminUser.getPassword()))
			adminUser.setPassword(MyUtil.encryptPWD(StringUtils
					.trimToEmpty(adminUser.getPassword())));

		// String validTime = adminUser.getValid_Time();
		// if (validTime.equals("")) {
		// adminUser.setValid_Time(null);
		// } else if (!StringUtils.isEmpty(validTime)) {
		// adminUser.setValid_Time(validTime + " 23:59:59");
		// }
		String time = DateManager.getNowTime();
		adminUser.setUpdateTime(time);
		try {
			// adminUser.setUpdateBaseInfo(1);
			sysService.updateObject("AdminUser.editUser", adminUser);
			tips = "修改成功！";
			// 记录日志
			SysLog sysLog = new SysLog();
			sysLog.setType(3);// 1表示登录2表示任务3表示管理
			sysLog.setUserid(userSession.getId());
			String remark = "用户" + userSession.getUsername() + "修改系统用户："
					+ adminUser.getUsername();
			sysLog.setRemark(remark);
			sysLog.setSystime(DateManager.getNowTime());
			sysLog.setIp(request.getRemoteAddr());
			sysService.saveObject("SysLog.addSysLog", sysLog);

			String path = "<script>alert('"
					+ tips
					+ "');"
					+ "parent.location.href='sys-users!list.action';parent.CloseMyModal();</script>";

			PrintWriter out = null;
			try {
				out = getResponse().getWriter();
				getResponse().getWriter().print(path);
				out.close();
			} catch (Exception et) {
				out.close();
			}
			return null;
		} catch (Exception e) {
			logger.error(e.toString());
			if (e.toString().indexOf("IX_AdminUser") != -1)
				tips = "您修改的用户名已存在！";
			else
				tips = "修改用户失败，请返回重试！";
			String path = "<script>alert('" + tips + "');</script>";
			PrintWriter out = null;
			try {
				out = getResponse().getWriter();
				getResponse().getWriter().print(path);
				out.close();
			} catch (Exception et) {
				out.close();
			}
			return null;
		}
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public List<AdminUser> getAdminUserList() {
		return adminUserList;
	}

	public long getId() {
		return id;
	}

	public AdminUser getModel() {
		return adminUser;
	}

	public String getSign() {
		return sign;
	}

	@com.googlecode.jsonplugin.annotations.JSON(serialize = false)
	public SysService getSysService() {
		return sysService;
	}

	@SuppressWarnings("unchecked")
	public List getSysUserList() {
		return SysUserList;
	}

	public AdminUser getSysUsers() {
		return adminUser;
	}

	public String getTips() {
		return tips;
	}

	public String index() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.USERSESSION);
		return "index";
	}

	/**
	 * 
	 * @return 描述:用户管理列表
	 */
	public String list() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.USERSESSION);
		page = sysService.getPageList("AdminUser.getPageList",
				"AdminUser.getTotalCount", page, adminUser);

		return "list";
	}

	public String login() {
		// 验证用户名/密码是否正确
		// 这里，adminUser 中数据加密
		tips = sysService.login(getRequest(), adminUser, codeImg, sign);
		// 由于ModelDriven<AdminUser>，用户名和密码自动装入
		if (tips.length() > 0) {
			return "loginImg";
		} else {
			System.out.println("else");
			HttpServletRequest request = ServletActionContext.getRequest();
			UserSession userSession = (UserSession) request.getSession()
					.getAttribute(Constants.USERSESSION);
			// 记录登录日志
			SysLog sysLog = new SysLog();
			sysLog.setType(1);// 1表示登录2表示任务3表示管理
			sysLog.setUserid(userSession.getId());
			String remark = "用户" + userSession.getUsername() + "登录进入系统";
			sysLog.setRemark(remark);
			sysLog.setSystime(DateManager.getNowTime());
			sysLog.setIp(request.getRemoteAddr());
			sysService.saveObject("SysLog.addSysLog", sysLog);   // 用ibatis 记录日志
			// 跳转至首页，用return "index"会使IE链接保持在login.action下
			String path = "<script>location.href='sys-users!index.action'</script>";
			PrintWriter out = null;
			try {
				out = getResponse().getWriter();   //  获取response 的writer， 输出js 脚本跳转
				getResponse().getWriter().print(path);
				out.close();
			} catch (IOException e) {
				logger.error(e);
				out.close();
			}
			return null;
			// return "index";
		}

	}

	public String logout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession) request.getSession()
				.getAttribute(Constants.USERSESSION);
		// 记录登录日志
		SysLog sysLog = new SysLog();
		sysLog.setType(1);// 1表示登录2表示任务3表示管理
		sysLog.setUserid(userSession.getId());
		String remark = "用户" + userSession.getUsername() + "退出系统";
		sysLog.setRemark(remark);
		sysLog.setSystime(DateManager.getNowTime());
		sysLog.setIp(request.getRemoteAddr());

		sysService.saveObject("SysLog.addSysLog", sysLog);
		HttpSession session = this.getSession();

		session.invalidate();
		return "login";
	}

	/**
	 * 添加用户的输入页面
	 * 
	 * @return
	 */
	public String preAdd() {
		return "preAdd";
	}

	public String preEditPass() {
		return "preEditPass";
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setAdminUserList(List<AdminUser> adminUserList) {
		this.adminUserList = adminUserList;
	}

	public void setCodeImg(String codeImg) {
		this.codeImg = codeImg;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@com.googlecode.jsonplugin.annotations.JSON(serialize = false)
	public void setSysService(SysService sysService) {
		this.sysService = sysService;
	}

	@SuppressWarnings("unchecked")
	public void setSysUserList(List sysUserList) {
		SysUserList = sysUserList;
	}

	public void setSysUsers(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String showEditPassword() {
		return "editpassword";
	}
}
