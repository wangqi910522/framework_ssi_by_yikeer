package com.ht.yikecrm.util.json;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang3.StringUtils;

public class SimpleDateJsonValueProcessor implements JsonValueProcessor {
	public static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	private DateFormat dateFormat;

	/**
	 * 构造方法.
	 * 
	 */
	public SimpleDateJsonValueProcessor() {
		dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
	}

	/**
	 * 构造方法.
	 * 
	 * @param datePattern
	 *            日期格式
	 */
	public SimpleDateJsonValueProcessor(String datePattern) {
		if (StringUtils.isEmpty(datePattern))
			dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
		else
			dateFormat = new SimpleDateFormat(datePattern);
	}

	/**
	 * 
	 */
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return process(arg0);
	}

	/**
	 * 
	 */
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		return process(arg1);
	}

	/**
	 * 
	 * @param value
	 * @return
	 */
	private Object process(Object value) {
		// return dateFormat.format((Date) value);
		String rStr = StringUtils.EMPTY;
		if (null != value)
			rStr = dateFormat.format(((Date) value));
		return rStr;
	}
}
