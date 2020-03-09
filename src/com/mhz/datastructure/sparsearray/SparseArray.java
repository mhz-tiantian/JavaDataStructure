package com.mhz.datastructure.sparsearray;

public class SparseArray {

    // 二维数据转化成稀疏数组的思路
    // 1. 遍历原始的二维数组,得到有效数据的个数 sum
    // 2. 根据sum 就可以创建出来稀疏数组 sparseArr int[sum+1][3]
    // 3. 将二维数组的有效数据,存入到稀疏数组中

    //--------------------- 分割线-------------------
    // 稀疏数组 转成原始的二维数组的思路
    //1. 先读取稀疏数组的第一行, 根据第一行的数组, 创建原始的二维数组,
    // 比如上面的chessArr2=int [11][11]
    // 2. 在读取稀疏数组后的几行数组, 并赋值给二维数组即可

    public static void main(String[] agrs) {
        //1 .先创建一个原始的二维数组 11*11
        // 0. 表示没有棋子, 1, 表示黑子, 2,表示蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        // 输出原始的二维数据
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        // 请二维数组 转成稀疏数组
        // 1. 先遍历二维数组, 得非0的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2. 创建稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;
        // 遍历二维数组, 将非0 的值存放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        // 输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为----------");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        // 将稀疏数组 ======>恢复成原始的二维数组
        // 1.先读取稀疏数组的第一行, 根据第一行的数据, 创建原始的二维数组大小
        // 2. 在读取稀疏数组后的几行数组, 并赋值原始的二维数组即可

        // 1. 先读取稀疏数组的第一行,根据第一行的数据, 创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2. 遍历稀疏数组, 赋值给原始数组 注意是从稀疏数组的第二行数据开始赋值的
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int row[] : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
