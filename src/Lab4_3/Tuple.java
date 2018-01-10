package Lab4_3;

/**
 * Two-tuple
 * @author Alex Feng
 * @since 1/9/2018
 * @param <X> item1
 * @param <Y> item2
 */
public class Tuple<X, Y> { 
	public final X first; 
	public final Y second; 
	public Tuple(X x, Y y) { 
		this.first = x; 
		this.second = y; 
	} 
} 