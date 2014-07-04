package com.ht.yikecrm.parameters.services;

import java.util.Map;

import com.ht.yikecrm.parameters.model.ComParameters;

public interface ComParametersService {

	/**
	 * 根据key 取出 value
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public ComParameters getModel(String key)throws Exception;
	
	/**
	 * 取出所有的 系统公共参数
	 * @param clause
	 * @return
	 * @throws Exception
	 */
	
	public Map<String, String>  getParametersMap(Map< String,Object> clause, String projectName) throws Exception;
	/**
	 * 根据key 修改 value
	 * @param key
	 * @param vlaue
	 * @throws Exception
	 */
	public void  update (String key,String value) throws Exception;
	
	/**
	 * 添加数据
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public void insert (String key , String value) throws Exception;
	
	
}
