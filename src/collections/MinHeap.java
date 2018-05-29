package collections;

public interface MinHeap<T extends Comparable<? super T>> {
	
	// TODO: Create a separate class and implement this interface there.

	public void add(T newEntry);
	
	public T removeMin();
	
	public T getMin();
	
	public boolean isEmpty();
	
	public int getSize();
	
	public void clear();
	
}
