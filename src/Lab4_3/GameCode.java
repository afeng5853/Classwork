package Lab4_3;

import java.util.ArrayList;
import java.util.List;

import java.io.File;

import javafx.animation.SequentialTransition;
import javafx.animation.Animation.Status;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;


public class GameCode extends Application {
	private final Circle red = new Circle();
	private final Circle yellow = new Circle();
	private final Circle green = new Circle();
	private final Circle blue = new Circle();
	private SequentialTransition show = new SequentialTransition();
	private Scene inGameScene;
	
	public static void main(String[] args) {
        launch(args);
    }

	public void handleInput(BackEnd b, Circle c) {
		int state = b.receiveInput(c);
		if (state == BackEnd.SHOW && show.getStatus() == Status.STOPPED) {
			show = new SequentialTransition();
			show.setAutoReverse(false);
			b.fill(show, red, yellow, green, blue);
			show.play();
		}
		if (state == BackEnd.END) {
			//should try/catch
			File theFile = new File("gameScores.csv");
			CSVUtilities score = new CSVUtilities(theFile);
			ScoreTracker endScore = new ScoreTracker(null, "Player",b.getScoreTracker().getScore());
			score.saveScore(endScore);
			System.out.println(score.getTopTen(1));
			System.out.println("Appended");
			// switch scenes
		}
	}
	
	private void initButtons(BackEnd backEnd) {
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
	}
	
    @Override
    public void start(Stage primaryStage) {
    	int initScore = 0;
    	Text score = new Text(String.valueOf(initScore));
    	ScoreTracker scoreTracker = new ScoreTracker(score, initScore);
    	BackEnd backEnd = new BackEnd(scoreTracker);
    	
    	GridPane mainGrid = new GridPane();
        primaryStage.setTitle("Hello World!");
        BorderPane layout = new BorderPane();
  		GridPane grid = new GridPane();
  		layout.setCenter(grid);
  		VBox gameInfo = new VBox();
  		mainGrid.add(layout, 0, 0);
  		mainGrid.add(score, 0, 1);
  		mainGrid.add(gameInfo, 0, 2);
  		mainGrid.setAlignment(Pos.CENTER);
  		gameInfo.setAlignment(Pos.CENTER);
  		
  		
  		red.setFill(Color.RED);
  		yellow.setFill(Color.YELLOW);
  		blue.setFill(Color.BLUE);
  		green.setFill(Color.GREEN);
  		
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
  		
  		Button start = new Button("Start Game");
  		start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		initButtons(backEnd);
            		handleInput(backEnd,null);
            		start.setDisable(true);
            } 
        });        
  		Label scoreInfo = new Label();
  		scoreInfo.setText("Current Score");
  		
  		inGameScene = new Scene(mainGrid, 800, 600);
  		gameInfo.getChildren().add(start);
  		primaryStage.setScene(inGameScene);
        primaryStage.show();
    }

}
