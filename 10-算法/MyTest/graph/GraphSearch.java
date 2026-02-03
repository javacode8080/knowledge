package MyTest.graph;


import java.util.*;

/**
 * @author sunjian23
 * @title GraphSearch
 * @date 2026/1/22 16:49
 * @description TODO
 */
public class GraphSearch {
    //按照list形式的图结构生成dfs/bfs

    //广度优先搜索
    public static List<Integer> bfs(Map<Vertex, List<Vertex>> graph, Vertex start) {
        //1.需要一个set集合记录已经遍历过的顶点
        Set<Vertex> vertexSet = new HashSet<>();
        //2.需要一个queue用来记录遍历过的顶点
        Queue<Vertex> queue = new LinkedList<>();
        //3. 记录结果的List
        List<Integer> res = new ArrayList<>();
        queue.offer(start);
        vertexSet.add(start);
        while (!queue.isEmpty()) {
            Vertex poll = queue.poll();
            res.add(poll.val);
            for (Vertex vertex : graph.get(poll)) {
                //已经走过的就跳过
                if (!vertexSet.contains(vertex)) {
                    queue.offer(vertex);
                    vertexSet.add(vertex);
                }
            }
        }
        return res;
    }

    public static List<Integer> dfs(Map<Vertex, List<Vertex>> graph, Vertex start) {
        Set<Vertex> vertexSet = new HashSet<>();
        List<Integer> res = new ArrayList<>();
        dfs(graph, start, vertexSet, res);
        return res;
    }

    // 深度优先搜索
    private static void dfs(Map<Vertex, List<Vertex>> graph, Vertex vertex, Set<Vertex> vertexSet, List<Integer> res) {
        //不包含这个顶点的时候才开始继续递归
        if (!vertexSet.contains(vertex)) {
            vertexSet.add(vertex);
            res.add(vertex.val);
            //已经走过的就跳过
            for (Vertex v : graph.get(vertex)) {
                dfs(graph, v, vertexSet, res);
            }
        }
    }

    public static void main(String[] args) {
        Vertex[] v = Vertex.valsToVets(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9});
        Vertex[][] edges = {{v[0], v[1]}, {v[0], v[3]}, {v[1], v[2]}, {v[1], v[4]},
                {v[2], v[5]}, {v[3], v[4]}, {v[3], v[6]}, {v[4], v[5]},
                {v[4], v[7]}, {v[5], v[8]}, {v[6], v[7]}, {v[7], v[8]}};
        GraphList graph = new GraphList(edges);
        System.out.println("\n初始化后，图为");
        graph.print();

        /* 广度优先遍历 */
        List<Integer> res = GraphSearch.bfs(graph.getGraph(), v[0]);
        System.out.println("\n广度优先遍历（BFS）顶点序列为");
        System.out.println(res);

        /* 深度优先遍历 */
        List<Integer> res2 = GraphSearch.dfs(graph.getGraph(), v[0]);
        System.out.println("\n深度优先遍历（BFS）顶点序列为");
        System.out.println(res2);
    }
}
