package com.mic.libokhttp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FResponse {

    private final InputStream inputStream;
    public FResponse(InputStream inputStream) {
        this.inputStream =inputStream;
    }

    public String string(){

        String body =convertStreamToString(inputStream);

        return body;
    }

    public String convertStreamToString(InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();

        String line ;

        try{
            while ((line=reader.readLine())!=null){
                sb.append(line);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                is.close();
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return sb.toString();

    }

}
