package leetcode.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 *
 * There is a tree (i.e., a connected, undirected graph with no cycles) structure country network consisting of n cities numbered from 0 to n - 1 and exactly n - 1 roads. The capital city is city 0. You are given a 2D integer array roads where roads[i] = [ai, bi] denotes that there exists a bidirectional road connecting cities ai and bi.
 *
 * There is a meeting for the representatives of each city. The meeting is in the capital city.
 *
 * There is a car in each city. You are given an integer seats that indicates the number of seats in each car.
 *
 * A representative can use the car in their city to travel or change the car and ride with another representative. The cost of traveling between two cities is one liter of fuel.
 *
 * Return the minimum number of liters of fuel to reach the capital city.
 *
 *
 *
 * Example 1:
 *                      2
 *                      |
 *                      |
 *                      0
 *                    /   \
 *                  /      \
 *                 1        3
 *
 * Input: roads = [[0,1],[0,2],[0,3]], seats = 5
 * Output: 3
 * Explanation:
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative2 goes directly to the capital with 1 liter of fuel.
 * - Representative3 goes directly to the capital with 1 liter of fuel.
 * It costs 3 liters of fuel at minimum.
 * It can be proven that 3 is the minimum number of liters of fuel needed.
 * Example 2:
 *
 *                              3
 *                            /   \
 *                          /      \
 *                         1        2
 *                          \
 *                           \
 *                            0
 *                          /   \
 *                        /      \
 *                       4        5
 *                     /
 *                   /
 *                  6
 *
 * Input: roads = [[3,1],[3,2],[1,0],[0,4],[0,5],[4,6]], seats = 2
 * Output: 7
 * Explanation:
 * - Representative2 goes directly to city 3 with 1 liter of fuel.
 * - Representative2 and representative3 go together to city 1 with 1 liter of fuel.
 * - Representative2 and representative3 go together to the capital with 1 liter of fuel.
 * - Representative1 goes directly to the capital with 1 liter of fuel.
 * - Representative5 goes directly to the capital with 1 liter of fuel.
 * - Representative6 goes directly to city 4 with 1 liter of fuel.
 * - Representative4 and representative6 go together to the capital with 1 liter of fuel.
 * It costs 7 liters of fuel at minimum.
 * It can be proven that 7 is the minimum number of liters of fuel needed.
 * Example 3:
 *
 *
 * Input: roads = [], seats = 1
 * Output: 0
 * Explanation: No representatives need to travel to the capital city.
 *
 *
 * Constraints:
 *
 * 1 <= n <= 105
 * roads.length == n - 1
 * roads[i].length == 2
 * 0 <= ai, bi < n
 * ai != bi
 * roads represents a valid tree.
 * 1 <= seats <= 105
 *
 *
 */

public class _2477_Minimum_Fuel_Cost_to_Report_to_the_Capital {
    public long minimumFuelCost(int[][] roads, int seats) {
        Map<Integer, List<Integer>> adjacency = new HashMap<>();

        for (int[] road : roads) {
            int node1 = road[0];
            int node2 = road[1];
            List<Integer> list = adjacency.getOrDefault(node1, new ArrayList<>());
            list.add(node2);
            adjacency.put(node1, list);
            list = adjacency.getOrDefault(node2, new ArrayList<>());
            list.add(node1);
            adjacency.put(node2, list);
        }
        seat = seats;
        dfs(adjacency, 0, -1);
        return fuel;
    }
    private int seat = 1;
    long fuel = 0;


    private int dfs(Map<Integer, List<Integer>> adjacency, int node, int parent) {
        List<Integer> list = adjacency.getOrDefault(node, new ArrayList<>());
        int numberOfPerson = 1;

        for (int child : list) {

            if (child != parent) {
                numberOfPerson += dfs(adjacency, child, node);
            }
        }

        if (node != 0) {
            fuel += Math.ceil((double) numberOfPerson / seat);
        }
        return numberOfPerson;
    }
}
