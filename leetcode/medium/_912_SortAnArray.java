package leetcode.medium;

/**
 * 
 * @author Pranay Jha
 *
 *         Given an array of integers nums, sort the array in ascending order.
 * 
 * 
 * 
 *         Example 1:
 * 
 *         Input: nums = [5,2,3,1] Output: [1,2,3,5]
 * 
 *         Example 2:
 * 
 *         Input: nums = [5,1,1,2,0,0] Output: [0,0,1,1,2,5]
 * 
 * 
 *         Constraints:
 * 
 *         1 <= nums.length <= 5 * 10^4 -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 */

public class _912_SortAnArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// =============================================================================
	// Bubble Sort
	public int[] sortArray1(int[] nums) {
		bubbleSort(nums);
		return nums;
	}

	private void bubbleSort(int[] nums) {

		for (int i = 0; i < nums.length - 1; i++) {
			boolean swapped = true;

			for (int j = 0; j <= nums.length - 2; j++) {

				if (nums[j] > nums[j + 1]) {
					swapped = true;
					swap(nums, j, j + 1);
				}
			}

			if (!swapped) {
				break;
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[j];
		nums[j] = nums[i];
		nums[i] = temp;
	}

	// =============================================================================
	// Selection Sort
	public int[] sortArray2(int[] nums) {
		selectionSort(nums);
		return nums;
	}

	private void selectionSort(int[] nums) {

		for (int i = 0; i < nums.length; i++) {
			int minIndex = i;

			for (int j = i + 1; j < nums.length; j++) {
				minIndex = nums[minIndex] > nums[j] ? j : minIndex;
			}
			swap(nums, minIndex, i);
		}
	}

	// =============================================================================
	// Insertion Sort
	public int[] sortArray3(int[] nums) {
		insertionSort(nums);
		return nums;
	}

	private void insertionSort(int[] nums) {

		for (int i = 0; i < nums.length - 1; i++) {

			for (int j = i + 1; j > 0; j--) {
				if (nums[j] < nums[j - 1]) {
					swap(nums, j, j - 1);
				}
			}
		}
	}

	// =============================================================================
	// Counting Sort
	public int[] sortArray4(int[] nums) {
		countingSort(nums);
		return nums;
	}

	private void countingSort(int[] nums) {
		int max = Integer.MIN_VALUE;
		int min = Integer.MAX_VALUE;

		for (int num : nums) {
			max = Math.max(max, Math.abs(num));
			min = Math.min(min, num);
		}
		// To incorporate -vs values
		int[] counts = new int[2 * max + 1];

		for (int num : nums) {
			counts[num + max]++;
		}
		int index = 0;

		for (int i = min; i <= max; i++) {
			int count = counts[i + max];

			if (count > 0) {
				while (count-- > 0) {
					nums[index++] = i;
				}
			}
		}
	}

	// ============================================================================
	// Heap Sort
	public int[] sortArray5(int[] nums) {
		heapSort(nums);
		return nums;
	}

	private void heapSort(int[] nums) {
		buildHeap(nums);
		int lo = 0;

		for (int i = nums.length - 1; i >= 0; i--) {
			swap(nums, i, lo);
			heapify(nums, lo, i - 1);
		}
	}

	private void buildHeap(int[] nums) {

		for (int i = (nums.length - 1) / 2; i >= 0; i--) {
			heapify(nums, i, nums.length - 1);
		}
	}

	// Building max Heap for Ascending. Min heap for descending
	private void heapify(int[] nums, int index, int lastIndex) {
		int leftIndex = 2 * index + 1;
		int rightIndex = 2 * index + 2;
		int max = index;

		if (leftIndex <= lastIndex) {
			max = nums[index] > nums[leftIndex] ? index : leftIndex;
		}

		if (rightIndex <= lastIndex) {
			max = nums[max] > nums[rightIndex] ? max : rightIndex;
		}

		if (max != index) {
			swap(nums, max, index);
			heapify(nums, max, lastIndex);
		}
	}

	// ============================================================================
	// Quick Sort
	public int[] sortArray6(int[] nums) {
		quickSort(nums, 0, nums.length - 1);
		return nums;
	}

	private void quickSort(int[] nums, int lo, int hi) {
		int pivot = lo + (hi - lo) / 2;
		int pivotVal = nums[pivot];
		int l = lo;
		int h = hi;

		while (l <= h) {

			while (l <= h && nums[l] < pivotVal) {
				l++;
			}

			while (l <= h && nums[h] > pivotVal) {
				h--;
			}

			if (l <= h) {
				swap(nums, l, h);
				l++;
				h--;
			}
		}

		if (l < hi) {
			quickSort(nums, l, hi);
		}

		if (lo < h) {
			quickSort(nums, lo, h);
		}
	}

	// ============================================================================
	// Merge Sort
	public int[] sortArray(int[] nums) {
		mergeSort(nums, 0, nums.length - 1);
		return nums;
	}

	private void mergeSort(int[] nums, int lo, int hi) {

		if (lo < hi) {
			int pivot = lo + (hi - lo) / 2;
			mergeSort(nums, lo, pivot);
			mergeSort(nums, pivot + 1, hi);
			merge(nums, lo, pivot, hi);
		}
	}

	private void merge(int[] nums, int lo, int pivot, int hi) {
		int[] holder = new int[hi - lo + 1];
		int index = 0;
		int lo1 = lo;
		int hi1 = pivot;
		int lo2 = pivot + 1;
		int hi2 = hi;

		while (lo1 <= hi1 && lo2 <= hi2) {

			if (nums[lo1] < nums[lo2]) {
				holder[index++] = nums[lo1++];
			} else {
				holder[index++] = nums[lo2++];
			}
		}

		while (lo1 <= hi1) {
			holder[index++] = nums[lo1++];
		}

		while (lo2 <= hi2) {
			holder[index++] = nums[lo2++];
		}
		index = lo;

		for (int num : holder) {
			nums[index++] = num;
		}
	}
}
