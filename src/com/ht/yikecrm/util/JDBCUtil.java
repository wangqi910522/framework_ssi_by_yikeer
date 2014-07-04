package com.ht.yikecrm.util;

import java.util.List;
import java.util.Map;

import com.ht.yikecrm.model.Attribute;
import com.ht.yikecrm.model.Condition;

public class JDBCUtil {
	
	/**
	 * 
	 */
	public static String getNOCrmBusinessesListSql(Map<String, Object> tableMap,String columnNames,String[] fromTableInfo,List<Condition> conditionList,DojoPage page){
		String [] alias = new String[fromTableInfo.length];
		//给 表取别名
		for (int i = 0; i < fromTableInfo.length; i++) {
			alias[i]=fromTableInfo[i]  + " as "  + ((char)(97+i));
		}
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		sqlString.append(" select ");
		//select....
		sqlString.append(space);
		sqlString.append(columnNames);
		sqlString.append(space);
		sqlString.append(" from ");
		sqlString.append(space);
		//根据 tableMap  里面经过daoAop处理后的结果获取库名
		String databaseName = "";
		String [] tableInfo = tableMap.get("tableName").toString().split("\\.");
		if(tableInfo!=null && tableInfo.length>1){
			databaseName = tableInfo[0];
		}
		//from.....
		for (String string : alias) {
			sqlString.append(databaseName+"."+string);
			sqlString.append(space);
		}
		//where....
		if(conditionList != null && conditionList.size() > 0){
			sqlString.append(" where ");
			sqlString.append(space);
			
			for (Condition cond : conditionList) {
				sqlString.append(cond.getSqlWhere());
				sqlString.append("and");
			}
			
			//sqlString.append(getSqlStringByCondition(conditionList));
			
			sqlString.append(space);
		}
		if(page!=null){
			sqlString.append(space).append(" limit ").append(space);
			sqlString.append(page.getStart()).append(",").append(page.getPageSize());
			sqlString.append(space);
		}
		return sqlString.toString();
	}

	
	/**
	 *  获取查询SQL语句
	 * @param columnNames		显示列
	 * @param tableMap			库名(aop),表名,tenantID
	 * @param conditionList		条件对象集合
	 * @param orderByInfo		排序字符串
	 * @param orderdesc			排序顺序
	 * @param page				分页对象
	 * @return
	 * @throws Exception
	 */
	public static String getQuerySql(String columnNames,Map<String, Object> tableMap,List<Condition> conditionList,String orderByInfo,String orderdesc,DojoPage page,Boolean columnIsNumber) throws Exception{
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		sqlString.append(getQuerySql(columnNames, tableMap, conditionList));
		if(StringUtils.isNullOrEmpty(orderdesc)){
			orderdesc = " desc ";
		}
		sqlString.append(space).append(" order by ").append(space);
		if(StringUtils.isNullOrEmpty(orderByInfo)){
			String [] tableInfo = tableMap.get("tableName").toString().split("\\.");
			if(tableInfo.length>1){
				if(tableInfo[1].equals("crm_customer")){//客户
					sqlString.append(" hotCustomers ");
				}else if(tableInfo[1].equals("crm_linkman")){
					sqlString.append(" hotlinkman ");
				}else if(tableInfo[1].equals("crm_salesLead")){
					sqlString.append(" hotsaleslead ");
				}else{
					sqlString.append(" modifyDate desc ,createDate ");
				}
			}
		} else {
//			if(orderByInfo.trim().indexOf("column")<0){//不是自定义字段
//				sqlString.append(orderByInfo);
//			}else{//自定义字段
				if(columnIsNumber!=null && columnIsNumber){//自定义字段并且该租户 该字段为 数字字段
					sqlString.append(" cast("+orderByInfo.trim()+" as decimal(36,2)) ");
				}else{
					sqlString.append(orderByInfo);
				}
//			}
//			orderByInfo = URLDecoder.decode(orderByInfo,"utf8");
//			if(orderByInfo.split(",").length>1){
//				sqlString.append(orderByInfo.replaceAll(",",space+orderdesc+space));
//			}else{
			//	sqlString.append(orderByInfo);
//			}
		}
		sqlString.append(space).append(orderdesc).append(space);
		if(page!=null){
			sqlString.append(space).append(" limit ").append(space);
			sqlString.append(page.getStart()).append(",").append(page.getPageSize());
			sqlString.append(space);
		}
		return sqlString.toString();
	}
	
	public static String getQuerySql(String columnNames,Map<String, Object> tableMap,List<Condition> conditionList) throws Exception {
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		sqlString.append(" select ");
		sqlString.append(space);
		sqlString.append(columnNames);
		sqlString.append(space);
		sqlString.append(" from ");
		sqlString.append(space);
		sqlString.append(tableMap.get("tableName").toString());
		sqlString.append(space);
		if(conditionList != null && conditionList.size() > 0){
			sqlString.append(" where ");
			sqlString.append(space);
			sqlString.append(getSqlStringByCondition(conditionList));
			sqlString.append(space);
		}
		return sqlString.toString();
	}
	
