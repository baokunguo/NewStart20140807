package zAlgorithmTest;

import java.util.Stack;

public class ReversePolishNotation {

	/**
	 * @param args
	 *            Evaluate the value of an arithmetic expression in Reverse
	 *            Polish Notation.
	 * 
	 *            Valid operators are +, -, *, /. Each operand may be an integer
	 *            or another expression.
	 * 
	 *            Some examples: ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
	 *            ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
	 * 
	 *            栈的定义
	 *             　栈（Stack）是限制仅在表的一端进行插入和删除运算的线性表。
	 *            　　(1)通常称插入、删除的这一端为栈顶（Top），另一端称为栈底（Bottom）。 　　
	 *            	 (2)当表中没有元素时称为空栈。
	 *            　　(3)栈为后进先出（Last In First Out）的线性表，简称为LIFO表。
	 *            栈的修改是按后进先出的原则进行。每次删除（退栈）的总是当前栈中
	 * 
	 *            "最新"的元素，即最后插入（进栈）的元素，而最先插入的是被放在栈的底部，要到最后才能删除。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] example = new String[] {"2", "1", "+", "3", "*"};
		String[] example2 = new String[] {"4", "13", "5", "/", "+"} ;
		System.out.println(evalue(example) + " is example1\t "+evalue(example2));
				
	}
	
	public static int evalue(String[] example) {
		
		String operation = "+-*/";
		Stack<String> stack = new Stack<String>();
		
		for (String string : example) {
			if (!operation.contains(string)) {
				stack.push(string);
			}else {
				int a = Integer.parseInt(stack.pop());
				int b = Integer.parseInt(stack.pop());
				int index = operation.indexOf(string);
				switch (index) {
				case 2:
					stack.push(String.valueOf(a*b));
					break;
				case 0:
					stack.push(String.valueOf(a+b));
					break;
				case 1:
					stack.push(String.valueOf(a-b));
					break;
				case 3:
					stack.push(String.valueOf(b/a));
					break;
				}
			}
		}
		return Integer.parseInt(stack.pop());
	}

}
