package Lab3_1;

import java.util.ArrayList;
import java.util.HashMap;

import ljthw.CopyArrays;

/**
 * Lab 3.1
 * <p> Contains useful array methods <p>
 * @author Alex Feng, Raymond Cheung
 * @since 11/9/2017
 */

public class ArrayMethods {

	public static void main(String[] args) {
		/*
		int[] test = {2, 3, 5, 6, 7, 7, 8, 8, 9};
		int[] test2 = {2, 3, 5, 6, 7, 7, 8, 8, 9};
		int[] testnodupe = removeDuplicates(test);
		CopyArrays.printArray(testnodupe);
		//int[][] productArr = productArray(test, test2);
		//CopyArrays.print2DArray(productArr);
		int[][] pascals = pascalTriangle(6);
		printPascalTriangle(pascals);
		*/
	}
	
	/**
	 * Returns a new array containing unique values of the original array.
	 * @param  list	the array to be checked for duplicates
	 * @return  	the array without duplicates
	 */

	public static int[] removeDuplicates(int[] list) {
		boolean hasZero = false; // Since uninitialized vars in an int array becomes 0, we need to see if the array had a zero originally
		int[] ansMedium = new int[list.length]; // container of duplicate values + null (0) if duplicates
		int size = 0; // size of non-duplicate array
		
		for (int i = 0; i < list.length; i++) {
			boolean duplicate = false; // used to flag whether or not to add to non-duplicate array
			for (int j = i+1; j < list.length; j++) {
				// if it's duplicate, don't add it to the non-duplicate array and continue
				if (list[i] == list[j]) {
					duplicate = true;
					break;
				}
			}
			if (!duplicate) {
				if (list[i] == 0) {
					hasZero = true;
				}
				ansMedium[i] = list[i]; // fills the array with non-duplicate + zeros if the index for list is a duplicate
				size++;
			}
		}
		int[] ans = new int[size];
		int populateIdx = 0; // index counter for non-duplicate array
		for (int i = 0; i < ansMedium.length; i++) {
			if (ansMedium[i] != 0) {
				ans[populateIdx] = ansMedium[i];
				populateIdx++;
			}
		}
		
		// add back zero if it was in the original array
		if (hasZero) {
			ans[populateIdx+1] = 0;
		}

		return ans;
	}

	/**
	 * Creates a multiplication table using a multidimensional array containing the products of each combination of elements
	 * @param arr1 array containing the multiplicands
	 * @param arr2 array containing the multipliers
	 * @return     array containing the products of the multiplicands and multipliers
	 */
	
	public static int[][] productArray(int[] arr1, int[] arr2) {
		// create a multidimensional array with row and column equal to arr1.length and arr2.length respectively
		int[][] ans = new int[arr1.length][arr2.length];
		
		for (int i = 0; i < arr1.length; i++) {
			for (int j = 0; j < arr2.length; j++) {
				ans[i][j] = arr1[i] * arr2[j];
			}
		}
		return ans;
	}
	
	/**
	 * Creates Pascal's triangle with specified number of rows
	 * @param   n	the row of Pascal's triangle to be builded up to
	 * @return	    a jagged array containing Pascal's triangle up to row n
	 */
	
	public static int[][] pascalTriangle(int n) {
		int[][] ans = new int[n][];
		for (int i = 0; i < n; i++) {
			ans[i] = new int[i+1];
			for (int j = 0; j <= i; j++) {
				if (j == 0 || j == i) {
					ans[i][j] = 1;
				} else {
					ans[i][j] = ans[i-1][j-1] + ans[i-1][j];
				}
			}
		}
		return ans;
	}
	
	/**
	 * Prints Pascal's triangle
	 * @param triangle pascals triangle as a jagged array
	 */
	
	public static void printPascalTriangle(int[][] triangle) {
		for (int i = 0; i < triangle.length; i++) {
			int padding = triangle.length - i - 1;
			StringBuilder spacePadding = new StringBuilder();
			for (int p = 0; p < padding; p++) {
				spacePadding.append(' ');
			}
			System.out.print(spacePadding);
			for (int j = 0; j < triangle[i].length; j++) {
				System.out.print(triangle[i][j] + " ");
			}
			System.out.println();
		}
	}
}
