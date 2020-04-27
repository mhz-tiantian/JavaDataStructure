package com.mhz.datastructure.annotation.ceshi;


public class NoBug {

    @Jiecha
    public void suanshu() {
        System.out.println("1234567890");
    }

    @Jiecha
    public void jiafa() {
        System.out.println("1+1=" + 1 + 1);
    }
    @Jiecha
    public void chengfa() {
        System.out.println("3*5=" + 3 * 5);
    }

    @Jiecha
    public void chufa() {
        System.out.println("6/0=" + 6 / 0);
    }

    public void  ziwojieshao() {
        System.out.println("我写的程序没有bug!");
    }
}
