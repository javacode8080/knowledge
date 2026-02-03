package MyTest.DivideAndConquer;

import MyTest.tree.TreeNode;

import java.util.*;

/**
 * @author sunjian23
 * @title DivideAndConquer
 * @date 2026/1/27 14:01
 * @description TODO
 */
public class DivideAndConquer {

    /*********************************1.分治实现二分查找*****************************************************/
    public static int dfsSearch(int[] nums, int target, int left, int right) {
        if (left > right) {
            //不存在则返回-1
            return -1;
        }
        int mid = left + (right - left) / 2;
        if (nums[mid] > target) {
            return dfsSearch(nums, target, left, mid - 1);
        } else if (nums[mid] < target) {
            return dfsSearch(nums, target, mid + 1, right);
        } else {
            return mid;
        }
    }

    public static int search(int[] nums, int target) {
        return dfsSearch(nums, target, 0, nums.length - 1);
    }

    /*********************************2.分治实现根据前序+中序/中序+后续实现树结构的还原*****************************************************/
    /**
     *
     * @param preOrder:树的前序遍历
     * @param inOrder：树的中序遍历，key为值，value为索引位置
     * @param i：根节点在前序遍历中的位置
     * @param l：中序遍历中子树的范围起点
     * @param r：中序遍历中子树的范围终点
     * @return
     */
    public static TreeNode dfsPreTree(int[] preOrder, Map<Integer, Integer> inOrder, int i, int l, int r) {
        if (l > r) {
            //已经没有节点了，直接返回null
            return null;
        }
        int rootVal = preOrder[i];
        Integer m = inOrder.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        //获取左节点
        root.setLeft(dfsPreTree(preOrder, inOrder, i + 1, l, m - 1));
        //获取右节点
        root.setRight(dfsPreTree(preOrder, inOrder, i + 1 + m - l, m + 1, r));
        return root;
    }


    private static TreeNode dfsPostTree(int[] postOrder, Map<Integer, Integer> inOrder, int i, int l, int r) {
        if (l > r) {
            //已经没有节点了，直接返回null
            return null;
        }
        int rootVal = postOrder[i];
        Integer m = inOrder.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        //获取左节点
        root.setLeft(dfsPostTree(postOrder, inOrder, i - 1 - (r - m), l, m - 1));
        //获取右节点
        root.setRight(dfsPostTree(postOrder, inOrder, i - 1, m + 1, r));
        return root;
    }

    public static TreeNode buildTree(int[] preOrder, int[] inOrder, int[] postOrder) {
        if (null == inOrder) {
            throw new RuntimeException("想要恢复树结构必须有中序遍历！");
        }
        Map<Integer, Integer> inOrderMap = new HashMap();
        for (int i = 0; i < inOrder.length; i++) {
            if (inOrderMap.containsKey(inOrder[i])) {
                throw new RuntimeException("想要恢复树结构不能存在重复数据");
            }
            inOrderMap.put(inOrder[i], i);
        }
        if (preOrder != null) {
            return dfsPreTree(preOrder, inOrderMap, 0, 0, inOrder.length - 1);
        } else if (postOrder != null) {
            return dfsPostTree(postOrder, inOrderMap, inOrder.length - 1, 0, inOrder.length - 1);
        } else {
            throw new RuntimeException("前序遍历/后序遍历必存其一！");
        }
    }

    /*********************************3.汉诺塔问题*****************************************************/
    /**
     * @param i:移动的圆盘数量
     * @param src：起始位置
     * @param buf：缓冲位置
     * @param tar：目标位置
     * @return
     * @author sunjian23
     * @description TODO
     * @date 2026/1/27 16:27
     */
    public static void hanotaDfs(int i, List<Integer> src, List<Integer> buf, List<Integer> tar) {
        System.out.print("buf的存储值：");
        System.out.println(buf);
        if (i == 1) {
            move(src, tar);
            return;
        }
        //拆分
        //1.将n-1个圆盘以tar为缓冲从src移动到buf
        hanotaDfs(i - 1, src, tar, buf);
        //2.剩一个采用f(1)方式从src移动到tar
        move(src, tar);
        //3.将n-1个圆盘从buf,以src为缓冲移动到tar
        hanotaDfs(i - 1, buf, src, tar);
    }

    public static void hanota(List<Integer> src, List<Integer> buf, List<Integer> tar) {
        int n = src.size();
        hanotaDfs(n, src, buf, tar);
    }

    //单个问题，就是把最大的圆盘放置到目标位置
    private static void move(List<Integer> src, List<Integer> tar) {
        //最大的那个圆盘
        Integer remove = src.remove(src.size() - 1);
        tar.add(remove);
    }

    public static void main(String[] args) {
        //1. 二分查找
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(search(nums, 5));
        //2. 根据前序+中序或者中序+后续实现树的还原
        int[] preorder = {3, 9, 2, 1, 7};
        int[] inorder = {9, 3, 1, 2, 7};
        int[] postorder = {9, 1, 7, 2, 3};
        System.out.println("前序遍历 = " + Arrays.toString(preorder));
        System.out.println("中序遍历 = " + Arrays.toString(inorder));

        // 根据前+中实现
        TreeNode root = buildTree(preorder, inorder, null);
        // 根据中+后实现
//        TreeNode root = buildTree(null, inorder, postorder);
        System.out.println("构建的二叉树为：");
        TreeNode.printTreePretty(root);
        System.out.println("前序遍历为：");
        System.out.println(TreeNode.preOrder(root));
        System.out.println("中序遍历为：");
        System.out.println(TreeNode.inOrder(root));
        System.out.println("后序遍历为：");
        System.out.println(TreeNode.postOrder(root));
        //3.汉诺塔
        List<Integer> src = new ArrayList<>();
        src.add(1);
        src.add(2);
        src.add(3);
//        src.add(4);
//        src.add(5);
//        src.add(6);
//        src.add(7);
//        src.add(8);
//        src.add(9);
        List<Integer> buf = new ArrayList<>();
        List<Integer> tar = new ArrayList<>();
        hanota(src, buf, tar);
        System.out.println("汉诺塔移动结果：");
        System.out.println(tar);
    }

}
