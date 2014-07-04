package com.ht.yikecrm.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class BigDecimalJsonValueProcessor implements JsonValueProcessor {
	protected static final Log LOG = LogFactory.getLog(BigDecimalJsonValueProcessor.class);
	
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		if (arg0 == null) {
			return "";
		} else {
			String s=arg0.toString();
			return s;
		}
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if (arg1 == null) {
			return "";
		} else {
			String s=arg1.toString();
			return s;
		}
	}
	
//	@Override
//	public Object processArrayValue(Object arg0, JsonConfig arg1) {
//		if (arg0 == null)
//			return null;
//		java.util.Date d=new java.util.Date();
//		Long s=d.getTime();
//		if (arg0 instanceof java.util.Date) {
//			// String str = new SimpleDateFormat(format).format((Date) value);
//			//DateFormat df= DateFormat.getInstance();
//			//Calendar c=Calendar.getInstance();
//			//c.setTime((Date) arg0);			
//			s=((java.util.Date) arg0).getTime();
//		}else if(arg0 instanceof java.sql.Date){
//			//java.sql.Date temp=(java.sql.Date)arg0;
//			s=((java.sql.Date) arg0).getTime();
//		}
//		LOG.debug("============date get time===============:"+s);	
//		return s;
//	}
//
//	@Override
//	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
//		if (arg1 == null)
//			return null;
//		java.util.Date d=new java.util.Date();
//		Long s=d.getTime();
//		if (arg1 instanceof java.util.Date) {
//			// String str = new SimpleDateFormat(format).format((Date) value);
//			//DateFormat df= DateFormat.getInstance();
//			//Calendar c=Calendar.getInstance();
//			//c.setTime((Date) arg0);			
//			s=((java.util.Date) arg1).getTime();
//		}else if(arg1 instanceof java.sql.Date){
//			//java.sql.Date temp=(java.sql.Date)arg0;
//			s=((java.sql.Date) arg1).getTime();
//		}
//		LOG.debug("============date get time===============:"+s);
//		return s;
//	}

}
