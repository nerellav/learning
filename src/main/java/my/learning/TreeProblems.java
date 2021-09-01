package my.learning;

import java.util.ArrayList;
import java.util.List;

public class TreeProblems {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "one";
        boolean used[] = {false, false, false};
        permuteMine(0, "", used, s);
        
        
        Node l31 = new Node(null, null, "L31");
        Node l32 = new Node(null, null, "L32");
        Node l33 = new Node(null, null, "L33");
        Node l34 = new Node(null, null, "L34");
        
        Node l21 = new Node(l31, null, "L21");
        Node l22 = new Node(null, l32, "L22");
        Node l23 = new Node(null, l33, "L23");
        Node l24 = new Node(l34, null, "L24");
        
        Node l11 = new Node(l21, l22, "L11");
        Node l12 = new Node(l23, l24, "L12");
        
        Node s21 = new Node(null, null, "L21");
        Node s22 = new Node(null, null, "L22");
        Node s23 = new Node(null, null, "L23");
        Node s24 = new Node(null, null, "L24");
        
        Node s11 = new Node(s21, s22, "L11");
        Node s12 = new Node(s23, s24, "L12");
        
        
        Node root1 = new Node(l11,l12,"root");
        
        Node root2 = new Node(s11,s12,"root");
        
        if (isTreeSame(root1, root2)) {
        	System.out.println("same trees");
        }
        
        if (isSubTree(l24, null)) {
        	System.out.println("sub tree.");
        }
        levelSum(root1);
        //print_d_distance_nodes(root1, 3);

	}

	static void permute(int level, String permuted, boolean used[], String original) {
		int length = original.length();
		if (level == length) {
			System.out.println(permuted);
		} else {
			for (int i = 0; i < length; i++) {
				if (!used[i]) {
					used[i] = true;
					permute(level + 1, permuted + original.charAt(i), used,
							original);
					used[i] = false;
				}
			}
		}
	}
	
	static void permuteMine(int level, String permuted, boolean used[], String original) {
		int len = original.length();
		if (level == len) {
			System.out.println(permuted);
		}
		else {
			for( int i = 0; i < len; i++ ) {
				if (used[i] == false) {
					used[i] = true;
					
					permuteMine(level + 1, permuted + original.charAt(i), used, original );
					used[i] = false;
				}
			}
		}
	}
	
	static boolean isSubTree(Node first, Node second) {
		if(isTreeSame(first, second)) {
			return true;
		} else {
			if (first != null && isSubTree(first.left, second)) {
				return true;
			}else if (first != null && isSubTree(first.right, second)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	static boolean isTreeSame(Node first, Node second) {
		
		if (first == second) {//both the pointers are same.
			return true;
		} else if(first == null || second == null) { //false if one of them is null
			return false;
		} else { //both are non null
			if (first.data == second.data) {
				return isTreeSame(first.left, second.left) && isTreeSame(first.right, second.right);
			}else {
				return false;
			}
		}
	}
	
	static void print_d_distance_nodes_uni (Node n, int d) {
		if (d == 0 && n != null ) {
			System.out.println(n.data);
		} else {
			if (n != null) print_d_distance_nodes_uni (n.left, d -1);
			if (n != null) print_d_distance_nodes_uni (n.right, d -1);
		}
	}
	
	static void print_d_distance_nodes (Node root, Node n, int d) {
		if (d == 0 && n != null ) {
			System.out.println(n.data);
		} else {
			if (n != null) print_d_distance_nodes(root,n.left, d -1);
			if (n != null) print_d_distance_nodes(root,n.right, d -1);
		}
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
