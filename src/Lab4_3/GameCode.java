package Lab4_3;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class GameCode extends Application {
	private final Circle red = new Circle();
	private final Circle yellow = new Circle();
	private final Circle green = new Circle();
	private final Circle blue = new Circle();
	
	public static void main(String[] args) {
        launch(args);
    }

	public void handleInput(BackEnd b, Circle c) {
		int state = b.receiveInput(c);
		if (state == BackEnd.SHOW) {
			SequentialTransition show = new SequentialTransition();
			show.setAutoReverse(false);
			b.fill(show, red, yellow, green, blue);
			show.play();
		}
		if (state == BackEnd.END) {
			
		}
	}
	
    @Override
    public void start(Stage primaryStage) {
    	BackEnd backEnd = new BackEnd();
    	
    	
        primaryStage.setTitle("Hello World!");
        BorderPane layout = new BorderPane();
        HBox gameInfo = new HBox();
        layout.setRight(gameInfo);
        gameInfo.setMinWidth(500);
  		GridPane grid = new GridPane();
  		layout.setCenter(grid);
  		
  		red.setFill(Color.RED);
  		yellow.setFill(Color.YELLOW);
  		blue.setFill(Color.BLUE);
  		green.setFill(Color.GREEN);
  		red.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				handleInput(backEnd, red);
			}
  		});
  		yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
  				handleInput(backEnd, yellow);
			}
  		});
  		blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
  				handleInput(backEnd, blue);
			}
  		});
  		green.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
  				handleInput(backEnd, green);
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
  		
  		Button button = new Button("Start Game");
  		button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		handleInput(backEnd,null);
            } 
        });        
  		Label scoreInfo = new Label();
  		scoreInfo.setText("Current Score");
  		
  		gameInfo.getChildren().add(button);
  		primaryStage.setScene(new Scene(layout, 800, 600));
        primaryStage.show();
    }

}
