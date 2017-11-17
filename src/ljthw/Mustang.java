package ljthw;

public class Mustang implements Horse {
	private String name;
	private int weight;
	
	public Mustang(String name, int weight) {
		this.name = name;
		this.weight = weight;
	}
	
	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getWeight() {
		return weight;
	}
	
	@Override
	public String toString() {
		return "[" + name + "]";
	}

}
