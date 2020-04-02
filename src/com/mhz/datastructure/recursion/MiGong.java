package com.mhz.datastructure.recursion;

public class MiGong {

    public static void main(String[] agrs) {
        // 先创建一个二维数组,   模拟迷宫
        // 地图
        int[][] map = new int[8][7];
        // 使用1 表示墙
        // 上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        // 左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //  设置挡板 1 表示 挡板
        map[3][1] = 1;
        map[3][2] = 1;
        System.out.println("地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
        findWay2(map, 1, 1);

        System.out.println("找到路后地图的情况");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 7; j++) {
                System.out.printf(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**说明
     * 1. map表示地图
     *  2. i j 表示从地图的那个位置开始出发(1,1)
     *  3, 如果小球能到map[6][5]位置 则说明通路找到了
     *  4. 约定  当map[i][j]为0 表示该点没有走过,
     *  当为1 表示墙;  2 表示通路可以走, 3表示该点已经走过, 但是走不通
     *  5.在走迷宫时, 需要确定一个策略(方法) 下->右->上->左 如果该点走不通, 再回溯
     *
     *
     *
     */

    /**
     * @param map 表示地图
     * @param i   从那个位置开始找
     * @param j
     * @return 如果找到通路， 返回true , 否则返回false
     */
    public static boolean findWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //表示当前这个点还没有走过
            if (map[i][j] == 0) {
                // 按照 策略 下->右->上->左  走
                // 首先假定该点是可以走通的
                map[i][j] = 2;
                if (findWay(map, i + 1, j)) { // 往下开始走
                    return true;
                } else if (findWay(map, i, j + 1)) {//  往右走
                    return true;
                } else if (findWay(map, i - 1, j)) { // 往上走
                    return true;
                } else if (findWay(map, i, j - 1)) { // 往左走
                    return true;
                } else {
                    // 没有找到可以走的路
                    map[i][j] = 3; // 回溯
                    return false;
                }
            } else {// 如果 map[i][j] != 0 , 可能是 1， 2， 3
                // 说明以前的走过, 但是走不通, 等回溯
                return false;

            }
        }
    }
    // 修改找路的策略，改成 上->右->下->左

    public static boolean findWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            //表示当前这个点还没有走过
            if (map[i][j] == 0) {
                // 按照 策略 下->右->上->左  走
                // 首先假定该点是可以走通的
                map[i][j] = 2;
                if (findWay2(map, i - 1, j)) { // 往上走
                    return true;
                } else if (findWay2(map, i, j + 1)) {//  往右走
                    return true;
                } else if (findWay2(map, i + 1, j)) { // 往下走
                    return true;
                } else if (findWay2(map, i, j - 1)) { // 往左走
                    return true;
                } else {
                    // 没有找到可以走的路
                    map[i][j] = 3; // 回溯
                    return false;
                }
            } else {// 如果 map[i][j] != 0 , 可能是 1， 2， 3
                // 说明以前的走过, 但是走不通, 等回溯
                return false;

            }
        }
    }
}
