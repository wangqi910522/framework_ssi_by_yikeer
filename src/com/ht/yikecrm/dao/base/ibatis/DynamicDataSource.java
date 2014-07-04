package com.ht.yikecrm.dao.base.ibatis;

import java.sql.SQLFeatureNotSupportedException;

import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态选择获取数据源
 * @author zb
 *
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	static Logger log = Logger.getLogger("DynamicDataSource");
	
	protected Object determineCurrentLookupKey() {
		return DbContextHolder.getDbType();
	}

	public java.util.logging.Logger getParentLogger()
			throws SQLFeatureNotSupportedException {
		return null;
	}
	
}
