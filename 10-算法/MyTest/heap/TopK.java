package MyTest.heap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @author sunjian23
 * @title TopK
 * @date 2026/1/22 10:38
 * @description TODO
 */
public class TopK {

    //采用最小堆实现
    public static Collection<Integer> topK(int k, int[] nums) {
        if (k > nums.length || nums.length == 0) {
            return new PriorityQueue<>();
        }
        //1. 初始化一个最小堆
        Queue<Integer> minHeap = new PriorityQueue<>();
        for (int i = 0; i < k; i++) {
            minHeap.offer(nums[i]);
        }
        //2. 后续的参数之间增减
        for (int i = k + 1; i < nums.length; i++) {
            if (nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        return minHeap;
    }

}
