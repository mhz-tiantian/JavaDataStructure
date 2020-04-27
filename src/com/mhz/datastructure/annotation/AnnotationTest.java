package com.mhz.datastructure.annotation;


import java.text.SimpleDateFormat;
import java.util.Date;

public class AnnotationTest {


    @SuppressWarnings(value = {"deprecation"})
    public String getString() {
        Date date = new Date(118, 9, 23);
        SimpleDateFormat format = new SimpleDateFormat();
        format.format(date);
        return getString1();
    }


    @Deprecated
    public String getString1() {
        return "Hee;l";
    }

}
