package com.ht.yikecrm.util.memcached.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ht.yikecrm.util.memcached.dao.MemCachedParamterDao;
import com.ht.yikecrm.util.memcached.model.MemCachedState;

public class MemCachedParamterDaoImpl extends SqlMapClientDaoSupport implements MemCachedParamterDao {

	@Override
	public int delete(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(Map<String, Object> clause) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemCachedState> getList(Map clause) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public MemCachedState getModel(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Map clause) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public int update(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

}
