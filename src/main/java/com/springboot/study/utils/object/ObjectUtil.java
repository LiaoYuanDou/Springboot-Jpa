package com.springboot.study.utils.object;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: ObjectUtil
 * @author: XX
 * @date: 2018/9/17 17:46
 * @description: JavaBean对象与Map互相转化
 */
public class ObjectUtil {

	/**
	 * 将一个 JavaBean 对象转化为一个 Map
	 *
	 * @param bean
	 *            要转化的JavaBean 对象
	 * @return 转化出来的 Map 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 * @author XX
	 * @description 将一个 JavaBean 对象转化为一个 Map
	 * @date 2018/9/17 17:47
	 */
	public static Map<String, Object> convertBean(Object bean)
			throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Class type = bean.getClass();
		Map<String, Object> returnMap = new HashMap<String, Object>();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);

		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
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

	/**
	 * 将一个 Map 对象转化为一个 JavaBean
	 *
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属性值的 map
	 * @return 转化出来的 JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失败
	 * @throws IllegalAccessException
	 *             如果实例化 JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例化 JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属性的 setter 方法失败
	 * @author XX
	 * @date 2018/9/17 17:47
	 */
	public static Object convertMap(Class type, Map map)
			throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
		// 获取类属性
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		// 创建 JavaBean 对象
		Object obj = type.newInstance();

		// 给 JavaBean 对象的属性赋值
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++) {
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName)) {
				// 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
				try {
					Object value = map.get(propertyName);

					Object[] args = new Object[1];
					args[0] = value;

					descriptor.getWriteMethod().invoke(obj, args);
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}

			}
		}
		return obj;
	}

	/**
	 * 判断 实体类对象所有属性是否全部为空
	 *
	 * @param object
	 *            实体类对象
	 * @return
	 * @author XX
	 * @date 2018/9/17 17:47
	 */
	public static boolean objectIsEmpty(Object object) {
		if (object == null) {
			return false;
		}
		JSONObject json = (JSONObject) JSONObject.toJSON(object);

		for (java.util.Map.Entry<String, Object> entry : json.entrySet()) {

			if (entry.getValue() != null) {
				if (StringUtils.isNotBlank(entry.getValue().toString())) {
					return true;
				}
			}

		}
		return false;
	}

	public static boolean isBlank(String str) {
		return StringUtils.isBlank(str) || "null".equals(str);
	}

	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
}
