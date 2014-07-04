package com.ht.yikecrm.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LongJsonValueProcessor implements JsonValueProcessor {
	protected static final Log LOG = LogFactory
			.getLog(LongJsonValueProcessor.class);

	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		// TODO Auto-generated method stub

		return arg0;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		// TODO Auto-generated method stub
		if (arg1 == null)
			return null;
		String s = arg1.toString();
		if (null != arg0 && arg0.toLowerCase().equals("permission")) {
			if (arg1 instanceof Long) {
				switch (Integer.parseInt(s)) {
				case 5:
					s = "普通用户";
					break;
				case 9:
					s = "管理员";
				}
			}
			return s;	
		}
		return arg1;
	}

}
