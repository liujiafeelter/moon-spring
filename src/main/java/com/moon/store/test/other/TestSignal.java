package com.moon.store.test.other;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * kill -12 SIGUSR2
 * wait 5s
 * kill -15 SIGTERM
 */
public class TestSignal implements SignalHandler {

    public void addShutdownHook(){
        //kill -15时会执行
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("这个钩子启动");
                //socket.close tcp挥手
                System.out.println("这个钩子退出");
            }
        });
    }

    @Override
    public void handle(Signal signal) {
        System.out.println(signal.getName() + " is recevied.");
        if(signal.getName().equals("USR2")){
            //处理现场，比如停止接收新请求
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestSignal testSignalHandler = new TestSignal();
        testSignalHandler.addShutdownHook();
        //要在linux环境中执行
        Signal.handle(new Signal("USR2"), testSignalHandler);
        for (;;) {
            Thread.sleep(3000);
            System.out.println("running......");
        }
    }

}
