package com.ht.yikecrm.dao.base.router;

/**
 * @author
 */
public class DataSourceRouter {

	public static ThreadLocal<RouterStrategy> currentRouterStrategy = new ThreadLocal<RouterStrategy>();

	/**
	 * 设置MultiDataSource的路由策略
	 * 
	 * @param type
	 *            ： 路由策略类型
	 * @param routeFactor
	 *            ： 路由时参数
	 */
	public static void setRouterStrategy(String type, int routeFactor) {
		if (type == null) {
			throw new IllegalArgumentException("路由策略类型不能为空.");
		}
		RouterStrategy rs = currentRouterStrategy.get();
		if (rs == null) {
			rs = new RouterStrategy();
			currentRouterStrategy.set(rs);
		}
		rs.setType(type);
		rs.setRouteFactor(routeFactor);
	}

	/**
	 * 设置MultiDataSource的路由策略
	 * 
	 * @param type
	 *            ： 路由策略类型
	 * @param key
	 *            ：路由时的 key
	 */
	public static void setRouterStrategy(String type, String key) {
		if (type == null) {
			throw new IllegalArgumentException("路由策略类型不能为空.");
		}
		RouterStrategy rs = currentRouterStrategy.get();
		if (rs == null) {
			rs = new RouterStrategy();
			currentRouterStrategy.set(rs);
		}
		rs.setType(type);
		rs.setKey(key);
	}
}
