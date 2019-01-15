package com.springboot.study.utils.restultful;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @ClassName: ResultUtil
 * @Author: XX
 * @Date: 2018/7/31 14:06
 * @Description: 返回结果工具类
 */
public class ResultUtil {

    private ResultUtil() {
    }

    private static Logger logger = LoggerFactory.getLogger(ResultUtil.class);

    /**
     * 空list
     */
    private static final List<Map<String, Object>> EMPTY_LIST = new ArrayList<Map<String, Object>>();

    /**
     * 空map
     */
    private static final Map<String, Object> EMPTY_MAP = new HashMap<String, Object>();

    /**
     * setResult
     *
     * @param code     返回码
     * @param dataList 返回数据
     * @return Result 返回模板
     * @Author XX
     * @Description 返回模板样式数据（选择通用的返回信息）
     * @Date 2018/7/31 14:14
     */
    public static Result setResult(int code, List<?> dataList) {

        Result result = new Result();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResultMessage(resultCode.getMessage());
                result.setResutlState(resultCode.name());
                break;
            }
        }
        result.setResultCode(code);

        Data data = new Data();
        data.setDataList(dataList);
        result.setResultData(data);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * setResult
     *
     * @param code          返回码
     * @param resultMessage 返回信息
     * @param dataList      返回数据
     * @return Result 返回模板
     * @Author XX
     * @Description 返回模板样式数据（选择自己传入的返回信息）
     * @Date 2018/7/31 14:26
     */
    public static Result setResult(int code, String resultMessage, List<?> dataList) {
        Result result = new Result();

        Data data = new Data(dataList);
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                break;
            }
        }
        result.setResultData(data);
        result.setResultCode(code);
        result.setResultMessage(resultMessage);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * setResult
     *
     * @param code          返回码
     * @param resultMessage 返回信息
     * @return Result 返回模板
     * @Author XX
     * @Description 返回模板样式数据（选择自己传入的返回信息,空list）
     * @Date 2018/8/7 17:49
     */
    public static Result setResult(int code, String resultMessage) {
        Result result = new Result();

        Data data = new Data();
        data.setDataList(EMPTY_LIST);
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                break;
            }
        }
        result.setResultData(data);
        result.setResultCode(code);
        result.setResultMessage(resultMessage);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * @param code        返回码
     * @param dataList    返回数据
     * @param totalCount  总条数
     * @param currentPage 当前页
     * @param pageSize    页面显示多少条
     * @return Result 返回模板
     * @Author XX
     * @Description 带分页返回模板样式数据（选择通用的返回信息）
     * @Date 2018/7/31 14:28
     */
    public static Result setResult(int code, List<?> dataList, int totalCount, int currentPage, int pageSize) {

        Result result = new Result();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResultMessage(resultCode.getMessage());
                result.setResutlState(resultCode.name());
                break;
            }
        }
        result.setResultCode(code);

        Data data = new Data(dataList, totalCount, currentPage, pageSize);
        result.setResultData(data);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;

    }

    /**
     * @param code          返回码
     * @param resultMessage 返回信息
     * @param dataList      返回数据
     * @param totalCount    总条数
     * @param currentPage   当前页
     * @param pageSize      页面显示多少条
     * @return Result 返回模板
     * @Author XX
     * @Description 带分页返回模板样式数据（选择自己传入的返回信息）
     * @Date 2018/7/31 14:28
     */
    public static Result setResult(int code, String resultMessage, List<?> dataList, int totalCount, int currentPage, int pageSize) {
        Result result = new Result();

        Data data = new Data(dataList, totalCount, currentPage, pageSize);
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                break;
            }
        }
        result.setResultCode(code);
        result.setResultMessage(resultMessage);
        result.setResultData(data);

        logger.info("》》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * @param code          返回码
     * @param resultMessage 返回信息
     * @param data          返回数据
     * @return Result 返回模板
     * @author XX
     * @description 返回模板样式数据（选择自己传入的返回信息）
     * @date 2018/8/15 15:26
     */
    public static Result setResult(int code, String resultMessage, Data data) {
        Result result = new Result(code, resultMessage, data);
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                break;
            }
        }

        logger.info("》》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * setResult
     *
     * @param code 返回码
     * @param data 返回数据
     * @return Result 返回模板
     * @Author XX
     * @Description 返回模板样式数据（选择通用的返回信息）
     * @@Entity @DynamicInsert @DynamicUpdate 2018/8/15 15:26
     */
    public static Result setResult(int code, Data data) {

        Result result = new Result();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                result.setResultMessage(resultCode.getMessage());
                break;
            }
        }
        result.setResultCode(code);
        result.setResultData(data);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * setResult
     *
     * @param code 返回码
     * @return Result 返回模板
     * @Author XX
     * @Description 返回模板样式数据（选择自己传入的返回信息,空list）
     * @Date 2018/8/16 10:49
     */
    public static Result setResult(int code) {
        Result result = new Result();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                result.setResutlState(resultCode.name());
                result.setResultMessage(resultCode.getMessage());
                break;
            }
        }
        Data data = new Data(EMPTY_LIST);
        result.setResultData(data);
        result.setResultCode(code);

        logger.info("》》》》》》》》》》》》》》返回结果: " + result.toString());
        return result;
    }

    /**
     * 返回模板Map样式数据（选择通用的返回信息）
     *
     * @param code    返回码
     * @param dataMap 返回数据
     * @return SingleResult 返回模板
     * @author XX
     * @description 返回模板Map样式数据（选择通用的返回信息）
     * @date 2018/9/12 9:06
     */
    public static SingleResult setSingleResult(int code, Object dataMap) {

        SingleResult singleResult = new SingleResult();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                singleResult.setResutlState(resultCode.name());
                singleResult.setResultMessage(resultCode.getMessage());
                break;
            }
        }
        singleResult.setResultCode(code);
        singleResult.setResultData(dataMap);

        logger.info("》》》》》》》》》》》》》》返回结果: " + singleResult.toString());
        return singleResult;
    }

    /**
     * 返回模板Map样式数据（选择通用的返回信息）
     *
     * @param code    返回码
     * @param dataMap 返回数据
     * @return SingleResult 返回模板
     * @author XX
     * @description 返回模板Map样式数据（选择通用的返回信息）
     * @date 2018/9/12 9:06
     */
    public static SingleResult setSingleResult(int code, String resultMessage, Object dataMap) {
        SingleResult singleResult = new SingleResult();

        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                singleResult.setResutlState(resultCode.name());
                break;
            }
        }
        singleResult.setResultCode(code);
        singleResult.setResultMessage(resultMessage);
        singleResult.setResultData(dataMap);
        logger.info("》》》》》》》》》》》》》》返回结果: " + singleResult.toString());
        return singleResult;
    }

    /**
     * 返回模板Map样式数据（选择通用的返回信息）
     *
     * @param code 返回码
     * @return SingleResult 返回模板
     * @author XX
     * @description 返回模板Map样式数据（选择通用的返回信息）
     * @date 2018/9/12 9:13
     */
    public static SingleResult setSingleResult(int code) {
        SingleResult singleResult = new SingleResult();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                singleResult.setResutlState(resultCode.name());
                singleResult.setResultMessage(resultCode.getMessage());
                break;
            }
        }
        singleResult.setResultData(EMPTY_MAP);
        singleResult.setResultCode(code);

        logger.info("》》》》》》》》》》》》》》返回结果: " + singleResult.toString());
        return singleResult;
    }

    /**
     * 返回模板Map样式数据（选择通用的返回信息）
     *
     * @param code          返回码
     * @param resultMessage 返回信息
     * @return SingleResult
     * @author XX
     * @description 返回模板Map样式数据（选择通用的返回信息）
     * @date 2018/9/13 9:43
     */
    public static SingleResult setSingleResult(int code, String resultMessage) {
        SingleResult singleResult = new SingleResult(code, resultMessage, EMPTY_MAP);

        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                singleResult.setResutlState(resultCode.name());
                break;
            }
        }
        logger.info("》》》》》》》》》》》》》》返回结果: " + singleResult.toString());
        return singleResult;
    }

    /**
     * 返回模板信息（没有数据data）
     *
     * @param code          返回码
     * @param resultMessage 返回信息
     * @return BaseResult
     * @author XX
     * @description 返回模板信息（没有数据data）
     * @date 2018/9/14 10:41
     */
    public static BaseResult setBaseResult(int code, String resultMessage) {
        BaseResult baseResult = new BaseResult();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                baseResult.setResutlState(resultCode.name());
                break;
            }
        }
        baseResult.setResultCode(code);
        baseResult.setResultMessage(resultMessage);
        logger.info("》》》》》》》》》》》》》》返回结果: " + baseResult.toString());
        return baseResult;
    }

    /**
     * 返回模板信息（没有数据data）
     *
     * @param code 返回码
     * @return BaseResult
     * @author XX
     * @description 返回模板信息（没有数据data）
     * @date 2018/9/14 10:41
     */
    public static BaseResult setBaseResult(int code) {
        BaseResult baseResult = new BaseResult();
        EnumSet<ResultCode> resultCodes = EnumSet.allOf(ResultCode.class);
        for (ResultCode resultCode : resultCodes) {
            if (resultCode.getCode() == code) {
                baseResult.setResutlState(resultCode.name());
                baseResult.setResultMessage(resultCode.getMessage());
                break;
            }
        }
        baseResult.setResultCode(code);

        logger.info("》》》》》》》》》》》》》》返回结果: " + baseResult.toString());
        return baseResult;
    }

}
