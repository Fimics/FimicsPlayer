package com.mic.frame.model;


/**
 * 兼容后台返回各种错误
 */
public class BaseResult {
    public String code;
    public String msg;

    public String getMsg() {
        return msg;
    }

    public String getCode() {
        return code;
    }

    public boolean isOk(){
        return "200".equals(code);
    }
}
