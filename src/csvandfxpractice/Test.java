package csvandfxpractice;

import java.util.List;

public class Test {
	public static void main(String[] args) {
		List<List<String>> out = CSVReader.read("test.csv");
		for (List<String> row : out) {
			for (String s : row) {
				System.out.print(s + " .");
			}
			System.out.println();
		}
		CSVReader.append("test.csv", "alex, 100000");
	}
}
