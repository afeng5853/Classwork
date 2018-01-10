package Lab4_3;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

/**
 * A custom blink transition that doesn't extend Transition
 * @author fenga
 *
 */
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
		// fade transition
		fade = new FadeTransition(Duration.millis(150), c);
		fade.setFromValue(1.0);
	    fade.setToValue(0);
	    
	    // unfade transition
	    unfade = new FadeTransition(Duration.millis(150), c);
	    unfade.setFromValue(0);
	    unfade.setToValue(1.0);
	}

	/**
	 * Plays the blink transition
	 */
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
