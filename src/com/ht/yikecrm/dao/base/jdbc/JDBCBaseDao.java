package com.ht.yikecrm.dao.base.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.ht.yikecrm.model.Attribute;
import com.ht.yikecrm.model.Condition;
import com.ht.yikecrm.util.DojoPage;
import com.ht.yikecrm.util.JDBCUtil;
import com.ht.yikecrm.util.StringUtils;

public class JDBCBaseDao {
	
	private static final Log LOG = LogFactory.getLog(JDBCBaseDao.class);

	private JdbcTemplate jdbcTemplate;
	
	public List<Map<String,Object>> getListBySql(String sql) throws Exception{
		return jdbcTemplate.queryForList(sql);
	}

	public List<Map<String, Object>> getList(Map<String, Object> tableMap, String columnNames, List<Condition> conditionList,String orderByInfo,String orderdesc,DojoPage page,Boolean columnIsNumber) throws Exception{
		if (tableMap.containsKey("tenantID") && tableMap.get("tenantID") != null) {
			conditionList.add(new Condition("tenantID", "eq", tableMap.get("tenantID").toString(), 1));
		}
		String sql = JDBCUtil.getQuerySql(columnNames, tableMap, conditionList,orderByInfo,orderdesc,page,columnIsNumber);
		return jdbcTemplate.queryForList(sql);
	}
	
	public List<Map<String, Object>> getList(Map<String, Object> tableMap, String columnNames, List<Condition> conditionList) throws Exception{
		if (tableMap.containsKey("tenantID") && tableMap.get("tenantID") != null) {
			conditionList.add(new Condition("tenantID", "eq", tableMap.get("tenantID").toString(), 1));
		}
		String sql = JDBCUtil.getQuerySql(columnNames, tableMap, conditionList);
		return jdbcTemplate.queryForList(sql);
	}	
	
	public int getCount(Map<String, Object> tableMap, List<Condition> conditionList) throws Exception {
		if(tableMap.containsKey("tenantID") && tableMap.get("tenantID") != null){
			conditionList.add(new Condition("tenantID", "eq", tableMap.get("tenantID").toString(), 1));
		}
		String sql = JDBCUtil.getCountSql(tableMap, conditionList);
		return jdbcTemplate.queryForInt(sql);
	}
	
	/**
	 * 添加
	 * @param tableMap
	 * @param attributeList
	 * @param tenantID
	 * @return
	 * @throws Exception
	 */
	public boolean insert(Map<String, Object> tableMap, List<Attribute> attributeList) throws Exception{
		String sql = JDBCUtil.getSaveSql(tableMap, attributeList,1);
		int i = jdbcTemplate.update(sql);
		if (i <= 0){
			return false;
		}
		return true;
	} 
	
	/**
	 * 添加，返回自动增长列
	 * @param tableMap
	 * @param attributeList
	 * @param tenantID
	 * @return
	 * @throws Exception
	 */
	public int insert2(Map<String, Object> tableMap, List<Attribute> attributeList) throws Exception{
		final String sql = JDBCUtil.getSaveSql(tableMap, attributeList,1);
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection)
					throws SQLException {
				PreparedStatement ps = connection.prepareStatement(sql);
				return ps;
			}
		},keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	/**
	 * 批量添加
	 * @param tableMap
	 * @param attributeLists
	 * @return
	 * @throws Exception
	 */
	public boolean batchInsert(Map<String, Object> tableMap, List<List<Attribute>> attributeLists) throws Exception{
		final List<List<Attribute>> lists = attributeLists;
		String sqlString = JDBCUtil.getSaveSql(tableMap, attributeLists.get(0),2);
		jdbcTemplate.batchUpdate(sqlString, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int j) throws SQLException {
				//1:Integer 2:Long 3:BigDecimal 4:String 5:Date 6:Double
				for(int i = 0; i < lists.get(0).size(); i++){
					Integer type = lists.get(0).get(i).getAttrType();
					String value = lists.get(j).get(i).getAttrValue();
					try {
						switch (type) {
						case 1:
							if(StringUtils.isNullOrEmpty(value)){
								ps.setObject(i+1, StringUtils.EMPTY);
							} else {
								ps.setInt(i+1,Integer.parseInt(value));
							}
							break;
						case 2:
							if(StringUtils.isNullOrEmpty(value)){
								ps.setNull(i+1, Types.INTEGER);
							} else {
								ps.setLong(i+1,Long.parseLong(value));
							}
							break;
						case 3:
							if(StringUtils.isNullOrEmpty(value)){
								ps.setNull(i+1, Types.DECIMAL);
							} else {
								ps.setBigDecimal(i+1,new BigDecimal(value));
							}
							break;
						case 4:
							if(value == null){
								value = StringUtils.EMPTY;
							}
							ps.setString(i+1, value);
							break;
						case 5:
							if(StringUtils.isNullOrEmpty(value)){
								ps.setNull(i+1, Types.DATE);
							} else {
								//使用setDate构造方法丢失时分秒，所以用setString
//								Long mills = DateUtil.strToDatetime(value).getTime();
//								ps.setDate(i+1, new java.sql.Date(mills));
								ps.setString(i+1, value);
							}
							break;
						case 6:
							if(StringUtils.isNullOrEmpty(value)){
								ps.setNull(i+1, Types.DOUBLE);
							} else {
								ps.setDouble(i+1, Double.parseDouble(value));
							}
							break;
						default:
							break;
						}
					} catch (Exception e) {
						LOG.error("error:----------------JDBC批量插入错误!-----------" , e);
					}
					
				}
			}
			
			public int getBatchSize() {
				return lists.size();
			}
		});
		return true;
	}
	
	/**
	 * 修改
	 * @param tableMap
	 * @param attributeList
	 * @param conditionList
	 * @return
	 * @throws Exception
	 */
	public int update(Map<String, Object> tableMap, List<Attribute> attributeList, List<Condition> conditionList) throws Exception{
		String sql = JDBCUtil.getUpdateSql(tableMap, attributeList,conditionList);
		return jdbcTemplate.update(sql);
	}
	
	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	
}
