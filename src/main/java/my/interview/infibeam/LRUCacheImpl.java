/**
 * 
 */
package my.interview.infibeam;

import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author vamsi
 * 
 */
class LRUCacheImpl implements LRUCache {
	private static final int MAX_CACHE_SIZE = 10000;

	@SuppressWarnings("serial")
	private final Map<String, ValueRecord> map = new LinkedHashMap<String, ValueRecord>(
			MAX_CACHE_SIZE, 0.75f, true) {

		// overriding this method to remove least recently used methods. i.e the
		// cache contains MAX_CACHE_SIZE elements at max
		@Override
		protected boolean removeEldestEntry(
				@SuppressWarnings("rawtypes") Map.Entry eldest) {
			return size() > MAX_CACHE_SIZE;
		}
	};
	
	//wrap linkedHashMap in a synchronizedMap to achieve synchronization + LRU capabilities
	private final Map<String, ValueRecord> cache = Collections
			.synchronizedMap(map);

	LRUCacheImpl() {
		System.out.println("creating the cache for: " + Thread.currentThread().toString());
		
		// start a scheduler to remove stale entries in cache
		startTimerTask();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.infibeam.LRUCache#put(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void put(String key, String value) {
		ValueRecord valueRecord = new ValueRecord(value, new Date());
		cache.put(key, valueRecord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.infibeam.LRUCache#put(java.lang.String,
	 * java.lang.String, long)
	 */
	@Override
	public void put(String key, String value, long ttl) {
		ValueRecord valueRecord = new ValueRecord(value, new Date(), ttl);
		cache.put(key, valueRecord);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.infibeam.LRUCache#get(java.lang.String)
	 */
	@Override
	public String get(String key) {
		ValueRecord valueRecord = cache.get(key);

		String value = null;
		if (valueRecord != null) {
			if (new Date().getTime() - valueRecord.getTimeInserted().getTime() > valueRecord
					.getTtl()) {
				// stale value -- time to live expired - remove this from cache
				// and return null
				remove(key);
				return null;
			}
			// value legal
			value = valueRecord.getValue();
		}
		// if the key is not in cache value is null
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see my.interview.infibeam.LRUCache#remove(java.lang.String)
	 */
	@Override
	public String remove(String key) {
		ValueRecord valueRecord = cache.remove(key);
		String value = null;
		if (valueRecord != null) {
			value = valueRecord.getValue();
		}
		return value;
	}

	private void startTimerTask() {
		// in TimeUnits -- using very small number to test concurrency
		// put a reasonable values in real-world
		int delay = 1;
		int repeat = 3;
		
		ScheduledExecutorService execService = Executors.newScheduledThreadPool(3);
		
		execService.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				removeStaleEntriesInCache();
			}
		}, delay, repeat, TimeUnit.SECONDS);
		
		//TODO: need to devise a mechanism to control when to shutdown
		//execService.shutdown();
	}

	private void removeStaleEntriesInCache() {
		System.out.println(Thread.currentThread().toString() + ": removeStaleEntriesInCache");
		
		//synchronize on the backedup object
		synchronized (cache) {
			Iterator itr = cache.entrySet().iterator();
			while (itr.hasNext()) {
				Entry<String, ValueRecord> e = (Entry<String, ValueRecord>) itr
						.next();

				ValueRecord valueRecord = e.getValue();

				if (new Date().getTime()
						- valueRecord.getTimeInserted().getTime() > valueRecord
							.getTtl()) {
					itr.remove();
				}
			}
		}
	}
}
