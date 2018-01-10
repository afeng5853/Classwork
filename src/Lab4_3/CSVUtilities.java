package Lab4_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Includes CSV related functions such as reading and writing
 * indices start at 0 for csv files
 * @author fenga
 */
public class CSVUtilities {

	private ArrayList<String> CSVData;
	private int numColumns;
	private String fileName;
	

	public ArrayList<String> getCSVData() {
		return CSVData;
	}

	/**
	 * adds each row of data into the list CSVData
	 * @param csv the csv file to be read
	 */
	public CSVUtilities(File csv) {
		this.fileName = csv.getName();
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
	}

	/**
	 * @return an ArrayList with the headers for each column
	 */
	public List<String> getColumnHeaders() {
		return Arrays.asList(CSVData.get(0).split(","));
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

	/**
	 * @param column the column index
	 * @return an ArrayList with the data converted to Integer
	 */
	public List<Integer> getDataInt(int column) {
		List<Integer> data = new ArrayList<Integer>();
		for (int i = 1; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split(",");
			String dataCellString = row[column];
			Integer dataCellInteger = null;
			try {
				dataCellInteger = Integer.parseInt(dataCellString);
			} catch(NumberFormatException e) {
				continue;
			} finally {
				data.add(dataCellInteger);
			}
		}
		return data;
	}

	/**
	 * @param column the column index
	 * @return an ArrayList with the data converted to Double
	 */
	public List<Double> getDataDouble(int column) {
		List<Double> data = new ArrayList<Double>();
		for (int i = 1; i < CSVData.size(); i++) {
			String[] row = CSVData.get(i).split(",");
			String dataCellString = row[column];
			Double dataCellDouble = null;
			try {
				dataCellDouble = Double.parseDouble(dataCellString);
			} catch(NumberFormatException e) {
				continue;
			} finally {
				data.add(dataCellDouble);
			}
		}
		return data;
	}

	/**
	 * @param column the column index
	 * @param count the number of rows
	 * @return an ArrayList with the data for a column specified
	 */
	public List<String> getDataString(int column, int count) {
		List<String> data = new ArrayList<String>();
		for (int i = 1; i < count; i++) {
			String[] row = CSVData.get(i).split(",");
			data.add(row[column]);
		}
		return data;
 	}
	
	/**
	 * @param column the column index
	 * @param count the number of rows that are not null
	 * @return an ArrayList with the data converted to Integer
	 */
	public Map<Integer, Integer> getDataInt(int column, int count) {
		Map<Integer, Integer> indexAndData = new HashMap<Integer, Integer>();
		int i = 1;
		while (i < count && i < CSVData.size()) {
			String[] row = CSVData.get(i).split(",");
			String dataCellString = row[column];
			Integer dataCellInt = null;
			try {
				dataCellInt = Integer.parseInt(dataCellString);
			} catch(NumberFormatException e) {
				i++;
				count++;
				continue;
			}
			indexAndData.put(i, dataCellInt);
			i++;
		}
		return indexAndData;
	}

	/**
	 * @param column the column index
	 * @param count the number of rows that are not null
	 * @return an ArrayList with the data converted to Double
	 */
	public Map<Integer, Double> getDataDouble(int column, int count) {
		Map<Integer, Double> indexAndData = new HashMap<Integer, Double>();
		int i = 1;
		while (i < count && i < CSVData.size()) {
			String[] row = CSVData.get(i).split(",");
			String dataCellString = row[column];
			Double dataCellDouble = null;
			try {
				dataCellDouble = Double.parseDouble(dataCellString);
			} catch(NumberFormatException e) {
				i++;
				count++;
				continue;
			}
			indexAndData.put(i, dataCellDouble);
			i++;
		}
		return indexAndData;
	}
	
	/**
	 * Get data at specified row and column
	 * @param rowIdx
	 * @param columnIdx
	 * @return the data at a specified row and column
	 */
	public String getData(int rowIdx, int columnIdx) {
		String[] row = CSVData.get(rowIdx).split(",");
		return row[columnIdx];
 	}
	
	/**
	 * Get data based on a list of indices
	 * @param col
	 * @param indices
	 * @return a list of strings of the data in the csv
	 */
	public List<String> getData(int col, Collection<Integer> indices) {
		List<String> data = new ArrayList<String>();
		for (int i : indices) {
			data.add(getData(i, col));
		}
		return data;
 	}
	
	/**
	 * Adding score while maintaining sorted structure of CSV
	 * @param name 
	 * @param score
	 */
	public void save(String name, int score) {
		List<Integer> scores = getDataInt(1);
		int lineNumToWrite = scores.size() + 1;
		// find line num to add by ascending order
		for (int i = 0; i < scores.size(); i++) {
			if (score > scores.get(i)) {
				lineNumToWrite = i + 1;
				break;
			}
		}
		if (lineNumToWrite >= CSVData.size()) {
			CSVData.add(name + "," + score);
		} else {
			CSVData.add(lineNumToWrite, name + "," + score);
		}
		try {
			Files.write(Paths.get(this.fileName), CSVData);
		} catch (IOException e) {
			e.printStackTrace();
		}
 	}
}
