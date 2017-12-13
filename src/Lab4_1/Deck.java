package Lab4_1;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> unDealt;
	private ArrayList<Card> Dealt;
	
	public Deck(String[] ranks, String[] suits, int[] points) {
		ArrayList<Card> cards = new ArrayList<>();
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				cards.add(new Card(ranks[i], suits[j], points[i]));
			}
		}
		this.unDealt = cards;
	}
	
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	public int size() {
		return this.unDealt.size();
	}
	
	public Card deal() {
		return this.unDealt.remove(this.size()-1);
	}
	
	private void exchange(int i, int j) {
		Card temp = unDealt.get(i);
		unDealt.set(i, unDealt.get(j));
		unDealt.set(j, temp);
	}
	
	public void shuffle() {
		Random r = new Random();
		for (int k = 51; k > 0; k--) {
			exchange(k, r.nextInt(k+1));
		}
	}
}
