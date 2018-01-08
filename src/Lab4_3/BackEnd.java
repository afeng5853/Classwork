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

public class BackEnd {
	private SimonSaysOrder simonSays;
	public static final int SHOW = 0;
	public static final int PLAY = 1;
	public static final int END = 2;
	private int round = 0;
	public int state = SHOW;
	private ScoreTracker scoreTracker;

	public BackEnd(ScoreTracker scoreTracker) {
		this.simonSays = new SimonSaysOrder(1);
		this.scoreTracker = scoreTracker;
	}
	
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
			TranslateTransition nothing = new TranslateTransition(Duration.millis(500), red); // pad animation by 200ms
			st.getChildren().addAll(nothing, blink.getFade(), blink.getUnfade());
			st.setOnFinished(new EventHandler<ActionEvent>(){
	            @Override
	            public void handle(ActionEvent arg0) {
	                state = PLAY;
	            }
	        });
		}
	}
	
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

	public int receiveInput(Circle c) {
		if (this.state != BackEnd.SHOW && this.state != BackEnd.END) {
			(new BlinkTransition(c)).play();
		}
		if (this.state == BackEnd.PLAY) {
			int result = simonSays.stepEqual(mapPaintToConstantColor(c.getFill()));
			switch (result) {
				case SimonSaysOrder.MATCH:
					scoreTracker.incrementScore();
					if (simonSays.noMoreMoves()) {
						this.state = BackEnd.SHOW;
						simonSays.addNextColor();
						simonSays.resetRound();
						this.round++;
					}
					break;
				case SimonSaysOrder.NO_MATCH:
					this.state = BackEnd.END;
					break;
				default:
					System.out.println("test");
					break;
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
}
