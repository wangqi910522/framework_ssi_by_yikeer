package com.ht.yikecrm.util;

/**
 * 
 * @author Administrator
 *
 */
public class Structure {
	
	private String filedID;
	private String field;       //查询的值
	private String href;
	private String maxLength;
	private String name; 		//字段名
	private String nameShow;    //字段显示名
	private String src;
	private Long type;			//字段类型 1.文字2.数字3.日期4.外键5.数据字典6.金额
	private String hrefmodul;	//列表显示的超链接
	private Long modelID;		//模块ID
	private String orderbyName;

	public String getOrderbyName() {
		return orderbyName;
	}

	public void setOrderbyName(String orderbyName) {
		this.orderbyName = orderbyName;
	}

	public String getFiledID() {
		return filedID;
	}

	public void setFiledID(String filedID) {
		this.filedID = filedID;
	}

	public String getField() {
		return field;
	}

	public String getHref() {
		return href;
	}

	public String getMaxLength() {
		return maxLength;
	}

	public String getName() {
		return name;
	}
	
	public String getNameShow() {
		return nameShow;
	}

	public String getSrc() {
		return src;
	}

	public void setField(String field) {
		this.field = field;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public void setMaxLength(String maxLength) {
		this.maxLength = maxLength;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setNameShow(String nameShow) {
		this.nameShow = nameShow;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public String getHrefmodul() {
		return hrefmodul;
	}

	public void setHrefmodul(String hrefmodul) {
		this.hrefmodul = hrefmodul;
	}

	public void setModelID(Long modelID) {
		this.modelID = modelID;
	}

	public Long getModelID() {
		return modelID;
	}
	
}
