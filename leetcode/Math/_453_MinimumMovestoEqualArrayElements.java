package leetcode.Math;

import java.util.Arrays;

/**
 *
 * Given an integer array nums of size n, return the minimum number of moves required to make all array elements equal.
 *
 * In one move, you can increment n - 1 elements of the array by 1.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [1,2,3]
 * Output: 3
 * Explanation: Only three moves are needed (remember each move increments two elements):
 * [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * Example 2:
 *
 * Input: nums = [1,1,1]
 * Output: 0
 *
 *
 * Constraints:
 *
 * n == nums.length
 * 1 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 *
 */

/*
The problem gets simplified if we sort the given array in order to obtain a sorted array aa.
Now, similar to Approach 2,we use the difference diff=max-mindiff=max−min to update the elements
of the array, but we need not traverse the whole array to find the maximum and minimum element every
time, since if the array is sorted, we can make use of this property to find the maximum and minimum
element after updation in O(1)O(1) time. Further, we need not actually update all the elements of
the array. To understand how this works, we'll go in a stepwise manner.

Firstly, assume that we are updating the elements of the sorted array after every step of
calculating the difference diffdiff. We'll see how to find the maximum and minimum element without
 traversing the array. In the first step, the last element is the largest element. Therefore,
 diff=a[n-1]-a[0]diff=a[n−1]−a[0]. We add diffdiff to all the elements except the last
  one i.e. a[n-1]a[n−1]. Now, the updated element at index 0 ,a'[0]a
′
 [0] will be a[0]+diff=a[n-1]a[0]+diff=a[n−1]. Thus, the smallest element a'[0]a
′
 [0] is now equal to the previous largest element a[n-1]a[n−1]. Since, the elements of the array
 are sorted, the elements upto index i-2i−2 satisfy the property a[j]>=a[j-1]a[j]>=a[j−1]. Thus, after
 updation, the element a'[n-2]a
′
 [n−2] will become the largest element, which is obvious due to the sorted array property. Also,
 a[0] is still the smallest element.

Thus, for the second updation, we consider the difference diffdiff as diff=a[n-2]-a[0]diff=a[n−2]−a[0].
After updation, a''[0]a
′′
 [0] will become equal to a'[n-2]a
′
 [n−2] similar to the first iteration. Further, since a'[0]a
′
 [0] and a'[n-1]a
′
 [n−1] were equal. After the second updation, we get a''[0]=a''[n-1]=a'[n-2]a
′′
 [0]=a
′′
 [n−1]=a
′
 [n−2]. Thus, now the largest element will be a[n-3]a[n−3]. Thus, we can continue in this fashion,
 and keep on incrementing the number of moves with the difference found at every step.

Now, let's come to step 2. In the first step, we assumed that we are updating the elements of the
array aa at every step, but we need not do this. This is because, even after updating the elements the difference which we consider to add to the number of moves required remains the same because both the elements maxmax and minmin required to find the diffdiff get updated by the same amount everytime.
 */

public class _453_MinimumMovestoEqualArrayElements {
    public int minMoves(int[] nums) {
        Arrays.sort(nums);
        int count = 0;

        for (int i = nums.length - 1; i > 0; i--) {
            count += nums[i] - nums[0];
        }
        return count;
    }
}
