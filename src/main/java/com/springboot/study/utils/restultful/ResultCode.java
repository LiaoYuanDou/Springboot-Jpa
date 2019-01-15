package com.springboot.study.utils.restultful;

import java.io.Serializable;

/**
 * @ClassName: ResultCode
 * @Author: XX
 * @Date: 2018/7/31 13:57
 * @Description: 常见返回码定义类
 */
public enum ResultCode implements Serializable {
    /**
     * 强制更新
     */
    FORCEDUPDATE(-9, "您的APP版本过低，请先更新APP!"),
    /**
     * 重复提价
     */
    NOREPEATSUBMIT(-8, "请不要重复提交请求!"),
    /**
     * 恶搞url
     */
    INCORRECTINFORMATION(-7, "您没有上过这节课，请不要随意修改课程ID哦！"),
    /**
     * 账号或密码错误
     */
    ACCOUNTUNBIND(-6, "账号没有与第三方平台绑定"),
    /**
     * 账号或密码错误
     */
    ACCOUNTWRONG(-5, "账号或密码错误"),
    /**
     * 数据库错误
     */
    DATABASEERROR(-4, "数据库错误"),
    /**
     * 用户权限不足
     */
    NOPERMISSION(-3, "用户权限不足"),
    /**
     * 必输参数未输
     */
    NOPARAMS(-2, "必输参数未输"),
    /**
     * 登录失效，请重新登录
     */
    RELOGIN(-1, "登录失效，请重新登录"),
    /**
     * 操作成功
     */
    SUCCESS(0, "操作成功"),
    /**
     * 操作失败
     */
    FAILY(1, "操作失败");

    private final int code;
    private final String message;

    private ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
