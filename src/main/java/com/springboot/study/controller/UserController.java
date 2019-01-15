package com.springboot.study.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.study.pojo.User;
import com.springboot.study.service.UserService;
import com.springboot.study.utils.object.MapToObjectUtil;
import com.springboot.study.utils.restultful.BaseResult;
import com.springboot.study.utils.restultful.ResultCode;
import com.springboot.study.utils.restultful.ResultUtil;

/**
 * 控制层
 * 
 * @ClassName: UserController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author XX
 * @date 2019年1月15日
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/testPath/{id}", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> testPath(@PathVariable("id") int userId) {
		User user = userService.testPath(userId);
		System.out.println(user.toString());
		Map<?, ?> objectToMap = MapToObjectUtil.objectToMap(user);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResultUtil.setSingleResult(ResultCode.SUCCESS.getCode(), objectToMap));
	}

	@RequestMapping(value = "/userTest", method = RequestMethod.GET)
	public ResponseEntity<BaseResult> userTest(@RequestParam(value = "id") int userId) {
		Map<String, Object> userMap = userService.userTest(userId);
		return ResponseEntity.status(HttpStatus.OK)
				.body(ResultUtil.setSingleResult(ResultCode.SUCCESS.getCode(), userMap));
	}
}
