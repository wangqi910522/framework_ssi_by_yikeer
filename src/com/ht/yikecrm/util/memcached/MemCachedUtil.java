package com.ht.yikecrm.util.memcached;

import java.util.Date;

import com.danga.MemCached.MemCachedClient;
import com.ht.yikecrm.util.MyWebUtils;
import com.ht.yikecrm.util.SpringContextHolder;
import com.schooner.MemCached.MemcachedItem;

public class MemCachedUtil {
	
	public static MemCachedClient getMemCachedClient() {
		return SpringContextHolder.getBean("memcachedClient");
	}

	/**
	 * 根据Key读取缓存
	 * 
	 * @return 缓存对象
	 */
	public static Object getFromCache(String key) {
		Object object = null;
		MemCachedClient memCachedClient = getMemCachedClient();
		object = memCachedClient.get(key);
		return object;
	}

	/**
	 * 加入对象到缓存
	 * 
	 */
	public static void putInCache(String key, Object object) {
		if (object != null) {
			MemCachedClient memCachedClient = getMemCachedClient();
			memCachedClient.set(key, object);
		}
	}

	/**
	 * 加入对象到缓存
	 * 
	 * @param key
	 * @param object
	 * @param expiry
	 */
	public static void putInCache(String key, Object object, Long expiry) {
		if (object != null) {
			MemCachedClient memCachedClient = getMemCachedClient();
			memCachedClient.set(key, object, new Date(expiry));
		}
	}

	/**
	 * 刷新删除对象到缓存
	 * 
	 * @param key
	 */
	public static void flushEntry(String key) {
		MemCachedClient memCachedClient = getMemCachedClient();
		memCachedClient.delete(key);
	}
	/**
	 * 清空缓存
	 * 
	 * @param key
	 */
	public static void flushAll() {
		MemCachedClient memCachedClient = getMemCachedClient();
		memCachedClient.flushAll();
	}
	
	public static MemcachedItem gets(String key){
		MemCachedClient memCachedClient = getMemCachedClient();
		return memCachedClient.gets(key);
	}
	//判断给定的 memcache 有没有被改变
	public static boolean cas(String key, Object value, long unique) {
		// TODO Auto-generated method stub
		return getMemCachedClient().cas(key, value, unique);
	}
	
	//获得memCache的版本号；
	public static long getCasUnique(String key){
		return getMemCachedClient().gets(key).getCasUnique();
	}
	//存储key的计数器，值为count。
	public static boolean storeCounter(String key, long count){
		return getMemCachedClient().storeCounter(key, count);
	}
	
	//在计数器不存在的时候不保存任何值，返回-1    增加
	public static long incr(String key, long inc){
		return getMemCachedClient().incr(key, inc);
	} 
	//在计数器不存在的时候不保存任何值，返回-1       减少
	public static long decr(String key, long inc){
		return getMemCachedClient().decr(key, inc);
	}
	//获取key的计数器，如果不存在返回-1
	public static long getCounter(String key){
		return getMemCachedClient().getCounter(key);
	}

	public static void test(){
		putInCache("123","LOVE");
//		System.out.println(gets("123").getCasUnique()+"      "+gets("123").getValue().toString()+ "   123");
//		long unique =getCasUnique("123");
//		System.out.println(getCasUnique("123"));
//		System.out.println(cas("123", gets("123").getValue(), unique));
//		putInCache("123","LOVE");
//		if(cas("123", ".", unique)){
//			MyWebUtils.ajaxText("success");
//			
//		}else {
//			MyWebUtils.ajaxText("failure");
//		}
		
		System.out.println(getFromCache("123")+"\n");
		boolean flag=storeCounter("123", 0);
		System.out.println(getFromCache("123"));
		System.out.println(flag);
//		System.out.println(decr("123",9L));
		System.out.println(getCounter("123"));
//		System.out.println(getMemCachedClient().addOrDecr("123"));
		if(getCounter("123")!=-1){
			MyWebUtils.ajaxText("success");
		}else {
			MyWebUtils.ajaxText("failure");
		}
	}
}
