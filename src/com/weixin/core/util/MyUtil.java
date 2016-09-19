package com.weixin.core.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.zip.CRC32;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpConnectionManagerParams;
import org.apache.log4j.Logger;
import org.apache.ws.security.WSConstants;
import org.apache.ws.security.handler.WSHandlerConstants;
import org.apache.xpath.XPathAPI;
import org.cyberneko.html.parsers.DOMParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.mytools.util.StringUtils;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: MyUtils.java
 * @n Function: 系统专用的方法
 */

public class MyUtil {

	private static Logger logger = Logger.getLogger(MyUtil.class);

	private static String urlKey = "27&6TK^E";

	private static String classpath = Constants.class.getResource("/")
			.getPath().replaceAll("%20", " ");

	private static String webpath = classpath
			.replaceAll("WEB-INF/classes/", "");

	private static String proxyHost = "";

	private static int proxyPort = 0;

	/*
	 * 根据长度截取传入的字符串
	 */
	public static String getValidString(String str, int x) {

		if (getASCLen(str, x) > 0)
			return str.substring(0, getASCLen(str, x)) + "..";
		else
			return str;

	}

	/*
	 * 返回应该截取多少个字符
	 */
	public static int getASCLen(String ss, int toSub) {
		if (ss == null)
			return 0;

		int sub = 0;
		int len = 0;

		char[] chars = ss.toCharArray();

		for (int i = 0; i < chars.length; i++) {
			if (isChinese(chars[i])) {
				len = len + 2;
			} else
				len = len + 1;

			if (len > toSub) {
				sub = i;
				break;
			}
		}

		return sub;
	}

	public static boolean isChinese(char c) {
		return (19968 <= (int) c) && ((int) c <= 40891);
	}

	/**
	 * *****************************************************
	 * 
	 * @function：将密码用MD5加密
	 * @param：sPassword 待加密的密码字符串
	 * @return: String MD5字符串
	 *          ******************************************************
	 */
	public static String encryptPWD(String sPassword) {
		MD5 md5 = new MD5();
		String sResult = md5.getkeyBeanofStr(sPassword);
		return sResult.toLowerCase().substring(8, 24);
	}

	public static Long createId() {
		UUID uuid = UUID.randomUUID();
		return getCRC32(uuid.toString());
	}

	public static long getCRC32(String txt) {
		long crc = 0;
		try {
			CRC32 c = new CRC32();
			c.reset();
			c.update(txt.getBytes());
			crc = c.getValue();
		} catch (Exception e) {
		}
		return crc;
	}

	/*
	 * 读取配置文件参数
	 */
	public static String getConf(String path, String parameter) {
		String result = "";
		String conf = path + "\\conf\\conf.ini";

		try {
			Properties prop = new Properties();
			FileInputStream fs = new FileInputStream(conf);
			prop.load(fs);
			result = prop.getProperty(parameter);
			fs.close();
			prop = null;
		} catch (Exception e) {
			logger.error("读取conf.ini配置文件异常", e);
		}
		if (result == null)
			result = "";

		return result;
	}

	/*
	 * 读取配置文件参数(特定配置目录下)
	 */
	public static String getConf(String parameter) {
		String result = "";
		String conf = webpath + "conf/conf.ini";

		try {
			Properties prop = new Properties();
			FileInputStream fs = new FileInputStream(conf);
			prop.load(fs);
			result = prop.getProperty(parameter);
			fs.close();
			prop = null;
		} catch (Exception e) {
			logger.error("读取conf.ini配置文件异常", e);
		}
		if (result == null)
			result = "";

		return result;
	}

	/*
	 * 去除网页标签和转义特殊符号用于归档时过滤标题内容
	 */
	public static String changeSpecialSign(String str) {
		if (StringUtil.isNullOrEmpty(str))
			str = "";
		str = str.replaceAll("<[^>]*>", "");// 去标签
		str = str.replaceAll("&quot;", "\"");
		str = str.replaceAll("&amp;", "&");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&nbsp;", " ");
		return str;
	}

