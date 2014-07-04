package com.ht.yikecrm.util.json;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonFormat {
	protected static final Log LOG = LogFactory.getLog(JsonFormat.class);

	private static JsonConfig cfg;

	public static JsonConfig getDateCfg() {
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new DateJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new DateJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}
	
	public static JsonConfig getDateAndBigDecimalCfg(String datePattern){
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.sql.Timestamp.class,
				new SimpleDateJsonValueProcessor(datePattern));
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new SimpleDateJsonValueProcessor(datePattern));
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new SimpleDateJsonValueProcessor(datePattern));
		//对BigDecimal类型的处理
		cfg.registerJsonValueProcessor(java.math.BigDecimal.class,
				new BigDecimalJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}

	public static JsonConfig getSimpleDateCfg(String datePattern) {
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.sql.Timestamp.class,
				new SimpleDateJsonValueProcessor(datePattern));
		cfg.registerJsonValueProcessor(java.util.Date.class,
				new SimpleDateJsonValueProcessor(datePattern));
		cfg.registerJsonValueProcessor(java.sql.Date.class,
				new SimpleDateJsonValueProcessor(datePattern));
		//对BigDecimal类型的处理
		cfg.registerJsonValueProcessor(java.math.BigDecimal.class,
				new BigDecimalJsonValueProcessor());
		//对Long类型进行处理
		cfg.registerJsonValueProcessor(Long.class,
				new ProductLongJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}
	
	public static JsonConfig getProductLongCfg(){
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Long.class,
				new ProductLongJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}

	public static JsonConfig getLongCfg() {
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(Long.class,
						new LongJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}
	
	public static JsonConfig getBigDecimal() {
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.math.BigDecimal.class,
				new BigDecimalJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}
	public static JsonConfig getBigDecimalAndStringCfg(){
		cfg = new JsonConfig();
		cfg.registerJsonValueProcessor(java.math.BigDecimal.class,
				new BigDecimalJsonValueProcessor());
		cfg.registerJsonValueProcessor(java.lang.String.class,new StringNullValueProcessor());
		return cfg;
	}

	@SuppressWarnings("deprecation")
	public static Date getDateValue(JSONObject jsonObject, String key) {
		Date date = null;
		if (jsonObject != null) {
			if (jsonObject.containsKey(key)) {
				String s = jsonObject.getString(key);
				SimpleDateFormat format = new SimpleDateFormat(
						"yyyy-MM-dd'T'HH:mm:ss'Z'");
				try {

					date = format.parse(s);
					Calendar ca = Calendar.getInstance();
					ca.setTime(date);
					if (ca.get(Calendar.YEAR) < 1980) {
						ca.add(Calendar.YEAR, 10);
					}
					ca.add(Calendar.HOUR_OF_DAY, 8);
					date = ca.getTime();
				} catch (ParseException e) {
					try {
						date = new Date(s);
					} catch (Exception e1) {
						LOG.debug("=========Not a date type========" + e1);
					}
				}
			}
		}
		return date;
	}

}
