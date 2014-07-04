package com.ht.yikecrm.parameters.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.support.DaoSupport;

import com.danga.MemCached.MemCachedClient;
import com.ht.yikecrm.parameters.dao.ComParametersDao;
import com.ht.yikecrm.parameters.model.ComParameters;
import com.ht.yikecrm.parameters.services.ComParametersService;
import com.ht.yikecrm.util.WebConstant;
import com.ht.yikecrm.util.memcached.MemCachedUtil;

public class ComParametersServiceImpl implements ComParametersService {

	private ComParametersDao comParametersDao;
	
	
	public void setComParametersDao(ComParametersDao comParametersDao) {
		this.comParametersDao = comParametersDao;
	}

	@Override
	public ComParameters getModel(String key) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> clause=new HashMap<String, Object>();
		clause.put("pkey",key);
		return comParametersDao.getModel(clause);
	}

	@Override
	public Map<String, String> getParametersMap(Map<String, Object> clause, String projectName)
			throws Exception {
		// TODO Auto-generated method stub
		Map<String,String> parametersMap = new HashMap<String, String>();
		String key = WebConstant.PARAMETER_MEMCACHE_MAP;
		if(projectName.equals("back")){
			key = WebConstant.BACK_PARAMETER_MEMCACHE_MAP;	
		} 
		parametersMap = (Map<String,String>)MemCachedUtil.getFromCache(key);
		 if(parametersMap==null){
			 List<ComParameters > list=comParametersDao.getList(clause);
			 parametersMap=new HashMap<String, String>();
			 for (ComParameters comParameters : list) {
				parametersMap.put(comParameters.getPkey(), comParameters.getPvalue());
			} 
			 MemCachedUtil.putInCache(key, parametersMap);
		 }
		 return parametersMap;
	}

	@Override
	public void update(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		Map<String,Object> clause=new HashMap<String, Object>();
		clause.put("pkey",key);
		Map<String,String> filedMap= new HashMap<String, String>();
		filedMap.put("pvalue", value);
		clause.put("filed",filedMap);
		comParametersDao.update(clause);
		MemCachedUtil.flushEntry(WebConstant.PARAMETER_MEMCACHE_MAP);
	}

	@Override
	public void insert(String key, String value) throws Exception {
		// TODO Auto-generated method stub
		ComParameters par=new ComParameters();
		par.setPkey(key);
		par.setPvalue(value);
		Map clause = new HashMap();
		clause.put("model", par);
		comParametersDao.insert(clause);
		MemCachedUtil.flushEntry(WebConstant.PARAMETER_MEMCACHE_MAP);
	}
}
