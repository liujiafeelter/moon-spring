package com.moon.store.test.other;

/**
 * 指令重排
 */
public class TestVolatile2 {
    int a = 0;
    boolean flag = false;

    public void testA(){
        a = 1;
        flag = true;
    }

    public void testB(){
        if (flag){
            a = a + 5;
            System.out.println(a);
        }
    }

    public static void main(String[] args) {
        TestVolatile2 a = new TestVolatile2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                a.testA();
            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                a.testB();
            }
        },"B").start();
    }


}
