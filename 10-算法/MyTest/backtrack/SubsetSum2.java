package MyTest.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sunjian23
 * @title SubsetSum
 * @date 2026/1/29 14:21
 * @description TODO
 */

/**
 * 子集和问题2:
 * <p>
 * 给定一个正整数数组 nums 和一个目标正整数 target ，
 * 请找出所有可能的组合，使得组合中的元素和等于 target 。
 * 给定数组无重复元素，每个元素可以被选取多次。
 * 请以列表形式返回这些组合，列表中不应包含重复组合。
 * 注意：书中的示例比我的这个实现更简单高效
 **/
public class SubsetSum2 {
    public static List<List<Integer>> subsetSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        boolean[] selected = new boolean[nums.length];
        backTrack(state, nums, result, target, selected);
        return result;
    }

    public static void backTrack(List<Integer> state, int[] choices, List<List<Integer>> res, int target, boolean[] selected) {
        //1.是否满足结果
        if (isSolution(state, target)) {
            solutionRecord(state, res);
        }
        //2. 循环选择
        Set<Integer> dep = new HashSet<>();
        for (int i = 0; i < choices.length; i++) {
            if (isValid(state, choices[i], target, selected[i], dep)) {
                choice(state, choices[i], selected, i, dep);
                backTrack(state, choices, res, target, selected);
                undoChoice(state, selected, i);
            }
        }
    }

    private static void undoChoice(List<Integer> state, boolean[] selected, int i) {
        state.remove(state.size() - 1);
        selected[i] = false;
    }

    private static void choice(List<Integer> state, int choice, boolean[] selected, int i, Set<Integer> dep) {
        state.add(choice);
        selected[i] = true;
        dep.add(choice);
    }

    private static boolean isValid(List<Integer> state, int choice, int target, boolean selected, Set<Integer> dep) {
        return sum(state) + choice <= target && uper(state, choice) && !selected && !dep.contains(choice);
    }

    private static boolean uper(List<Integer> state, int choice) {
        return state.isEmpty() || state.get(state.size() - 1) <= choice;
    }

    public static boolean isSolution(List<Integer> state, int target) {
        return sum(state) == target;

    }

    public static void solutionRecord(List<Integer> state, List<List<Integer>> res) {
        res.add(new ArrayList<>(state));
    }

    public static int sum(List<Integer> state) {
        int sum = 0;
        for (Integer i : state) {
            sum += i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, 5, 3, 6, 2, 5, 1, 3, 3};
        int target = 9;
        print(subsetSum(nums, target));
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
}
