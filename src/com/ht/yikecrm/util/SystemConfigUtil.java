package com.ht.yikecrm.util;

import java.io.Serializable;
import java.util.Map;

import com.ht.yikecrm.util.PathUtil.ProjectName;
import com.ht.yikecrm.util.memcached.MemCachedUtil;
/**
 * 配置文件相关
 * @author HongToo
 *
 */
public class SystemConfigUtil implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1109938995188049188L;

	/**
	 * 获取系统配置信息
	 * 
	 * @return SystemConfig 对象
	 */
	public static SystemConfig getSystemConfig() throws Exception{
		SystemConfig systemConfig = (SystemConfig) PathUtil.getSystemConfig(ProjectName.CRM.value());
		if (systemConfig != null) {
			Map<String, String> config = systemConfig.getDbConfig();
			systemConfig.setFlowFile(config.get("flowFile"));
			MemCachedUtil.putInCache(systemConfig.getMemCacheKey(), systemConfig);
			return systemConfig;
		}
		return null;
	}
	
	public static SystemConfig getImsSystemConfig() throws Exception {
		SystemConfig systemConfig = (SystemConfig) PathUtil.getSystemConfig(ProjectName.IMS.value());
		if (systemConfig != null) {
			MemCachedUtil.putInCache(systemConfig.getMemCacheKey(), systemConfig);
			return systemConfig;
		}
		return null;
	}

	/**
	 * 刷新系统配置信息
	 * 
	 */
	public void flush() {
		PathUtil.flushProjectSystemConfig(ProjectName.CRM.value());
	}

}