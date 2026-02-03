package MyTest.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author sunjian23
 * @title Permutations
 * @date 2026/1/29 10:34
 * @description TODO
 */
//全排列问题
public class Permutations {

    // 全排列

    /**
     * @param nums:给定的数组
     * @param deduplication：是否去重
     * @return {@link List< List< Integer>>}
     * @author sunjian23
     * @description TODO
     * @date 2026/1/29 13:59
     *
     *
     **/

    public static List<List<Integer>> permutations(int[] nums, boolean deduplication) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> state = new ArrayList<>();
        boolean[] selected = new boolean[nums.length];
        backTrack(state, nums, result, selected, deduplication);
        return result;
    }


    public static void backTrack(List<Integer> state, int[] choices, List<List<Integer>> res, boolean[] selected, boolean deduplication) {
        //1.是否满足条件
        if (isSolution(state, choices)) {
            solutionRecord(state, res);
        }
        Set<Integer> duplication = new HashSet<>();
        //2.遍历可能的选择
        for (int i = 0; i < choices.length; i++) {
            // 3. 剪枝
            if (isValid(selected, i) && deduplication(deduplication, choices[i], duplication)) {
                // 4. 尝试
                putChoice(state, choices[i], selected, i, duplication);
                // 5. 迭代
                backTrack(state, choices, res, selected, deduplication);
                //6. 回溯
                undoChoice(state, selected, i);
            }
        }
    }

    //是否去重
    private static boolean deduplication(boolean deduplication, int choice, Set<Integer> duplication) {
        if (deduplication) {
            return !duplication.contains(choice);
        }
        return true;
    }

    private static void undoChoice(List<Integer> state, boolean[] selected, int i) {
        state.remove(state.size() - 1);
        selected[i] = false;
    }

    private static void putChoice(List<Integer> state, Integer choice, boolean[] selected, int i, Set<Integer> duplication) {
        state.add(choice);
        selected[i] = true;
        duplication.add(choice);
    }

    private static boolean isValid(boolean[] selected, int i) {
        return !selected[i];
    }

    public static boolean isSolution(List<Integer> state, int[] choices) {
        return state.size() == choices.length;
    }

    public static void solutionRecord(List<Integer> state, List<List<Integer>> res) {
        res.add(new ArrayList<>(state));
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 3};
        print(permutations(nums, true));
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
