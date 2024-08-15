package leetcode.Greedy;

import java.util.*;

/**
 * Given an m x n matrix, return a new matrix answer where answer[row][col] is the rank of matrix[row][col].
 *
 * The rank is an integer that represents how large an element is compared to other elements. It is calculated using the following rules:
 *
 * The rank is an integer starting from 1.
 * If two elements p and q are in the same row or column, then:
 * If p < q then rank(p) < rank(q)
 * If p == q then rank(p) == rank(q)
 * If p > q then rank(p) > rank(q)
 * The rank should be as small as possible.
 * The test cases are generated so that answer is unique under the given rules.
 *
 *
 *
 * Example 1:
 *                  [1,2]
 *                  [3,4]
 *
 *                    ||
 *                    V
 *                  [1,2]
 *                  [2,3]
 *
 * Input: matrix = [[1,2],[3,4]]
 * Output: [[1,2],[2,3]]
 * Explanation:
 * The rank of matrix[0][0] is 1 because it is the smallest integer in its row and column.
 * The rank of matrix[0][1] is 2 because matrix[0][1] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][0] is 2 because matrix[1][0] > matrix[0][0] and matrix[0][0] is rank 1.
 * The rank of matrix[1][1] is 3 because matrix[1][1] > matrix[0][1], matrix[1][1] > matrix[1][0], and both matrix[0][1] and matrix[1][0] are rank 2.
 * Example 2:
 *
 *
 * Input: matrix = [[7,7],[7,7]]
 * Output: [[1,1],[1,1]]
 * Example 3:
 *
 *
 * Input: matrix = [[20,-21,14],[-19,4,19],[22,-47,24],[-19,4,19]]
 * Output: [[4,2,3],[1,3,4],[5,1,6],[1,3,4]]
 *
 *
 * Constraints:
 *
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 500
 * -109 <= matrix[row][col] <= 10^9
 *
 */

public class _1632_Rank_Transform_of_a_Matrix {
    int[] parent;

    public int find(int x) {

        if (parent[x] != x) {
            return parent[x] = find(parent[x]);
        }
        return x;
    }

    public void union(int x, int y) {
        int px = find(x);
        int py = find(y);

        if (px != py) {
            parent[py] = px;
        }
    }

    public int[][] matrixRankTransform(int[][] matrix) {
        parent = new int[matrix.length * matrix[0].length];

        for (int i = 0; i < matrix.length * matrix[0].length; i++) {
            parent[i] = i;
        }
        Map<Integer, Map<Integer, List<Integer>>> valRowToCol = new HashMap<>();
        Map<Integer, Map<Integer, List<Integer>>> valColToRow = new HashMap<>();
        TreeMap<Integer, List<int[]>> valPoints = new TreeMap<>();
        // Mapping rows to cols and cols to Rows for the same value
        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {
                int val = matrix[r][c];
                List<int[]> pointList = valPoints.getOrDefault(val, new ArrayList<>());
                pointList.add(new int[] {r, c});
                valPoints.put(val, pointList);

                if (!valRowToCol.containsKey(val)) {
                    Map<Integer, List<Integer>> rowCol = new HashMap<>();
                    valRowToCol.put(val, rowCol);
                }
                Map<Integer, List<Integer>> rowCol = valRowToCol.get(val);
                List<Integer> cols = rowCol.getOrDefault(r, new ArrayList<>());
                cols.add(c);
                rowCol.put(r, cols);

                if (!valColToRow.containsKey(val)) {
                    Map<Integer, List<Integer>> colRow = new HashMap<>();
                    valColToRow.put(val, colRow);
                }
                Map<Integer, List<Integer>> colRow = valColToRow.get(val);
                List<Integer> rows = colRow.getOrDefault(c, new ArrayList<>());
                rows.add(r);
                colRow.put(c, rows);
            }
        }
        //Union rows and column for the same value
        for (int val : valRowToCol.keySet()) {
            Map<Integer, List<Integer>> rowCol = valRowToCol.get(val);

            for (int row : rowCol.keySet()) {
                List<Integer> cols = rowCol.get(row);
                int prevId = -1;

                for (int col : cols) {
                    int currId = col + row * matrix[0].length;

                    if (prevId != -1) {
                        union(prevId, currId);
                    }
                    prevId = currId;
                }
            }
        }

        for (int val : valColToRow.keySet()) {
            Map<Integer, List<Integer>> colRow = valColToRow.get(val);

            for (int col : colRow.keySet()) {
                List<Integer> rows = colRow.get(col);
                int prevId = -1;

                for (int row : rows) {
                    int currId = col + row * matrix[0].length;

                    if (prevId != -1) {
                        union(prevId, currId);
                    }
                    prevId = currId;
                }
            }
        }
        Map<Integer, List<Integer>> parentId = new HashMap<>();
        //Group the points according to their parent
        for (int r = 0; r < matrix.length; r++) {

            for (int c = 0; c < matrix[0].length; c++) {
                int currId = c + r * matrix[0].length;
                int pcurr = find(currId);
                List<Integer> ids = parentId.getOrDefault(pcurr, new ArrayList<>());
                ids.add(currId);
                parentId.put(pcurr, ids);
            }
        }
        int[][] ans = new int[matrix.length][matrix[0].length];
        int[][] v = new int[matrix.length][matrix[0].length];
        int[] maxRankRows = new int[matrix.length];
        int[] maxRankCols = new int[matrix[0].length];
        // Each connected point will have the same rank.
        // Calculating and updating
        for (int val : valPoints.keySet()) {
            List<int[]> points = valPoints.get(val);

            for (int[] point : points) {
                int r = point[0];
                int c = point[1];

                if (v[r][c] == 1) {
                    continue;
                }
                int idx = c + r * matrix[0].length;
                int currParent = find(idx);
                List<Integer> groupIds = parentId.get(currParent);
                int rank = 1;

                for (int id : groupIds) {
                    int row = id / matrix[0].length;
                    int col = id % matrix[0].length;
                    rank = Math.max(rank, Math.max(maxRankRows[row], maxRankCols[col]) + 1);
                }

                for (int id : groupIds) {
                    int row = id / matrix[0].length;
                    int col = id % matrix[0].length;
                    ans[row][col] = rank;
                    maxRankRows[row] = Math.max(maxRankRows[row], rank);
                    maxRankCols[col] = Math.max(maxRankCols[col], rank);
                    v[row][col] = 1;
                }
            }
        }
        return ans;
    }
}
