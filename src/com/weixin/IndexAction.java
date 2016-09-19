package com.weixin;

import org.apache.log4j.Logger;

import com.weixin.core.actions.BaseAction;

/**
 * @function 单点登录
 * @author administrator
 */

public class IndexAction extends BaseAction {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(IndexAction.class);

	private static final long serialVersionUID = 1L;

	private String jsesin;

	public String getJsesin() {
		return jsesin;
	}

	public void setJsesin(String jsesin) {
		this.jsesin = jsesin;
	}

	public String login() {
		logger.info("jsesin:" + jsesin);
		return null;
	}
}
