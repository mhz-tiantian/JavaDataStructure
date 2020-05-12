package com.mhz.bio;

import org.omg.Messaging.SyncScopeHelper;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BIOService {
    public static void main(String[] args) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器启动了");
        while (true) {
            // 监听, 等待客户端链接
            final Socket socket = serverSocket.accept();
            System.out.println("链接到一个客户端");
            // 就创建一个线程 与之通讯
            newCachedThreadPool.execute(new Runnable() {
                public void run() {
                    handler(socket);
                }
            });
        }
    }

    public static void handler(Socket socket) {

        try {
            System.out.println("线程信息===" + Thread.currentThread().getName() + "线程的id" + Thread.currentThread().getId());
            byte[] bytes = new byte[1024];
            InputStream inputStream = socket.getInputStream();
            while (true) {
                System.out.println("线程信息===" + Thread.currentThread().getName() + "线程的id" + Thread.currentThread().getId());
                int read = inputStream.read(bytes);
                if (read != -1) {
                    // 输入客户端的数据
                    System.out.println(new String(bytes, 0, read));
                } else {
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            System.out.println("关闭和Client的链接");
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
