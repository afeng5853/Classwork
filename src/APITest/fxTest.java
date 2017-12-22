package APITest;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONException;
import org.json.JSONObject;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
 
public class fxTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public static JSONObject getBitcoinData() {
    	JSONObject data = null;
    	try {
			data = JSONReader.readJsonFromUrl("https://api.coinmarketcap.com/v1/ticker/ethereum/");
		} catch (IOException | JSONException e) {
			e.printStackTrace();
		}
    	return data;
    }
    
    public static Object getBitcoinPrice() {
    	try {
			return getBitcoinData().get("price_usd");
		} catch (JSONException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    @Override
    public void start(Stage primaryStage) {
    	long startTime = System.currentTimeMillis();
    	double firstPrice = Double.parseDouble(getBitcoinPrice().toString());

    	
    	final NumberAxis xAxis = new NumberAxis();
        final NumberAxis yAxis = new NumberAxis(firstPrice-10, firstPrice+10, 1);
        
        xAxis.setLabel("Time since start (ms)");
        //creating the chart
        final LineChart<Number,Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
        lineChart.setTitle("Ethereum Price (USD)");
        //defining a series
        XYChart.Series price = new XYChart.Series();
        lineChart.getData().add(price);
        BorderPane bp = new BorderPane();
        bp.setCenter(lineChart);
        primaryStage.setScene(new Scene(bp, 800, 600));
        primaryStage.show();
        price.setName("Ethereum");
        
    	//price.getData().add(new XYChart.Data<>(System.currentTimeMillis(), firstPrice));
        final Timeline timeline = new Timeline(
        	    new KeyFrame(
        	        Duration.millis(5000),
        	        event -> {
        	        	price.getData().add(new XYChart.Data<>((System.currentTimeMillis() - startTime), Double.parseDouble(getBitcoinPrice().toString())));
        	        }
        	    )
        	);
    	timeline.setCycleCount( Animation.INDEFINITE );
    	timeline.play();
    	
    	
        /*primaryStage.setTitle("Hello World!");
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
        primaryStage.show();*/
    }
}