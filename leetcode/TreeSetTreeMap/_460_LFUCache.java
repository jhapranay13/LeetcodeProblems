package leetcode.TreeSetTreeMap;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Pranay Jha
 *
 *         Design and implement a data structure for a Least Frequently Used
 *         (LFU) cache.
 * 
 *         Implement the LFUCache class:
 * 
 *         LFUCache(int capacity) Initializes the object with the capacity of
 *         the data structure. int get(int key) Gets the value of the key if the
 *         key exists in the cache. Otherwise, returns -1. void put(int key, int
 *         value) Update the value of the key if present, or inserts the key if
 *         not already present. When the cache reaches its capacity, it should
 *         invalidate and remove the least frequently used key before inserting
 *         a new item. For this problem, when there is a tie (i.e., two or more
 *         keys with the same frequency), the least recently used key would be
 *         invalidated. To determine the least frequently used key, a use
 *         counter is maintained for each key in the cache. The key with the
 *         smallest use counter is the least frequently used key.
 * 
 *         When a key is first inserted into the cache, its use counter is set
 *         to 1 (due to the put operation). The use counter for a key in the
 *         cache is incremented either a get or put operation is called on it.
 * 
 *         The functions get and put must each run in O(1) average time
 *         complexity.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input ["LFUCache", "put", "put", "get", "put", "get", "get", "put",
 *         "get", "get", "get"] [[2], [1, 1], [2, 2], [1], [3, 3], [2], [3], [4,
 *         4], [1], [3], [4]] Output [null, null, null, 1, null, -1, 3, null,
 *         -1, 3, 4]
 * 
 *         Explanation // cnt(x) = the use counter for key x // cache=[] will
 *         show the last used order for tiebreakers (leftmost element is most
 *         recent) LFUCache lfu = new LFUCache(2); lfu.put(1, 1); //
 *         cache=[1,_], cnt(1)=1 lfu.put(2, 2); // cache=[2,1], cnt(2)=1,
 *         cnt(1)=1 lfu.get(1); // return 1 // cache=[1,2], cnt(2)=1, cnt(1)=2
 *         lfu.put(3, 3); // 2 is the LFU key because cnt(2)=1 is the smallest,
 *         invalidate 2. // cache=[3,1], cnt(3)=1, cnt(1)=2 lfu.get(2); //
 *         return -1 (not found) lfu.get(3); // return 3 // cache=[3,1],
 *         cnt(3)=2, cnt(1)=2 lfu.put(4, 4); // Both 1 and 3 have the same cnt,
 *         but 1 is LRU, invalidate 1. // cache=[4,3], cnt(4)=1, cnt(3)=2
 *         lfu.get(1); // return -1 (not found) lfu.get(3); // return 3 //
 *         cache=[3,4], cnt(4)=1, cnt(3)=3 lfu.get(4); // return 4 //
 *         cache=[3,4], cnt(4)=2, cnt(3)=3
 * 
 * 
 *         Constraints:
 * 
 *         0 <= capacity <= 104 0 <= key <= 105 0 <= value <= 109 At most 2 *
 *         105 calls will be made to get and put.
 *
 */

public class _460_LFUCache {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class Node {
		Node next;
		Node prev;
		int val = Integer.MIN_VALUE;
		int key;
		int freq;
	}

	class NodeCont {
		Node head;
		Node tail;
		int size = 0;

		public NodeCont() {
			this.head = new Node();
			this.tail = new Node();
			head.next = tail;
			tail.prev = head;
		}
	}

	class LFUCache {
		Map<Integer, NodeCont> freqMap;
		Map<Integer, Node> keyMap;
		int capacity;
		int minFreq;

		public LFUCache(int capacity) {
			this.freqMap = new HashMap<>();
			this.keyMap = new HashMap<>();
			this.capacity = capacity;
			this.minFreq = 0;
		}

