package com.ht.yikecrm.model;

/**
 * 非持久化类
 * @author Administrator
 *
 */
public class Service {

	/**
	 * 服务名称
	 */
	private String name;
	
	/**
	 * 显示名
	 */
	private String displayName;
	
	/**
	 * 默认页面
	 */
	private String defaultUrl;
	
	public Service(String name, String displayName, String defaultUrl) {
		super();
		this.name = name;
		this.displayName = displayName;
		this.defaultUrl = defaultUrl;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getDefaultUrl() {
		return defaultUrl;
	}

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	
	
}
