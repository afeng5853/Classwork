package Lab3_2;

import java.util.ArrayList;
import java.util.Arrays;

import ljthw.*;

public class ArrayMethods2 {
	public static void main (String[] args) {
		String[] test1 = {"ad", "hi", "money", "apple", "hello"};
		String[] test2 = {};
		String[] merged = merge(test1, test2);
		CopyArrays.printArray(mergeSort(test1));
		int[] list = {5, 1, 7, 8, 9, 5, 2};
		System.out.println(partition(list));
	}
	
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
	
	public static String[] mergeSort(String[] list) {
		if (list.length == 1) {
			return list;
		}
		else {
			int mid = list.length / 2;
			String[] split1 = Arrays.copyOfRange(list, 0, mid);
			String[] split2 = Arrays.copyOfRange(list, mid, list.length);
			return merge(mergeSort(split1), mergeSort(split2));
		}
	}
	
	public static int partition(int[] list) {
		int pivot = list[0];
		ArrayList<Integer> left = new ArrayList<>();
		ArrayList<Integer> right = new ArrayList<>();
		for (int i = 1; i < list.length; i++) {
			if (list[i] > pivot) {
				right.add(list[i]);
			} else {
				left.add(list[i]);
			}
		}
		list = left.addAll(right);
		return left.size();
	}
}
