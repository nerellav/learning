package my.learning;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;


public class PrintPaths {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Node l31 = new Node("L31", null, null);
		Node l32 = new Node("L32", null, null);
		//Node l33 = new Node("L33", null, null);
		Node l34 = new Node("L34", null, null);
		Node l35 = new Node("L35", null, null);
		Node l36 = new Node("L36", null, null);
		Node l37 = new Node("L37", null, null);
		Node l38 = new Node("L38", null, null);
		
		//Node l21 = new Node("L21", l31, l32);
		Node l22 = new Node("L22", null, l34);
		Node l23 = new Node("L23", l35, l36);
		Node l24 = new Node("L24", l37, l38);

		Node l11 = new Node("L11", null, l22);
		Node l12 = new Node("L12", l23, l24);

		Node root = new Node("Root", l11, l12);

		printPaths(root, root.data);
		inOrder(root);System.out.println(" in.");
		preOrder(root);System.out.println(" pre.");
		postOrder(root);System.out.println(" post.");
		levelOrder(root);System.out.println(" level.");
		
		levelOrderRecursive(root);System.out.println(" level recursive.");
		
		leftView(root);System.out.println(" left.");
		rightView(root);System.out.println(" right.");
		
		System.out.println("height: "+height(root));
		
		levelSum(root);
		
		String in = "L31 L21 L32 L11 L22 L34 Root L35 L23 L36 L12 L37 L24 L38";
		String pre = "Root L31 L21 L32 L11 L22 L34 L35 L23 L36 L12 L37 L24 L38";
		
//		constructTree(in, pre);

	}
	
	static Node constructTree(String a, String b) {
		String[] in = a.split(" ");
		String[] pre = b.split(" ");
		
		return null;
	}

	static void printPaths(Node n, String pathSoFar) {
		if (n == null)
			return;

		if (n.left == null && n.right == null) {
			System.out.println(pathSoFar );
			return;
		}
		if (n.left != null) printPaths(n.left, pathSoFar + "|" + n.left.data);
		if (n.right != null) printPaths(n.right, pathSoFar + "|" + n.right.data);
	}
	
	static void levelOrder(Node n) {
		if (n == null ) return;
		
		List<Node> nodes = new ArrayList<Node>(10);
		
		nodes.add(n);
		
		while (nodes.size() > 0) {
			
			Node node = nodes.remove(0);
			System.out.print(node.data + " ");
			if (node.left != null )nodes.add(node.left);
			if (node.right != null) nodes.add(node.right);
		}
	}
	
	static void inOrder(Node n) {
		if (n == null) return;
		
		inOrder(n.left);
		System.out.print(n.data + " ");
		inOrder(n.right);
	}
	
	static void preOrder(Node n) {
		if (n == null) return;
		
		System.out.print(n.data + " ");	
		preOrder(n.left);
		preOrder(n.right);
	}
	
	static void postOrder(Node n) {
		if (n == null) return;
		
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n.data + " ");
	}
	
	static void leftView (Node n) {
		if (n == null) return;
		
		List<Node> q = new ArrayList<Node>();
		
		Node marker = new Node("", null,null);
		q.add(marker);
		q.add(n);
		
		while (q.size() > 0 ) {
			Node element = q.remove(0);
			
			if (element == marker) {
				if (q.size() > 0) {
					element = q.remove(0);
					System.out.print(element.data + " ");
					q.add(marker);	
				}
			}
			
			if (element.left != null) q.add(element.left);
			if (element.right != null) q.add(element.right);
		}
	}
	
	static int height(Node n) {
		if (n == null) return 0;
		int lheight=0,rheight=0;
		
		if (n.left != null) {
			lheight = height(n.left);
		}
		
		if (n.right != null) {
			rheight = height(n.right);
		}
		
		return lheight > rheight ? lheight + 1 : rheight + 1;
	}
	
	static void levelOrderRecursive (Node n) {
		int height = height(n);
		
		for (int i =1 ; i<=height; i++) {
			printLevel(n, i);
			
		}
	}
	
	static void levelSum (Node n) {
		int height = height(n);
		
		for (int i =1 ; i<=height; i++) {
			System.out.print("level "+ i + ": ");
			System.out.println(levelSum(n,i));
		}
	}
	
	static void printLevel(Node node, int level) {
		if(node == null) return;
		if (level == 1 ) System.out.print(node.data + " ");
		
		if (node.left != null) printLevel(node.left,level -1);
		if (node.right != null) printLevel(node.right,level -1);
	}
	
	static String levelSum(Node node, int level) {
		if(node == null) return "";
		
		String levelStr = "";
		if (level == 1 )  levelStr += node.data + " ";
		
		if (node.left != null) {
			levelStr += levelSum(node.left,level -1);
		}
		if (node.right != null) {
			levelStr += levelSum(node.right,level -1);
		}
		return levelStr;
	}
	
	static boolean isLevelSum(Node n, int sum) {
		
		//if (node == null) return false;
		
		return false;

	}
	
	static void rightView (Node n) {
		if (n == null) return;
		
		List<Node> q = new ArrayList<Node>();
		
		Node marker = new Node("", null,null);
		//q.add(marker);
		q.add(n);
		q.add(marker);
		
		Node lastSeen = null;
		
		while (q.size() > 0 ) {
			Node element = q.remove(0);
			
			if (element == marker) {
				System.out.print(lastSeen.data + " ");
				q.add(marker);
				if (q.size() > 0) {
					element = q.remove(0);
						
				}
			}
			
			if (element.left != null) q.add(element.left);
			if (element.right != null) q.add(element.right);
			lastSeen = element;
		}
	}
}