	/*
	 * 判断ID是否在ID序列中 传入参数一：ID序列idSeq，用逗号隔开的字符串 传入参数二：ID字符串
	 */
	public static boolean isIdIndexOf(String idSeq, String id) {
		boolean flag = false;
		String[] ids = idSeq.split(",");
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				flag = true;
				break;
			}
		}
		return flag;

	}

	public static String replaceIdInIdSeq(String idSeq, String id) {
		String[] ids = idSeq.split(",");
		String result = "";
		for (int i = 0; i < ids.length; i++) {
			if (ids[i].equals(id)) {
				continue;
			}
			result = result + ids[i] + ",";
		}
		if (result.lastIndexOf(",") > -1) {
			result = result.substring(0, result.length() - 1);
		}
		return result;
	}

	/*
	 * 替换全角引号多余空格等用于规范化输入@2012-11-12
	 */
	public static String normalize(String str) {
		if (str == null)
			str = "";
		str = str.replaceAll("　", " ");
		str = str.replaceAll("[“”＂]", "\"");
		str = str.replaceAll("｜", "|");
		str = str.replaceAll("｜", "|");
		str = str.replaceAll("\\|", " | ");

		str = str.replaceAll("\\s+", " ");
		str = str.replaceAll("-\\s+", "-");

		str = str.replaceAll("(?i)\\sand\\s", " ");
		str = str.replaceAll("(?i)\\sor\\s", " | ");
		str = str.trim();
		return str;
	}

	/*
	 * 读取文件内容
	 */
	public static String readFile(String fileName) {
		String content = "";
		try {
			File file1 = new File(fileName);
			if (file1.exists()) {
				InputStreamReader inr = new InputStreamReader(
						new FileInputStream(file1), "utf-8");
				byte[] buf = new byte[(int) (file1.length())];
				FileInputStream fis = new FileInputStream(file1);
				fis.read(buf);
				content = new String(new String(buf, "utf-8"));
				fis.close();
				inr.close();
			}
		} catch (IOException ioe) {
			content = "";
			ioe.printStackTrace();
		}
		return content;
	}

	/**
	 * 文件写入文件并制定编码方式
	 * 
	 * @param src
	 * @param charset
	 * @param fileName
	 */
	public static void writeFile(String src, String charset, String fileName) {
		try {
			URL path = JavaUtil.class.getResource("/");
			String dir = URLDecoder.decode(path.getPath(), "utf-8");
			FileOutputStream fs = new FileOutputStream(fileName);
			fs.write(src.getBytes(charset));
			fs.close();
		} catch (FileNotFoundException e) {

		} catch (IOException e) {

		}
	}

	public static void writeFileAppend(String content, String fileName) {
		try {
			// 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
			FileWriter writer = new FileWriter(fileName, true);
			writer.write(content);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * 根据正则匹配任务关键词类型@2014-5-16
	 */

	public static String getSearchType(String query) {
		String type = "";

		if (query.matches("(?i)[\\w_\\.-]+@[\\w[.-]]+\\.[\\w]+.*\\s*.*"))
			type = "email";
		else if (query
				.matches("(?i)((1([358]\\d|47)\\d{8})|((010|02[0-9]|0[3-9]\\d{2})[-]?)[1-9]\\d{6,7})\\s*.*"))
			type = "phone";
		else if (query.matches("(?i)id\\s+.*"))
			type = "id";
		else if (query.matches("(?i)(QQ|qq)\\s*\\d{5,11}.*")
				|| query.matches("(?i)\\d{5,11}.*"))
			type = "qq";
		else if (query.matches("[1-9]\\d{17}"))
			type = "IDCardNo";
		else if (query.matches("https?://[^>\\s]+\\s.*"))
			type = "url";
		else if (query
				.matches("(([^\\w]*[省|自治区])?[^\\d]*[市|区|县](.*[道|路|街|村])?.{1,4}号)l"))
			type = "address";
		else
			type = "id";

		return type;
	}

	/*
	 * 根据s_type值转成对应的中文类型@2014-5-21
	 */
	public static String getSearchTypeName(String s_type) {
		if (s_type == null)
			s_type = "关键词";
		else if (s_type.equals("email"))
			s_type = "邮箱";
		else if (s_type.equals("phone"))
			s_type = "电话";
		else if (s_type.equals("id"))
			s_type = "虚拟身份";
		else if (s_type.equals("qq"))
			s_type = "QQ";
		else if (s_type.equals("IDCardNo"))
			s_type = "身份证";
		else if (s_type.equals("url"))
			s_type = "网址";
		else if (s_type.equals("address"))
			s_type = "地址";
		else
			s_type = "关键词";

		return s_type;
	}

	/*
	 * 读取指定文件返回字节
	 */
	public static byte[] getBytesFromFile(String fileName) {
		File f = new File(fileName);
		if (f == null) {
			return null;
		}
		try {
			FileInputStream stream = new FileInputStream(f);
			ByteArrayOutputStream out = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = stream.read(b)) != -1)
				out.write(b, 0, n);
			stream.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 获取指定url的网页内容
	 */
	public static String getUrlContent(String url, String proxyIp, int proxyPort) {

		String result = "";
		HttpClient client = new HttpClient();
		if (!proxyIp.equals("") && proxyPort > 0)
			client.getHostConfiguration().setProxy(proxyIp, proxyPort);

		HttpConnectionManagerParams managerParams = client
				.getHttpConnectionManager().getParams();

		managerParams.setConnectionTimeout(15000);

		managerParams.setSoTimeout(30000);

		try {
			GetMethod get = null;
			// String time = DateManager.formatDate(new Date(),
			// "yyyy-MM-dd HH:mm:ss");
			// logger.error("sina api tran shortURL:" + time + "_url:" + url);
			get = new GetMethod(url);
			// /get.setRequestHeader("Cookie", cookie);
			client.executeMethod(get);
			String responseString = new String(get.getResponseBodyAsString());
			// .getBytes("ISO-8859-1")).trim();
			// logger.info(responseString);
			result = responseString;
			get.releaseConnection();

		} catch (Exception e) {
			if (String.valueOf(e).indexOf("Connection reset") != -1
					|| String.valueOf(e).indexOf("TimeoutException") != -1) {
				result = "ex:网络连接超时";
				logger.error("网络连接超时:" + url);
			} else if (String.valueOf(e).endsWith(
					"failed to respond with a valid HTTP response")) {
				System.out.println(e.toString());
				result = "ex:代理连接异常";
				logger.error(e);
			} else if (String.valueOf(e).startsWith(
					"java.net.UnknownHostException")) {
				System.out.println(e.toString());
				result = "ex:网络连接异常";
				logger.error(e);
			} else {
				result = "ex:未知访问页面错误";
				logger.error(e.toString());
			}

		}

		return result;
	}

	/**
	 * 
	 * @param ipStr
	 *            参数:
	 * @return 描述:字符串类型IP转换成数字类型
	 */
	public static long ipStrTranToNum(String ipStr) {
		long ip_long = 0;
		String tmpstr;
		try {
			int len, at;
			tmpstr = ipStr.trim();

			at = tmpstr.indexOf(".");
			if (at == -1)
				return 0;

			len = tmpstr.length();

			if ((Long.valueOf(tmpstr.substring(0, at))).longValue() < 0
					|| (Long.valueOf(tmpstr.substring(0, at))).longValue() > 255)
				return 0;
			ip_long = (Long.valueOf(tmpstr.substring(0, at))).longValue() * 256 * 256 * 256;
			tmpstr = tmpstr.substring(at + 1, len);

			len = tmpstr.length();
			at = tmpstr.indexOf(".");
			if (at == -1)
				return 0;

			if ((Long.valueOf(tmpstr.substring(0, at))).longValue() < 0
					|| (Long.valueOf(tmpstr.substring(0, at))).longValue() > 255)
				return 0;
			ip_long = ip_long
					+ (Long.valueOf(tmpstr.substring(0, at))).longValue() * 256
					* 256;
			tmpstr = tmpstr.substring(at + 1, len);

			len = tmpstr.length();
			at = tmpstr.indexOf(".");
			if (at == -1)
				return 0;

			if ((Long.valueOf(tmpstr.substring(0, at))).longValue() < 0
					|| (Long.valueOf(tmpstr.substring(0, at))).longValue() > 255)
				return 0;
			ip_long = ip_long
					+ (Long.valueOf(tmpstr.substring(0, at))).longValue() * 256;
			ip_long = ip_long
					+ (Long.valueOf(tmpstr.substring(at + 1))).longValue();

		} catch (Exception e) {
			ip_long = 0;
			logger.error("ipString类型转换成数字异常", e);
		}
		return ip_long;
	}

	/**
	 * 
	 * 
	 * @param ipNum
	 *            参数:
	 * @return 描述:数字类型的Ip转换成String类型
	 */
	public static String ipNumTranToipStr(long ipNum) {
		String ip_str = "";
		try {
			long tmp_int = 0;
			double tmp_float = 0;
			long zys = ipNum;

			tmp_float = zys / (256 * 256 * 256);
			tmp_int = zys / (256 * 256 * 256);
			if (tmp_int > tmp_float) {
				tmp_int = tmp_int - 1;
			}
			zys = (zys - tmp_int * 256 * 256 * 256);
			ip_str = "" + tmp_int;

			tmp_float = zys / (256 * 256);
			tmp_int = zys / (256 * 256);
			if (tmp_int > tmp_float) {
				tmp_int = tmp_int - 1;
			}
			zys = (zys - tmp_int * 256 * 256);
			ip_str = ip_str + "." + tmp_int;

			tmp_float = zys / (256);
			tmp_int = zys / (256);
			if (tmp_int > tmp_float) {
				tmp_int = tmp_int - 1;
			}
			zys = (zys - tmp_int * 256);
			ip_str = ip_str + "." + tmp_int + "." + zys;
		} catch (Exception e) {
			logger.error("数字类型IP转换成String类型异常", e);
		}
		return ip_str;
	}

	/*
	 * 通过访问ip138获取系统的IP
	 */
	public static String getLocalIp() {
		String ip = "";

		// String url = "http://iframe.ip138.com/ic.asp";
		// String url = "http://20140507.ip138.com/ic.asp";
		// 2014-12-1改版地址
		// String url = "http://1111.ip138.com/ic.asp";
		String url = "http://ip138.com/ips138.asp";

		// 读取代理设置
		proxyHost = getConf("proxyHost");
		String _proxyPort = getConf("proxyPort");
		try {
			proxyPort = Integer.valueOf(_proxyPort);
			if (proxyPort < 0) {
				proxyPort = 0;
			}
		} catch (Exception e) {
			proxyPort = 0;
		}

		String content = getUrlContent(url, proxyHost, proxyPort);
		if (!StringUtil.isNullOrEmpty(content) && !content.startsWith("ex:")) {
			try {
				content = new String(content.getBytes("ISO-8859-1")).trim();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int m = content.indexOf("您的IP地址是：[");
		int n = content.indexOf("] 来自");
		if (m > 0 && n > m) {
			ip = content.substring(m + "您的IP地址是：[".length(), n);
		}

		return ip;
	}

	public static String getEncryptString(String encryptStr) {
		String result = "";
		try {
			result = Des.encryptDES(encryptStr, urlKey);
		} catch (Exception e) {
			logger.error("加密异常", e);
		}
		return result;
	}

	/**
	 * 
	 * 
	 * @param inputFile
	 *            参数:
	 * @param outputFile
	 *            参数:
	 * @return 描述:将文件存储到硬盘
	 */
	public static boolean uploadFileToDisk(File inputFile, File outputFile) {
		boolean flag = false;
		try {
			FileInputStream inputStream = new FileInputStream(inputFile);
			FileOutputStream outputStream = new FileOutputStream(outputFile);
			byte[] b = new byte[1024 * 5];
			int len = 0;
			while ((len = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, len);
			}
			outputStream.flush();
			outputStream.close();
			inputStream.close();
			flag = true;
		} catch (Exception e) {
			logger.error("存储文件异常", e);
		}
		return flag;
	}

	/**
	 * 根据附件名生成对应附件的存放路径按年月分目录保存
	 */
	public static String getAttachmentPath(String fileName) {
		String filePath = "";
		// 读取配置的图片路径如果未配置则放到WEB下pic目录
		String attachmentPath = getConf("attachmentPath");
		if (StringUtil.isNullOrEmpty(attachmentPath))
			attachmentPath = webpath + File.separator + "attachment";
		String subPath = "";
		// 取前2位年份和下2位月份作2级子目录
		if (fileName.length() > 4) {
			subPath = fileName.substring(0, 2) + File.separator
					+ fileName.substring(2, 4);
		}
		filePath = attachmentPath + File.separator + subPath;
		return filePath;
	}

	/*
	 * 
	 * 
	 * /** 将String 解析成Documnet
	 * 
	 * @param tempbodyStr @return @throws Exception
	 */
	public static Document getDocument(String tempbodyStr) throws Exception {
		String bodyStr = tempbodyStr.replace("　", " ")
				.replaceAll("&nbsp;", " ");
		// 生成html parser
		DOMParser parser = new DOMParser();
		// 设置网页的默认编码
		parser.setFeature("http://xml.org/sax/features/namespaces", false);
		parser.setProperty(
				"http://cyberneko.org/html/properties/default-encoding",
				"UTF-8");
		java.io.ByteArrayInputStream bis = new java.io.ByteArrayInputStream(
				bodyStr.getBytes("UTF-8"));
		InputStreamReader fileIn = new InputStreamReader(bis, "UTF-8");
		BufferedReader in = new BufferedReader(fileIn);
		parser.parse(new InputSource(in));
		Document doc = parser.getDocument();
		return doc;
	}

	/**
	 * 获取结点的Text值
	 * 
	 * @param node
	 * @return
	 */
	public static String getTextContent(Node node) {
		if (node == null)
			return null;
		String textContent = node.getTextContent();
		if (textContent == null)
			return textContent;

		return textContent.trim();
	}

	/**
	 * 获取结点的属性值
	 * 
	 * @param node
	 * @param attrName
	 * @return
	 */
	public static String getNodeValue(Node node, String attrName) {
		if (node == null || node.getAttributes() == null)
			return null;
		Node attrNode = node.getAttributes().getNamedItem(attrName);
		if (attrNode == null || attrNode.getNodeValue() == null)
			return null;
		return attrNode.getNodeValue().trim();

	}

	/**
	 * 根据传入的IP或域名返回IP所在地@2014-9-2
	 */

	public static String ipSearch(String ip) {
		String result = "";
		String url = "http://ip138.com/ips138.asp?ip=" + ip.trim()
				+ "&action=2";
		try {
			// 读取代理设置
			proxyHost = getConf("proxyHost");
			String _proxyPort = getConf("proxyPort");
			try {
				proxyPort = Integer.valueOf(_proxyPort);
				if (proxyPort < 0) {
					proxyPort = 0;
				}
			} catch (Exception e) {
				proxyPort = 0;
			}
			String content = MyUtil.getUrlContent(url, proxyHost, proxyPort);
			content = new String(content.getBytes("iso-8859-1"), "GBK");
			Document doc = MyUtil.getDocument(content);
			NodeList liNodes = XPathAPI.selectNodeList(doc,
					".//UL[@class='ul1']/LI");
			if (liNodes != null) {
				for (int i = 0; i < liNodes.getLength(); i++) {
					Node liNode = liNodes.item(i);
					result += "<LI>" + MyUtil.getTextContent(liNode) + "</LI>";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据传入的手机号码段查询所在地@2014-9-2
	 */

	public static String phoneSearch(String phone) {
		String result = "";
		String url = "http://www.ip138.com:8080/search.asp?action=mobile&mobile="
				+ phone.trim();
		try {
			// 读取代理设置
			proxyHost = getConf("proxyHost");
			String _proxyPort = getConf("proxyPort");
			try {
				proxyPort = Integer.valueOf(_proxyPort);
				if (proxyPort < 0) {
					proxyPort = 0;
				}
			} catch (Exception e) {
				proxyPort = 0;
			}
			String content = MyUtil.getUrlContent(url, proxyHost, proxyPort);
			content = new String(content.getBytes("iso-8859-1"), "GBK");
			Document doc = MyUtil.getDocument(content);
			NodeList trNodes = XPathAPI.selectNodeList(doc,
					".//TR[@class='tdc']");
			if (trNodes != null && trNodes.getLength() == 6) {
				for (int i = 1; i < trNodes.getLength(); i++) {
					Node trNode = trNodes.item(i);
					Node tdNode1 = XPathAPI.selectSingleNode(trNode, "TD[1]");
					result += "<tr><td width=120 align=center class=public_color12>"
							+ MyUtil.getTextContent(tdNode1) + "</td>";
					Node tdNode2 = XPathAPI.selectSingleNode(trNode, "TD[2]");
					result += "<td align=center><b class=public_color>"
							+ MyUtil.getTextContent(tdNode2).replaceAll(
									"测吉凶\\(新\\)", "").replaceAll("更详细的..", "")
							+ "</b></td></tr>";
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 时间格式转换
	 */
	public static String formatTime(String time) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		try {
			Date date = sdf.parse(time);
			time = sdf2.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return time;
	}
}
