package leetcode.LinkedList;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Pranay Jha
 *
 *Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

Implement the LRUCache class:

LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
int get(int key) Return the value of the key if the key exists, otherwise return -1.
void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
The functions get and put must each run in O(1) average time complexity.



Example 1:

Input
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
Output
[null, null, null, 1, null, -1, null, -1, 3, 4]

Explanation
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // cache is {1=1}
lRUCache.put(2, 2); // cache is {1=1, 2=2}
lRUCache.get(1);    // return 1
lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
lRUCache.get(2);    // returns -1 (not found)
lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
lRUCache.get(1);    // return -1 (not found)
lRUCache.get(3);    // return 3
lRUCache.get(4);    // return 4


Constraints:

1 <= capacity <= 3000
0 <= key <= 104
0 <= value <= 105
At most 2 * 105 calls will be made to get and put.
 *
 */

/**
 * Your LRUCache object will be instantiated and called as such: LRUCache obj =
 * new LRUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
 */

public class _146_LRUCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Dll {
		Dll prev;
		Dll next;
		int key;
		int val;
	}

	class LRUCache {
		Map<Integer, Dll> cache;
		int size;
		Dll head;
		Dll tail;

		public LRUCache(int capacity) {
			this.cache = new HashMap<>();
			this.size = capacity;
			this.head = new Dll();
			this.tail = new Dll();
			head.next = tail;
			tail.prev = head;
		}

		public int get(int key) {
			int ans = -1;

			if (cache.containsKey(key)) {
				Dll node = cache.get(key);
				ans = node.val;
				remove(node);
				addFirst(node);
			}
			return ans;
		}

		public void put(int key, int value) {

			if (cache.containsKey(key)) {
				Dll node = cache.get(key);
				node.val = value;
				remove(node);
				addFirst(node);
			} else {
				Dll node = new Dll();
				node.val = value;
				node.key = key;
				addFirst(node);
				cache.put(key, node);

				if (size < cache.size()) {
					Dll lastNode = tail.prev;
					remove(lastNode);
					cache.remove(lastNode.key);
				}
			}
		}

		private void remove(Dll node) {
			Dll prev = node.prev;
			Dll next = node.next;
			prev.next = next;
			next.prev = prev;
		}

		private void addFirst(Dll node) {
			Dll next = head.next;
			head.next = node;
			node.next = next;
			next.prev = node;
			node.prev = head;
		}
	}
}
