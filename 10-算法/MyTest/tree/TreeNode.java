package MyTest.tree;

import java.util.*;

/**
 * @author sunjian23
 * @title TreeNode
 * @date 2026/1/21 11:13
 * @description TODO
 */
public class TreeNode {
    // 定义一个根节点
    private TreeNode root;
    private int val;
    private TreeNode left;
    private TreeNode right;
    //给AVL数专用的字段 - 节点的高度'
    private int high;

    public static int getHigh(TreeNode node) {
        // 空节点的高为-1
        return node == null ? -1 : node.high;
    }

    public static void setHigh(TreeNode node) {
        // 节点左右节点的高度最大值+1
        node.high = Math.max(TreeNode.getHigh(node.left), TreeNode.getHigh(node.right)) + 1;
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public TreeNode(int val, TreeNode left, TreeNode right, int high) {
        this.val = val;
        this.left = left;
        this.right = right;
        this.high = high;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    // 1.二叉树的层序遍历
    public static List<Integer> levelOrder(TreeNode cur) {
        //借助一个数组遍历
        Queue<TreeNode> queue = new LinkedList<>();
        // 结果
        List<Integer> result = new ArrayList<>();
        if (cur == null) {
            return result;
        }
        queue.add(cur);
        while (!queue.isEmpty()) {
            //弹出一个
            TreeNode node = queue.poll();
            //放入到结果中
            result.add(node.val);
            //左节点入栈
            if (node.left != null) {
                queue.add(node.left);
            }
            //右节点入栈
            if (node.right != null) {
                queue.add(node.right);
            }
        }
        return result;
    }

    // 2.前序遍历
    public static List<Integer> preOrder(TreeNode cur) {
        List<Integer> result = new ArrayList<>();
        preOrder(cur, "pre", result);
        return result;
    }

    // 3.前序遍历
    public static List<Integer> inOrder(TreeNode cur) {
        List<Integer> result = new ArrayList<>();
        preOrder(cur, "in", result);
        return result;
    }

    // 4.前序遍历
    public static List<Integer> postOrder(TreeNode cur) {
        List<Integer> result = new ArrayList<>();
        preOrder(cur, "post", result);
        return result;
    }

    //5. 辅助方法
    private static void preOrder(TreeNode node, String type, List<Integer> result) {
        if (null == node) {
            return;
        }
        //1. 前序
        if (type.equals("pre")) result.add(node.val);
        //2. 找左节点
        preOrder(node.left, type, result);
        //3. 中序
        if (type.equals("in")) result.add(node.val);
        //4. 找右节点
        preOrder(node.right, type, result);
        //5. 后续
        if (type.equals("post")) result.add(node.val);
    }

    //6. 二叉搜索树相关的方法
    // 6.1 二叉搜索树的查找
    public TreeNode search(int val) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.val == val) {
                break;
            } else if (cur.val < val) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return cur;
    }

