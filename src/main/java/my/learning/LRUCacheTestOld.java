/**
 * 
 */
package my.learning;

import java.util.concurrent.TimeUnit;

import my.interview.infibeam.LRUCache;
import my.interview.infibeam.LRUCacheFactory;

/**
 * @author vamsi
 *
 */
public class LRUCacheTestOld {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final LRUCache cache = LRUCacheFactory.getBasicLRUCache();
		
		//thread 1
		new Thread(
			new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().toString());
					for(int i = 6000; i < 13000; i++) {
						cache.put(Integer.toString(i), Integer.toString(i), i*2);
					}
				}
			}
		).start();
		
		System.out.println(Thread.currentThread().toString());
		
		System.out.println(cache.get("6789") + " " + cache.get("1234"));
		
		
//		try {
//			TimeUnit.SECONDS.sleep(7);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		System.out.println(cache.get("6789") + " " + cache.get("1234"));
		
		//thread 2
		new Thread(
				new Runnable() {
					@Override
					public void run() {
						System.out.println(Thread.currentThread().toString());
						//Made it Long deliberately to take more time
						Long count = 0L;
						for(int i = 6000; i < 7000; i++) {
							if (cache.get(Integer.toString(i)) != null) {
								count++;
							}
						}
						System.out.println("Got " + count + " keys ");
						System.out.println(cache.get("6789") + " " + cache.get("1234"));
					}
				}
			).start();
		
		try {
			TimeUnit.SECONDS.sleep(7);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(cache.get("6789") + " " + cache.get("1234"));
		
		for(int i = 100; i < 7500; i++) {
			cache.put(Integer.toString(i), Integer.toString(i) + Integer.toString(i), i*3);
		}
		
		System.out.println(cache.get("6789") + " " + cache.get("1234"));
		
			
		System.out.println("end of main");
	}

}
