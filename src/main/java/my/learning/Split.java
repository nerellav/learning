package my.learning;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Split {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "fofofofof";

		System.out.println(s.split("fo").length - 1);
		float f1 = (float) 1234.568;
		System.out.println("vamsi");

		StringBuffer sbu = new StringBuffer("vamsi");
		System.out.println(sbu.reverse());
		StringBuilder sb = new StringBuilder("vamsi");
		System.out.println(sb.reverse());

		Map<String, String> lm = (new LinkedHashMap<String, String>(10,
						(float) 0.75, true));

		lm.put("2", "2");
		lm.put("4", "4");
		lm.put("1", "1");
		lm.put("3", "3");
		
		Iterator it = lm.keySet().iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		System.out.println("-----------------------");
		lm.get("1");
		lm.get("2");
		
		it = lm.keySet().iterator();
		
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		
		List<Integer> l = new ArrayList<Integer>();
		l.add(1);
		l.add(2);
		l.add(3);
		
		for (int i : l) {
			System.out.println(i);
			if (i==1) System.out.println(l.get(1) + 100);;
		}
		
		
//		for (String key : lm.keySet()) {
//			System.out.println(lm.get(key));
//		}

		// for (String key : lm.keySet()) {
		// System.out.println(lm.get(key));
		// }
	}

}
