/**
 * 
 */
package my.interview.infibeam.test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import my.interview.infibeam.LRUCache;
import my.interview.infibeam.LRUCacheFactory;

/**
 * @author vamsi
 * 
 */
public class CreateLRUCacheTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		
		
		//using countdownlatch as described in effectiveJava
		final CountDownLatch ready = new CountDownLatch(3);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(3);
		
		final LRUCache cache = LRUCacheFactory
				.getBasicLRUCache();
		//main thread - put
		for (int i = 5000; i < 7500; i++) {
			cache.put(Integer.toString(i), Integer.toString(i), i * 3);
		}

		// thread 1 - put
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().toString());
				ready.countDown();
				try {
					start.await();
					
					final LRUCache cache = LRUCacheFactory
							.getBasicLRUCache();
					for (int i = 3000; i < 10000; i++) {
						cache.put(Integer.toString(i), Integer.toString(i),
								i * 2);
					}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					done.countDown();
				}

			}
		}).start();

		System.out.println(Thread.currentThread().toString());

		System.out.println(cache.get("6789") + " " + cache.get("1234"));

		// thread 2 - get
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().toString());

				ready.countDown();
				try {
					start.await();
					final LRUCache cache = LRUCacheFactory
							.getBasicLRUCache();
					TimeUnit.SECONDS.sleep(2);
					// Made it Long deliberately to take more time
					Long count = 0L;
					for (int i = 6000; i < 17000; i++) {
						if (cache.get(Integer.toString(i)) != null) {
							count++;
						}
					}
					System.out.println("Got " + count + " keys ");
					System.out.println(cache.get("6789") + " "
							+ cache.get("1234"));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					done.countDown();
				}

			}
		}).start();

		// thread 3 - remove 
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().toString());
				ready.countDown();
				try {
					start.await();
					final LRUCache cache = LRUCacheFactory
							.getBasicLRUCache();
					int counter =0 ;
					for (int i = 4000; i < 6000; i++) {
						if (cache.remove(Integer.toString(i)) != null) {
							counter++;
						}
					}
					System.out.println("removed " + counter + "entries.");
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} finally {
					done.countDown();
				}

			}
		}).start();

		ready.await(); //wait for other threads to be ready.
		start.countDown(); // threads are off at the same time..
		
		final LRUCache cache2 = LRUCacheFactory
				.getBasicLRUCache();
		
		// main thread - remove - running parallel with other threads
		for (int i = 100; i < 7500; i++) {
			cache2.remove(Integer.toString(i));
		}
		done.await(); //wait for other threads to finish.
		
		System.out.println("main sleeping");
		TimeUnit.SECONDS.sleep(10);
		System.out.println("main woke up");
		System.out.println(cache2.get("6789") + " " + cache2.get("1234"));

		System.out.println("end of main");
	}

}
