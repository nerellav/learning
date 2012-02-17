/**
 * 
 */
package my.interview;

/**
 * @author vamsi
 *
 */
public interface LRUCache {
	/**
	 * 
	 * @param key
	 * @param value
	 */
	void put(String key, String value); 
	
	/**
	 * 
	 * @param key
	 * @param value
	 * @param ttl - time to live in seconds
	 */
	void put(String key, String value, int ttl); 
	
	/**
	 * 
	 * @param key
	 * @return corresponding value for the key
	 */
	String get(String key);
	
	/**
	 * Returns the value to which this map previously associated the key, or null if 
	 * the map contained no mapping for the key.
	 * The map will not contain a mapping for the specified key once the call returns.

	 * @param key
	 * @return the 
	 */
	String remove(String key);
}
