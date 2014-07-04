package com.ht.yikecrm.util;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

public class Flow implements Serializable {
	
	/**
	 * 实现序列化
	 */
	private static final long serialVersionUID = -7879019573031813367L;

	private HttpServletRequest request;//request请求
	
	private String userName;//用户名
	
	private String name;//姓名
	
	private String company;//公司
	
	private String displayName;//模块名
	
	private Boolean b;//是否登录（false:未登录；true:登录）
	
	private String type;//访问系统类型（crm：亿客crm；amc：账户管理中心）
	
	private String url;//文件存放服务器的地址

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Boolean getB() {
		return b;
	}

	public void setB(Boolean b) {
		this.b = b;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
