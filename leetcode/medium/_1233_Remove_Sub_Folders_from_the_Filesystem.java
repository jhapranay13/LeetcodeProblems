package leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given a list of folders folder, return the folders after removing all sub-folders in those folders. You may return the answer in any order.
 *
 * If a folder[i] is located within another folder[j], it is called a sub-folder of it.
 *
 * The format of a path is one or more concatenated strings of the form: '/' followed by one or more lowercase English letters.
 *
 * For example, "/leetcode" and "/leetcode/problems" are valid paths while an empty string and "/" are not.
 *
 *
 * Example 1:
 *
 * Input: folder = ["/a","/a/b","/c/d","/c/d/e","/c/f"]
 * Output: ["/a","/c/d","/c/f"]
 * Explanation: Folders "/a/b" is a subfolder of "/a" and "/c/d/e" is inside of folder "/c/d" in our filesystem.
 * Example 2:
 *
 * Input: folder = ["/a","/a/b/c","/a/b/d"]
 * Output: ["/a"]
 * Explanation: Folders "/a/b/c" and "/a/b/d" will be removed because they are subfolders of "/a".
 * Example 3:
 *
 * Input: folder = ["/a/b/c","/a/b/ca","/a/b/d"]
 * Output: ["/a/b/c","/a/b/ca","/a/b/d"]
 *
 *
 * Constraints:
 *
 * 1 <= folder.length <= 4 * 10^4
 * 2 <= folder[i].length <= 100
 * folder[i] contains only lowercase letters and '/'.
 * folder[i] always starts with the character '/'.
 * Each folder name is unique.
 *
 */

public class _1233_Remove_Sub_Folders_from_the_Filesystem {
    class TrieNode {
        boolean eow = false;
        Map<String, TrieNode> children = new HashMap<>();
    }

    class Trie {
        TrieNode root = new TrieNode();

        public void insert(String[] component) {
            TrieNode curr = root;

            for (int i = 1; i < component.length; i++) {
                String folder = component[i];

                if (!curr.children.containsKey(folder)) {
                    curr.children.put(folder, new TrieNode());
                }
                curr = curr.children.get(folder);
            }
            curr.eow = true;
        }

        public List<String> getList() {
            List<String> ans = new ArrayList<>();
            StringBuilder holder = new StringBuilder();
            recur(root, holder, ans);
            return ans;
        }

        private void recur(TrieNode node, StringBuilder holder, List<String> ans) {

            if (node == null) {
                return;
            }

            if (node.eow) {
                ans.add(holder.toString());
                return;
            }
            Map<String, TrieNode> children = node.children;

            for (String key : children.keySet()) {
                String folder = "/" + key;
                int size = holder.length();
                holder.append(folder);
                recur(children.get(key), holder, ans);
                holder.delete(size, holder.length());
            }
        }
    }

    public List<String> removeSubfolders(String[] folder) {
        Trie trie = new Trie();

        for (String f : folder) {
            String[] path = f.split("/");
            //System.out.println(Arrays.toString(path));
            trie.insert(path);
        }
        List<String> ans = trie.getList();
        return ans;
    }
}
