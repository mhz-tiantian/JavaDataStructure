package com.mhz.im;

import com.sun.deploy.ui.UITextArea;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;

/**
 * 1. 链接服务器端,
 * 2. 发送消息到服务器端
 */
public class IMClient {
    private final static String HOST = "127.0.0.1";
    private final static int PORT = 6667;
    private Selector mSelectors;
    private SocketChannel mSocketChannel;
    private String userName;


    public IMClient() {
        try {
            mSelectors = Selector.open();
            mSocketChannel = SocketChannel.open(new InetSocketAddress(HOST, PORT));
            mSocketChannel.configureBlocking(false);
            mSocketChannel.register(mSelectors, SelectionKey.OP_READ);
            userName = mSocketChannel.getLocalAddress().toString().substring(1);
            System.out.println(userName + "is ok");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendInfo(String info) {
        info = userName + "说：" + info;
        try {
            mSocketChannel.write(ByteBuffer.wrap(info.getBytes()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void readInfo() {
        try {
            int readCount = mSelectors.select();
            System.out.println("readCount===" + readCount);
            if (readCount > 0) {
                Iterator<SelectionKey> iterator = mSelectors.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isReadable()) {
                        SocketChannel sc = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        sc.read(buffer);
                        String msg = new String(buffer.array());
                        System.out.println(msg.trim());
                    }
                }
                // 移除当前的key
                iterator.remove();
            } else {
                System.out.println("沒有可用的通道===");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        final IMClient imClient = new IMClient();
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    imClient.readInfo();
                    try {
                        Thread.currentThread().sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            imClient.sendInfo(s);
        }
    }
}
