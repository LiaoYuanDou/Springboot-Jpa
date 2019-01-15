package com.springboot.study.exception;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ValidationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.springboot.study.utils.restultful.BaseResult;
import com.springboot.study.utils.restultful.ResultCode;
import com.springboot.study.utils.restultful.ResultUtil;

/**
 * 
 * @ClassName: GlobalExceptionHandler
 * @Description: 异常统一处理类
 * @author XX
 * @date 2019年1月15日
 *
 */
@ControllerAdvice
@Component
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@Bean
	public MethodValidationPostProcessor methodValidationPostProcessor() {
		return new MethodValidationPostProcessor();
	}

	@ExceptionHandler
	public ResponseEntity<BaseResult> exceptionHandler(RuntimeException e, HttpServletRequest request) {
		logger.error("系统出现未捕获的异常：{} {}", request.getMethod(), request.getRequestURI(), e);
		if (("NoRepeatSubmitException!").equals(e.getMessage())) {
			/*
			 * String requestURIKey = message.substring(23, message.length());
			 * RedisUtil.deleteRequestURLFlag(requestURIKey);
			 */
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ResultUtil.setBaseResult(ResultCode.NOREPEATSUBMIT.getCode()));
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（系统出现未捕获的异常）"));
	}

	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<BaseResult> validationExceptionHandler(ValidationException e, HttpServletRequest request) {
		logger.error("系统出现参数校验异常：{} {}", request.getMethod(), request.getRequestURI(), e);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（系统出现参数校验异常）"));
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<BaseResult> missingParameterException(MissingServletRequestParameterException e,
			HttpServletRequest request) {
		logger.error("请求参数不存在：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getParameterName(), e);
		if ("token".equals(e.getParameterName())) {
			return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.RELOGIN.getCode()));
		} else {
			return ResponseEntity.badRequest()
					.body(ResultUtil.setBaseResult(ResultCode.NOPARAMS.getCode(), e.getParameterName() + " 参数未输"));
		}
	}

	@ExceptionHandler(MissingParamException.class)
	public ResponseEntity<BaseResult> missingParamException(MissingParamException e, HttpServletRequest request) {
		logger.error("请求参数不存在：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getParameterName(), e);
		if ("token".equals(e.getParameterName())) {
			return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.RELOGIN.getCode()));
		} else {
			return ResponseEntity.badRequest()
					.body(ResultUtil.setBaseResult(ResultCode.NOPARAMS.getCode(), e.getParameterName() + " 参数未输"));
		}

	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<BaseResult> argumentTypeHandler(MethodArgumentTypeMismatchException e,
			HttpServletRequest request) {
		logger.error("请求参数的类型不合法：{} {} ,param={}", request.getMethod(), request.getRequestURI(), e.getName(), e);
		return ResponseEntity.badRequest().body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "请求参数的类型不合法！"));
	}

	@ExceptionHandler(ObjectAlreadyExistsException.class)
	public ResponseEntity<BaseResult> objectAlreadyExists(ObjectAlreadyExistsException e) {
		logger.error("数据已经存在异常:" + e.getParam());
		return ResponseEntity.badRequest()
				.body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（数据已经存在）"));
	}

	@ExceptionHandler(ParamNotFoundException.class)
	public ResponseEntity<BaseResult> paramNotFound(ParamNotFoundException e, HttpServletRequest request) {
		logger.error("根据请求参数获取不到对象,method:{},uri:{},param:{}", request.getMethod(), request.getRequestURL(),
				e.getParam());
		return ResponseEntity.badRequest()
				.body(ResultUtil.setBaseResult(ResultCode.FAILY.getCode(), "系统繁忙!请稍后再试！（根据请求参数获取不到对象）"));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<BaseResult> requestUnauthorizedHandler(HttpServletRequest request) {
		logger.error("请求未授权：{} {} ", request.getMethod(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<BaseResult> requestForbiddenHandler(ForbiddenException e, HttpServletRequest request) {
		logger.error("{}：{} {} ", e.getMessage(), request.getMethod(), request.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN)
				.body(ResultUtil.setBaseResult(ResultCode.NOPERMISSION.getCode()));
	}
}
