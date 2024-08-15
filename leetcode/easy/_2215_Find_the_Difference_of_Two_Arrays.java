package leetcode.easy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _2215_Find_the_Difference_of_Two_Arrays {
    public List<List<Integer>> findDifference(int[] nums1, int[] nums2) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        ans.add(new ArrayList<>());
        Set<Integer> container1 = new HashSet<>();
        Set<Integer> container2 = new HashSet<>();

        for (int num : nums1) {
            container1.add(num);
        }

        for (int num : nums2) {
            container2.add(num);
        }

        for (int num : container2) {

            if (!container1.contains(num)) {
                ans.get(1).add(num);
            }
        }

        for (int num : container1) {

            if (!container2.contains(num)) {
                ans.get(0).add(num);
            }
        }
        return ans;
    }
}
