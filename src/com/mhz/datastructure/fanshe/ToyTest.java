package com.mhz.datastructure.fanshe;


interface HasBatteries {
}

interface Waterproof {

}

interface Shoots {

}

interface Text {

}

class Toy {
    // 如果是反射来调用的话, 构造函数是必须要的, 默认的无参数的构造方法
    Toy() {
    }

    Toy(int i) {

    }
}

class FancyToy extends Toy implements Text, HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}


public class ToyTest {

    static void printInfo(Class cc) {
        // cc。getName()获得完整的类名
        Print.print("class name :" + cc.getName()
                + " is interface ?[" + cc.isInterface() + "]"
        );
        // cc.getSimpleName()不包括包名的类名
        Print.print("Simple name：" + cc.getSimpleName());
        // cc.getCanonicalName()  在java 1.5 的时候引入的, 包括完全的包名
        Print.print("Canonical name :" + cc.getCanonicalName());

        // 总结:getSimpleName() 和getCanonicalName() 其实就是在1.5 的时候
        // 把getName() 分开了
    }

    public static void main(String[] agrs) {
        Class c = null;
        try {
            //通过完整的类名来 生成 Class 对象,
            c = Class.forName("com.mhz.datastructure.fanshe.FancyToy");
        } catch (ClassNotFoundException e) {
            Print.print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        // c.getInterfaces获得c的类可以实现的接口, 因为可以实现多个接口,所有是个数组
        for (Class face : c.getInterfaces()) {
            printInfo(face);
        }
        // c.getSuperclass() 获得c的类, 继承的类(父类, 因为java是一个单继承的方式)
        Class up = c.getSuperclass();
        Object object = null;
        try {
            // 这里有调用默认的 构造方法(), 如果你类里面没有默认的构造方法就会出现
            // InstantiationException 异常
            // 完整的异常:
            /**
             *java.lang.InstantiationException: com.mhz.datastructure.fanshe.Toy
             * 	at java.lang.Class.newInstance(Class.java:427)
             * 	at com.mhz.datastructure.fanshe.ToyTest.main(ToyTest.java:65)
             * Caused by: java.lang.NoSuchMethodException: com.mhz.datastructure.fanshe.Toy.<init>()
             * 	at java.lang.Class.getConstructor0(Class.java:3082)
             * 	at java.lang.Class.newInstance(Class.java:412)
             * 	... 1 more
             */

            // Class.
            object = up.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            Print.print("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            Print.print("Cannot access");
            System.exit(1);
        }
        printInfo(object.getClass());
    }
}
