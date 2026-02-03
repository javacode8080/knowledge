package MyTest.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author sunjian23
 * @title Sort
 * @date 2026/1/23 15:43
 * @description TODO
 */
public class Sort {

    /* 元素交换 */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    //1.选择排序（非稳定排序）
    public static int[] selectSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(nums, i, minIndex);
        }
        return nums;
    }

    //2.冒泡排序（稳定排序）
    public static int[] bubbleSort(int[] nums) {
        for (int i = nums.length - 1; i > 0; i--) {
            boolean flag = false; // 初始化标志位
            for (int j = 0; j < i; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
                    flag = true;
                }
            }
            if (!flag) break; // 此轮“冒泡”未交换任何元素，直接跳出
        }
        return nums;
    }

    //3.插入排序（稳定排序）
    public static int[] insertSort(int[] nums) {
        // 外循环：已排序区间为 [0, i-1]
        for (int i = 1; i < nums.length; i++) {
            int base = nums[i], j = i - 1;
            // 内循环：将 base 插入到已排序区间 [0, i-1] 中的正确位置
            while (j >= 0 && nums[j] > base) {
                nums[j + 1] = nums[j]; // 将 nums[j] 向右移动一位
                j--;
            }
            nums[j + 1] = base;        // 将 base 赋值到正确位置
        }
        return nums;
    }

    //4. 快速排序（稳定排序）
    private static int partition(int[] nums, int left, int right) {
        // 1.选取三个候选元素的中位数
        int med = medianThree(nums, left, (left + right) / 2, right);
        // 2.将中位数交换至数组最左端
        swap(nums, left, med);
        // 3.以左作为基准
        int baseIndex = left;
        // 以 nums[left] 为基准数
        int base = nums[baseIndex];
        while (left < right) {
            //1. 从右侧往前找到第一个小于基准值的数
            while (nums[right] >= base && right > left) {
                right--;  // 从右向左找首个小于基准数的元素
            }
            //2.从左侧找到第一个大于基准的数
            while (nums[left] <= base && left < right) {
                left++; // 从左向右找首个大于基准数的元素
            }
            //交换两者的值
            swap(nums, left, right);
        }
        //将基准值进行交换
        swap(nums, left, baseIndex); // 将基准数交换至两子数组的分界线
        return left;
    }

    /* 选取三个候选元素的中位数 */
    private static int medianThree(int[] nums, int left, int mid, int right) {
        int l = nums[left], m = nums[mid], r = nums[right];
        if ((l <= m && m <= r) || (r <= m && m <= l)) return mid; // m 在 l 和 r 之间
        if ((m <= l && l <= r) || (r <= l && l <= m)) return left; // l 在 m 和 r 之间
        return right;
    }

    /* 快速排序（普通方案） */
