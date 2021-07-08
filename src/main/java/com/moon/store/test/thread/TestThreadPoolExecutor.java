package com.moon.store.test.thread;

import java.util.concurrent.*;

public class TestThreadPoolExecutor {

    public static void main(String[] args) {

        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 200, TimeUnit.MILLISECONDS,
                new LinkedBlockingDeque<Runnable>(5), new ThreadFactory(){
            @Override
            public Thread newThread(Runnable r) {
                Thread newThr = new Thread(r);
                newThr.setName("MyThread-"+ newThr.getId());
                return  newThr;
            }
        });

        for (int i=1; i<=18; i++){
            executor.execute(new Task(i));
            try {
                Thread.sleep(10);
            }catch (Exception e){}
        }
    }

}

class Task implements Runnable{
    private int num;
    public Task(int num){
        this.num = num;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "task:" + num);
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
