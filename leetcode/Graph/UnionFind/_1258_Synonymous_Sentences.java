package leetcode.Graph.UnionFind;

import java.util.*;

/**
 *
 * You are given a list of equivalent string pairs synonyms where synonyms[i] = [si, ti] indicates that si and ti are equivalent strings. You are also given a sentence text.
 *
 * Return all possible synonymous sentences sorted lexicographically.
 *
 *
 *
 * Example 1:
 *
 * Input: synonyms = [["happy","joy"],["sad","sorrow"],["joy","cheerful"]], text = "I am happy today but was sad yesterday"
 * Output: ["I am cheerful today but was sad yesterday","I am cheerful today but was sorrow yesterday","I am happy today but was sad yesterday","I am happy today but was sorrow yesterday","I am joy today but was sad yesterday","I am joy today but was sorrow yesterday"]
 * Example 2:
 *
 * Input: synonyms = [["happy","joy"],["cheerful","glad"]], text = "I am happy today but was sad yesterday"
 * Output: ["I am happy today but was sad yesterday","I am joy today but was sad yesterday"]
 *
 *
 * Constraints:
 *
 * 0 <= synonyms.length <= 10
 * synonyms[i].length == 2
 * 1 <= si.length, ti.length <= 10
 * si != ti
 * text consists of at most 10 words.
 * All the pairs of synonyms are unique.
 * The words of text are separated by single spaces.
 *
 */

public class _1258_Synonymous_Sentences {
    class UnionFind {
        Map<String, String> children = new HashMap<>();

        public UnionFind(Set<String> set) {

            for (String str : set) {
                children.put(str, str);
            }
        }

        public String find(String s) {

            if (!children.containsKey(s)) {
                return null;
            }

            if (children.get(s).equals(s)) {
                return s;
            }
            return find(children.get(s));
        }

        public void union(String x, String y) {
            String px = find(x);
            String py = find(y);

            if (!px.equals(py)) {
                children.put(py, px);
            }
        }
    }
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Set<String> set = new HashSet<>();

        for (List<String> list : synonyms) {
            set.add(list.get(0));
            set.add(list.get(1));
        }
        UnionFind uf = new UnionFind(set);

        for (List<String> list : synonyms) {
            uf.union(list.get(0), list.get(1));
        }
        Map<String, List<String>> parentChildMap = new HashMap<>();

        for (String str : set) {
            String parent = uf.find(str);
            List<String> list = parentChildMap.getOrDefault(parent, new ArrayList<>());
            list.add(str);
            parentChildMap.put(parent, list);
        }
        String[] split = text.split(" ");
        List<String> ans = new ArrayList<>();
        StringBuilder holder = new StringBuilder();
        recur(split, ans, parentChildMap, 0, holder, uf);
        Collections.sort(ans);
        return ans;
    }

    private void recur(String[] split, List<String> ans, Map<String, List<String>> parentChildMap, int index,
                       StringBuilder holder, UnionFind uf) {

        if (index == split.length) {
            ans.add(holder.toString());
            return;
        }
        String str = split[index];
        String parent = uf.find(str);
        boolean isEmpty = holder.isEmpty();

        if (parent == null) {
            int start = holder.length();

            if (!isEmpty) {
                holder.append(" ");
            }
            holder.append(str);
            recur(split, ans, parentChildMap, index + 1, holder, uf);
            holder.delete(start, holder.length());
        } else {
            List<String> list = parentChildMap.get(parent);

            for (String s : list) {
                int start = holder.length();

                if (!isEmpty) {
                    holder.append(" ");
                }
                holder.append(s);
                recur(split, ans, parentChildMap, index + 1, holder, uf);
                holder.delete(start, holder.length());
            }
        }
    }
}
