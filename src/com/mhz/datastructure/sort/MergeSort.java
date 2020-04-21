package com.mhz.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 归并排序
 * 是利用归并的思想实现的排序方法,
 * 该算法采用经典的分治策略(
 * 分治法将问题分(divide)成一些小的问题然后
 * 递归求解,
 * 而治的阶段则将分的阶段得到的各答案"修补"在一起, 即分而治之
 * )
 */
public class MergeSort {

    public static int[] leftArray;

    public static void main(String[] agrs) {


        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);
//        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
        int[] arr = getRandomArray(800000);
        leftArray = new int[arr.length / 2];
        mergeSort(arr, 0, arr.length);

        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = format2.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

//        System.out.println("归并排序的结果===" + Arrays.toString(arr));


    }


    /**
     * 获得随机的数据
     *
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

    public static void mergeSort(int[] array, int begin, int end) {

        if ((end - begin) < 2) return;
        // 就是除以二的意思
        int middle = (begin + end) / 2;
        // 拆开的阶段, 分的阶段
        mergeSort(array, begin, middle);
        // 把两边分开
        mergeSort(array, middle, end);


        merge(array, begin, middle, end, leftArray);
    }

    /**
     * 合并的方法
     *
     * @param array
     * @param begin
     * @param end
     */
    private static void merge(int[] array, int begin, int middle, int end, int[] leftArray) {
        // 左边数组的索引值
        int leftIndex = 0;
        //原来数组的 索引值
        int arrayIndex = begin;
        //右边数组的索引值
        int rightIndex = middle;
        // 左边数组的长度
        int leftEnd = middle - begin;
        int rightEnd = end;

        // 首先1.把左边的数组, 填充下, 用原来的数组 i的起始值, 应该为0
        for (int i = leftIndex; i < leftEnd; i++) {
            leftArray[i] = array[begin + i];
        }
        // leftArray这个数组不能每次都去 创建, 会停浪费时间的, 直接在数组开始排序的时候创建好, 就行

        // 如果左边的索引值, 小于左边的长度的话, 才走while循环
        while (leftIndex < leftEnd) {
            // 如果右边的先结束, 说明 右边的值小, 左边的值大,
            if ((rightIndex < rightEnd) && (leftArray[leftIndex] > array[rightIndex])) {
                array[arrayIndex] = array[rightIndex];
                arrayIndex++;
                rightIndex++;
            } else {
                array[arrayIndex] = leftArray[leftIndex];
                arrayIndex++;
                leftIndex++;
            }

        }

    }
}
