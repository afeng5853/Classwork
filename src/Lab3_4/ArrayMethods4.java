package Lab3_4;

public class ArrayMethods4 {

	public static void main(String[] args) {
		
	}

	/**
	 * Partitions an array using the first item in the array. 
	 * @param list the array to be partitioned.
	 * @param a the start index
	 * @param b the end index
	 * @return returns the index of the pivot
	 */

	public static int partition(int[] list, int a, int b) {
		// if list is empty
		if (list.length == 0) {
			return -1;
		}
		int pivot = list[a]; // set pivot to first item
		int j = a; // current index to swap for items less than the pivot
		int pivotIdx = a;
		for (int i = a + 1; i < b; i++) {
			if (list[i] <= pivot) {
				// track pivot index 
				if (pivotIdx == j) {
					pivotIdx = i;
				}
				// swap pivot to latest index in the small partition
				int temp = list[j];
				list[j] = list[i];
				list[i] = temp;
				j++;
			}
			
		}
		// return pivot to correct location
		int temp = list[pivotIdx];
		list[pivotIdx] = list[j];
		list[j] = temp;
		return j;
	}

	/**
	 * Sorts an array using quick sort
	 * @param arr the array to be sorted
	 * @param i the start index
	 * @param j the end index
	 */
	
	public static void quickSort(int[] arr, int i, int j) {
		if (j - i <= 1) {
			return; 
		}
		else {
			//CopyArrays.printArray(arr);
			int pivotIdx = partition(arr, i, j);
			quickSort(arr, i, pivotIdx);
			quickSort(arr, pivotIdx + 1, j);
		}
	}
	
	/**
	 * Sorts an array of integers using counting sort
	 * 
	 * Pros: O(n) for integers
	 * Cons: A limited range of integers has to be specified
	 * 		 Range of integers is limited by memory
	 * 
	 * For the first iteration, record the number of occurrences of an integer in an integer array with the key being the number
	 * and the value being the number of occurrences
	 * 
	 * For the second iteration, insert each number starting from 0 to n (where n is the max) with the respective number of occurrences
	 * @param arr
	 * @return
	 */
	public static int[] customSort(int[] arr) {
		int[] stored = new int[65536];
		int[] result = new int[arr.length];
		for (int i = 0; i < arr.length; i++) {
			int num = arr[i];
			stored[num]++;
		}
		int resultIdx = 0;
		for (int i = 0; i < stored.length; i++) {
			while (stored[i] > 0) {
				result[resultIdx++] = i;
				stored[i]--;
			}
		}
		return result;
	}
}
