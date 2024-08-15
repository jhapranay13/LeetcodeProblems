package leetcode.medium;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1. You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first if you want to take course ai.

For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
Return the ordering of courses you should take to finish all courses. If there are many valid answers, return any of them. If it is impossible to finish all courses, return an empty array.

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]

Constraints:

1 <= numCourses <= 2000
0 <= prerequisites.length <= numCourses * (numCourses - 1)
prerequisites[i].length == 2
0 <= ai, bi < numCourses
ai != bi
All the pairs [ai, bi] are distinct.
 *
 */

public class _210_CouseSchedule2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//================================================================================
	//DFS approach Topological Sort
	public int[] findOrder(int numCourses, int[][] prerequisites) {
		int[] ans = new int[numCourses];
		Map<Integer, List<Integer>> adjacency = new HashMap<>();

		for (int[] pre : prerequisites) {
			int parent = pre[1];
			int child = pre[0];
			List<Integer> children = adjacency.getOrDefault(parent, new ArrayList<>());
			children.add(child);
			adjacency.put(parent, children);
		}
		Set<Integer> visited = new HashSet<>();
		Deque<Integer> stack = new LinkedList<>();

		for (int i = 0; i < numCourses; i++) {
			Set<Integer> cycle = new HashSet<>();

			if (!visited.contains(i)) {

				if (!dfs(visited, cycle, stack, adjacency, i)) {
					return new int[0];
				}
			}
		}

		if (!stack.isEmpty() && stack.size() == numCourses) {
			int index = 0;

			while (index < numCourses) {
				ans[index++] = stack.pop(); 
			}
		}
		return ans;
	}

	private boolean dfs(Set<Integer> visited, Set<Integer> cycle, Deque<Integer> stack,
			Map<Integer, List<Integer>> adjacency, int course) {
		if (cycle.contains(course)) {
			return false;
		}
		visited.add(course);
		cycle.add(course);
		List<Integer> children = adjacency.getOrDefault(course, new ArrayList<>());

		for (int child : children) {

			if (cycle.contains(child)) {
				return false;
			}

			if (!visited.contains(child) && 
					!dfs(visited, cycle, stack, adjacency, child)) {
				return false;
			}
		}
		cycle.remove(course);
		stack.push(course);
		return true;
	}
	//============================================================================
	//BFS Kahn's Algorithm Topological Sort
	public int[] findOrder1(int numCourses, int[][] prerequisites) {
		int[] ans = new int[numCourses];
		Map<Integer, List<Integer>> adjacency = new HashMap<>();
		int[] incoming = new int[numCourses];

		for (int[] pre : prerequisites) {
			int parent = pre[1];
			int child = pre[0];
			List<Integer> children = adjacency.getOrDefault(parent, new ArrayList<>());
			children.add(child);
			adjacency.put(parent, children);
			incoming[child]++;
		}
		Deque<Integer> queue = new LinkedList<>();
		int count = 0;

		for (int i = 0; i < numCourses; i++) {

			if (incoming[i] == 0) {
				queue.offer(i);
			}
		}
		List<Integer> holder = new ArrayList<>();

		while (!queue.isEmpty()) {
			int parent = queue.poll();
			List<Integer> children = adjacency.getOrDefault(parent, new ArrayList<>());

			for (int child : children) {
				incoming[child]--;

				if (incoming[child] == 0) {
					queue.add(child);
				}
			}
			holder.add(parent);
			count++;
		}

		if (count != numCourses) {
			return new int[0];
		}
		int index = 0;

		for (int node : holder) {
			ans[index++] = node;
		}
		return ans;
	}
}
