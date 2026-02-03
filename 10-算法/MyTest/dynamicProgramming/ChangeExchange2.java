package MyTest.dynamicProgramming;

/**
 * 给定 i 种硬币，第 i 种硬币的面值为 coins[i-1]，目标金额为 amt，
 * 每种硬币可以重复选取，问凑出目标金额的硬币组合数量。
 */
public class ChangeExchange2 {

    public static int changeExchange(int[] coins, int amt) {
        int[][] dp = new int[coins.length + 1][amt + 1];
        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amt; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
                }
            }
        }
        return dp[coins.length][amt];
    }

    /**
     * 空间优化
     */
    public static int changeExchange2(int[] coins, int amt) {
        int[] dp = new int[amt + 1];
        dp[0] = 1;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amt; j++) {
                if (coins[i - 1] <= j) {
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
                }
            }
        }
        return dp[amt];
    }


    public static void main(String[] args) {
        int[] coins = {1, 2, 5, 10, 20};
        int amt = 50;
        System.out.println(changeExchange(coins, amt));
        System.out.println(changeExchange2(coins, amt));
    }
}
