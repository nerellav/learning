package my.learning;

import java.util.Date;

import my.interview.infibeam.ValueRecord;

/**
 * This class is used as container to hold value object and associated book keeping data
 * @author vamsi
 *
 */
class ValueRecordNode extends ValueRecord {
	
	ValueRecordNode prev = null;
	ValueRecordNode next = null;
	
	ValueRecordNode(String v, Date d) {
		super(v, d);
	}
	
	ValueRecordNode(String v, Date d, long ttl) {
		super(v, d, ttl);
	}
}
