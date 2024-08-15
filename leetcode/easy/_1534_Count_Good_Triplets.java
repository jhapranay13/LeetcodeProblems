package leetcode.easy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Given an array of integers arr, and three integers a, b and c. You need to find the number of good triplets.
 *
 * A triplet (arr[i], arr[j], arr[k]) is good if the following conditions are true:
 *
 * 0 <= i < j < k < arr.length
 * |arr[i] - arr[j]| <= a
 * |arr[j] - arr[k]| <= b
 * |arr[i] - arr[k]| <= c
 * Where |x| denotes the absolute value of x.
 *
 * Return the number of good triplets.
 *
 *
 *
 * Example 1:
 *
 * Input: arr = [3,0,1,1,9,7], a = 7, b = 2, c = 3
 * Output: 4
 * Explanation: There are 4 good triplets: [(3,0,1), (3,0,1), (3,1,1), (0,1,1)].
 * Example 2:
 *
 * Input: arr = [1,1,2,2,3], a = 0, b = 0, c = 1
 * Output: 0
 * Explanation: No triplet satisfies all conditions.
 *
 *
 * Constraints:
 *
 * 3 <= arr.length <= 100
 * 0 <= arr[i] <= 1000
 * 0 <= a, b, c <= 1000
 *
 */

public class _1534_Count_Good_Triplets {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int ans = 0;

        for (int i = 0; i <= arr.length - 2; i++) {

            for (int j = i + 1; j <= arr.length - 1; j++) {

                if (Math.abs(arr[i] - arr[j]) <= a) {

                    for (int k = j + 1; k < arr.length; k++) {

                        if (Math.abs(arr[j] - arr[k]) <= b && Math.abs(arr[i] - arr[k]) <= c) {
                            ans++;
                        }
                    }
                }
            }
        }
        return ans;
    }
    //=============================================================================================
    // Different approach
    public int countGoodTriplets1(int[] arr, int a, int b, int c) {
        int result = 0;
        List<Map<String,Integer>> cache = new ArrayList<>();
        for( int i = 0; i < arr.length; i++){
            for( int j = i+ 1; j < arr.length; j ++){
                if( abs( arr[i] - arr[j]) <= a){
                    Map<String, Integer> eachCache = new HashMap<String,Integer>();
                    eachCache.put("j-b", arr[j] - b);
                    eachCache.put("j+b", arr[j] + b);
                    eachCache.put("i-c", arr[i] - c);
                    eachCache.put("i+c", arr[i] + c);
                    eachCache.put("j", j);
                    cache.add(eachCache);
                }
            }
        }
        for(Map<String, Integer> eachCache: cache){
            int v1 = eachCache.get("j-b");
            int v2 = eachCache.get("j+b");
            int v3 = eachCache.get("i-c");
            int v4 = eachCache.get("i+c");
            int j = eachCache.get("j");
            for(int k = j + 1; k < arr.length; k++){
                if( arr[k] >= v1 && arr[k] <= v2 && arr[k] >= v3 && arr[k] <= v4){
                    result++;
                }
            }

        }

        return result;
    }
    public int abs(int input){
        if(input < 0){
            return -input;
        }else{
            return input;
        }
    }
}
