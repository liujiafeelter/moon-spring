package com.moon.store.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadServer2 {

    public static void main(String[] args) throws IOException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            executorService.submit(new SocketHandler(socket));
        }
    }

}

class SocketHandler implements Runnable{
    Socket socket = null;

    public SocketHandler(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("收到连接:" + inetAddress.getHostName());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputStr = bufferedReader.readLine();
            System.out.println("收到数据:" + inputStr);
            socket.close();
        }catch (IOException e){
        }
    }

}





