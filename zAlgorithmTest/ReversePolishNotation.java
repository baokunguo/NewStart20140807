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
	 *            ջ�Ķ���
	 *             ��ջ��Stack�������ƽ��ڱ��һ�˽��в����ɾ����������Ա�
	 *            ����(1)ͨ���Ʋ��롢ɾ������һ��Ϊջ����Top������һ�˳�Ϊջ�ף�Bottom���� ����
	 *            	 (2)������û��Ԫ��ʱ��Ϊ��ջ��
	 *            ����(3)ջΪ����ȳ���Last In First Out�������Ա����ΪLIFO��
	 *            ջ���޸��ǰ�����ȳ���ԭ����С�ÿ��ɾ������ջ�������ǵ�ǰջ��
	 * 
	 *            "����"��Ԫ�أ��������루��ջ����Ԫ�أ������Ȳ�����Ǳ�����ջ�ĵײ���Ҫ��������ɾ����
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
