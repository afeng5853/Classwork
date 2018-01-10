package Lab4_3;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation.Status;
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Front-end component of Simon Says
 * @author Alex Feng, Raymond Cheung
 * @since 1/9/2017
 */
public class GameCode extends Application {
	private final Circle red = new Circle();
	private final Circle yellow = new Circle();
	private final Circle green = new Circle();
	private final Circle blue = new Circle();
	private SequentialTransition show = new SequentialTransition();
	private Stage stage;
	private Scene inGameScene;
	private final static CSVUtilities CSV = new CSVUtilities(new File("hiscore.csv"));
	private int lastState = -1;
	
	
	public static void main(String[] args) {
        launch(args);
    }

	/**
	 * Called when a circle is clicked and delegates responsiblity to the backend for game logic
	 * Depending on the state of the game as indicated by the backend, the frontend either plays
	 * the order of the colors or ends the game
	 * @param b the backend
	 * @param c the circle clicked
	 */
	public void handleInput(BackEnd b, Circle c) {
		int state = b.receiveInput(c); // state of game
		if (state == BackEnd.SHOW && show.getStatus() == Status.STOPPED) {
			show = new SequentialTransition();
			show.setAutoReverse(false);
			// fills the sequential transition with the correct animations
			b.fill(show, red, yellow, green, blue);
			show.play();
		}
		// if the state is to end
		if (state == BackEnd.END) {
			Scene gameOver = createGameOverScene(b, 800, 600);
			deinitButtons(b);
			stage.setScene(gameOver);
		}
		lastState = state;
	}
	
	
    /**
     * Creates the scene when the game is over
     * @param backEnd the backend
     * @param w width of the window
     * @param h height of the window
     * @return the game over scene
     */
	public Scene createGameOverScene(BackEnd backEnd, int w, int h) {
		Rectangle bgLayer = new Rectangle();
		bgLayer.setWidth(w);
		bgLayer.setHeight(h);
		bgLayer.setFill(Color.WHITE);
		StackPane sp = new StackPane();
		VBox vb = new VBox();
		Text gameOver = new Text("Game Over!\nYour score is : " + backEnd.getScore() + "\nWould you like to save your score?");
		
		GridPane scores = new GridPane();
		scores.setHgap(10);
		GridHiscore.display(scores, CSV.getCSVData());
		
		Button save = new Button("Save");
		Button playAgain = new Button("Play Again");
		TextField name = new TextField();
		
		playAgain.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                start(stage); // reset
            }
        });
		save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	CSV.save(name.getText(), backEnd.getScore());
            	start(stage); // reset
            }
        });
		
		HBox options = new HBox();
		options.getChildren().addAll(save, playAgain);
		vb.getChildren().addAll(gameOver, scores, name, options);
		sp.getChildren().addAll(bgLayer, vb);
		Scene gameOverScene = new Scene(sp, w, h);
		gameOverScene.getStylesheets().add("Lab4_3/gameOver.css");
		return gameOverScene;
	}
	
	/**
	 * initializes the button with a click event to interact with the game logic
	 * @param backEnd the backend
	 */
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
	
	/**
	 * Sets the buttons event on click to do noting
	 * @param backEnd the backend
	 */
	private void deinitButtons(BackEnd backEnd) {
		red.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
			}
  		});
  		yellow.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
			}
  		});
  		blue.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
			}
  		});
  		green.setOnMouseClicked(new EventHandler<MouseEvent>() {
  			@Override
			public void handle(MouseEvent event) {
			}
  		});
	}
	
    @Override
    public void start(Stage primaryStage) {
    	// primary stage
    	stage = primaryStage;
    	primaryStage.setTitle("Hello World!");
    	
    	// initialize backend
    	int initScore = 0;
    	Text score = new Text(String.valueOf(initScore));
    	ScoreTracker scoreTracker = new ScoreTracker(score, initScore);
    	BackEnd backEnd = new BackEnd(scoreTracker);
    	
    	HBox scoreWrapper = new HBox(score);
    	
    	// initialization of the main grid and it's children
    	GridPane mainGrid = new GridPane();
    	
        BorderPane layout = new BorderPane();
        
        // grid of circles
  		GridPane grid = new GridPane(); 
  		grid.add(red, 0, 0);
  		grid.add(yellow, 1, 0);
  		grid.add(blue, 0, 1);
  		grid.add(green, 1, 1);
  		grid.getStyleClass().add("circles");
  		
  		VBox gameInfo = new VBox();
  		
  		layout.setCenter(grid);
  		mainGrid.add(layout, 0, 0);
  		mainGrid.add(scoreWrapper, 0, 1);
  		mainGrid.add(gameInfo, 0, 2);
  		
  		// end initialization of main grid
  		
  		// css classes
  		mainGrid.getStyleClass().add("main");
  		gameInfo.getStyleClass().add("game_info");
  		
  		// init circle attributes
  		red.setFill(Color.RED);
  		yellow.setFill(Color.YELLOW);
  		blue.setFill(Color.BLUE);
  		green.setFill(Color.GREEN);
  		
  		red.setRadius(60.0);
  		yellow.setRadius(60.0);
  		blue.setRadius(60.0);
  		green.setRadius(60.0);
  		
  		// start button
  		Button start = new Button("Start Game");
  		start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            		initButtons(backEnd);
            		handleInput(backEnd,null);
            		start.setDisable(true);
            } 
        });
  		
  		inGameScene = new Scene(mainGrid, 800, 600);
  		gameInfo.getChildren().add(start);
  		primaryStage.setScene(inGameScene);
  		inGameScene.getStylesheets().add("Lab4_3/game.css");
        primaryStage.show();
    }

}
