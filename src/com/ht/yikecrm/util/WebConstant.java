package com.ht.yikecrm.util;


/**
 * 全局缓存静态变量
 * @author HongToo
 *
 */
public interface WebConstant {
	// ===============================================================用户会话标识
	
	/**
	 * 用户标识--comuser
	 */
	public final static String USER_MEMCACHE_COMUSER="currComUser";
	/**
	 * 后台用户标识--backOperator
	 */
	public final static String OPERATOR_MEMCACHE_OPERATOR="backOperator";
	/**
	 * 后台用户标识--backOperatorID
	 */
	public final static String OPERATOR_MEMCACHE_OPERATORID="backOperatorID";
	/**
	 * 用户标识--crmuser
	 */
	public final static String USER_MEMCACHE_CRMUSER="currCrmUser";
	/**
	 * 全局-普通用户ID标识
	 */
	public final static String USER_SESSION_CURUSERID = "currentUserID";

	/**
	 * 全局-普通用户角色标识
	 */
	public final static String ROLE_MEMCACHE_CURUSER_CRMROLE = "currentUserCrmRole";
	
	/**
	 * 全局-普通用户角色标示
	 */
	public final static String ROLE_MEMCACHE_CURUSER_IMSROLE = "currentUserImsRole";


	/**
	 * 全局-全局-租户标识---只能在注册的时候用来存tenantid和在选库的时候取tenantid  不能做其他用途
	 * 
	 */
	public final static String TENANT_MEMCACHE_TENANTID_ONLY_FRO_REG = "regeditTenantID";
	
	/**
	 * 全局-租户级别 ---是否正在导入
	 * 
	 */
	
	public final static String TENANT_MEMCACHE_ISIMPORT = "tenantMemcacheIsImport";
	
	/**
	 * 租户下正在导入的用户的姓名
	 */
	public final static String TENANT_MEMCACHE_IMPORTUSER = "tenantMemcacheImportUser";

	/**
	 * 全局-用户-模块权限
	 */
	public final static String ROLE_MEMCACHE_CURRENTROLERELATIONMAP = "currentRoleRelationMap";
	
	// ===============================================================租户MemCache标识
	
	/**
	 * 全局-用户-头部菜单
	 */
	public final static String TENANT_MC_CURRENTTOPMENULIST = "currentTopMenuList";
	
	/**
	 * 全局-角色-菜单
	 */
	public final static String ROLE_MC_CURRROLE_LIST = "currentRoleMenuList";
	

	/**
	 * 全局-缓存-版本list
	 */
	public final static String LICENSE_MEMCACHE_LICENSE_LIST = "global_mc_license_list";
	/**
	 * 全局-缓存-版本map--tag
	 */
	public final static String LICENSE_MEMCACHE_LICENSE_MAPTAG = "global_mc_license_maptag";
	/**
	 * 全局-缓存-版本map--id
	 */
	public final static String LICENSE_MEMCACHE_LICENSE_MAPID = "global_mc_license_mapID";
	
	// ===============================================================租户MemCache标识
	/**
	 * 租户-缓存-租户信息
	 */
	public final static String TENANT_MEMCACHE_TENANT = "tenant_mc_tenant";
	/**
	 * 全局-缓存-租户自定义模块MAP
	 */
	public final static String TENANT_MEMCACHE_CUSTOMMODUL_ID_MAP = "tenant_mc_customeModul_id_map";
	
	/**
	 * 全局-缓存-租户自定义模块---MAP-id
	 */
	public final static String TENANT_MEMCACHE_CUSTOMMODUL_MAPID = "tenant_mc_customeModul_mapid";
	/**
	 * 全局-缓存-租户自定义模块---MAP-tag
	 */
	public final static String TENANT_MEMCACHE_CUSTOMMODUL_MAPTAG = "tenant_mc_customeModul_maptag";
	
	/**
	 * 全局-缓存-租户自定义模块--list
	 */
	public final static String TENANT_MEMCACHE_CUSTOMMODUL_LIST = "tenant_mc_customeModul_list";
	/**
	 * 全局-缓存-租户模块字段自定义MAP....连接租户id
	 */
	public final static String TENANT_MEMCACHE_MMODULCOLUMN_LIST = "tenant_mc_modulColumn_list";
	
	/**
	 * 全局-缓存-租户模块字段自定义选项信息MAP....连接租户id
	 */
	public final static String TENANT_MEMCACHE_MMODULCOLUMNCHILD_MAP = "tenant_mc_modulColumnChild_map";
	
	/**
	 * 全局-缓存-租户信息改变状态....连接租户id
	 */
	public final static String TENANT_MEMCACHE_CHANGESTATE_MAP = "tenant_mc_changeState_map"; 
	// ===============================================================全局标识
	/**
	 * 全局-标识-false-0
	 */
	public final static int GLOBAL_FALSE = 0;

	/**
	 * 全局-标识-true-1
	 */
	public final static int GLOBAL_TRUE = 1;
	
	
	public final static int TENANT_ACTIVE =0;
	
