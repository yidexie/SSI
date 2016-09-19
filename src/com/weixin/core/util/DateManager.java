package com.weixin.core.util;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Description:根据参数提供的字符串，获取对应的Date对象
 */
public class DateManager {
	public static String getDefaultSearchTime() {
		Calendar calendar = Calendar.getInstance();
		// int beforeDay = 7;
		// calendar.set(Calendar.DAY_OF_MONTH,
		// calendar.get(Calendar.DAY_OF_MONTH) - beforeDay);

		int beforeYear = 1;
		calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) - beforeYear);
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String calendarStr = fordate.format(calendar.getTime());
		return calendarStr;
	}

	public static Date preMonthFirstDate(Date date) {
		int month = DateManager.getMonth(date);
		int year = DateManager.getYear(date);
		month--;
		if (month == 0) {
			month = 12;
			year--;
		}
		String strDate = year + "-" + month + "-" + "1";
		return getDate(strDate);
	}

	public static Date preMonthLastDate(Date date) {
		int month = DateManager.getMonth(date);
		int year = DateManager.getYear(date);
		month--;
		if (month == 0) {
			month = 12;
			year--;
		}
		int daysInMonth[] = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
		String strDate = year + "-" + month + "-" + daysInMonth[month - 1];
		return getDate(strDate);
	}

	public static Date getDate(String value, String patten) {
		Date date = null;
		if (value == null) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat(patten);
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;

	}

	public static Date getTime(String value) {
		Date date = null;
		if (value == null) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return date;
	}

	public static Date getDate(String value) {
		Date date = null;
		if (value == null) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd");
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static Date getDate_(String value) {
		Date date = null;
		if (value == null) {
			return new Date();
		}
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = fordate.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
		return date;
	}

	public static String getNowTime() {
		Date date = new Date();
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return fordate.format(date);
	}

	public static Date preDate(Date date) {
		long time = date.getTime();
		time = time - 24 * 60 * 60 * 1000;
		Date ytd = new Date(time);
		return ytd;
	}

	public static Date yesterday() {
		Date now = new Date();
		long time = now.getTime();
		time = time - 24 * 60 * 60 * 1000;
		Date ytd = new Date(time);
		return ytd;

	}

	@SuppressWarnings("deprecation")
	public static String yesterdayFromZ() {
		Date date = new Date();
		long time = date.getTime();
		time = time - 24 * 60 * 60 * 1000;
		Date ytd = new Date(time);
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_ = fordate.format(ytd);
		time_ = time_.substring(0, time_.lastIndexOf("-") + 4);
		time_ = time_ + " 00:00:00";
		return time_;
	}

	/**
	 * 
	 * 作者: wangfp 版本: May 14, 2013 3:04:43 PM v1.0 日期: May 14, 2013 参数:
	 * 
	 * @return 描述:获取7天前时间
	 */
	public static String recent7Day() {
		Date now = new Date();
		long time = now.getTime();
		time = time - 7 * 24 * 60 * 60 * 1000;

		Date ytd = new Date(time);
		SimpleDateFormat fordate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time_ = fordate.format(ytd);
		time_ = time_.substring(0, time_.lastIndexOf("-") + 4);
		time_ = time_ + " 00:00:00";
		return time_;
	}

	public static Date nextDate(Date date) {

		long time = date.getTime();
		time = time + 24 * 60 * 60 * 1000;
		Date tomorrow = new Date(time);
		return tomorrow;

	}

	public static Date tomorrow() {
		Date now = new Date();
		long time = now.getTime();
		time = time + 24 * 60 * 60 * 1000;
		Date tomorrow = new Date(time);
		return tomorrow;

	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDate(Date date, String formater) {
		SimpleDateFormat bartDateFormat = new SimpleDateFormat(formater);
		if (date == null) {
			return null;
		} else {
			String datestring = bartDateFormat.format(date);
			return datestring;
		}
	}

	public static String getYearMonth() {
		String dateStr = formatDate(new Date(), "yyyy.MM");
		return dateStr;
	}

	public static String getFormatDate(String format) {
		String dateStr = formatDate(new Date(), format);
		return dateStr;
	}

	/**
	 * 
	 */
	public static Date getDateFromMonth(String value) {
		Date date = null;
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT,
				java.util.Locale.CHINA);

		Date d = null;
		if (value == null) {
			return new Date();
		}
		try {
			d = df.parse(value);
		} catch (java.text.ParseException e) {
			try {
				d = df.parse("1900-1-1");
			} catch (java.text.ParseException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(d);
		date = new java.sql.Date(calendar.getTimeInMillis());
		return date;
	}

	public static String getYearStr(Date date) {
		String a = formatDate(date, "yyyy");
		return a;
	}

	public static int getYear(Date date) {
		String a = formatDate(date, "yyyy");
		int b = Integer.parseInt(a);
		return b;
	}

	public static String getDayStr(Date date) {
		String a = formatDate(date, "dd");
		return a;
	}

	public static int getDay(Date date) {
		String a = formatDate(date, "dd");
		int b = Integer.parseInt(a);
		return b;
	}

	public static int getHour(Date date) {
		String a = formatDate(date, "HH");
		int b = Integer.parseInt(a);
		return b;
	}

	public static String getMonthStr(Date date) {
		String a = formatDate(date, "MM");
		return a;
	}

	public static int getMonth(Date date) {
		String a = formatDate(date, "MM");
		int b = Integer.parseInt(a);
		return b;
	}

	public static Date getDateAfter(Date d, int day) {
		Calendar now = Calendar.getInstance();
		now.setTime(d);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + day);
		return now.getTime();
	}

	/**
	 * 取得当前时间
	 * 
	 * @return: yyyyMMdd HHmmss
	 */
	public static String getDateTimeForStr(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
		return df.format(date);
	}

	/**
	 * 取得当前时间
	 * 
	 * @return: yyyyMMdd HHmmss
	 */
	public static String getDateForStr(Date date) {
		if (date == null) {
			return "";
		}
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(date);
	}

	/**
	 * 取得当前时间
	 * 
	 * @return: yyyyMMdd
	 */
	public static String getDateTime(Date date) {
		DateFormat df = new SimpleDateFormat("yyyyMMdd");
		return df.format(date);
	}

	/**
	 * 
	 * 作者: wangfp 版本: May 14, 2013 2:47:09 PM v1.0 日期: May 14, 2013 参数:
	 * 
	 * @param date
	 *            参数:
	 * @return 描述:获取凌晨时间
	 * @throws ParseException
	 */
	public static Date getDateTime24(Date date) throws ParseException {
		Date now = new Date();
		String nowStr = getDateForStr(now);
		String sub_ = JavaUtil.match(nowStr, "\\s+(\\d+:\\d+:\\d+)")[1];
		String[] aa = sub_.split(":");
		Long t1 = Long.valueOf(aa[0]) * 60 * 60 * 1000 + Long.valueOf(aa[1])
				* 60 * 1000 + Long.valueOf(aa[2]) * 1000;
		t1 = now.getTime() - t1;
		return new Date((t1));
	}

	public static String getDateTime24_(Date date) {
		Date now = new Date();
		String nowStr = getDateForStr(now);
		String sub_ = nowStr.substring(0, nowStr.lastIndexOf("-") + 4);
		sub_ = sub_ + " 00:00:00";

		return sub_;
	}

	public static Integer between(Date start, Date end) {
		if (start.getTime() >= end.getTime()) {
			return 0;
		}
		int i = 1;
		start = DateManager.getDate(DateManager.formatDate(start));
		end = DateManager.getDate(DateManager.formatDate(end));
		while (start.getTime() < end.getTime()) {
			start.setTime(start.getTime() + 24 * 60 * 60 * 1000);
			i++;
		}
		return i;
	}

	public static Date getLastWeekFirst(Date date) {

		return null;
	}

	/**
	 * 
	 * 作者: wangfp 版本: Nov 28, 2013 5:14:44 PM v1.0 日期: Nov 28, 2013 参数:
	 * 
	 * @return 描述:获取当前网络时间
	 */
	public static long getHttpTime() {
		long time = 0;
		try {
			URL url = new URL("http://www.baidu.com");
			// 生成连接对象
			URLConnection uc = url.openConnection();
			// 发出连接
			uc.connect();
			time = uc.getDate();
		} catch (Exception e) {
			time = new Date().getTime();
		}
		return time;
	}

}