package com.springboot.study.utils.dao;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * 
 * @ClassName: BaseDaoImpl
 * @Description:基础的数据操作实现层
 * @author: XX
 * @date: 2019年1月15日 上午11:19:53
 * 
 * @param <T>
 */
@Repository
public class BaseDaoImpl<T> implements BaseDao<T> {

	@PersistenceContext
	private EntityManager entityManager;

	private static Logger logger = LoggerFactory.getLogger(BaseDaoImpl.class);

	private final static String SQL_LAST = ";";

	@Override
	public boolean save(T entity) {
		boolean flag = false;
		try {
			entityManager.persist(entity);
			flag = true;
			entityManager.flush();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			logger.info("===================保存出错===================");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Object findById(Object entity, long id) {
		return entityManager.find(entity.getClass(), id);
	}

	@Override
	public Object findNormalObjectById(T entity, long id) {
		String sql = " FROM " + entity.getClass().getSimpleName() + " AS p WHERE p.status = 0 AND p.id =?1 ";
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Object obj = null;
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, id);
			obj = query.getSingleResult();
			entityManager.flush();
			entityManager.clear();
			entityManager.close();
		} catch (NoResultException e1) {
			obj = new Object();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Override
	public List<T> findListByFieldMap(T entity, LinkedHashMap<String, Object> map) {
		StringBuilder sql = new StringBuilder(" FROM " + entity.getClass().getSimpleName() + " AS p WHERE 1=1 ");
		Set<String> set = map.keySet();
		List<String> FieldNamelist = new ArrayList<String>(set);
		List<Object> FieldValueList = new ArrayList<Object>();

		for (int i = 0; i < FieldNamelist.size(); i++) {
			int a = i + 1;
			sql.append(" AND p." + FieldNamelist.get(i) + " =?");
			sql.append(a);
			FieldValueList.add(FieldNamelist.get(i));
		}

		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);

		Query query = entityManager.createQuery(sql.toString());
		for (int i = 0; i < FieldValueList.size(); i++) {
			int a = i + 1;
			query.setParameter(a, map.get(FieldValueList.get(i)));
		}
		entityManager.close();
		List<T> dataList = query.getResultList();

		return dataList;
	}

	@Override
	public List<T> findPage(T entity, int start, int pageNumber) {
		String sql = "FROM " + entity.getClass().getSimpleName() + " AS p WHERE p.status = 0 ";
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		List<T> list = new ArrayList<T>();
		try {
			Query query = entityManager.createQuery(sql);
			query.setFirstResult((start - 1) * pageNumber);
			query.setMaxResults(pageNumber);
			list = query.getResultList();
		} catch (Exception e) {
			logger.info("===================分页错误===================");
			e.printStackTrace();
		}
		entityManager.close();
		return list;
	}

	@Override
	public List<T> findByMoreFieldPage(T entity, LinkedHashMap<String, Object> map, int start, int pageNumer) {
		StringBuilder sql = new StringBuilder(" FROM " + entity.getClass().getSimpleName() + " AS p WHERE 1=1 ");
		Set<String> set = map.keySet();
		List<String> FieldNamelist = new ArrayList<String>(set);
		List<Object> FieldValueList = new ArrayList<Object>();

		for (int i = 0; i < FieldNamelist.size(); i++) {
			int a = i + 1;
			sql.append(" AND p." + FieldNamelist.get(i) + " =?").append(a);
			FieldValueList.add(FieldNamelist.get(i));
		}

		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);

		Query query = entityManager.createQuery(sql.toString());
		for (int i = 0; i < FieldValueList.size(); i++) {
			int a = i + 1;
			query.setParameter(a, map.get(FieldValueList.get(i)));
		}

		query.setFirstResult((start - 1) * pageNumer);
		query.setMaxResults(pageNumer);
		List<T> dataList = query.getResultList();
		entityManager.close();
		return dataList;
	}

	@Override
	public boolean update(T entity) {
		boolean flag = false;
		try {
			entityManager.merge(entity);
			flag = true;
			entityManager.flush();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			logger.info("===================更新出错===================");
			e.printStackTrace();
		}
		return flag;
	}

	@Override
	public Integer updateMoreField(T entity, LinkedHashMap<String, Object> setMap,
			LinkedHashMap<String, Object> whereMap) {
		StringBuilder sql = new StringBuilder("UPDATE " + entity.getClass().getSimpleName() + " AS p SET ");

		Set<String> updateSet = setMap.keySet();
		List<String> updateFieldNamelist = new ArrayList<String>(updateSet);

		for (int i = 0; i < updateFieldNamelist.size(); i++) {
			if (setMap.get(updateFieldNamelist.get(i)).getClass().getTypeName() == "java.lang.String") {
				logger.info("-*****" + setMap.get(updateFieldNamelist.get(i)) + "==================="
						+ updateFieldNamelist.get(i) + ":"
						+ setMap.get(updateFieldNamelist.get(i)).getClass().getTypeName());
				sql.append(
						"p." + updateFieldNamelist.get(i) + " = '" + setMap.get(updateFieldNamelist.get(i)) + "' , ");
			} else {
				sql.append("p." + updateFieldNamelist.get(i) + " = " + setMap.get(updateFieldNamelist.get(i)) + " , ");
			}
		}

		sql.deleteCharAt(sql.length() - 2);

		sql.append("WHERE 1=1 ");
		Set<String> whereSet = whereMap.keySet();
		List<String> whereFieldNamelist = new ArrayList<String>(whereSet);
		List<Object> whereFieldValueList = new ArrayList<Object>();
		for (int i = 0; i < whereFieldNamelist.size(); i++) {
			int a = i + 1;
			sql.append(" AND p." + whereFieldNamelist.get(i) + " =?").append(a);
			whereFieldValueList.add(whereFieldNamelist.get(i));
		}

		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);

