package com.springboot.study.utils.restultful;

/**
 * @className: SingleResult
 * @author: XX
 * @date: 2018/9/12 09:02
 * @description:
 */
public class SingleResult extends BaseResult {

    
	/**
	* @Fields field:field:{todo}(用一句话描述这个变量表示什么)
	*/
	private static final long serialVersionUID = 696556854275167818L;
	private Object resultData;

    public SingleResult() {
    }

    SingleResult(int resultCode, String resultMessage, Object resultData) {
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.resultData = resultData;
    }


    public Object getResultData() {
        return resultData;
    }

    public void setResultData(Object resultData) {
        this.resultData = resultData;
    }

    @Override
    public String toString() {
        return "SingleResult{" +
                " resultCode=" + resultCode +
                ", resutlState='" + resutlState + '\'' +
                ", resultMessage='" + resultMessage + '\'' +
                ", resultData=" + resultData +
                '}';
    }
}
