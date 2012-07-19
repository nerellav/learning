package my.learning;
public class Test {

	Integer i;

	public Test(Integer i) {
		super();
		this.i = i;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Test t1 = new Test(new Integer(5));
		Test t2 = new Test(new Integer(5));
		if (t1 == t2)
			System.out.println("== correct");
		else
			System.out.println("== not correct");

		if (t1.equals(t2))
			System.out.println("equals correct");
		else
			System.out.println("equals not correct");
		
		
		Integer i1 = 5;//new Integer(5);
		Integer i2 = 5;//new Integer(5);
		if (i1 == i2)
			System.out.println("== correct");
		else
			System.out.println("== not correct");

		if (i1.equals(i2))
			System.out.println("equals correct");
		else
			System.out.println("equals not correct");
		
		System.out.println("�1 = " + i1.hashCode() + "\ti2 = " + i2.hashCode());
		
		
		String s1 = "5";//new Integer(5);
		String s2 = "5";
		if (s1 == s2)
			System.out.println("== correct");
		else
			System.out.println("== not correct");

		if (s1.equals(s2))
			System.out.println("equals correct");
		else
			System.out.println("equals not correct");
		
		System.out.println("s1 = " + s1.hashCode() + "\ts2 = " + s2.hashCode());

		
		
		Float f1 = (float) 5.0;//new Integer(5);
		Float f2 = (float) 5;//new Integer(5);
		if (f1 == f2)
			System.out.println("== correct");
		else
			System.out.println("== not correct");

		if (f1.equals(f2))
			System.out.println("equals correct");
		else
			System.out.println("equals not correct");
		
		System.out.println("f1 = " + f1.hashCode() + "\tf2 = " + f2.hashCode());
		
		
		Double d1 = 5.0;//new Integer(5);
		Double d2 =  5.00;//new Integer(5);
		if (d1 == d2)
			System.out.println("== correct");
		else
			System.out.println("== not correct");

		if (d1.equals(d2))
			System.out.println("equals correct");
		else
			System.out.println("equals not correct");
		
		System.out.println("d1 = " + d1.hashCode() + "\td2 = " + d2.hashCode());
		
		System.out.println("hello");
		
		
		long l = 2147483647; //(long) Math.pow(2.0, 32.0)  -1;
		
		System.out.println(Long.toBinaryString(l) + " " + l);
		long l2 = (long) Integer.MIN_VALUE;
		
		System.out.println(Long.toBinaryString(l2) + " " + l2);

		long l3 = (l ^ (l >>> 32));
		
		System.out.println(Long.toBinaryString(l3) + " " + l3);
		int i3 = (int)(l ^ (l >>> 32));
		
		System.out.println(Integer.toBinaryString(i3) + " " + i3);

		
//		//1667610645
//		System.out.println(i);

	}

}
