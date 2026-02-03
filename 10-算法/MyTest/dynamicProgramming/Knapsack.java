package MyTest.dynamicProgramming;

import java.util.ArrayList;
import java.util.List;

/**
 * 0-1 背包问题
 * 给定 n 个物品，第 i个物品的重量为wgt[i-1]、价值为val[i-1]，和一个容量为cap的背包。
 * 每个物品只能选择一次，问在限定背包容量下能放入物品的最大价值。
 **/
public class Knapsack {

    /**************************解法1：回溯-暴力破解*************************************************/
    public static int knapsackDFS(int[] wgt, int[] val, int maxCap) {
        List<Integer> maxVals = new ArrayList<>();
        maxVals.add(0);
        knapsackDFS(wgt, val, maxVals, 0, maxCap, 0, 0);
        return maxVals.get(0);
    }

    public static void knapsackDFS(int[] wgt, int[] val, List<Integer> maxVal, int nowCap, int maxCap, int nowVal, int start) {
        // 如果是一个更大的结果则保存这个更大的结果
        if (nowVal > maxVal.get(0)) {
            maxVal.set(0, nowVal);
        }
        for (int i = start; i < wgt.length; i++) {
            //剪枝：是否超出背包容量
            if (nowCap + wgt[i] <= maxCap) {
                // 缓存当前的价值
                int temp1 = nowVal;
                //缓存当前的重量
                int temp2 = nowCap;
                // 选择后的价值
                nowVal = nowVal + val[i];
                // 选择后的容量
                nowCap = nowCap + wgt[i];
                // 回溯
                knapsackDFS(wgt, val, maxVal, nowCap, maxCap, nowVal, start + 1);
                // 回退
                nowVal = temp1;
                nowCap = temp2;

            }
        }
    }

    /**************************解法2：暴力破解 - 按照状态转移方程实现*************************************************/
    /**
     * 状态转移方程是什么？，首先我们状态的推导时放入第i个商品的时候容量还剩下c。即当前物品编号i和当前背包容量c。注意这里的c是指背包中已经装了的两，而不是剩下的量
     * dp[i][c] = MAX(dp[i-1][c], dp[i-1][c-wgt[i-1]]+val[i-1])
     * 也就是说当前的价值是看上一次的容量为c的时候且这次不放进去的价值，和上次容量为c-wgt[i-1]的价值加上放进去这次的价值的和两者之间的最大值就是最优解
     * 这个过程是一个从最终结果往前推的过程
     */
    public static int knapsackDFS2(int[] wgt, int[] val, int i, int c) {
        // 若已选完所有物品或背包无剩余容量，则返回价值 0
        if (i == 0 || c == 0) {
            return 0;
        }
        // 若超过背包容量，则只能选择不放入背包
        if (wgt[i - 1] > c) {
            return knapsackDFS2(wgt, val, i - 1, c);
        }
        //1.放入
        int yes = knapsackDFS2(wgt, val, i - 1, c - wgt[i - 1]) + val[i - 1];
        //2.不放入
        int no = knapsackDFS2(wgt, val, i - 1, c);
        return Math.max(yes, no);
    }

    /**************************解法3：记忆法 - 按照状态转移方程实现，记录下计算过的结果*************************************************/
    public static int knapsackDFS3(int[] wgt, int[] val, int[][] mems, int i, int c) {
        // 若已选完所有物品或背包无剩余容量，则返回价值 0
        if (i == 0 || c == 0) {
            return 0;
        }
        // 若已有记录，则直接返回
        if (mems[i][c] != -1) {
            return mems[i][c];
        }
        // 若超过背包容量，则只能选择不放入背包
        if (wgt[i - 1] > c) {
            return knapsackDFS3(wgt, val, mems, i - 1, c);
        }
        // 计算不放入和放入物品 i 的最大价值
        int no = knapsackDFS3(wgt, val, mems, i - 1, c);
        int yes = knapsackDFS3(wgt, val, mems, i - 1, c - wgt[i - 1]) + val[i - 1];
        // 记录并返回两种方案中价值更大的那一个
        mems[i][c] = Math.max(no, yes);
        return mems[i][c];
    }

    /**************************解法4：动态规划*************************************************/
    public static int knapsackDFS4(int[] wgt, int[] val, int cap) {
        //1.初始化
        int[][] dp = new int[wgt.length + 1][cap + 1];
        //2.状态转移方程
        for (int i = 1; i < dp.length; i++) {
            for (int c = 1; c < dp[0].length; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[wgt.length][cap];
    }

    /**************************解法5：动态规划 - 空间优化*************************************************/
    public static int knapsackDFS5(int[] wgt, int[] val, int cap) {
        //1.初始化
        int[] dp = new int[cap + 1];
        int row = 1;
        //2.状态转移方程
        while (row <= wgt.length) {
            // 注意这里倒序很重要，因为状态转移方程利用了前面的记录，如果是正序的话会存在覆盖的情况
            for (int c = cap; c > 0; c--) {
                if (wgt[row - 1] <= c) {
                    dp[c] = Math.max(dp[c], dp[c - wgt[row - 1]] + val[row - 1]);
                }
            }
            row++;
        }
        return dp[cap];
    }

    public static void main(String[] args) {
        int[] wgt = {10, 20, 30, 40, 50};
        int[] val = {50, 120, 150, 210, 240};
//        int[] val = {50, 120, 180, 210, 290};
        int cap = 50;

        // 1.暴力搜索 - 回溯
        int res = knapsackDFS(wgt, val, cap);
        System.out.println("1.不超过背包容量的最大物品价值为 " + res);
        // 2. 暴力搜索 - 状态转移方程
        int res2 = knapsackDFS2(wgt, val, wgt.length, cap);
        System.out.println("2.不超过背包容量的最大物品价值为 " + res2);
        // 2. 暴力搜索 - 状态转移方程
        int[][] mems = new int[wgt.length + 1][cap + 1];
        for (int i = 0; i < mems.length; i++) {
            for (int j = 0; j < mems[0].length; j++) {
                mems[i][j] = -1;
            }
        }
        int res3 = knapsackDFS3(wgt, val, mems, wgt.length, cap);
        System.out.println("3.不超过背包容量的最大物品价值为 " + res3);
        int res4 = knapsackDFS4(wgt, val, cap);
        System.out.println("4.不超过背包容量的最大物品价值为 " + res4);
        int res5 = knapsackDFS5(wgt, val, cap);
        System.out.println("5.不超过背包容量的最大物品价值为 " + res5);
    }
}
