package leetcode.HashMapHashSet;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * You are asked to design a file system that allows you to create new paths and associate them with different values.
 *
 * The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string "" and "/" are not.
 *
 * Implement the FileSystem class:
 *
 * bool createPath(string path, int value) Creates a new path and associates a value to it if possible and returns true. Returns false if the path already exists or its parent path doesn't exist.
 * int get(string path) Returns the value associated with path or returns -1 if the path doesn't exist.
 *
 *
 * Example 1:
 *
 * Input:
 * ["FileSystem","createPath","get"]
 * [[],["/a",1],["/a"]]
 * Output:
 * [null,true,1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/a", 1); // return true
 * fileSystem.get("/a"); // return 1
 * Example 2:
 *
 * Input:
 * ["FileSystem","createPath","createPath","get","createPath","get"]
 * [[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
 * Output:
 * [null,true,true,2,false,-1]
 * Explanation:
 * FileSystem fileSystem = new FileSystem();
 *
 * fileSystem.createPath("/leet", 1); // return true
 * fileSystem.createPath("/leet/code", 2); // return true
 * fileSystem.get("/leet/code"); // return 2
 * fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
 * fileSystem.get("/c"); // return -1 because this path doesn't exist.
 *
 *
 * Constraints:
 *
 * The number of calls to the two functions is less than or equal to 104 in total.
 * 2 <= path.length <= 100
 * 1 <= value <= 10^9
 *
 */

public class _1166_Design_File_System {
    //HashMap Solution
    class FileSystem {
        Map<String, Integer> cache;

        public FileSystem() {
            this.cache = new HashMap<>();
        }

        public boolean createPath(String path, int value) {

            if (path.isEmpty()) {
                return false;
            }
            int lastIndex = path.lastIndexOf("/");

            if (lastIndex != 0) {
                String firstPart = path.substring(0, lastIndex);

                if (!cache.containsKey(firstPart)) {
                    return false;
                }
            }

            if (cache.containsKey(path)) {
                return false;
            }
            cache.put(path, value);
            return true;
        }

        public int get(String path) {
            return cache.getOrDefault(path, -1);
        }
    }
    //=============================================================================================
    //Trie Solution
    class FileSystem1 {
        class TrieNode {
            Map<String, TrieNode> child = new HashMap<>();
            int value = -1;
            boolean eow = false;
        }
        TrieNode root;

        public FileSystem1() {
            this.root = new TrieNode();
        }

        public boolean createPath(String path, int value) {

            if (path.isEmpty()) {
                return false;
            }
            TrieNode curr = root;
            String[] parts = path.split("/");

            for (int i = 1; i < parts.length; i++) {
                String part = parts[i];

                if (!curr.child.containsKey(part)) {

                    if (i == parts.length - 1) {
                        curr.child.put(part, new TrieNode());
                    } else {
                        return false;
                    }
                }
                curr = curr.child.get(part);
            }

            if (curr.value != -1) {
                return false;
            }
            curr.value = value;
            curr.eow = true;
            return true;
        }

        public int get(String path) {
            int ans = -1;
            String[] parts = path.split("/");
            TrieNode curr = root;

            for (int i = 1; i < parts.length; i++) {
                String part = parts[i];

                if (!curr.child.containsKey(part)) {
                    return ans;
                }
                curr = curr.child.get(part);
            }
            ans = curr.value;
            return ans;
        }
    }


/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.createPath(path,value);
 * int param_2 = obj.get(path);
 */

}
