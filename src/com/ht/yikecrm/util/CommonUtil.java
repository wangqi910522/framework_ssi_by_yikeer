package com.ht.yikecrm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 工具类 - 公用
 * ============================================================================
 * 版权所有 2008-2010 武汉宏途科技有限公司，并保留所有权利。
 * ----------------------------------------------------------------------------
 * 提示：
 * ----------------------------------------------------------------------------
 * 官方网站：http://www.hongtoo.com
 * ----------------------------------------------------------------------------
 * KEY:
 * ============================================================================
 */

public class CommonUtil {
	/**
	 * 随机获取UUID字符串(无中划线)
	 * 
	 * @return UUID字符串
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString();
		return uuid.substring(0, 8) + uuid.substring(9, 13)
				+ uuid.substring(14, 18) + uuid.substring(19, 23)
				+ uuid.substring(24);
	}

	/**
	 * 随机获取字符串
	 * 
	 * @param length 随机字符串长度
	 * 
	 * @return 随机字符串
	 */
	public static String getRandomString(int length) {
		if (length <= 0) {
			return "";
		}
		char[] randomChar = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p', 'a', 's',
				'd', 'f', 'g', 'h', 'j', 'k', 'l', 'z', 'x', 'c', 'v', 'b',
				'n', 'm' };
		Random random = new Random();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			stringBuffer.append(randomChar[Math.abs(random.nextInt())
					% randomChar.length]);
		}
		return stringBuffer.toString();
	}

	/**
	 * 根据指定长度 分隔字符串
	 * 
	 * @param str 需要处理的字符串
	 * @param length 分隔长度
	 * 
	 * @return 字符串集合
	 */
	public static List<String> splitString(String str, int length) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length(); i += length) {
			int endIndex = i + length;
			if (endIndex <= str.length()) {
				list.add(str.substring(i, i + length));
			} else {
				list.add(str.substring(i, str.length() - 1));
			}
		}
		return list;
	}

	/**
	 * 将字符串List转化为字符串，以分隔符间隔.
	 * 
	 * @param list 需要处理的List.
	 * @param separator 分隔符.
	 * 
	 * @return 转化后的字符串
	 */
	public static String toString(List<String> list, String separator) {
		StringBuffer stringBuffer = new StringBuffer();
		for (String str : list) {
			stringBuffer.append(separator + str);
		}
		stringBuffer.deleteCharAt(0);
		return stringBuffer.toString();
	}

	/**
	 * 字符串合并方法，返回一个合并后的字符串
	 */
	public static String format(String str, Object... args) {

		// 这里用于验证数据有效性
		if (str == null || "".equals(str))
			return "";
		if (args.length == 0) {
			return str;
		}

		/*
		 * 如果用于生成SQL语句，这里用于在字符串前后加单引号 for(int i=0;i<args.length;i++) { String
		 * type="java.lang.String";
		 * if(type.equals(args[i].getClass().getName()))
		 * args[i]="'"+args[i]+"'"; }
		 */

		String result = str;

		// 这里的作用是只匹配{}里面是数字的子字符串
		java.util.regex.Pattern p = java.util.regex.Pattern
				.compile("\\{(\\d+)\\}");
		java.util.regex.Matcher m = p.matcher(str);

		while (m.find()) {
			// 获取{}里面的数字作为匹配组的下标取值
			int index = Integer.parseInt(m.group(1));

			// 这里得考虑数组越界问题，{1000}也能取到值么？？
			if (index < args.length) {

				// 替换，以{}数字为下标，在参数数组中取值
				result = result.replace(m.group(), args[index].toString());
			}
		}
		return result;
	}

	/**
	 *  字符串a是否包含字符串b
	 */
	public static boolean ifContainIndexOf(String a, String b) {
		if (a.indexOf(b) >= 0) {
			return true;
		}
		return false;
	}

	/**
	 *  字符串a是否包含字符串b（正则表达式判断）
	 */
	public boolean ifContainRegex(String a, String b) {
		if (a.matches(".*" + b + ".*")) {
			return true;
		}
		return false;
	}

	/**
	 * 是否是数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumber(String str) {
		Pattern pattern = Pattern.compile("^-?\\d+(\\.\\d+)?$");// /^-?\d+$/;
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否为整数
	 * 
	 * @param str
	 *            传入的字符串
	 * 
	 * @return 是整数返回true,否则返回false
	 */
	public static boolean isInteger(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?[\\d]+$");// ^(\+|-)?\d+$
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否是yyyy-m-d的日期格式
	 * 
	 * @param str
	 * @return
	 * @throws ParseException
	 */
	public static boolean isDate(String str) throws ParseException {
		boolean result = true;
		Pattern pattern = Pattern
				.compile("^$|^(\\d{1,4})(-)(\\d{1,2})(-)(\\d{1,2})$");
		if (!pattern.matcher(str).matches()) {
			result = false;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			cal.setTime(sdf.parse(str));
			String[] dates = str.split("-");
			if (Integer.parseInt(dates[0]) != cal.get(Calendar.YEAR)
					|| Integer.parseInt(dates[1]) != cal.get(Calendar.MONTH) + 1
					|| Integer.parseInt(dates[2]) != cal.get(Calendar.DATE)) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 判断是否为浮点数，包括double和float
	 * 
	 * @param str
	 *            传入的字符串
	 * 
	 * @return 是浮点数返回true,否则返回false
	 */
	public static boolean isDouble(String str) {
		Pattern pattern = Pattern.compile("^[-\\+]?\\d*\\.?\\d+$");// ^[-+]?\d*\.?\d*$
		return pattern.matcher(str).matches();
	}

	/**
	 * 判断是否是金额
	 * 
	 * @param str
	 *            传入的字符串
	 * @return 是金额类型返回true,否则返回false
	 */
	public static boolean isMoney(String str) {
		boolean result = true;
		Pattern pattern = Pattern
				.compile("^$|^-?(?:\\d+|\\d{1,3}(?:,\\d{3})+)(?:\\.\\d{1,2})?$");
		if (!pattern.matcher(str).matches() || str.length() > 19) {
			result = false;
		}
		return result;
	}

	/**
	 * 判断是否是百分比
	 * 
	 * @param str	传入的字符串
	 * @return	是百分比类型返回true,否则返回false
	 */
	public static boolean isRatio(String str) {
		boolean result = true;
		if (!isDouble(str)) {
			result = false;
		} else {
			float ratio = Float.parseFloat(str);
			if (ratio > 9999999999999999999F
					|| ratio < 0
					|| str.length() > 19
					|| (str.indexOf(".") != -1 && str.split("\\.")[1].length() > 2)) {
				result = false;
			}
		}
		return result;
	}

	/**
	 * 判断是否是电话
	 * 
	 * @param str	传入的字符串
	 * @return	是电话类型返回true,否则返回false
	 */
	public static boolean isPhone(String str) {
		boolean result = true;
		Pattern pattern = Pattern
				.compile("^(\\d{3}-\\d+(-\\d+)*)|(\\d{4}-\\d+(-\\d+)*)|(\\d*)$");
		if (!pattern.matcher(str).matches() || str.length() > 19) {
			result = false;
		}
		return result;
	}

	/**
	 * 获得一个UUID
	 * 
	 * @return String UUID
	 */
	public static String getUUIDCode() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}

	/**
	 * 是否为邮箱
	 * 
	 * @param 待验证的字符串
	 * 
	 * @return 如果是符合邮箱格式的字符串,返回<b>true</b>,否则为<b>false</b>
	 */
	public static boolean isEmail(String str) {

		// String regex =
		// "^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$"
		// ;
		String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
		return match(regex, str);

	}

	/**
	 * 是否是网址类型
	 * 
	 * @param 待验证的字符串
	 * 
	 * @return 如果是符合网址格式的字符串,返回<b>true</b>,否则为<b>false</b>
	 */

	public static boolean isWebUrl(String str) {

		boolean flag = false;

		// 去掉"/"验证 （导入空格已经去掉）
		if (str.endsWith("/")) {
			str = str.substring(0, str.length() - 1);
		}

		// String regex = "http://(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*"
		// ;
		// String regex2 = "www.(([a-zA-z0-9]|-){1,}\\.){1,}[a-zA-z0-9]{1,}-*" ;
		// String regex =
		// "(^(http://www\\.)||^(www\\.))[a-zA-z0-9]{1,}\\.[\\S]{1,}";
		// String regex
		// ="(http://|www\\.)([a-zA-z0-9]{1,}\\.){1,}[a-zA-z0-9]{1,}\\w*";
		String regex = "(http://[a-zA-Z0-9]{0,}\\.|www\\.|https://[a-zA-Z0-9]{0,}\\.)?((([a-zA-Z0-9\\u4e00-\\u9fa5]{1})([a-zA-z0-9\\u4e00-\\u9fa5\\-\\/]{0,}))?([a-zA-Z0-9\\u4e00-\\u9fa5]{1}\\.)){1,}([a-zA-z]{1,})[a-zA-Z0-9\\u4e00-\\u9fa5\\/\\?\\#\\=]{0,}";
		if (match(regex, str)) {
			flag = true;
		} else {
			// flag = match(regex2 ,str );
		}
		return flag;
	}

	/**
	 * 
	 * @param 待验证的字符串
	 * 
	 * @return 如果是符合网址格式的字符串,返回<b>true</b>,否则为<b>false</b>
	 */

	public static boolean regValidate(String reg, String str) {
		return match(reg, str);
	}

	/**
	 * 
	 * @param regex
	 *            正则表达式字符串
	 * 
	 * @param str
	 *            要匹配的字符串
	 * 
	 * @return 如果str 符合 regex的正则表达式格式,返回true, 否则返回 false;
	 */

	private static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}

	/**
	 * 根据日期计算2日期之前的天数差
	 * 
	 * @param date
	 * @param createTime
	 * @param format
	 * @return
	 */
	public static int dateDiff(java.util.Date date, java.util.Date createTime) {
		// 按照传入的格式生成一个simpledateformate对象
		long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
		// 获得两个时间的毫秒时间差异
		long diff = date.getTime() - createTime.getTime();
		Long day = diff / nd;// 计算差多少天
		// 输出结果
		return day.intValue();
	}

	/**
	 * 将特殊字符替换成html标签
	 * 
	 * @return
	 */
	public static String replaceEnter(String field) {
		String result = field;
		if (field != null && !"".equals(field)) {
			result = field.trim().replaceAll("<", "&lt;").replaceAll(">",
					"&gt;");
		}
		if (result.contains("\r\n")) {
			result = result.replaceAll("\r\n", "<br />");
		}
		return result;
	}

	/**
	 * 去掉数组中重复值
	 * 
	 * @param str
	 * @return
	 */
	public static String[] removeRepeat(String[] str) {
		List<String> list = new ArrayList<String>();
		for (int i = 0; i < str.length; i++) {
			if (!list.contains(str[i])) {
				list.add(str[i]);
			}
		}
		return list.toArray(new String[1]); // 返回一个包含所有对象的指定类型的数组
	}

	/**
	 * 
	 * @param money
	 * @return
	 */
	public static String getMoneyData(String money) {
		boolean boo = true;
		if (money.indexOf("-") != -1) {
			money = money.substring(1);
			boo = false;
		}
		String result = StringUtils.EMPTY;
		String after = StringUtils.EMPTY;
		if (money.indexOf('.') != -1) {
			after = money.substring(money.indexOf('.'));
			money = money.substring(0, money.indexOf('.'));
		} else {
			after = ".00";
		}
		int len = money.length();
		int index = 0;
		if (len % 3 == 0) {
			index = len / 3 - 1;
		} else {
			index = (int) Math.floor(len / 3);
		}
		for (int i = 0; i < index; i++) {
			result = money.substring(len - 3, len) + "," + result;
			len = len - 3;
		}
		result = money.substring(0, len) + "," + result;
		if (boo == false) {
			return "-" + result.substring(0, result.length() - 1) + after;
		} else {
			return result.substring(0, result.length() - 1) + after;
		}
	}
}
