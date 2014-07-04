package com.ht.yikecrm.util.json;

import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class StringNullValueProcessor implements JsonValueProcessor {

	//"\"null\"".
	@Override
	public Object processArrayValue(Object arg0, JsonConfig arg1) {
		return arg0;
	}

	@Override
	public Object processObjectValue(String arg0, Object arg1, JsonConfig arg2) {
		if (arg1 == null) {
			return "";
		} else {
			String s;
			if(arg1 instanceof String){
				s = arg1.toString();
				if(s.equals("null")){
					arg1="null ";
				}
			}
			s=arg1.toString();
			return s;
			
		}
	}

}
