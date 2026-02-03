package MyTest.dynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunjian23
 * @title 上台阶
 * @date 2026/1/30 09:35
 * @description TODO
 */

/**
 * 给定一个共有 n阶的楼梯，你每步可以上 1 阶或者 2阶，请问有多少种方案可以爬到楼顶？
 **/
public class GoUpTheSteps {

    /*****************************解法5：动态规划的空间优化 dp[i]=dp[i-1]+dp[i-2]********************************************/
    public static int climbingStairsDPComp(int n) {
        if (n == 1 || n == 2)
            return n;
        int dp1 = 1;
        int dp2 = 2;
        for (int i = 3; i <= n; i++) {
            int temp = dp2;
            dp2 = dp1 + dp2;
            dp1 = temp;
        }
        return dp2;
    }

    /*****************************解法4：动态规划 dp[i]=dp[i-1]+dp[i-2]********************************************/
    public static int climbingStairsDP(int n) {
        if (n == 1 || n == 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

    /*****************************解法3：记忆化搜索 dp[i]=dp[i-1]+dp[i-2]********************************************/
    /* 爬楼梯：记忆化搜索 */
    public static int climbingStairsDFSMem(int n) {
        // mem[i] 记录爬到第 i 阶的方案总数，-1 代表无记录
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return dfs(n, mem);
    }

    /* 记忆化搜索 */
    private static int dfs(int i, int[] mem) {
        // 已知 dp[1] 和 dp[2] ，返回之
        if (i == 1 || i == 2)
            return i;
        // 若存在记录 dp[i] ，则直接返回之
        if (mem[i] != -1)
            return mem[i];
        // dp[i] = dp[i-1] + dp[i-2]
        int count = dfs(i - 1, mem) + dfs(i - 2, mem);
        // 记录 dp[i]
        mem[i] = count;
        return count;
    }

    /*****************************解法2：暴力搜索dp[i]=dp[i-1]+dp[i-2]********************************************/
    public static int dfs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return dfs(n - 1) + dfs(n - 2);
    }

    /*****************************解法1：回溯********************************************/
    public static List<List<Integer>> goUpTheSteps(int n) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        backtrack(state, n, res);
        return res;
    }

    private static void backtrack(List<Integer> state, int n, List<List<Integer>> res) {
        //1.如果是结果则记录
        if (isSolution(state, n)) {
            solutionRecord(state, res);
            return;
        }
        //2.循环判断条件选择
        for (int i = 1; i <= 2; i++) {
            if (isValid(i, state, n)) {
                choice(state, i);
                backtrack(state, n, res);
                undoChoice(state);
            }
        }
    }

    private static void undoChoice(List<Integer> state) {
        state.remove(state.size() - 1);
    }

    private static void choice(List<Integer> state, int i) {
        state.add(i);
    }

    private static boolean isValid(int i, List<Integer> state, int n) {
        return sum(state) + i <= n;
    }

    private static boolean isSolution(List<Integer> state, int n) {
        return sum(state) == n;
    }

    private static void solutionRecord(List<Integer> state, List<List<Integer>> res) {
        res.add(new ArrayList<>(state));
    }

    private static int sum(List<Integer> state) {
        int sum = 0;
        for (Integer i : state) {
            sum += i;
        }
        return sum;
    }

    public static void print(List<List<Integer>> lists) {
        for (List<Integer> list : lists) {
            System.out.print("[");
            for (int i = 0; i < list.size(); i++) {
                Integer val = list.get(i);
                System.out.print(val);
                if (i != list.size() - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }


    public static void main(String[] args) {
        //1. 这个可以记录每一个步骤的方法
        List<List<Integer>> goUpTheSteps = goUpTheSteps(9);
        System.out.println(goUpTheSteps.size());
        //2. 暴力搜索只能记录一共有多少种方法
        System.out.println(dfs(9));
        //3. 记忆方法有效的减少不必要的重复递归
        System.out.println(climbingStairsDFSMem(9));
        //4. 动态规划：不需要递归
        System.out.println(climbingStairsDP(9));
        //5. 动态规划：空间优化
        System.out.println(climbingStairsDPComp(9));
    }

}
