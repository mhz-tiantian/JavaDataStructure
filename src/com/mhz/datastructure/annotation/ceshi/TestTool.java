package com.mhz.datastructure.annotation.ceshi;

import java.lang.reflect.Method;

public class TestTool {
    public static void main(String[] agrs) {
        NoBug testObj = new NoBug();
        Class clazz = testObj.getClass();
        Method[] methods = clazz.getDeclaredMethods();
        StringBuilder log = new StringBuilder();
        int errornum = 0;
        for (Method method : methods) {
            if (method.isAnnotationPresent(Jiecha.class)) {
                try {
                    method.setAccessible(true);
                    method.invoke(testObj, null);
                } catch (Exception e) {
                    errornum++;
                    e.printStackTrace();
                    log.append(method.getName());
                    log.append("  ");
                    log.append(" has error:");
                    log.append("  \n\r caused by");
                    log.append(e.getCause().getClass().getSimpleName());
                    log.append("\n\r");
                    log.append(e.getCause().getMessage());
                    log.append("\n\r");
                }
            }
        }
        log.append(clazz.getSimpleName());
        log.append(" has \r");
        log.append(errornum);
        log.append(" error .");
        // 生成测试报告
        System.out.println(log.toString());

    }
}