	/** 当前用户-缓存-统计 */
	public final static String USER_MEMCACHE_COUNT = "user_mc_count";
	
	/** 当前用户-缓存-是否最大化*/
	public final static String USER_MEMCACHE_MAXVIEW = "user_mc_maxview";
	
	/**
	 * 全局-缓存-常用参数
	 */
	public final static String PARAMETER_MEMCACHE_MAP = "global_mc_parameter_map";
	
	public final static String BACK_PARAMETER_MEMCACHE_MAP = "back_global_mc_parameter_map";

	
	public final static String AGENT_SESSION_AGENTID = "agent_session_agentID";
	
	public final static String AGENT_MEMCACHE_AGENT = "agent_session_loginAgent";
	
	
	public final static String TENANT_WORKREPORTSET = "tenant_workreportSet_info";
	
	/**
	 * 角色下属id,允许父级控制
	 */
	public final static String ROLE_CHILDREN_CONTROL_TRUE="role_children_control_true";
	
	/**
	 * 角色下属id,所有的
	 */
	public final static String ROLE_CHILDREN_CONTROL_FALSE="role_children_control_false";
	
	/**
	 * 角色下属id 可编辑的
	 */
	public final static String ROLE_CHILDREN_CONTROLEEDIT = "role_children_controleedit";
	/**
	 * 角色下属id 可编辑的
	 */
	public final static String ROLE_CHILDREN_CONTROLEDELETE = "role_children_controledelete";
	
	/**
	 * web 调用返回的  数据
	 */
	public static final String RESULTDATA = "resultData_for_crm";
	
	/**
	 * Web 调用返回的类型
	 * 
	 */
	public static final String RESULTTYPE = "resultType_for_crm";
	
	/**
	 * web 调用 需要页面写出的值
	 */
	public static final String RESULTWRITEDATA = "resultWriteData_for_crm";
	/**
	 * web 调用 需要页面写出的值
	 */
	public static final String RESULTWRITEDATAHEADER = "resultWriteDataHeader_for_crm";
	/**
	 * web  url
	 */ 
	public static final String WEBURLAMC = "amc.yk";
	
	/**
	 * web  url
	 */
	public static final String WEBURLBACK = "back.yk";
	
	/**
	 * 全局-缓存 所有整合过主模块的 显示列(com_listdisplay里的数据)
	 */
	public final static String TENANT_MEMCACHE_MODELLISTDISPLAY = "tenant_mc_modellistdisplay";
	/**
	 * 全局-缓存 com_com_screening 数据缓存
	 */
	public final static String TENANT_MEMCACHE_COMSCREENING = "tenant_mc_comscreening";
	public final static String TENANT_MEMCACHE_COMCOLUMNLIST = "tenant_mc_comcolumnlist";
	
	public final static String RESULTACTIONERROR = "resultActionError_fro_crm";
	
	public final static String RESULTACTIONMESSAGE = "resultactionmessage_for_crm";

	/* CRM提醒栏缓存数据 */
	public final static String CRM_NOTICE_MEMCACHE_DATA = "crm_notice_memcache_data";
	/* AMC账户缓存数据 */
	public final static String AMC_ACCOUT_MEMCACHE_DATA = "amc_account_memcache_data";
	/* CRM账户缓存数据 */
	public final static String CRM_DELETE_MEMCACHE_USER = "crm_delete_memcache_user";
	/* 密码初始化时间缓存 */
	public final static String AMC_PASSWORD_DATE_MEMCACHE_DATA = "amc_password_date_memcache_date";
	
	/**
	 * 全局缓存，IMS模块集合
	 */
	public final static String IMS_MEMCACHE_MODELDEFINEMAP = "ims_mc_modeldefinemap";
	
	/**
	 * 全局缓存，IMS模块集合
	 */
	public final static String IMS_MEMCACHE_MODELDEFINELIST = "ims_mc_modeldefinelist";
	
	/**
	 * 全局缓存，IMS操作请求集合
	 */
	public final static String IMS_MEMCACHE_OPERATIONURLLIST = "ims_mc_operationurllist";
	
	/**
	 * 模块的操作请求
	 */
	public final static String IMS_MEMCACHE_MODELOPERATION = "ims_mc_modeloperation";
	
	/**
	 * 系统邮件缓存
	 */
	
	public final static String MEMCACHE_SYSTEMEMAIL ="mc_systememail";
	/**
	 * 租户下拉选项字段信息
	 * 
	 */
	public final static String CRM_TENANT_SELECTVALUEALL = "crm_tenant_selectvalueall"; 
	
	/**
	 * 页面布局编辑缓存
	 */
	public final static String CRM_TENANT_LAYOUTEDIT = "crm_tenant_layoutEdit";
	
	/**
	 * 用户下手机版登录信息
	 * 
	 */
	public final static String CRM_MOBILE_LOGINTYPE = "crm_mobile_logintype";
	
}
