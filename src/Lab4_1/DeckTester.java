package Lab4_1;
/**
 * Lab 4.1
 * @author Alex Feng
 * @since 12/14/2017
 */
public class DeckTester {
	public static void main(String[] args) {
		// init deck fields
		String[] ranks = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] suits = new String[] {"Diamond", "Club", "Spade", "Heart"};
		int[] pointValues = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		
		// init deck
		Deck deck = new Deck(ranks, suits, pointValues);
		
		deck.shuffle();
		
		// print all of the cards in the deck
		for (int i = deck.size(); i > 0; i--) {
			System.out.println(deck.deal());
		}
 	}
}
