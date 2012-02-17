/**
 * 
 */
package my.interview;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author vamsi
 * @param <V>
 * @param <K>
 * 
 */
class LRUCacheImpl implements LRUCache {
	private static final int DEFAULT_CACHE_SIZE = 1000;
	
	@SuppressWarnings("serial")
	private final Map<String, ValueRecord> map = new LinkedHashMap<String, ValueRecord>(
			100, 0.75f, true){
		
		//overriding this method to remove least recenctly used methods. i.e the cache contains
		//DEFAULT_CACHE_SIZE elements
		protected boolean removeEldestEntry(@SuppressWarnings("rawtypes") Map.Entry eldest) {
			return size() > DEFAULT_CACHE_SIZE;
		}
	};

	private final Map<String, ValueRecord> cache = Collections
			.synchronizedMap(map);

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.LRUCache#put(java.lang.String, java.lang.String)
	 */
	@Override
	public void put(String key, String value) {
		ValueRecord valueRecord = new ValueRecord(value, new Date());
		cache.put(key, valueRecord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.LRUCache#put(java.lang.String, java.lang.String, long)
	 */
	@Override
	public void put(String key, String value, int ttl) {
		ValueRecord valueRecord = new ValueRecord(value, new Date(), ttl);
		cache.put(key, valueRecord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.LRUCache#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		ValueRecord valueRecord = cache.get(key);
		return valueRecord.getValue();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.LRUCache#remove(java.lang.String)
	 */
	@Override
	public String remove(String key) {
		ValueRecord valueRecord =  cache.remove(key);
		return valueRecord.getValue();
	}
}
