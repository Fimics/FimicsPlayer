package com.mic.xsample.pattern.factory.factory2;

/**
 * 运行内存存储的 Factory
 * Created by hcDarren on 2017/9/24.
 */

public class MemoryIOFactory implements IOFactory{
    @Override
    public IOHandler createIOHandler() {
        return new MemoryIOHandler();
    }
}
