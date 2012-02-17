package my.learning;

import java.util.concurrent.TimeUnit;

public class StopThreadVolatile {
	
	static volatile boolean stopRequested=false;
	
	static int i =0;
	static void doSomething() {
		while (!stopRequested) {
			i++;
		}
	}
	
	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		Thread sampleThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				doSomething();
			}
		});
		
		sampleThread.start();
		
		TimeUnit.SECONDS.sleep(1);
		
		stopRequested = true;
		
		System.out.println(i);
	}

}
