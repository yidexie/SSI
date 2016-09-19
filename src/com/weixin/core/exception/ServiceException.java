package com.weixin.core.exception;

 
/** 
 *Copyright(C):	
 *@n
 *@n File:			ServiceException.java
 *@n Function:		业务异常(dao层不用捕获异常，统一交给控制器处理)，本异常一般为手工抛出的业务异常。
 *@n Author:		administrator整合	
 */
public class ServiceException extends RuntimeException {
 
	private static final long serialVersionUID = 1L;
	
	public ServiceException() {
		super();
	}

	public ServiceException(String msg) {
		super(msg);
	}
	public ServiceException(Throwable t){
		super(t);
	}

}
