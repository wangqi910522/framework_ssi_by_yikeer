package com.ht.yikecrm.util;

import java.io.Serializable;

import com.ht.yikecrm.model.BaseSystemConfig;

public class SystemConfig extends BaseSystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3065485733765126855L;
	
	private String flowFile;//用户访问文件

	public String getFlowFile() {
		return flowFile;
	}

	public void setFlowFile(String flowFile) {
		this.flowFile = flowFile;
	}

	
}