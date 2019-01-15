package com.springboot.study.utils.object;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: MapToObjectUtil
 * @author: XX
 * @date: 2018/11/8 15:38
 * @description: 对象与容器互转的工具类
 */
public class MapToObjectUtil {

	/**
	 * 实体对象转成Map
	 *
	 * @param obj
	 *            实体对象
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:29
	 */
	public static Map<String, Object> pojoToMap(Object obj) {
		Map<String, Object> map = new HashMap<>();
		if (obj == null) {
			return map;
		}
		Class clazz = obj.getClass();
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * Map转成实体对象
	 *
	 * @param map
	 *            map实体对象包含属性
	 * @param clazz
	 *            实体对象类型
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:28
	 */
	public static Object mapToPojo(Map<String, Object> map, Class<?> clazz) {
		if (map == null) {
			return null;
		}
		Object obj = null;
		try {
			obj = clazz.newInstance();
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	/**
	 * 方法说明：将bean转化为另一种bean实体
	 *
	 * @param object
	 * @param entityClass
	 * @author XX
	 * @date 2018/11/8 16:27
	 */
	public static <T> T convertBean(Object object, Class<T> entityClass) {
		if (null == object) {
			return null;
		}
		return JSON.parseObject(JSON.toJSONString(object), entityClass);
	}

	/**
	 * 方法说明：对象转换
	 *
	 * @param source
	 *            原对象
	 * @param target
	 *            目标对象
	 * @param ignoreProperties
	 *            排除要copy的属性
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:27
	 */
	public static <T> T copy(Object source, Class<T> target, String... ignoreProperties) {
		T targetInstance = null;
		try {
			targetInstance = target.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (ArrayUtils.isEmpty(ignoreProperties)) {
			BeanUtils.copyProperties(source, targetInstance);
		} else {
			BeanUtils.copyProperties(source, targetInstance, ignoreProperties);
		}
		return targetInstance;

	}

	/**
	 * 方法说明：对象转换(List);
	 *
	 * @param list
	 *            原对象
	 * @param target
	 *            目标对象
	 * @param ignoreProperties
	 *            排除要copy的属性
	 * @param
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:23
	 */
	public static <T, E> List<T> copyList(List<E> list, Class<T> target, String... ignoreProperties) {
		List<T> targetList = new ArrayList<>();
		if (CollectionUtils.isEmpty(list)) {
			return targetList;
		}
		for (E e : list) {
			targetList.add(copy(e, target, ignoreProperties));
		}
		return targetList;
	}

	/**
	 * map转化为对象
	 *
	 * @param
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:23
	 */
	public static <T> T mapToObject(Map<String, Object> map, Class<T> t)
			throws InstantiationException, IllegalAccessException, InvocationTargetException {
		T instance = t.newInstance();
		// org.apache.commons.beanutils.BeanUtils.populate(instance, map);
		return instance;
	}

	/**
	 * 对象转化为Map
	 *
	 * @param
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:23
	 */
	public static Map<?, ?> objectToMap(Object object) {
		return convertBean(object, Map.class);
	}

	/**
	 * list 对象转化为Map
	 *
	 * @param
	 * @return
	 * @author XX
	 * @date 2018/11/8 16:21
	 */
	public static List<Map<?, ?>> objectToMapList(List<?> list) {
		List<Map<?, ?>> dataList = new ArrayList<>();
		for (Object object : list) {
			dataList.add(objectToMap(object));
		}
		return dataList;
	}
}
