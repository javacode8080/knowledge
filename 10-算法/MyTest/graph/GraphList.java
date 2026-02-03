package MyTest.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sunjian23
 * @title Graph_list
 * @date 2026/1/22 16:06
 * @description TODO
 */
public class GraphList {
    private Map<Vertex, List<Vertex>> graph;

    public Map<Vertex, List<Vertex>> getGraph() {
        return graph;
    }

    public GraphList(Vertex[][] vertices) {
        for (Vertex[] vertex : vertices) {
            //添加顶点
            addVertex(vertex[0]);
            addVertex(vertex[1]);
            //添加边
            addEdge(vertex[0], vertex[1]);
        }
    }

    public void addVertex(Vertex vertex) {
        if (null == graph) {
            graph = new HashMap<>();
        }
        //如果已经添加过的就跳过
        if (graph.containsKey(vertex)) return;
        graph.put(vertex, new ArrayList<>());
    }

    public void removeVertex(Vertex vertex) {
        if (null == graph) {
            graph = new HashMap<>();
        }
        if (!graph.containsKey(vertex))
            throw new IllegalArgumentException();
        //先移除元素
        graph.remove(vertex);
        //遍历其他的内容移除同样的元素
        graph.forEach((k, v) -> {
            v.remove(vertex);
        });
    }

    //添加边
    public void addEdge(Vertex from, Vertex to) {
        if (null == graph) {
            graph = new HashMap<>();
        }
        if (!graph.containsKey(from) || !graph.containsKey(to) || from == to)
            throw new IllegalArgumentException();
        graph.get(from).add(to);
        graph.get(to).add(from);
    }

    //移除边
    public void removeEdge(Vertex from, Vertex to) {
        if (null == graph) {
            graph = new HashMap<>();
        }
        if (!graph.containsKey(from) || !graph.containsKey(to) || from == to)
            throw new IllegalArgumentException();
        graph.get(from).remove(to);
        graph.get(to).remove(from);
    }

    /* 打印邻接表 */
    public void print() {
        System.out.println("邻接表 =");
        for (Map.Entry<Vertex, List<Vertex>> pair : graph.entrySet()) {
            List<Integer> tmp = new ArrayList<>();
            for (Vertex vertex : pair.getValue())
                tmp.add(vertex.val);
            System.out.println(pair.getKey().val + ": " + tmp + ",");
        }
    }

    public static void main(String[] args) {
        /* 初始化无向图 */
        Vertex[] v = Vertex.valsToVets(new int[] { 1, 3, 2, 5, 4 });
        Vertex[][] edges = { { v[0], v[1] }, { v[0], v[3] }, { v[1], v[2] },
                { v[2], v[3] }, { v[2], v[4] }, { v[3], v[4] } };
        GraphList graph = new GraphList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 添加边 */
        // 顶点 1, 2 即 v[0], v[2]
        graph.addEdge(v[0], v[2]);
        System.out.println("\n添加边 1-2 后，图为");
        graph.print();

        /* 删除边 */
        // 顶点 1, 3 即 v[0], v[1]
        graph.removeEdge(v[0], v[1]);
        System.out.println("\n删除边 1-3 后，图为");
        graph.print();

        /* 添加顶点 */
        Vertex v5 = new Vertex(6);
        graph.addVertex(v5);
        System.out.println("\n添加顶点 6 后，图为");
        graph.print();

        /* 删除顶点 */
        // 顶点 3 即 v[1]
        graph.removeVertex(v[1]);
        System.out.println("\n删除顶点 3 后，图为");
        graph.print();
    }
}
