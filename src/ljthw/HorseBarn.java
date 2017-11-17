package ljthw;

public class HorseBarn {
	/** The spaces in the barn. Each array element holds a reference to the horse
	* that is currently occupying the space. A null value indicates an empty space.
	*/
	private Horse[] spaces;
	
	public HorseBarn(Horse[] horses) {
		this.spaces = horses;
	}
	
	/** Returns the index of the space that contains the horse with the specified name.
	* Precondition: No two horses in the barn have the same name.
	* @param name the name of the horse to find
	* @return the index of the space containing the horse with the specified name;
	* -1 if no horse with the specified name is in the barn.
	*/
	public int findHorseSpace(String name)
	{ 
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null && name.equals(spaces[i].getName())) {
				return i;
			}
		}
		return -1;
	}
	/** Consolidates the barn by moving horses so that the horses are in adjacent spaces,
	* starting at index 0, with no empty space between any two horses.
	* Postcondition: The order of the horses is the same as before the consolidation.
	*/
	public void consolidate()
	{ 
		Horse[] answer = new Horse[spaces.length];
		int ansIdx = 0;
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null) {
				answer[ansIdx++] = spaces[i];
			}
		}
		this.spaces = answer;
	}
	// There may be instance variables, constructors, and methods that are not shown.
	
	@Override
	public String toString() {
		StringBuilder build = new StringBuilder();
		for (int i = 0; i < spaces.length; i++) {
			if (spaces[i] != null) {
				build.append(spaces[i].toString() + " ");
			} else {
				build.append("null ");
			}
		}
		return build.toString();
	}
}
