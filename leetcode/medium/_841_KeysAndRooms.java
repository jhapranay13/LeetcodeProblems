package leetcode.medium;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author Pranay Jha
 *
 *         There are N rooms and you start in room 0. Each room has a distinct
 *         number in 0, 1, 2, ..., N-1, and each room may have some keys to
 *         access the next room.
 * 
 *         Formally, each room i has a list of keys rooms[i], and each key
 *         rooms[i][j] is an integer in [0, 1, ..., N-1] where N = rooms.length.
 *         A key rooms[i][j] = v opens the room with number v.
 * 
 *         Initially, all the rooms start locked (except for room 0).
 * 
 *         You can walk back and forth between rooms freely.
 * 
 *         Return true if and only if you can enter every room.
 * 
 *         Example 1:
 * 
 *         Input: [[1],[2],[3],[]] Output: true Explanation: We start in room 0,
 *         and pick up key 1. We then go to room 1, and pick up key 2. We then
 *         go to room 2, and pick up key 3. We then go to room 3. Since we were
 *         able to go to every room, we return true.
 * 
 *         Example 2:
 * 
 *         Input: [[1,3],[3,0,1],[2],[0]] Output: false Explanation: We can't
 *         enter the room with number 2. Note:
 * 
 *         1 <= rooms.length <= 1000 0 <= rooms[i].length <= 1000 The number of
 *         keys in all rooms combined is at most 3000.
 *
 */

public class _841_KeysAndRooms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean canVisitAllRooms(List<List<Integer>> rooms) {
		boolean[] visited = new boolean[rooms.size()];
		dfs(rooms, visited, 0);

		for (boolean visit : visited) {

			if (!visit) {
				return visit;
			}
		}
		return true;
	}

	private void dfs(List<List<Integer>> rooms, boolean[] visited, int index) {
		if (index == rooms.size() || visited[index]) {
			return;
		}
		List<Integer> child = rooms.get(index);
		visited[index] = true;

		for (int room : child) {
			dfs(rooms, visited, room);
		}
	}
	//=============================================================================================
	//Slightly different approach
	public boolean canVisitAllRooms1(List<List<Integer>> rooms) {
		Set<Integer> v = new HashSet<>();
		recur(rooms, v, 0);
		return v.size() == rooms.size();
	}

	private void recur(List<List<Integer>> rooms, Set<Integer> v, int index) {

		if (!v.add(index)) {
			return;
		}
		List<Integer> keys = rooms.get(index);

		for (int key : keys) {
			recur(rooms, v, key);
		}
	}
}