		int result = 0;
		try {
			Query query = entityManager.createQuery(sql.toString());
			for (int i = 0; i < whereFieldValueList.size(); i++) {
				int a = i + 1;
				query.setParameter(a, whereMap.get(whereFieldValueList.get(i)));
			}
			result = query.executeUpdate();
			entityManager.flush();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			logger.info("===================更新出错===================");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Integer deleteStatus(T entity, long id) {
		boolean flag = false;
		String sql = "UPDATE " + entity.getClass().getSimpleName() + " AS p SET p.status = 1 WHERE p.id =?1";
		int result = 0;
		try {
			Query query = entityManager.createQuery(sql);
			query.setParameter(1, id);
			result = query.executeUpdate();
			entityManager.flush();
			entityManager.clear();
			entityManager.close();
		} catch (Exception e) {
			logger.info("===================删除(更新状态)出错===================");
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public Object findCount(T entity, LinkedHashMap<String, Object> map) {
		StringBuilder sql = new StringBuilder(
				"SELECT count(1) AS totalNum FROM " + entity.getClass().getSimpleName() + " AS p WHERE 1=1");
		Set<String> set = map.keySet();
		List<String> FieldNameList = new ArrayList<String>(set);
		List<Object> FieldValueList = new ArrayList<Object>();

		for (int i = 0; i < FieldNameList.size(); i++) {
			int a = i + 1;
			sql.append(" AND p." + FieldNameList.get(i) + " =?").append(a);
			FieldValueList.add(FieldNameList.get(i));
		}

		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);

		Query query = entityManager.createQuery(sql.toString());
		for (int i = 0; i < FieldValueList.size(); i++) {
			int a = i + 1;
			query.setParameter(a, map.get(FieldValueList.get(i)));
		}
		entityManager.close();
		return query.getSingleResult();
	}

	@Override
	public List<Map<String, Object>> findBySqlClass(String sql, Class clazz) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Query query = entityManager.createNativeQuery(sql, clazz);
		entityManager.close();
		return query.getResultList();
	}

	@Override
	public List<Map<String, Object>> findByPreparedSqlClass(String sql, Class clazz, List<Object> list) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Query query = entityManager.createNativeQuery(sql, clazz);
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i + 1, list.get(i));
		}
		entityManager.close();
		return query.getResultList();
	}

	@Override
	public List<Map<String, Object>> findBySql(String sql) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Query query = entityManager.createNativeQuery(sql);
		// query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		entityManager.close();
		return query.getResultList();
	}

	@Override
	public List<Map<String, Object>> findByPreparedSql(String sql, List<Object> list) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Query query = entityManager.createNativeQuery(sql);

		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i + 1, list.get(i));
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		entityManager.close();
		return query.getResultList();
	}

	@Override
	public List<Object> findOneFieldByPreparedSql(String sql, List<Object> list) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);
		Query query = entityManager.createNativeQuery(sql);

		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i + 1, list.get(i));
		}
		entityManager.close();
		return query.getResultList();
	}

	@Override
	public Integer executeUpdate(String sql, List<Object> list) {
		logger.info("》》》》》》》》》》》》》》》》 SQL: " + sql);

		Query query = entityManager.createNativeQuery(sql);
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i + 1, list.get(i));
		}

		int i = query.executeUpdate();
		entityManager.flush();
		entityManager.clear();
		entityManager.close();
		return i;
	}

	@Override
	public Integer count(String sql, List<Object> list) {
		sql = sql.trim();
		if (SQL_LAST.equals(sql.substring(sql.length() - 1, sql.length()))) {
			sql = sql.substring(0, sql.length() - 1);
		}
		String countSql = "SELECT COUNT(1) AS totalNum FROM ( " + sql + " ) as tab ;";

		Query query = entityManager.createNativeQuery(countSql);
		for (int i = 0; i < list.size(); i++) {
			query.setParameter(i + 1, list.get(i));
		}
		query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		// query.unwrap(NativeQueryImpl.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		Map<String, Object> resultMap = (Map<String, Object>) query.getResultList().get(0);
		entityManager.close();
		return Integer.parseInt(String.valueOf(resultMap.get("totalNum")));
	}

	@Override
	public void batchInsert(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			entityManager.persist(list.get(i));
			if (i % 30 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		entityManager.flush();
		entityManager.clear();
		entityManager.close();
	}

	@Override
	public void batchUpdate(List<T> list) {
		for (int i = 0; i < list.size(); i++) {
			entityManager.merge(list.get(i));
			if (i % 30 == 0) {
				entityManager.flush();
				entityManager.clear();
			}
		}
		entityManager.flush();
		entityManager.clear();
		entityManager.close();
	}
}
