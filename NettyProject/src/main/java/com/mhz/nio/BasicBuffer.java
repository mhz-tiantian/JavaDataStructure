package com.mhz.nio;

import java.nio.IntBuffer;

public class BasicBuffer {
    public static void main(String[] args) {
        // 创建 Buffer  可以存放5个 int 值
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for (int i = 0; i < intBuffer.capacity(); i++) {
            // 存放数据
            intBuffer.put(i * 2);
        }

        // 将buffer切换, 读写切换
        intBuffer.flip();

        // buffer先进先出
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }

    }
}
