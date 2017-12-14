package Lab4_1;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	private ArrayList<Card> unDealt;
	private ArrayList<Card> Dealt;
	
	public Deck(String[] ranks, String[] suits, int[] points) {
		ArrayList<Card> cards = new ArrayList<>();
		Dealt = new ArrayList<>();
		// for each rank, create each suit for that rank
		for (int i = 0; i < ranks.length; i++) {
			for (int j = 0; j < suits.length; j++) {
				cards.add(new Card(ranks[i], suits[j], points[i]));
			}
		}
		this.unDealt = cards;
	}
	
	/**
	 * returns whether the size of the Deck is 0
	 * @return if the deck is empty
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * returns the amount of the undealt cards
	 * @return the remaining amount of cards in the deck
	 */
	public int size() {
		return this.unDealt.size();
	}
	
	/**
	 * removes a card from the top of the deck by removing the last card and returning it
	 * also adds it to the dealt array
	 * @return the card on top (the last card of the list)
	 */
	public Card deal() {
		Card dealtCard = this.unDealt.remove(this.size()-1);
		Dealt.add(dealtCard);
		return dealtCard;
	}
	
	/**
	 * exchanges two cards
	 * @param i first card index
	 * @param j second card index
	 */
	private void exchange(int i, int j) {
		Card temp = unDealt.get(i);
		unDealt.set(i, unDealt.get(j));
		unDealt.set(j, temp);
	}
	
	/**
	 * shuffles the deck using an algorithm simialr to selection sort
	 */
	public void shuffle() {
		Random r = new Random();
		for (int k = 51; k > 0; k--) {
			exchange(k, r.nextInt(k+1));
		}
	}
}
