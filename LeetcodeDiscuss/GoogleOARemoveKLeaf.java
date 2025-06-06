package LeetcodeDiscuss;

import java.util.*;

/**
 *
 * You are given a tree consisting of vertices
 *
 * You can perform the following operation at most times: delete a single leaf of the tree (the operation can produce new leafs, that can be deleted later). The resulting tree must have as small diameter as possible. Find the minimum possible diameter.
 *
 * Input Format
 * The first line contains two space separated integers(Number Of nodes and leafs to be removed) and . Each of the next lines contains two space separated integers , describing the current tree edge. It's guaranteed that the given graph is a tree.
 *
 * Constraints
 *
 * 0<n<=1e5
 * 0<k<n
 *
 * Sample Input #00
 *
 * 4 0
 * 1 2
 * 2 3
 * 4 3
 *
 * Sample Output #00
 * 3
 *
 * Sample Input #01
 *
 * 4 1
 * 2 3
 * 4 3
 * 1 4
 *
 * Sample Output #01
 * 2
 *
 *
 * 5
 *
 *
 * 6
 *
 */
public class GoogleOARemoveKLeaf {

   public static void main(String args[]) {
        int k = 0;
        int[][] edge = {{1, 2}, {2, 3}, {4, 3}};
        int numberOfNodes = 4;

        /*int k = 1;
        int[][] edge = {{2, 3}, {4, 3}, {1, 4}};
        int numberOfNodes = 4;*/

        /*int k = 2;
        int[][] edge = {{5, 1}, {1, 4}, {5, 2}, {1, 3}, {4, 6}};
        int numberOfNodes = 6;*/
        int diameter = findDiameter(numberOfNodes, edge, k);
        System.out.println(diameter);
   }

    private static int findDiameter(int numberOfNodes, int[][] edge, int k) {
        int[] degree = getDegree(edge, numberOfNodes);
        Map<Integer, List<Integer>> adjacencyList = getAdjacencyList(edge);
        int rootNode = getRootNode(degree, adjacencyList, numberOfNodes);
        Map<Integer, Integer> nodeDepthMap = getNodeDepthMap(rootNode, adjacencyList);
        degree = getDegree(edge, numberOfNodes);
        int ans = removeKLeafs(degree, adjacencyList, nodeDepthMap, k, rootNode);
        return ans;
    }

    private static int removeKLeafs(int[] degree, Map<Integer, List<Integer>> adjacencyList,
                                     Map<Integer, Integer> nodeDepthMap, int k, int rootNode) {

        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) ->
                nodeDepthMap.get(b) - nodeDepthMap.get(a));
        List<Integer> removedLeafs = new ArrayList<>();
        int []degreeCpy = degree.clone();

        // Adding all the leaves to the queue
        for (int i = 1; i < degree.length; i++) {
            if (degree[i] == 1) {
                pq.offer(i);
            }
        }

        while (!pq.isEmpty() && k > 0) {
            int leaf = pq.poll();
            degree[leaf] = 0; // Marking the leaf as removed

            for (int neighbor : adjacencyList.get(leaf)) {

                if (degree[neighbor] > 0) {
                    degree[neighbor]--;

                    if (degree[neighbor] == 1) {
                        pq.offer(neighbor);
                    }
                }
            }
            adjacencyList.remove(leaf); // Remove the leaf from the adjacency list
            k--;
        }
        int[] maxDistanceNode = getMaxDistanceNode(adjacencyList, rootNode, degree);
        int[] diameter = getMaxDistanceNode(adjacencyList, maxDistanceNode[0], degree); // The diameter is twice the distance to the farthest node
        return diameter[1];
   }

    private static int[] getMaxDistanceNode(Map<Integer, List<Integer>> adjacencyList, int rootNode, int[] degree) {
        boolean[] visited = new boolean[degree.length + 1];
        Queue<Integer> q = new LinkedList<>();
        q.offer(rootNode);
        visited[rootNode] = true;
        int farthest = rootNode;
        int[] distance = new int[degree.length + 1];;

        while (!q.isEmpty()) {
            int curr = q.poll();
            int currDistance = distance[curr];

            for (int neighbor : adjacencyList.get(curr)) {

                if (!visited[neighbor] && degree[neighbor] > 0) {
                    visited[neighbor] = true;
                    distance[neighbor] = currDistance + 1;
                    q.offer(neighbor);
                    farthest = neighbor;
                }
            }
        }

        return new int[] {farthest, distance[farthest]};
    }

    private static Map<Integer, Integer> getNodeDepthMap(int rootNode, Map<Integer, List<Integer>> adjacencyList) {
        Map<Integer, Integer> nodeDepthMap = new HashMap<>();
        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(rootNode);
        nodeDepthMap.put(rootNode, 0);

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            int currentDepth = nodeDepthMap.get(currentNode);

            for (int neighbor : adjacencyList.get(currentNode)) {

                if (!nodeDepthMap.containsKey(neighbor)) {
                    nodeDepthMap.put(neighbor, currentDepth + 1);
                    queue.add(neighbor);
                }
            }
        }
        return nodeDepthMap;
    }

    private static Map<Integer, List<Integer>> getAdjacencyList(int[][] edge) {
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

        for (int[] e : edge) {
            adjacencyList.putIfAbsent(e[0], new ArrayList<>());
            adjacencyList.putIfAbsent(e[1], new ArrayList<>());
            adjacencyList.get(e[0]).add(e[1]);
            adjacencyList.get(e[1]).add(e[0]);
        }
        return adjacencyList;
    }

    private static int[] getDegree(int[][] edge, int numberOfNodes) {
        int[] degree = new int[numberOfNodes + 1];

        for (int[] e : edge) {
            degree[e[0]]++;
            degree[e[1]]++;
        }
        return degree;
    }

    private static int getRootNode(int[] degree, Map<Integer, List<Integer>> adjacencyList, int numberOfNodes) {
        Deque<Integer> queue = new ArrayDeque<>();
        List<Integer> rootCandidate = new ArrayList<>();

        // Adding all the leaves to the queue
        for (int i = 1; i <= numberOfNodes; i++) {
            if (degree[i] == 1) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int size = queue.size();

            while(size-- > 0) {
                int node = queue.poll();
                rootCandidate.add(node);
                degree[node] = 0; // Marking the node as processed and removed
                List<Integer> adjacents = adjacencyList.get(node);

                for (int adjacent : adjacents) {

                    if (degree[adjacent] > 0) {
                        degree[adjacent]--;

                        if (degree[adjacent] == 1) {
                            queue.add(adjacent);
                        }
                    }
                }
            }

            if (!queue.isEmpty()) {
                rootCandidate = new ArrayList<>();
            }
        }
        int rootNode = -1;

        for (int candidate : rootCandidate) {
            rootNode = candidate;
            break;
        }
        return rootNode;
    }
}
