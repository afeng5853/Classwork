package csvandfxpractice;

import java.util.List;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
 
public class fxTest extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    
    public static Text[][] asTextMatrix(List<List<String>> matrix) {
    	Text[][] textMatrix = new Text[matrix.size()][];
    	int i = 0;
    	for (List<String> row : matrix) {
    		int j = 0;
    		Text[] textRow = new Text[row.size()];
			for (String s : row) {
				textRow[j++] = new Text(s);
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
    
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        List<List<String>> out = CSVReader.read("test.csv");
        Text[][] textMatrix = asTextMatrix(out);
        GridPane grid = new GridPane();
        addToGrid(grid, textMatrix);
        
        GridPane inputs = new GridPane();
        Button add = new Button("Add");
        TextField name = new TextField();
        TextField score = new TextField();

        add.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent event) {
        		CSVReader.append("test.csv", name.getText() + ", " + score.getText());
        		List<List<String>> out = CSVReader.read("test.csv");
    	        Text[][] textMatrix = asTextMatrix(out);
    	        addToGrid(grid, textMatrix);
        	}
        });
        inputs.add(new Text("Name: "), 0, 0);
        inputs.add(new Text("Score: "), 0, 1);
        inputs.add(name, 1, 0);
        inputs.add(score, 1, 1);
        inputs.add(add, 1, 2);
        
        BorderPane bp = new BorderPane();
        bp.setLeft(grid);
        bp.setRight(inputs);
        grid.getStyleClass().add("grid");
        grid.getStylesheets().add("csvandfxpractice/grid.css");
        primaryStage.setScene(new Scene(bp, 300, 250));
        primaryStage.show();
    }
}