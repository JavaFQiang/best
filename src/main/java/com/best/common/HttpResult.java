package com.best.common;

import java.io.Serializable;

/**
 *
 */
public class HttpResult implements Serializable {
    private static final long serialVersionUID = -3227087774288815716L;

    /**
     * 接口执行成功
     */
    public static final int EXECUTE_SUCCESSED = 0;
    public static final String SUCCESSED_INFO="请求执行成功";

    /**
     * 接口执行失败
     */
    public static final int EXECUTE_FAILED = -1;
    public static final String FAILED_INFO="请求执行失败";

    /**
     * 通用错误定义
     */
    public static final int ERRORCODE_MODULE_NOTFOUND = -2;
    public static final String ERRORINFO_MODULE_NOTFOUND = "未找到指定的模块";

    public static final int ERRORCODE_XAUTH_UNSETPARAMS = -3;
    public static final String ERRORINFO_XAUTH_UNSETPARAMS = "xauth参数不正确";

    public static final int ERRORCODE_XAUTH_INVALIDNONCE = -4;
    public static final String ERRORINFO_XAUTH_INVALIDNONCE = "重复请求";

    public static final int ERRORCODE_XAUTH_INVALIDSIGNATURE = -5;
    public static final String ERRORINFO_XAUTH_INVALIDSIGNATURE = "签名验证失败";

    public static final int ERRORCODE_XAUTH_NEEDLOGIN = -6;
    public static final String ERRORINFO_XAUTH_NEEDLOGIN = "需要登录才能访问";

    public static final int ERRORCODE_XAUTH_INVALIDUSERINFO = -7;
    public static final String ERRORINFO_XAUTH_INVALIDUSERINFO = "登录信息失效";

    public static final int ERRORCODE_API_INVALIDPARAMS = -8;
    public static final String ERRORINFO_API_INVALIDPARAMS = "参数不正确";

    //DB ERROR
    public static final int ERRORCODE_DB_REQFAILED = -9;
    public static final String ERRORINFO_DB_REQFAILED = "数据请求失败,请稍候再试";

    public static final int ERRORCODE_DB_DATANOTFOUND = -11;
    public static final String ERRORINFO_DB_DATANOTFOUND = "没有这条记录";

    public static final int ERRORCODE_DB_INSERTFAILED = -14;
    public static final String ERRORINFO_DB_INSERTFAILED = "添加数据失败,请稍候再试";

    public static final int ERRORCODE_DB_DUPLICATEKEY = -15;
    public static final String ERRORINFO_DB_DUPLICATEKEY = "插入重复数据";


    public static String getErrorinfo(int errorcode) {
        switch(errorcode) {
            case EXECUTE_FAILED:
                return FAILED_INFO;
            case ERRORCODE_MODULE_NOTFOUND:
                return ERRORINFO_MODULE_NOTFOUND;
            case ERRORCODE_XAUTH_UNSETPARAMS:
                return ERRORINFO_XAUTH_UNSETPARAMS;
            case ERRORCODE_XAUTH_INVALIDNONCE:
                return ERRORINFO_XAUTH_INVALIDNONCE;
            case ERRORCODE_XAUTH_INVALIDSIGNATURE:
                return ERRORINFO_XAUTH_INVALIDSIGNATURE;
            case ERRORCODE_XAUTH_NEEDLOGIN:
                return ERRORINFO_XAUTH_NEEDLOGIN;
            case ERRORCODE_XAUTH_INVALIDUSERINFO:
                return ERRORINFO_XAUTH_INVALIDUSERINFO;
            case ERRORCODE_API_INVALIDPARAMS:
                return ERRORINFO_API_INVALIDPARAMS;
            case ERRORCODE_DB_REQFAILED:
                return ERRORINFO_DB_REQFAILED;
            case ERRORCODE_DB_INSERTFAILED:
                return ERRORINFO_DB_INSERTFAILED;
            case ERRORCODE_DB_DATANOTFOUND:
                return ERRORINFO_DB_DATANOTFOUND;
            case ERRORCODE_DB_DUPLICATEKEY:
                return ERRORINFO_DB_DUPLICATEKEY;
            default:
                assert false;
                return "";
        }
    }
    public static HttpResult makeSuccessedResult(String info) {
        HttpResult result = new HttpResult();
        result.setCode(EXECUTE_SUCCESSED);
        result.setInfo(info);
        return result;
    }
    public static HttpResult makeSuccessedResult(ResultData data) {
        HttpResult result = new HttpResult();
        result.setCode(EXECUTE_SUCCESSED);
        result.setInfo(SUCCESSED_INFO);
        result.setData(data);
        return result;
    }

    public static HttpResult makeSuccessedResult(String info, ResultData data) {
        HttpResult result = new HttpResult();
        result.setCode(EXECUTE_SUCCESSED);
        result.setInfo(info);
        result.setData(data);
        return result;
    }

    public static HttpResult makeFailedResult(int code) {
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setInfo(getErrorinfo(code));
        return result;
    }

    public static HttpResult makeFailedResult(int code, String info) {
        HttpResult result = new HttpResult();
        result.setCode(code);
        result.setInfo(info);
        return result;
    }

    /**
     * 返回码，0为成功，其余为失败
     */
    private int code;

    /**
     * 执行返回信息，对于EXECUTE_FAILED类型错误，客户端可以直接把info信息提示给用户
     */
    private String info;

    /**
     * 返回数据
     */
    private ResultData data;

    public ResultData getData() {
        return data;
    }

    public void setData(ResultData data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
