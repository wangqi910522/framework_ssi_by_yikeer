package com.ht.yikecrm.util;

import java.util.HashMap;
import java.util.Map;

import com.ht.yikecrm.parameters.services.ComParametersService;

public class  GlobalParameterUtil {

	private ComParametersService comParametersService;
	
	private  ComParametersService getService(){
		if(comParametersService==null){
			comParametersService= (ComParametersService)SpringUtil.getBean("comParametersService");
		}
		return comParametersService;
	}
	/**
	 * 取map
	 * @return
	 * @throws Exception
	 */
	public Map<String,String> getParameterMap()throws Exception{
		return getService().getParametersMap(new HashMap<String, Object>(), "crm");
	}
	
	public Map<String,String> getBackParameterMap() throws Exception {
		return getService().getParametersMap(new HashMap<String, Object>(), "back");
	}
	
	/**
	 * 根据key 取value
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String getParameter(String key) throws Exception{
		GlobalParameterUtil gpu=new GlobalParameterUtil();
		return gpu.getParameterMap().get(key);
	}
	
	public static String getBackParameter(String key) throws Exception{
		GlobalParameterUtil gpu=new GlobalParameterUtil();
		return gpu.getBackParameterMap().get(key);
	}
	
	/**
	 * 更新参数 ]]玩
	 * @param key 
	 * @param value
	 * @throws Exception
	 */
	public static void fullParameter(String key,String value) throws Exception{
		GlobalParameterUtil gpu=new GlobalParameterUtil();
		gpu.getService().update(key, value);
	}
	
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @throws Exception
	 */
	public static void addParameter(String key , String value) throws Exception{
		GlobalParameterUtil gpu=new GlobalParameterUtil();
		if(getParameter(key)!=null){
			throw new Exception("不能用重复的key..............");
		}else{
			gpu.getService().insert(key, value);
		}
	}
	/**
	 * 获取后台登陆ip
	 * @return
	 * @throws Exception
	 */
	public static String getBackLoginIp() throws Exception{
		GlobalParameterUtil gpu=new GlobalParameterUtil();
		return gpu.getService().getModel("backLoginIp").getPvalue();
		
	}
}
