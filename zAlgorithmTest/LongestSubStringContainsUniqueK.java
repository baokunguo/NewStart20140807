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
	 *            包含K个唯一字符串的最大子字符串：
	 *            从母字符串的头移到尾，每次检查其K个字符串以后的字符是否在集合里面，
	 *            若在，则将其长度加，否则继续在原母字符串移动，知道末尾最后第K个字符串
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
