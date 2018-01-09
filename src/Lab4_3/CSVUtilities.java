package Lab4_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CSVUtilities {
	ArrayList<String> CSVData;
	private int numColumns;
	File file;

	public CSVUtilities(File csv) {
		CSVData = new ArrayList<String>();
		try (BufferedReader input = new BufferedReader(new FileReader(csv))) {
			String line;
			while ((line = input.readLine()) != null) {
				CSVData.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.numColumns = getColumnHeaders().size();
		this.file = csv;
	}
	
	public List<String> getColumnHeaders() {
		return Arrays.asList(CSVData.get(0).split(","));
	}
	
	public static List<String> read(String fileName) {
		List<String> data = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
		} catch (IOException ioe ){
			ioe.printStackTrace(); 
		}
		return data;
	}
	
	public void append(String line) {
		try (FileWriter pw = new FileWriter(this.file, true);) {
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
	
	public void saveScore(ScoreTracker score) {
		append(score.getPlayer() + ", " + score.getScore());
	}
	

	/**
	 * @param column the column index
	 * @return an ArrayList with the data for a column specified
	 */
	public List<String> getDataString(int column) {
		List<String> data = new ArrayList<String>();
		for (int i = 1; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split(",");
			data.add(row[column]);
		}
		return data;
 	}
	
	public List<String> getTopTen(int column){
		List<String> data = getDataString(column);
		data.sort(Comparator.naturalOrder());
		return data;
	}

}
