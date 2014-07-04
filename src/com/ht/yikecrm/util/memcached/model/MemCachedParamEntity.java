package com.ht.yikecrm.util.memcached.model;

import java.io.Serializable;

public class MemCachedParamEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4226063716578373219L;
	private String key;
	private String value;
	private String description;
	
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
