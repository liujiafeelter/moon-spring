package com.moon.store.test.socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

/**
 * https://blog.csdn.net/u013857458/article/details/82424104
 */
public class NIOServer3 {

    public static void main(String[] args) throws IOException{
        NIOServer3 NIOServer = new NIOServer3();
        NIOServer.run();
    }

    private void run()throws IOException{
        Worker worker = new Worker();
        worker.register();
        Thread thread = new Thread(worker);
        thread.start();
    }


    class Worker implements Runnable {
        private Selector selector = null;

        public Worker() throws IOException{
            selector = Selector.open();//轮询器
        }

        public void register() throws IOException{
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }

        public void run() {
            try {
                while (true){
                    selector.select();//阻塞到至少有一个通道在你注册的事件上就绪了
                    Iterator<SelectionKey> it = selector.selectedKeys().iterator();
                    while (it.hasNext()){
                        SelectionKey key = it.next();
                        it.remove();
                        if(key.isAcceptable()){
                            ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            System.out.println("aaaaaaaaaaaaaaaaa:" + socketChannel);
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            System.out.println("accept a client : " + socketChannel.socket().getInetAddress().getHostName());
                        }else if(key.isReadable()){
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            System.out.println("bbbbbbbbbbbbbbbbb:" + socketChannel);
                            ByteBuffer buffer = ByteBuffer.allocate(1024);
                            socketChannel.read(buffer);
                            buffer.flip();
                            System.out.println("收到客户端" + socketChannel.socket().getInetAddress().getHostName() + "的数据：" + new String(buffer.array()));
                        }
                    }
                }
            }catch (IOException e){
                System.out.println(e);
            }
        }


    }

}
