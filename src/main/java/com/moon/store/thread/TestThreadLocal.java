package com.moon.store.thread;

import java.util.Random;

public class TestThreadLocal {

    public static void main(String[] args) {
        Thread t = new Thread();//ThreadLocal.ThreadLocalMap threadLocals = null;
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("hello");
        tl.get();



    }

}

