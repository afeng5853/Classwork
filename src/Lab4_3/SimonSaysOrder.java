package Lab4_3;

import java.util.ArrayList;
import java.util.List;

public class SimonSaysOrder {
	public final static int RED = 0;
	public final static int YELLOW = 1;
	public final static int BLUE = 2;
	public final static int GREEN = 3;
	public final static int ROUND_DONE = -1;
	public final static int MATCH = 1;
	public final static int NO_MATCH = 0;
	private List<Integer> saveOrder;
	private List<Integer> playOrder;
	
	public List<Integer> getSaveOrder() {
		return saveOrder;
	}

	public SimonSaysOrder(int size) {
		this.saveOrder = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			this.addNextColor();
		}
		this.playOrder = new ArrayList<>(this.saveOrder);
	}

	private int randomColor() {
		return (int) ((Math.random() * 4) + 1);
	}
	
	public void addNextColor() {
		this.saveOrder.add(randomColor());
	}
	
	public int stepEqual(int color) {
		if (this.playOrder.size() == 0) {
			return ROUND_DONE;
		} else if (this.playOrder.remove(0) == color) {
			return MATCH;
		} else {
			return NO_MATCH;
		}
	}
	
	public void resetRound() {
		this.playOrder = new ArrayList<>(this.saveOrder);
	}
	
	public void clear() {
		this.saveOrder.clear();
	}
}

	
