package MyTest.dynamicProgramming;

/**
 * 输入两个字符串 s 和 t ，返回将 s 转换为 t 所需的最少编辑步数。
 * 你可以在一个字符串中进行三种编辑操作：插入一个字符、删除一个字符、将字符替换为任意一个字符
 */
public class Levenshtein {

    public static int levenshtein(String src, String target) {
        int[][] dp = new int[src.length() + 1][target.length() + 1];
        //1.初始化
        for (int i = 0; i < src.length() + 1; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j < target.length() + 1; j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= src.length(); i++) {
            for (int j = 1; j <= target.length(); j++) {
                if (src.charAt(i - 1) == target.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    //新增
                    int insert = dp[i - 1][j];
                    //删除
                    int delete = dp[i][j - 1];
                    //修改
                    int modify = dp[i - 1][j - 1];
                    dp[i][j] = Math.min(Math.min(insert, delete), modify) + 1;
                }
            }
        }
        return dp[src.length()][target.length()];
    }

    public static int levenshtein2(String src, String target) {
        int[] dp = new int[target.length() + 1];
        //1.初始化
        for (int j = 0; j < target.length() + 1; j++) {
            dp[j] = j;
        }

        for (int i = 1; i <= src.length(); i++) {
            int temp = dp[0];
            dp[0] = i;
            for (int j = 1; j <= target.length(); j++) {
                if (src.charAt(i - 1) == target.charAt(j - 1)) {
                    int temp2 = dp[j];
                    dp[j] = temp;
                    temp = temp2;
                } else {
                    //新增
                    int insert = dp[j];
                    //删除
                    int delete = dp[j - 1];
                    //修改
                    int modify = temp;
                    temp = dp[j];
                    dp[j] = Math.min(Math.min(insert, delete), modify) + 1;
                }
            }
        }
        return dp[target.length()];
    }

    public static void main(String[] args) {
        String s = "bag";
        String t = "pack";
        // 动态规划
        int res1 = levenshtein(s, t);
        System.out.println("将 " + s + " 更改为 " + t + " 最少需要编辑 " + res1 + " 步");

        // 空间优化后的动态规划
        int res2 = levenshtein2(s, t);
        System.out.println("将 " + s + " 更改为 " + t + " 最少需要编辑 " + res2 + " 步");
    }

}
