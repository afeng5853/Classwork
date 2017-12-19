package Lab4_2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		List<String> headers = new ArrayList<String>();
		String[] headerRow = CSVData.get(0).split(",");
		for (int i = 0; i < headerRow.length; i++) {
			headers.add(headerRow[i]);
		}
		return headers;
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
				// ignore
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
				// ignore
			} finally {
				data.add(dataCellDouble);
			}
		}
		return data;
	}


}
