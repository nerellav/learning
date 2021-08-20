/**
 * 
 */
package my.learning;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author vamsi
 * 
 */
public class ObjectTest {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {

		// using countdownlatch as described in effectiveJava
		final CountDownLatch ready = new CountDownLatch(2);
		final CountDownLatch start = new CountDownLatch(1);
		final CountDownLatch done = new CountDownLatch(2);

		final ObjTest ot = new ObjTest();
		
		
		List l= new ArrayList();
		l.add(new Integer(5));
		l.add(new String("vamsi"));
		
		int i = 0;

		// thread 1 - put
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().toString());
				ready.countDown();
				try {
					start.await();
					System.out.println("" + ot.getField());

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					done.countDown();
				}

			}
		}).start();

		// thread 1 - put
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println(Thread.currentThread().toString());
				ready.countDown();
				try {
					start.await();
					Object f1 = ot.getField();
					Thread.sleep(5);
					Object f2 = ot.getField();
					
					if (f1 != f2) {
						System.out.println("Gotchaaaa");
					}

				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally {
					done.countDown();
				}

			}
		}).start();
		
		
		start.countDown();
		System.out.println(++i);
		done.await();

		System.out.println("end of main");
	}

}

class ObjTest {
	/*volatile*/ Object field;

	Object getField() {
		if (field == null) {
			System.out.println(Thread.currentThread().toString() + " trying to acquire lock. field is " + field );
			synchronized (this) {
				System.out.println(Thread.currentThread().toString() + " Got lock. field is " + field );
				if (field == null) {
					System.out.println("went inside..MS is gone");
					Double d = (1237367.83683 * 652746284248224.4645) / 782548548251.86582648246 * 375784264224.0 * 7685365265325.0;
					field = new Object();
				}
			}
		}
		return field;
	}
}
