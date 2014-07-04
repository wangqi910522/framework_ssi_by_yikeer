package com.ht.yikecrm.util.back;

import java.io.Serializable;

import com.ht.yikecrm.model.BaseSystemConfig;

public class SystemConfig extends BaseSystemConfig implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3065485733765126855L;
	
	private String yikecrmPath;      //亿客系统发布名
	
	private String crmFlowFile;//crm流文件
	
	private String amcFlowFile;//amc流文件
	
	public String getYikecrmPath() {
		return yikecrmPath;
	}

	public void setYikecrmPath(String yikecrmPath) {
		this.yikecrmPath = yikecrmPath;
	}

	public String getCrmFlowFile() {
		return crmFlowFile;
	}

	public void setCrmFlowFile(String crmFlowFile) {
		this.crmFlowFile = crmFlowFile;
	}

	public String getAmcFlowFile() {
		return amcFlowFile;
	}

	public void setAmcFlowFile(String amcFlowFile) {
		this.amcFlowFile = amcFlowFile;
	}
	
}