package com.ht.yikecrm.util;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ht.yikecrm.model.BaseSystemConfig;
import com.ht.yikecrm.util.memcached.MemCachedUtil;

public class PathUtil {
	
	public static final Log LOG = LogFactory.getLog(PathUtil.class);
	public static final String REALMNAME = "realmName";
	
	/**
	 * 根据传入的项目名，获取contextPath
	 * @param projectName
	 * @return
	 */
	public static String getContextPath(String projectName) throws Exception{
		BaseSystemConfig systemConfig = getSystemConfig(projectName);
		if (systemConfig != null) {
			return systemConfig.getAppUrl();
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 获取域名
	 * @return
	 * @throws Exception
	 */
	public static String getRealmName() throws Exception {
		return GlobalParameterUtil.getParameter(REALMNAME);
	}
	
	/**
	 * 获取域名
	 * @return
	 * @throws Exception
	 */
	public static String getBackRealmName() throws Exception {
		return GlobalParameterUtil.getBackParameter(REALMNAME);
	}
	
	/**
	 * 根据传入的项目名，获取该项目的配置对象(用的父类接收，只有appUrl和imgUrl属性)
	 * @param projectName
	 * @return
	 * @throws Exception
	 */
	public static BaseSystemConfig getSystemConfig(String projectName) throws Exception {
		BaseSystemConfig systemConfig = null;
		if (!StringUtils.isNullOrEmpty(projectName)) {
			String memCacheKey = getMemcacheKeyByProjectName(projectName);
			if (!StringUtils.isNullOrEmpty(memCacheKey)) {
				systemConfig = (BaseSystemConfig) MemCachedUtil.getFromCache(memCacheKey);
			}
			if (systemConfig != null) {
				return systemConfig;
			}
			String dbConfigKey = getDBConfigKeyByProjectName(projectName);
			Map<String, String> config = new HashMap<String, String>();
			if (!StringUtils.isNullOrEmpty(dbConfigKey)) {
				config = JSONObject.fromObject(GlobalParameterUtil.getParameter(dbConfigKey));
				if(projectName.trim().equalsIgnoreCase(ProjectName.BACK.value())){
					config = JSONObject.fromObject(GlobalParameterUtil.getBackParameter(dbConfigKey));
				}
				if (systemConfig == null) {
					systemConfig = constructSystemConfig(systemConfig, projectName,config);
				}
			}
		}
		return systemConfig;
	}
	
	public static BaseSystemConfig constructSystemConfig(BaseSystemConfig systemConfig, String projectName, Map<String, String> config)  {
		if (projectName.trim().equalsIgnoreCase(ProjectName.CRM.value)) {
			systemConfig = new com.ht.yikecrm.util.SystemConfig();
		} else if (projectName.trim().equalsIgnoreCase(ProjectName.AMC.value)) {
			systemConfig = new com.ht.yikecrm.util.amc.SystemConfig();
		} else if (projectName.trim().equalsIgnoreCase(ProjectName.AGENT.value)) {
			systemConfig = new com.ht.yikecrm.util.agent.SystemConfig();
		} else if (projectName.trim().equalsIgnoreCase(ProjectName.MESSAGE.value)) {
			systemConfig = new com.ht.yikecrm.util.message.SystemConfig();
		} else if (projectName.trim().equalsIgnoreCase(ProjectName.BACK.value)) {
			systemConfig = new com.ht.yikecrm.util.back.SystemConfig();
		}
		systemConfig.setAppUrl(config.get("appUrl"));
		systemConfig.setStaticSourceUrl(config.get("staticSourceUrl"));
		systemConfig.setDbConfig(config);
		systemConfig.setMemCacheKey(getMemcacheKeyByProjectName(projectName));
		return systemConfig;
	}
	
	public static void flushProjectSystemConfig(String projectName) {
		MemCachedUtil.flushEntry(getMemcacheKeyByProjectName(projectName));
	}
	
	/**
	 * 根据传入的项目名，判断项目是否存在
	 * @param projectName
	 * @return
	 */
	private static boolean isExistsProject(String projectName) {
		if (!StringUtils.isNullOrEmpty(projectName)) {
			for (ProjectName name : ProjectName.values()) {
				if (projectName.equalsIgnoreCase(name.value)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获取缓存key
	 * @param projectName
	 * @return
	 */
	private static String getMemcacheKeyByProjectName(String projectName){
		String memcacheKey = StringUtils.EMPTY;
		if (!StringUtils.isNullOrEmpty(projectName) && isExistsProject(projectName)) {
			memcacheKey = projectName.trim().toLowerCase() + "SystemConfig";
		}
		return memcacheKey;
	}
	
	/**
	 * 获取数据库配置key
	 * @param projectName
	 * @return
	 */
	private static String getDBConfigKeyByProjectName(String projectName){
		String configKey = StringUtils.EMPTY;
		if (!StringUtils.isNullOrEmpty(projectName) && isExistsProject(projectName)) {
			configKey = projectName.trim().toLowerCase() + "Config";
		}
		return configKey;
	}
	
	public enum ProjectName {
		CRM("crm"),
		IMS("ims"),
		AMC("amc"),
		BACK("back"),
		MESSAGE("message"),
		AGENT("agent");
		
		private final String value;

		private ProjectName(String value) {
			this.value = value;
		}
		
		public String value() {
			return this.value;
		}
	}
	
	
}
