package leetcode.ForBiginners.Graph.TopologicalSort;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class _207_CourseSchedule {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//=============================================================================
	//Toplogical Sort DFS
	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
		createAdjacencyList(adjacencyList, prerequisites);
		Set<Integer> visited = new HashSet<>();

		for (int i = 0; i < numCourses; i++) {
			Set<Integer> cycle = new HashSet<>();

			if (!visited.contains(i)) {

				if( !recur(adjacencyList, cycle, visited, i) ) {
					return false;
				}
			}
		}
		return true;
	}

	private void createAdjacencyList(Map<Integer, List<Integer>> adjList, int[][] pres) {

		for (int[] pre : pres) {
			int parent = pre[1];
			int child = pre[0];
			List<Integer> childList = adjList.getOrDefault(parent, new ArrayList<>());
			childList.add(child);
			adjList.put(parent, childList);
		}
	}

	private boolean recur(Map<Integer, List<Integer>> adj, Set<Integer> cycle, 
			Set<Integer> visited, int course) {

		if (cycle.contains(course)) {
			return false;
		}
		visited.add(course);
		cycle.add(course);
		List<Integer> children = adj.get(course);
		boolean flag = true;

		if (children != null) {

			for (int child : children) {

				if (cycle.contains(child)) {
					return false;
				}

				if (visited.contains(child)) {
					continue;
				}
				flag = recur(adj, cycle, visited, child);

				if (!flag) {
					break;
				}
			}
		}
		cycle.remove(course);
		return flag;
	}
	//==============================================================================
	//Toplogical Sort BFS Kahn's Algorithm;
	public boolean canFinish1(int numCourses, int[][] prerequisites) {
		int[] incoming = new int[numCourses];
		Map<Integer, List<Integer>> adjacencyList = new HashMap<>();

		for (int[] pre : prerequisites) {
			int parent = pre[1];
			int child = pre[0];
			List<Integer> children = 
					adjacencyList.getOrDefault(parent, new ArrayList<>());
			children.add(child);
			adjacencyList.put(parent, children);
			incoming[child]++;
		}
		Deque<Integer> queue = new LinkedList<>();

		for (int i = 0; i < incoming.length; i++) {

			if (incoming[i] == 0) {
				queue.offer(i);
			}
		}
		int count = 0;

		while (!queue.isEmpty()) {
			List<Integer> children = adjacencyList.getOrDefault(queue.poll(), new ArrayList<>());

			for (int child : children) {
				incoming[child]--;

				if (incoming[child] == 0) {
					queue.offer(child);
				}
			}
			count++;
		}

		if(count == numCourses) {
			return true;
		}
		return false;
	}
}
