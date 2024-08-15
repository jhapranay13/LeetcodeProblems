package leetcode.medium;

import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class _752_OpenTheLock {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//==============================================================================
	//Similar to World ladder and ShortestPath
	public int openLock(String[] deadends, String target) {
		Set<String> deadEndSet = new HashSet<>();

		for (String deadend : deadends) {

			if (deadend.equals("0000")) {
				return -1;
			}
			deadEndSet.add(deadend);
		}

		Deque<String> queue = new LinkedList<>();
		Set<String> visited = new HashSet<>();
		queue.offer("0000");
		int counter = 0;
		boolean found = false;

		outer:
			while (!queue.isEmpty()) {
				int size = queue.size();

				while (size-- > 0) {
					String combination = queue.poll();

					if (visited.contains(combination)) {
						continue;
					}
					visited.add(combination);

					if (combination.equals(target)) {
						found = true;
						break outer;
					}

					for (int i = 0; i < combination.length(); i++) {
						String left = combination.substring(0, i);
						char ch = combination.charAt(i);
						String right = combination.substring(i + 1);
						int oneAwayNum = ch - '0';
						int oneAwayCombo1 = oneAwayNum == 9 ? 0 : oneAwayNum + 1;
						int oneAwayCombo2 = oneAwayNum == 0 ? 9 : oneAwayNum - 1;
						String nextCombo1 = left + oneAwayCombo1 + right;
						String nextCombo2 = left + oneAwayCombo2 + right;

						if (!visited.contains(nextCombo1) && 
								!deadEndSet.contains(nextCombo1)) {
							queue.offer(nextCombo1);
						}

						if (!visited.contains(nextCombo2) &&
								!deadEndSet.contains(nextCombo2)) {
							queue.offer(nextCombo2);
						}
					}
				}
				counter++;
			}
		return found ? counter : -1;
	}
}
