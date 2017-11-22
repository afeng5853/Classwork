package CountingSort;

import java.util.HashMap;
import java.util.Random;

import ljthw.CopyArrays;
import QuickSort.*;

public class CountingSort {
	public static void main(String[] args) {
		Random r = new Random();
		int[] test = new int[] {r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100), r.nextInt(100)};
		long time = System.nanoTime();
		int[] ans = countingSort(test);
		//CopyArrays.printArray(ans);
	    System.out.println(System.nanoTime() - time);
	    
		time = System.nanoTime();
		ArrayMethod4.quickSort(test, 0, test.length);
	    System.out.println(System.nanoTime() - time);
	}
	public static int[] countingSort(int[] arr) {
		int[] stored = new int[100000];
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
