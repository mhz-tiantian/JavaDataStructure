package com.mhz.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    public static void main(String[] args) throws Exception {

        // 创建ServerSocketChannel  ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 创建一个Selector 对象
        Selector selector = Selector.open();
        // 绑定一个端口6666 在服务段监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));

        //设置为非阻塞的
        serverSocketChannel.configureBlocking(false);
        // 把 ServerSocketChannel 注册到 Selector 关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) == 0) {
                System.out.println("服务器等待了 1秒, 没有链接  不等了");
                continue;
            }
            // 如果返回的大于0 就拿到SelectionKey的集合
            //selector.selectedKeys()返回关注时间的集合
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历集合
            Iterator<SelectionKey> keyIterator = selectionKeys.iterator();
            while (keyIterator.hasNext()) {
                SelectionKey next = keyIterator.next();

                if (next.isAcceptable()) {// 表示的是  如果是这个事件的话 OP_ACCEPT  isAcceptable()
                    // 就走下面的代码
                    // 为该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("客户端链接成功了, 生成了一个SocketChannel" + socketChannel.hashCode());                    // g关联一个Buffer
                    // 设置一个非阻塞的模式
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                // 发生 OP_READ 的事件的时候
                if (next.isReadable()) {
                    // 通过SelectionKey 反向获取到对应的channel
                    SocketChannel channel = (SocketChannel) next.channel();
                    // 获取到channel对应的buffer
                    ByteBuffer buffer = (ByteBuffer) next.attachment();
                    channel.read(buffer);
                    System.out.println("from 客户端" + new String(buffer.array()));
                }

                // 把SelectionKey 刪除掉， 防止重复操作
                keyIterator.remove();

            }

        }


    }
}
