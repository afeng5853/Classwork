package Lab4_3;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;

/**
 * Grid utilities, helps fill GridPane with CSV data
 * @author alex
 *
 */
public class GridHiscore {
	/**
	 * Converts array list of lines to 2d matrix of rows and columns
	 * @param matrix csv data (String as row)
	 * @return converted matrix representation of input matrix
	 */
	private static Text[][] asTextMatrix(List<String> matrix) {
    	Text[][] textMatrix = new Text[matrix.size()][];
    	for (int i = 0; i < matrix.size(); i++) {
    		String[] row = matrix.get(i).split(",");
    		Text[] textRow = new Text[row.length];
    		for (int j = 0 ; j < matrix.get(i).split(",").length; j++) {
    			textRow[j] = new Text(row[j]);
    		}
    		textMatrix[i] = textRow;
    	}
    	return textMatrix;
    }
    
	/**
	 * fills grid pane with csv data correspondingly
	 * @param grid the GridPane
	 * @param data CSV data
	 */
    public static void display (GridPane grid, List<String> data) {
    	Text[][] matrix = GridHiscore.asTextMatrix(data);
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
}
