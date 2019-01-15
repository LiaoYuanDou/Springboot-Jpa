package com.springboot.study.service;

import java.util.Map;

import com.springboot.study.pojo.User;

public interface UserService {
	User testPath(int userId);

	Map<String, Object> userTest(int userId);
}
