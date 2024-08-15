package leetcode.TreeSetTreeMap;

import java.util.*;

/**
 *
 *
 *
 */

public class _432_AllOoneDataStructure {
    class AllOne {
        class DblNode {
            int freq = 0;
            Set<String> keySet = new HashSet<>();
            DblNode next = null;
            DblNode prev = null;
        }

        DblNode head;
        DblNode tail;
        Map<String, DblNode> keyNodeMap;

        public AllOne() {
            this.head = new DblNode();
            this.tail = new DblNode();
            this.head.next = this.tail;
            this.tail.prev = head;
            this.head.freq = -1;
            this.tail.freq = -1;
            this.keyNodeMap = new HashMap<>();
        }

        public void inc(String key) {

            if (keyNodeMap.containsKey(key)) {
                DblNode node = keyNodeMap.get(key);
                node.keySet.remove(key);
                DblNode nodeNext = node.next;

                if (nodeNext == tail || nodeNext.freq != node.freq + 1) {
                    add(node, nodeNext, new DblNode());
                    node.next.freq = node.freq + 1;
                    node.next.keySet.add(key);
                    keyNodeMap.put(key, node.next);
                } else {
                    nodeNext.keySet.add(key);
                    keyNodeMap.put(key, nodeNext);
                }

                if (node.keySet.size() == 0) {
                    remove(node);
                }

            } else {

                if (head.next == tail || head.next.freq != 1) {
                    add(head, head.next, new DblNode());
                }
                DblNode node = head.next;
                node.keySet.add(key);
                node.freq = 1;
                keyNodeMap.put(key, node);
            }
        }

        public void dec(String key) {
            DblNode node = keyNodeMap.get(key);
            node.keySet.remove(key);
            keyNodeMap.remove(key);
            DblNode nodePrev = node.prev;

            if (node.freq > 1) {

                if (nodePrev == head || nodePrev.freq != node.freq - 1) {
                    add(nodePrev, node, new DblNode());
                    node.prev.freq = node.freq - 1;
                    node.prev.keySet.add(key);
                    keyNodeMap.put(key, node.prev);
                } else {
                    nodePrev.keySet.add(key);
                    keyNodeMap.put(key, nodePrev);
                }
            }

            if (node.keySet.size() == 0) {
                remove(node);
            }
        }

        private void remove(DblNode node) {
            DblNode prev = node.prev;
            DblNode next = node.next;
            prev.next = next;
            next.prev = prev;
        }

        private void add(DblNode prev, DblNode next, DblNode curr) {
            prev.next = curr;
            curr.prev = prev;
            curr.next = next;
            next.prev = curr;
            curr.freq = prev.freq + 1;
        }

        public String getMaxKey() {
            if (tail.prev == head) {
                return "";
            }
            return tail.prev.keySet.iterator().next();
        }

