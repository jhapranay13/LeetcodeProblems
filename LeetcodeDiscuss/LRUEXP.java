package LeetcodeDiscuss;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    class Node {
        int key;
        int val;
        Node next;
        Node prev;
    }
    private int capacity;
    private int size;
    private Map<Integer, Node> keyVal;
    private Node head;
    private Node tail;

    public LRUCache(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity canot be zero");
        }
        this.capacity = capacity;
        this.size = 0;
        this.keyVal = new HashMap<>();
        this.head = new Node();
        this.tail = new Node();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = keyVal.get(key);

        if (node != null) {
            add(node.key, node.val);
        }
        return node.val;
    }

    public void put(int key, int value) {
        System.out.println(key + " " + value);
        if (size < capacity || keyVal.containsKey(key)) {
            add(key, value);
        } else {
            remove();
            add(key, value);
        }
    }

    public void remove() {
        Node node = tail.prev;
        node.prev.next = tail;
        tail.prev = node.prev;
        keyVal.remove(node.key);
        size--;
    }

    private void add(int key, int value) {
        Node node = null;

        if (keyVal.containsKey(key)) {
            node = keyVal.get(key);
            node.val = value;
            Node prev = node.prev;
            Node next = node.next;
            prev.next = next;
            next.prev = prev;
        } else {
            node = new Node();
            node.val = value;
            node.key = key;
            keyVal.put(key, node);
            size++;
        }
        Node next = head.next;
        node.prev = head;
        node.next = next;
    }
}

public class LRUEXP {
    public static void main(String args[]) {
        LRUCache obj = new LRUCache(2);
    }
}
