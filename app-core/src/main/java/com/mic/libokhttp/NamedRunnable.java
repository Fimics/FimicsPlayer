package com.mic.libokhttp;

public abstract class NamedRunnable implements Runnable {

    @Override
    public void run() {
       execute();
    }

    public abstract void execute();
}