        public String getMinKey() {
            if (head.next == tail) {
                return "";
            }
            return head.next.keySet.iterator().next();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
    //=============================================================================================
    class AllOne4 {
        TreeMap<Integer, Set<String>> freqString;
        Map<String, Integer> stringFreq;

        public AllOne4() {
            this.freqString = new TreeMap<>();
            this.stringFreq = new HashMap<>();
        }

        public void inc(String key) {

            if (stringFreq.containsKey(key)) {
                int freq = stringFreq.get(key);
                Set<String> stringSet = freqString.get(freq);
                stringSet.remove(key);

                if (stringSet.size() == 0) {
                    freqString.remove(freq);
                }
                freq++;
                stringFreq.put(key, freq);
                stringSet = freqString.getOrDefault(freq, new HashSet<>());
                stringSet.add(key);
                freqString.put(freq, stringSet);
            } else {
                Set<String> stringSet = freqString.getOrDefault(1, new HashSet<>());
                stringSet.add(key);
                freqString.put(1, stringSet);
                stringFreq.put(key, 1);
            }
        }

        public void dec(String key) {
            int freq = stringFreq.get(key);
            Set<String> stringSet = freqString.get(freq);
            stringSet.remove(key);

            if (stringSet.size() == 0) {
                freqString.remove(freq);
            }
            freq--;

            if (freq == 0) {
                stringFreq.remove(key);
                return;
            }
            stringFreq.put(key, freq);
            stringSet = freqString.getOrDefault(freq, new HashSet<>());
            stringSet.add(key);
            freqString.put(freq, stringSet);
        }

        public String getMaxKey() {

            if (freqString.isEmpty()) {
                return "";
            }
            return freqString.lastEntry().getValue().iterator().next();
        }

        public String getMinKey() {

            if (freqString.isEmpty()) {
                return "";
            }
            return freqString.firstEntry().getValue().iterator().next();
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */


    //===============================================================================================
    //Using Priority Queue
    class AllOne1 {
        Map<String, Integer> cache;
        PriorityQueue<String> minQ;
        PriorityQueue<String> maxQ;

        public AllOne1() {
            this.cache = new HashMap<>();
            this.minQ = new PriorityQueue<>(new Comparator<String>() {
                public int compare(String x, String y) {
                    return cache.get(x) - cache.get(y);
                }
            });
            this.maxQ = new PriorityQueue<>(new Comparator<String>() {
                public int compare(String x, String y) {
                    return cache.get(y) - cache.get(x);
                }
            });
        }

        public void inc(String key) {

            if (!cache.containsKey(key)) {
                cache.put(key, 1);
                minQ.offer(key);
                maxQ.offer(key);
                return;
            }
            cache.put(key, cache.get(key) + 1);
            minQ.remove(key);
            maxQ.remove(key);
            minQ.offer(key);
            maxQ.offer(key);
        }

        public void dec(String key) {
            if (cache.containsKey(key) && cache.get(key) == 1) {
                cache.remove(key);
                minQ.remove(key);
                maxQ.remove(key);
                return;
            }
            cache.put(key, cache.get(key) - 1);
            minQ.remove(key);
            maxQ.remove(key);
            minQ.offer(key);
            maxQ.offer(key);
        }

        public String getMaxKey() {
            return maxQ.size() > 0 ? maxQ.peek() : "";
        }

        public String getMinKey() {
            return minQ.size() > 0 ? minQ.peek() : "";
        }
    }

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
    //===============================================================================================
    //Using Linked List and HashMap
    class Node {
        String v;
        int counts;
        Node left;
        Node right;
    }

    class AllOne2 {
        //double linked list. head side is small count, tail side is big count
        Node head = null;
        Node tail = null;
        //also use map to store every Node.
        Map<String, Node> map = new HashMap<>();

        public AllOne2() {
        }

        public void inc(String key) {
            Node node = map.get(key);
            // if it's new key, then add to head
            if (node == null) {
                node = new Node();
                map.put(key, node);
                node.v = key;
                node.counts = 1;
                if (head == null) {
                    head = node;
                    tail = node;
                } else {
                    head.left = node;
                    node.right = head;
                    head = node;
                }
            } else {
                //if the key exists, count++, and move to right
                node.counts++;
                while (tail != node && node.counts > node.right.counts) {
                    swap(node, node.right);
                }
            }
        }

        public void dec(String key) {
            Node node = map.get(key);
            node.counts--;
            //if count==0, remove from list and map
            if (node.counts == 0) {
                map.remove(key);
                if (node.right != null) {
                    node.right.left = node.left;
                }
                if (node.left != null) {
                    node.left.right = node.right;
                }
                if (node == head) {
                    head = node.right;
                }
                if (node == tail) {
                    tail = node.left;
                }
            } else {
                //if count still >0, move to left
                while (head != node && node.counts < node.left.counts) {
                    swap(node.left, node);
                }
            }
        }

        public String getMinKey() {
            if (head == null) {
                return "";
            }
            return head.v;
        }

        public String getMaxKey() {
            if (tail == null) {
                return "";
            }
            return tail.v;
        }

        // this function is to swap two nodes. Use this function to move node to left or right
        private void swap(Node n1, Node n2) {
            Node left = n1.left;
            Node right = n2.right;
            if (left == null) {
                head = n2;
            } else {
                left.right = n2;
            }
            n2.left = left;
            if (right == null) {
                tail = n1;
            } else {
                right.left = n1;
            }
            n1.right = right;

            n1.left = n2;
            n2.right = n1;
        }
    }
}
