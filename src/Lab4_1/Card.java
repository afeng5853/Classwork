package Lab4_1;

public class Card {
	public String getRank() {
		return rank;
	}

	public String getSuit() {
		return suit;
	}

	public int getPointValue() {
		return pointValue;
	}

	private String rank;
	private String suit;
	private int pointValue;
	
	public Card(String rank, String suit, int pointValue) {
		this.rank = rank;
		this.suit = suit;
		this.pointValue = pointValue;
	}
	
	public boolean equals(Card other) {
		return this.pointValue == other.pointValue;
	}

	@Override
	public String toString() {
		return "Card [rank=" + rank + ", suit=" + suit + ", pointValue=" + pointValue + "]";
	}
}
