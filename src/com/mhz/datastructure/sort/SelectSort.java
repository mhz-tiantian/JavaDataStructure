package com.mhz.datastructure.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 思想:
 * 选择排序也是一种简单的排序方法
 * 它的基本思想是:
 * 第一次从arr[0]-arr[n-1]中选取最小值,与arr[0]交换
 * 第二次从arr[1]-arr[n-1]从选取最小值,与arr[1]交换
 * ....
 * 第i次从arr[i-1]-arr[n-1]从选取最小值,与arr[i-1]交换
 * 总共通过n-1次, 得到一个从小到大的有序列表
 */
public class SelectSort {


    public static void main(String[] agrs) {
        // 101, 34, 119, 1 原始数据
        int arr[] = {101, 34, 119, 1};
//        System.out.println("原始数据为=-----");
//        System.out.println(Arrays.toString(arr));

        int arrs[]  = getRandomArray(80000);
        long timeMillis = System.currentTimeMillis();

//        Date date1 = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date1Str = format.format(date1);
//        System.out.println("排序前的时间是=" + date1Str);

        selectSort(arrs);


//        Date date2 = new Date();
//        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String date2Str = format2.format(date2);
//        System.out.println("排序后的时间是=" + date2Str);

//        System.out.println(Arrays.toString(arr));
        System.out.printf("排序的时间%d", System.currentTimeMillis() - timeMillis);


    }


    /**
     *  获得随机的数据
     * @param maxSize
     * @return
     */
    public static int[] getRandomArray(int maxSize) {
        int[] arr = new int[maxSize];
        for (int i = 0; i < maxSize; i++) {
            // 随机生成[0,8000000]之间的数
            arr[i] = (int) (Math.random() * 8000000);
        }
        return arr;
    }


    // 选择排序 时间复杂度O(n^2) n的2次方
    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int min = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (min > arr[j]) {
                    // 把arr[j]赋值给min
                    min = arr[j];
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = min;
            }
        }
    }


    // 选择排序 推演过程
    public static void selectSortInfer(int[] arr) {
        // 第一轮
        int minIndex = 0;
        int min = arr[0];
        for (int j = 0 + 1; j < arr.length; j++) {
            if (min > arr[j]) {// 说明假定的最小值, 并不是最小的
                //重置min
                min = arr[j];
                minIndex = j;
            }
        }
        // 将arr[0]变成最小的那个值, 就是   第一次从arr[0]-arr[n-1]中选取最小值,与arr[0]交换
        //交换
        if (minIndex != 0) {
            arr[minIndex] = arr[0];
            arr[0] = min;
        }
        System.out.println("第一轮后=-----");
        // 1 ,34,119 101
        System.out.println(Arrays.toString(arr));









        // 第二轮
        minIndex = 1;
        min = arr[1];

        for (int j = 1 + 1; j < arr.length; j++) {
            if (min > arr[j]) {// 说明假定的最小值, 并不是最小的
                //重置min
                min = arr[j];
                minIndex = j;
            }
        }

        //交换
        if (minIndex != 1) {
            arr[minIndex] = arr[1];
            arr[1] = min;
        }
        System.out.println("第二轮后=-----");
        // 1 ,34,119 101
        System.out.println(Arrays.toString(arr));








        // 第三轮
        minIndex = 2;
        min = arr[2];

        for (int j = 2 + 1; j < arr.length; j++) {
            if (min > arr[j]) {// 说明假定的最小值, 并不是最小的
                //重置min
                min = arr[j];
                minIndex = j;
            }
        }

        //交换
        if (minIndex != 2) {
            arr[minIndex] = arr[2];
            arr[2] = min;
        }
        System.out.println("第三轮后=-----");
        // 1 ,34,119 101
        System.out.println(Arrays.toString(arr));

    }

}
