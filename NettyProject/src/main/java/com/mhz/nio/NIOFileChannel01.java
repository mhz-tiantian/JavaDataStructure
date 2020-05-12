package com.mhz.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel01 {

    // InputStream(输入流):输入流是用来读入数据的。- - - > > >读入
    //OutputStream(输出流):输出流是用来写出数据的。- - - > > >写出
    // 对于IO流而言，IO(input Output)输入输出的意思。在输入输出之间我们需要找一个参照物，
    // 然后判断相对于参照物而言是输入还是输出。而我们一般会把内存作为参照物。当我们打开一个应用程序时，
    // 计算机会把那个应用程序加载到内存，然后等待CPU执行，对内存就相当于输入。当我们写代码时，代码首先会进内存里面，
    // 按下Ctrl+S时，计算机会将内存中的代码保存到硬盘中，对内存而言就相当与输出。带有Input  Reader输入（读），Output Writer输出（写）。


    public static void main(String[] args) throws IOException {
        String str = "hello Netty FileChannel";
        // 创建一个输出流 -> channel
        FileOutputStream fileOutputStream = new FileOutputStream("d:\\file01.text");

        // 这个FileChannel
        FileChannel fileChannel = fileOutputStream.getChannel();

        // 创建一个缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        // 缓冲区 放入数据 写入数据
        byteBuffer.put(str.getBytes());

        // 對byteBuffer缓冲区进行反转, 可以进行读取了
        byteBuffer.flip();
        // 把缓冲区的内容 写入到通道里面
        fileChannel.write(byteBuffer);
        // 关闭文件流
        fileOutputStream.close();
    }
}
