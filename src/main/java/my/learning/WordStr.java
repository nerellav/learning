package my.learning;

public class WordStr {
	private String str;
	private int counter;

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	public int getCounter() {
		int cnt = (counter++ % str.length()) - 1;
		return cnt;
	}

	public WordStr(String str) {
		super();
		this.str = str;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}
	
	public int length() {
		return str.length();
	}
	public String strAt(int i) {
		return String.valueOf(str.charAt(i));
	}
}
