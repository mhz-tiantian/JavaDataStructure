package com.mhz.nio;

import sun.awt.image.OffScreenImage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOFileChannel03 {

    public static void main(String[] args) throws IOException {
        //D:\git\JavaDataStructure\NettyProject\1.text
        FileInputStream fileInputStream = new FileInputStream("D:\\git\\JavaDataStructure\\NettyProject\\1.text");
        FileChannel fileChannel01 = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\git\\JavaDataStructure\\NettyProject\\2.text");
        FileChannel fileChannel02 = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
        while (true) {
            // 清空buffer   复位, 防止出现  read==0 的情况出现
            byteBuffer.clear();
            int read = fileChannel01.read(byteBuffer);
            System.out.println("read===" + read);
            if (read == -1) {
                break;
            }
            byteBuffer.flip();
            fileChannel02.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();

    }

}
