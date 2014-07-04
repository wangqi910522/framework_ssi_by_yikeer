package com.ht.yikecrm.util;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

import org.apache.struts2.ServletActionContext;

import com.ht.yikecrm.util.json.SimpleDateJsonValueProcessor;
import com.opensymphony.xwork2.ActionContext;

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

public class MyWebUtils {

	/**
	 * 获取Session里面的值
	 * 
	 * @param name
	 * @return
	 */
	public static Object getSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session.get(name);
	}
	
	
	/**
	 * 移除 session中的值
	 * 
	 * @param name
	 */

	public static void removeSession(String name) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.remove(name);
	}

	/**
	 * 获取request中的值
	 * @param name
	 * @return
	 */
	public static Object getAttribute(String name) {
		return ServletActionContext.getRequest().getAttribute(name);
	}
	/**
	 * 获取request中的值
	 * @param name
	 * @return
	 */
	public static void setRequest(String key,Object value) {
		ServletActionContext.getRequest().setAttribute(key, value);
	}
	/**
	 * 存取数据到request
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, Object value) {
		ServletActionContext.getRequest().setAttribute(name, value);
	}

	/**
	 * request 获取Parameter
	 * @param name
	 * @return
	 */
	public static String getParameter(String name) {
		return getRequest().getParameter(name);
	}
	
	/**
	 * request 获取Parameter数组
	 * @param name
	 * @return
	 */
	public static String[] getParameterValues(String name) {
		return getRequest().getParameterValues(name);
	}
	/**
	 *  获取session（Map）
	 * @return session
	 */
	public static Map<String, Object> getSession() {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		return session;
	}

	/**
	 * 存值到session
	 * @param name(key)
	 * @param value(value)
	 */
	public static void setSession(String name, Object value) {
		ActionContext actionContext = ActionContext.getContext();
		Map<String, Object> session = actionContext.getSession();
		session.put(name, value);
	}

	/**
	 * 获取request 对象
	 * @return  HttpServletRequest
	 */
	public static HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	/**
	 * 获取 response 对象
	 * @return HttpServletResponse
	 */
	public static HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	/**
	 * 获取Application
	 * @return ServletContext
	 */
	public static ServletContext getApplication() {
		return ServletActionContext.getServletContext();
	}

	/**
	 * ajax 输出
	 * @param content 类容
	 * @param type  输出类型 ("text/plain","text/html","text/xml", "text/html","text/html")
	 * @return
	 */
	public static String ajax(String content, String type) {
		try {
			HttpServletResponse response = ServletActionContext.getResponse();
			response.setContentType(type + ";charset=UTF-8");
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
			response.getWriter().write(content);
			response.getWriter().flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * // AJAX输出文本，返回null
	 * @param text 类容
	 * @return
	 */
	public static String ajaxText(String text) {
		return ajax(text, "text/plain");
	}

	/**
	 * // AJAX输出html，返回null
	 * @param html 类容
	 * @return
	 */
	public static String ajaxHtml(String html) {
		return ajax(html, "text/html");
	}

	/**
	 * // AJAX输出xml，返回null
	 * @param xml 类容
	 * @return
	 */
	public static String ajaxXml(String xml) {
		return ajax(xml, "text/xml");
	}

	/**
	 * // AJAX输出json，返回null
	 * @param jsonString 类容
	 * @return
	 */
	public static String ajaxJson(String jsonString) {
		return ajax(jsonString, "text/html");
	}

	/**
	 * 根据Map输出JSON，返回null
	 * @param jsonMap Map类容
	 * @return
	 */
	public static String ajaxJson(Map<String, String> jsonMap) {
		JSONObject jsonObject = JSONObject.fromObject(jsonMap, configJson());
		return ajax(jsonObject.toString(), "text/html");
	}

	/**
	 * 根据Object输出Json,返回null
	 * 
	 * @param object
	 * @return
	 */
	public static String ajaxJson(Object object) {
		// JSONObject jsonObject = JSONObject.fromObject(object,configJson());
		JSONObject jsonObject = JSONObject.fromObject(object);
		// return ajax(jsonObject.toString(), "text/html");
		return jsonObject.toString();
	}

	/**
	 * 
	 * 设置页面不缓存
	 */
	public static void setResponseNoCache() {
		getResponse().setHeader("progma", "no-cache");
		getResponse().setHeader("Cache-Control", "no-cache");
		getResponse().setHeader("Cache-Control", "no-store");
		getResponse().setDateHeader("Expires", 0);
	}

	/**
	 * 
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson(String datePattern) {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new SimpleDateJsonValueProcessor(datePattern));
		return jsonConfig;
	}

	/**
	 * 
	 * @param datePattern
	 * @return
	 */
	public static JsonConfig configJson() {
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "" });
		jsonConfig.setIgnoreDefaultExcludes(false);
		jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
		jsonConfig.registerJsonValueProcessor(Date.class,
				new SimpleDateJsonValueProcessor());
		return jsonConfig;
	}
}