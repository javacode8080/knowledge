package MyTest.greedy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 分数背包问题：
 * 给定 n 个物品，第 i 个物品的重量为 wgt[i-1]、价值为 val[i-1] ，
 * 和一个容量为 cap 的背包。
 * 每个物品只能选择一次，**但可以选择物品的一部分，价值根据选择的重量比例计算**，
 * 问在限定背包容量下背包中物品的最大价值。
 */
public class FractionalKnapsack {

    public static double fractionalKnapsack(int[] wgt, int[] val, int cap) {
        //首先按照物品的单位重量价值排序
        Item[] items = new Item[wgt.length];
        for (int i = 0; i < wgt.length; i++) {
            items[i] = new Item(wgt[i], val[i]);
        }
        //按照物品单位价值排序
        Arrays.sort(items, Comparator.comparingDouble(item -> -((double) item.val / item.wgt)));
        //贪心
        double res = 0;
        for (Item item : items) {
            if (item.wgt <= cap) {
                cap -= item.wgt;
                res += item.val;
            } else {
                res += ((double) item.val / item.wgt) * cap;
                //已经放满了，跳出循环即可
                break;
            }
        }
        return res;
    }


    static class Item {
        int wgt;
        int val;

        public Item(int wgt, int val) {
            this.wgt = wgt;
            this.val = val;
        }
    }

    public static void main(String[] args) {
        int[] wgt = { 10, 20, 30, 40, 50 };
        int[] val = { 50, 120, 150, 210, 240 };
        int cap = 50;

        // 贪心算法
        double res = fractionalKnapsack(wgt, val, cap);
        System.out.println("不超过背包容量的最大物品价值为 " + res);
    }
}
