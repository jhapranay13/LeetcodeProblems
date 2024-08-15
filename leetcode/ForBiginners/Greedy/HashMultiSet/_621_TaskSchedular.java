package leetcode.ForBiginners.Greedy.HashMultiSet;

import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * 
 * @author Pranay Jha
 *
 *         Given a characters array tasks, representing the tasks a CPU needs to
 *         do, where each letter represents a different task. Tasks could be
 *         done in any order. Each task is done in one unit of time. For each
 *         unit of time, the CPU could complete either one task or just be idle.
 * 
 *         However, there is a non-negative integer n that represents the
 *         cooldown period between two same tasks (the same letter in the
 *         array), that is that there must be at least n units of time between
 *         any two same tasks.
 * 
 *         Return the least number of units of times that the CPU will take to
 *         finish all the given tasks.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8
 *         Explanation: A -> B -> idle -> A -> B -> idle -> A -> B There is at
 *         least 2 units of time between any two same tasks. 
 *         
 *         Example 2:
 * 
 *         Input: tasks = ["A","A","A","B","B","B"], n = 0 Output: 6
 *         Explanation: On this case any permutation of size 6 would work since
 *         n = 0. ["A","A","A","B","B","B"] ["A","B","A","B","A","B"]
 *         ["B","B","B","A","A","A"] ... And so on. 
 *         
 *         Example 3:
 * 
 *         Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n =
 *         2 Output: 16 Explanation: One possible solution is A -> B -> C -> A
 *         -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle ->
 *         A
 * 
 * 
 *         Constraints:
 * 
 *         1 <= task.length <= 104 tasks[i] is upper-case English letter. The
 *         integer n is in the range [0, 100].
 *
 */

public class _621_TaskSchedular {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	public int leastInterval(char[] tasks, int n) {

		if (n == 0) {
			return tasks.length;
		}
		Map<Character, Integer> taskFreqMap = new HashMap<>();

		for (char task : tasks) {
			taskFreqMap.put(task, taskFreqMap.getOrDefault(task, 0) + 1);
		}
		PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
			public int compare(Character ch1, Character ch2) {

				if (taskFreqMap.get(ch2) == taskFreqMap.get(ch1)) {
					return ch1.compareTo(ch2);
				}
				return taskFreqMap.get(ch2) - taskFreqMap.get(ch1);
			}
		});

		for (char task : taskFreqMap.keySet()) {
			pq.offer(task);
		}
		Deque<Character> cache = new LinkedList<>();
		int ans = 0;

		while (!pq.isEmpty()) {
			int count = n + 1;

			while (!pq.isEmpty() && count > 0) {
				char task = pq.poll();
				int taskCount = taskFreqMap.get(task);

				if (taskCount == 1) {
					taskFreqMap.remove(task);
				} else {
					taskFreqMap.put(task, taskCount - 1);
					cache.add(task);
				}
				count--;
				ans++;
			}

			if (!cache.isEmpty()) {
				ans += count;
			}

			while (!cache.isEmpty()) {
				pq.offer(cache.poll());
			}
		}
		return ans;
	}
	//============================================================================
	//Another version
	public int leastInterval1(char[] tasks, int n) {

		if( n == 0 ) {
			return tasks.length;
		}
		Map< Character, Integer > freq = new HashMap<>();

		for( char t : tasks ) {
			freq.put( t, freq.getOrDefault( t, 0 ) + 1 );
		}
		PriorityQueue< Character > q = new PriorityQueue<>( new Comparator< Character >() {

			public int compare( Character x, Character y ) {

				if( freq.get( x ) == freq.get( y ) ) {
					return x.compareTo( y );
				}
				return freq.get( y ) - freq.get( x );
			}
		} );

		for( char ch : freq.keySet() ) {
			q.offer( ch );
		}
		Deque< Character > cache = new LinkedList<>();
		int count = n + 1;
		int gap = 0;

		while( !q.isEmpty() ) {
			char ch = q.poll();

			if( freq.containsKey( ch ) ) {
				int f = freq.get( ch );

				if( f == 1 ) {
					freq.remove( ch );
				} else {
					freq.put( ch, --f );
					cache.offer( ch );
				}
				count--;
				gap++;
			}

			if( q.isEmpty() || count == 0 ) {

				if( !cache.isEmpty() ) {
					gap += count;
				}

				while( !cache.isEmpty() ) {
					q.offer( cache.poll() );
				} 
				count = n + 1;
			}
		}
		return gap;
	}
}
