package leetcode.HashMapHashSet;

import java.util.LinkedList;

/**
 *
 * Design a HashSet without using any built-in hash table libraries.
 *
 * Implement MyHashSet class:
 *
 * void add(key) Inserts the value key into the HashSet.
 * bool contains(key) Returns whether the value key exists in the HashSet or not.
 * void remove(key) Removes the value key in the HashSet. If key does not exist in the HashSet, do nothing.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyHashSet", "add", "add", "contains", "contains", "add", "contains", "remove", "contains"]
 * [[], [1], [2], [1], [3], [2], [2], [2], [2]]
 * Output
 * [null, null, null, true, false, null, true, null, false]
 *
 * Explanation
 * MyHashSet myHashSet = new MyHashSet();
 * myHashSet.add(1);      // set = [1]
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(1); // return True
 * myHashSet.contains(3); // return False, (not found)
 * myHashSet.add(2);      // set = [1, 2]
 * myHashSet.contains(2); // return True
 * myHashSet.remove(2);   // set = [1]
 * myHashSet.contains(2); // return False, (already removed)
 *
 *
 * Constraints:
 *
 * 0 <= key <= 10^6
 * At most 104 calls will be made to add, remove, and contains.
 *
 */

public class _705_Design_HashSet {

    class MyHashSet {
        private Bucket[] bucketArray;
        private int keyRange;

        /** Initialize your data structure here. */
        public MyHashSet() {
            this.keyRange = 769;
            this.bucketArray = new Bucket[this.keyRange];
            for (int i = 0; i < this.keyRange; ++i)
                this.bucketArray[i] = new Bucket();
        }

        protected int _hash(int key) {
            return (key % this.keyRange);
        }

        public void add(int key) {
            int bucketIndex = this._hash(key);
            this.bucketArray[bucketIndex].insert(key);
        }

        public void remove(int key) {
            int bucketIndex = this._hash(key);
            this.bucketArray[bucketIndex].delete(key);
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int bucketIndex = this._hash(key);
            return this.bucketArray[bucketIndex].exists(key);
        }
    }
    
    class Bucket {
        private LinkedList<Integer> container;

        public Bucket() {
            container = new LinkedList<Integer>();
        }

        public void insert(Integer key) {
            int index = this.container.indexOf(key);
            if (index == -1) {
                this.container.addFirst(key);
            }
        }

        public void delete(Integer key) {
            this.container.remove(key);
        }

        public boolean exists(Integer key) {
            int index = this.container.indexOf(key);
            return (index != -1);
        }
    }

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
}
