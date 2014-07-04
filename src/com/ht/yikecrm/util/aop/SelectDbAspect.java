package com.ht.yikecrm.util.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;

import com.ht.yikecrm.dao.base.ibatis.DbContextHolder;
import com.ht.yikecrm.util.MyWebUtils;
import com.ht.yikecrm.util.WebConstant;

public class SelectDbAspect {
	
	protected static final Log LOG = LogFactory.getLog(SelectDbAspect.class);
	
	public void comBeforeAOP(JoinPoint pjp) {
		LOG.debug("==============comBeforeAOP:"+pjp.getTarget().getClass().getName());
		DbContextHolder.setDbType("system");
	}

	public void comAfterAOP() {
		//System.out.println("comAfterAOP");
	}
	
	/**
	 * 随即选择数据库， 不要乱用
	 */
	public void testBeforeAOP() {
		long i=Math.round(Math.random()*3)+1;
		DbContextHolder.setDbType(Long.valueOf(i).toString());
	}

	/**
	 * ProceedingJoinPoint
	 */
//	public void crmBeforeAOP(JoinPoint pjp) {
//		LOG.debug("==============comBeforeAOP:"+pjp.getTarget().getClass().getName());
//		Object tentId=MyWebUtils.getSession(WebConstant.TENANT_MEMCACHE_TENANTID);
//		if(tentId==null){
//			tentId=new Long(1);
//			MyWebUtils.setSession(WebConstant.TENANT_MEMCACHE_TENANTID,tentId);
//		}
//		long tendId=Long.parseLong(tentId.toString());
//		DbContextHolder.setDbType(Long.valueOf((tendId-1)%4).toString());		
//	}
//	
	/**
	 * 
	 */
	public void crmAfterAOP() {
		//System.out.println("crmAfterAOP");
	}

	/**
	 * 
	 */
	public void throwsExecption() {
		//System.out.println("throwsExecption");
	}
}
