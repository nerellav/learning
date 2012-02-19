package my.learning;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class MapTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String, String> m = new LinkedHashMap<String, String>(100, 0.75f,
				true) {
			protected boolean removeEldestEntry(
					@SuppressWarnings("rawtypes") Map.Entry eldest) {
				return size() > 3;
			}
		};

		m.put("a", "a");
		m.put("b", "b");
		m.put("c", "c");
		m.put("d", "d");

		m.put("b", "bbb");

		printMap(m);
		System.out.println("1-----------");

		Map<String, String> m1 = new HashMap<String, String>(m);

		printMap(m);
		System.out.println("2-----------");

		String s = m.get("b");

		printMap(m);
		System.out.println("3-----------");

		printMap(m);
		System.out.println("4-----------");

		Map<String, String> m2 = new HashMap<String, String>(m);

		printMap(m2);
		System.out.println("5-----------");
		m2 = new HashMap<String, String>(m);

		printMap(m2);
		System.out.println("6-----------");

	}

	private static void printMap(Map<String, String> m) {

		Iterator itr = m.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, String> e = (Entry<String, String>) itr.next();
			System.out.print(e.getKey() + "-" + e.getValue() + ";");
		}
		System.out.println();
	}
}
