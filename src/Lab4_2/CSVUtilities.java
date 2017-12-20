package Lab4_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CSVUtilities {

	ArrayList<String> CSVData;
	private int numColumns;

	/**
	 * adds each row of data into the list CSVData
	 * @param csv the csv file to be read
	 */
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
	public List<Integer> getDataInt(int column, int count) {
		List<Integer> data = new ArrayList<Integer>();
		int i = 1;
		while (i < count) {
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
			data.add(dataCellInt);
			i++;
		}
		return data;
	}

	/**
	 * @param column the column index
	 * @param count the number of rows that are not null
	 * @return an ArrayList with the data converted to Double
	 */
	public Map<Integer, Double> getDataDouble(int column, int count) {
		Map<Integer, Double> indexAndData = new HashMap<Integer, Double>();
		int i = 1;
		while (i < count) {
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
	
	public String getData(int rowIdx, int columnIdx) {
		String[] row = CSVData.get(rowIdx).split(",");
		return row[columnIdx];
 	}
	
	public List<String> getData(int col, Collection<Integer> indices) {
		List<String> data = new ArrayList<String>();
		for (int i : indices) {
			data.add(getData(i, col));
		}
		return data;
 	}
}
