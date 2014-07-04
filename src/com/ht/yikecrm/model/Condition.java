package com.ht.yikecrm.model;

import com.ht.yikecrm.util.JDBCUtil;

public class Condition {

	/**
	 * 列名
	 */
	private String columnName;
	/**
	 * 操作符
	 */
	private String operation;
	/**
	 * 值
	 */
	private String columnValue;
	/**
	 * 列类型  1.数字 2.字符串 3.日期
	 */
	private Integer columnType;
	
	/**
	 * 类型：1.字段拼接 2：已拼接的字符串
	 */
	private Integer type;
	
	private String sqlWhere;
	
	public Condition(String sqlWhere) {
		super();
		this.sqlWhere = JDBCUtil.replaceSpecial(sqlWhere);
		this.type = 2;
	}
	
	public Condition(String columnName, String operation, String columnValue, Integer columnType) {
		super();
		this.columnName = columnName;
		this.operation = operation;
		this.columnValue = JDBCUtil.replaceSpecial(columnValue);
		this.columnType = columnType;
		this.type = 1;
	}
	
	public Condition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getOperation() {
		return operation;
	}

	public void setOperation(String operation) {
		this.operation = operation;
	}

	public String getColumnValue() {
		return columnValue;
	}

	public void setColumnValue(String columnValue) {
		this.columnValue = columnValue;
	}

	public Integer getColumnType() {
		return columnType;
	}

	public void setColumnType(Integer columnType) {
		this.columnType = columnType;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}
	
}
