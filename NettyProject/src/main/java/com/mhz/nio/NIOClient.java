package com.mhz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient {

    public static void main(String[] args) throws Exception {
        // 得到一个网络通道
        SocketChannel channel = SocketChannel.open();
        // 设置非阻塞
        channel.configureBlocking(false);
        // 获取IP地址, 还有端口号
        InetSocketAddress address = new InetSocketAddress("127.0.0.1", 6666);

        // 没有链接成功
        if (!channel.connect(address)) {
            // 链接没有结束
            while (!channel.finishConnect()) {
                System.out.println("因为链接需要时间, 客户端没有阻塞, 可以做其他的工作");
            }
        }
        // 链接成功
        String str = "Hello Ceo";
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 把buffer里面的数据 , 写入到通道里面
        channel.write(buffer);
        System.in.read();

    }
}
