package com.ht.yikecrm.util;

import java.util.List;

public class ListUtils {

	/**
	 * 处理字符串集合拼接为字符串
	 */
	public static String caseListToString(List<String> list) {
		return caseListToString(list, StringUtils.EMPTY);
	}
	
	/**
	 * 处理字符串集合拼接为字符串
	 */
	public static String caseListToString(List<String> list, String string) {
		if (list != null && list.size() > 0) {
			for (String str : list) {
				string += "'" + str +"',";
			}
		}
		return string;
	}
	
}
