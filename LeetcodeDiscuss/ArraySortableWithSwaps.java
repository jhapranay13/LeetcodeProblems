package LeetcodeDiscuss;

import java.util.*;

/**
 *
 *
 * There is an array a of size n and an integer k. The task is to find whether the array can be sorted using the following operations as many times as possible.
 *
 * Operation: Take two indices i, j of the array, say i, j (0 <= i,j < n). We can swap a[i] and a[j] if and only if abs(a[i] − a[j]) <= k
 *
 * Here, abs denotes the absolute value function.
 *
 * NOTE: 0-based indexing
 *
 * Output:
 * Print a single line containing either "YES" or "NO"
 *
 */

public class ArraySortableWithSwaps {

    public static void main(String[] args) {
        /*int[] arr = {1, 5, 3, 4, 2};
        int k = 0;*/

        /*int[] arr = {1, 2, 3, 4, 5};
        int k = 1;
        // Expected: YES*/

        /*int[] arr = {7, 7, 7, 7};
        int k = 0;*/

        /*int[] arr = {40, 30, 20, 10, 1};
        int k = 5;
        // Expected: NO*/

        /*int[] arr = {5, 4, 3, 2, 1};
        int k = 1;
        // Expected: YES . NO for K = 0*/

        /*int[] arr = {10, 12, 11, 13, 9, 8};
        int k = 5;
        // Expected: YES*/

        int[] arr = {4, 2, 4, 2, 1};
        int k = 2;
        // Expected: YES
        System.out.println(isSortable(arr, k));
    }

    public static class UnionFind {
        int[] parent;

        public  UnionFind(int size) {
            this.parent = new int[size];

            for (int i = 0; i < size; i++) {
                this.parent[i] = i;
            }
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {
                parent[rootY] = rootX;
            }
        }

        private int find(int y) {

            if (parent[y] != y) {
                parent[y] = find(parent[y]);
            }
            return parent[y];
        }
    }

    private static String isSortable(int[] arr, int k) {
        int[] sortedArr = arr.clone();
        Arrays.sort(sortedArr);
        Map<Integer, List<Integer>> valIndexMap = new HashMap<>();

        for (int i =0; i < arr.length; i++) {
            if (!valIndexMap.containsKey(arr[i])) {
                valIndexMap.put(arr[i], new ArrayList<>());
            }
            valIndexMap.get(arr[i]).add(i);
        }
        UnionFind uf = new UnionFind(arr.length);

        for (Integer key : valIndexMap.keySet()) {
            List<Integer> indices = valIndexMap.get(key);

            if (indices.size() > 1) {

                for (int i = 1; i < indices.size(); i++) {
                    uf.union(indices.get(i), indices.get(i - 1));
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {

            for (int j = i + 1; j < arr.length; j++) {

                if (sortedArr[i] == sortedArr[j]) {
                    continue;
                }

                if (Math.abs(sortedArr[i] - sortedArr[j]) <= k) {
                    int indexI = valIndexMap.get(sortedArr[i]).get(0);
                    int indexJ = valIndexMap.get(sortedArr[j]).get(0);
                    uf.union(indexI, indexJ);
                }
            }
        }

        for (int i = 0; i < arr.length; i++) {
            if (uf.find(i) != uf.find(valIndexMap.get(sortedArr[i]).get(0))) {
                return "NO";
            }
        }
        return "YES";
    }
}
