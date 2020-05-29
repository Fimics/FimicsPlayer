package com.mic.core.thirdparty.okhttp;

public class ApiResponse<T> {
    public boolean success;
    public int status;
    public String message;
    public T body;
}
