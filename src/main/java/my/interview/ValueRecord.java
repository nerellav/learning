package my.interview;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class ValueRecord {
	private final String value;
	private final Date timeInserted;
	
	//specify ttl in seconds
	private final int ttl;
	
	public ValueRecord(String v, Date t) {
		value = v;
		timeInserted = t;
		//defalut time to live is an hour
		ttl = 3600;
	}
	
	public ValueRecord(String v, Date t,int timetolive) {
		value = v;
		timeInserted = t;
		ttl = timetolive;
	}
	
	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		Date d1 = new Date();
		System.out.println(TimeUnit.SECONDS.toHours(3656));
		Date d2 = new Date();

		System.out.println(d2.getTime() - d1.getTime());

	}

	public String getValue() {
		return value;
	}

	public Date getTimeInserted() {
		return timeInserted;
	}

	public int getTtl() {
		return ttl;
	}

}
