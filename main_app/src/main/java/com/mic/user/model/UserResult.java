package com.mic.user.model;

import com.mic.frame.model.Result;

public class UserResult extends Result {

    // 成功是一个对象正常，不成功是一个 String （出错）
    public Object data;
}
