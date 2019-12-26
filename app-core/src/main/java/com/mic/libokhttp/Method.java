package com.mic.libokhttp;

public enum Method {
    POST("POST"),GET("GET"),HEAD("HEAD"),DELETE("DELETE"),PUT("PUT"),PATCH("PATCH");

    private String name;

    Method(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean doOutput() {
        boolean doOutput =false;
        switch (this){
            case GET:
            case POST:
            case PUT:
            case HEAD:
                doOutput=true;
                break;
        }
        return doOutput;
    }
}
