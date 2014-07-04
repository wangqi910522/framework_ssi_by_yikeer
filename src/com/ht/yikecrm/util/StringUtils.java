package com.ht.yikecrm.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtils extends org.apache.commons.lang3.StringUtils{

	/**
	 * 验证字符串为NULL或者为空
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static boolean isNullOrEmpty(String str){
		if(null == str || "".equals(str.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证字符串不为NULL不为空
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static Boolean isNotNullOrNotEmpty(String str){
		if(null != str && StringUtils.isNotEmpty(str)){
			return true;
		}
		return false;
	}
	
	/**
	 * 替换掉字符串中的 制表符、回车、换行
	 * @param str
	 * @return
	 */
	public static String replaceBlank(String str){
		String result = "";
		if (str != null){
			Pattern p = Pattern.compile("\r\n|\t|\r|\n");
			Matcher m = p.matcher(str);
			result = m.replaceAll(" ");
		}
		return result;
	}
	
	/**
	 * 为字符串中的' " \ 加上转义
	 * @param str
	 * @return
	 */
	public static String replaceEscapse(String str){
		if(str != null){
			str = str.replaceAll("\\\\", "\\\\\\\\");
			str = str.replaceAll("'", "\\\\'");
			str = str.replaceAll("\"", "\\\\\\\"");
			str = str.replaceAll("<", "&lt;");
			str = str.replaceAll(">", "&gt;");
		}
		return str;
	}
	
	public static String replaceSpecial(String str) throws Exception{
//		if(!isNullOrEmpty(str)){
//			str = str.replaceAll("&", "&amp;");
//			str = str.replaceAll("\"", "&#34;");
//			str = str.replaceAll("'", "&#39;");
//			str = str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
//			if(str.contains("\r\n")){
//				str = str.replaceAll("\r\n", "");
//			}
//		}
		return str;
	}
	
	public static String replaceSpecialMark(String str, int sign) throws Exception {
		if(!isNullOrEmpty(str)) {
			str = str.replaceAll("\\\\", "\\\\\\\\");
			if(sign == 1) {	//sign为1时，替换单引号'
				str = str.replaceAll("'", "\\\\'");
			} else if(sign == 2) {	//sign为2时，替换双引号"
				str = str.replaceAll("\"", "\\\\\\\"");
			}
		}
		return str;
	}
	/**
	 * 转换' "
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public static String replaceSpecialMark(String str) throws Exception {
		if(!isNullOrEmpty(str)) {
			str = str.replaceAll("\\\\", "\\\\\\\\");
			str = str.replaceAll("'", "\\\\'");
			str = str.replaceAll("\"", "\\\\\\\"");
		}
		return str;
	}
	public static String getString(Object obj) throws Exception {
		if(obj != null){
			return obj.toString();
		}
		return "";
	}
	
	/**
	 * 去除字符串的最后一位
	 * @param str
	 * @return
	 */
	public static String removeLastIndex(String str) {
		if (!isNullOrEmpty(str)) {
			str = str.substring(0, str.length() - 1);
		}
		return str;
	}
	
	/**
	 * 处理一个字符串
	 * @param strA
	 * @param strB
	 * @return
	 */
	public static String dealStrWithAnOtherStr(String strA, String strB) {
		if(StringUtils.isNullOrEmpty(strA)){
			return strB;
		}
		return strA + "," + strB;
	}
	
}
