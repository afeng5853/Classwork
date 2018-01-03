package Lab4_3;

import java.util.List;

import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class BackEnd {
	private SimonSaysOrder simonSays;
	private static final int SHOW = 0;
	private static final int PLAY = 1;
	private static final int END = 2;
	private int state = SHOW;

	public BackEnd() {
		this.simonSays = new SimonSaysOrder(10);
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
			st.getChildren().addAll(blink.getFade(), blink.getUnfade());
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

	public void receiveInput(Circle c) {
		(new BlinkTransition(c)).play();
		if (this.state == BackEnd.PLAY) {
			int result = simonSays.stepEqual(mapPaintToConstantColor(c.getFill()));
			switch (result) {
				case SimonSaysOrder.MATCH:
					if (simonSays.noMoreMoves()) {
						this.state = BackEnd.SHOW;
					}
					break;
				case SimonSaysOrder.NO_MATCH:
					this.state = 2;
					break;
				default:
					System.out.println("test");
					break;
			}
		}
	}


	
}