//    public static void quickSort(int[] nums, int left, int right) {
//        if (left < right) {
//            //1. 先进行第一次划分
//            int mid = partition(nums, left, right);
//            //2.进行左递归
//            quickSort(nums, left, mid - 1);
//            //3.进行有递归
//            quickSort(nums, mid + 1, right);
//        }
//    }

    /* 快速排序（递归深度优化） */
    public static void quickSort(int[] nums, int left, int right) {
        // 子数组长度为 1 时终止
        // 注意这里和普通递归的区别，这里是while循环，可以理解为先把所有的短分区递归完然后再进入循环去递归长的那部分。
        // 每次 partition 后，基准点已经处于正确位置，而左右子数组需要进一步排序。
        // 优化方法通过递归处理一个子数组（较短的），另一个子数组通过循环“推迟”处理。
        while (left < right) {
            // 哨兵划分操作
            int pivot = partition(nums, left, right);
            // 对两个子数组中较短的那个执行快速排序
            if (pivot - left < right - pivot) {
                quickSort(nums, left, pivot - 1); // 递归排序左子数组
                left = pivot + 1; // 剩余未排序区间为 [pivot + 1, right]
            } else {
                quickSort(nums, pivot + 1, right); // 递归排序右子数组
                right = pivot - 1; // 剩余未排序区间为 [left, pivot - 1]
            }
        }
    }

    public static int[] quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    // 归并排序（稳定排序）
    private static void merge(int[] nums, int left, int right, int mid) {
        //用来暂存排序的内容
        int[] temp = new int[right - left + 1];
        //引入中间变量
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            if (nums[i] < nums[j]) {
                // 左半部分小入
                temp[k++] = nums[i++];
            } else {
                // 右半部分小入
                temp[k++] = nums[j++];
            }
        }
        //如果左半部分有剩余左入
        while (i <= mid) {
            temp[k++] = nums[i++];
        }
        //如果右半部分有剩余右入
        while (j <= right) {
            temp[k++] = nums[j++];
        }
        //合并完成赋值到原始队列中
        for (int m = 0; m < temp.length; m++) {
            nums[left++] = temp[m];
        }
    }

    private static void mergeSort(int[] nums, int left, int right) {
        int mid = left + (right - left) / 2;
        if (left < right) {
            mergeSort(nums, left, mid);
            mergeSort(nums, mid + 1, right);
            merge(nums, left, right, mid);
        }
    }

    public static int[] mergeSort(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    //堆排序（稳定排序）
    //1.获取父节点
    private static int getParent(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    //获取左子节点
    private static int getLeft(int i) {
        return 2 * i + 1;
    }

    //获取右子节点
    private static int getRight(int i) {
        return 2 * i + 2;
    }

    private static void shiftDown(int[] nums, int length, int i) {
        //初始化左右节点数
        while (true) {
            int left = getLeft(i);
            int right = getRight(i);
            //先假设父节点最大
            int max = i;
            // 注意这里非常巧妙，我们采用一个最大值索引记录，然后假定索引最大为父节点然后先和左节点比较如果左节点大就切为左节点，然后再和右节点比较，
            // 注意比较的都是nums[max]而不是nums[i]，这样就不需要后面再判断这个最大值所以取自哪个节点了。
            if (left < length && nums[left] > nums[max]) {
                max = left;
            }
            if (right < length && nums[right] > nums[max]) {
                max = right;
            }
            if (max == i) {
                break;
            }
            swap(nums, i, max);
            i = max;
        }
    }

    //建堆（大顶堆）
    private static void maxHeap(int[] nums, int length) {
        // 堆化除叶节点以外的其他所有节点
        for (int i = getParent(length - 1); i >= 0; i--) {
            shiftDown(nums, length, i);
        }

    }

    //堆排序
    public static int[] heapSort(int[] nums) {
        //1. 建堆
        maxHeap(nums, nums.length);
        for (int i = nums.length - 1; i > 0; i--) {
            //交换0和i的位置(最后一个位置保证是最大值)
            swap(nums, i, 0);
            //将剩下的重新进行shiftDown（从第一个元素开始，并且数组长度-1，不排序尾部已经归为的内容）
            shiftDown(nums, i, 0);
        }
        return nums;
    }

    //桶排序
    /* 桶排序 */
    public static float[] bucketSort(float[] nums) {
        // 初始化 k = n/2 个桶，预期向每个桶分配 2 个元素
        int k = nums.length / 2;
        List<List<Float>> buckets = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            buckets.add(new ArrayList<>());
        }
        // 1. 将数组元素分配到各个桶中
        for (float num : nums) {
            // 输入数据范围为 [0, 1)，使用 num * k 映射到索引范围 [0, k-1]
            int i = (int) (num * k);
            // 将 num 添加进桶 i
            buckets.get(i).add(num);
        }
        // 2. 对各个桶执行排序
        for (List<Float> bucket : buckets) {
            // 使用内置排序函数，也可以替换成其他排序算法
            Collections.sort(bucket);
        }
        // 3. 遍历桶合并结果
        int i = 0;
        for (List<Float> bucket : buckets) {
            for (float num : bucket) {
                nums[i++] = num;
            }
        }
        return nums;
    }

    //计数排序
    public static int[] countSort(int[] nums) {
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }
        //可能存在0，所以要申请+1
        int[] counter = new int[max + 1];
        for (int num : nums) {
            counter[num] += 1;
        }
        int i = 0;
        for (int k = 0; k < counter.length; k++) {
            for (int m = 0; m < counter[k]; m++)
                nums[i++] = k;
        }
        return nums;
    }
    //基数排序
    /* 获取元素 num 的第 k 位，其中 exp = 10^(k-1) */
    private static int digit(int num, int exp) {
        // 传入 exp 而非 k 可以避免在此重复执行昂贵的次方计算
        return (num / exp) % 10;
    }

    /* 计数排序（根据 nums 第 k 位排序） */
    private static void countingSortDigit(int[] nums, int exp) {
        // 十进制的位范围为 0~9 ，因此需要长度为 10 的桶数组
        int[] counter = new int[10];
        int n = nums.length;
        // 统计 0~9 各数字的出现次数
        for (int i = 0; i < n; i++) {
            int d = digit(nums[i], exp); // 获取 nums[i] 第 k 位，记为 d
            counter[d]++;                // 统计数字 d 的出现次数
        }
        // 求前缀和，将“出现个数”转换为“数组索引”
        for (int i = 1; i < 10; i++) {
            counter[i] += counter[i - 1];
        }
        // 倒序遍历，根据桶内统计结果，将各元素填入 res
        int[] res = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int d = digit(nums[i], exp);
            int j = counter[d] - 1; // 获取 d 在数组中的索引 j
            res[j] = nums[i];       // 将当前元素填入索引 j
            counter[d]--;           // 将 d 的数量减 1
        }
        // 使用结果覆盖原数组 nums
        for (int i = 0; i < n; i++)
            nums[i] = res[i];
    }

    /* 基数排序 */
    public static int[] radixSort(int[] nums) {
        // 获取数组的最大元素，用于判断最大位数
        int m = Integer.MIN_VALUE;
        for (int num : nums)
            if (num > m)
                m = num;
        // 按照从低位到高位的顺序遍历
        for (int exp = 1; exp <= m; exp *= 10) {
            // 对数组元素的第 k 位执行计数排序
            // k = 1 -> exp = 1
            // k = 2 -> exp = 10
            // 即 exp = 10^(k-1)
            countingSortDigit(nums, exp);
        }
        return nums;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 3, 6, 2, 5, 7, 3, 6, 4, 5};
        float[] nums2 = new float[]{0.1f, 0.5f, 0.3f, 0.6f, 0.2f, 0.5f, 0.7f, 0.3f, 0.6f, 0.4f, 0.5f};
        //1. 选择排序
//        System.out.println(Arrays.toString(selectSort(nums)));
        //2. 冒泡排序
//        System.out.println(Arrays.toString(bubbleSort(nums)));
        //3. 插入排序
//        System.out.println(Arrays.toString(insertSort(nums)));
        //4. 快速排序
//        System.out.println(Arrays.toString(quickSort(nums)));
        //5. 归并排序
//        System.out.println(Arrays.toString(mergeSort(mergeSort(nums))));
        //6. 堆排序
//        System.out.println(Arrays.toString(heapSort(nums)));
        //7. 桶排序
//        System.out.println(Arrays.toString(bucketSort(nums2)));
        //8. 计数排序
//        System.out.println(Arrays.toString(countSort(nums)));
        //9. 基数排序
        System.out.println(Arrays.toString(countSort(nums)));
    }

}