	public static String getCountSql(Map<String, Object> tableMap, List<Condition> conditionList) throws Exception{
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		sqlString.append(" select count(id) from ").append(space);
		sqlString.append(tableMap.get("tableName").toString()).append(space);
		if(conditionList != null && conditionList.size() > 0){
			sqlString.append(" where ").append(space);
			sqlString.append(getSqlStringByCondition(conditionList));
		}
		return sqlString.toString();
	}
	
	
	/**
	 * 通过已经拼接好的SQL条件，生成一个条件对象
	 * @param sqlString
	 * @return
	 */
	public static Condition addSqlWhere(String sqlString){
		return new Condition(sqlString);
	}
	
	/**
	 * 获取添加SQL语句
	 * @param tableMap			库名(aop),表名,tenantID
	 * @param attributeList		
	 * @param type				1:普通拼接 2:批量拼接(使用占位符)
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public static String getSaveSql(Map<String, Object> tableMap, List<Attribute> attributeList, Integer type) throws Exception{
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		if(attributeList != null || attributeList.size() > 0){
			String columns = StringUtils.EMPTY;
			String values = StringUtils.EMPTY;
			for(int i = 0; i < attributeList.size(); i++){
				Attribute attr = attributeList.get(i);
				columns += attr.getAttrName() + ",";
				Integer attrType = attr.getAttrType();
				if(type == 1){
					if(attrType == 1 || attrType == 2 || attrType == 3 || attrType == 6){//Integer , Long float, BigDecimal, double
						if(StringUtils.isNullOrEmpty(attr.getAttrValue())){
							values += "'',";
						}else{
							values += attr.getAttrValue() + ",";
						}
					} else if(attrType == 4 || attrType == 5){//String ,Date
						if(StringUtils.isNullOrEmpty(attr.getAttrValue()) && attrType == 5) {
							values += "null,";
						} else {
							values += "'" + attr.getAttrValue() + "',";
						}
						
					} 	
				} else if(type == 2){
					values += "?,";
				}
			}
			if(!StringUtils.isNullOrEmpty(columns) && !StringUtils.isNullOrEmpty(values)){
				columns = columns.substring(0,columns.length()-1);
				values = values.substring(0,values.length()-1);
			}
			sqlString.append("insert into ").append(space);
			sqlString.append(tableMap.get("tableName").toString()).append(space);
			sqlString.append(" ( ").append(space);
			sqlString.append(columns).append(space);
			sqlString.append(" ) ").append(space);
			sqlString.append(" values ").append(space);
			sqlString.append(" ( ").append(space);
			sqlString.append(values).append(space);
			sqlString.append(" ) ").append(space);
			
		}
		
		return sqlString.toString();
	}
	
	/**
	 * 获取修改SQL语句
	 * @param tableMap
	 * @param attributeList
	 * @param conditionList
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("null")
	public static String getUpdateSql(Map<String, Object> tableMap, List<Attribute> attributeList, List<Condition> conditionList) throws Exception{
		StringBuffer sqlString = new StringBuffer();
		String space = " ";
		if(attributeList != null || attributeList.size() > 0){
			sqlString.append("update ").append(space).append(tableMap.get("tableName").toString());
			sqlString.append(space).append(" set ").append(space);
			for(int i = 0; i < attributeList.size(); i++){
				Attribute attr = attributeList.get(i);
				String name = attr.getAttrName();
				if (name.equals("id") || name.equals("ID")) {
					continue;
				}
				Integer type = attr.getAttrType();
				String value = attr.getAttrValue();
				if(type == 4 || type == 5){//String ,Date
					if(type == 5 && StringUtils.isNullOrEmpty(value)){
						sqlString.append(name+" = "+null).append(" ,");
					}else{
						sqlString.append(name+" = '"+value+"'").append(" ,");
					}
				} else {
					if (StringUtils.isNullOrEmpty(value)) {
						value = "''";
					}
					sqlString.append(name+" = "+value).append(" ,");
				}
			}
			sqlString = new StringBuffer(sqlString.substring(0, sqlString.length()-1));
			if(conditionList != null && conditionList.size() > 0){
				sqlString.append(space).append(" where ");
				sqlString.append(getSqlStringByCondition(conditionList));
			}
		}
		
		return sqlString.toString();
	}
	
	public static void initTableMap(String tableName, Long tenantID, Map<String, Object> tableMap) throws Exception{
		tableMap.clear();
		tableMap.put("tenantID", tenantID);
		tableMap.put("tableName", tableName);
	}
	
	public static void initTableMap(String tableName, String database, Map<String, Object> tableMap) throws Exception{
		tableMap.clear();
		tableMap.put("database", database);
		tableMap.put("tableName", tableName);
	}
	
	/**
	 * 根据条件集合拼接条件SQL
	 * @param conditionList
	 * @return
	 * @throws Exception
	 */
	private static String getSqlStringByCondition (List<Condition> conditionList) throws Exception {
		StringBuffer sqlWhere = new StringBuffer();
		String space = " ";
		for(int i = 0; i < conditionList.size(); i++){
			Condition condition = conditionList.get(i);
			//获取类型 1:根据condition对象拼接条件 2：直接加上已拼接好的条件
			Integer type = condition.getType();
			if(type == 2){
				sqlWhere.append(revertSpecial(condition.getSqlWhere()));
			}else if(type == 1){
				//获取列名
				String column = condition.getColumnName();
				//获取操作符
				String operator = condition.getOperation();
				//获取列类型 1：数字,2:字符串,3:日期
				Integer ctype = condition.getColumnType();
				//获取比较值
				String val =  condition.getColumnValue();
//				val = JDBCUtil.replaceSpecial(val);
				//临时保存条件str
				String rString = StringUtils.EMPTY;
				if(ctype==1&&(StringUtils.isNullOrEmpty(val)||StringUtils.isNullOrEmpty(val.trim()))){
					val="''";
				}else if(StringUtils.isNullOrEmpty(val)){
					val="";
				}
				if (StringUtils.isNotEmpty(operator)) {
					if (operator.equals("eq")) {//等于
						if(ctype == 1) {
							rString = column + space + " = " + space + val + space;
						} else if(ctype == 2){
							rString = column + space + " = " + space + "'" + val + "'" + space;
						}else if(ctype == 3){
							rString = " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " = " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("ne")) {//不等于
						if(ctype == 1) {
							rString = column + space + " != " + space + val + space;
						} else if(ctype == 2){
							rString = column + space + " != " + space + "'" + val + "'" + space;
						}else if(ctype == 3){
							rString =" DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " != " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("ne_en")){//不等于 或 为空
						if(ctype == 1) {
							rString = " ( " + column + space + " != " + space + val + space + " or " + column + " is null )";
						} else if(ctype == 2){
							rString = " ( " + column + space + " != " + space + "'" + val + "'" + space + " or " + column + " is null )";
						}else if(ctype == 3){
							rString = " ( " + " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " != " + space + "'" + val + "'" + space + " or " + column + " is null )";
						} 
					} else if (operator.equals("ge")) {//大于等于
						if(ctype == 1) {
							rString = column + space + " >= " + space + val + space;
						} else if(ctype == 3) {
							rString = " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " >= " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("gt")) {//大于
						if(ctype == 1) {
							rString = column + space + " > " + space + val + space;
						} else if(ctype == 3){
							rString = " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " > " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("le")) {//小于等于
						if(ctype == 1) {
							rString = column + space + " <= " + space + val + space;
						} else if(ctype == 3){
							rString = " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " <= " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("lt")) {//小于
						if(ctype == 1) {
							rString = column + space + " < " + space + val + space;
						} else if(ctype == 3) {
							rString = " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " < " + space + "'" + val + "'" + space;
						}
					} else if (operator.equals("sl") && ctype == 2) {//起始字符
						rString = column + space + " like '" + val + "%' ";
					} else if (operator.equals("cl") && ctype == 2) {//包含
						rString = column + space + " like '%" + val + "%' ";
					} else if (operator.equals("nl") && ctype == 2) {//不包含
						rString = " ( " + column + " not like '%" + val + "%' or " + column + " is null )" ;
					} else if (operator.equals("in")){
						val = revertSpecial(val);
						rString = column + space + " in " + space + " ( " + val + " ) " + space;
					} else if(operator.equals("noin")){
						val = revertSpecial(val);
						rString = column + space + " not in " + space + " ( " + val + " ) " + space;
					}else if (operator.equals("en_empty")) {
						if (ctype == 2){
							rString = "(" + column + space + " is null or " + column + " = '' ) ";
						} else if(ctype == 3){
							rString = "(" + " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " is null or " + column + " = '' ) ";
						} else {
							rString = column + space + " is null ";
						}
					}else if(operator.equals("ne_nen")){
						if(ctype == 1) {
							rString = " ( " + column + space + " != " + space + "''" + space + " and " + column + " is not null )";
						} else if(ctype == 2){
							rString = " ( " + column + space + " != " + space + "''" + space + " and " + column + " is not null )";
						}else if(ctype == 3){
							rString = " ( " + " DATE_FORMAT("+column+", '%Y-%m-%d')" + space + " != " + space + "''" + space + " and " + column + " is not null )";
						} 
					}
					sqlWhere.append(rString);
				}
			}
			sqlWhere.append(space);
			sqlWhere.append("   and ");
			sqlWhere.append(space);
		}
		return sqlWhere.substring(0, sqlWhere.length() - 5);
	}
	
	public static String replaceSpecial(String columnVal){
		if(!StringUtils.isNullOrEmpty(columnVal)){
			columnVal = columnVal.replaceAll("\\\\", "\\\\\\\\");
			columnVal = columnVal.replaceAll("'","''");
			columnVal = columnVal.replaceAll("\"","\\\\\"");
		}
		return columnVal;
	}
	
	public static String revertSpecial(String columnVal){
		if(!StringUtils.isNullOrEmpty(columnVal)){
			columnVal = columnVal.replaceAll("\\\\\"","\"");
			columnVal = columnVal.replaceAll("''", "'");
			columnVal = columnVal.replaceAll("\\\\\\\\", "\\\\");
		}
		return columnVal;
	}
}
