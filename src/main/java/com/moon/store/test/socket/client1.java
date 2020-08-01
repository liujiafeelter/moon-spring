package com.moon.store.test.socket;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class client1 {

    public static void main(String[] args)  throws Exception {
        Socket socket = new Socket("127.0.0.1", 8080);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bufferedWriter.write("你好啊server");
        bufferedWriter.flush();
        Thread.sleep(200000);
        socket.close();
    }

}
