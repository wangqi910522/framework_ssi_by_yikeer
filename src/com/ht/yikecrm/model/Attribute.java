package com.ht.yikecrm.model;

import com.ht.yikecrm.util.JDBCUtil;

public class Attribute {

	private String attrName;
	/**
	 * 1:Integer 2:Long 3:BigDecimal 4:String 5:Date 6:Float,double
	 */
	private Integer attrType;
	private String attrValue;
	
	
	public Attribute() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Attribute(String attrName, Integer attrType, String attrValue) {
		super();
		this.attrName = attrName;
		this.attrType = attrType;
		this.attrValue = JDBCUtil.replaceSpecial(attrValue);
	}
	
	public Attribute(String attrName, String attrValue) {
		super();
		this.attrName = attrName;
		this.attrValue = attrValue;
	}

	public String getAttrName() {
		return attrName;
	}

	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}

	public Integer getAttrType() {
		return attrType;
	}

	public void setAttrType(Integer attrType) {
		this.attrType = attrType;
	}

	public String getAttrValue() {
		return attrValue;
	}

	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}

}
