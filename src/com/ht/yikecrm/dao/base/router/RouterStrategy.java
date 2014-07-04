package com.ht.yikecrm.dao.base.router;

/**
 * 数据源路由策略
 * 
 * @author
 */
public class RouterStrategy {

	/**
	 * 路由策略类型-Map的key类型
	 */
	public static final String STRATEGY_TYPE_MAP = "MAP";
	/**
	 * 路由策略类型-算法取模CLUSTER类型
	 */
	public static final String STRATEGY_TYPE_CLUSTER = "CLUSTER";
	/**
	 * 可选值 "MAP" ， "CLUSTER"
	 * 
	 * MAP ：根据key从DataSourceMap中选中群集 | CLUSTER:根据routeFactor参数，通过算法获取群集
	 */
	private String type;
	/**
	 * "MAP" 类型路由 时的key
	 */
	private String key;
	/**
	 * "CLUSTER" 类型路由时的参数
	 */
	private int routeFactor;
	/**
	 * True:表示RouterStrategy更新过, False:表示没有更新
	 */
	private boolean refresh;

	public String getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 *            路由类型 【如果路由类型相同，则不更新路由策略】
	 */
	public void setType(String type) {
		if (this.type != null && !this.type.equals(type)) {
			this.type = type;
			this.refresh = true;
		} else if (this.type == null && type != null) {
			this.type = type;
			this.refresh = true;
		}
	}

	public String getKey() {
		return key;
	}

	/**
	 * 
	 * @param key
	 *            :路由Key【如果路由Key相同，则不更新路由策略】
	 */
	public void setKey(String key) {
		if (this.key != null && !this.key.equals(key)) {
			this.key = key;
			this.refresh = true;
		} else if (this.key == null && key != null) {
			this.key = key;
			this.refresh = true;
		}
	}

	public int getRouteFactor() {
		return routeFactor;
	}

	/**
	 * 
	 * @param routeFactor
	 *            ：路由参数【如果路由参数相同，则不更新路由策略】
	 */
	public void setRouteFactor(int routeFactor) {
		if (this.routeFactor != routeFactor) {
			this.routeFactor = routeFactor;
			this.refresh = true;
		}
	}

	public boolean isRefresh() {
		return refresh;
	}

	/**
	 * 
	 * @param refresh
	 *            :路由策略更新标志【True：更新,False:未更新】
	 */
	public void setRefresh(boolean refresh) {
		this.refresh = refresh;
	}
}