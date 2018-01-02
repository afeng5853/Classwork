package Lab4_3;

import java.util.List;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BackEnd {
	SimonSaysOrder simonSays;
	public BackEnd() {
		this.simonSays = new SimonSaysOrder(1);
	}
	
	public void fill(Timeline tl, Circle red, Circle yellow, Circle green, Circle blue) {
		List<Integer> order = simonSays.getSaveOrder();
		for (int i = 0; i < order.size(); i++) {
			switch(order.get(i)) {
				case SimonSaysOrder.RED:
					tl.getKeyFrames().addAll(
							new KeyFrame(new Duration(1000),
									new KeyValue(red.opacityProperty(), 0.25)
							),
							new KeyFrame(new Duration(1000),
									new KeyValue(red.opacityProperty(), 1.0)
							)
					);
					break;
				case SimonSaysOrder.YELLOW:
					tl.getKeyFrames().addAll(
							new KeyFrame(new Duration(1000),
									new KeyValue(yellow.opacityProperty(), 0.25)
							),
							new KeyFrame(new Duration(1000),
									new KeyValue(yellow.opacityProperty(), 1.0)
							)
					);
					break;
				case SimonSaysOrder.BLUE:
					tl.getKeyFrames().addAll(
							new KeyFrame(new Duration(1000),
									new KeyValue(blue.opacityProperty(), 0.25)
							),
							new KeyFrame(new Duration(1000),
									new KeyValue(blue.opacityProperty(), 1.0)
							)
					);
					break;
				case SimonSaysOrder.GREEN:
					tl.getKeyFrames().addAll(
							new KeyFrame(new Duration(1000),
									new KeyValue(green.opacityProperty(), 0.25)
							),
							new KeyFrame(new Duration(1000),
									new KeyValue(green.opacityProperty(), 1.0)
							)
					);
					break;
				default:
					break;
			}
			
		}
	}
	
}
