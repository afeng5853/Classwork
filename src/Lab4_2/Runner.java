package Lab4_2;

import java.io.File;
import java.util.List;

/**
 * tests CSVUtilities
 * @author Alex Feng
 * @since 12/19/2017
 */
public class Runner {
	public static void main(String[] args) {
		File grad_rates = new File("2005-2011_Grad_Rates.csv");
		CSVUtilities csv = new CSVUtilities(grad_rates);
		
		// test headers
		List<String> headers = csv.getColumnHeaders();
		for (String header : headers) {
			System.out.print(header + " ");
		}
		System.out.println();
		
		// test getDataString
		List<String> demographics = csv.getDataString(4);
		for (String demographic : demographics) {
			System.out.println(demographic);
		}
		
		// test getDataInt
		List<Integer> totalCohort = csv.getDataInt(5);
		for (Integer cohort : totalCohort) {
			System.out.println(cohort);
		}
		
		// test getDataDouble
		List<Double> regentsGradRates = csv.getDataDouble(7);
		for (Double gradRate : regentsGradRates) {
			System.out.println(gradRate);
		}
	}

}
