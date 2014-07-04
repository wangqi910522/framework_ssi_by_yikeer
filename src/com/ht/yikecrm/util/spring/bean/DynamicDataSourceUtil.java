package com.ht.yikecrm.util.spring.bean;

import java.util.List;

import com.ht.yikecrm.spring.bean.impl.DataSourceDynamicBean;

public class DynamicDataSourceUtil {

	private static final String MYSQL_DRIVER_CLASS = "com.mysql.jdbc.Driver";
	private static final String SQLMAP_CONFIG_LOCATION = "classpath:sqlMapConfig.xml";
	private static final String timeBetweenEvictionRunsMillis = "1800000";//多长时间检查连接池
	private static final String minEvictableIdleTimeMillis = "3600000";//检查 限制多长时间的连接
	
	public static String getXml(List<DataSourceDynamicBean> dataSourceList) {
        StringBuffer buf = new StringBuffer();  
        buf.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>")  
            .append("<beans xmlns=\"http://www.springframework.org/schema/beans\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"")  
            .append("       xmlns:p=\"http://www.springframework.org/schema/p\" xmlns:aop=\"http://www.springframework.org/schema/aop\"")  
            .append("       xmlns:context=\"http://www.springframework.org/schema/context\" xmlns:jee=\"http://www.springframework.org/schema/jee\"")  
            .append("       xmlns:tx=\"http://www.springframework.org/schema/tx\"")  
            .append("       xsi:schemaLocation=\"")  
            .append("           http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop-2.5.xsd")  
            .append("           http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans-2.5.xsd")  
            .append("           http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-2.5.xsd")  
            .append("           http://www.springframework.org/schema/jee http://www.springframework.org/schema/jee/spring-jee-2.5.xsd")  
            .append("           http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx-2.5.xsd\">")  
            .append(getBeanXml(dataSourceList))  
            .append("</beans>");  
        return buf.toString();  
	}
	
	private static String getBeanXml(List<DataSourceDynamicBean> dataSourceList) {
		StringBuffer buffer = new StringBuffer();
		
		if (dataSourceList != null && dataSourceList.size() > 0) {
			for (DataSourceDynamicBean datasource : dataSourceList) {
				if (datasource.getIsDefault() == 1) {//如果是默认数据源
					continue;
				}
				String beanName = datasource.getBeanName();
				//DataSource 
				/*
					<bean  id = "dataSource_1"   class = "org.enhydra.jdbc.pool.StandardXAPoolDataSource"   destroy-method = "shutdown" >    
						<property   name = "dataSource" >    
							<bean   class = "org.enhydra.jdbc.standard.StandardXADataSource"   destroy-method = "shutdown" >    
								<property   name = "transactionManager"   ref = "jotm" />    
								<property   name = "driverName"   value = "${jdbc.driverClass}" />    
								<property   name = "url"   value = "${jdbc.url_1}" />    
							</bean >    
						</property >    
						<property   name = "user"   value = "${jdbc.username}" />    
						<property   name = "password"   value = "${jdbc.password}" />
						<property name = "timeBetweenEvictionRunsMillis" value="1800000" />
       					<property name = "minEvictableIdleTimeMillis" value="3600000" />    
					</bean > 
				 */
//				buffer.append("<bean id=\"" + beanName + "\" class=\"org.enhydra.jdbc.pool.StandardXAPoolDataSource\" destroy-method=\"shutdown\">")
//			        .append("<property name=\"dataSource\">")
//			        .append("<bean class=\"org.enhydra.jdbc.standard.StandardXADataSource\" destroy-method=\"shutdown\">")
//			        .append("<property name=\"transactionManager\" value=\"jotm\" />")
//			        .append("<property name=\"driverName\" value=\"" + MYSQL_DRIVER_CLASS + "\" />")
//			        .append("<property name=\"url\" value=\"" + datasource.getUrl() + "\" />")
//			        .append("</bean>")
//			        .append("</property>")
//			        .append("<property name=\"user\" value=\"" + datasource.getUsername() + "\" />")
//			        .append("<property name=\"password\" value=\"" + datasource.getPassword() + "\" />")
//			        .append("<property name=\"timeBetweenEvictionRunsMillis\" value=\"" + timeBetweenEvictionRunsMillis + "\" />")
//			        .append("<property name=\"minEvictableIdleTimeMillis\" value=\"" + minEvictableIdleTimeMillis + "\" />")
//			        .append("</bean>");  
				
				
				/*
					<bean id="dataSource_default" parent="abstractXADataSource">
						<property name="uniqueResourceName" value="dataSource_default" />
						<property name="xaDataSourceClassName" value="com.mysql.jdbc.jdbc2.optional.MysqlXADataSource" />
						<property name="xaProperties">
							<props>
								<prop key="URL">${jdbc.url}</prop>
								<prop key="user">${jdbc.username}</prop>
								<prop key="password">${jdbc.password}</prop>
							</props>
						</property>
					</bean>
				*/
				buffer.append("<bean id=\"" + beanName + "\" parent=\"abstractXADataSource\">")
					.append("<property name=\"uniqueResourceName\" value=\"d"+ beanName +"\" />")
					.append("<property name=\"xaDataSourceClassName\" value=\"com.mysql.jdbc.jdbc2.optional.MysqlXADataSource\" />")
					.append("<property name=\"xaProperties\">")
					.append("<props>")
					.append("<prop key=\"URL\">${jdbc.url}</prop>")
					.append("<prop key=\"user\">${jdbc.username}</prop>")
					.append("<prop key=\"password\">${jdbc.password}</prop>")
					.append("</props>")
					.append("</property>")
					.append("</bean>");
			
				//sqlMapClient
				/*
				 	<bean id="sqlMapClient_1" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
						<property name="configLocation" value="classpath:sqlMapConfig.xml" />
						<property name="dataSource" ref="dataSource_1" />
					</bean>
				 */
				buffer.append("<bean id=\"" + getSqlMapBeanName(beanName) + "\" class=\"org.springframework.orm.ibatis.SqlMapClientFactoryBean\">")
					.append("<property name=\"configLocation\" value=\"" + SQLMAP_CONFIG_LOCATION + "\" />")
					.append("<property name=\"dataSource\" ref=\"d" + beanName + "\" />")
					.append("</bean>");
				
				//jbdcTemplate
				/*
					<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
						<property name="dataSource" ref="dataSource_1"></property>
					</bean>
				 */
				buffer.append("<bean id=\"" + getJDBCBeanName(beanName) + "\" class=\"org.springframework.jdbc.core.JdbcTemplate\">")
				.append("<property name=\"dataSource\" ref=\"d" + beanName + "\" />")
				.append("</bean>");
			}
		}
		return buffer.toString();
	}
	
	private static String getSqlMapBeanName(String dataSourceBeanName) {
		return "sqlMapClient_" + dataSourceBeanName.split("_")[1];
	}
	
	private static String getJDBCBeanName(String dataSourceBeanName) {
		return "jdbcTemplate_" + dataSourceBeanName.split("_")[1];
	}
	
}
