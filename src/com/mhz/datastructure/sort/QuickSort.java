package com.mhz.datastructure.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 快速排序 是对冒泡排序的一种改进,
 * 基本思想:
 * 通过一趟排序,将要排序的数据分割成独立的两部分
 * 其中一部分的所有数据都比另外一部分的所有数据都要小,
 * 然后再按此方法对这两部分数据 分别进行快速排序,
 * 整个排序过程可以递归进行,
 * 以此达到整个数据变成有序序列
 */
public class QuickSort {

    public static void main(String[] agrs) {

        int[] arr = {-9, 78, 0, 23, -567, 70, -1, 900, 4561};
//        quickSort(arr, 0, arr.length - 1);
//        System.out.println("快速排序后的数组为===" + Arrays.toString(arr));

        quickSortXiaoMa(arr, 0, arr.length);

        System.out.println("快速排序后的数组为===" + Arrays.toString(arr));
    }


    /**
     * 快速排序 ,小马哥教育讲的 [begin, end)
     *
     * @param arr   数据
     * @param begin 开始的下标
     * @param end   结果的下标 后一位
     */
    public static void quickSortXiaoMa(int[] arr, int begin, int end) {
        // 扫描剩下的元素 为1的是直接返回
        if (end - begin < 2) {
            return;
        }
        // 返回
        int mid = pivotIndex(arr, begin, end);
        quickSortXiaoMa(arr, begin, mid);
        quickSortXiaoMa(arr, mid + 1, end);

    }

    /**
     * 确定轴点位置 的下标
     *
     * @param arr
     * @param begin
     * @param end
     * @return 确定中缀位置 的下标
     */
    private static int pivotIndex(int[] arr, int begin, int end) {
        // 先把 轴点元素的值 保存到一个变量里
        int pivot = arr[begin];
        // 把最后的下标前移一位, 指向最后的下标
        end--;
        while (begin < end) {
            while (begin < end) {
                //右边的元素大于 轴点的元素, 直接前移一位
                if (arr[end] > pivot) {
                    end--;
                } else {
                    // 右边的元素小于或者等于轴点元素, 交换, 就把左边的指针指向下一个元素
                    // 如果左边的指针不动的话, 它其实值指向了已经交换过的元素,
                    // 还有就是不利用下次的交换, 所以一定要++
                    // 这样就造成了, 没有必要的一次对比 ,
                    // 交换完成以后, 改变指针移动的方向  break了, 执行下面的代码去了
                    arr[begin] = arr[end];
                    begin++;
                    break;
                }
            }
            while (begin < end) {
                //左边的元素小于轴点元素的时候,
                // 直接左指针向前移动
                if (arr[begin] < pivot) {
                    begin++;
                } else {
                    // 左边的元素 大于或者等于轴点元素的时候, 交换
                    // 左边的元素放到当前end处, 然后end--
                    arr[end] = arr[begin];
                    end--;
                    break;
                }
            }
        }

        // 把轴点元素,放入到最终的位置上
        arr[begin] = pivot;
        // 返回当前轴点元素的下标
        return begin;
    }


    /**
     * 快速排序 网上的代码
     *
     * @param a
     * @param low
     * @param high
     * @return
     */

    private static int partition(int[] a, int low, int high) {
        // https://blog.csdn.net/qq_36462955/article/details/82834839
        int pivot = a[low];

        while (low < high) {
            //从后往前开始比较，直到遇到比pivot小的数,将其移到low位置,此时high位置的数在[]中有两个
            while (low < high && a[high] >= pivot) {
                high--;
            }
            a[low] = a[high];
            //再从前开始比较，当然第一个a[low]是刚移过来的，low++,直到遇到比pivot小的数,将其替换到high位置的值
            while (low < high && a[low] <= pivot) {
                low++;
            }
            a[high] = a[low];
        }
        //遍历到后来low>high时，结束
        a[low] = pivot;
        return low;
    }

    public static void quickSort(int[] data, int low, int high) {
        if (low < high) {
            int pivot = partition(data, low, high);
            quickSort(data, low, pivot - 1);
            quickSort(data, pivot + 1, high);

        }
    }

    /**
     * 韩老师教的快速排序的代码(有bug)
     *
     * @param arr
     * @param left
     * @param right
     */
    public static void quickSortHan(int[] arr, int left, int right) {
        int l = left;// 左下标
        int r = right;// 右下标
        // 中间的值
        int pivot = arr[(right + left) / 2];
        int temp = 0;
        // while的循环目的是让比 pivot值小放到左边
        // 比pivot值大放到右边
        while (l < r) {
            // 在pivot的左边一直找, 找到大于等于pivot值才退出 退出后 说明找到了第一个
            while (arr[l] < pivot) {
                l++;
            }
            // 在pivot的右边一直找, 找到小于等于pivot值才退出
            while (arr[r] > pivot) {
                r--;
            }
            // 这时候可以交换
            //l ==r 的时候说明已经找到  pivot的下标处了, 可以退出了
            if (l >= r) {
                break;
            }
            // 交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个 arr[l] == pivot 值 相等 r--， 前移
            if (arr[l] == pivot) {
                r--;
            }
//            //如果交换完后，发现这个 arr[r] == pivot 值 相等 l++， 后移
            if (arr[r] == pivot) {
                l++;
            }
//            // 如果 l == r, 必须 l++, r--, 否则为出现栈溢出
            if (l == r) {
                l += 1;
                r -= 1;
            }
            // 向左递归
            if (left < r) {
                quickSort(arr, left, r);
            }
            // 向右递归
            if (right > l) {
                quickSort(arr, l, right);
            }

        }


    }
}
