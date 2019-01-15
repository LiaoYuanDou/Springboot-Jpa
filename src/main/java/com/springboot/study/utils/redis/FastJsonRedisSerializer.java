package com.springboot.study.utils.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @ClassName: FastJsonRedisSerializer
 * @Author: XX
 * @Date: 2018/8/1 14:04
 * @Description: 通用序列化方式的类
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    static final byte[] EMPTY_ARRAY = new byte[0];

    private Class clazz;

    public FastJsonRedisSerializer(Class clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (null == t) {
            return EMPTY_ARRAY;
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (this.isEmpty(bytes)) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return (T) JSON.parseObject(str, clazz);
    }

    private boolean isEmpty(byte[] data) {
        return (null == data || data.length <= 0);
    }
}
