package collections;

public interface Set<T> {
	

	public void add(T element);
	
	public void remove(T element);
	
	public boolean contains(T element);
	
	public int size();
	
	public boolean isEmpty();
	
	public T[] toArray();
	
}
