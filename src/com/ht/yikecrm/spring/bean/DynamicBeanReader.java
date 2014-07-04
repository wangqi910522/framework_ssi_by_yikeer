package com.ht.yikecrm.spring.bean;

import java.util.List;

import com.ht.yikecrm.spring.bean.impl.DataSourceDynamicBean;

public interface DynamicBeanReader {
	/** 
     * 动态加载bean 
     * @param dynamicBean 
     */  
    public void loadBean(List<DataSourceDynamicBean> dataSourceList);
}
