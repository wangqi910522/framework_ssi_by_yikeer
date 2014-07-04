package com.ht.yikecrm.util.message;

import java.io.Serializable;

import com.ht.yikecrm.model.BaseSystemConfig;

public class SystemConfig extends BaseSystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3065485733765126855L;

	private String httpURL;
	
	public String getHttpURL() {
		return httpURL;
	}

	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}

}