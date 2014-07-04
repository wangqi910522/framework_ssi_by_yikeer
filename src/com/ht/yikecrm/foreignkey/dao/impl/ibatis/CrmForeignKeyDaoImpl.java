package com.ht.yikecrm.foreignkey.dao.impl.ibatis;

import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ht.yikecrm.foreignkey.dao.CrmForeignKeyDao;

public class CrmForeignKeyDaoImpl extends SqlMapClientDaoSupport implements CrmForeignKeyDao {

	@Override
	public Map<String, Object> getByForeignKey(Long tenantID, Map<String, Object> clause)
			throws Exception {
		return (Map<String, Object>)getSqlMapClientTemplate().queryForObject("findForeignKey", clause);
	}
	
}
