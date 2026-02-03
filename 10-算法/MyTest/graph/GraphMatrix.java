package MyTest.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sunjian23
 * @title Graph_matrix
 * @date 2026/1/22 16:06
 * @description TODO
 */
public class GraphMatrix {

    // 图的每个节点对应的值和索引位置
    private List<Integer> vertices = new ArrayList<>();

    // 图的最后形式
    private List<List<Integer>> graph = new ArrayList<>();

    public GraphMatrix(int[] vertices, int[][] edges) {
        // 首先添加节点
        for (Integer vertex : vertices) {
            addVertex(vertex);
        }
        // 将边进行赋值
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    public int size() {
        return vertices.size();
    }

    // 添加一个具体的节点
    public void addVertex(Integer vertex) {
        //1.获取插入之前的图的大小
        int n = size();
        //2.添加新的节点
        vertices.add(vertex);
        //3.初始创建新的行，并将新行的所有列元素初始化为0
        List<Integer> row = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            row.add(0);
        }
        //4.将新创建的行加入到图中
        graph.add(row);
        //5. 处理列，因为新增加一个节点就要新增加一列
        for (List<Integer> rows : graph) {
            rows.add(0);
        }
    }

    // 根据所在的索引移除一个节点
    public void removeVertex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        //1.删除vertices中对应位置
        vertices.remove(index);
        //2.删除图中的第index行
        graph.remove(index);
        //3.删除每行的第index元素
        for (List<Integer> row : graph) {
            row.remove(index);
        }
    }

    // 添加边
    public void addEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        graph.get(i).set(j, 1);
        graph.get(j).set(i, 1);
    }

    // 添加边
    public void removeEdge(int i, int j) {
        if (i < 0 || j < 0 || i >= size() || j >= size() || i == j) {
            throw new IndexOutOfBoundsException("index out of bounds");
        }
        graph.get(i).set(j, 0);
        graph.get(j).set(i, 0);
    }

    /* 打印邻接矩阵 */
    public void print() {
        System.out.print("顶点列表 = ");
        System.out.println(vertices);
        System.out.println("邻接矩阵 =");
        printMatrix(graph);
    }


    /* 打印矩阵（List） */
    public static <T> void printMatrix(List<List<T>> matrix) {
        System.out.println("[");
        for (List<T> row : matrix) {
            System.out.println("  " + row + ",");
        }
        System.out.println("]");
    }


    public static void main(String[] args) {
        /* 初始化无向图 */
        // 请注意，edges 元素代表顶点索引，即对应 vertices 元素索引
        int[] vertices = {1, 3, 2, 5, 4};
        int[][] edges = {{0, 1}, {0, 3}, {1, 2}, {2, 3}, {2, 4}, {3, 4}};
        GraphMatrix graph = new GraphMatrix(vertices, edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 添加边 */
        // 顶点 1, 2 的索引分别为 0, 2
        graph.addEdge(0, 2);
        System.out.println("\n添加边 1-2 后，图为");
        graph.print();

        /* 删除边 */
        // 顶点 1, 3 的索引分别为 0, 1
        graph.removeEdge(0, 1);
        System.out.println("\n删除边 1-3 后，图为");
        graph.print();

        /* 添加顶点 */
        graph.addVertex(6);
        System.out.println("\n添加顶点 6 后，图为");
        graph.print();

        /* 删除顶点 */
        // 顶点 3 的索引为 1
        graph.removeVertex(1);
        System.out.println("\n删除顶点 3 后，图为");
        graph.print();
    }

}
