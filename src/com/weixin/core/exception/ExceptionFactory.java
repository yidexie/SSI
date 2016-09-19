/** 
  *ExceptionFactory.java 2010-3-29 Administrator
  */
package com.weixin.core.exception;

import java.sql.SQLException;

/**
 * @function 创建各种异常
 * @author Administrator
 */
public class ExceptionFactory {
	
	
	 public static void addServiceException(String msg) throws ServiceException{
		 
		 throw new ServiceException(msg);
	 }
	 
	 public static void addSqlException(String msg) throws SQLException{
		 
		 throw new SQLException(msg);
	 }
	 
	 public static void addCommonException(String msg) throws Exception{
		 
		 throw new Exception(msg);
	 }
	 
	 
}
