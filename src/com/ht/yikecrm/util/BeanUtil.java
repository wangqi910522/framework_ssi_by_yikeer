package com.ht.yikecrm.util;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ht.yikecrm.dictionary.JsonDictionary;
import com.ht.yikecrm.model.Attribute;

public class BeanUtil {

	/**
	 * 将JavaBean对象转换为Map
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	public static Map convertBean(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
	
	public static List<Attribute> convertAddAttributes(Object bean) throws Exception{
		return convertAttributes(bean,1);
	}
	
	public static List<Attribute> convertUpdateAttributes(Object bean) throws Exception{
		return convertAttributes(bean,2);
	}
	
	private static List<Attribute> convertAttributes(Object bean, int cType) throws Exception{
		List<Attribute> returnList = new ArrayList<Attribute>();
		Class type = bean.getClass();
		//获取对象类名
		String[] packName = type.getName().split("\\.");
		String className = packName[packName.length-1];
		//获取该对象里面未经持久化的字段集合
		String [] notPersistent = JsonDictionary.getNotPersistent().get(className);
		
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			boolean falg = false;
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();
			//判断是否为非持久化字段，如果是则 不做操作
			if(notPersistent!=null){
				for (String string : notPersistent) {
					if(string.equals(propertyName)){
						falg = true;
					}
				}
			}
			if (!propertyName.equals("class")&&!falg) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean, new Object[0]);
				if(result == null || StringUtils.isNullOrEmpty(result.toString())){
					if(cType == 1){
						continue;
					} else if(cType == 2){
						result = "";
					}
				}
				//System.out.println(readMethod.getReturnType());
				Integer ptype = 4;
				String value = result.toString();
//				if (readMethod.getReturnType().equals(Integer.class)) {
//					ptype  = 1;
//				} else if (readMethod.getReturnType().equals(Long.class)) {
//					ptype  = 2;
//				} else if (readMethod.getReturnType().equals(BigDecimal.class)) {
//					ptype  = 3;
//				} else if (readMethod.getReturnType().equals(Double.class)) {
//					ptype  = 6;
//				}else if (readMethod.getReturnType().equals(Float.class)) {
//					ptype  = 6;
//				}else if (readMethod.getReturnType().equals(String.class)) {
//					ptype  = 4;
//				} else 
					
				if (readMethod.getReturnType().equals(Date.class)) {
					ptype  = 5;
					if(result != ""){
						Date tempDate = (Date) result;
						value = DateUtil.timeToStr(tempDate);
					}
				}
				returnList.add(new Attribute(propertyName, ptype, value));
			}
		}
		return returnList; 
	}
	

	/**
	 * map对象转换为JavaBean
	 * @param type
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public static Object convertMap(Class type, Map map) throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属性
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo
				.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来,这样当一个属性赋值失败的时候就不会影响其他属性赋值.
				Object value = map.get(propertyName);
				Object[] args = new Object[1];
				args[0] = value;
				Class classes = descriptor.getWriteMethod().getParameterTypes()[0];
				boolean flag = false;
				if(value != null){
					if(value instanceof java.sql.Timestamp){
						args[0] = (Object)new Date(((java.sql.Timestamp) value).getTime());
						flag = true;
					}
					if (classes.equals(Integer.class)) {
						args[0] = Integer.parseInt(StringUtils.isNullOrEmpty(value.toString())?"0":value.toString());
					} else if (classes.equals(Long.class)) {
						args[0] = Long.parseLong(StringUtils.isNullOrEmpty(value.toString())?"0":value.toString());
					} else if (classes.equals(BigDecimal.class)) {
						args[0] = new BigDecimal(StringUtils.isNullOrEmpty(value.toString())?"0":value.toString());
					} else if (classes.equals(String.class)) {
						args[0]= value.toString();
					}else if (classes.equals(Float.class)) {
						args[0] = Float.parseFloat(value.toString());
					}else if (classes.equals(Double.class)) {
						args[0] = Double.parseDouble(value.toString());
					}
					if (!flag && classes.equals(Date.class)) {
						if(value != ""){
							args[0] = (Date) value;
						}
					}
				}
				descriptor.getWriteMethod().invoke(obj, args);
//				System.out.println(propertyName+":     =="+descriptor.getWriteMethod().getParameterTypes()[0]);
			}
		}
		return obj;
	}
	
	public static Map<String, Object> getOtherMap(Map<String, Object> map) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		for(Map.Entry<String, Object> entry : map.entrySet()){
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}
	
	public static Object getBean(Object bean) throws Exception {
		return convertMap(bean.getClass(), convertBean(bean));
	} 
}
