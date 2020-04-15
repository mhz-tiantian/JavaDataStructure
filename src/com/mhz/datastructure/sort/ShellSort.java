package com.mhz.datastructure.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 * 插入排序可能存在的问题,
 * 数组 arr={2,3,4,5,6,1}
 * 这时需要插入的数1 最小这样的过程是:
 * {2,3,4,5,6,6}
 * {2,3,4,5,5,6}
 * {2,3,4,4,5,6}
 * {2,3,3,4,5,6}
 * {2,2,3,4,5,6}
 * {1,2,3,4,5,6}
 * 结论: 当需要插入的数是较小的数时, 后移的次数明显增多, 对效率有影响
 * <p>
 * 希尔排序也是一种插入排序, 它是简单插入排序经过改进之后的一个更高效的版本,
 * 也成为缩小增量排序
 * <p>
 * 基本思想:
 * 希尔排序是把记录按下标的一定增量分组, 对每组使用直接插入排序算法排序
 * 随着增量逐渐减少, 每组包含的关键字越来越多, 当增量减至1时, 整个文件恰被分成一组,算法便终止
 * <p>
 * <p>
 * <p>
 * <p>
 * <p>
 * 有一群小牛, 考试成绩分别是 {8,9,1,7,2,3,5,4,6,0} 请从小到大排序. 请分别使用
 * 1) 希尔排序时， 对有序序列在插入时采用 交换法, 并测试排序速度.
 * 2) 希尔排序时， 对有序序列在插入时采用 移动法, 并测试排序速度
 */
public class ShellSort {

    public static void main(String[] agrs) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

//        shellSortExchangeInfer(arr);

//        shellSortExchange(arr);

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date1Str = format.format(date1);
        System.out.println("排序前的时间是=" + date1Str);

        //8千万的数据, 大概30多秒  这速度
        shellSortGression(getRandomArray(8000000));

//        shellSortExchange(getRandomArray(800000000));

        Date date2 = new Date();
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date2Str = format2.format(date2);
        System.out.println("排序后的时间是=" + date2Str);

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


    /**
     *  希尔排序, 移位方式(插入的方式, 找到temp插入的位置)
     * @param arr
     */
    public static void shellSortGression(int[] arr) {
        int temp = 0;
        // 增量gap 并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap = gap / 2) {
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                temp = arr[j];
                while ((j - gap >= 0) && temp < arr[j - gap]) {
                    arr[j] = arr[j - gap];
                    j -= gap;

                }
                //当退出 while 后，就给 temp 找到插入的位置
                arr[j] = temp;
            }
        }
//        System.out.println("希尔排序后===" + Arrays.toString(arr));


    }



    /**
     * 希尔排序 交换法
     *
     * @param arr
     */
    public static void shellSortExchange(int[] arr) {
        int temp = 0;  //临时变量 , 用于交换
        int stepSize = arr.length;
        for (stepSize = stepSize / 2; stepSize >= 1; stepSize = stepSize / 2) {
            for (int i = stepSize; i < arr.length; i++) {
                // 遍历每组中所有的元素, (共5组, 每组2个元素) 步长 5
                for (int j = i - stepSize; j >= 0; j -= stepSize) {
                    // 如果当前元素大于加上步长后的那个元素, 说明交换
                    if (arr[j] > arr[j + stepSize]) {
                        temp = arr[j];
                        arr[j] = arr[j + stepSize];
                        arr[j + stepSize] = temp;
                    }

                }

            }

        }

        System.out.println("希尔排序后===" + Arrays.toString(arr));

    }


    // 交换法, 希尔排序,推演过程  从小到大来排序
    public static void shellSortExchangeInfer(int[] arr) {
        int temp = 0;
        // 希尔排序第1轮 排序
        // 因为第1轮排序, 是将10个数据分成5组
        for (int i = 5; i < arr.length; i++) {
            // 遍历每组中所有的元素, (共5组, 每组2个元素) 步长 5
            for (int j = i - 5; j >= 0; j -= 5) {
                // 如果当前元素大于加上步长后的那个元素, 说明交换
                if (arr[j] > arr[j + 5]) {
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }

            }

        }

        System.out.println("希尔排序 1轮后===" + Arrays.toString(arr));
        // 希尔排序的第二轮排序
        //因为 第二轮排序, 是将10 个数据分成了 5/2 =2组
        for (int i = 2; i < arr.length; i++) {
//            System.out.println("sssssssssss" + Arrays.toString(arr));
            for (int j = i - 2; j >= 0; j -= 2) {
                if (arr[j] > arr[j + 2]) {
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序2轮后===" + Arrays.toString(arr));

        for (int i = 1; i < arr.length; i++) {
            System.out.println("sssssssssss" + Arrays.toString(arr));
            for (int j = i - 1; j >= 0; j -= 1) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序3轮后===" + Arrays.toString(arr));


    }
}
