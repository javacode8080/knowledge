package MyTest.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author sunjian23
 * @title Heap
 * @date 2026/1/22 10:46
 * @description TODO
 */
// 大顶堆
public class MaxHeap {
    private List<Integer> heap;

    // 建堆方法
    /* 构造方法，根据输入列表建堆 */
    public MaxHeap(List<Integer> nums) {
        // 将列表元素原封不动添加进堆
        heap = new ArrayList<>(nums);
        // 堆化除叶节点以外的其他所有节点
        for (int i = parent(heap.size() - 1); i >= 0; i--) {
            shiftDown(i);
        }
    }

    // 获取栈顶元素
    public Integer peek() {
        return heap.isEmpty() ? null : heap.get(0);
    }

    // 父节点
    public Integer parent(int index) {
        if (index == 0) {
            return -1;
        } else {
            return (index - 1) / 2;
        }
    }

    // 左子节点
    public Integer left(int index) {
        return 2 * index + 1;
    }

    // 右子节点
    public Integer right(int index) {
        return 2 * index + 2;
    }

    // 元素入堆
    public void push(int value) {
        heap.add(value);
        shiftUp(heap);
    }

    // 栈顶元素出堆
    public int pop() {
        if (heap == null || heap.isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        //1.交换首尾元素,移除末尾元素
        int temp = heap.get(0);
        heap.set(0, heap.get(heap.size() - 1));
        heap.remove(heap.size() - 1);
        //2.shiftDown
        shiftDown(heap);
        return temp;
    }

    // 自下而上(这个方法默认了从第最后一个节点开始)
    public void shiftUp(List<Integer> heap) {
        if (heap != null && !heap.isEmpty()) {
            //1. 取出最后一个叶子节点
            int index = heap.size() - 1;
            Integer last = heap.get(index);
            while (true) {
                if (last > parent(index) && parent(index) >= 0) {
                    //交换数据
                    heap.set(index, heap.get(parent(index)));
                    heap.set(parent(index), last);
                    // 更新索引
                    index = parent(index);
                } else {
                    break;
                }
            }
        }
    }

    /* 从节点 i 开始，从底至顶堆化 */
    public void shiftUp(int i) {
        while (true) {
            // 获取节点 i 的父节点
            int p = parent(i);
            // 当“越过根节点”或“节点无须修复”时，结束堆化
            if (p < 0 || heap.get(i) <= heap.get(p))
                break;
            // 交换两节点
            swap(i, p);
            // 循环向上堆化
            i = p;
        }
    }

    // 自上而下(这个方法默认了从第一个节点开始)
    public void shiftDown(List<Integer> heap) {
        if (heap != null && !heap.isEmpty()) {
            int index = 0;
            int first = heap.get(index);
            while (true) {
                // 当前节点如果小于左右子节点的话，和左右子节点中最大值交换位置
                // 初始化左右子节点
                Integer left = Integer.MIN_VALUE;
                Integer right = Integer.MIN_VALUE;
                Integer leftIndex = left(index);
                Integer rightIndex = right(index);
                if (leftIndex < heap.size()) left = heap.get(leftIndex);
                if (rightIndex < heap.size()) right = heap.get(rightIndex);
                //此时左右节点都不存在了，则结束当前的循环
                if (left == Integer.MIN_VALUE && right == Integer.MIN_VALUE) break;
                //比较first，left，right，谁最大谁作为父节点
                int max = Math.max(left, right);
                if (first < max) {
                    if (left == max) {
                        //和左节点交换位置
                        heap.set(index, max);
                        heap.set(leftIndex, first);
                        index = leftIndex;
                    } else {
                        //和右节点交换位置
                        heap.set(index, max);
                        heap.set(rightIndex, first);
                        index = rightIndex;
                    }
                } else {
                    break;
                }

            }
        }
    }

    /* 从节点 i 开始，从顶至底堆化 */
    void shiftDown(int i) {
        while (true) {
            // 判断节点 i, l, r 中值最大的节点，记为 ma
            int l = left(i), r = right(i), ma = i;
            if (l < heap.size() && heap.get(l) > heap.get(ma))
                ma = l;
            if (r < heap.size() && heap.get(r) > heap.get(ma))
                ma = r;
            // 若节点 i 最大或索引 l, r 越界，则无须继续堆化，跳出
            if (ma == i)
                break;
            // 交换两节点
            swap(i, ma);
            // 循环向下堆化
            i = ma;
        }
    }


    // 交换索引元素
    public void swap(int i, int j) {
        int temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    // 简化版打印方法 - 更易读的实现
    public void printHeapSimple() {
        if (heap.isEmpty()) {
            System.out.println("堆为空");
            return;
        }

        int height = (int) (Math.log(heap.size()) / Math.log(2)) + 1;
        printHeapSimpleHelper(0, "", true, height, 0);
    }

    private void printHeapSimpleHelper(int index, String prefix, boolean isLeft, int totalHeight, int currentDepth) {
        if (index >= heap.size()) return;

        // 计算缩进
        String indent = "    ";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < currentDepth; i++) {
            sb.append(indent);
        }
        String currentIndent = sb.toString();

        if (currentDepth == 0) {
            // 根节点
            System.out.println(currentIndent + heap.get(index));
        } else {
            System.out.println(currentIndent + prefix + (isLeft ? "└── " : "├── ") + heap.get(index));
        }

        // 递归打印子节点
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size() || rightChild < heap.size()) {
            printHeapSimpleHelper(leftChild, currentIndent + (isLeft ? "    " : "│   "),
                    rightChild >= heap.size(), totalHeight, currentDepth + 1);
            printHeapSimpleHelper(rightChild, currentIndent + (isLeft ? "    " : "│   "),
                    true, totalHeight, currentDepth + 1);
        }
    }

