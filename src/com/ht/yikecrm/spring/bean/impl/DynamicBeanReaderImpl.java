package com.ht.yikecrm.spring.bean.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.xml.ResourceEntityResolver;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;

import com.ht.yikecrm.spring.bean.DynamicBeanReader;
import com.ht.yikecrm.spring.bean.DynamicResource;

public class DynamicBeanReaderImpl implements DynamicBeanReader,ApplicationContextAware {
	private static final Log logger = LogFactory.getLog(DynamicBeanReaderImpl.class);//日记  
    
    private ConfigurableApplicationContext applicationContext = null;    
      
    private XmlBeanDefinitionReader beanDefinitionReader;  
    /*初始化方法*/  
    public void init(){  
        beanDefinitionReader = new XmlBeanDefinitionReader((BeanDefinitionRegistry)  
                applicationContext.getBeanFactory());    
        beanDefinitionReader.setEntityResolver(new ResourceEntityResolver(applicationContext));    
    }  
      
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {    
        this.applicationContext = (ConfigurableApplicationContext)applicationContext;    
    }  
      
    public void loadBean(List<DataSourceDynamicBean> dataSourceList){   
        long startTime = System.currentTimeMillis();  
        beanDefinitionReader.loadBeanDefinitions(new DynamicResource(dataSourceList));  
        logger.info("初始化动态数据源,耗时"+(System.currentTimeMillis()-startTime)+"毫秒。");  
    }   
}
