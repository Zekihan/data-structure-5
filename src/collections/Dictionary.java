package collections;

import java.util.Iterator;

public interface Dictionary<K, V> {
	
	// TODO: Create a separate class and implement this interface there.
	
	public V add(K key, V value);
	
	public V remove(K key);
	
	public V getValue(K key);
	
	public boolean contains(K key);
	
	public Iterator<K> getKeyIterator();

	public Iterator<V> getValueIterator();
	
	public boolean isEmpty();
	
	public int getSize();
	
	public void clear();
	
}
