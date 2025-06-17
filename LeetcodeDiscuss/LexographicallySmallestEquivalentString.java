package LeetcodeDiscuss;


/**
 *
 * You are given two strings of the same length s1 and s2 and a string baseStr.
 *
 * We say s1[i] and s2[i] are equivalent characters.
 *
 * For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
 * Equivalent characters follow the usual rules of any equivalence relation:
 *
 * Reflexivity: 'a' == 'a'.
 * Symmetry: 'a' == 'b' implies 'b' == 'a'.
 * Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.
 * For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.
 *
 * Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 *
 * Example 1:
 *
 * Input: s1 = "parker", s2 = "morris", baseStr = "parser"
 * Output: "makkek"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
 * The characters in each group are equivalent and sorted in lexicographical order.
 * So the answer is "makkek".
 * Example 2:
 *
 * Input: s1 = "hello", s2 = "world", baseStr = "hold"
 * Output: "hdld"
 * Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
 * So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".
 * Example 3:
 *
 * Input: s1 = "leetcode", s2 = "programs", baseStr = "sourcecode"
 * Output: "aauaaaaada"
 * Explanation: We group the equivalent characters in s1 and s2 as [a,o,e,r,s,c], [l,p], [g,t] and [d,m], thus all letters in baseStr except 'u' and 'd' are transformed to 'a', the answer is "aauaaaaada".
 *
 * Constraints:
 *
 * 1 <= s1.length, s2.length, baseStr <= 1000
 * s1.length == s2.length
 * s1, s2, and baseStr consist of lowercase English letters.
 *
 */
public class LexographicallySmallestEquivalentString {

    public static void main(String args[]) {
        System.out.println(lexicographicallySmallestEquivalentString("parker", "morris", "parser"));
        System.out.println("--- Example Test Cases ---");
        // Example 1: Basic Equivalence and Sorting
        System.out.println("Ex1: " + lexicographicallySmallestEquivalentString("parker", "morris", "parser")); // Expected: makkek
        // Example 2: Simple Equivalence, Some Characters Not Affected
        System.out.println("Ex2: " + lexicographicallySmallestEquivalentString("hello", "world", "hold"));    // Expected: hdld
        // Example 3: Complex Equivalence with Transitivity
        System.out.println("Ex3: " + lexicographicallySmallestEquivalentString("leetcode", "programs", "sourcecode")); // Expected: aauaaaaada

        System.out.println("\n--- Custom Test Cases ---");

        // Custom 4: All Characters Equivalent (simple)
        System.out.println("C4: " + lexicographicallySmallestEquivalentString("aaaaa", "bbbbb", "abcde")); // Expected: aacde

        // Custom 5: No Equivalence (distinct characters in s1, s2 pairs)
        System.out.println("C5: " + lexicographicallySmallestEquivalentString("abc", "def", "xyz"));     // Expected: xyz

        // Custom 6: Complex Transitivity Chain
        System.out.println("C6: " + lexicographicallySmallestEquivalentString("aceg", "bdfh", "abcdefgh")); // Expected: aacceegg

        // Custom 7: Single Character Strings
        System.out.println("C7: " + lexicographicallySmallestEquivalentString("z", "a", "z"));            // Expected: a

        // Custom 8: All Characters Become the Same Smallest (longer chain of transitivity)
        System.out.println("C8: " + lexicographicallySmallestEquivalentString("zyxw", "abcd", "wxyz")); // Expected: dcba

        // Custom 9: Middle character and boundary conditions (no commonalities)
        System.out.println("C9: " + lexicographicallySmallestEquivalentString("mid", "xyz", "mix"));      // Expected: mim

        // Custom 10: Chain of three characters (a=b, b=c, c=a -> all to 'a')
        System.out.println("C10: " + lexicographicallySmallestEquivalentString("abc", "bca", "cab"));      // Expected: aaa


        // Custom 11: Longer strings, multiple disjoint groups
        System.out.println("C11: " + lexicographicallySmallestEquivalentString("abcdefg", "mnopqrs", "acegikmoq")); // Expected: acegikace

        // Custom 12: All characters the same in s1 and s2
        System.out.println("C12: " + lexicographicallySmallestEquivalentString("zzzz", "zzzz", "aaaa")); // Expected: aaaa

        // Custom 13: Mixed case, some already smallest in group
        System.out.println("C13: " + lexicographicallySmallestEquivalentString("apple", "grape", "pearl")); // Expected: eaaae
        // a=g (a), p=r (p), p=a (p=a, so a=g=p=r) -> all become 'a'
        // l=p (l,p), e=e (e)
        // Groups: [a,g,p,r], [l], [e]
        // P -> a, E -> e, A -> a, R -> a, L -> l
        // Expected: eaaal
    }

    static class UnionFind {
        int[] parent;

        public UnionFind(int n) {
            this.parent = new int[n];

            for (int i = 0; i < n; i++) {
                this.parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX != rootY) {

                if (rootX < rootY) {
                    parent[rootY] = rootX;// Union by rank can be added for optimization
                } else {
                    parent[rootX] = rootY;
                }
            }
        }
    }

    private static String lexicographicallySmallestEquivalentString(String s1, String s2, String baseStr) {
        UnionFind uf = new UnionFind(26);

        for (int i = 0; i < s1.length(); i++) {
            int index1 = s1.charAt(i) - 'a';
            int index2 = s2.charAt(i) - 'a';
            uf.union(index1, index2);
        }
        StringBuilder result = new StringBuilder();

        for (char c : baseStr.toCharArray()) {
            int index = c - 'a';
            int rootIndex = uf.find(index);
            char smallestChar = (char) (rootIndex + 'a');
            result.append(smallestChar);
        }
        return result.toString();
    }
}