    // 6.2 二叉搜索树的添加
    public void insert(int val) {
        //1.根节点为空的情况下我添加到根节点即可
        if (root == null) {
            root = new TreeNode(val);
        }
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.val == val) {
                return;
            } else if (cur.val < val) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }
        }
        //找到最后之后我只需把他添加到pre的后面即可
        TreeNode child = new TreeNode(val);
        if (val > pre.val) {
            pre.right = child;
        } else {
            pre.left = child;
        }
        // 计算高度
        setHigh(child);
    }

    // 6.3 二叉搜索树的删除
    public void delete(int val) {
        if (root == null) return;
        TreeNode cur = root;
        TreeNode pre = null;
        while (cur != null) {
            if (cur.val == val) {
                // 当存在0/1个子节点的时候
                if (cur.left == null || cur.right == null) {
                    TreeNode child = cur.left == null ? cur.right : cur.left;
                    //判断当前节点是否为根节点
                    if (cur == root) {
                        root = child;
                    } else {
                        //如果当前要删除的节点是前一个节点的左节点且当前节点下面还有一个child的情况下放到左节点
                        if (pre.left == cur)
                            pre.left = child;
                        else
                            pre.right = child;
                    }
                    return;
                } else {
                    // 有两个节点的情况下,找到cur节点的中序遍历的下一个节点(也就是当前cur节点的右子树的最小节点)
                    // 右子树
                    TreeNode temp = cur.right;
                    // 迭代获取最左节点，就是cur的最小柚子树节点
                    while (temp.left != null) {
                        temp = temp.left;
                    }
                    if (pre.val < val) {
                        pre.right = temp;
                    } else {
                        pre.left = temp;
                    }
                    //移除temp节点
                    delete(temp.val);
                }
            } else if (cur.val < val) {
                pre = cur;
                cur = cur.right;
            } else {
                pre = cur;
                cur = cur.left;
            }
        }

    }

    // 7. AVL树相关方法
    // 7.1 获取平衡因子
    int balanceFactor(TreeNode node) {
        // 空节点平衡因子为 0
        if (node == null)
            return 0;
        // 节点平衡因子 = 左子树高度 - 右子树高度
        return getHigh(node.left) - getHigh(node.right);
    }

    // 7.2 右旋(将一个偏左的子树重新平衡)
    TreeNode rightRotate(TreeNode node) {
        TreeNode child = node.left;
        TreeNode grandChild = child.right;
        // 以child为原点，将child旋转
        node.left = grandChild;
        child.right = node;
        //重新计算高度
        setHigh(node);
        setHigh(child);
        return child;
    }

    // 7.3 左旋
    TreeNode leftRotate(TreeNode node) {
        TreeNode child = node.right;
        TreeNode grandChild = child.left;
        // 以child为原点，将child旋转
        node.right = grandChild;
        child.left = node;
        //重新计算高度
        setHigh(node);
        setHigh(child);
        return child;
    }

    // 7.4 旋转方法
    TreeNode rotate(TreeNode node) {
        int balanceFactor = balanceFactor(node);
        if (balanceFactor >= -1 && balanceFactor <= 1) {
            return node;
        } else if (balanceFactor > 1) {//左偏树，要进行右旋
            int childBalanceFactor = balanceFactor(node.left);
            if (childBalanceFactor < 0) {// 子节点右偏，要先对子节点左旋
                node.left = leftRotate(node.left);
                return rightRotate(node);
            } else {
                return rightRotate(node);
            }
        } else {// 右偏树，要进行左旋
            int childBalanceFactor = balanceFactor(node.left);
            if (childBalanceFactor > 0) {// 子节点左偏，要先对子节点右旋
                node.right = rightRotate(node.right);
                return leftRotate(node);
            } else {
                return leftRotate(node);
            }
        }
    }

    // 7.5 AVL树的插入方法
    public void insertAVL(int val) {
        root = insertHelper(root, val);
    }

    private TreeNode insertHelper(TreeNode cur, int val) {
        if (cur == null) {
            return new TreeNode(val);
        }

        if (cur.val == val) {
            return cur;
        } else if (cur.val < val) {
            cur.right = insertHelper(cur.right, val);
        } else {
            cur.left = insertHelper(cur.left, val);
        }
        // 计算高度
        setHigh(cur);
        //旋转
        cur = rotate(cur);
        // 返回子树的根节点
        return cur;
    }

    //7.6 AVL树的删除方法
    public void remove(int val) {
        root = romoveHelper(root, val);
    }

    private TreeNode romoveHelper(TreeNode cur, int val) {
        if (cur == null) return null;
        if (cur.val < val) {
            cur.right = romoveHelper(cur.right, val);
        } else if (cur.val > val) {
            cur.left = romoveHelper(cur.left, val);
        } else {
            // 当前这个要移除的节点有几个子节点
            if (cur.left == null || cur.right == null) {
                TreeNode child = cur.left == null ? cur.right : cur.left;
                if (child == null) {
                    //当子节点为null说明没有子节点，直接返回，让上一层去整理高度并旋转
                    return null;
                } else {
                    // 子节点替换了当前节点，这里就不能再返回了，需要在这个节点就要整理高度并旋转
                    cur = child;
                }
            } else {
                //有两个节点,先找右子树的最左节点
                TreeNode temp = cur.right;
                while (temp.left != null) {
                    temp = temp.left;
                }
                //移除temp节点.注意这里必须要从右子树的根节点开始去移除，这样才能重新平衡这个右子树
                cur.right = romoveHelper(cur.right, temp.val);
                cur.val = temp.val;
            }
        }
        // 重新计算高度
        getHigh(cur);
        // 旋转
        return rotate(cur);
    }

    public static void main(String[] args) {
        TreeNode root2 = new TreeNode(0);
        root2.insertAVL(1);
        root2.insertAVL(2);
        root2.insertAVL(3);
        root2.insertAVL(4);
        root2.insertAVL(5);
        root2.insertAVL(6);
        root2.insertAVL(7);
        root2.insertAVL(8);
        root2.insertAVL(9);
        root2.insertAVL(10);
        root2.insertAVL(11);
        root2.insertAVL(12);
        root2.insertAVL(13);
        root2.insertAVL(14);
        root2.insertAVL(15);
        root2.insertAVL(16);
        root2.insertAVL(17);
        root2.insertAVL(18);
        root2.insertAVL(19);
        root2.insertAVL(20);
        printTreePretty(root2.root);
        List<Integer> inOrder = TreeNode.inOrder(root2.root);
        System.out.println(inOrder);
        List<Integer> levelOrder = TreeNode.levelOrder(root2.root);
        System.out.println(levelOrder);
//        root2.remove(8);
        root2.remove(13);
        root2.remove(14);
        root2.remove(15);
        printTreePretty(root2.root);
        List<Integer> inOrder2 = TreeNode.inOrder(root2.root);
        System.out.println(inOrder2);
        List<Integer> levelOrder2 = TreeNode.levelOrder(root2.root);
        System.out.println(levelOrder2);
        List<Integer> preOrder = TreeNode.preOrder(root2.root);
        System.out.println(preOrder);
        List<Integer> postOrder = TreeNode.postOrder(root2.root);
        System.out.println(postOrder);
    }
    // 最实用的树形打印方法
    public static void printTreePretty(TreeNode root) {
        if (root == null) return;

        // 获取树的高度
        int height = getMaxDepth(root);
        // 最后一层的节点数
        int width = (int) Math.pow(2, height) - 1;

        // 初始化一个二维数组来存储树的结构
        String[][] grid = new String[height * 2 - 1][width];
        for (String[] row : grid) {
            Arrays.fill(row, "  ");
        }

        // 使用递归填充网格
        fillGrid(grid, root, 0, 0, width - 1);

        // 打印网格
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private static void fillGrid(String[][] grid, TreeNode node, int row, int left, int right) {
        if (node == null) return;

        int mid = (left + right) / 2;
        grid[row][mid] = String.format("%2d", node.val);

        if (node.left != null) {
            grid[row + 1][(left + mid - 1) / 2] = " /";
            fillGrid(grid, node.left, row + 2, left, mid - 1);
        }

        if (node.right != null) {
            grid[row + 1][(mid + 1 + right) / 2] = "\\ ";
            fillGrid(grid, node.right, row + 2, mid + 1, right);
        }
    }
    // 获取树的最大深度
    private static int getMaxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getMaxDepth(root.left), getMaxDepth(root.right)) + 1;
    }
}
