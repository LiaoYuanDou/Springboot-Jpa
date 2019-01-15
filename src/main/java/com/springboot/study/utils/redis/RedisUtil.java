package com.springboot.study.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import com.springboot.study.utils.dao.BaseDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName: RedisUtil
 * @Author: XX
 * @Date: 2018/8/1 15:02
 * @Description: Redis 常用操作
 */
@Component
public class RedisUtil {
	private RedisUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(RedisUtil.class);

	/**
	 * 默认验证码过期时长 单位：s
	 */
	public static final long DEFAULT_TEACHER_TIME_EXPIRE = 600;

	/**
	 * null值
	 */
	private static final String NULL = "null";

	private static RedisTemplate redisTemplate;

	private static BaseDao baseDao;

	@Autowired
	public void setRedisTemplate(RedisTemplate redisTemplate) {
		RedisUtil.redisTemplate = redisTemplate;
	}

	@Autowired
	public void setBaseDao(BaseDao baseDao) {
		RedisUtil.baseDao = baseDao;
	}

	/**
	 * 默认token过期时长 单位：s
	 */
	public static final long DEFAULT_TOKEN_EXPIRE = 1800;

	/**
	 * 默认验证码过期时长 单位：s
	 */
	public static final long DEFAULT_CAPTCHACODE_EXPIRE = 300;

	/**
	 * 不设置过期时长
	 */

	public static final long NOT_EXPIRE = -1;

	/**
	 * hasKey
	 *
	 * @param key
	 *            查询的key值
	 * @return boolean
	 * @Author XX
	 * @Description 查询key值是否存在
	 */
	public static boolean hasKey(Object key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * setStr
	 *
	 * @param str
	 *            key值
	 * @param obj
	 *            value值
	 * @return boolean
	 * @Author XX
	 * @Description 保存String类型的数据
	 */
	public static boolean setStr(String str, Object obj) {
		boolean flag = false;
		try {
			redisTemplate.opsForValue().set(str, obj);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	/**
	 * getStr
	 *
	 * @param str
	 *            key值
	 * @return Object 返回数据
	 * @Author XX
	 * @Description 根据key值得到返回的值
	 */
	public static Object getStr(String str) {
		if (str == null) {
			return null;
		}
		Object obj = redisTemplate.opsForValue().get(str);
		if (!checkValueIsNotNull(obj)) {
			return null;
		} else {
			return obj;
		}

	}

	/**
	 * @param
	 * @return
	 * @author XX
	 * @description 判断redis的返回值是否是空值
	 */
	public static boolean checkValueIsNotNull(Object object) {
		return object != null;
	}

	/**
	 * eleteKey
	 *
	 * @param keys
	 *            可变参数key值
	 * @return
	 * @Author XX
	 * @Description 删除key
	 */
	public static void deleteKey(Object... keys) {
		if (keys.length == 1) {
			redisTemplate.delete(keys[0]);
		} else {
			redisTemplate.delete(keys);
		}

	}

	/**
	 * deleteKey
	 *
	 * @param
	 * @return
	 * @Author XX
	 * @Description 删除多个key
	 */
	public static void deleteKey(List<Object> list) {
		redisTemplate.delete(list);
	}

	/**
	 * expire
	 *
	 * @param key
	 *            键
	 * @param time
	 *            过期时间
	 * @return
	 * @Author XX
	 * @Description 指定缓存失效时间
	 */
	public static boolean expire(String key, long time) {
		try {
			if (time > 0) {
				redisTemplate.expire(key, time, TimeUnit.SECONDS);
			} else {
				redisTemplate.expire(key, DEFAULT_TOKEN_EXPIRE, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 根据key 获取过期时间
	 *
	 * @param key
	 *            键
	 * @return long 时间
	 * @Author XX
	 * @Description TODO
	 */
	public static long getExpire(String key) {
		return redisTemplate.getExpire(key);
	}

	/**
	 * setStrExpire
	 *
	 * @param key
	 *            key值
	 * @param obj
	 *            缓存对象
	 * @param time
	 *            过期时间
	 * @return
	 * @Author XX
	 * @Description String数据缓存放入并设置时间
	 */
	public static boolean setStrExpire(String key, Object obj, long time) {
		try {
			if (time > 0) {
				redisTemplate.opsForValue().set(key, obj, time, TimeUnit.SECONDS);
			} else {
				redisTemplate.opsForValue().set(key, obj, DEFAULT_TOKEN_EXPIRE, TimeUnit.SECONDS);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * incr
	 *
	 * @param key
	 *            键
	 * @param data
	 *            要增加几(大于0)
	 * @return
	 * @Author XX
	 * @Description 递增
	 */
	public static long incr(String key, long data) {
		if (data < 0) {
			throw new RuntimeException("递增因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, data);
	}

	/**
	 * decr
	 *
	 * @param key
	 *            键
	 * @param data
	 *            要减少几(大于0)
	 * @return
	 * @Author XX
	 * @Description 递减
	 */
	public static long decr(String key, long data) {
		if (data < 0) {
			throw new RuntimeException("递减因子必须大于0");
		}
		return redisTemplate.opsForValue().increment(key, -data);
	}

	/**
	 * 清除所有的可以值
	 *
	 * @param
	 * @return
	 * @author XX
	 */
	public static void flushAll() {
		String pattern = "*";
		RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();

		Set<byte[]> caches = connection.keys(pattern.getBytes());
		if (!caches.isEmpty()) {
			connection.del(caches.toArray(new byte[][] {}));
		}
	}
}