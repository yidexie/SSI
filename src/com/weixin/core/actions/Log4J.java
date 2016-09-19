package com.weixin.core.actions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;


/** 
 *Copyright(C):	
 *@n
 *@n File:			Log4J.java
 *@n Function:		初始化log4j
 *@n Author:		administrator
 */
public class Log4J extends HttpServlet {

	  
	private static final long serialVersionUID = 1L;

	/**
     *<br> *****************************************************
     *<br> 功   能：初始化Log4j 
     *<br> ******************************************************
     */
	public void init() throws ServletException {
		
		String logFileProperties = "log4j.appender.myLogFile.File";
 
		String prefix = getServletContext().getRealPath("/");
		
		String filePath = prefix + getInitParameter("propfile");
  
		Properties props = new Properties();
        try {
            FileInputStream istream = new FileInputStream(filePath);
            props.load(istream);
            istream.close();  
            String logFile = prefix + props.getProperty(logFileProperties);//设置路径
            props.setProperty(logFileProperties,logFile);
            PropertyConfigurator.configure(props);//装入log4j配置信息
        } catch (IOException e) {
        	System.out.println("Could not read configuration file [" + filePath + "].");
        	System.out.println("Ignoring configuration file [" + filePath + "].");
            return;
        }

	}
	

}