		public int get(int key) {

			if (capacity == 0) {
				return -1;
			}
			Node node = keyMap.get(key);

			if (node == null) {
				return -1;
			}
			incrementNode(node);
			return node.val;
		}

		public void put(int key, int value) {

			if (capacity == 0) {
				return;
			}
			Node node = keyMap.get(key);

			if (node == null) {
				node = new Node();
				node.val = value;
				node.key = key;

				if (keyMap.size() == capacity) {
					removeMinimumNode();
				}
				addNode(node);
				keyMap.put(key, node);
				minFreq = 1;
			} else {
				node.val = value;
				incrementNode(node);
			}
		}

		private void incrementNode(Node node) {
			NodeCont container = freqMap.get(node.freq);
			removeNode(node);
			container.size--;

			if (container.size == 0 && node.freq == minFreq) {
				minFreq++;
			}
			addNode(node);
		}

		private void removeMinimumNode() {
			NodeCont container = freqMap.get(minFreq);
			Node node = container.tail.prev;
			removeNode(node);
			keyMap.remove(node.key);
			container.size--;
		}

		public void removeNode(Node node) {
			Node prev = node.prev;
			Node next = node.next;
			prev.next = next;
			next.prev = prev;
		}

		private void addNode(Node node) {
			node.freq++;
			NodeCont container = freqMap.getOrDefault(node.freq, new NodeCont());
			Node head = container.head;
			Node next = head.next;
			head.next = node;
			node.prev = head;
			node.next = next;
			next.prev = node;
			container.size++;
			freqMap.put(node.freq, container);
		}
	}

	/**
	 * Your LFUCache object will be instantiated and called as such: LFUCache obj =
	 * new LFUCache(capacity); int param_1 = obj.get(key); obj.put(key,value);
	 */
	//=============================================================================================
	//TreeMap, LinkedHashMap based solution
	class LFUCache1 {
		Map<Integer, Integer> keyFreqMap;
		TreeMap<Integer, LinkedHashMap<Integer, Integer>> freqValMap;
		int capacity;
		int currSize = 0;

		public LFUCache1(int capacity) {
			this.keyFreqMap = new HashMap<>();
			this.freqValMap = new TreeMap<>();
			this.capacity = capacity;
		}

		public int get(int key) {

			if (capacity == 0 || !keyFreqMap.containsKey(key)) {
				return -1;
			}
			int ans = -1;
			int freq = keyFreqMap.get(key);
			LinkedHashMap<Integer, Integer> valMap = freqValMap.get(freq);
			ans = valMap.get(key);
			remove(key);
			add(key, ans, ++freq);
			return ans;
		}

		public void put(int key, int value) {

			if (capacity == 0) {
				return;
			}
			int frequency = 1;

			if (keyFreqMap.containsKey(key)) {
				int freq = keyFreqMap.get(key);
				frequency = freq + 1;
				remove(key);
				currSize--;
			} else {

				if (currSize == capacity) {
					Map.Entry<Integer, LinkedHashMap<Integer, Integer>> smallestEntry = freqValMap.firstEntry();
					LinkedHashMap<Integer, Integer> valMap = smallestEntry.getValue();

					for (int ke : valMap.keySet()) {
						remove(ke);
						currSize--;
						break;
					}
				}
			}
			add(key, value, frequency);
			currSize++;
		}

		private void add(int key, int value, int frequency) {
			keyFreqMap.put(key, frequency);
			LinkedHashMap<Integer, Integer> valMap =
					freqValMap.getOrDefault(frequency, new LinkedHashMap<>());
			valMap.put(key, value);
			freqValMap.put(frequency, valMap);
		}

		private void remove(int key) {
			int freq = keyFreqMap.get(key);
			keyFreqMap.remove(key);
			LinkedHashMap<Integer, Integer> valMap = freqValMap.get(freq);
			valMap.remove(key);

			if (valMap.size() == 0) {
				freqValMap.remove(freq);
			}
		}
	}

}
