package MyTest.search;

/**
 * @author sunjian23
 * @title binarySearch
 * @date 2026/1/23 09:48
 * @description TODO
 */
public class binarySearch {


    //二分查找
    public static int search(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    // 二分插入,找到指定的参数插入在左侧如果存在
    public static int insertLeft(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    // 二分插入,找到指定的参数插入在右侧如果存在
    public static int insertRight(int[] nums, int target) {
        if (nums.length == 0) {
            return 0;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                low = mid + 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 2, 3, 3, 4, 4, 5, 6, 7, 9};
        int search = search(nums, 8);
        System.out.println(search);
        int insertLeft = insertLeft(nums, 10);
        System.out.println(insertLeft);
        int insertRight = insertRight(nums, 10);
        System.out.println(insertRight);
    }
}
