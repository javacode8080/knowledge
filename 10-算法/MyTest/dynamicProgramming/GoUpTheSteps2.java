package MyTest.dynamicProgramming;


/**
 * 爬楼梯问题进阶
 **/
public class GoUpTheSteps2 {

    /**
     * 给定一个楼梯，你每步可以上1阶或者2阶，每一阶楼梯上都贴有一个非负整数，表示你在该台阶所需要付出的代价。给定一个非负整数数组cost，
     * 其中cost[i]表示在第i个台阶需要付出的代价，cost[0]为地面（起始点）。
     * 请计算最少需要付出多少代价才能到达顶部？
     **/

    public static int minCostClimbingStairsDP(int[] cost) {
        int n = cost.length - 1;
        if (n == 1 || n == 2) {
            return cost[n];
        }
        int dp1 = cost[1];
        int dp2 = cost[2];
        for (int i = 3; i <= n; i++) {
            int temp = dp2;
            dp2 = Math.min(dp1, dp2) + cost[i];
            dp1 = temp;
        }
        return dp2;
    }

    /**
     * 给定一个共有n阶的楼梯，你每步可以上1阶或者2阶，但不能连续两轮跳1阶，请问有多少种方案可以爬到楼顶？(空间优化)
     * dp[i][1] = dp[i-1,2]
     * dp[i,2] = dp[i-2,1] + dp[i-2,2]
     **/
    public static int climbingStairsConstraintDP(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        //dp1_1代表的是dp[i-2,1],dp1_2代表的是dp[i-2,2],dp2_1代表的是dp[i-1,1],dp2_1代表的是dp[i-1,2],
        int dp1_1 = 1;//初始化：上一个台阶到1的方案1种
        int dp1_2 = 0;//初始化：上两个台阶到1的方案0种
        int dp2_1 = 0;//初始化：上一个台阶到2的方案0种，因为只有[1,1]，这是不被允许的
        int dp2_2 = 1;//初始化：上两个台阶到2的方案1种
        for (int i = 3; i <= n; i++) {
            int temp1 = dp2_1;
            int temp2 = dp2_2;
            //当前台阶的方案数量
            //上一个台阶的dp[i][1] = dp[i-1,2]
            dp2_1 = dp2_2;
            //上两个台阶的dp[i,2] = dp[i-2,1] + dp[i-2,2]
            dp2_2 = dp1_1 + dp1_2;
            dp1_1 = temp1;
            dp1_2 = temp2;
        }
        return dp2_1 + dp2_2;
    }

    /**
     * 给定一个共有n阶的楼梯，你每步可以上1阶或者2阶，但不能连续两轮跳1阶，请问有多少种方案可以爬到楼顶？(无空间优化)
     * dp[i][1] = dp[i-1,2]
     * dp[i,2] = dp[i-2,1] + dp[i-2,2]
     **/
    /* 带约束爬楼梯：动态规划 */
    public static int climbingStairsConstraintDP2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 初始化 dp 表，用于存储子问题的解
        int[][] dp = new int[n + 1][3];
        // 初始状态：预设最小子问题的解
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[2][1] = 0;
        dp[2][2] = 1;
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
        }
        return dp[n][1] + dp[n][2];
    }

    public static void main(String[] args) {
        System.out.println(minCostClimbingStairsDP(new int[]{1, 2, 3, 4, 5, 6, 7}));
        System.out.println(climbingStairsConstraintDP(5));
        System.out.println(climbingStairsConstraintDP2(5));
    }
}
