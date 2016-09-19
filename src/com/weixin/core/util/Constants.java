package com.weixin.core.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Constants extends HttpServlet {
	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(Constants.class);

	private static final long serialVersionUID = 1L;

	public static final String USERSESSION = "UserSession";

	public static String LOGO1 = "logo1";

	public static String LOGO2 = "logo2";

	public static String ISUKEY = "";

	public static String centerUrl;

	public static String copyright;// 版权信息

	private static String classpath = Constants.class.getResource("/")
			.getPath().replaceAll("%20", " ");

	private static String path = classpath.replaceAll("WEB-INF/classes/", "");

	static {
		// initSysParam();
	}

	private static void initSysParam() {

		String classpath = Constants.class.getResource("/").getPath()
				.replaceAll("%20", " ");
		String filePath = classpath + "database.properties";
		Properties props = new Properties();
		try {

			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			PropertyConfigurator.configure(props);

		} catch (IOException e) {
			System.out.println("Could not read configuration file [" + filePath
					+ "].");
			System.out.println("Ignoring configuration file [" + filePath
					+ "].");
		}

	}

	public void init() throws ServletException {
		initSysParam();
		// 执行修改数据库脚本
		// 读取版本信息@2013-11-8
		copyright = MyUtil.getConf(path, "copyright");
		if (copyright.equals(""))
			copyright = "Copyright (C) 2013";
	}

	/**
	 * 
	 * @param beanName
	 *            spring中的注册名
	 * @return spring中的注册对象
	 */
	public Object getBean(String beanName) {
		return ContextLookup.getBean(getServletContext(), beanName);
	}

}