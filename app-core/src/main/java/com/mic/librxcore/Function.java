package com.mic.librxcore;



public interface Function<T,R> {
    R apply(T t) throws Exception;
}
