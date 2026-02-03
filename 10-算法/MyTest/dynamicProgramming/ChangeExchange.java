package MyTest.dynamicProgramming;

import java.util.Arrays;

/**
 * 给定 i 种硬币，第 i 种硬币的面值为 coins[i-1]，目标金额为 amt，
 * 每种硬币可以重复选取，问能够凑出目标金额的最少硬币数量。
 * 如果无法凑出目标金额，则返回 -1 。
 */
public class ChangeExchange {

    /*我自己实现的方法，有点麻烦，这里把目标金额是0元和不能完成(-1)这两种情况没有区分，导致在yes条件下判断条件变多*/
    public static int changeExchange(int[] coins, int amt) {
        int[][] dp = new int[coins.length + 1][amt + 1];
        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amt; j++) {
                if (coins[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    int no = dp[i - 1][j];
                    //这里需要判断一下就是dp[i][j - coins[i - 1]]不存在但是j刚好和现在的金额匹配这个可以+1
                    int yes = dp[i][j - coins[i - 1]] == 0 && j != coins[i - 1] ? 0 : dp[i][j - coins[i - 1]] + 1;
                    if (no != 0 && yes != 0) {
                        dp[i][j] = Math.min(yes, no);
                    } else if (no != 0) {
                        dp[i][j] = no;
                    } else {
                        dp[i][j] = yes;
                    }
                }
            }
        }
        return dp[coins.length][amt] == 0 ? -1 : dp[coins.length][amt];
    }

    /* 零钱兑换：动态规划 - 书中代码
     * 他这个好处就是用0和无穷区分了0元和不可能两种情况，这样的话就不需要判断会不会存在一种情况是
     * dp[i][j - coins[i - 1]]不存在但是刚好现在i的钱数就和目标钱数相同的情况，因为如果是这个情况的话那么一定存在一个空出这么多钱的时候就是目标金额是0元的时候
     * 其实这应该是对的做法，要区分出目标0元和不能够实现这两种本质上不是一回事
     *  */
    public static int changeExchange2(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][amt + 1];
        // 状态转移：首行首列
        for (int a = 1; a <= amt; a++) {
            dp[0][a] = MAX;
        }
        // 状态转移：其余行和列
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[i][a] = dp[i - 1][a];
                } else {
                    // 不选和选硬币 i 这两种方案的较小值
                    dp[i][a] = Math.min(dp[i - 1][a], dp[i][a - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amt] != MAX ? dp[n][amt] : -1;
    }

    /* 零钱兑换：空间优化后的动态规划 */
    public static int changeExchange3(int[] coins, int amt) {
        int n = coins.length;
        int MAX = amt + 1;
        // 初始化 dp 表
        int[] dp = new int[amt + 1];
        Arrays.fill(dp, MAX);
        dp[0] = 0;
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int a = 1; a <= amt; a++) {
                if (coins[i - 1] > a) {
                    // 若超过目标金额，则不选硬币 i
                    dp[a] = dp[a];
                } else {
                    // 不选和选硬币 i 这两种方案的较小值
                    dp[a] = Math.min(dp[a], dp[a - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amt] != MAX ? dp[amt] : -1;
    }

    public static void main(String[] args) {
//        int[] coins = {1, 2, 5, 10, 20};
//        int amt = 50;
        int[] coins = {3, 5};
        int amt = 25;
        int res1 = changeExchange(coins, amt);
        int res2 = changeExchange2(coins, amt);
        System.out.println(res1);
        System.out.println(res2);
    }
}
