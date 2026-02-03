package MyTest.dynamicProgramming;

/**
 * 给定一个 n x m 的二维网格 grid ，网格中的每个单元格包含一个非负整数，表示该单元格的代价。
 * 机器人以左上角单元格为起始点，每次只能向下或者向右移动一步，直至到达右下角单元格。
 * 请返回从左上角到右下角的最小路径和。
 **/
public class MinPathSum {

    // dp[i,j] = min(dp[i-1,j],dp[i,j-1])+grid(i,j)
    public static int minPathSumDp(int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        int[][] dp = new int[row][col];
        //初始化边界
        dp[0][0] = nums[0][0];
        for (int i = 1; i < row; i++) {
            dp[i][0] = nums[i][0] + dp[i - 1][0];
        }
        for (int i = 1; i < col; i++) {
            dp[0][i] = nums[0][i] + dp[0][i - 1];
        }
        // 状态转移矩阵
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + nums[i][j];
            }
        }
        return dp[row - 1][col - 1];
    }


    // dp[i,j] = min(dp[i-1,j],dp[i,j-1])+grid(i,j) -- 空间优化，一行一行的来计算，最后只保留一行的元素
    public static int minPathSumDp2(int[][] nums) {
        int row = nums.length;
        int col = nums[0].length;
        int[] dp = new int[col];
        //初始化第一行
        dp[0] = nums[0][0];
        for (int i = 1; i < col; i++) {
            dp[i] = dp[i - 1] + nums[0][i];
        }
        for (int i = 1; i < row; i++) {
            //行数切换首位元素更新
            dp[0] = dp[0] + nums[i][0];
            for (int j = 1; j < col; j++) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + nums[i][j];
            }
        }
        return dp[col - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                { 1, 3, 1, 5 },
                { 2, 2, 4, 2 },
                { 5, 3, 2, 1 },
                { 4, 3, 5, 2 }
        };
        System.out.println(minPathSumDp(grid));
        System.out.println(minPathSumDp2(grid));
    }
}
