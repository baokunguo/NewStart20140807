package zAlgorithmTest;

import java.util.Set;
import java.util.TreeSet;

public class LongestSubStringContainsUniqueK {

	/**
	 * @author baobao
	 * baokunguo@gmail.com
	 * @param args
	 * 
	 *            Given a string, find the longest substring that contains only
	 *            two unique characters. For example, given
	 *            "abcbbbbcccbdddadacb", the longest substring that contains 2
	 *            unique character is "bcbbbbcccb". 
	 *            
	 *            ����K��Ψһ�ַ�����������ַ�����
	 *            ��ĸ�ַ�����ͷ�Ƶ�β��ÿ�μ����K���ַ����Ժ���ַ��Ƿ��ڼ������棬
	 *            ���ڣ����䳤�ȼӣ����������ԭĸ�ַ����ƶ���֪��ĩβ����K���ַ���
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "abcbbbbcccbdddadacbadacbadacbbcbbbcbbbcbbbcbbbcbb";
		/*for (int i = 0; i < string.length(); i++) {
			System.out.println(string.charAt(i));
			if (i > 1) {
				break;
			}
		}*/
		System.out.println("maxsub is : \n " + maxsub(string, 2));
	}
	
	public static int maxsub(String example,int klen){
		int maxlen = 0;
		Set<Character> kuniSet = new TreeSet<Character>();
		for (int i = 0; i < example.length(); i++) {
			int templen = 0;
			kuniSet.clear();
			// add the k 
			for (int j = i; j < example.length(); j++) {
				if (kuniSet.size() > klen) {
					break;
				}
				kuniSet.add(example.charAt(j));
				templen = j-i+1;
			}
			System.out.println("temp substring " + templen + " value " + example.substring(i,templen+i-1));
			if (templen > maxlen) {
				maxlen = templen;
			}
			//System.out.println("maxlen " + maxlen);
		}
		
		return maxlen;
		
	}
}
