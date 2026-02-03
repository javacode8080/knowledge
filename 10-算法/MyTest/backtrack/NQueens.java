package MyTest.backtrack;

/**
 * @author sunjian23
 * @title Nqueens
 * @date 2026/1/29 15:18
 * @description TODO
 */

import java.util.ArrayList;
import java.util.List;

/**
 * n皇后问题
 * 根据国际象棋的规则，皇后可以攻击与同处一行、一列或一条斜线上的棋子。
 * 给定n个皇后和一个n x n大小的棋盘，
 * 寻找使得所有皇后之间无法相互攻击的摆放方案。
 **/
public class NQueens {

    public static List<List<Pair>> nQueens(int n) {
        // 按照行一行一行放，然后剪枝列重复/主次对角线重复的情况
        //1.列
        boolean[] cols = new boolean[n];
        //2.主对角线
        boolean[] diags1 = new boolean[2 * n - 1];
        //3.此对角线
        boolean[] diags2 = new boolean[2 * n - 1];
        //4. state
        List<Pair> state = new ArrayList<>();
        List<List<Pair>> result = new ArrayList<>();
        backtrack(0, n, state, result, cols, diags1, diags2);
        return result;
    }

    public static void backtrack(int start, int n, List<Pair> state,
                                 List<List<Pair>> res,
                                 boolean[] cols, boolean[] diags1, boolean[] diags2) {
        if (isSolution(state, n)) {
            solutionRecord(state, res);
            return;
        }
        // 行循环
        // 列循环
        for (int j = 0; j < n; j++) {
            Pair st = new Pair(start, j);
            if (isValid(st, cols, n, diags1, diags2)) {
                choice(state, st, cols, n, diags1, diags2);
                backtrack(start + 1, n, state, res, cols, diags1, diags2);
                undoChoice(state, st, cols, n, diags1, diags2);
            }
        }
    }

    private static void undoChoice(List<Pair> state, Pair st, boolean[] cols, int n, boolean[] diags1, boolean[] diags2) {
        state.remove(state.size() - 1);
        cols[st.col] = false;
        diags1[st.row - st.col + n - 1] = false;
        diags2[st.row + st.col] = false;
    }

    private static void choice(List<Pair> state, Pair st, boolean[] cols, int n, boolean[] diags1, boolean[] diags2) {
        state.add(st);
        cols[st.col] = true;
        diags1[st.row - st.col + n - 1] = true;
        diags2[st.row + st.col] = true;
    }

    private static boolean isValid(Pair st, boolean[] cols, int n, boolean[] diags1, boolean[] diags2) {
        //1.判断列/主/次对角线是否重复
        return !cols[st.col] && !diags1[st.row - st.col + n - 1] && !diags2[st.row + st.col];

    }

    private static void solutionRecord(List<Pair> state,
                                       List<List<Pair>> res) {
        res.add(new ArrayList<>(state));
    }

    public static boolean isSolution(List<Pair> state, int n) {
        return state.size() == n;
    }

    // 打印棋盘
    public static void printChessBoard(List<Pair> solution) {
        int n = solution.size();
        char[][] board = new char[n][n];

        // 初始化棋盘为 '#'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '#';
            }
        }

        // 放置皇后 'Q'
        for (Pair queen : solution) {
            board[queen.row][queen.col] = 'Q';
        }

        // 打印棋盘
        System.out.println("┌" + "─".repeat(n * 2 + 1) + "┐");
        for (int i = 0; i < n; i++) {
            System.out.print("│ ");
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("│");
        }
        System.out.println("└" + "─".repeat(n * 2 + 1) + "┘");
    }

    static class Pair {
        int row;
        int col;

        public Pair(int row, int col) {
            //行
            this.row = row;
            //列
            this.col = col;
        }

        @Override
        public String toString() {
            return "(" + row + "," + col + ")";
        }
    }

    public static void main(String[] args) {
        List<List<Pair>> lists = nQueens(5);
        for (int i = 1; i <= lists.size(); i++) {
            System.out.println("n皇后解法" + i + ":");
            printChessBoard(lists.get(i-1));
        }
    }
}
