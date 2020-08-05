package com.moon.store.test.other;

/**
 * 1、double check 双重校验
 *  a、b两个线程都进入第一层判断里面if(singleton == null){
 *  a获取锁，创建好了对象，释放锁
 *  b得到CPU执行，获取锁，又创建了一个对象就
 *
 * 2、volatile 防止指令重排序
 *  创建对象也是分指令的：堆上分配空间，属性默认值，栈指针指向对象地址，初始化
 *  如果a线程new Singleton()后，栈指针指向对象地址了，还没有给对象初始化
 *  其他线程这时候进入第一层判断if(singleton == null){，不为null，则获取的是一个没有初始化的对象
 */
public class Singleton {

    private static volatile Singleton singleton;

    private Singleton(){}

    public static Singleton getInstance(){
        if(singleton == null){
            synchronized (Singleton.class){
                if(singleton == null){
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

}
