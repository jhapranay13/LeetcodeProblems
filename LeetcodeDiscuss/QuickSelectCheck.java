package LeetcodeDiscuss;

import java.util.Arrays;

public class QuickSelectCheck {

    public static void main(String[] args) {
        int[] arr = {9, 5, 18, 14, 6, 7, 1, 29, 34, 22};
        System.out.println(quickSelect(arr, 0, arr.length - 1, 2));
        System.out.println(quickSelect(arr, 0, arr.length - 1, 7));
        System.out.println(Arrays.toString(arr));
    }

    private static int quickSelect(int[] arr, int lo, int hi, int nthVal) {

        if (lo == hi) {
            return arr[lo];
        }
        int pivotVal = arr[hi];
        int index = lo;

        for (int i = lo; i < hi; i++) {

            if (pivotVal > arr[i]) {
                int temp = arr[i];
                arr[i] = arr[index];
                arr[index++] = temp;
            }
        }
        int temp = arr[index];
        arr[index] = pivotVal;
        arr[hi] = temp;


        if (index + 1 < nthVal) {
            return quickSelect(arr, index + 1, hi, nthVal);
        } else if (index + 1 > nthVal){
            return quickSelect(arr, lo, index - 1, nthVal);
        }

        return arr[index];
    }
}
