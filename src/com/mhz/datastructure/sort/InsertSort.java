package com.mhz.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 * 思想 把n个待排序的元素 看成一个有序表,和一个无序表.
 * 开始时  有序表中只包括一个元素, 无序表包括有n-1个元素,
 * 排序过程中每次
 * 从无序表中取出第一个元素
 * ,把它的排序吗依次与有序表元素的排序吗进行比较,
 * 将它插入到有序表中的适当位置, 使之成为新的有序表
 */
public class InsertSort {


    public static void main(String[] args) {
//        int[] arrs = {101, 34, 119, 1, -1, 89};
//        insertSortInfer(arrs);
//        System.out.println("排序前的数组");
//        System.out.println(Arrays.toString(arrs));

        long timeMillis = System.currentTimeMillis();


        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        insertSort(getRandomArray(8000000));

        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = format2.format(date2);
        System.out.println("排序后的时间是=" + date2Str);


//        System.out.printf("排序的时间%d", System.currentTimeMillis() - timeMillis);
//        System.out.println("排序后的数组");

//        System.out.println(Arrays.toString(arrs));
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

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }





    /**
     *  插入排序的推演过程
     * @param arr
     */
    public static void insertSortInfer(int[] arr) {
        // 第 1 轮 {101, 34, 119, 1}; => {34, 101, 119, 1}
        // 定义待插入的数
        int insertVal = arr[1];
        //带插入的数, 前面的数的下标
        int insertIndex = 1 - 1;
        // insetVal 找到插入的位置
        // 说明
        // 1. insertIndex>=0保证在给insetVal找插入位置, 不越界
        // 2. insertVal<arr[insetIndex]待插入的数, 还没有找到插入的位置
        // 3.就需要将 arr[insertIndex]后移

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        // 当退出while循环时, 说明插入的位置找到了, insertIndex+1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第一轮插入");
        System.out.println(Arrays.toString(arr));


        // 第二轮

         insertVal = arr[2];
        //带插入的数, 前面的数的下标
         insertIndex = 2 - 1;
        // insetVal 找到插入的位置
        // 说明
        // 1. insertIndex>=0保证在给insetVal找插入位置, 不越界
        // 2. insertVal<arr[insetIndex]待插入的数, 还没有找到插入的位置
        // 3.就需要将 arr[insertIndex]后移

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        // 当退出while循环时, 说明插入的位置找到了, insertIndex+1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第二轮插入");
        System.out.println(Arrays.toString(arr));


        // 第三轮
        insertVal = arr[3];
        //带插入的数, 前面的数的下标
        insertIndex = 3 - 1;
        // insetVal 找到插入的位置
        // 说明
        // 1. insertIndex>=0保证在给insetVal找插入位置, 不越界
        // 2. insertVal<arr[insetIndex]待插入的数, 还没有找到插入的位置
        // 3.就需要将 arr[insertIndex]后移

        while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex--;
        }
        // 当退出while循环时, 说明插入的位置找到了, insertIndex+1
        arr[insertIndex + 1] = insertVal;
        System.out.println("第三轮插入");
        System.out.println(Arrays.toString(arr));

    }


}
