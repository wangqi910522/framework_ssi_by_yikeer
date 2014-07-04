package com.ht.yikecrm.dao.base;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ht.yikecrm.model.IModel;

public interface BaseDao<T extends IModel> {
	/**
	 * 插入
	 * @param iModel
	 */
	public void insert(Map<String, Object> clause) throws Exception;
	
	/**
	 * 删除 
	 * @param clause 条件
	 * @return
	 * @throws Exception
	 */
	public int delete(Map<String, Object> clause) throws Exception;

	/**
	 * 更新
	 * @param clause  包含有model
	 * @return
	 * @throws Exception
	 */
	public int update(Map<String, Object> clause) throws Exception;

	/**
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public T getModel(Map<String, Object> clause) throws Exception;
	
	/**
	 * 分页使用	 
	 * @param beginIndex
	 * @param endIndex
	 * @param strWhere
	 * @param orderby
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<T> getList(Map<String, Object> clause) throws SQLException ;
	
	public int getCount(Map<String, Object> clause) throws SQLException;
}
