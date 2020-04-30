package com.mhz.datastructure.fanshe;

// 写一个程序, 判断char数组是一个 基本类型, 还是一个对象
public class PrimitiveDemo {

    public static void main(String[] agrs) {

        char[] chars = new char[2];
        chars[0] = '1';
        chars[1] = '2';
        PrimitiveDemo primitiveDemo = new PrimitiveDemo();
        primitiveDemo.checkArray(chars);
    }

    private void checkArray(char[] chars) {

        // c is an Object:
        System.out.println("Superclass of char[] c: " +
                chars.getClass().getSuperclass());
        System.out.println("char[] c instanceof Object: " +
                (chars instanceof Object));
    }
}
