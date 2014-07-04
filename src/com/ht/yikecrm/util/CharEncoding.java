/**
 * 工具包
 */
package com.ht.yikecrm.util;

/**
 * 该类负责编码转换
 * 
 * @author Administrator
 */
public class CharEncoding {
	/**
	 * 字符编码转换，将其转换为ISO-8859-1编码
	 * 
	 * @param str
	 *            需转换的字符串
	 * @return 转换编码的字符串
	 */
	public static String codeToString(String str) {
		String s = str;
		try {
			byte tempB[] = s.getBytes("ISO-8859-1");
			s = new String(tempB);
			return s;
		} catch (Exception e) {
			return s;
		}
	}
}