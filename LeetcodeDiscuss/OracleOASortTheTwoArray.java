package LeetcodeDiscuss;

import java.util.Arrays;

/**
 *
 * Given two arrays, sort without extra space
 * A = [10, 2, 20, -1]
 * B = [1, 5, 25]
 * Output:
 * A = [-1, 1, 2, 5]
 * B = [10, 20, 25]
 *
 */

public class OracleOASortTheTwoArray {

    public static void main(String ...args) {
        System.out.println("Test Merge Without Extra Space");
        System.out.println("=====================================");
        int[] a1 = {10, 2, 20, -1};
        int[] b1 = {1, 5, 25};
        System.out.println("A = " + Arrays.toString(a1));
        System.out.println("B = " + Arrays.toString(b1));
        sortTwoArrays(a1, b1);
        System.out.println("After Sort A = " + Arrays.toString(a1));
        System.out.println("After Sort B = " + Arrays.toString(b1));
        System.out.println("=====================================");
        int[] a2 = {1, 3, 5};
        int[] b2 = {2, 4, 6};
        System.out.println("A = " + Arrays.toString(a2));
        System.out.println("B = " + Arrays.toString(b2));
        sortTwoArrays(a2, b2);
        System.out.println("After Sort A = " + Arrays.toString(a2));
        System.out.println("After Sort B = " + Arrays.toString(b2));
        System.out.println("=====================================");
        int[] a3 = {9, 8, 7};
        int[] b3 = {6, 5, 4};
        System.out.println("A = " + Arrays.toString(a3));
        System.out.println("B = " + Arrays.toString(b3));
        sortTwoArrays(a3, b3);
        System.out.println("After Sort A = " + Arrays.toString(a3));
        System.out.println("After Sort B = " + Arrays.toString(b3));
        System.out.println("=====================================");
        int[] a4 = {9, 9, 9};
        int[] b4 = {9, 9, 9, 9};
        System.out.println("A = " + Arrays.toString(a4));
        System.out.println("B = " + Arrays.toString(b4));
        sortTwoArrays(a4, b4);
        System.out.println("After Sort A = " + Arrays.toString(a4));
        System.out.println("After Sort B = " + Arrays.toString(b4));
    }

    private static void sortTwoArrays(int[] a, int[] b) {

        if (a.length < b.length) {
            sortTwoArrays(b, a);
            return;
        }
        int lo = 0;
        int hi = a.length + b.length - 1;
        quickSort(a, b, lo, hi);
    }

    private static void quickSort(int[] a, int[] b, int lo, int hi) {
        if (lo < hi) {
            int l = lo;
            int h = hi;
            int pivot = lo + (hi - lo) / 2;
            int pivotValue = pivot >= a.length ? b[pivot % a.length] : a[pivot];
            int index = lo;
            int anchor = lo;

            while (l <= h) {

                while (l <= hi && (l < a.length ? a[l] : b[l % a.length]) < pivotValue) {
                    l++;
                }

                while (h >= lo && (h < a.length ? a[h] : b[h % a.length]) > pivotValue) {
                    h--;
                }

                if (l <= h) {
                    swap(a, b, l, h);
                    l++;
                    h--;
                }
            }

            if (l < hi) {
                quickSort(a, b, l, hi);
            }

            if (h > lo) {
                quickSort(a, b, lo, h);
            }
            quickSort(a, b, lo, h);
        }
    }

    private static void swap(int[] a, int[] b, int x, int y) {
        int aVal = x < a.length ? a[x] : b[x % a.length];
        int bVal = y < a.length ? a[y] : b[y % a.length];

        if (x < a.length) {
            a[x] = bVal;
        } else {
            b[x % a.length] = bVal;
        }

        if (y < a.length) {
            a[y] = aVal;
        } else {
            b[y % a.length] = aVal;
        }
    }

}
