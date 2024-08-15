package leetcode.Graph;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * There are n couples sitting in 2n seats arranged in a row and want to hold hands.
 *
 * The people and seats are represented by an integer array row where row[i] is the ID of the person sitting in the ith seat. The couples are numbered in order, the first couple being (0, 1), the second couple being (2, 3), and so on with the last couple being (2n - 2, 2n - 1).
 *
 * Return the minimum number of swaps so that every couple is sitting side by side. A swap consists of choosing any two people, then they stand up and switch seats.
 *
 *
 *
 * Example 1:
 *
 * Input: row = [0,2,1,3]
 * Output: 1
 * Explanation: We only need to swap the second (row[1]) and third (row[2]) person.
 * Example 2:
 *
 * Input: row = [3,2,0,1]
 * Output: 0
 * Explanation: All couples are already seated side by side.
 *
 *
 * Constraints:
 *
 * 2n == row.length
 * 2 <= n <= 30
 * n is even.
 * 0 <= row[i] < 2n
 * All the elements of row are unique.
 *
 */

public class _765_Couples_Holding_Hands {
    // Union Find Approach
    // The idea behind Union Find is that there are N / 2 couples so if the num / 2 and (num + 1) / 2 are same
    // Then the count doesnt decrease otherwise it decreases and total number of swaps will be total couple - count left
    class UnionFind {
        int[] parent;
        int count;

        public UnionFind(int n) {
            parent = new int[n];
            count = n;

            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

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
                count--;
            }
        }
    }

    public int minSwapsCouples(int[] row) {
        int totalNumberOfCouples = row.length / 2;
        UnionFind uf = new UnionFind(totalNumberOfCouples);

        for (int i = 0; i < row.length; i += 2) {
            int first = row[i];
            int second = row[i + 1];
            //0 and 1 will be 0th couple 2 and 3 will be 1st couple and so on so it is i / 2 th couple
            uf.union(first / 2, second / 2);
        }
        // Number of couple - wrong union is number of swap
        return totalNumberOfCouples - uf.count;
    }
    //=============================================================================================
    // HashMap Approach
    public int minSwapsCouples1(int[] row) {
        Map<Integer, Integer> coupleHolder = new HashMap<>();
        // Setting up current neighbours
        for (int i = 0; i < row.length; i += 2) {
            coupleHolder.put(row[i], row[i + 1]);
            coupleHolder.put(row[i + 1], row[i]);
        }
        int ans = 0;

        for (int i = 0; i < row.length; i += 2) {

            if (coupleHolder.get(i) != i + 1) {
                //swapping
                int neighbour1 = coupleHolder.get(i);
                int neighbour2 = coupleHolder.get(i + 1);
                coupleHolder.put(neighbour1, neighbour2);
                coupleHolder.put(neighbour2, neighbour1);
                ans++;
                //both are now together
                coupleHolder.remove(i);
                coupleHolder.remove(i + 1);
            }
        }
        return ans;
    }
}
