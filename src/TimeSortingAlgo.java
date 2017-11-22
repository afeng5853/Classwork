import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import java.util.function.Function;

import CountingSort.CountingSort;
import Lab3_2.ArrayMethods2;
import Lab3_3.ArrayMethods3;
import QuickSort.ArrayMethod4;

public class TimeSortingAlgo {
	public static void main(String[] args) {
		int maxInt = 100000;
		int iterations = 100000;
		System.out.println("Random integer test 0-255, iterations " + iterations + " for counting and merge");
		int[] test = generateRandomIntArr(100000, maxInt);
		int[] test2 = Arrays.copyOfRange(test, 0, test.length);
		int[] test3 = Arrays.copyOfRange(test2, 0, test.length);
		BigInteger countingSortTime = calculateTime(test, CountingSort::countingSort, 50);
		System.out.println("Counting sort: " + countingSortTime);
		
		BigInteger mergeSortTime = calculateTime(test, TimeSortingAlgo::mergeSort, 50);
		System.out.println("Merge sort: " + mergeSortTime);
		
		
		
		long time = System.nanoTime();
		Arrays.sort(test);
	    System.out.println("Array.sort: " + (System.nanoTime() - time));	
		
		long time2 = System.nanoTime();
		ArrayMethod4.quickSort(test2, 0, test.length);
	    System.out.println("Quick sort: " + (System.nanoTime() - time2));
	    
	    
	    long time3 = System.nanoTime();
		ArrayMethods3.insertionSort(test3);
	    System.out.println("Insertion sort: " + (System.nanoTime() - time3));
	    
	    long time4 = System.nanoTime();
		ArrayMethods3.selectionSort(test3);
	    System.out.println("Selection sort: " + (System.nanoTime() - time4));
	    
	    long time5 = System.nanoTime();
		ArrayMethods3.bubbleSort(test3);
	    System.out.println("Bubble sort: " + (System.nanoTime() - time5));
	}
	
	public static int[] generateRandomIntArr(int size, int maxInt) {
		Random r = new Random();
		int[] test = new int[size];
		for (int i = 0; i < test.length; i++) {
			test[i] = r.nextInt(maxInt);
		}
		return test;
	}
	
	public static BigInteger calculateTime(int[] a, Function<int[],int[]> func, int iterations) {
		BigInteger sum = BigInteger.valueOf(0);
		for (int i = 0; i < iterations; i++) {
			long time = System.nanoTime();
			func.apply(a);
			sum = sum.add(BigInteger.valueOf((System.nanoTime() - time)));
		}
		return sum.divide(BigInteger.valueOf(iterations));
	}
	
	
	public static int[] merge(int[] list1, int[] list2) {
		int[] result = new int[list1.length + list2.length];
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
			else if (list1[i] < list2[j]) {
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
	
	public static int[] mergeSort(int[] list) {
		if (list.length <= 1) {
			return list;
		}
		else {
			int mid = list.length / 2;
			int[] split1 = Arrays.copyOfRange(list, 0, mid);
			int[] split2 = Arrays.copyOfRange(list, mid, list.length);
			return merge(mergeSort(split1), mergeSort(split2));
		}
	}
}
