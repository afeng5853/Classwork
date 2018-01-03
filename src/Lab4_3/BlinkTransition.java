package Lab4_3;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class BlinkTransition {
	private FadeTransition fade;
	public FadeTransition getFade() {
		return fade;
	}

	public FadeTransition getUnfade() {
		return unfade;
	}

	private FadeTransition unfade;
	
	public BlinkTransition(Circle c) {
		fade = new FadeTransition(Duration.millis(150), c);
		fade.setFromValue(1.0);
	    fade.setToValue(0);
	    
	    unfade = new FadeTransition(Duration.millis(150), c);
	    unfade.setFromValue(0);
	    unfade.setToValue(1.0);
	}

	public void play() {
		fade.setOnFinished(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent arg0) {
                unfade.play();
            }
        });
		fade.play();
	}
}
