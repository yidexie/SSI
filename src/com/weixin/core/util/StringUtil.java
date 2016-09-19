package com.weixin.core.util;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Copyright(C):
 * 
 * @n
 * @n File: StringUtils.java
 * @n Function: 字符串处理类
 */

public class StringUtil {

	/**
	 * <br>
	 * ***************************************************** <br>
	 * 功 能：将空值转为0 <br>
	 * 入口参数：param s1 待加工的字符串 <br>
	 * 返 回：如果传入参数是null返回空串"",否则原字串去空格后返回 <br>
	 * ******************************************************
	 */
	public static String nullToZero(String s1) {
		if (s1 == null)
			return "0";
		s1 = s1.trim();
		if (s1.length() == 0)
			return "0";
		return s1;
	}

	/**
	 * <br>
	 * ***************************************************** <br>
	 * 功 能：获取异常堆栈信息 <br>
	 * 入口参数：param e 异常对象 <br>
	 * 返 回：return length 打印长度，0为不限制长度 <br>
	 * ******************************************************
	 */
	public static String getErrorStack(Exception e, int length) {
		String error = null;
		if (e != null) {
			try {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				PrintStream ps = new PrintStream(baos);
				e.printStackTrace(ps);
				error = baos.toString();
				if (length > 0) {
					if (length > error.length()) {
						length = error.length();
					}
					error = error.substring(0, length);
				}
				baos.close();
				ps.close();
			} catch (Exception e1) {
				error = e.toString();
			}
		}
		return error;
	}

	/**
	 * 判断字符是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNullOrEmpty(String s) {
		if (s == null || s.trim().length() == 0)
			return true;
		return false;
	}

	/*
	 * 根据身份证号取出生日
	 */
	public static String getBirthdayByIdCard(String idCard) {
		String birthday = "";
		if (15 == idCard.length()) { // 15位身份证号码
			birthday = String.valueOf(idCard.charAt(6))
					+ String.valueOf(idCard.charAt(7));

			if (Integer.parseInt(birthday) < 10) {
				birthday = "20" + birthday;
			} else {
				birthday = "19" + birthday;
			}
			birthday = birthday + '-' + idCard.charAt(8) + idCard.charAt(9);
		}

		if (18 == idCard.length()) { // 18位身份证号码
			birthday = String.valueOf(idCard.charAt(6)) + idCard.charAt(7)
					+ idCard.charAt(8) + idCard.charAt(9) + "-"
					+ idCard.charAt(10) + idCard.charAt(11) + "-"
					+ idCard.charAt(12) + idCard.charAt(13);
		}
		return birthday;
	}

	/*
	 * 根据身份证号取出性别
	 */
	public static String getSexByIdCard(String idCard) {
		String sex = "";
		if (15 == idCard.length()) { // 15位身份证号码
			if ((idCard.charAt(14) / 2) * 2 != idCard.charAt(14))
				sex = "1";
			else
				sex = "2";
		}

		if (18 == idCard.length()) { // 18位身份证号码

			if ((idCard.charAt(16) / 2) * 2 != idCard.charAt(16))
				sex = "1";
			else
				sex = "2";
		}
		return sex;
	}

	/*
	 * 根据长度截取传入的字符串
	 */
	public static String getValidString(String str, int x) {
		if (getASCLen(str, x) > 0) {
			return str.substring(0, getASCLen(str, x)) + "..";

		} else
			return str;

	}

	/*
	 * 根据长度截取没html标签的字符串
	 */
	public static String getNoHtmlValidString(String str, int x) {
		str = str.replaceAll("<[^>]*>", "");
		if (getASCLen(str, x) > 0) {
			return str.substring(0, getASCLen(str, x)) + "..";

		} else
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
}
