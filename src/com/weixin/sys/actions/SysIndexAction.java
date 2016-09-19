/** 
 *SysUsersAction.java 
 *@author administrator
 */
package com.weixin.sys.actions;

import org.apache.log4j.Logger;

import com.weixin.core.actions.BaseAction;

/**
 * @function 系统首页
 * @author administrator
 */

public class SysIndexAction extends BaseAction {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(SysIndexAction.class);

	private static final long serialVersionUID = 1L;
	
	private String tips = "";

	public String index() {
		return "index";
	}

	public String login() {
		//tips = sysService.login(getRequest(), sysUsers, rand, sign);
		if (tips.length() > 0) {
			return "login";
		} else
			return "index";
	}
}
