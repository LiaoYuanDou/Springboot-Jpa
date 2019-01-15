package com.springboot.study.utils.norepeatsubmit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.springboot.study.utils.object.MapToObjectUtil;
import com.springboot.study.utils.object.ObjectUtil;
import com.springboot.study.utils.redis.RedisUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @className: NoRepeatSubmitAop
 * @author: XX
 * @date: 2018/12/29 14:19
 * @description: 防止重复提交 aop解析注解 防止表单重复提交拦截器
 */
@Aspect
@Component
public class NoRepeatSubmitAop {

	private static final Logger logger = LoggerFactory.getLogger(NoRepeatSubmitAop.class);

	@Pointcut("execution( * com.springboot.study.controller..*.*(..))")
	public void pointcutPackage() {

	}

	@Before("pointcutPackage() && @annotation(noRepeatSubmit)")
	public void doBefore(JoinPoint proceedingJoinPoint, NoRepeatSubmit noRepeatSubmit) {
		if (noRepeatSubmit != null) {
			/*
			 * HttpServletRequest request = null; HttpServletResponse response = null;
			 * 
			 * for (int i = 0; i < args.length; i++) { if(args[i] instanceof
			 * HttpServletRequest){ request = (HttpServletRequest) args[i]; continue; }
			 * if(args[i] instanceof HttpServletResponse){ response = (HttpServletResponse)
			 * args[i]; continue; } }
			 */
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			/*
			 * String sessionId =
			 * RequestContextHolder.getRequestAttributes().getSessionId();
			 */
			HttpServletRequest request = attributes.getRequest();
			HttpServletResponse response = attributes.getResponse();
			String requestURI = request.getRequestURI();
			String method = request.getMethod();
			String token = getAopToken(proceedingJoinPoint, request, method);

			// redis存取用户 路径 唯一标识
		}
	}

	@AfterReturning("pointcutPackage() && @annotation(noRepeatSubmit)")
	public void doAfterReturning(JoinPoint proceedingJoinPoint, NoRepeatSubmit noRepeatSubmit) {
		if (noRepeatSubmit != null) {
			ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
					.getRequestAttributes();
			HttpServletRequest request = attributes.getRequest();
			String requestURI = request.getRequestURI();
			String method = request.getMethod();
			String token = getAopToken(proceedingJoinPoint, request, method);
			// redis 删除用户 路径 唯一标识
		}
	}

	public String getAopToken(JoinPoint proceedingJoinPoint, HttpServletRequest request, String method) {
		String token = "";
		if ("GET".equals(method)) {
			token = request.getParameter("token");
		} else {
			Object[] args = proceedingJoinPoint.getArgs();
			for (int i = 0; i < args.length; i++) {
				if (args[i] instanceof Map) {
					Map map = (Map) args[i];
					token = String.valueOf(map.get("token"));
				} else {
					Map<?, ?> tokenMap = MapToObjectUtil.objectToMap(args[i]);
					token = String.valueOf(tokenMap.get("token"));
				}
				if (ObjectUtil.isNotBlank(token)) {
					return token;
				}
			}
		}
		return token;
	}

	public synchronized String getRedisKey(String requestURI, long userId) {
		StringBuilder noRepeatSubmitKey = new StringBuilder("SUBMIT:");
		noRepeatSubmitKey.append(userId).append(":");
		noRepeatSubmitKey.append(requestURI).append(":FLAG");
		return noRepeatSubmitKey.toString();
	}

}
