package com.ht.yikecrm.model;

/**
 * 用于页面上的判断 columnDefine.jsp
 * 
 * @author LGF
 * 注：同步处理位置: columnType_json.js    ComScreening.java
 * 
 * 
 */
public class ColumnType {

	private int varchar;
	private int naturalNumber;
	private int date;
	private int forgien;
	private int option;
	private int money;
	private int webSite;
	private int postCode;
	private int ratio;
	private int email;
	private int phone;
	private int num;
	private int datetime;
	private int time;
	private int checkBox;
	private int serialNumber;
	private int formula;
	private int bigColumn;
	private int address;

	public ColumnType() {
		super();
		this.varchar = 1;
		this.naturalNumber = 2;
		this.date = 3;
		this.forgien = 4;
		this.option = 5;
		this.money = 6;
		this.webSite = 7;
		this.postCode = 8;
		this.ratio = 9;
		this.email = 10;
		this.phone = 11;
		this.num = 12;
		this.datetime = 13;
		this.time = 14;
		this.checkBox = 15;
		this.serialNumber = 16;
		this.formula = 17;
		this.bigColumn = 18;
		this.address = 19;
	}

	public int getVarchar() {
		return varchar;
	}

	public void setVarchar(int varchar) {
		this.varchar = varchar;
	}

	public int getNaturalNumber() {
		return naturalNumber;
	}

	public void setNaturalNumber(int naturalNumber) {
		this.naturalNumber = naturalNumber;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
		this.date = date;
	}

	public int getForgien() {
		return forgien;
	}

	public void setForgien(int forgien) {
		this.forgien = forgien;
	}

	public int getOption() {
		return option;
	}

	public void setOption(int option) {
		this.option = option;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public int getWebSite() {
		return webSite;
	}

	public void setWebSite(int webSite) {
		this.webSite = webSite;
	}

	public int getPostCode() {
		return postCode;
	}

	public void setPostCode(int postCode) {
		this.postCode = postCode;
	}

	public int getRatio() {
		return ratio;
	}

	public void setRatio(int ratio) {
		this.ratio = ratio;
	}

	public int getEmail() {
		return email;
	}

	public void setEmail(int email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getDatetime() {
		return datetime;
	}

	public void setDatetime(int datetime) {
		this.datetime = datetime;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getCheckBox() {
		return checkBox;
	}

	public void setCheckBox(int checkBox) {
		this.checkBox = checkBox;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getFormula() {
		return formula;
	}

	public void setFormula(int formula) {
		this.formula = formula;
	}

	public int getBigColumn() {
		return bigColumn;
	}

	public void setBigColumn(int bigColumn) {
		this.bigColumn = bigColumn;
	}

	public int getAddress() {
		return address;
	}

	public void setAddress(int address) {
		this.address = address;
	}

}
