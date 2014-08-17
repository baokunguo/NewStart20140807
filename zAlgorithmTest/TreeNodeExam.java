package zAlgorithmTest;

import java.util.ArrayList;
import java.util.Stack;

public class TreeNodeExam {

	/**
	 * @param args
	 */
	public ArrayList<Integer> preorderTraversal(TreeNode root) {
		ArrayList<Integer> returnList = new ArrayList<Integer>();
		if (root == null) {
			return returnList;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.push(root);
		while (!stack.empty()) {
			TreeNode node = stack.pop();
			returnList.add(node.val);

			if (node.right != null) {
				stack.push(node.right);
			}
			if (node.left != null) {
				stack.push(node.left);
			}
		}
		return returnList;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode treeNode1 = new TreeNode(100);
		TreeNode treeNode = treeNode1;
		for (int i = 0; i < 10; i++) {
			treeNode.left = new TreeNode(2 * i);
			treeNode.right = new TreeNode(2 * 3 * i);
			if (i % 2 == 0) {
				treeNode = treeNode.left;
			} else {
				treeNode = treeNode.right;
			}
		}
		ArrayList<Integer> preArrayList = new TreeNodeExam().preorderTraversal(treeNode1);
		System.out.println(preArrayList);
		
		/*stack */
		Stack<Integer> stack = new Stack<Integer>();
		for (int i = 0; i < 10; i++) {
			stack.push((int) (i+Math.round(Math.random()*10)));
		}
		System.out.println("stack is " + stack.toString());
		for (int i = 0; i < 10; i++) {
			System.out.println(stack.pop());
		}

	}

	

}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	public TreeNode(int x) {
		// TODO Auto-generated constructor stub
		val = x;
	}
	
	/*public String toString(){
		return 
	}*/
}
