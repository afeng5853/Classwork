package Lab3_2;

import java.util.Arrays;

import ljthw.*;

/**
 * Lab 3.2
 * <p> Contains useful sorting array methods <p>
 * @author Alex Feng, Raymond Cheung
 * @since 11/14/2017
 */
public class ArrayMethods2 {
	public static void main (String[] args) {
		String[] test1 = {"test", "zebra", "lol", "year"};
		String[] test2 = {};
		String[] merged = merge(test1, test2);
		CopyArrays.printArray(mergeSort(test1));
		int[] list = {};
		System.out.println(partition(list));
		CopyArrays.printArray(list);
	}
	
	/**
	 * merges two sorted arrays and sorts it
	 * @param list1 a sorted array
	 * @param list2 a sorted array
	 * @return a sorted merged array
	 */
	public static String[] merge(String[] list1, String[] list2) {
		String[] result = new String[list1.length + list2.length];
		int i = 0; // list 1 index
		int j = 0; // list 2 index
		int resultIdx = 0;
		while (i < list1.length || j < list2.length) {
			if (i == list1.length) {
				result[resultIdx] = list2[j];
				j++;
			}
			else if (j == list2.length) {
				result[resultIdx] = list1[i];
				i++;
			}
			else if (list1[i].compareTo(list2[j]) < 0) {
				result[resultIdx] = list1[i];
				i++;
			}
			else {
				result[resultIdx] = list2[j];
				j++;
			}
			resultIdx++;
		}	
		
		return result;
	}
	
	/**
	 * sorts array based on the merge sort algorithm
	 * @param list the array to be sorted
	 * @return a sorted array
	 */
	public static String[] mergeSort(String[] list) {
		if (list.length <= 1) {
			return list;
		}
		else {
			int mid = list.length / 2;
			String[] split1 = Arrays.copyOfRange(list, 0, mid);
			String[] split2 = Arrays.copyOfRange(list, mid, list.length);
			return merge(mergeSort(split1), mergeSort(split2));
		}
	}
	
	/**
	 * The pivot is set to the first item and all of the others items are partitioned based on that pivot
	 * If it's less than or equal to, it is partitioned on the left side
	 * If it's on the right than, it is partition on the right side
	 * @param list to be partitioned
	 * @return the index of the pivot
	 */
	public static int partition(int[] list) {
		// if list is empty
		if (list.length == 0) {
			return -1;
		}
		int pivot = list[0]; // set pivot to first item
		int j = 0; // current index to swap for items less than the pivot
		int pivotIdx = 0;
		for (int i = 1; i < list.length; i++) {
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
}
