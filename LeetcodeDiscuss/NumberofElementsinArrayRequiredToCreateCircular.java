package LeetcodeDiscuss;

import java.util.*;

/**
 * Ran into this problem a few days back. Does anyone know how to solve this?
 *
 * Given an array of integers of size n, find the maximum number of integers k that can be selected from this array such that re-arranging these k integers into a circular array allows for an ordering that ensures the absolute difference between any two adjacent integers is at most 1.
 *
 * Examples:
 * Input: [4, 3, 5, 1, 2, 2, 1]
 * Output: 5
 * Explanation: Pick the elements [3, 1, 2, 2, 1] which can be re-arranged to [2, 1, 1, 2, 3]. Length = 5
 *
 * Input: [2, 2, 3, 2, 1, 2, 2]
 * Output: 7
 * Explanation: Pick the elements [2, 2, 3, 2, 1, 2, 2]. Length = 7
 *
 * Input: [3, 7, 5, 1, 5]
 * Output: 2
 * Explanation: Pick the elements [5, 5] Length = 2
 */

public class NumberofElementsinArrayRequiredToCreateCircular {

    public static void main(String[] args) {
        System.out.println(numberOfElements(new int[]{4, 3, 5, 1, 2, 2, 1}));
        System.out.println(numberOfElements(new int[]{2, 2, 3, 2, 1, 2, 2}));
        System.out.println(numberOfElements(new int[]{3, 7, 5, 1, 5}));
        System.out.println(numberOfElements(new int[]{1,2,3}));
        System.out.println(numberOfElements(new int[]{1, 2, 3, 4, 4, 3, 2, 1}));
    }

    public static int numberOfElements(int[] arr) {
       int ans = 0;
       Map<Integer, Integer> freqMap = new HashMap<>();

       for (int num : arr) {
           freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
       }
       List<Integer> keys = new ArrayList<>(freqMap.keySet());
       Collections.sort(keys);
       int currLength = 0;
       int prevNum = keys.get(0);
       boolean start = true;

       for (int key: keys) {

           if (key - prevNum <= 1) {
               int freq = freqMap.get(key);
               currLength += freq;
               ans = Math.max(ans, currLength);

               if (!start && freq == 1) {
                   currLength = freqMap.get(key);
               }

               if (start) {
                   start = false;
               }
           } else {
               ans = Math.max(ans, currLength);
               currLength = freqMap.get(key);
           }
           prevNum = key;
       }
       return ans;
    }
}
