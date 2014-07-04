package com.ht.yikecrm.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DateJsonValueProcessor implements JsonValueProcessor {
	protected static final Log LOG = LogFactory.getLog(DateJsonValueProcessor.class);
	
	
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		if (arg0 == null)
			return null;
		String s=arg0.toString();
		if (arg0 instanceof java.util.Date) {
			s=((java.util.Date) arg0).toString();
		}else if(arg0 instanceof java.sql.Date){
			s=((java.sql.Date) arg0).toString();
		}
		LOG.debug("============date before replace===============:"+s);		
		s=s.replace("CST", "UTC+0800");
		LOG.debug("============date end replace===============:"+s);
		return s;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return null;
		String s=arg1.toString();
		if (arg1 instanceof java.util.Date) {
			s=((java.util.Date) arg1).toString();			
		}else if(arg1 instanceof java.sql.Date){
			s=((java.sql.Date) arg1).toString();			
		}
		LOG.debug("============date before replace===============:"+s);		
		s=s.replace("CST", "UTC+0800");
		LOG.debug("============date end replace===============:"+s);
		return s;
	}
	
//	@Override
//	public Object processArrayValue(Object arg0, JsonConfig arg1) {
//		// TODO Auto-generated method stub
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
//		// TODO Auto-generated method stub
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
