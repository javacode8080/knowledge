package MyTest.greedy;

/**
 * 最大容量问题
 * <p>
 * 输入一个数组 ht ，其中的每个元素代表一个垂直隔板的高度。数组中的任意两个隔板，以及它们之间的空间可以组成一个容器。
 * 容器的容量等于高度和宽度的乘积（面积），其中高度由较短的隔板决定，宽度是两个隔板的数组索引之差。
 * 请在数组中选择两个隔板，使得组成的容器的容量最大，返回最大容量。
 */
public class MaxCapacity {
    public static int maxCapacity(int[] nums) {
        //1.初始化指针
        int i = 0;
        int j = nums.length - 1;
        int res = 0;
        while (i < j) {
            int num1 = nums[i];
            int num2 = nums[j];
            if (num2 > num1) {
                res = Math.max(num1 * (j - i), res);
                i++;
            } else {
                res = Math.max(num2 * (j - i), res);
                j--;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] ht = {3, 8, 5, 2, 7, 7, 3, 4};

        // 贪心算法
        int res = maxCapacity(ht);
        System.out.println("最大容量为 " + res);
    }
}
