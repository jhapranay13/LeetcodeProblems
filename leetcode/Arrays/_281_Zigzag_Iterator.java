package leetcode.Arrays;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given two vectors of integers v1 and v2, implement an iterator to return their elements alternately.
 *
 * Implement the ZigzagIterator class:
 *
 * ZigzagIterator(List<int> v1, List<int> v2) initializes the object with the two vectors v1 and v2.
 * boolean hasNext() returns true if the iterator still has elements, and false otherwise.
 * int next() returns the current element of the iterator and moves the iterator to the next element.
 *
 *
 * Example 1:
 *
 * Input: v1 = [1,2], v2 = [3,4,5,6]
 * Output: [1,3,2,4,5,6]
 * Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,3,2,4,5,6].
 * Example 2:
 *
 * Input: v1 = [1], v2 = []
 * Output: [1]
 * Example 3:
 *
 * Input: v1 = [], v2 = [1]
 * Output: [1]
 *
 *
 * Constraints:
 *
 * 0 <= v1.length, v2.length <= 1000
 * 1 <= v1.length + v2.length <= 2000
 * -231 <= v1[i], v2[i] <= 231 - 1
 *
 *
 * Follow up: What if you are given k vectors? How well can your code be extended to such cases?
 *
 * Clarification for the follow-up question:
 *
 * The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic".
 *
 * Follow-up Example:
 *
 * Input: v1 = [1,2,3], v2 = [4,5,6,7], v3 = [8,9]
 * Output: [1,4,8,2,5,9,3,6,7]
 *
 */

public class _281_Zigzag_Iterator {
    public class ZigzagIterator {
        List<List<Integer>> holder;
        List<Integer> v1;
        List<Integer> v2;
        int size;
        int[] indexHolder;
        int index;
        int count;

        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            this.holder = new ArrayList<>();
            this.size = 0;

            if (v1.size() > 0) {
                this.holder.add(v1);
                this.size += v1.size();
            }

            if (v2.size() > 0) {
                this.holder.add(v2);
                this.size += v2.size();;
            }
            this.indexHolder = new int[holder.size()];
            this.index = 0;
            this.count = 0;
        }

        public int next() {
            count++;
            index %= holder.size();
            int anchor = index;

            do {
                index %= holder.size();

                if (holder.get(index).size() > indexHolder[index]) {
                    break;
                }
                index++;
            } while (anchor != index);
            int tempIndex = index++;

            if (holder.get(tempIndex).size() > indexHolder[tempIndex]) {
                return holder.get(tempIndex).get(indexHolder[tempIndex]++);
            }
            return 0;
        }

        public boolean hasNext() {

            if (count < size) {
                return true;
            }
            return false;
        }
    }

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
}
