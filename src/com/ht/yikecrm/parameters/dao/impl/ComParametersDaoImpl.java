package com.ht.yikecrm.parameters.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ht.yikecrm.parameters.dao.ComParametersDao;
import com.ht.yikecrm.parameters.model.ComParameters;

public class ComParametersDaoImpl extends SqlMapClientDaoSupport implements ComParametersDao  {

	@Override
	public int delete(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getCount(Map clause) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ComParameters> getList(Map clause) throws SQLException {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().queryForList("findParametersAll",clause);
	}

	@Override
	public ComParameters getModel(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return(ComParameters)getSqlMapClientTemplate().queryForObject("findParametersAll",clause);
	}

	@Override
	public void insert(Map clause) throws Exception {
		// TODO Auto-generated method stub
		getSqlMapClientTemplate().insert("insertParameters",clause);
	}

	@Override
	public int update(Map clause) throws Exception {
		// TODO Auto-generated method stub
		return getSqlMapClientTemplate().update("updateParameter",clause);
	}

}
