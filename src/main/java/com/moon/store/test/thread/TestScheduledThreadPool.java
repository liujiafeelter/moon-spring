package com.moon.store.test.thread;

import java.util.concurrent.*;

public class TestScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledService = Executors.newScheduledThreadPool(10, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread newThr = new Thread(r);
                newThr.setName("MyScheduledThread-"+ newThr.getId());
                return  newThr;
            }
        });
        scheduledService.scheduleAtFixedRate(new ScheduledTask(), 2000, 5000, TimeUnit.MILLISECONDS);
    }
}

class ScheduledTask implements Runnable{
    public ScheduledTask(){
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
    }

}