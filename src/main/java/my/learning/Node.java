package my.learning;
/**
 * 
 */

/**
 * @author vamsi
 * 
 */
public class Node {
	String data;
	Node left;
	Node right;

	public Node(String data, Node left, Node right) {
		super();
		this.data = data;// Long.toString(counter++);
		this.left = left;
		this.right = right;

	}
	
	public Node(Node left, Node right,String data) {
		super();
		this.data = data;// Long.toString(counter++);
		this.left = left;
		this.right = right;

	}
	
}
