package com.mhz.datastructure.annotation;

import java.lang.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Annotation 在反射函数中的使用示例
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation1 {

    String[] value() default "unknown";

}

class Person {
    @MyAnnotation1
    @Deprecated
    public void empty() {
        System.out.println("\n empty");
    }

    @MyAnnotation1(value = {"gril", "boy"})
    public void somebody(String name, int age) {
        System.out.println("\n somebody" + name + ", " + age);
    }

}


class AnnotationTestDemo {
    public static void main(String[] agrs) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        Class<Person> c = Person.class;
        Method mSomebody = c.getMethod("somebody", new Class[]{String.class, int.class});
        mSomebody.invoke(person, new Object[]{"lily", 18});
        iteratorAnnotations(mSomebody);

        Method mEmpty = c.getMethod("empty", new Class[]{});
        mEmpty.invoke(person, new Object[]{});
        iteratorAnnotations(mEmpty);

    }


    public static void iteratorAnnotations(Method method) {
        // 判断somebody()方法是否包括 MyAnnotation1注解
        if (method.isAnnotationPresent(MyAnnotation1.class)) {
            MyAnnotation1 myAnnotation1 = method.getAnnotation(MyAnnotation1.class);
            String[] values = myAnnotation1.value();
            for (String str : values) {
                System.out.printf(str + ",");
            }
            System.out.println();

            // 获取方法上的所有注解, 并打印出来
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation);
            }

        }
    }

}


