package Lab4_2;

import java.io.File;
import java.util.ArrayList;
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
    
    public static Text[][] asTextMatrix(List<List<Object>> matrix) {
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
    }
    
    private Map<String, Double> getAverageGradRates(CSVUtilities csv, Map<Integer, Double> gradRateWithIndex) {
    	Map<String, Double> ethnicityToGradRate = new HashMap<String, Double>();
    	Map<String, Integer> ethnicityLengths = new HashMap<String, Integer>();
    	for (Map.Entry<Integer, Double> entry : gradRateWithIndex.entrySet())
    	{
    		String ethnicityYear = csv.getData(entry.getKey(), 5) + " " + csv.getData(entry.getKey(), 3);
    		if (ethnicityToGradRate.containsKey(ethnicityYear)) {
    			ethnicityToGradRate.put(ethnicityYear, ethnicityToGradRate.get(ethnicityYear) + entry.getValue());
    			ethnicityLengths.put(ethnicityYear, ethnicityLengths.get(ethnicityYear) + 1);
    		} else {
    			ethnicityToGradRate.put(ethnicityYear, entry.getValue());
    			ethnicityLengths.put(ethnicityYear, 1);
    		}
    	}
    	for (Map.Entry<String, Double> entry : ethnicityToGradRate.entrySet())
    	{
    		String ethnicityYear = entry.getKey();
    		ethnicityToGradRate.put(ethnicityYear, entry.getValue() / ethnicityLengths.get(ethnicityYear));
    	}
    	return ethnicityToGradRate;
    }
    
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        File grad_rates = new File("2005-2011_Grad_Rates.csv");
		CSVUtilities csv = new CSVUtilities(grad_rates);
		
		Map<Integer, Double> gradRateWithIndex = csv.getDataDouble(7, 200);
		Map<String, Double> gradRateByEthnicity = getAverageGradRates(csv, gradRateWithIndex);
        
        final NumberAxis xAxis = new NumberAxis(2004, 2012, 1);
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Year");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Graduation rate by ethnicity, 2005-2011");
        //defining a series
        XYChart.Series hispanic = new XYChart.Series();
        XYChart.Series asian = new XYChart.Series();
        XYChart.Series white = new XYChart.Series();
        XYChart.Series black = new XYChart.Series();
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
        primaryStage.setScene(new Scene(bp, 300, 250));
        primaryStage.show();
    }
}