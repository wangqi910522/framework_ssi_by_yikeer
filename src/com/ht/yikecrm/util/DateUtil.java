package com.ht.yikecrm.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 日期操作功能类.
 * <p/>
 * 最后更新：2008-5-31
 * 
 * @author mcl
 */
public class DateUtil {

	/**
	 * 判断字符串是否是有效的日期， 格式"yyyy-MM-dd,yyyy-MM-d,yyyy-M-dd,yyyy-M-d
	 * "yyyy/MM/dd,yyyy/MM/d,yyyy/M/dd,yyyy/M/d" "yyyyMMdd"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidDate(String date) {
		if ((date == null) || (date.length() < 8)) {
			return false;
		}
		try {
			boolean result = false;
			SimpleDateFormat formatter;
			char dateSpace = date.charAt(4);
			String format[];
			if ((dateSpace == '-') || (dateSpace == '/')) {
				format = new String[4];
				String strDateSpace = Character.toString(dateSpace);
				format[0] = "yyyy" + strDateSpace + "MM" + strDateSpace + "dd";
				format[1] = "yyyy" + strDateSpace + "MM" + strDateSpace + "d";
				format[2] = "yyyy" + strDateSpace + "M" + strDateSpace + "dd";
				format[3] = "yyyy" + strDateSpace + "M" + strDateSpace + "d";
			} else {
				format = new String[1];
				format[0] = "yyyyMMdd";
			}

			for (int i = 0; i < format.length; i++) {
				String aFormat = format[i];
				formatter = new SimpleDateFormat(aFormat);
				formatter.setLenient(false);
				String tmp = formatter.format(formatter.parse(date));
				if (date.equals(tmp)) {
					result = true;
					break;
				}
			}
			return result;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 判断字符串是否是有效的日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 是则返回true，否则返回false
	 */
	public static boolean isValidTime(String date) {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat(
					"yyyy-MM-dd HH:mm:ss");
			formatter.setLenient(false);
			formatter.parse(date);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期字符串,格式为"yyyy-MM-dd"
	 * @return 返回格式化的日期
	 */
	public static Date strToDate2(String date) throws Exception {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date2 = null;
		formatter.setLenient(false);
		try {
			date2 = formatter.parse(date);
		} catch (ParseException e) {
			try {
				formatter = new SimpleDateFormat("yyyy/MM/dd");
				date2 = formatter.parse(date);
			} catch (Exception e2) {
				throw e2;
			}
		}
		return date2;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date strtodate = formatter.parse(strDate);
			return strtodate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy.MM.dd
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDate4(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
//		ParsePosition pos = new ParsePosition(0);
		Date strtodate = null ;
		try {
			strtodate = formatter.parse(strDate);
			return strtodate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}

	/**
	 * 将短时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDatetime(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return strtodate;
	}
	
	public static Date strToDate3(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		if (strtodate == null) {
			formatter = new SimpleDateFormat("yyyy/MM/dd");
			pos = new ParsePosition(0);
			strtodate = formatter.parse(strDate, pos);
		}
		return strtodate;
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd"
	 * 
	 * @param date
	 *            日期字符串,格式为"yyyyMMdd"
	 * @return 返回格式化的日期
	 */
	public static Date strToFormateDate(String date) throws ParseException {
		String str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6, 8);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		return formatter.parse(str);
	}

	public static Date strToFormateDate2(String date) throws ParseException {
		String str = date.substring(0, 4) + "-" + date.substring(4, 6) + "-"
				+ date.substring(6, 8) + " " + date.substring(8, 10) + ":"
				+ date.substring(10, 12) + ":" + date.substring(12, 14);
		return strToTime(str);
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期字符串
	 * @return 返回格式化的日期
	 */
	public static Date strToTime(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd HH"
	 */
	public static Date strToTimeH(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH");
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	/**
	 * 转换字符串为日期，格式"yyyy-MM-dd HH:mm"
	 */
	public static Date strToTimeM(String date) throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		formatter.setLenient(false);
		return formatter.parse(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd HH:mm"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStrTime(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，YYYY年MM月dd日HH:mm
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStrTime2(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，YYYY年MM月dd日
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStrTime3(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd "
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStr(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"MM-dd "
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStr2(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式pattern
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String dateToStr(Date date, String pattern) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyyMMdd"
	 * 
	 * @param date
	 * @return
	 */

	public static String dateToStrNoBlank(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyyMMdd"
	 * 
	 * @param date
	 * @return
	 */

	public static String dateToSimpleStr(Date date) {
		String str = "";
		String simpleStr = "";
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		str = formatter.format(date);
		simpleStr = str.substring(2, 4) + str.substring(4, 6)
				+ str.substring(6, 8);
		return simpleStr;
	}

	/**
	 * 转换日期为字符串，格式"yyyy.MM.dd"
	 * 
	 * @param date
	 * @return
	 */

	public static String dateToSimpleStr3(Date date) {
		String str = "";
		// String simpleStr = "";
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		str = formatter.format(date);
		// simpleStr = str.substring(2, 4) + str.substring(4, 6)
		// + str.substring(6, 8);
		return str;
	}

	/**
	 * 转换日期为字符串，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String timeToStr(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(date);
	}

	/**
	 * 转换日期为字符串，格式"yyyyMMddHHmmss"
	 * 
	 * @param date
	 *            日期
	 * @return 返回格式化的日期字符串
	 */
	public static String timeToStr2(Date date) {
		if (date == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
		return formatter.format(date);
	}

	/**
	 * 取得现在的日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return 返回格式化的日期字符串
	 */
	public static String getNow() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得现在的日期，格式"yyyy-MM-dd HH:mm:ss"
	 * 
	 * @return 返回格式化的日期字符串
	 */
	public static String getNow(String fromat) {
		SimpleDateFormat formatter = new SimpleDateFormat(fromat);
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得现在的日期，格式"yyyy-MM-dd"
	 * 
	 * @return 返回格式化的日期字符串
	 */
	public static String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得现在的时间，格式"HH:mm:ss"
	 * 
	 * @return 返回格式化的时间字符串
	 */
	public static String getTime() {
		SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
		Date Now = new Date();
		return formatter.format(Now);
	}

	/**
	 * 取得年份，格式"yyyy"
	 * 
	 * @return 返回当前年份
	 */
	public static int getYear() {
		Date Now = new Date();
		return getYear(Now);
	}

	/**
	 * 取得当年年初日期，格式"yyyy-mm-dd"
	 * 
	 * @return 返回当年年初日期
	 */
	public static String getBeginDate() {
		String date = String.valueOf(getYear()) + "-" + "01" + "-" + "01";
		return date;
	}

	/**
	 * 取得日期的年份，格式"yyyy"
	 * 
	 * @param date
	 *            日期
	 * @return 日期的年份
	 */
	public static int getYear(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得月份
	 * 
	 * @return 返回当前月份
	 */
	public static int getMonth() {
		Date Now = new Date();
		return getMonth(Now);
	}
	
	/**
	 * 获得指定日期月份的第一天
	 * @param date  
	 * @return
	 */
	public static Date getMonthofFirstDay(Date date){

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(dateToStr(date)));
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获得指定日期最近的星期一
	 * @param date
	 * @return
	 */
	public static Date getMondayofDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(dateToStr(date)));
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获得指定日期最近的星期天
	 * @param date
	 * @return
	 */
	public static Date getSundayofDate(Date date){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(dateToStr(date)));
		calendar.set(Calendar.DAY_OF_WEEK,0);
		calendar.add(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 取得日期的月份
	 * 
	 * @param date
	 *            日期
	 * @return 日期的月份
	 */
	public static int getMonth(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("M");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得今天的日期数
	 * 
	 * @return 返回今天的日期数
	 */
	public static int getDay() {
		Date Now = new Date();
		return getDay(Now);
	}

	/**
	 * 取得日期的天数
	 * 
	 * @param date
	 *            日期
	 * @return 日期的天数
	 */
	public static int getDay(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("d");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得日期的小时
	 * 
	 * @param date
	 *            日期
	 * @return 日期的天数
	 */
	public static int getHour(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("H");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 取得日期的分钟
	 * 
	 * @param date
	 *            日期
	 * @return 日期的天数
	 */
	public static int getMinute(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("m");
		return Integer.parseInt(formatter.format(date));
	}

	/**
	 * 获得与某日期相隔几分的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的分数
	 * @return 返回的日期
	 */
	public static Date addMinute(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, addCount);
		return calendar.getTime();
	}

	/**
	 * 获得与某日期相隔几时的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的时数
	 * @return 返回的日期
	 */
	public static Date addHour(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, addCount);
		return calendar.getTime();
	}

	/**
	 * 获得与某日期相隔几天的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的天数
	 * @return 返回的日期
	 */
	public static Date addDay(Date date, int addCount) {
		// Calendar cal = new GregorianCalendar();
		// 最好用Calendar.getInstance();
		// 因为有的地方，不是使用GregorianCalendar的。
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(strToDate(dateToStr(date)));
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addCount);
		return calendar.getTime();
	}

	/**
	 * 获得与某日期相隔几月的日期
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的月数
	 * @return 返回的日期
	 */
	public static Date addMonth(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		// calendar.setTime(strToDate(dateToStr(date)));
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, addCount);
		return calendar.getTime();
	}

	/**
	 * 将传入的日期转换为中文格式
	 * 
	 * @param enDate
	 *            指定的日期，格式为2007-01-02
	 * @return 返回的符合中文格式的日期
	 */
	public static String enToCnDate(Date enDate) {
		String datestr = dateToStr(enDate);
		String s = datestr.substring(0, 4);
		s = s + "年";
		String s2 = datestr.substring(5, 7);
		s2 = s2 + "月";
		String s3 = datestr.substring(8, datestr.length());
		s2 = s2 + s3 + "日";
		String sFinal;
		String[] a = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };
		String[] b = { "O", "一", "二", "三", "四", "五", "六", "七", "八", "九" };
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < s.length() - 1; j++) {
				if (s.substring(j, j + 1).equals(a[i]))
					s = s.substring(0, j) + b[i]
							+ s.substring(j + 1, s.length());
			}
		}
		String[] c = { "01", "02", "03", "04", "05", "06", "07", "08", "09",
				"10", "11", "12", "13", "14", "15", "16", "17", "18", "19",
				"20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
				"30", "31" };

		String[] d = { "一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一",
				"十二", "十三", "十四", "十五", "十六", "十七", "十八", "十九", "二十", "二十一",
				"二十二", "二十三", "二十四", "二十五", "二十六", "二十七", "二十八", "二十九", "三十",
				"三十一" };

		for (int i = 0; i < c.length; i++) {
			for (int j = 0; j < s2.length() - 2; j++) {
				if (s2.substring(j, j + 2).equals(c[i]))
					s2 = s2.substring(0, j) + d[i]
							+ s2.substring(j + 2, s2.length());
			}
		}
		sFinal = s + s2;
		return sFinal;
	}

	/**
	 * 得到二个日期间的间隔天数
	 */
	public static String getTwoDay(String sj1, String sj2) {
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		long day = 0;
		try {
			java.util.Date date = myFormatter.parse(sj1);
			java.util.Date mydate = myFormatter.parse(sj2);
			day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000L);
		} catch (Exception e) {
			return "";
		}
		return day + "";
	}

	/**
	 * 根据一个日期，返回是星期几的字符串
	 * 
	 * @param sdate
	 * @return
	 */
	public static String getWeek(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		return new SimpleDateFormat("EEEE").format(c.getTime());
	}

	/**
	 * 两个时间之间的天数
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static long getDays(String date1, String date2) {
		if (date1 == null || date1.equals(""))
			return 0;
		if (date2 == null || date2.equals(""))
			return 0;
		// 转换为标准时间
		SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date date = null;
		java.util.Date mydate = null;
		try {
			date = myFormatter.parse(date1);
			mydate = myFormatter.parse(date2);
		} catch (Exception e) {

		}
		long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000L);
		return day;
	}

	/**
	 * 当月第一天
	 */
	public static String getFirstDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 给定日期当月第一天
	 */
	public static Date getFirstDayOfMonth(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		return lastDate.getTime();
	}

	/**
	 * 当年第一天
	 */
	public static String getFirstDayOfYear() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 上月第一天
	 */
	public static String getPreviousMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为上月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 上N个月第一天 -1 表示上一个月 -2 上上个月 以此类推
	 */
	public static String getPreviousP2MonthFirst(int n) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, n);// 减一个月，变为上月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 去年第一天
	 */
	public static String getPreviousYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.YEAR, -1);// 减一年，变为去年的1月的号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 下月第一天
	 */
	public static String getNextMonthFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 给定日期下月第一天
	 */
	public static Date getNextMonthFirst(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.getTime();
		return lastDate.getTime();
	}

	/**
	 * 明年第一天
	 */
	public static String getNextYearFirst() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.YEAR, 1);// 加一年
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 指定月份第一天
	 * 
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String getFirstDayOfMonth(String strDate) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(calendar.getTime());
		return str;
	}

	/**
	 * 指定月份第一天
	 * 
	 * @param strDate
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getFirstDayOfMonths(String strDate) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		str = sdf.format(calendar.getTime());
		return str;
	}

	/**
	 * 当月最后一天
	 */
	public static String getLastDayOfMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 当年最后一天
	 */
	public static String getLastDayOfYear() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当月的1号
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当年最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 上月最后一天
	 */
	public static String getLastDayOfPreviousMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为上月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 去年最后一天
	 */
	public static String getLastDayOfPreviousYear() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为去年最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 下月最后一天
	 */
	public static String getLastDayOfNextMonth() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.MONTH, 2);// 加二个月，变为下下月的1号
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为下月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 明年最后一天
	 */
	public static String getLastDayOfNextYear() {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当前月的1号
		lastDate.add(Calendar.YEAR, 2);// 加二个年
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为下月最后一天
		str = sdf.format(lastDate.getTime());
		return str;
	}

	/**
	 * 指定月最后一天
	 * 
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static String getLastDayOfMonth(String strDate) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		str = sdf.format(calendar.getTime());
		return str;
	}

	/**
	 * 获得本周星期日的日期
	 */
	public static String getCurrentWeekday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 6);
		Date monday = currentDate.getTime();

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获取当天时间
	 */
	public static String getNowTime(String dateformat) {
		Date now = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(dateformat);// 可以方便地修改日期格式
		String hehe = dateFormat.format(now);
		return hehe;
	}

	/**
	 * 获得当前日期与本周日相差的天数
	 */
	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1; // 因为按中国礼拜一作为第一天所以这里减1
		if (dayOfWeek == 1) {
			return 0;
		}else if (dayOfWeek == 0) {
			return -6;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获得本周一的日期
	 */
	public static String getMondayOFWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得相应周的周六的日期
	 */
	public static String getSaturday() {
		int weeks = 0;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得上周星期日的日期
	 */
	public static String getPreviousWeekSunday() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得上上周星期日的日期
	 */
	public static String getPreviousP2WeekSunday() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks-7);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得星期日的日期
	 */
	public static String getWeekSunday() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return sdf.format(c.getTime());
	}

	/**
	 * 获得上周星期一的日期
	 */
	public static String getPreviousWeekday() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得上上周星期一的日期
	 */
	public static String getPreviousP2Weekday() {
		int weeks = 0;
		weeks--;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 
	 * 获得下周星期一的日期
	 */
	public static String getNextMonday() {
		int weeks = 0;
		weeks++;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 获得下周星期日的日期
	 */
	public static String getNextSunday() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date monday = currentDate.getTime();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String preMonday = df.format(monday);
		return preMonday;
	}

	/**
	 * 
	 * @return
	 */
	public static int getMonthPlus() {
		int MaxDate;
		Calendar cd = Calendar.getInstance();
		int monthOfNumber = cd.get(Calendar.DAY_OF_MONTH);
		cd.set(Calendar.DATE, 1);// 把日期设置为当月第一天
		cd.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
		MaxDate = cd.get(Calendar.DATE);
		if (monthOfNumber == 1) {
			return -MaxDate;
		} else {
			return 1 - monthOfNumber;
		}
	}

	private static int getWeekNum(String sdate) {
		// 再转换为时间
		Date date = strToDate(sdate);
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		// int hour=c.get(Calendar.DAY_OF_WEEK);
		// hour中存的就是星期几了，其范围 1~7
		// 1=星期日 7=星期六，其他类推
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 获取指定日期的当月的第一天
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date getFirstOfMonth(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的当月的最后一天
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date getLastOfMonth(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的上月的第一天
	 * @param strDate
	 * @return
	 */
	public static Date getFristOfLastMonth(String strDate){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		calendar.add(Calendar.MONTH, -1);// 减一个月，变为下月的1号
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的上月的最后一天
	 * @param strDate
	 * @return
	 */
	public static Date getLastOfLastMonth(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		calendar.add(Calendar.DATE, -1);// 减去一天，变为上一月月最后一天
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}

	/**
	 * 获取指定日期的当月的最后一天(00.00.00)时分秒为0
	 * @param strDate
	 * @return
	 */
	public static Date getLastOfMonth2(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(strToDate(strDate));
		calendar.set(Calendar.DATE, 1);// 设为当前月的1号
		calendar.add(Calendar.MONTH, 1);// 加一个月，变为下月的1号
		calendar.add(Calendar.DATE, -1);// 减去一天，变为当月最后一天
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获取指定日期的当周的第一天 
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date getFirstOFWeekByDate(String sdate) {
		int mondayPlus = getWeekNum(sdate);
		GregorianCalendar currentDate = new GregorianCalendar();
		Date date = strToDate(sdate);
		currentDate.setTime(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date monday = currentDate.getTime();

		return monday;
	}

	/**
	 * 获取指定日期的当周的最后一天 
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date getLastOFWeekByDate(String sdate) {
		int mondayPlus = getWeekNum(sdate);
		GregorianCalendar currentDate = new GregorianCalendar();
		Date date = strToDate(sdate);
		currentDate.setTime(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus
				+ GregorianCalendar.DAY_OF_WEEK - 1);
		currentDate.set(GregorianCalendar.HOUR, 23);
		currentDate.set(GregorianCalendar.MINUTE, 59);
		currentDate.set(GregorianCalendar.SECOND, 59);
		Date sunday = currentDate.getTime();

		return sunday;
	}
	/**
	 * 获取指定日期的当周的最后一天(不带时分秒的)
	 * 
	 * @param sdate
	 * @return
	 */
	public static Date getLastOFWeekByDate2(String sdate) {
		int mondayPlus = getWeekNum(sdate);
		GregorianCalendar currentDate = new GregorianCalendar();
		Date date = strToDate(sdate);
		currentDate.setTime(date);
		currentDate.add(GregorianCalendar.DATE, mondayPlus
				+ GregorianCalendar.DAY_OF_WEEK - 1);
		Date sunday = currentDate.getTime();
		return sunday;
	}

	/**
	 * 获得给定时间毫秒数之后的时间（负数代表给定时间之前）
	 * 
	 * @param date
	 * @param time
	 * @return
	 */
	public static Date getIntervalDate(Date date, Long time) {
		Long oldTime = date.getTime();
		Long newTime = oldTime + time;
		return new Date(newTime);
	}

	/**
	 * 获得给定日期的星期数（）
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekOfDate(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int week = c.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0) {
			week = 7;
		}
		return week;
	}

	/**
	 * 前n个月天数
	 */
	public static Long gettwoMonth(int mouth) {
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar lastDate = Calendar.getInstance();
		lastDate.set(Calendar.DATE, getDay(new Date()));// 设为和今天一样的号
		lastDate.add(Calendar.MONTH, mouth);// 向前二个月
		str = sdf.format(lastDate.getTime());
		long a = getDays(str, sdf.format(new Date()));
		return a;
	}

	/**
	 * 去掉时分秒比较时间是否相等
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean dateEquals(Date d1, Date d2) {
		Date newd1 = strToDate(dateToStr(d1));
		Date newd2 = strToDate(dateToStr(d2));
		if (newd1.getTime() == newd2.getTime()) {
			return true;
		}
		return false;
	}

	/**
	 * 获得给定日期的 年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getFirstDayByYear(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.MONTH, 0);// 设为当年的的1月
		cl.set(Calendar.DATE, 1);// 设为当前月的1号
		return cl.getTime();
	}

	/**
	 * 获得给定日期的 年的第一天
	 * 
	 * @param date
	 * @return
	 */
	public static Date getLastDayByYear(Date date) {
		Calendar lastDate = Calendar.getInstance();
		lastDate.setTime(date);
		lastDate.set(Calendar.MONTH, 0);// 设为当年的的1月
		lastDate.set(Calendar.DATE, 1);// 设为当月的1号
		lastDate.add(Calendar.YEAR, 1);// 加一个年
		lastDate.add(Calendar.DATE, -1);// 减去一天，变为当年最后一天
		return lastDate.getTime();
	}

	/**
	 * 根据年月 或获取最后一天天数
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static Long getlastDayByYearMonth(Long year, Long month) {
		if (month == 4 || month == 6 || month == 9 || month == 11) {
			return 30L;
		} else if (month == 2
				&& !((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
			return 28L;
		} else if (month == 2
				&& ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {
			return 29L;
		} else {
			return 31L;
		}
	}

	/**
	 * 根据给定日期返回该月所有周的日期信息 给2.15 renturn 1.30-2.5 2.6-12 2.13-19 2.20-2.26
	 * 2.27-3.5(例如这样的，这标示2.152月里周信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getWeekInMonth(Date date){
		//获得当月第一周的周一时间
		Date first = DateUtil.addDay(getFirstOFWeekByDate(DateUtil.dateToStr(DateUtil.addDay(DateUtil.getFirstOfMonth(DateUtil.dateToStr(date)),-1))),1);
		//获得本月的最后一天
		Date monthend = DateUtil.getLastOfMonth2(DateUtil.dateToStr(date));
		List<String> weekList = new ArrayList<String>();
		int num = 0;
		Date yi = first;//周一
		Date tian = DateUtil.addDay(first,num*7);//周日
		String time="";
		while (tian.getTime()<monthend.getTime()) {
			if(num==0){
				yi = first;
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
			} else {
				yi = DateUtil.addDay(first, num * 7);
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
			}
			time = DateUtil.dateToSimpleStr3(yi) + "-"
					+ DateUtil.dateToSimpleStr3(tian);
			num++;
			weekList.add(time);
		}
		return weekList;
	}

	
	/**
	 * 根据给定日期返回该月该日期之前所有周的日期信息 给2.15 return 1.30-2.5
	 * 2.6-12(例如这样的，这标示2月2.15以前的周信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getWeekInMonthUpDate(Date date) {
		// 获得当月第一周的周一时间
		Date first = DateUtil.addDay(
				DateUtil.getFirstOFWeekByDate(DateUtil.dateToStr(DateUtil
						.getFirstOfMonth(DateUtil.dateToStr(date)))), 1);
		// 获得给定时间周一的时间
		Date sta = DateUtil.addDay(DateUtil.getFirstOFWeekByDate(DateUtil
				.dateToStr(date)), 1);
		List<String> weekList = new ArrayList<String>();
		int num = 0;
		Date yi = first;//周一
		Date tian = DateUtil.addDay(first,num*7);//周日
		String time="";
		while (tian.getTime()<date.getTime()) {
			if(num==0){
				yi = first;
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
			} else {
				yi = DateUtil.addDay(first, num * 7);
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
				if (yi.getTime() > sta.getTime()) {
					break;
				}
			}
			time = DateUtil.dateToSimpleStr3(yi) + "-"
					+ DateUtil.dateToSimpleStr3(tian);
			num++;
			weekList.add(time);
		}
		return weekList;
	}

	
	
	/**
	 * 根据给定日期返回该月该日期之前所有周的日期信息 给2.15 return 2.20-2.26
	 * 2.27-3.5(例如这样的，这标示2.15以后2月里周信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getWeekInMonthDownDate(Date date){
		//获得给定时间周一的时间
		Date first = DateUtil.addDay(getFirstOFWeekByDate(DateUtil.dateToStr(DateUtil.addDay(date,-1))),1);
		//获得本月的最后一天
		Date monthend = DateUtil.getLastOfMonth2(DateUtil.dateToStr(date));
		List<String> weekList = new ArrayList<String>();
		int num = 0;
		Date yi = first;//周一
		Date tian = DateUtil.addDay(first,num*7);//周日
		String time="";
		while (tian.getTime()<monthend.getTime()) {
			if(num==0){
				yi = first;
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
			} else {
				yi = DateUtil.addDay(first, num * 7);
				tian = DateUtil.addDay(first, (num + 1) * 7 - 1);
			}
			time = DateUtil.dateToSimpleStr3(yi) + "-"
					+ DateUtil.dateToSimpleStr3(tian);
			num++;
			weekList.add(time);
		}
		return weekList;
	}
	/**
	 * 获得当前系统时间的本周一日期和本周天日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Date> getSysMonAndSun() {
		List<Date> list = new ArrayList<Date>();
		list.add(DateUtil.strToDate(DateUtil.getMondayOFWeek()));// 周一
		list.add(DateUtil.strToDate(DateUtil.getWeekSunday()));// 周日
		return list;
	}

	/**
	 * 根据给定日期返回该年所有月的日期信息 给2012.2.15 renturn 2012.1.01-2012.1.31
	 * 2012.2.01-2012.1.29 2012.3.01-2012.3.31...... (例如这样的，这标示该年里月信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getMonthInYear(Date date) {
		int year = DateUtil.getYear(date);
		List<String> list = new ArrayList<String>();
		list.add(year + ".01.01-" + year + ".01.31");
		list.add(year + ".02.01-" + year + ".02.28");
		list.add(year + ".03.01-" + year + ".03.31");
		list.add(year + ".04.01-" + year + ".04.30");
		list.add(year + ".05.01-" + year + ".05.31");
		list.add(year + ".06.01-" + year + ".06.30");
		list.add(year + ".07.01-" + year + ".07.31");
		list.add(year + ".08.01-" + year + ".08.31");
		list.add(year + ".09.01-" + year + ".09.30");
		list.add(year + ".10.01-" + year + ".10.31");
		list.add(year + ".11.01-" + year + ".11.30");
		list.add(year + ".12.01-" + year + ".12.31");
		if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {// 闰年
			list.set(1, year + ".02.01-" + year + ".02.29");
		}
		return list;
	}

	/**
	 * 根据给定日期返回该年所有月的日期信息 给2012.3.15 return 2012.1.01-2012.1.31
	 * 2012.2.01-2012.1.29(例如这样的，这标示2月2.15以前的周信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getMonthInYearUpDate(Date date) {
		int year = DateUtil.getYear(date);
		List<String> list = new ArrayList<String>();
		list.add(year + ".01.01-" + year + ".01.31");
		list.add(year + ".02.01-" + year + ".02.28");
		list.add(year + ".03.01-" + year + ".03.31");
		list.add(year + ".04.01-" + year + ".04.30");
		list.add(year + ".05.01-" + year + ".05.31");
		list.add(year + ".06.01-" + year + ".06.30");
		list.add(year + ".07.01-" + year + ".07.31");
		list.add(year + ".08.01-" + year + ".08.31");
		list.add(year + ".09.01-" + year + ".09.30");
		list.add(year + ".10.01-" + year + ".10.31");
		list.add(year + ".11.01-" + year + ".11.30");
		list.add(year + ".12.01-" + year + ".12.31");
		if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {// 闰年
			list.set(1, year + ".02.01-" + year + ".02.29");
		}
		int month = DateUtil.getMonth(date);
		List<String> result = new ArrayList<String>();
		for (int i = 0; i < month; i++) {
			result.add(list.get(i));
		}
		return result;
	}

	/**
	 * 根据给定日期返回该年所有月的日期信息 给2012.9.15 return 2012.10.01-2012.10.31
	 * 2012.11.01-2012.11.30 2012.12.01-2012.12.31(例如这样的，这标示2月2.15以前的周信息)
	 * 
	 * @param date
	 * @return
	 */
	public static List<String> getMonthInYearDownDate(Date date) {
		int year = DateUtil.getYear(date);
		List<String> list = new ArrayList<String>();
		list.add(year + ".01.01-" + year + ".01.31");
		list.add(year + ".02.01-" + year + ".02.28");
		list.add(year + ".03.01-" + year + ".03.31");
		list.add(year + ".04.01-" + year + ".04.30");
		list.add(year + ".05.01-" + year + ".05.31");
		list.add(year + ".06.01-" + year + ".06.30");
		list.add(year + ".07.01-" + year + ".07.31");
		list.add(year + ".08.01-" + year + ".08.31");
		list.add(year + ".09.01-" + year + ".09.30");
		list.add(year + ".10.01-" + year + ".10.31");
		list.add(year + ".11.01-" + year + ".11.30");
		list.add(year + ".12.01-" + year + ".12.31");
		if (((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)) {// 闰年
			list.set(1, year + ".02.01-" + year + ".02.29");
		}
		int month = DateUtil.getMonth(date);
		List<String> result = new ArrayList<String>();
		for (int i = month - 1; i < 12; i++) {
			result.add(list.get(i));
		}
		return result;
	}

	/**
	 * 获得当前系统时间的本月一日期和本月最后一天日期
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static List<Date> getSysStaAndEnd() {
		List<Date> list = new ArrayList<Date>();
		list.add(DateUtil.strToDate(DateUtil.getFirstDayOfMonth()));// 本月一号
		list.add(DateUtil.strToDate(DateUtil.getLastDayOfMonth()));// 本月最后一天
		return list;
	}

	/**
	 * 判断两个日期时间的大小，精确到秒   [1:大于，0:相等， -1:小于]
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static int compare(Date d1, Date d2) {
		SimpleDateFormat FORMAT = new SimpleDateFormat(
		"yyyy-MM-dd HH:mm:ss");
		String str1 = FORMAT.format(d1);
		String str2 = FORMAT.format(d2);

		int result = str1.compareTo(str2);
		if (result > 0) {
			System.out.println(str1 + " 晚于 " + str2);
			return 1;
		} else if (result == 0) {
			System.out.println(str1 + " 等于 " + str2);
			return 0;
		} else {
			System.out.println(str1 + " 早于 " + str2);
			return -1;
		}
	}
	
	/**
	 * cus  1：自定义填写    ；  2:选项选择
	 * @param daycount
	 * @param cus
	 * @return
	 */
	public static List<Map<String, Date>> getRangeByBirthday(int daycount,int cus) {
		List<Map<String, Date>> resultlist = new ArrayList<Map<String, Date>>();
		Calendar c = Calendar.getInstance();
		c.setTime(new Date());
		int nowyear = c.get(Calendar.YEAR);
		int sumday = 365;
		if(nowyear % 4 == 0 && nowyear % 100 != 0|| nowyear % 400 == 0) {
			sumday = 366;
		}
		int nowdaycount = c.get(Calendar.DAY_OF_YEAR);
		if(nowdaycount + daycount > sumday) {
			int overday = (nowdaycount + daycount) - sumday;
			if(overday / sumday > 0) {
				overday = overday % sumday;
			}
			Calendar lastDate = Calendar.getInstance();
			lastDate.set(Calendar.MONTH, 0);
			lastDate.set(Calendar.DATE, 1);
			lastDate.add(Calendar.YEAR, 1);
			lastDate.add(Calendar.DATE, -1);
			Date yearlastdate = lastDate.getTime();
			Map<String, Date> maptemp = new HashMap<String, Date>();
			maptemp.put("startdate", new Date());
			maptemp.put("enddate", yearlastdate);
			resultlist.add(maptemp);
			Map<String, Date> nextmap = new HashMap<String, Date>();
			Calendar startDate = Calendar.getInstance();
			startDate.setTime(new Date());
			startDate.set(Calendar.MONTH, 0);
			startDate.set(Calendar.DATE, 1);
			nextmap.put("startdate", startDate.getTime());
			Calendar endDate = Calendar.getInstance();
			endDate.setTime(startDate.getTime());
			endDate.add(Calendar.DATE, overday);
			nextmap.put("enddate", endDate.getTime());
			resultlist.add(nextmap);
		} else {
			c.add(Calendar.DATE, daycount);
			Map<String, Date> maptemp = new HashMap<String, Date>();
			if(daycount==1 && cus == 2){  //联系人生日 选择“明天”
				Calendar calTom = Calendar.getInstance();
				calTom.setTime(new Date());
				calTom.set(calTom.get(Calendar.YEAR), calTom.get(Calendar.MONTH), calTom.get(Calendar.DATE)+1, 0, 0, 0);
				maptemp.put("startdate", calTom.getTime());
			}else {
				maptemp.put("startdate", new Date());
			}
			maptemp.put("enddate", c.getTime());
			resultlist.add(maptemp);
		}
		return resultlist;
	}

	public static void main(String[] args) throws Exception {
		//String dString = "2012-05-12 11:11:11";
		//System.out.println( new java.sql.Date(strToDatetime(dString).getTime()));
//		System.out.println(DateUtil.strToTimeH("2012-05-12 11:11:11.0"));
//		System.out.println(getFirstDayOfMonths("2012-05-12"));
//		System.out.println("2012-05-12 11:11:11.0".substring(0,10));
		//System.out.println(getMondayOFWeek());
	}

}
