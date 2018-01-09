package Lab4_3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Text;

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
	
	public void incrementScore() {
		this.score += 100 * multiplier;
		this.multiplier += 0.1;
		this.scoreText.setText(String.valueOf(score));
	}
	
	public int plays() {
		int count = 0;
		for (int i = 1; i < read("gameScores.csv").size(); i++) {
			String[] r = read("gameScores.csv").get(i).split(",");
			String dataCellString = r[0];
			try {
				if(r[0].equals(player)) {
					count++;
				}
			} catch(NumberFormatException e) {
				continue;
			} finally {
			}
		}
		return count;
	}
	
	public int highScore() {
		int score = 0;
		for (int i = 1; i < read("gameScores.csv").size(); i++) {
			String[] r = read("gameScores.csv").get(i).split(",");
			String dataCellString = r[1];
			Integer dataCellInteger = null;
			try {
				dataCellInteger = Integer.parseInt(dataCellString);
				if(Integer.parseInt(dataCellString) > score) {
					score = Integer.parseInt(dataCellString);
				}
			} catch(NumberFormatException e) {
				continue;
			} finally {
			}
		}
		return score;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public int getScore() {
		return score;
	}
}


