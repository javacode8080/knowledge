package MyTest.greedy;

/**
 * 给定一个正整数 n ，将其切分为至少两个正整数的和，求切分后所有整数的乘积最大是多少，
 */
public class MaxProductCutting {

    public static int maxProductCutting(int num) {
        // 当 n <= 3 时，必须切分出一个 1
        if (num <= 3) {
            return 1 * (num - 1);
        }
        //通过数学计算，按照3进行切分，如果余数为0或2则不必处理，余数为1时则2*2>3*1，就切换为2*2
        int res = 1;
        while (num > 3) {
            num -= 3;
            res *= 3;
        }
        if (num == 1) {
            res = res / 3 * 2 * 2;
        } else {
            res *= num;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 2;

        // 贪心算法
        int res = maxProductCutting(n);
        System.out.println("最大切分乘积为 " + res);
    }
}
