package com.ht.yikecrm.util.json;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class ProductLongJsonValueProcessor implements JsonValueProcessor {
	
	protected static final Log LOG = LogFactory
	.getLog(LongJsonValueProcessor.class);

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

}
