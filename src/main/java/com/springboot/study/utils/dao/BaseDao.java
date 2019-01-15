package com.springboot.study.utils.dao;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @ClassName: BaseDao
 * @Description: 基础的数据操作接口层
 * @author: XX
 * @date: 2019年1月15日 上午11:14:01
 * 
 * @param <T>
 */
public interface BaseDao<T> {

	/**
	 * save
	 *
	 * @param entity
	 *            需要保存的实体
	 * @return boolean 返回是否保存成功
	 * @author XX
	 * @description 保存数据对象基类接口
	 */
	boolean save(T entity);

	/**
	 * findById
	 *
	 * @param entity
	 *            实体类
	 * @param id
	 *            需要查询的主键id
	 * @return Object 返回实体类
	 * @author XX
	 * @description 根据主键id查询实体类
	 */
	Object findById(T entity, long id);

	/**
	 * findNormalById
	 *
	 * @param entity
	 *            实体类
	 * @param id
	 *            需要查询的主键id
	 * @return Object 返回实体类
	 * @author XX
	 * @description 根据主键id查询status为0的实体类
	 */
	Object findNormalObjectById(T entity, long id);

	/**
	 * findObjectByFildMap
	 *
	 * @param entity
	 *            实体类
	 * @param map
	 *            条件的键值对
	 * @return List<T> 返回实体类列表
	 * @author XX
	 * @description 根据多个条件查询列表
	 */
	List<T> findListByFieldMap(T entity, LinkedHashMap<String, Object> map);

	/**
	 * findPage
	 *
	 * @param entity
	 *            实体类
	 * @param start
	 *            第几页
	 * @param pageNumber
	 *            一个页面的条数
	 * @return List<T> 返回实体类列表
	 * @author XX
	 * @description 正常(status = 0)数据的分页
	 */
	List<T> findPage(T entity, int start, int pageNumber);

	/**
	 * findByMoreFieldPage
	 *
	 * @param entity
	 *            实体类
	 * @param map
	 *            以map存储key,value 条件查询
	 * @param start
	 *            第几页
	 * @param pageNumber
	 *            一个页面的条数
	 * @return List<T> 返回实体类列表
	 * @author XX
	 * @description 根据多个条件查询列表分页
	 */
	List<T> findByMoreFieldPage(T entity, LinkedHashMap<String, Object> map, int start, int pageNumber);

	/**
	 * update
	 *
	 * @param entity
	 *            实体类
	 * @return boolean 返回是否更新成功
	 * @author XX
	 * @description 更新对象
	 */
	boolean update(T entity);

	/**
	 * updateMoreField
	 *
	 * @param entity
	 *            实体类
	 * @param setMap
	 *            以map存储key,value 更改数据
	 * @param whereMap
	 *            以map存储key,value 条件数据
	 * @return Integer 返回修改的条数
	 * @author XX
	 * @description 根据传入的map遍历key, value 更新数据
	 */
	Integer updateMoreField(T entity, LinkedHashMap<String, Object> setMap, LinkedHashMap<String, Object> whereMap);

	/**
	 * deleteStatus
	 *
	 * @param entity
	 *            实体类
	 * @param id
	 *            主键id
	 * @return Integer 删除多少条
	 * @author XX
	 * @description 删除动作（实际上是更新status=1）
	 */
	Integer deleteStatus(T entity, long id);

	/**
	 * findCount
	 *
	 * @param entity
	 *            实体类
	 * @param map
	 *            以map存储key,value 条件查询
	 * @return Object 返回多少条
	 * @author XX
	 * @description 根据条件查询总条数
	 */
	Object findCount(T entity, LinkedHashMap<String, Object> map);

	/**
	 * findBySql
	 *
	 * @param sql
	 *            自己写的SQL
	 * @param clazz
	 *            单表查询的pojo类
	 * @return Object 返回结果
	 * @author XX
	 * @description 根据自己写的SQL查询数据（单表查询）
	 */
	List<Map<String, Object>> findBySqlClass(String sql, Class clazz);

	/**
	 * findByPreparedSqlClass
	 *
	 * @param sql
	 *            自己写的SQL
	 * @param clazz
	 *            单表查询的pojo类
	 * @param list
	 *            按顺序将参数值写进list
	 * @return Object 返回结果
	 * @author XX
	 * @description 根据自己写的预编译SQL查询数据（单表查询）
	 */
	List<Map<String, Object>> findByPreparedSqlClass(String sql, Class clazz, List<Object> list);

	/**
	 * findBySql
	 *
	 * @param sql
	 *            自己写的SQL
	 * @return List<Map<String,Object>> 返回结果
	 * @author XX
	 * @description 根据自己写的SQL查询数据（多表查询尽量按照pojo格式写别名）
	 */
	List<Map<String, Object>> findBySql(String sql);

	/**
	 * findByPreparedSql
	 *
	 * @param sql
	 *            自己写的SQL
	 * @param list
	 *            按顺序将参数值写进list
	 * @return List<Map<String,Object>> 返回结果
	 * @author XX
	 * @description 根据自己写的预编译SQL查询数据（多表查询尽量按照pojo格式写别名）
	 */
	List<Map<String, Object>> findByPreparedSql(String sql, List<Object> list);

	/**
	 * 根据自己写的预编译SQL查询数据一列返回List<Object> findOneFileldByPreparedSql
	 *
	 * @param sql
	 * @param list
	 * @return
	 * @author XX
	 */
	List<Object> findOneFieldByPreparedSql(String sql, List<Object> list);

	/**
	 * executeUpdate
	 *
	 * @param sql
	 *            自己写的SQL
	 * @param list
	 *            按顺序将参数值写进list
	 * @return Integer 更改多少条
	 * @author XX
	 * @description 根据自己写的SQL更新或者插入数据
	 */
	Integer executeUpdate(String sql, List<Object> list);

	/**
	 * count
	 *
	 * @param sql
	 *            自己写的SQL
	 * @param list
	 *            按顺序将参数值写进list
	 * @return Integer 总数多少条
	 * @author XX
	 * @description 查询多少条
	 */
	Integer count(String sql, List<Object> list);

	/**
	 * 批量导入
	 *
	 * @param list
	 * @return
	 * @author XX
	 */
	void batchInsert(List<T> list);

	/**
	 * 批量修改
	 *
	 * @param list
	 * @return
	 * @author XX
	 */
	void batchUpdate(List<T> list);
}
