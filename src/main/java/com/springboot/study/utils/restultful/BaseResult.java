package com.springboot.study.utils.restultful;

import java.io.Serializable;

/**
 * @className: BaseResult
 * @author: XX
 * @date: 2018/9/14 10:24
 * @description: 返回结果基类
 */
public class BaseResult implements Serializable {

    
	/**
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -2445705147267237413L;
	protected int resultCode;
    protected String resutlState;
    protected String resultMessage;

    public BaseResult() {
    }

    BaseResult(int resultCode, String resutlState, String resultMessage) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resutlState = resutlState;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage;
    }

    public String getResutlState() {
        return resutlState;
    }

    public void setResutlState(String resutlState) {
        this.resutlState = resutlState;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    @Override
    public String toString() {
        return "BaseResult{" +
                "resultCode=" + resultCode +
                ", resutlState='" + resutlState + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                '}';
    }
}
