package com.ht.yikecrm.util;

public class ReflectUtil {
	public static String getGetterName(String name){
		return "get"+name.substring(0,1).toUpperCase()+name.substring(1);		
	}
	
	public static String getSetterName(String name){
		return "set"+name.substring(0,1).toUpperCase()+name.substring(1);		
	}
}
