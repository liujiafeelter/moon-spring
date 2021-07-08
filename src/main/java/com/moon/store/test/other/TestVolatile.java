package com.moon.store.test.other;

/**
 * 线程可见性
 */
public class TestVolatile {
    volatile boolean running = true;

    public void run(){
        System.out.println("test start");
        while (running){
        }
        System.out.println("test end");
    }

    public static void main(String[] args)throws Exception {
        TestVolatile test = new TestVolatile();
        Thread t = new Thread(test::run, "test111");
        t.start();

        Thread.sleep(1000);
        test.running = false;
        System.out.println("main end");
    }


}
