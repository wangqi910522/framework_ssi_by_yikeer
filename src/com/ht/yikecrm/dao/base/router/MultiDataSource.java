package com.ht.yikecrm.dao.base.router;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

/**
 * 复合多数据源
 * 
 * @author
 * 
 */
public class MultiDataSource implements DataSource {

	static Logger logger = Logger.getLogger(MultiDataSource.class);

	/**
	 * 当前线程对应的实际DataSource
	 */
	private ThreadLocal<DataSource> currentDataSourceHolder = new ThreadLocal<DataSource>();

	/**
	 * 使用Key-Value映射的DataSource
	 */
	private Map<String, DataSource> mappedDataSources;

	/**
	 * 使用横向切分的分布式DataSource
	 */
	private ArrayList<DataSource> clusterDataSources;

	public Map<String, DataSource> getMappedDataSources() {
		return mappedDataSources;
	}

	public void setMappedDataSources(Map<String, DataSource> mappedDataSources) {
		this.mappedDataSources = mappedDataSources;
	}

	public ArrayList<DataSource> getClusterDataSources() {
		return clusterDataSources;
	}

	public void setClusterDataSources(ArrayList<DataSource> clusterDataSources) {
		this.clusterDataSources = clusterDataSources;
	}

	/**
	 * 构造
	 */
	public MultiDataSource() {
		mappedDataSources = new HashMap<String, DataSource>(4);
		clusterDataSources = new ArrayList<DataSource>(4);
	}

	/**
	 * 数据库连接池初始化 该方法通常在web 应用启动时调用
	 */
	public void initialMultiDataSource() {
		for (DataSource ds : clusterDataSources) {
			if (ds != null) {
				Connection conn = null;
				try {
					conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						conn = null;
					}
				}
			}
		}
		Collection<DataSource> dsCollection = mappedDataSources.values();
		for (DataSource ds : dsCollection) {
			if (ds != null) {
				Connection conn = null;
				try {
					conn = ds.getConnection();
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					if (conn != null) {
						try {
							conn.close();
						} catch (SQLException e) {
							e.printStackTrace();
						}
						conn = null;
					}
				}
			}
		}
	}

	/**
	 * 获取当前线程绑定的DataSource
	 * 
	 * @return
	 */
	public DataSource getCurrentDataSource() {
		// 如果路由策略存在，且更新过，则根据路由算法选择新的DataSource
		RouterStrategy strategy = DataSourceRouter.currentRouterStrategy.get();
		if (strategy == null) {
			throw new IllegalArgumentException("未找到DataSource的路由策略.");
		}
		if (strategy != null && strategy.isRefresh()) {
			if (RouterStrategy.STRATEGY_TYPE_MAP.equals(strategy.getType())) {
				this.choiceMappedDataSources(strategy.getKey());

			} else if (RouterStrategy.STRATEGY_TYPE_CLUSTER.equals(strategy
					.getType())) {
				this.routeClusterDataSources(strategy.getRouteFactor());
			}
			strategy.setRefresh(false);
		}
		return currentDataSourceHolder.get();
	}

	/**
	 * 使用Key选择当前的数据源
	 * 
	 * @param key
	 */
	public void choiceMappedDataSources(String key) {
		DataSource ds = this.mappedDataSources.get(key);
		if (ds == null) {
			throw new IllegalStateException("Key映射DataSources并不存在!");
		}
		this.currentDataSourceHolder.set(ds);
	}

	/**
	 * 使用取模算法，在群集数据源中做路由选择
	 * 
	 * @param routeFactor
	 */
	public void routeClusterDataSources(int routeFactor) {
		int size = this.clusterDataSources.size();
		if (size == 0) {
			throw new IllegalStateException("DataSources的集群并不存在!");
		}
		int choosen = routeFactor % size;
		DataSource ds = this.clusterDataSources.get(choosen);
		if (ds == null) {
			throw new IllegalStateException("切换后 DataSources 为空!");
		}
		logger.debug("切换后 DataSources 编号." + choosen + " : " + ds.toString());
		this.currentDataSourceHolder.set(ds);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	public Connection getConnection() throws SQLException {
		DataSource ds = getCurrentDataSource();
		if (ds != null) {
			return ds.getConnection();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 * java.lang.String)
	 */
	public Connection getConnection(String username, String password)
			throws SQLException {
		if (getCurrentDataSource() != null) {
			return getCurrentDataSource().getConnection(username, password);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	public PrintWriter getLogWriter() throws SQLException {
		if (getCurrentDataSource() != null) {
			return getCurrentDataSource().getLogWriter();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	public int getLoginTimeout() throws SQLException {
		if (getCurrentDataSource() != null) {
			return getCurrentDataSource().getLoginTimeout();
		}
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	public void setLogWriter(PrintWriter out) throws SQLException {
		if (getCurrentDataSource() != null) {
			getCurrentDataSource().setLogWriter(out);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	public void setLoginTimeout(int seconds) throws SQLException {
		if (getCurrentDataSource() != null) {
			getCurrentDataSource().setLoginTimeout(seconds);
		}
	}

	/*
	 * (non-Javadoc) 该接口方法since 1.6 不是所有的DataSource都实现有这个方法
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	public boolean isWrapperFor(Class<?> iface) throws SQLException {

		// if(getCurrentDataSource() != null){
		// return getCurrentDataSource().isWrapperFor(iface);
		// }
		return false;
	}

	/*
	 * (non-Javadoc) 该接口方法since 1.6 不是所有的DataSource都实现有这个方法
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// if(getCurrentDataSource() != null){
		// return getCurrentDataSource().unwrap(iface);
		// }
		return null;
	}

	public java.util.logging.Logger getParentLogger()
			throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}
}