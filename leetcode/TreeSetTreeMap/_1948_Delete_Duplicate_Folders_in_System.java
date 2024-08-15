package leetcode.TreeSetTreeMap;

import java.util.*;

/**
 *
 * Due to a bug, there are many duplicate folders in a file system. You are given a 2D array paths, where paths[i] is an array representing an absolute path to the ith folder in the file system.
 *
 * For example, ["one", "two", "three"] represents the path "/one/two/three".
 * Two folders (not necessarily on the same level) are identical if they contain the same non-empty set of identical subfolders and underlying subfolder structure. The folders do not need to be at the root level to be identical. If two or more folders are identical, then mark the folders as well as all their subfolders.
 *
 * For example, folders "/a" and "/b" in the file structure below are identical. They (as well as their subfolders) should all be marked:
 * /a
 * /a/x
 * /a/x/y
 * /a/z
 * /b
 * /b/x
 * /b/x/y
 * /b/z
 * However, if the file structure also included the path "/b/w", then the folders "/a" and "/b" would not be identical. Note that "/a/x" and "/b/x" would still be considered identical even with the added folder.
 * Once all the identical folders and their subfolders have been marked, the file system will delete all of them. The file system only runs the deletion once, so any folders that become identical after the initial deletion are not deleted.
 *
 * Return the 2D array ans containing the paths of the remaining folders after deleting all the marked folders. The paths may be returned in any order.
 *
 *
 *
 * Example 1:
 *                                          /
 *                                       /  |  \
 *                                      a   c   d
 *                                      ||  ||  |
 *                                      b   b   a
 *
 *
 * Input: paths = [["a"],["c"],["d"],["a","b"],["c","b"],["d","a"]]
 * Output: [["d"],["d","a"]]
 * Explanation: The file structure is as shown.
 * Folders "/a" and "/c" (and their subfolders) are marked for deletion because they both contain an empty
 * folder named "b".
 * Example 2:
 *
 *                                          /
 *                                        /  |  \
 *                                       a   c   w
 *                                       |   |   ||
 *                                       b   b   y
 *                                       |
 *                                       x
 *                                       ||
 *                                       y
 *
 *
 * Input: paths = [["a"],["c"],["a","b"],["c","b"],["a","b","x"],["a","b","x","y"],["w"],["w","y"]]
 * Output: [["c"],["c","b"],["a"],["a","b"]]
 * Explanation: The file structure is as shown.
 * Folders "/a/b/x" and "/w" (and their subfolders) are marked for deletion because they both contain an empty folder named "y".
 * Note that folders "/a" and "/c" are identical after the deletion, but they are not deleted because they were not marked beforehand.
 * Example 3:
 *                                          /
 *                                        /   \
 *                                       a     c
 *                                       |     |
 *                                       b     d
 *
 * Input: paths = [["a","b"],["c","d"],["c"],["a"]]
 * Output: [["c"],["c","d"],["a"],["a","b"]]
 * Explanation: All folders are unique in the file system.
 * Note that the returned array can be in a different order as the order does not matter.
 *
 *
 * Constraints:
 *
 * 1 <= paths.length <= 2 * 104
 * 1 <= paths[i].length <= 500
 * 1 <= paths[i][j].length <= 10
 * 1 <= sum(paths[i][j].length) <= 2 * 105
 * path[i][j] consists of lowercase English letters.
 * No two paths lead to the same folder.
 * For any folder not at the root level, its parent folder will also be in the input.
 * Ac
 *
 */

public class _1948_Delete_Duplicate_Folders_in_System {
    class TrieNode {
        // Coz we need the path in order For the level Path generation to be consistent
        TreeMap<String, TrieNode> children = new TreeMap<>();
        boolean eow = false;
        boolean deleted = false;
    }

    class Trie {
        TrieNode root = new TrieNode();
        Map<String, List<TrieNode>> pathMapping = new HashMap<>();

        public void insert(List<String> path) {
            TrieNode curr = root;

            for (String component : path) {

                if (!curr.children.containsKey(component)) {
                    curr.children.put(component, new TrieNode());
                }
                TrieNode temp = curr.children.get(component);
                curr = temp;
            }
            curr.eow = true;
        }

        public List<List<String>> deleteDuplicate() {
            initPathMapping(root);
            Map<String, List<TrieNode>> temp = pathMapping;

            for (String path : pathMapping.keySet()) {

                if (pathMapping.get(path).size() > 1) {

                    for (TrieNode curr : pathMapping.get(path)) {
                        curr.deleted = true;
                    }
                }
            }
            List<List<String>> ans = new ArrayList<>();
            initAns(ans, root, new ArrayList<String>());
            return ans;
        }

        private void initAns(List<List<String>> ans, TrieNode node, ArrayList<String> holder) {

            if (node.deleted) {
                return;
            }

            for (String key : node.children.keySet()) {
                holder.add(key);
                initAns(ans, node.children.get(key), holder);
                holder.remove(holder.size() - 1);
            }

            if(holder.size() > 0) {
                ans.add((List<String>)(holder.clone()));
            }
        }

        private String initPathMapping(TrieNode node) {
            StringBuilder strHolder = new StringBuilder();
            // getting string of one level coz if the level is same it is deleted.
            for (String child : node.children.keySet()) {
                strHolder.append(child + "#");
            }

            for (String child : node.children.keySet()) {
                strHolder.append("$" + initPathMapping(node.children.get(child)));
            }

            if(!pathMapping.containsKey(strHolder.toString())) {
                pathMapping.put(strHolder.toString(), new ArrayList<>());
            }

            if(strHolder.toString().length() > 0) {
                pathMapping.get(strHolder.toString()).add(node);
            }

            return strHolder.toString();
        }
    }

    public List<List<String>> deleteDuplicateFolder(List<List<String>> paths) {
        Trie trie = new Trie();

        for (List<String> path : paths) {
            trie.insert(path);
        }
        List<List<String>> ans = trie.deleteDuplicate();
        return ans;
    }
}
