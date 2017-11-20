package Lab3_3;

import ljthw.CopyArrays;

/**
 * Lab 3.3 - In Place Sorts
 * Useful sorting methods
 * @author BT_1N3_15
 */

public class ArrayMethods3 {
	public static void main(String[] args) {
		double[] arr = new double[] {20, 12, 7, 4, 200, 100, 300, 150, 20, 10, 30, 40, 10, 5, 3};
		String[] arrString = new String[] {"ab", "cd", "aa", "gg", "lol", "bb", "ff", "cd"};
		bubbleSort(arrString);
		CopyArrays.printArray(arrString);
	}
	
	/**
	 * Swaps two objects in an array
	 * @param list the array with the objects
	 * @param i the object to be swapped
	 * @param j them object to be swapped
	 */
	
	private static void swap(Object[] list, int i, int j) {
		Object temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	
	/**
	 * Swaps two integers in an array
	 * @param list the array with the integers
	 * @param i the integer to be swapped
	 * @param j them integer to be swapped
	 */
	
	private static void swap(int[] list, int i, int j) {
		int temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	
	/**
	 * Swaps two doubles in an array
	 * @param list the array with the doubles
	 * @param i the double to be swapped
	 * @param j them double to be swapped
	 */
	
	private static void swap(double[] list, int i, int j) {
		double temp = list[i];
		list[i] = list[j];
		list[j] = temp;
	}
	
	/**
	 * Sorts an array using insertion sort
	 * @param list1 the array to be sorted
	 */
	
	public static void insertionSort(int[] list1) {
		for (int i = 1; i < list1.length; i++) {
			int j = i-1;
			int k = i;
			while (j != -1 && list1[k] < list1[j]) {
				swap(list1, k, j);
				j--;
				k--;
			}
		}
	}
	
	/**
	 * Returns the minimum value in an interval of an array
	 * @param list the array to be searched through
	 * @param start the beginning index
	 * @param end the end index
	 * @return the minimum integer of an array in a set interval
	 */
	private static int min(double[] list, int start, int end) {
		if (list.length == 0) {
			return -1;
		}
		double min = list[start];
		int minIdx = start;
		for (int i = start + 1; i < end; i++) {
			if (list[i] < min) {
				min = list[i];
				minIdx = i;
			}
		}
		return minIdx;
	}
	
	/**
	 * Sorts an array using selection sort
	 * @param list1 the array to be sorted
	 */
	public static void selectionSort(double [] list1) {
		for (int i = 0; i < list1.length; i++) {
			int minIdx = min(list1, i, list1.length);
			swap(list1, i, minIdx);
		}
	}
	/**
	 * Sorts an array using bubble sort
	 * @param list1 the array to be sorted
	 */
	public static void bubbleSort(String [] list1) {
		int swaps = 0;
		for (int i = 1; i < list1.length; i++) {
			if (i == list1.length - 1) {
				if (swaps == 0) {
					break;
				} else {
					swaps = 0;
					i = 1;
				}
			}
			if (list1[i].compareTo(list1[i-1]) < 0) {
				swap(list1, i, i-1);
				swaps++;
			}
		}
	}
}
