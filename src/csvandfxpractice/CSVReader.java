package csvandfxpractice;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVReader {
	public static List<List<String>> read(String fileName) {
		List<List<String>> data = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line;
			while ((line = br.readLine()) != null) {
				List<String> row = Arrays.asList(line.split(","));
				data.add(row);
			}
		} catch (IOException ioe ){
			ioe.printStackTrace();
		}
		return data;
	}
	
	public static void append(String fileName, String line) {
		try (FileWriter pw = new FileWriter(Paths.get(fileName).toString(), true);) {
			for (String s : line.split(",")) {
				pw.append(s);
				pw.append(',');
			}
			pw.append('\n');
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
