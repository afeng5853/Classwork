package Lab4_3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScoreTracker {
	
	String player;
	int score;
	public ScoreTracker(String player, int score) {
		//if state=BackEnd.END
		this.player = player;
		this.score = score;
	}
	
	public static void displayMessage() {
		// "Congratulations on achieving a score of " + score + " " + player + "you have played " + plays + " times!"
		//if (getScore() > highScore()) {
			// "Congratulations on setting the new high score with a score of " + score + " " + player + "you have played " + plays + " time!"
		//}
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
	
	public void saveScore() {
		append("gameScores.csv", getPlayer() + ", " + getScore());
	}
	
	
	public static List<String> read(String fileName) {
		List<String> data = new ArrayList<>();
		Path pathToFile = Paths.get(fileName);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
			String line;
			while ((line = br.readLine()) != null) {
				data.add(line);
			}
		} catch (IOException ioe ){
			ioe.printStackTrace(); 
		}
		return data;
	}
	
	public static void append(String fileName, String line) {
		try (FileWriter pw = new FileWriter(Paths.get(fileName).toString(), true);) {
			for (String s : line.split(",")) {
				pw.append(s);
				pw.append(',');
			}
			pw.append('\n');
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

