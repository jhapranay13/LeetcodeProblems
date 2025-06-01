package leetcode.medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * You have n  tiles, where each tile has one letter tiles[i] printed on it.
 *
 * Return the number of possible non-empty sequences of letters you can make using the letters printed on those tiles.
 *
 *
 *
 * Example 1:
 *
 * Input: tiles = "AAB"
 * Output: 8
 * Explanation: The possible sequences are "A", "B", "AA", "AB", "BA", "AAB", "ABA", "BAA".
 * Example 2:
 *
 * Input: tiles = "AAABBC"
 * Output: 188
 * Example 3:
 *
 * Input: tiles = "V"
 * Output: 1
 *
 *
 * Constraints:
 *
 * 1 <= tiles.length <= 7
 * tiles consists of uppercase English letters.
 *
 */
public class _1079_Letter_Tile_Possibilities {
    public int numTilePossibilities(String tiles) {
        return recur(tiles.toCharArray(), 0);
    }

    private int recur(char[] tiles, int index) {
        int ans = 0;

        if (index == tiles.length) {
            return ans;
        }
        Set<Character> used = new HashSet<>();

        for (int i = index; i < tiles.length; i++) {

            if (!used.add(tiles[i])) {
                continue;
            }
            ans++;
            swap(tiles, i, index);
            ans += recur(tiles, index + 1);
            swap(tiles, i, index);
        }
        return ans;
    }

    private void swap(char[] tiles, int x, int y) {
        char temp = tiles[x];
        tiles[x] = tiles[y];
        tiles[y] = temp;
    }
    //=============================================================================================
    //Another way
    public int numTilePossibilities1(String tiles) {
        /**
         This solution is from Leetcode
         */

        Map<Character, Integer> tmap = new HashMap<>();
        for(char c : tiles.toCharArray()){
            tmap.put(c, tmap.getOrDefault(c,0)+1);
        }

        return backTracking(tmap);
    }

    private int backTracking(Map<Character, Integer> tmap){

        int res =0;

        for(Character c: tmap.keySet()){
            if(tmap.get(c) > 0){
                int count = tmap.get(c);

                tmap.put(c,count-1);
                res+=1;
                res += backTracking(tmap);

                tmap.put(c, count);
            }

        }
        return res;
    }
}
