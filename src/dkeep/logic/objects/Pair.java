package dkeep.logic.objects;

import java.io.Serializable;

/** Implementation of Pair<K,V> */
public class Pair <K, V> implements Serializable {

	/** Key. */
	public K first;
	
	/** Value. */
	public V second;
	
	/** Creates an instance of Pair.
	 * @param key Key of the pair.
	 * @param value Value of the pair. */
	public Pair(K key, V value) {
		first = key;
		second = value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return first.equals(((Pair<?,?>) obj).first) && second.equals(((Pair<?,?>) obj).second);
	}
}
