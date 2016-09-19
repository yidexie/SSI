package com.weixin.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.weixin.core.exception.ServiceException;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: LogInterceptor.java
 * @n Function: struts2 异常管理
 */
public class ExceptionInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(ExceptionInterceptor.class);

	public String intercept(ActionInvocation invocation) throws Exception {
		String result = "error";

		try {

			result = invocation.invoke();

		} catch (NumberFormatException ex) {
			logger.error("error：", ex);
			throw new ServiceException("请输入正确的数字！");
		} catch (DataAccessException ex) {

			logger.error("error：", ex);
			throw new ServiceException("数据库操作失败！");

		} catch (UnsupportedEncodingException ex) {

			logger.error("error：", ex);
			throw new ServiceException("编码异常！");

		} catch (NullPointerException ex) {

			logger.error("error：", ex);
			throw new ServiceException("调用了未经初始化的对象或者是不存在的对象！");

		} catch (IOException ex) {

			logger.error("error：", ex);
			throw new ServiceException("IO异常！");

		} catch (ClassNotFoundException ex) {

			logger.error("error：", ex);
			throw new ServiceException("指定的类不存在！");

		} catch (ArithmeticException ex) {

			logger.error("error：", ex);
			throw new ServiceException("数学运算异常！");

		} catch (ArrayIndexOutOfBoundsException ex) {

			logger.error("error：", ex);
			throw new ServiceException("数组下标越界!");

		} catch (IllegalArgumentException ex) {

			logger.error("error：", ex);
			throw new ServiceException("方法的参数错误！");

		} catch (ClassCastException ex) {

			logger.error("error：", ex);
			throw new ServiceException("类型强制转换错误！");

		} catch (SecurityException ex) {

			logger.error("error：", ex);
			throw new ServiceException("违背安全原则异常！");

		} catch (SQLException ex) {

			logger.error("error：", ex);
			throw new ServiceException("操作数据库异常！");

		} catch (NoSuchMethodError ex) {

			logger.error("error：", ex);
			throw new ServiceException("方法末找到异常！");

		} catch (InternalError ex) {

			logger.error("error：", ex);
			throw new ServiceException("Java虚拟机发生了内部错误!");

		} catch (ServiceException ex) {

			logger.error("error：", ex);
			throw ex;

		} catch (Exception ex) {

			logger.error("error：", ex);
			throw new ServiceException("程序内部错误!，操作失败！");
		}

		return result;

	}

}
