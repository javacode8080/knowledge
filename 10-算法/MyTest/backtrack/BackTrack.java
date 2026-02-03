package MyTest.backtrack;

import MyTest.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunjian23
 * @title BackTrack
 * @date 2026/1/29 09:30
 * @description TODO
 */
public class BackTrack {
    /**************************题目：找到从根节点到节点为7的所有路径，如果遇到2则返回不记录****************************************************/

    /**************************解法1：通过回溯模板****************************************************/
    /* 判断当前状态是否为解 */
    static boolean isSolution(List<TreeNode> state) {
        return !state.isEmpty() && state.get(state.size() - 1).getVal() == 7;
    }

    /* 记录解 */
    static void recordSolution(List<TreeNode> state, List<List<TreeNode>> res) {
        res.add(new ArrayList<>(state));
    }

    /* 判断在当前状态下，该选择是否合法 */
    static boolean isValid(List<TreeNode> state, TreeNode choice) {
        return choice != null && choice.getVal() != 2;
    }

    /* 更新状态 */
    static void makeChoice(List<TreeNode> state, TreeNode choice) {
        state.add(choice);
    }

    /* 恢复状态 */
    static void undoChoice(List<TreeNode> state, TreeNode choice) {
        state.remove(state.size() - 1);
    }

    /* 回溯算法：例题三 */
    static void backtrack(List<TreeNode> state, List<TreeNode> choices, List<List<TreeNode>> res) {
        // 检查是否为解
        if (isSolution(state)) {
            // 记录解
            recordSolution(state, res);
        }
        // 遍历所有选择
        for (TreeNode choice : choices) {
            // 剪枝：检查选择是否合法
            if (isValid(state, choice)) {
                // 尝试：做出选择，更新状态
                makeChoice(state, choice);
                // 进行下一轮选择
                backtrack(state, Arrays.asList(choice.getLeft(), choice.getRight()), res);
                // 回退：撤销选择，恢复到之前的状态
                undoChoice(state, choice);
            }
        }
    }

    public static List<List<TreeNode>> backTrack(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        List<TreeNode> state = new ArrayList<>();
        state.add(root);
        List<TreeNode> choice = new ArrayList<>();
        choice.add(root.getLeft());
        choice.add(root.getRight());
        backtrack(state, choice, result);
        return result;
    }

    /**************************解法2：通过前序遍历***************************************************/
    public static void preOrder(TreeNode treeNode, List<TreeNode> path, List<List<TreeNode>> res) {
        if (treeNode == null || treeNode.getVal() == 2) {
            return;
        }
        // 尝试
        path.add(treeNode);
        //判断当前节点
        if (treeNode.getVal() == 7) {
            res.add(new ArrayList<>(path));
        }
        // 前序遍历左节点
        preOrder(treeNode.getLeft(), path, res);
        // 前序遍历右节点
        preOrder(treeNode.getRight(), path, res);
        //回溯
        path.remove(path.size() - 1);
    }

    public static List<List<TreeNode>> backTrackByPreOrder(TreeNode root) {
        List<List<TreeNode>> result = new ArrayList<>();
        List<TreeNode> path = new ArrayList<>();
        preOrder(root, path, result);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(0);
        TreeNode c1 = new TreeNode(1);
        TreeNode c2 = new TreeNode(5);
        TreeNode c3 = new TreeNode(7);
        TreeNode c4 = new TreeNode(8);
        TreeNode c5 = new TreeNode(10);
        TreeNode c6 = new TreeNode(16);
        TreeNode c7 = new TreeNode(4);
        TreeNode c8 = new TreeNode(2);
        TreeNode c9 = new TreeNode(3);
        TreeNode c10 = new TreeNode(7);
        TreeNode c11 = new TreeNode(61);
        TreeNode c12 = new TreeNode(67);
        TreeNode c13 = new TreeNode(7);
        TreeNode c14 = new TreeNode(5);
        TreeNode c15 = new TreeNode(31);
        root.setLeft(c1);
        root.setRight(c2);
        c1.setLeft(c4);
        c1.setRight(c8);
        c4.setRight(c3);
        c4.setLeft(c5);
        c2.setLeft(c6);
        c2.setRight(c7);
        c8.setLeft(c9);
        c8.setRight(c11);
        c6.setLeft(c12);
        c6.setRight(c14);
        c7.setLeft(c15);
        c7.setRight(c13);
        c11.setRight(c10);
        TreeNode.printTreePretty(root);
        List<List<TreeNode>> lists = backTrack(root);
        System.out.println("使用回溯模板生成的路径：");
        print(lists);
        System.out.println("使用前序遍历生成的路径：");
        List<List<TreeNode>> lists2 = backTrackByPreOrder(root);
        print(lists2);
    }

    public static void print(List<List<TreeNode>> lists) {
        for (List<TreeNode> list : lists) {
            System.out.print("[");
            for (TreeNode treeNode : list) {
                System.out.print(treeNode.getVal());
                if (treeNode != list.get(list.size() - 1)) {
                    System.out.print(", ");
                }
            }
            System.out.println("]");
        }
    }


}
