package Lab4_3;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that dictates the logic of Simon Says
 * @author fenga
 *
 */
public class SimonSaysOrder {
	// Color Constants
	public final static int RED = 0;
	public final static int YELLOW = 1;
	public final static int BLUE = 2;
	public final static int GREEN = 3;
	
	// Order of colors
	private List<Integer> saveOrder;
	
	// Order of colors that acts like a stack to keep track of player's progress
	private List<Integer> playOrder;
	
	public SimonSaysOrder(int size) {
		this.saveOrder = new ArrayList<>();
		// fills the order of colors based on size with random colors
		for (int i = 0; i < size; i++) {
			this.addNextColor();
		}
		this.playOrder = new ArrayList<>(this.saveOrder);
	}

	/**
	 * adds a random color to the order
	 */
	public void addNextColor() {
		this.saveOrder.add(randomColor());
	}

	/**
	 * clears the order
	 */
	public void clear() {
		this.saveOrder.clear();
	}
	
	public List<Integer> getSaveOrder() {
		return saveOrder;
	}
	
	/**
	 * Indicates whether or not their are more moves if the stack is empty
	 * @return any more moves?
	 */
	public boolean noMoreMoves() {
		return this.playOrder.isEmpty();
	}
	
	/**
	 * Generates a random color
	 * @return random integer 0-3 representing the colors in simon says
	 */
	private int randomColor() {
		return (int) (Math.random() * 4);
	}
	
	/**
	 * Resets the round by copying the order to the stack
	 */
	public void resetRound() {
		this.playOrder = new ArrayList<>(this.saveOrder);
	}
	
	/**
	 * Pops from stack and compares to user input to see if there is a match or not
	 * @param color
	 * @return
	 */
	public boolean stepEqual(int color) {
		return this.playOrder.remove(0) == color;
	}
}

	
