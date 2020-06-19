package com.moon.store.test.thread;

public class TestThreadLocal {

    public static void main(String[] args) {
        Thread t = new Thread();//ThreadLocal.ThreadLocalMap threadLocals = null;
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("hello");
        tl.get();



    }

}

