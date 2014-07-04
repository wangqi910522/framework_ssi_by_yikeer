package com.ht.yikecrm.model;

import java.io.Serializable;
import java.util.Map;

public class BaseSystemConfig implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4039634268742234754L;
	
	private String appUrl;
	private String staticSourceUrl;
	private Map<String, String> dbConfig;
	private String memCacheKey;

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getStaticSourceUrl() {
		return staticSourceUrl;
	}

	public void setStaticSourceUrl(String staticSourceUrl) {
		this.staticSourceUrl = staticSourceUrl;
	}

	public Map<String, String> getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(Map<String, String> dbConfig) {
		this.dbConfig = dbConfig;
	}

	public String getMemCacheKey() {
		return memCacheKey;
	}

	public void setMemCacheKey(String memCacheKey) {
		this.memCacheKey = memCacheKey;
	}
	
	
	
	

}
