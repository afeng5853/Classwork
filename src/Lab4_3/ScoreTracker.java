package Lab4_3;

import javafx.scene.text.Text;

/**
 * Keeps track of the store and updates it
 * @author fenga
 *
 */
public class ScoreTracker {
	
	private String player;
	private int score;
	private double multiplier = 1.0;
	private Text scoreText;
	
	public ScoreTracker(Text scoreText, String player, int score) {
		this.scoreText = scoreText;
		this.player = player;
		this.score = score;
	}
	
	public ScoreTracker(Text scoreText, int score) {
		this.scoreText = scoreText;
		this.score = score;
	}
	
	public static void displayMessage() {
		// "Congratulations on achieving a score of " + score + " " + player + "you have played " + plays + " times!"
		//if (getScore() > highScore()) {
			// "Congratulations on setting the new high score with a score of " + score + " " + player + "you have played " + plays + " time!"
		//}
	}
	
	/**
	 * Increments the score by a constant of 100 and multiplies it by a multiplier based on streaks
	 * Updates the text of the score
	 * TODO: variable increase
	 */
	public void incrementScore() {
		this.score += 100 * multiplier;
		this.multiplier += 0.1;
		this.scoreText.setText(String.valueOf(score));
	}
	
	public String getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
	

}

