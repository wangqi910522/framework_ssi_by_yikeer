package com.ht.yikecrm.foreignkey.dao;

import java.util.Map;

public interface CrmForeignKeyDao {
	/**
	 * 查询外键信息
	 * @param clause
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getByForeignKey(Long tenantID, Map<String, Object> clause) throws Exception;
}
