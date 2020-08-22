package com.moon.store.test;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestRateLimiter {

    static RateLimiter rateLimiter = RateLimiter.create(1.0);
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100,
            100, TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>());

    public static void main(String[] args) {
        for (int i=1; i<=100; i++){
            submitTask(new Task(i));
        }
    }

    public static void submitTask(Task task) {
        rateLimiter.acquire();
        executor.execute(task);
    }

}

class Task implements Runnable{

    int i;

    public Task(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        System.out.println("Task:" + i);
        try {
            //Thread.sleep(10000);
        }catch (Exception e){
        }
    }

}







