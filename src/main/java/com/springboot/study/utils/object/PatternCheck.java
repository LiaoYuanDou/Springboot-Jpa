package com.springboot.study.utils.object;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: PatternCheck
 * @Author: XX
 * @Date: 2018/8/9 09:05
 * @Description: 校验类
 */
public class PatternCheck {

	public final static int PWD_MAX_LENGTH = 20;

	public final static int PWD_MIN_LENGTH = 20;

	/**
	 * checkPassword
	 *
	 * @param password
	 *            密码
	 * @return
	 * @Author XX
	 * @Description 校验密码是否合格
	 * @Date 2018/8/9 9:08
	 */
	public static String checkPassword(String password) {
		if (StringUtils.isBlank(password)) {
			return "密码不能为空！";
		}

		if (password.length() < PWD_MIN_LENGTH || password.length() > PWD_MAX_LENGTH) {
			return "密码长度必须在6-20位之间！";
		}

		if (!verification(password)) {
			return "密码必须包含 字母、数字";
		}

		String passwordReg = "^[0-9a-zA-Z!@#$%^&*()]{6,20}$";
		// String passwordReg="^[0-9a-zA-Z]{6,20}$";
		Pattern passwordPattern = Pattern.compile(passwordReg);
		Matcher passwordMatch = passwordPattern.matcher(password);
		if (!passwordMatch.matches()) {
			return "密码只能包含字母、数字和特殊字符!@#$%^&*()！";
		}
		return null;
	}

	/**
	 * @param password
	 *            密码
	 * @return
	 * @Author XX
	 * @Description 验证密码格式 必须包含字母和数字组成
	 * @Date 2018/8/9 9:09
	 */
	public static boolean verification(String password) {
		int num = 0;
		// 字母
		String regUpper = "[a-zA-Z]+";
		Pattern upperPattern = Pattern.compile(regUpper);
		Matcher upperMatch = upperPattern.matcher(password);
		if (upperMatch.find()) {
			num++;
		}

		// 数字
		String regNumber = "[0-9]+";
		Pattern numberPattern = Pattern.compile(regNumber);
		Matcher numberMatch = numberPattern.matcher(password);
		if (numberMatch.find()) {
			num++;
		}

		return num == 2;
	}

	/**
	 * isPhone
	 *
	 * @param phone
	 *            手机号
	 * @return
	 * @Author XX
	 * @Description 校验手机号
	 * @Date 2018/8/9 9:13
	 */
	public static boolean isPhone(String phone) {
		String phonePattern = "^1[0-9]{10}$";
		Pattern pattern = Pattern.compile(phonePattern);
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	/**
	 * isRecommendCode
	 *
	 * @param recommendCode
	 *            推荐码
	 * @return
	 * @Author XX
	 * @Description 推荐码是否合格
	 * @Date 2018/8/9 9:54
	 */
	public static boolean isRecommendCode(String recommendCode) {
		String recommendCodePattern = "[0-9a-zA-Z]+";
		Pattern codePattern = Pattern.compile(recommendCodePattern);
		Matcher codematcher = codePattern.matcher(recommendCode);
		return codematcher.matches();
	}

	/**
	 * isEmail
	 *
	 * @param email
	 *            邮箱
	 * @return
	 * @Author XX
	 * @Description 验证是否为正确的邮箱号
	 * @Date 2018/8/9 18:41
	 */
	public static boolean isEmail(String email) {
		// 1、\\w+表示@之前至少要输入一个匹配字母或数字或下划线 \\w 单词字符：[a-zA-Z_0-9]
		// 2、(\\w+\\.)表示域名. 如新浪邮箱域名是sina.com.cn
		// {1,3}表示可以出现一次或两次或者三次.
		String emailPattern = ".*@(\\w+\\.){1,3}\\w+";
		Pattern emailPat = Pattern.compile(emailPattern);
		Matcher emailMatcher = emailPat.matcher(email);
		return emailMatcher.matches();
	}

	/**
	 * @param
	 * @return
	 * @Author lk
	 * @Description 自定义正则表达式
	 * @Date 2018/11/2 14:47
	 */
	public static boolean commonPattern(String patternStr, String reg) {
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(patternStr);
		return matcher.matches();
	}

	/**
	 * 校验是否英文名字
	 *
	 * @param
	 * @return
	 * @author XX
	 * @date 2018/11/5 13:54
	 */
	public static boolean checkEnglishName(String userName) {
		String emailPattern = "^[a-zA-Z]*";
		Pattern emailPat = Pattern.compile(emailPattern);
		Matcher emailMatcher = emailPat.matcher(userName);
		return emailMatcher.matches();
	}

	public static String strReplacePattern(String regex, String strSrc, String replaceMentStr) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(strSrc);
		StringBuffer sb = new StringBuffer();
		boolean result = matcher.find();
		// 如果匹配成功就替换
		while (result) {
			matcher.appendReplacement(sb, replaceMentStr);
			// 继续下一步匹配
			result = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	public static void main(String[] args) {
		String s = strReplacePattern("<img .*/>",
				"<p><img src='https://test151.talk915.com/resourceProxy/fdfsDownload/group2/M00/00/57/Cswq7FwN1EGAArgRAAIFU9VFLs0500.jpg' _src='https://test151.talk915.com/resourceProxy/fdfsDownload/group2/M00/00/57/Cswq7FwN1EGAArgRAAIFU9VFLs0500.jpg' style='width: 112px; height: 77px;'/>&nbsp;wt</p>",
				"");
		System.out.println(s);
	}

}
