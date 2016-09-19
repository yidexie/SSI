package com.weixin.core.actions; 

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.googlecode.jsonplugin.annotations.JSON;
import com.opensymphony.xwork2.ActionSupport;
import com.weixin.core.pojo.Page;
import com.weixin.core.pojo.UserSession;
import com.weixin.core.util.Constants;
import com.weixin.core.util.ContextLookup;

/** 
 *Copyright(C):	
 *@n
 *@n File:			BaseAction.java
 *@n Function:		struts2 baseAction 整合
 *@n Author:		administrator
 */
@SuppressWarnings("unchecked")
public class BaseAction extends ActionSupport{
 
	private static final long serialVersionUID = 1L;
	 
	public static final String LIST = "list" ; //分页列表
	public static final String JSON = "json" ; //分页列表
	
	public String menuId;
	
	
	public Page page = new Page();             // 分页参数封装
	private int pageNo=1;
	 
	/************* 取得Request/Response/Session的简化函数 **************/
	/**
	 * 取得HttpSession的简化函数.
	 */
	@JSON(serialize=false)
	public static HttpSession getSession() {
		 
		return ServletActionContext.getRequest().getSession();
	}

	/**
	 * 取得HttpRequest的简化函数.
	 */
	@JSON(serialize=false)
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 取得HttpResponse的简化函数.
	 */
	@JSON(serialize=false)
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}
	
	public static UserSession getUser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		UserSession userSession = (UserSession)request.getSession().getAttribute(Constants.USERSESSION);
		return userSession;
	}

	/**
	 * 取得Request Parameter的简化方法.
	 */
	@JSON(serialize=false)
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/**
    *
    * @function 获取bean
    */ 
	public Object getBean(String beanName) {
		return ContextLookup.getBean(ServletActionContext.getServletContext(), beanName);
	}
	
	
 
	/**
	 * 获取Object 类名称
	 */  
	@JSON(serialize=false)
	public static String getClassName(Object obj) {
		return obj.getClass().getName();
	}
	@JSON(serialize=false)
	public Page getPage() {
		return page;
	} 
	
	public void setPage(Page page) {
		this.page = page;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}
}
 