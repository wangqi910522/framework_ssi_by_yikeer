package com.ht.yikecrm.util.memcached;

import java.util.Map;

import com.ht.yikecrm.util.memcached.model.MemCachedState;

import net.sf.json.JSONObject;

public class MemCachedDescription {

	private static final String description = "{" +
	
			"'pid':'memcache服务器进程ID'," +
			"'uptime':'服务器已运行秒数'," +
			"'time':'服务器当前Unix时间戳'," +
			"'version':'memcache版本'," +
			"'pointer_size':'操作系统指针大小(位数)'," +
			"'rusage_user':'进程累计用户时间'," +
			"'rusage_system':'进程累计系统时间'," +
			"'curr_connections':'当前连接数量'," +
			"'total_connections':'Memcached运行以来连接总数'," +
			"'connection_structures':'Memcached分配的连接结构数量'," +
			"'cmd_get':'get命令请求次数'," +
			"'cmd_set':'set命令请求次数'," +
			"'cmd_flush':'flush命令请求次数'," +
			"'get_hits':'get命令命中次数'," +
			"'get_misses':'get命令未命中次数'," +
			"'delete_misses':'delete命令未命中次数'," +
			"'delete_hits':'delete命令命中次数'," +
			"'incr_misses':'incr命令未命中次数'," +
			"'incr_hits':'incr命令命中次数'," +
			"'decr_misses':'decr命令未命中次数'," +
			"'decr_hits':'decr命令命中次数'," +
			"'cas_misses':'cas命令未命中次数'," +
			"'cas_hits':'cas命令命中次数'," +
			"'cas_badval':'使用擦拭次数'," +
			"'auth_cmds':'认证命令处理的次数'," +
			"'auth_errors':'认证失败数目'," +
			"'bytes_read':'读取总字节数'," +
			"'bytes_written':'发送总字节数'," +
			"'limit_maxbytes':'分配的内存总大小（字节）'," +
			"'accepting_conns':'服务器是否达到过最大连接（0/1）'," +
			"'listen_disabled_num':'失效的监听数'," +
			"'threads':'当前线程数'," +
			"'conn_yields':'连接操作主动放弃数目'," +
			"'bytes':'当前存储占用的字节数'," +
			"'curr_items':'当前存储的数据总数'," +
			"'total_items':'启动以来存储的数据总数'," +
			"'evictions':'LRU释放的对象数目'," +
			"'reclaimed':'已过期的数据条目来存储新数据的数目'," +
			
			"}";
	
	public static String getDescription (String key) {
		JSONObject json = JSONObject.fromObject(description);
		return json.getString(key);
	}
	
	public static void main(String[] args) {
		System.out.println(getDescription("reclaimed"));
	}
	
	public static MemCachedState convertEntity(Map<String, String>  memMap){
		MemCachedState mst = new MemCachedState();
		for (String key :memMap.keySet()) {
//			if(key.equals(anObject))
		}
		
		return null;
	}
}
