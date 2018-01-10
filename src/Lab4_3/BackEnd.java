package Lab4_3;

import java.util.List;

import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * Backend of the game
 * @author fenga
 *
 */
public class BackEnd {
	private SimonSaysOrder simonSays;
	private ScoreTracker scoreTracker;
	
	// states of backend
	public static final int SHOW = 0;
	public static final int PLAY = 1;
	public static final int END = 2;
	
	public int state = SHOW;
	private int round = 0;

	public void setScoreTracker(ScoreTracker scoreTracker) {
		this.scoreTracker = scoreTracker;
	}

	public BackEnd(ScoreTracker scoreTracker) {
		this.simonSays = new SimonSaysOrder(1);
		this.scoreTracker = scoreTracker;
	}
	
	/**
	 * Fills the sequential transition with BlinkTransition corresponding to the order of simon says
	 * @param st the sequential transition
	 * @param red red circle
	 * @param yellow yellow circle
	 * @param green green circle
	 * @param blue blue circle
	 */
	public void fill(SequentialTransition st, Circle red, Circle yellow, Circle green, Circle blue) {
		List<Integer> order = simonSays.getSaveOrder();
		for (int i = 0; i < order.size(); i++) {
			BlinkTransition blink = null;
			switch(order.get(i)) {
				case SimonSaysOrder.RED:
					blink = new BlinkTransition(red);
					break;
				case SimonSaysOrder.YELLOW:
					blink = new BlinkTransition(yellow);
					break;
				case SimonSaysOrder.GREEN:
					blink = new BlinkTransition(green);
					break;
				case SimonSaysOrder.BLUE:
					blink = new BlinkTransition(blue);
					break;
				default:
					System.out.println("test");
					break;
			}
			 // pad animation by x duration
			TranslateTransition nothing = new TranslateTransition(Duration.millis(500), red);
			st.getChildren().addAll(nothing, blink.getFade(), blink.getUnfade());
			st.setOnFinished(new EventHandler<ActionEvent>(){
	            @Override
	            public void handle(ActionEvent arg0) {
	                state = PLAY;
	            }
	        });
		}
	}
	
	/**
	 * Maps the Paint class to a SimonSaysOrder constant color
	 * @param p the paint
	 * @return a SimonSaysOrder constant color
	 */
	private int mapPaintToConstantColor(Paint p) {
		if (p == Color.RED) {
			return SimonSaysOrder.RED;
		} else if (p == Color.YELLOW) {
			return SimonSaysOrder.YELLOW;
		} else if (p == Color.GREEN) {
			return SimonSaysOrder.GREEN;
		} else if (p == Color.BLUE) {
			return SimonSaysOrder.BLUE;
		}
		return -1;
	}

	/**
	 * Connection between simonSaysOrder and front end interaction
	 * @param c the circle clicked
	 * @return the state of the game
	 */
	public int receiveInput(Circle c) {
		if (this.state == BackEnd.PLAY) {
			(new BlinkTransition(c)).play();
			boolean match = simonSays.stepEqual(mapPaintToConstantColor(c.getFill()));
			if (match) {
				scoreTracker.incrementScore();
				if (simonSays.noMoreMoves()) {
					this.state = BackEnd.SHOW;
					simonSays.addNextColor();
					simonSays.resetRound();
					this.round++;
				}
			} else {
				this.state = BackEnd.END;
			}
		}
		return this.state;
	}

	public int getScore() {
		return this.scoreTracker.getScore();
	}
	
	public ScoreTracker getScoreTracker() {
		return scoreTracker;
	}
	public void setState(int state) {
		this.state = state;
	}

}
