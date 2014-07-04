package com.ht.yikecrm.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class EDateUtil {
	
	/**
	 * 获得当前日期与本周日相差的天数
	 */
	private static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期日是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return 0;
		} else {
			return 1 - dayOfWeek;
		}
	}
	
	/**
	 * 获得当前日期于本周一相差的天数 2222222222222222222222222
	 */
	private static int getMondayPlus2() {
		Calendar cd = Calendar.getInstance();
		// 获得今天是一周的第几天，星期一是第一天，星期二是第二天......
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK);
		if (dayOfWeek == 1) {
			return 1;
		} else {
			return 2 - dayOfWeek;
		}
	}
	
	/**
	 * 获得上周第一天的日期
	 */
	public static Date getPreWeekFirstDay() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	/**
	 * 获得上周第一天的日期222222222222222222222222222222222
	 */
	public static Date getPreWeekFirstDay2() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus2();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 * weeks);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	/**
	 * 获得上周最后一天的日期
	 */
	public static Date getPreWeekLastDay() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date lastday = currentDate.getTime();
		return lastday;
	}
	
	/**
	 * 获得上周最后一天的日期222222222222222222222222222222
	 */
	public static Date getPreWeekLastDay2() {
		int weeks = 0;
		weeks--;
		int mondayPlus = getMondayPlus2();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + weeks);
		Date lastday = currentDate.getTime();
		return lastday;
	}
	
	/**
	 * 获得本周第一天的日期
	 */
	public static Date getFirstDayOfWeek() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	/**
	 * 获得本周第一天的日期222222222222222222222222
	 */
	public static Date getFirstDayOfWeek2() {
		int mondayPlus = getMondayPlus2();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	/**
	 * 获得本周最后一天的日期
	 */
	public static Date getLastDayOfWeek() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
		Date lastday = c.getTime();
		return lastday;
	}
	
	/**
	 * 获得本周最后一天的日期222222222222222222222222222
	 */
	public static Date getLastDayOfWeek2() {
		Calendar c = Calendar.getInstance();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		Date lastday = c.getTime();
		return lastday;
	}
	
	/**
	 * 获取指定日期自定义周的第一天的日期
	 * @param date
	 * @param i (1.星期一,2.星期二,3.星期三,4.星期四,5.星期五,6.星期六,7.星期日)
	 */
	public static Date getFirstDayOfWeekCustom(Date date, int i) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		switch (i) {
			case 1:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				break;
			case 2:
				c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				break;
			case 3:
				c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				break;
			case 4:
				c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				break;
			case 5:
				c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				break;
			case 6:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				break;
			case 7:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				break;
			default:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				break;
		}	
		Date resultdate = c.getTime();
		return resultdate;
	}
	
	/**
	 * 获取指定日期自定义周的星期一的日期
	 * @param date
	 */
	public static Date getFirstDayOfWeekMonday(Date date) {
		Date resultdate = getFirstDayOfWeekCustom(date, 1);
		return resultdate;
	}
	
	/**
	 * 获取指定日期自定义周的最后一天的日期
	 * @param date
	 * @param i (1.星期一,2.星期二,3.星期三,4.星期四,5.星期五,6.星期六,7.星期日)
	 */
	public static Date getLastDayOfWeekCustom(Date date, int i) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.setFirstDayOfWeek(Calendar.MONDAY);
		switch (i) {
			case 1:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				break;
			case 2:
				c.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
				break;
			case 3:
				c.set(Calendar.DAY_OF_WEEK, Calendar.WEDNESDAY);
				break;
			case 4:
				c.set(Calendar.DAY_OF_WEEK, Calendar.THURSDAY);
				break;
			case 5:
				c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
				break;
			case 6:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
				break;
			case 7:
				c.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
				break;
			default:
				c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
				break;
		}
		c.add(Calendar.DATE, 6);
		Date resultdate = c.getTime();
		return resultdate;
	}
	
	/**
	 * 获取指定日期自定义周的星期天的日期
	 * @param date
	 */
	public static Date getLastDayOfWeekSunday(Date date) {
		Date resultdate = getLastDayOfWeekCustom(date, 1);
		return resultdate;
	}
	
	/**
	 * 
	 * 获得下周第一天的日期
	 */
	public static Date getNextWeekFirstDay() {
		int weeks = 0;
		weeks++;
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	public static Date getNextWeekFirstDay2() {
		int weeks = 0;
		weeks++;
		int mondayPlus = getMondayPlus2();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7);
		Date firstday = currentDate.getTime();
		return firstday;
	}
	
	/**
	 * 获得下周最后一天的日期
	 */
	public static Date getNextWeekLastDay() {
		int mondayPlus = getMondayPlus();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date lastday = currentDate.getTime();
		return lastday;
	}
	
	public static Date getNextWeekLastDay2() {
		int mondayPlus = getMondayPlus2();
		GregorianCalendar currentDate = new GregorianCalendar();
		currentDate.add(GregorianCalendar.DATE, mondayPlus + 7 + 6);
		Date lastday = currentDate.getTime();
		return lastday;
	}
	
	/**
	 * 获得与某日期相隔几天的日期的00:00:00
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的天数
	 * @return 返回的日期
	 */
	public static Date addDayStart(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addCount);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 获得与某日期相隔几天的日期的23:59:59
	 * 
	 * @param date
	 *            指定的日期
	 * @param addCount
	 *            相隔的天数
	 * @return 返回的日期
	 */
	public static Date addDayEnd(Date date, int addCount) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, addCount);
		calendar.getTime();
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}
	
	/**
	 * 获得某年的最后一天
	 * @param yearstr
	 * @return
	 */
	public static Date getLastDayOfYear(String yearstr) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(yearstr));
		calendar.set(Calendar.MONTH, 11);
		calendar.set(Calendar.DATE, 31);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		return calendar.getTime();
	}
	
	/**
	 * 获得某年的第一天
	 * @param yearstr
	 * @return
	 */
	public static Date getFirstDayOfYear(String yearstr) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, Integer.parseInt(yearstr));
		calendar.set(Calendar.MONTH, 0);
		calendar.set(Calendar.DATE, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		return calendar.getTime();
	}
	
	/**
	 * 1970年以后可用//获得给定日期  本周星期一的日期(中国)
	 */
	public static Date getCurrentWeekMon(Date date) {
		Long time = date.getTime();
		Calendar c = Calendar.getInstance();
		int weekInfo = c.get(Calendar.DAY_OF_WEEK);//1~7  星期天为1...
		if(weekInfo==1){
			weekInfo = 7-1;
		}else{
			weekInfo = weekInfo-1-1;
		}
		time = time - 3600000L * 24L * (weekInfo);
		Date result = new Date(time);
		return result;
	}
	
	
	/**
	 * 1970年以后可用////获得给定时间 本周星期天的日期(中国)
	 */
	public static Date getCurrentWeekSun(Date date) {
		Long time = date.getTime();
		Calendar c = Calendar.getInstance();
		int weekInfo = c.get(Calendar.DAY_OF_WEEK);//1~7  星期天为1...
		if(weekInfo==1){
			weekInfo = 0;
		}else{
			weekInfo = 8-weekInfo;
		}
		time = time + 3600000L * 24L * (weekInfo);
		Date result = new Date(time);
		return result;
	}
}
