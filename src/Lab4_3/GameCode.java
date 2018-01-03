package Lab4_3;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameCode extends Application {

	public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
    	BackEnd backEnd = new BackEnd();
    	
    	SequentialTransition show = new SequentialTransition();
    	show.setAutoReverse(false);
    	
        primaryStage.setTitle("Hello World!");
        BorderPane layout = new BorderPane();
  		GridPane grid = new GridPane();
  		layout.setCenter(grid);
  		
  		Circle red = new Circle();
  		Circle yellow = new Circle();
  		Circle blue = new Circle();
  		Circle green = new Circle();
  		red.setFill(Color.RED);
  		yellow.setFill(Color.YELLOW);
  		blue.setFill(Color.BLUE);
  		green.setFill(Color.GREEN);
  		red.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				backEnd.receiveInput(red);
			}
  		});
  		yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
				backEnd.receiveInput(yellow);
			}
  		});
  		blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
				backEnd.receiveInput(blue);
			}
  		});
  		green.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
				backEnd.receiveInput(green);
			}
  		});
  		List<Circle> circles = new ArrayList<>();
  		circles.add(red);
  		circles.add(yellow);
  		circles.add(blue);
  		circles.add(green);
  		for (Circle c : circles) {
  			c.setRadius(60.0);
  		}
  		
  		grid.add(red, 0, 0);
  		grid.add(yellow, 1, 0);
  		grid.add(blue, 0, 1);
  		grid.add(green, 1, 1);
  		
  		backEnd.fill(show, red, yellow, green, blue);
  		show.play();
        primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }

}
