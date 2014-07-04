package com.ht.yikecrm.util;

import java.util.Calendar;
import java.util.Date;

public class QuarterBean {
	public final static int QUARTERS_EVERY_YEAR = 4;
	private final static String[] QUARTERS = { "一季度 {0}(一月 - 三月)",
			"二季度 {0}(四月 - 六月)", "三季度 {0}(七月 - 九月)", "四季度 {0}(十月 - 十二月)" };
	private final static int[][] MONTHS = { { 1, 2, 3 }, { 4, 5, 6 },
			{ 7, 8, 9 }, { 10, 11, 12 } };

	private int year;
	private int quarter;

	/**
	 * 本季度第一天
	 */
	private Date startDate;
	/**
	 * 本季度最后一天
	 */
	private Date endDate;

	public QuarterBean(int year, int quarter) {
		if (quarter < 1 || quarter > QUARTERS_EVERY_YEAR) {
			throw new IllegalArgumentException("季度无效.");
		}
		this.year = year;
		this.quarter = quarter;
		
		int startMonth = MONTHS[this.quarter - 1][0];
		int endMonth = MONTHS[this.quarter - 1][2];
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, startMonth-1);
		c.set(Calendar.DAY_OF_MONTH, 1);
		this.startDate = c.getTime();
		c.set(Calendar.MONTH, endMonth);//下个月
		c.add(Calendar.DATE, -1);//减一天
		this.endDate = c.getTime();

	}

	private QuarterBean() {

	}

	public int getYear() {
		return year;
	}

	public int getQuarter() {
		return quarter;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	/**
	 * 获得上一季度
	 * 
	 * @return
	 */
	public QuarterBean getPastQuarter() {
		QuarterBean bean = new QuarterBean();
		int y = (QUARTERS_EVERY_YEAR + 1 - quarter) / QUARTERS_EVERY_YEAR;
		bean.year = year - y;
		bean.quarter = y * QUARTERS_EVERY_YEAR + (quarter - 1);
		return bean;
	}

	public String toString() {
		return String.format(QUARTERS[quarter - 1], String.valueOf(year));
	}
}
