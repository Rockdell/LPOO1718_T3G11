package dkeep.logic.objects;

import java.io.Serializable;

public class Pair <K, V> implements Serializable {

	public K first;
	public V second;
	
	public Pair(K key, V value) {
		first = key;
		second = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		return first.equals(((Pair<?,?>) obj).first) && second.equals(((Pair<?,?>) obj).second);
	}
}