    // 最实用的打印方法 - 水平展示
    public void printHeapPretty() {
        if (heap.isEmpty()) return;

        int height = (int) (Math.log(heap.size()) / Math.log(2)) + 1;
        int width = (int) Math.pow(2, height) - 1;

        // 初始化网格
        String[][] grid = new String[height * 2][width * 3];
        for (String[] row : grid) {
            Arrays.fill(row, " ");
        }

        // 填充网格
        fillHeapGrid(grid, 0, 0, width * 3 - 1, 0, height);

        // 打印网格
        for (String[] row : grid) {
            for (String cell : row) {
                System.out.print(cell);
            }
            System.out.println();
        }
    }

    private void fillHeapGrid(String[][] grid, int index, int left, int right, int row, int totalHeight) {
        if (index >= heap.size()) return;

        int mid = (left + right) / 2;
        String value = String.valueOf(heap.get(index));

        // 放置节点值
        for (int i = 0; i < value.length() && mid + i < grid[0].length; i++) {
            grid[row][mid + i] = String.valueOf(value.charAt(i));
        }

        // 计算子节点位置
        int leftChild = 2 * index + 1;
        int rightChild = 2 * index + 2;

        if (leftChild < heap.size()) {
            // 画左连接线
            grid[row + 1][mid - 1] = "/";
            fillHeapGrid(grid, leftChild, left, mid - 1, row + 2, totalHeight);
        }

        if (rightChild < heap.size()) {
            // 画右连接线
            grid[row + 1][mid + 1] = "\\";
            fillHeapGrid(grid, rightChild, mid + 1, right, row + 2, totalHeight);
        }
    }

    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap(Arrays.asList(10, 20, 15, 30, 40, 25, 5, 35, 45, 50));
        System.out.println("\n堆的树形结构（美观版）：");
        heap.printHeapPretty();
        heap.push(800);
        System.out.println("\n堆的树形结构（美观版）：");
        heap.printHeapPretty();
        heap.pop();
        System.out.println("\n堆的树形结构（美观版）：");
        heap.printHeapPretty();
    }

}
