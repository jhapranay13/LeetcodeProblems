package leetcode.ForBiginners.Graph.UnionFind;

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
 *         Given an array equations of strings that represent relationships
 *         between variables, each string equations[i] has length 4 and takes
 *         one of two different forms: "a==b" or "a!=b". Here, a and b are
 *         lowercase letters (not necessarily different) that represent
 *         one-letter variable names.
 * 
 *         Return true if and only if it is possible to assign integers to
 *         variable names so as to satisfy all the given equations.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: ["a==b","b!=a"] Output: false Explanation: If we assign say, a
 *         = 1 and b = 1, then the first equation is satisfied, but not the
 *         second. There is no way to assign the variables to satisfy both
 *         equations.
 * 
 *         Example 2:
 * 
 *         Input: ["b==a","a==b"] Output: true Explanation: We could assign a =
 *         1 and b = 1 to satisfy both equations.
 * 
 *         Example 3:
 * 
 *         Input: ["a==b","b==c","a==c"] Output: true
 * 
 *         Example 4:
 * 
 *         Input: ["a==b","b!=c","c==a"] Output: false
 * 
 *         Example 5:
 * 
 *         Input: ["c==c","b==d","x!=z"] Output: true
 * 
 * 
 *         Note:
 * 
 *         1 <= equations.length <= 500 equations[i].length == 4 equations[i][0]
 *         and equations[i][3] are lowercase letters equations[i][1] is either
 *         '=' or '!' equations[i][2] is '='
 *
 */

public class _990_SatisfiabilityOfEqualityEquation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// DFS Solution
	public boolean equationsPossible(String[] equations) {
		Map<Character, List<Character>> equalityMap = new HashMap<>();

		for (String equation : equations) {

			if (equation.charAt(1) == '=') {
				char a = equation.charAt(0);
				char b = equation.charAt(3);
				List<Character> list = equalityMap.getOrDefault(a, new ArrayList<>());
				list.add(b);
				equalityMap.put(a, list);
				list = equalityMap.getOrDefault(b, new ArrayList<>());
				list.add(a);
				equalityMap.put(b, list);
			}
		}

		for (String equation : equations) {

			if (equation.charAt(1) == '!') {
				Set<Character> visited = new HashSet<>();

				if (dfs(equation.charAt(0), equation.charAt(3), equalityMap, visited)) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean dfs(Character var1, Character var2, Map<Character, List<Character>> equalityMap,
			Set<Character> visited) {
		if (visited.contains(var1)) {
			return false;
		}

		if (var1 == var2) {
			return true;
		}
		visited.add(var1);
		List<Character> adj = equalityMap.getOrDefault(var1, new ArrayList<>());

		for (char child : adj) {
			if (dfs(child, var2, equalityMap, visited)) {
				return true;
			}
		}
		return false;
	}

	// ============================================================================
	// union find solution
	class UnionFind {
		int[] parent;

		public UnionFind(int size) {
			this.parent = new int[size];

			for (int i = 0; i < size; i++) {
				this.parent[i] = i;
			}
		}

		public int find(int node) {
			if (parent[node] != node) {
				return find(parent[node]);
			}
			return node;
		}

		public boolean union(int a, int b) {
			int parentA = find(a);
			int parentB = find(b);

			if (parentA != parentB) {
				parent[parentB] = parentA;
				return true;
			}
			return false;
		}
	}

	public boolean equationsPossible1(String[] equations) {
		UnionFind uf = new UnionFind(26);

		for (String equation : equations) {

			if (equation.charAt(1) == '=') {
				uf.union(equation.charAt(0) - 'a', equation.charAt(3) - 'a');
			}
		}

		for (String equation : equations) {

			if (equation.charAt(1) == '!') {
				int ch1 = equation.charAt(0) - 'a';
				int ch2 = equation.charAt(3) - 'a';
				int pch1 = uf.find(ch1);
				int pch2 = uf.find(ch2);

				if (pch1 == pch2) {
					return false;
				}
			}
		}
		return true;
	}
}
