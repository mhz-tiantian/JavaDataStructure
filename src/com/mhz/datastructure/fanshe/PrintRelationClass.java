package com.mhz.datastructure.fanshe;


import java.lang.reflect.Field;

class A {
    private int age;
    private String name;

}

class B extends A {
    private int file;

}

class C extends B {
    private int cage;
    private String cName;

}


public class PrintRelationClass {


    public static void main(String[] agrs) throws InstantiationException, IllegalAccessException {
        C c = new C();
//        B b = new B();
        PrintRelationClass printRelationClass = new PrintRelationClass();
        printRelationClass.printClassInfo(c);
    }


    public  void printClassInfo(Object t) throws IllegalAccessException, InstantiationException {
        // 获取对象的类信息
        Class tClass = t.getClass();
        if (tClass.getSuperclass() != null) {
            System.out.println(tClass + " is a subclass of " +
                    tClass.getSuperclass());
            Field[] fields = tClass.getDeclaredFields();
            for (Field field : fields) {
                System.out.println(field);
            }
            printClassInfo(tClass.getSuperclass().newInstance());
        }
    }


    public static void print(String classMsg) {
        System.out.println(classMsg);

    }

    public static class Ex8 {

        public static void Hierarchy(Object o) {
            if(o.getClass().getSuperclass() != null) {
                System.out.println(o.getClass() + " is a subclass of " +
                        o.getClass().getSuperclass());
                try {
                    Hierarchy(o.getClass().getSuperclass().newInstance());
                } catch(InstantiationException e) {
                    System.out.println("Unable to instantiate obj");
                } catch(IllegalAccessException e) {
                    System.out.println("Unable to access");
                }
            }
        }
        public static void main(String[] args) {
            Hierarchy(new B());
        }
    }








}
