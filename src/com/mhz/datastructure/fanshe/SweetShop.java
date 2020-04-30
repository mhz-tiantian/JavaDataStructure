package com.mhz.datastructure.fanshe;

class Candy {
    static {
        print("loading Candy");
    }

    static void print(String content) {
        System.out.println(content);
    }
}

class Gum {
    static {
        print("loading Gum");
    }

    static void print(String content) {
        System.out.println(content);
    }
}

class Cookie {
    static {
        print("loading Cookie");
    }

    static void print(String content) {
        System.out.println(content);
    }
}


public class SweetShop {


    public static void main(String[] agrs) {
        print("inside  main");
        new Candy();
        print(" after  creating candy");

        try {
            // 这里的路径是一个全路径, 包括包的路径
            Class.forName("com.mhz.datastructure.fanshe.Gum");
        } catch (ClassNotFoundException e) {
            print("couldn't  find Gum");
        }
        print("after  class.forName('Gum')");
        new Cookie();
        print("after creating cookie");
    }

    static void print(String content) {
        System.out.println(content);
    }
}


