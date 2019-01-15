package com.springboot.study.utils.restultful;

/**
 * @ClassName: Result
 * @Author: XX
 * @Date: 2018/7/31 13:54
 * @Description: 返回结果类
 */
public class Result extends BaseResult {

    
	/**
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = -8058940944373509297L;
	private Data resultData;

    public Result() {
    }

    Result(int resultCode, String resultMessage, Data resultData) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }


    public Data getResultData() {
        return resultData;
    }

    public void setResultData(Data resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", resutlState='" + resutlState + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
