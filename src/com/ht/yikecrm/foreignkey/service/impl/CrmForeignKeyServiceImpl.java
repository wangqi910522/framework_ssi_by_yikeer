package com.ht.yikecrm.foreignkey.service.impl;

import java.util.Map;

import com.ht.yikecrm.foreignkey.dao.CrmForeignKeyDao;
import com.ht.yikecrm.foreignkey.service.CrmForeignKeyService;

public class CrmForeignKeyServiceImpl implements CrmForeignKeyService {

	private CrmForeignKeyDao crmForeignKeyDao;
	
	public CrmForeignKeyDao getCrmForeignKeyDao() {
		return crmForeignKeyDao;
	}

	public void setCrmForeignKeyDao(CrmForeignKeyDao crmForeignKeyDao) {
		this.crmForeignKeyDao = crmForeignKeyDao;
	}

	@Override
	public Map<String, Object> getByForeignKey(Long tenantID, Map<String, Object> clause)
			throws Exception {
		if (tenantID == null) {
			throw new Exception("租户ID不能为空");
		}
		clause.put("tenantID", tenantID);
		return crmForeignKeyDao.getByForeignKey(tenantID, clause);
	}
	
}
