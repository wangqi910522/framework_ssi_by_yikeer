package com.ht.yikecrm.spring.bean.impl;

import java.io.Serializable;

import com.ht.yikecrm.spring.bean.DynamicBean;

/**
 * 
 * 数据源动态bean描述对象
 */

public class DataSourceDynamicBean  extends DynamicBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5298566134505614210L;

	private String ID;

	private Integer isDefault;

	private String url;

	private String username;

	private String password;
	
	private String beanName;

	//    
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.youi.common.bean.DynamicBean#getBeanXml()
	 */
	@Override
	protected String getBeanXml() {
		StringBuffer xmlBuf = new StringBuffer();
		// xmlBuf.append("<bean id=\""+beanName+"\" class=\"org.apache.commons.dbcp.BasicDataSource\" destroy-method=\"close\"")
		// .append(" p:driverClassName=\""+driverClassName+"\" ")
		// .append(" p:url=\""+url+"\"")
		// .append(" p:username=\""+username+"\"")
		// .append(" p:password=\""+password+"\"")
		// .append(" p:timeBetweenEvictionRunsMillis=\""+timeBetweenEvictionRunsMillis+"\"")
		// .append(" p:minEvictableIdleTimeMillis=\""+minEvictableIdleTimeMillis+"\"/>");
		return xmlBuf.toString();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	public String getBeanName() {
		return beanName;
	}

	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	
}
