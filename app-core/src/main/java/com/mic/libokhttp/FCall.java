package com.mic.libokhttp;

public interface FCall {

    void enqueue(FCallback callback);

    FResponse execute();
}
