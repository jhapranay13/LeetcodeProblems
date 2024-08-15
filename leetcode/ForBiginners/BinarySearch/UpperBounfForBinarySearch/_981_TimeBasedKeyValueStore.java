package leetcode.ForBiginners.BinarySearch.UpperBounfForBinarySearch;

import java.util.*;

/**
 * 
 * @author Pranay Jha
 *
 *         Design a time-based key-value data structure that can store multiple
 *         values for the same key at different time stamps and retrieve the
 *         key's value at a certain timestamp.
 * 
 *         Implement the TimeMap class:
 * 
 *         TimeMap() Initializes the object of the data structure. void
 *         set(String key, String value, int timestamp) Stores the key key with
 *         the value value at the given time timestamp. String get(String key,
 *         int timestamp) Returns a value such that set was called previously,
 *         with timestamp_prev <= timestamp. If there are multiple such values,
 *         it returns the value associated with the largest timestamp_prev. If
 *         there are no values, it returns "".
 * 
 * 
 *         Example 1:
 * 
 *         Input ["TimeMap", "set", "get", "get", "set", "get", "get"] [[],
 *         ["foo", "bar", 1], ["foo", 1], ["foo", 3], ["foo", "bar2", 4],
 *         ["foo", 4], ["foo", 5]] Output [null, null, "bar", "bar", null,
 *         "bar2", "bar2"]
 * 
 *         Explanation TimeMap timeMap = new TimeMap(); timeMap.set("foo",
 *         "bar", 1); // store the key "foo" and value "bar" along with
 *         timestamp = 1. timeMap.get("foo", 1); // return "bar"
 *         timeMap.get("foo", 3); // return "bar", since there is no value
 *         corresponding to foo at timestamp 3 and timestamp 2, then the only
 *         value is at timestamp 1 is "bar". timeMap.set("foo", "bar2", 4); //
 *         store the key "foo" and value "ba2r" along with timestamp = 4.
 *         timeMap.get("foo", 4); // return "bar2" timeMap.get("foo", 5); //
 *         return "bar2"
 * 
 * 
 *         Constraints:
 * 
 *         1 <= key.length, value.length <= 100 key and value consist of
 *         lowercase English letters and digits. 1 <= timestamp <= 107 All the
 *         timestamps timestamp of set are strictly increasing. At most 2 * 105
 *         calls will be made to set and get.
 *
 */

public class _981_TimeBasedKeyValueStore {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	class TimeMap {
		Map<String, List<String[]>> cache;

		/** Initialize your data structure here. */
		public TimeMap() {
			this.cache = new HashMap<>();
		}

		public void set(String key, String value, int timestamp) {
			List<String[]> list = cache.getOrDefault(key, new ArrayList<>());
			list.add(new String[] { value, "" + timestamp });
			cache.put(key, list);
		}

		public String get(String key, int timestamp) {
			if (!cache.containsKey(key)) {
				return "";
			}
			return binarySearchLessThanEqualTo(cache.get(key), timestamp);
		}

		private String binarySearchLessThanEqualTo(List<String[]> list, int timestamp) {
			int lo = 0;
			int hi = list.size() - 1;
			int ans = 0;

			while (lo <= hi) {
				int pivot = lo + (hi - lo) / 2;
				String[] val = list.get(pivot);
				int timeStampVal = Integer.parseInt(val[1]);

				if (timeStampVal <= timestamp) {
					lo = pivot + 1;
					ans = pivot;
				} else {
					hi = pivot - 1;
				}
			}
			return Integer.parseInt(list.get(ans)[1]) <= timestamp ? list.get(ans)[0] : "";
		}
	}

	/**
	 * Your TimeMap object will be instantiated and called as such: TimeMap obj =
	 * new TimeMap(); obj.set(key,value,timestamp); String param_2 =
	 * obj.get(key,timestamp);
	 */
	//===================================================================================
	//TreeMap Solution
	class TimeMap1 {
		Map<String, TreeMap<Integer, String>> store;

		public TimeMap1() {
			this.store = new HashMap<>();
		}

		public void set(String key, String value, int timestamp) {
			TreeMap<Integer, String> map = store.getOrDefault(key, new TreeMap<>());
			map.put(timestamp, value);
			store.put(key, map);
		}

		public String get(String key, int timestamp) {

			if (!store.containsKey(key)) {
				return "";
			}
			Map.Entry<Integer, String> floorEntry = store.get(key).floorEntry(timestamp);

			if (floorEntry == null) {
				return "";
			}
			return floorEntry.getValue();
		}
	}

}
