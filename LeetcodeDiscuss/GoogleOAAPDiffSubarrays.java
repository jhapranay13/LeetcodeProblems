package LeetcodeDiscuss;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * Given an array of numbers, find all the subarrays which are forming good arithmetic sequence(AP).A good arithmetic sequence is a sequence of numbers having common difference equals to -1 or 1.
 * E.g {1,2,3} and {3,2,1} are good arithmethic sequence with common difference 1 & -1 respectively.
 *
 */
public class GoogleOAAPDiffSubarrays {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 5, 4, 3, 2, 8, 9};
        int ans = countAPDiffSubarrays(arr);
        System.out.println("The answer is: " + ans);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr));
        System.out.println("==========================");
        int arr2[] = {7, 2, 3, 4};
        int ans1 = countAPDiffSubarrays(arr2);
        System.out.println("The answer is: " + ans1);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr2));
        System.out.println("==========================");
        int arr3[] = {1, 2, 3, 4, 5};
        int ans2 = countAPDiffSubarrays(arr3);
        System.out.println("The answer is: " + ans2);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr3));
        System.out.println("==========================");
        int arr4[] = {5, 4, 3, 2, 1};
        int ans3 = countAPDiffSubarrays(arr4);
        System.out.println("The answer is: " + ans3);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr4));
        System.out.println("==========================");
        int arr5[] = {1, 2, 1, 2, 1};
        int ans5 = countAPDiffSubarrays(arr5);
        System.out.println("The answer is: " + ans5);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr5));
        System.out.println("==========================");
        int arr6[] = {1, 3, 5, 7, 9};
        int ans6 = countAPDiffSubarrays(arr6);
        System.out.println("The answer is: " + ans6);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr6));
        System.out.println("==========================");
        int arr7[] = {1, 2, 10, 9, 8, 7, 3, 4};
        int ans7 = countAPDiffSubarrays(arr7);
        System.out.println("All the sub arrays: " + findGoodAPSubarrays(arr7));
        System.out.println("The answer is: " + ans7);
    }

    private static int countAPDiffSubarrays(int[] arr) {
        int n = arr.length;
        int count = 0;
        int i = 0;

        while (i < n - 1) {
            int diff = arr[i + 1] - arr[i];

            // Only start if diff is +1 or -1
            if (Math.abs(diff) != 1) {
                i++;
                continue;
            }

            int len = 2; // at least two elements (i, i+1)
            int j = i + 1;

            while (j + 1 < n && arr[j + 1] - arr[j] == diff) {
                j++;
                len++;
            }

            // Total subarrays of length >= 2 in a segment of size len:
            // (len - 1) + (len - 2) + ... + 1 = (len - 1) * len / 2
            count += (len - 1) * len / 2;

            i = j; // jump to next segment
        }
        return count;
    }

    public static List<List<Integer>> findGoodAPSubarrays(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        int n = arr.length;
        int i = 0;

        while (i < n - 1) {
            int diff = arr[i + 1] - arr[i];

            // Only start when diff is +1 or -1
            if (Math.abs(diff) != 1) {
                i++;
                continue;
            }

            int j = i + 1;
            // Expand while difference remains same
            while (j + 1 < n && arr[j + 1] - arr[j] == diff) {
                j++;
            }

            // From i to j is a valid segment, collect all subarrays of length ≥ 2
            for (int start = i; start < j; start++) {
                for (int end = start + 1; end <= j; end++) {
                    List<Integer> sub = new ArrayList<>();
                    for (int k = start; k <= end; k++) {
                        sub.add(arr[k]);
                    }
                    result.add(sub);
                }
            }

            i = j; // Move to next potential start
        }

        return result;
    }

}
