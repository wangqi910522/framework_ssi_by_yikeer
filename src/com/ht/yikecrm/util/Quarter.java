package com.ht.yikecrm.util;

import java.util.Calendar;
/**
 * 季度相关
 * @author HongToo
 *
 */
public class Quarter {
	public static void main(String[] args) {
		QuarterBean[] quarters = getRecentQuarters(10);
		for (int i = 0; i < quarters.length; i++) {
			System.out.println(quarters[i].toString());
		}
	}

	/**
	 * 获得最近的季度
	 * 
	 * @param count
	 *            需要获得季度的数量
	 * @return
	 */
	public static QuarterBean[] getRecentQuarters(int count) {
		if (count < 1) {
			throw new IllegalArgumentException("必须大于0.");
		}
		QuarterBean[] quarters = new QuarterBean[count];
		quarters[0] = getCurrentQuarter();
		for (int i = 1; i < quarters.length; i++) {
			quarters[i] = quarters[i - 1].getPastQuarter();
		}
		return quarters;
	}

	/**
	 * 获得当前季度
	 * 
	 * @return
	 */
	public static QuarterBean getCurrentQuarter() {
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 获得指定日期季度
	 * 
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static QuarterBean getCurrentQuarter(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.strToDate(strDate));
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 获得上一季度
	 * 
	 * @return
	 */
	public static QuarterBean getPrevQuarter() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, -3); // 上3个月
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 获得上一季度
	 * 
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static QuarterBean getPrevQuarter(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.strToDate(strDate));
		calendar.add(Calendar.MONTH, -3); // 上3个月
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 获得下一季度
	 * 
	 * @return
	 */
	public static QuarterBean getNextQuarter() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MONTH, 3); // 下3个月
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 获得下一季度
	 * 
	 * @param strDate
	 *            yyyy-MM-dd
	 * @return
	 */
	public static QuarterBean getNextQuarter(String strDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(DateUtil.strToDate(strDate));
		calendar.add(Calendar.MONTH, 3); // 下3个月
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		return new QuarterBean(year, getQuarter(month));
	}

	/**
	 * 通过月份计算季度
	 * 
	 * @param month
	 * @return
	 */
	public static int getQuarter(int month) {
		if (month < 1 || month > 12) {
			throw new IllegalArgumentException("月份无效.");
		}
		return (month - 1) / 3 + 1;
	}
}
