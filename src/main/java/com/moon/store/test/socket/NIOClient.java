package com.moon.store.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws IOException, InterruptedException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8080));
        ByteBuffer writeBuffer = ByteBuffer.allocate(32);
        writeBuffer.put("helloaaaaaaaaaaaaa".getBytes());
        writeBuffer.flip();
        socketChannel.write(writeBuffer);
    }

}
