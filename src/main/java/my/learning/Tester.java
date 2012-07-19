package my.learning;
class Tester extends Thread {
	int total = 10;
	String status = "";

	public static void main(String[] args) throws Exception {

		Tester t = new Tester();
		t.start();
		System.out.println("hi how are you:");
		//
		synchronized (t) {
			System.out.println("waiting for t to complete");
			t.total = 10;
			t.notifyAll();
			t.wait();
			System.out.println("total" + t.total);
		}
	}
	
	synchronized public void run() {
		total = 0;
		for (int i = 0; i < 3; i++) {
			total = total + 1;
		}
		notifyAll();
		try {
			System.out.println("before wait in run: " + total);
			wait(2000);
			System.out.println("after wait in run: " + total);
			notifyAll();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
                                                    