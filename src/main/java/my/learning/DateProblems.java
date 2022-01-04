package my.learning;

import org.junit.Assert;

class MyDate {
	int y, m, d;
	MyDate(int y, int m, int d) {
		this.y=y;
		this.m=m;
		this.d=d;
	}
	MyDate() {
		// TODO Auto-generated constructor stub
	}
	public String toString() {
		return this.y + "-" + this.m + "-"+this.d;
	}
}

public class DateProblems {
	
	static int[] monthdays = new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
	
	
	public static boolean isLeap(int year) {
		//assume 0th year is not a leap
		if (year > 0 && ((year % 4 == 0 && year % 100 !=0) || year %400 ==0 ) ) {
			return true;
		}
		return false;
	}
	
	private static boolean isLeap(MyDate dt) {
		return isLeap(dt.y);
	}
	
	public static int leapsFromZero(int y) {
		//return (d.y * 365) + d.y/4 - d.y/100 + d.y/400 ;
		return y/4 - y/100 + y/400 ;
		
	}
	
	public static int daysToNewYear(int y) {
		return ((y-1) * 365) + leapsFromZero(y);
	}
	
	public static MyDate convertToDate(int days) {
		MyDate dt = new MyDate();
		int daysLeft = 0;
		// first date is 1-1-1
		dt.y = days/365 + 1;
		daysLeft = (days - leapsFromZero(days/365)) % 365;
		int month = 1;
		int daysToSubtract = 0;
		
		while (daysLeft > 0) {
			daysToSubtract = monthdays[month-1];
			
			if (isLeap(dt.y) && month == 2) {
				daysToSubtract = 29;
			}
			
			if (daysLeft > daysToSubtract ) {
				daysLeft = daysLeft - daysToSubtract;
				month++;
			} else {
				break;
			}
		}
		
		dt.m = month;
		dt.d = daysLeft;
		return dt;
	}
	
	public static int daysFromZero(MyDate dt) {
		
		int days =  daysToNewYear(dt.y);
		
		for (int i=0; i<dt.m - 1; i++) {
			days = days + monthdays[i];
		}
		
		days += dt.d;
		
		if (isLeap(dt.y) && dt.m > 2) {
			days = days + 1;
		}
		
		return days;
	}
	
	
	public static int dateDiff(MyDate first, MyDate second) {
		return Math.abs(daysFromZero(first) - daysFromZero(second));
	}
	
	public static void main(String[] args) {
		Assert.assertTrue(isLeap(new MyDate(2020,1,1)));
		Assert.assertTrue(isLeap(new MyDate(2000,1,1)));
		Assert.assertTrue(isLeap(new MyDate(2012,1,1)));
		Assert.assertFalse(isLeap(new MyDate(2100,1,1)));
		Assert.assertFalse(isLeap(new MyDate(2021,1,1)));
		
		Assert.assertEquals(0, leapsFromZero(0));
		Assert.assertEquals(121, leapsFromZero(497));
		Assert.assertEquals(121, leapsFromZero(503));
		Assert.assertEquals(96, leapsFromZero(396));
		Assert.assertEquals(97, leapsFromZero(400));
		
		System.out.println(leapsFromZero(396));
		System.out.println(leapsFromZero(400));
		
		System.out.println(daysToNewYear(2020)); //737425
		System.out.println(daysToNewYear(2021)); //737790
		System.out.println(daysToNewYear(1)); //365
		
		System.out.println(daysFromZero(new MyDate(2020,2,2))); //33
		
		Assert.assertEquals(737458, daysFromZero(new MyDate(2020,2,2)));
		
		System.out.println(daysFromZero(new MyDate(2020,3,1))); //61
		System.out.println(daysFromZero(new MyDate(2020,3,31))); //91
		System.out.println(daysFromZero(new MyDate(2021,3,31))); //90
		
//		Assert.assertEquals(737851, daysFromZero(new MyDate(2020,3,1)));
//		Assert.assertEquals(737881, daysFromZero(new MyDate(2020,3,31)));
//		Assert.assertEquals(738245, daysFromZero(new MyDate(2021,3,31)));
		
//		Assert.assertEquals(35, daysFromZero(new MyDate(0,2,4)));
//		Assert.assertEquals(31, daysFromZero(new MyDate(0,1,31)));
//		Assert.assertEquals(61, daysFromZero(new MyDate(0,3,2)));
		
//		System.out.println(new MyDate(2020,3,1));
//		System.out.println(convertToDate(737851));
		System.out.println("----");
		System.out.println(daysFromZero(new MyDate(0,2,4))); // 0-2-4
		System.out.println(convertToDate(35)); // 0-2-4
		System.out.println("----");
		System.out.println(convertToDate(31)); // 0-1-31
		System.out.println(convertToDate(60));
		System.out.println(convertToDate(61)); //0-3-2
		
		System.out.println(convertToDate(365)); // 0-12-31
		System.out.println(convertToDate(366)); //1-1-1
		System.out.println(convertToDate(367));
		
		System.out.println(convertToDate(737851)); 
		System.out.println(convertToDate(737881)); 
		System.out.println(convertToDate(738245)); 
		
		Assert.assertEquals(30, dateDiff(new MyDate(2020,3,1), new MyDate(2020,3,31)));
		
		Assert.assertEquals(33, dateDiff(new MyDate(2020,2,28), new MyDate(2020,4,1)));
		Assert.assertEquals(32, dateDiff(new MyDate(2021,2,28), new MyDate(2021,4,1)));
	}


}
