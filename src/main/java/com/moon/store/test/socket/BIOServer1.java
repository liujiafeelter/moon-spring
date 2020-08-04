package com.moon.store.test.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class BIOServer1 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while (true) {
            Socket socket = serverSocket.accept();
            InetAddress inetAddress = socket.getInetAddress();
            System.out.println("收到连接:" + inetAddress.getHostName());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String inputStr = bufferedReader.readLine();
            System.out.println("收到数据:" + inputStr);
            socket.close();
        }
    }

}
