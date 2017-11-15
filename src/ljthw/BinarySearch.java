package ljthw;

public class BinarySearch {
	public static void main (String[] args) {
		int[] test1 = {};
		int[] test2 = {};
		
		for (int i = -1; i < test1.length + 1; i++) {
			System.out.println(binarySearch(test1, i, 0, test1.length));
		}
		for (int i = -1; i < test1.length + 1; i++) {
			System.out.println(binarySearch(test2, i, 0, test2.length));
		}
	}
	
	public static int binarySearch(int[] nums, int query, int first, int last) {
		if (last > first) {
			int index=  (first + last - 1) / 2;
			int guess = nums[index];
			if (guess == query) {
				return index;
			}
			if (guess > query) {
				return binarySearch(nums, query, first, index);
			}
			return binarySearch(nums, query, index + 1, last);
		}
		return -1;
	}
}
