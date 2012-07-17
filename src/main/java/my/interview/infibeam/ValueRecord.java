package my.interview.infibeam;

import java.util.Date;

/**
 * This class is used as container to hold value object and associated book
 * keeping data
 * 
 * @author vamsi
 * 
 */
class ValueRecord {
	// make all the fields mutable by declaring final
	private final String value;
	private final Date timeInserted;

	// specify ttl in seconds
	private final long ttl;

	ValueRecord(String v, Date d) {
		value = v;
		timeInserted = d;

		// if not specified, live as long as possible.
		ttl = 1 << 60;
	}

	/**
	 * 
	 * @param v
	 * @param t
	 * @param timetolive
	 */
	ValueRecord(String v, Date d, long timeToLive) {
		value = v;
		timeInserted = d;

		if (timeToLive >= 0) {
			ttl = timeToLive;
		} else {
			// if it is negative treat it as not specified; live as long as
			// possible.
			ttl = 1 << 60;
		}

	}

	String getValue() {
		return value;
	}

	Date getTimeInserted() {
		return timeInserted;
	}

	long getTtl() {
		return ttl;
	}

}
