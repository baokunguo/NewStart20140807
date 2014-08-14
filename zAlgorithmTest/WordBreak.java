package zAlgorithmTest;

import java.util.Set;
import java.util.TreeSet;

public class WordBreak {

	/**
	 * @param args
	 *            Given a string s and a dictionary of words dict, determine if
	 *            s can be segmented into a space-separated sequence of one or
	 *            more dictionary words.
	 * 
	 *            For example, given s = "leetcode", dict = ["leet", "code"].
	 * 
	 *            Return true because "leetcode" can be segmented as
	 *            "leet code".
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String string = "ujiasdf";
		//String[] substring = new String[] { "ia", "sdf" };
		// false for {"sdf","uji"}
		String[] substring = new String[] { "sdf", "ujia" };
		Set<String> strings = new TreeSet<String>();
		
		for (String string2 : substring) {
			if (string.contains(string2)) {
				System.out.println(string2);
			}
			strings.add(string2);
		}
		
		System.out.println("new line \n ");
		Solution solution = new Solution();
		System.out.println(String.valueOf(solution.wordBreak(string, strings))+" out ");
	}

	/*
	 * native approach
	 * 对所要分解的字符串进行遍历，然后在每次遍历是，以字典中的长度去匹配，若发现某次匹配，则将匹配完成的字符串的终点位置作为起始点
	 * ，继续寻找字典里的字符串
	 * 
	 */
	public static class Solution {
		public boolean wordBreak(String s, Set<String> dict) {
			return wordBreakHelper(s, dict, 0);
		}

		public boolean wordBreakHelper(String s, Set<String> dict, int start) {
			if (start == s.length())
				return true;

			for (String a : dict) {
				int len = a.length();
				int end = start + len;

				// end index should be <= string length
				if (end > s.length())
					continue;

				if (s.substring(start, start + len).equals(a))
					if (wordBreakHelper(s, dict, start + len))
						return true;
			}

			return false;
		}
	}
	
	
	/*
	 * 对原始例子的改动
	 * 
	 * */
	
	public static class Solution2 {
	    public boolean wordBreak(String s, Set<String> dict) {
	        boolean[] t = new boolean[s.length()+1];
	        t[0] = true; //set first to be true, why?
	        //Because we need initial state
	 
	        for(int i=0; i<s.length(); i++){
	            //should continue from match position
	            if(!t[i]) 
	                continue;
	 
	            for(String a: dict){
	                int len = a.length();
	                int end = i + len;
	                if(end > s.length())
	                    continue;
	 
	                if(t[end]) continue;
	 
	                if(s.substring(i, end).equals(a)){
	                    t[end] = true;
	                }
	            }
	        }
	 
	        return t[s.length()];
	    }
	}
}
