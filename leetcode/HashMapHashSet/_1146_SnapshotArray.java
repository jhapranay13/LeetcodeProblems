package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 
 * @author Pranay Jha
 *
 *         Implement a SnapshotArray that supports the following interface:
 * 
 *         SnapshotArray(int length) initializes an array-like data structure
 *         with the given length. Initially, each element equals 0. void
 *         set(index, val) sets the element at the given index to be equal to
 *         val. int snap() takes a snapshot of the array and returns the
 *         snap_id: the total number of times we called snap() minus 1. int
 *         get(index, snap_id) returns the value at the given index, at the time
 *         we took the snapshot with the given snap_id
 * 
 * 
 *         Example 1:
 * 
 *         Input: ["SnapshotArray","set","snap","set","get"]
 *         [[3],[0,5],[],[0,6],[0,0]] Output: [null,null,0,null,5] Explanation:
 *         SnapshotArray snapshotArr = new SnapshotArray(3); // set the length
 *         to be 3 snapshotArr.set(0,5); // Set array[0] = 5 snapshotArr.snap();
 *         // Take a snapshot, return snap_id = 0 snapshotArr.set(0,6);
 *         snapshotArr.get(0,0); // Get the value of array[0] with snap_id = 0,
 *         return 5
 * 
 * 
 *         Constraints:
 * 
 *         1 <= length <= 50000 At most 50000 calls will be made to set, snap,
 *         and get. 0 <= index < length 0 <= snap_id < (the total number of
 *         times we call snap()) 0 <= val <= 10^9
 *
 */

public class _1146_SnapshotArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	class SnapshotArray {
		private int length;
		private Map<Integer, TreeMap<Integer, Integer>> snapshot;
		private int snapId;

		public SnapshotArray(int length) {
			this.snapshot = new HashMap<>();
			this.length = length;
			this.snapId = 0;
		}

		public void set(int index, int val) {

			if (index < length) {
				TreeMap<Integer, Integer> snap = snapshot.getOrDefault(index, new TreeMap<>());
				snap.put(snapId, val);
				snapshot.put(index, snap);
			}
		}

		public int snap() {
			int ans = snapId++;
			return ans;
		}

		public int get(int index, int snap_id) {
			TreeMap<Integer, Integer> snap =
					snapshot.getOrDefault(index, new TreeMap<Integer, Integer>());
			Map.Entry<Integer, Integer> entry = snap.floorEntry(snap_id);
			return entry == null ? 0 : entry.getValue();
		}
	}
	//=============================================================================================

	class SnapshotArray1 {
		private Map<Integer, Integer> array;
		private int length;
		private Map<Integer, Map<Integer, Integer>> snapshot;
		private int snapId;

		public SnapshotArray1(int length) {
			this.array = new HashMap<>();
			this.snapshot = new HashMap<>();
			this.length = length;
			this.snapId = 0;
		}

		public void set(int index, int val) {

			if (index < length) {
				array.put(index, val);
			}
		}

		public int snap() {
			Map<Integer, Integer> snap = new HashMap<>();

			for (Map.Entry<Integer, Integer> entry : array.entrySet()) {
				snap.put(entry.getKey(), entry.getValue());
			}
			snapshot.put(snapId, snap);
			int ans = snapId++;
			return ans;
		}

		public int get(int index, int snap_id) {
			Map<Integer, Integer> snap = snapshot.getOrDefault(snap_id, new HashMap<Integer, Integer>());
			int ans = snap.getOrDefault(index, 0);
			return ans;
		}
	}

	/**
	 * Your SnapshotArray object will be instantiated and called as such:
	 * SnapshotArray obj = new SnapshotArray(length); obj.set(index,val); int
	 * param_2 = obj.snap(); int param_3 = obj.get(index,snap_id);
	 */
}
