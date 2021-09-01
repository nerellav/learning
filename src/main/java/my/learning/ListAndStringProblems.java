package my.learning;

import java.util.Map;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

//Definition for singly-linked list.
class ListNode {
	int val;
	ListNode next;
	ListNode rand;

	ListNode() {
		System.out.println("default constructor called");
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}

	@Override
	public String toString() {
		String s = "";

		ListNode temp = this;

		while (temp != null) {
			String rval = temp.rand != null ? String.valueOf(temp.rand.val) : "";

			s = s.concat(temp.val + ":" + rval + "->");
			temp = temp.next;
		}

		return s;
	}
}

//* Definition for a binary tree node.
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class ListAndStringProblems {
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> sum = new HashMap<Integer, Integer>();
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			sum.put(nums[i], i);
		}

		for (int i = 0; i < nums.length; i++) {
			if (sum.containsKey(target - nums[i]) && i != sum.get(target - nums[i])) {
				System.out.printf("[%s,%s]\n", i, sum.get(target - nums[i]));
				result[0] = i;
				result[1] = sum.get(target - nums[i]);
				return result;
			}
		}

		return result;
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		int carry = 0;
		ListNode m = l1, n = l2, curr = null, first = null;

		while (m != null || n != null) {

			int x = (m != null ? m.val : 0);
			int y = (n != null ? n.val : 0);

			int sum = x + y + carry;

			carry = sum / 10;

			if (first == null) {
				first = new ListNode(sum % 10);
				curr = first;
			} else {
				curr.next = new ListNode(sum % 10);
				curr = curr.next;
			}

			if (m != null)
				m = m.next;
			if (n != null)
				n = n.next;
		}

		if (carry != 0) {
			// handle carry at the end
			curr.next = new ListNode(carry);
		}

		return first;
	}

	public static ListNode deepcopy(ListNode n) {
		ListNode curr = n;
		ListNode copy = new ListNode(-1);
		ListNode first = copy;

		while (curr != null) {
			copy.next = new ListNode(curr.val);
			curr = curr.next;
			copy = copy.next;
		}

		return first.next;

	}

	public static ListNode deepcopyDLL(ListNode n) {
		ListNode curr = n;
		ListNode first = n;

		// create a node and insert it between two nodes of orig list
		while (curr != null) {
			ListNode next = curr.next;
			ListNode copy = new ListNode(curr.val);

			curr.next = copy;
			copy.next = next;
			copy.rand = curr.rand;
			curr = next;
		}

		// restore orig

		ListNode second = first.next, secondhead = first.next;

		ListNode firsthead = first;

		// just handle rand in first pass
		while (first != null && first.next.next != null) {
			second = first.next;
			if (first.rand != null)
				second.rand = first.rand.next;
			else
				second.rand = null;

			first = first.next.next;
		}

		first = firsthead;
		// handle next links
		while (first != null && first.next.next != null) {
			second = first.next;
			first.next = second.next;
			second.next = second.next.next;
			first = first.next;
		}

		// handle last
		first.next = null;

		return secondhead;

	}

	static String longestPalindrome(String s) {
		char[] sa = s.toCharArray();
		int max = -1;
		char[] pal = null;
		for (int i = 0; i < sa.length; i++) {
			for (int j = i; j < sa.length; j++) {
				int len = j - i + 1;

				if (len > max) {
					if (isPalindrome(sa, i, j)) {

						max = len;
						pal = Arrays.copyOfRange(sa, i, j + 1);
					}

				}
			}

		}

		System.out.println(pal);
		return new String(pal);
	}

	static boolean isPalindrome(char[] s, int beg, int end) {

		int i = beg, j = end;
		while (i < j) {
			if (s[i] != s[j]) {
				return false;
			}
			i++;
			j--;
		}

		return true;
	}

	static String longestPalindromeDP(String s) {
		char[] sa = s.toCharArray();
		int max = -1;
		char[] pal = null;

		boolean[][] palmatrix = new boolean[sa.length][sa.length];

		// length 1 is a palindrome
		// badab

		// [1 0 0 0 0]
		// [0 1 0 0 0]
		// [0 0 1 0 0]
		// [0 0 0 1 0]
		// [0 0 0 0 1]
		for (int i = 0; i < sa.length; i++) {
			palmatrix[i][i] = true;
			max = 1;
			if (pal == null)
				pal = new char[] { sa[i] };
		}

		// length 2
		// badab
		// [1 0 0 0 0]
		// [0 1 0 0 0]
		// [0 0 1 0 0]
		// [0 0 0 1 0]
		// [0 0 0 0 1]
		for (int i = 0; i < sa.length - 1; i++) {
			if (sa[i] == sa[i + 1]) {
				palmatrix[i][i + 1] = true;
				max = 2;
				pal = Arrays.copyOfRange(sa, i, i + 2);
			}
		}

		// process all substrings greater than 3

		// badab
		// [1 0 0 0 1]
		// [0 1 0 1 0]
		// [0 0 1 0 0]
		// [0 0 0 1 0]
		// [0 0 0 0 1]

		for (int k = 3; k <= sa.length; k++) {

			for (int i = 0; i < sa.length - (k - 1); i++) {
				int j = i + k - 1;

				if (sa[i] == sa[j] && palmatrix[i + 1][j - 1] == true) {
					palmatrix[i][j] = true;

					if (j - i + 1 > max) {
						max = (j - i + 1);
						pal = Arrays.copyOfRange(sa, i, j + 1);
					}
				}
			}
		}

		return new String(pal);

	}

	static ListNode oddEvenList(ListNode head) {
		ListNode input = head, curr = head;

		int i = 0;

		ListNode first = null;

		while (curr != null) {

			if (first == null) {
				first = new ListNode(curr.val);
				curr = first;
			} else {

			}

			// move by 2
			curr = curr.next.next;

		}

		return first;

	}

	public List<String> letterCombinations(String digits) {
		List<char[]> buttons = new ArrayList<>();

		buttons.add(new char[] { 'a', 'b', 'c' });
		buttons.add(new char[] { 'd', 'e', 'f' });
		buttons.add(new char[] { 'g', 'h' });

		for (int i = 0; i < digits.length(); i++) {
			Integer.valueOf(digits);
		}
		return null;
	}

	public static int jump(int[] nums) {

		int n = nums.length;

		int[] jumps = new int[n];

		// initialize
		for (int i = 0; i < n; i++) {
			jumps[i] = 0;
		}

		// start from last but 1
		for (int i = n - 2; i >= 0; i--) {

			// can't jump from here
			if (nums[i] <= 0) {
				jumps[i] = Integer.MAX_VALUE;

				continue;
			}
			// straight forward case
			else if (nums[i] >= (n - 1 - i)) {
				jumps[i] = 1;
				continue;
			}

			int min = Integer.MAX_VALUE;

			// [2,3,1,1,2,4]
			// [, , , ,1,0]

			// find all places you can jump from ith, and calculate min to reach to end
			for (int j = i + 1; j <= nums[i] + i && j < n; j++) {

				// jumps[i] = min ( jumps[j] + 1, jumps[i]);
				if (jumps[j] < min) {
					min = jumps[j];
				}
			}

			if (min != Integer.MAX_VALUE) {
				jumps[i] = min + 1;
			} else {
				jumps[i] = Integer.MAX_VALUE;
			}

		}

		return jumps[0];

	}

	// in BST
	public void kthSmallest(TreeNode root, int k) {

		kthSmallest(root.left, k - 1);
		if (k == 0) {
			System.out.println(root.val);
			// return root.val;
		}
		kthSmallest(root.right, k - 1);
	}

	public static void main(String[] args) {
		int m[] = { 1, 3, 5 }, n[] = { 2, 4, 6, 8, 10, 11 };

		String s = "Sita";
		String r = "Rama";

//		System.out.println(s.charAt(0) > r.charAt(0));
//		
//		String pal = longestPalindrome("ababa");
//		
//		System.out.println(longestPalindromeDP("cccc"));
//		
//		int i = Integer.MAX_VALUE;
//		
////		char[] sa = "babad".toCharArray();
////		
////		System.out.println(Arrays.copyOfRange(sa, 1, 4));
//		
//		List<List<Integer>> result = threeSumSlow( new int[]{-1,0,1,2,-1,-3});
//		
//		System.out.println(result);

//		int x = 1234;
//		System.out.println(s.charAt(2));

		m = new int[] { 2, 3, 0, 1, 4 };

		int mj = jump(m);

//		System.out.println(mj);

		Map<Integer, int[]> nodes = new HashMap<Integer, int[]>();

		ListNode x = new ListNode(1);
		ListNode y = new ListNode(2);
		x.next = y;
		ListNode z = new ListNode(3);
		y.next = z;
		ListNode a = new ListNode(5);
		z.next = a;
		ListNode b = new ListNode(8);
		a.next = b;

		System.out.println(x);

		ListNode c = deepcopy(x);

		System.out.println(c);

		x.rand = a;
		y.rand = x;
		z.rand = b;
		a.rand = b;
		b.rand = y;

		System.out.println(x);

		ListNode d = deepcopyDLL(x);
		y = null;
		a = null;
		System.out.println(d);

		ListNode d2 = deepcopyDLL(d);
		System.out.println(d2);

	}

}