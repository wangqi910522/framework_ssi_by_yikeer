package com.ht.yikecrm.foreignkey.service;

import java.util.Map;

public interface CrmForeignKeyService {
	/**
	 * 查询外键信息
	 * @param clause
	 * @return
	 */
	public Map<String, Object> getByForeignKey(Long tenantID, Map<String, Object> clause) throws Exception;
}
