package com.mic.libokhttp;

public class FOkhttpClient {

     final Dispatcher dispatcher;
    public FOkhttpClient(Builder builder) {
        this.dispatcher=builder.dispatcher;
    }

    public FOkhttpClient() {
        this(new Builder());
    }

    public FCall newCall(FRequest request) {
        return RealCall.newCall(request,this);
    }

    public static class Builder{
        Dispatcher dispatcher;

        int connectTimeOut;
        int readTimeOut;

        public Builder() {
            dispatcher=new Dispatcher();
        }

        public FOkhttpClient build(){
            return new FOkhttpClient(this);
        }
    }
}
