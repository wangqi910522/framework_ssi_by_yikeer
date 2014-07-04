package com.ht.yikecrm.util.amc;

import java.io.Serializable;

import com.ht.yikecrm.model.BaseSystemConfig;

public class SystemConfig extends BaseSystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3065485733765126855L;

	private String httpURL;
	
	private String ipdataFilePath;   //ipdata存放路径
	
	private String yikecrmPath;      //亿客系统发布名
	
	private String flowFile;//用户访问文件
	
	public String getHttpURL() {
		return httpURL;
	}

	public void setHttpURL(String httpURL) {
		this.httpURL = httpURL;
	}

	public String getIpdataFilePath() {
		return ipdataFilePath;
	}

	public void setIpdataFilePath(String ipdataFilePath) {
		this.ipdataFilePath = ipdataFilePath;
	}

	public String getYikecrmPath() {
		return yikecrmPath;
	}

	public void setYikecrmPath(String yikecrmPath) {
		this.yikecrmPath = yikecrmPath;
	}

	public String getFlowFile() {
		return flowFile;
	}

	public void setFlowFile(String flowFile) {
		this.flowFile = flowFile;
	}
	
}