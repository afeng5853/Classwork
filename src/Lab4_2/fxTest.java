package Lab4_2;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class fxTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
  /*  public static Text[][] asTextMatrix(List<List<Object>> matrix) {
    	Text[][] textMatrix = new Text[matrix.size()][];
    	int i = 0;
    	for (List<Object> row : matrix) {
    		int j = 0;
    		Text[] textRow = new Text[row.size()];
			for (Object s : row) {
				textRow[j++] = new Text(String.valueOf(s));
			}
			textMatrix[i++] = textRow;
		}
    	return textMatrix;
    }
    
    public static void addToGrid (GridPane grid, Text[][] matrix) {
    	for (int i = 0; i < matrix.length; i++) {
    		for (int j = 0; j < matrix[i].length; j++) {
    			boolean filled = false;
    			for (Node element : grid.getChildren()) {
    				if (GridPane.getRowIndex(element) == i && GridPane.getColumnIndex(element) == j) {
    					filled = true;
    				}
    			}
    			if (filled) {
    				continue;
    			}
    			StackPane t = new StackPane(matrix[i][j]);
    			t.getStyleClass().add("text-box");
    			grid.add(t, j, i);
    		}
    	}
    }*/
    
    /**
     * Gets graduation rates of ethnicity by year
     * @param csv
     * @param gradWithIndex key: index value: value
     * @return graduation rates of ethnicity by year
     */
    private Map<String, Double> getAverageGradRates(CSVUtilities csv, Map<Integer, Integer> gradWithIndex) {
    	Map<String, Double> ethnicityToGrad = new HashMap<String, Double>();
    	Map<String, Integer> ethnicityToCohort = new HashMap<String, Integer>();
    	for (Map.Entry<Integer, Integer> entry : gradWithIndex.entrySet())
    	{
    		// key as Ethnicity + " " + Year e.g. "Hispanic 2001"
    		String ethnicityYear = csv.getData(entry.getKey(), 4) + " " + csv.getData(entry.getKey(), 2);
    		if (ethnicityToGrad.containsKey(ethnicityYear)) {
    			ethnicityToGrad.put(ethnicityYear, ethnicityToGrad.get(ethnicityYear) + entry.getValue());
    			int cohortAmount = Integer.parseInt(csv.getData(entry.getKey(), 5));
    			ethnicityToCohort.put(ethnicityYear, ethnicityToCohort.get(ethnicityYear) + cohortAmount);
    		} else {
    			ethnicityToGrad.put(ethnicityYear, (double) entry.getValue());
    			int cohortAmount = Integer.parseInt(csv.getData(entry.getKey(), 5));
    			ethnicityToCohort.put(ethnicityYear, cohortAmount);
    		}
    	}
    	for (Map.Entry<String, Double> entry : ethnicityToGrad.entrySet())
    	{
    		String ethnicityYear = entry.getKey();
    		ethnicityToGrad.put(ethnicityYear, entry.getValue() / ethnicityToCohort.get(ethnicityYear));
    	}
    	return ethnicityToGrad;
    }
    
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        File grad_rates = new File("2005-2011_Grad_Rates.csv");
		CSVUtilities csv = new CSVUtilities(grad_rates);
		
		Map<Integer, Integer> grads = csv.getDataInt(6, 14250);
		
		Map<String, Double> gradRateByEthnicity = getAverageGradRates(csv, grads);
        
        final NumberAxis xAxis = new NumberAxis(2000, 2008, 1);
        final NumberAxis yAxis = new NumberAxis(0.3, 1.0, 0.1);
        xAxis.setLabel("Year");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("NYC graduation rate by ethnicity, 2001-2007");
        //defining a series
        XYChart.Series hispanic = new XYChart.Series();
        XYChart.Series asian = new XYChart.Series();
        XYChart.Series white = new XYChart.Series();
        XYChart.Series black = new XYChart.Series();
        hispanic.setName("Hispanic");
        asian.setName("Asian");
        white.setName("White");
        black.setName("Black");
        for (Map.Entry<String, Double> entry : gradRateByEthnicity.entrySet()) {
        	String[] ethnicityAndYear = entry.getKey().split(" ");
        	String ethnicity = ethnicityAndYear[0];
        	String year = ethnicityAndYear[1];
        	switch (ethnicity) {
        		
		    	case "Hispanic": {
		    		hispanic.getData().add(new XYChart.Data<>(Integer.parseInt(year), entry.getValue()));
		    		break;
		    	}
		    	case "White": {
		    		white.getData().add(new XYChart.Data<>(Integer.parseInt(year), entry.getValue()));
		    		break;
		    	}
		    	case "Asian": {
		    		asian.getData().add(new XYChart.Data<>(Integer.parseInt(year), entry.getValue()));
		    		break;
		    	}
		    	case "Black": {
		    		black.getData().add(new XYChart.Data<>(Integer.parseInt(year), entry.getValue()));
		    		break;
		    	}
		    	default: {
		    		System.out.println("ERROR");
		    	}
        	}
        	
        }
        lineChart.getData().add(hispanic);
        lineChart.getData().add(asian);
        lineChart.getData().add(white);
        lineChart.getData().add(black);
        
        BorderPane bp = new BorderPane();
        bp.setCenter(lineChart);
        primaryStage.setScene(new Scene(bp, 800, 600));
        primaryStage.show();
    }
}