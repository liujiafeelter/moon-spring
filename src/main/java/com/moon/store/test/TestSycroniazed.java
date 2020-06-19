package com.moon.store.test;

import org.openjdk.jol.info.ClassLayout;

public class TestSycroniazed {

    public static void main(String[] args) throws InterruptedException {
        m4();
    }

    //无锁
    public static void m1(){
        Object obj = new Object();
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    //匿名偏向锁
    public static void m2()throws InterruptedException{
        Thread.sleep(5000);
        Object obj = new Object();
        System.out.println("=================================================");
        System.out.println(ClassLayout.parseInstance(obj).toPrintable());
    }

    //偏向锁
    public static void m3()throws InterruptedException{
        Thread.sleep(5000);
        Object obj = new Object();
        System.out.println("=================================================");
        synchronized (obj){
            System.out.println(ClassLayout.parseInstance(obj).toPrintable());
        }
    }

    //偏向锁
    public static void m4()throws InterruptedException {
        Thread.sleep(5000);
        A obj = new A();

        Thread thread1 = new Thread(){
            @Override
            public void run() {
                System.out.println("thread1 locking");
                synchronized (obj){
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
                try {
                    //thread1退出同步代码块，且没有死亡
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace(); 
                }
            }
        };

        Thread thread2 = new Thread(){
            @Override
            public void run() {
                System.out.println("thread2 locking");
                synchronized (obj){
                    System.out.println(ClassLayout.parseInstance(obj).toPrintable());
                }
            }
        };
        thread1.start();
        //让thread1执行完同步代码块中方法。
        Thread.sleep(50);
        thread2.start();
    }

}
class A{
    int a;
}