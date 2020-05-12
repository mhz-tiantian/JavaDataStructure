package com.mhz.im;


import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * 直播群聊  服务端
 */
public class IMService {
    private ServerSocketChannel mListenerChannel;
    private Selector mSelector;
    // 端口号
    private static final int PROT = 6667;


    public IMService() {
        try {
            // 拿到 通道 服务端的通道
            mListenerChannel = ServerSocketChannel.open();
            // 拿到选择器  可以管理多个通道
            mSelector = Selector.open();
            // 通道监听  那个端口 这里监听的是8888  这个端口
            mListenerChannel.socket().bind(new InetSocketAddress(PROT));
            // 设置非阻塞的模式
            mListenerChannel.configureBlocking(false);
            // 注册 选择器, 并监听 OP_ACCEPT  这个事件
            mListenerChannel.register(mSelector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }
    }

    // 监听的方法
    public void listener() {
        try {
            while (true) {
                // 监听通道2 秒  是不是有事件发生
                int count = mSelector.select();
                if (count > 0) {
                    // 有事件处理
                    // 拿到SelectionKey的 迭代器  拿到选择器中目前 有时间发生的事件
                    Iterator<SelectionKey> iterator = mSelector.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        // 如果当前的时间 是 OP_ACCEPT 这个事件的时候  accept 是链接的事件
                        if (key.isAcceptable()) {
                            // 拿到活跃的 可以接受的通道 客户端链接的通道
                            SocketChannel sc = mListenerChannel.accept();
                            // 设置非阻塞
                            sc.configureBlocking(false);
                            // 在链接通道上 注册  选择器, 还有事件
                            sc.register(mSelector, SelectionKey.OP_READ);
                            System.out.println(sc.getRemoteAddress() + "客户端链接成功");

                        }
                        // 读取的事件  在服务器发生读取的事件， 说明有客户端 发送到服务器有内容了
                        if (key.isReadable()) {
                            // 服务器这时候要接受到内容， 并且转发到其他的客户端上
                            // 在写一个 方法
                            readData(key);
                        }
                    }
                    // 移除当前的key , 防止重复操作
                    iterator.remove();


                } else {
                    System.out.println("等待中......");
                }
            }

        } catch (Exception ex) {

        } finally {


        }

    }

    /**
     * 读取客户端的消息,
     *
     * @param key
     */
    private void readData(SelectionKey key) {
        SocketChannel channel = null;
        try {
            channel = (SocketChannel) key.channel();
            // 设置缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            // 读取到的长度
            int readCount = channel.read(byteBuffer);
            if (readCount > 0) {
                // 读取到数据
                String msg = new String(byteBuffer.array());
                // 在服务器端 打印下 客户端发送的消息
                System.out.println("from   客户端" + msg);
                // 向其他的客户端去发送消息
                sendInfoToOtherClients(msg, channel);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            try {
                System.out.println(channel.getRemoteAddress() + "离线了");
                // 取消注册
                key.cancel();
                // 关闭通道
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } finally {


        }
    }

    /**
     * @param msg  消息
     * @param self 客户端发送消息的 通道
     */
    private void sendInfoToOtherClients(String msg, SocketChannel self) throws IOException {
        System.out.println("服务器转发消息中");
        // 拿到注册到
        Set<SelectionKey> keys = mSelector.keys();
        for (SelectionKey key : keys) {
            // 转发的通道
            Channel targetChannel = key.channel();
            // 转发的通道 是一个SocketChannel 并且不是自己 客户端发送消息的 通道
            if (targetChannel instanceof SocketChannel && targetChannel != self) {
                SocketChannel destChannel = (SocketChannel) targetChannel;
                // 把消息装到 buffer里面去
                ByteBuffer byteBuffer = ByteBuffer.wrap(msg.getBytes());
                // 将buffer 的数据 写到通道里面
                destChannel.write(byteBuffer);
            }

        }

    }


    public static void main(String[] args) {
        IMService imService = new IMService();
        imService.listener();

    }
}
