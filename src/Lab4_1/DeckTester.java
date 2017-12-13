package Lab4_1;

public class DeckTester {
	public static void main(String[] args) {
		String[] ranks = new String[] {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
		String[] suits = new String[] {"Diamond", "Club", "Spade", "Heart"};
		int[] pointValues = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
		Deck deck = new Deck(ranks, suits, pointValues);
		deck.shuffle();
		for (int i = 0; i < deck.size(); i++) {
			System.out.println(deck.deal());
		}
 	}
}
