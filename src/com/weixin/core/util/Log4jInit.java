package com.weixin.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet {
	/**
	 * <br>
	 * ***************************************************** <br>
	 * 功 能：初始化Log4j <br>
	 * ******************************************************
	 */
	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
		String path = getServletContext().getRealPath("/");
		System.setProperty("WORKDIR", path);
		String logFileProperties = "log4j.appender.myLogFile.File";

		String prefix = getServletContext().getRealPath("/");

		String filePath = prefix + getInitParameter("propfile");

		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			String logFile = prefix + props.getProperty(logFileProperties);// 设置路径
			props.setProperty(logFileProperties, logFile);
			PropertyConfigurator.configure(props);// 加载log4j配置信息
			System.out.println("加载log4j配置信息");
		} catch (IOException e) {
			System.out.println("Could not read configuration file [" + filePath
					+ "].");
			System.out.println("Ignoring configuration file [" + filePath
					+ "].");
			return;
		}

	}

}