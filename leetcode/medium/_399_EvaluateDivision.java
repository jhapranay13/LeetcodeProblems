package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         You are given an array of variable pairs equations and an array of
 *         real numbers values, where equations[i] = [Ai, Bi] and values[i]
 *         represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string
 *         that represents a single variable.
 * 
 *         You are also given some queries, where queries[j] = [Cj, Dj]
 *         represents the jth query where you must find the answer for Cj / Dj =
 *         ?.
 * 
 *         Return the answers to all queries. If a single answer cannot be
 *         determined, return -1.0.
 * 
 *         Note: The input is always valid. You may assume that evaluating the
 *         queries will not result in division by zero and that there is no
 *         contradiction.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: equations = [["a","b"],["b","c"]], values = [2.0,3.0], queries
 *         = [["a","c"],["b","a"],["a","e"],["a","a"],["x","x"]] Output:
 *         [6.00000,0.50000,-1.00000,1.00000,-1.00000] Explanation: Given: a / b
 *         = 2.0, b / c = 3.0 queries are: a / c = ?, b / a = ?, a / e = ?, a /
 *         a = ?, x / x = ? return: [6.0, 0.5, -1.0, 1.0, -1.0 ] 
 *         
 *         Example 2:
 * 
 *         Input: equations = [["a","b"],["b","c"],["bc","cd"]], values =
 *         [1.5,2.5,5.0], queries =
 *         [["a","c"],["c","b"],["bc","cd"],["cd","bc"]] Output:
 *         [3.75000,0.40000,5.00000,0.20000] 
 *         
 *         Example 3:
 * 
 *         Input: equations = [["a","b"]], values = [0.5], queries =
 *         [["a","b"],["b","a"],["a","c"],["x","y"]] Output:
 *         [0.50000,2.00000,-1.00000,-1.00000]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= equations.length <= 20 equations[i].length == 2 1 <= Ai.length,
 *         Bi.length <= 5 values.length == equations.length 0.0 < values[i] <=
 *         20.0 1 <= queries.length <= 20 queries[i].length == 2 1 <= Cj.length,
 *         Dj.length <= 5 Ai, Bi, Cj, Dj consist of lower case English letters
 *         and digits.
 *
 */

public class _399_EvaluateDivision {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, List<String[]>> adjacency = new HashMap<>();
		initAdjacency(equations, values, adjacency);
		double[] ans = new double[queries.size()];
		int index = 0;

		for (List<String> query : queries) {
			String op1 = query.get(0);
			String op2 = query.get(1);
			Set<String> visited = new HashSet<>();
			ans[index++] = dfs(op1, op2, adjacency, visited);
		}
		return ans;
	}

	private double dfs(String op1, String op2, Map<String, List<String[]>> adjacency, Set<String> visited) {

		if (visited.contains(op1) || !adjacency.containsKey(op1) || !adjacency.containsKey(op1)) {
			return -1;
		}

		if (op1.equals(op2)) {
			return 1;
		}
		double val = 0.0;
		visited.add(op1);
		List<String[]> children = adjacency.get(op1);

		for (String[] childVal : children) {
			String child = childVal[0];
			double childValue = Double.parseDouble(childVal[1]);
			val = dfs(child, op2, adjacency, visited);

			if (val != -1) {
				val *= childValue;
				break;
			}
		}
		return val;
	}

	private void initAdjacency(List<List<String>> equations, double[] values, Map<String, List<String[]>> adjacency) {
		for (int i = 0; i < equations.size(); i++) {
			List<String> equation = equations.get(i);
			double value = values[i];
			List<String[]> child1 = adjacency.getOrDefault(equation.get(0), new ArrayList<>());
			List<String[]> child2 = adjacency.getOrDefault(equation.get(1), new ArrayList<>());
			child1.add(new String[] { equation.get(1), Double.toString(value) });
			child2.add(new String[] { equation.get(0), Double.toString(1 / value) });
			adjacency.put(equation.get(0), child1);
			adjacency.put(equation.get(1), child2);
		}
	}

}
