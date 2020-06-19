package com.moon.store.test.ThreadLocal;

import java.util.HashMap;
import java.util.Map;

public class TestThreadLocal {



    public static void main(String[] args) {
        TestThread thread1 = new TestThread("taiyang");
        TestThread thread2 = new TestThread("yueliang");

        thread1.start();
        thread2.start();
    }
}

class TestThread extends Thread {
    private static final ThreadLocal<Map<Object, Object>> resources = new ThreadLocal<Map<Object, Object>>();
    String name;
    public TestThread(String name){
        this.name = name;
    }
    public void run() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a", name);
        resources.set(map);
        System.out.println(Thread.currentThread() + resources.get().toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread() + resources.get().toString());
    }

}

