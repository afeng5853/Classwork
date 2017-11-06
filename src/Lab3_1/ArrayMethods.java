package Lab3_1;

import java.util.ArrayList;
import java.util.HashMap;

public class ArrayMethods {
	public static void main(String[] args) {
		int[] test = {2, 3, 5, 6, 7, 7, 8, 8, 9};
		int[] testnodupe = removeDuplicates(test);
		for (int i = 0; i < testnodupe.length; i++) {
			System.out.println(testnodupe[i]);
		}
	}
	
	public static int[] removeDuplicates(int[] list) {
		ArrayList<Integer> ansMedium = new ArrayList<>();
		HashMap<Integer, Boolean> table = new HashMap<>();
		for (int i = 0; i < list.length; i++) {
			if (!table.containsKey(list[i])) {
				ansMedium.add(list[i]);
			}
			table.put(list[i], true);
		}
		int[] ans = new int[ansMedium.size()];
		for (int i = 0; i < ans.length; i++) {
			ans[i] = ansMedium.get(i);
		}
		return ans;
	}
	public static int[][] productArray(int[] arr1, int[] arr2) {
		return null;
		
	}
	public static int[ ][ ] pascalTriangle(int n) {
		return null;
		
	}
	public static void printPascalTriangle(int[ ] [ ] triangle) {
		
	}
}
