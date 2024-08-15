package leetcode.easy;

/**
 *
 * Design a HashMap without using any built-in hash table libraries.
 *
 * Implement the MyHashMap class:
 *
 * MyHashMap() initializes the object with an empty map.
 * void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 * int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 * void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 *
 *
 * Example 1:
 *
 * Input
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * Explanation
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 *
 *
 * Constraints:
 *
 * 0 <= key, value <= 106
 * At most 104 calls will be made to put, get, and remove.
 *
 */

public class _706_DesignHashMap {

    class ListNode{

        //key and value means the key-value pair for the nodes of a linked list.
        int key,value;
        ListNode next;

        ListNode(int key,int value){
            this.key = key;
            this.value = value;
        }

    }

    class MyHashMap {
        ListNode[] nodes;

        //Constructor
        public MyHashMap() {
            nodes = new ListNode[10000];
        }

        //Add value into the array
        public void put(int key, int value) {

            //Retrieve the hashed index
            int index = getIndex(key);

            ListNode prev = findElement(index,key);
            //If prev.next is empty we put the (key,value) pair here
            if(prev.next==null)
                prev.next = new ListNode(key,value);
            else
                //If not, we will replace the value for that index.
                prev.next.value = value;


        }

        //Returns the value at a given key
        public int get(int key) {

            //Retrieve the hashed index
            int index = getIndex(key);

            ListNode prev = findElement(index,key);

            //If it is empty, then no value exists with that particular key
            if(prev.next==null)
                return -1;
            else
                //else return it
                return prev.next.value;

        }

        //Remove an element from the array
        public void remove(int key) {
            //Retrieve the hashed index
            int index = getIndex(key);
            ListNode prev = findElement(index, key);

            //Removing element from a linkedlist
            if(prev.next != null)
                prev.next = prev.next.next;
        }

        //This will generate the hashed key for our array
        public int getIndex(int key){
            return Integer.hashCode(key)%nodes.length;
        }

        public ListNode findElement(int index,int key){
            //If no value exist at the particular index, create a node with (-1,-1) (key,value) pair , store it in the array and return it.
            if(nodes[index]==null)
                return nodes[index] =  new ListNode(-1,-1);

            //Use this variable to traverse the array
            ListNode prev = nodes[index];

            //index is the hashed value of the key, we will compare the keys and return the value accordingly where the key matches.
            while(prev.next!=null && prev.next.key!=key){
                prev = prev.next;
            }
            return prev;

        }

    }

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
}
