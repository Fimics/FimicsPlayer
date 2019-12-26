package com.mic.libokhttp;

import java.util.HashMap;

@SuppressWarnings("unused")
public class FRequest {

    public String url;
    public Method method;
    public HashMap<String,String> headers ;
    public FRequestBody requestBody;
    public FPublicParams publicParams;
    public String contentType;



    public FRequest(Builder builder) {
        this.url = builder.url;
        this.method=builder.method;
        this.headers=builder.headers;
        this.requestBody=builder.requestBody;
        this.publicParams=builder.publicParams;
        this.contentType=builder.contenType;
    }

    public FRequestBody requestBody() {
        return requestBody;
    }

    public String url() {
        return url;
    }

    public void header(String key, String value) {
        headers.put(key,value);
    }

    public static class Builder {

        String url;
        Method method=Method.GET;
        HashMap<String,String> headers;
        FRequestBody requestBody;
        FPublicParams publicParams;
        String contenType;


        public Builder() {
            headers = new HashMap<String,String>();
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder get(){
            method=Method.GET;
            return this;
        }

        public Builder post(FRequestBody body){
            method=Method.POST;
            this.requestBody=body;
            return this;
        }

        public Builder  header(String key,String value){
            headers.put(key,value);
            return this;
        }


        public Builder publicParams(FPublicParams publicParams){
            this.publicParams =publicParams;
            return this;
        }

        public Builder contentType(String contenType){
            this.contenType=contenType;
            return  this;
        }

        public FRequest build() {
            return new FRequest(this);
        }
    }
}
