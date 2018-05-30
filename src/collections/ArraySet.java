package collections;
import java.util.Arrays;

public class ArraySet<T> implements Set<T>{
	
	private T set[];
	private int size = 0;
	
	@SuppressWarnings("unchecked")
	public ArraySet() {
		set = (T[]) new Object[1];;
		size = 0;
	}

	public void add(T element) {
		ensureCapacity();
		if (!contains(element)) {
			set[size] = element;
			size++;
		}
		
	}

	public void remove(T element) {
		int index = getIndex(element);
		if (index != -1) {
			for (int i = index; i < size; i++) {                
			    set[i] = set[i+1];
			}
			size++;
		}
	}

	public boolean contains(T element) {
		int index = getIndex(element);
		boolean contain = false;
		if (index != -1) {
			contain = true;
		}
		return contain;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		boolean empty = false;
		if (size == 0) {
			empty = true;
		}
		return empty;
	}

	public T[] toArray() {
		return set;
	}
	private int getIndex(T element) {
		int index = -1,i;
		for (i = 0; i < size(); i++) {
			if (set[i] == element){
				index = i;
			}
		}
		return index;
		
	}
	private void ensureCapacity() {
		if (size > set.length-5) {
	        set = Arrays.copyOf(set, set.length*2);
		}
	}

}

