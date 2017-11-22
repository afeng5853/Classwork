package QuickSort;

import java.util.HashMap;
import java.util.Random;
import ljthw.CopyArrays;

/**
 * 
 * @author Alex Feng, Raymond Cheung
 *
 */
public class ArrayMethod4 {
	
	public static void main(String[] args) {
		Random r = new Random();
		int[] arr = new int[] {r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000), r.nextInt(1000)};
		// pre-sorted Array
		int[] span = new int[] {1, 4, 2, 1, 4, 4, 4};
		/*CopyArrays.printArray(arr);
		quickSort(arr, 0, arr.length);
		//sorted Array
		 */
		long time = System.nanoTime();
		CopyArrays.printArray(arr);
		System.out.println(maxSpan(span));
	    System.out.println(System.nanoTime() - time);

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
	
	public static int maxSpan(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		  HashMap<Integer, int[]> map = new HashMap<>();
		  boolean duplicate = false;
		  for (int i = 0; i < nums.length; i++) {
		    int[] val = map.get(nums[i]);
		    if (val == null) {
		    	map.put(nums[i], new int[] {i, 1});
		    } else {
		    	duplicate = true;
		      int idx = val[0];
		      int num = val[1];
		      if (i - idx > num) {
		    	  map.put(nums[i], new int[] {idx, i - idx});
		      }
		    }
		  }
		  if (!duplicate) {
			  return 1; 
		  }
		  int max = 0;
		  for (int[] value : map.values()) {
			  if (value[1] > max) {
				  max = value[1];
			  }
		  }
		  return max + 1;
		}

}
