package leetcode.HashMapHashSet;

import java.util.*;

/**
 *
 * RandomizedCollection is a data structure that contains a collection of numbers, possibly duplicates (i.e., a multiset). It should support inserting and removing specific elements and also removing a random element.
 *
 * Implement the RandomizedCollection class:
 *
 * RandomizedCollection() Initializes the empty RandomizedCollection object.
 * bool insert(int val) Inserts an item val into the multiset, even if the item is already present. Returns true if the item is not present, false otherwise.
 * bool remove(int val) Removes an item val from the multiset if present. Returns true if the item is present, false otherwise. Note that if val has multiple occurrences in the multiset, we only remove one of them.
 * int getRandom() Returns a random element from the current multiset of elements. The probability of each element being returned is linearly related to the number of same values the multiset contains.
 * You must implement the functions of the class such that each function works on average O(1) time complexity.
 *
 * Note: The test cases are generated such that getRandom will only be called if there is at least one item in the RandomizedCollection.
 *
 *
 *
 * Example 1:
 *
 * Input
 * ["RandomizedCollection", "insert", "insert", "insert", "getRandom", "remove", "getRandom"]
 * [[], [1], [1], [2], [], [1], []]
 * Output
 * [null, true, false, true, 2, true, 1]
 *
 * Explanation
 * RandomizedCollection randomizedCollection = new RandomizedCollection();
 * randomizedCollection.insert(1);   // return true since the collection does not contain 1.
 *                                   // Inserts 1 into the collection.
 * randomizedCollection.insert(1);   // return false since the collection contains 1.
 *                                   // Inserts another 1 into the collection. Collection now contains [1,1].
 * randomizedCollection.insert(2);   // return true since the collection does not contain 2.
 *                                   // Inserts 2 into the collection. Collection now contains [1,1,2].
 * randomizedCollection.getRandom(); // getRandom should:
 *                                   // - return 1 with probability 2/3, or
 *                                   // - return 2 with probability 1/3.
 * randomizedCollection.remove(1);   // return true since the collection contains 1.
 *                                   // Removes 1 from the collection. Collection now contains [1,2].
 * randomizedCollection.getRandom(); // getRandom should return 1 or 2, both equally likely.
 *
 *
 * Constraints:
 *
 * -2^31 <= val <= 2^31 - 1
 * At most 2 * 10^5 calls in total will be made to insert, remove, and getRandom.
 * There will be at least one element in the data structure when getRandom is called.
 *
 */

public class _381_Insert_Delete_GetRandom_O_1_Duplicates_allowed {
    class RandomizedCollection {
        List<Integer> collect;
        Map<Integer, Set<Integer>> indexHolder;
        Random rand = new Random();

        public RandomizedCollection() {
            this.collect = new ArrayList<>();
            this.indexHolder = new HashMap<>();
        }

        public boolean insert(int val) {

            if (!indexHolder.containsKey(val)) {
                indexHolder.put(val, new LinkedHashSet<>());
            }
            Set<Integer> set = indexHolder.get(val);
            int index = collect.size();
            set.add(index);
            collect.add(val);
            return set.size() == 1;
        }

        public boolean remove(int val) {
            List<Integer> tc = collect;
            Map<Integer, Set<Integer>> indexHoldert = indexHolder;

            if (indexHolder.containsKey(val) && indexHolder.get(val).size() >= 1) {
                int removeIndex = indexHolder.get(val).iterator().next();
                indexHolder.get(val).remove(removeIndex);
                int lastVal = collect.get(collect.size() - 1);
                collect.set(removeIndex, lastVal);
                indexHolder.get(lastVal).add(removeIndex);
                indexHolder.get(lastVal).remove(collect.size() - 1);
                collect.remove(collect.size() - 1);
                return true;
            }
            return false;
        }

        public int getRandom() {
            return collect.get(rand.nextInt(collect.size()));
        }
    }

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
}
