package com.springboot.study.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springboot.study.pojo.User;
import com.springboot.study.repository.UserRepository;
import com.springboot.study.service.UserService;
import com.springboot.study.utils.dao.BaseDao;

/**
 * 实现层
 * 
 * @ClassName: UserServiceImpl
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author TalkXX
 * @date 2019年1月15日
 *
 */
@Transactional(rollbackFor = Exception.class)
@Service
public class UserServiceImpl implements UserService {

	@SuppressWarnings({ "rawtypes", "unused" })
	@Autowired
	private BaseDao baseDao;

	@Autowired
	private UserRepository userRepository;

	@Override
	public User testPath(int userId) {
		return userRepository.findByUserId(userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> userTest(int userId) {
		String getUserSql = "select * from t_user_info where id = ? ";
		List<Object> fieldList = new ArrayList<Object>();
		fieldList.add(userId);
		List<Map<String, Object>> dataList = baseDao.findByPreparedSql(getUserSql, fieldList);
		return CollectionUtils.isNotEmpty(dataList) ? dataList.get(0) : new HashMap<String, Object>();
	}

}
