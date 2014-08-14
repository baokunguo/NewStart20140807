package zAlgorithmTest;

import java.util.HashSet;

public class WordLaderExam {

	/**
	 * @param args
	 * 
	 *            Given two words (start and end), and a dictionary, find the
	 *            length of shortest transformation sequence from start to end,
	 *            such that:
	 * 
	 *            Only one letter can be changed at a time Each intermediate
	 *            word must exist in the dictionary For example,
	 * 
	 *            Given: start = "hit" end = "cog" dict =
	 *            ["hot","dot","dog","lot","log"] As one shortest transformation
	 *            is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its
	 *            length 5.
	 * 
	 *            Note: Return 0 if there is no such transformation sequence.
	 *            All words have the same length. All words contain only
	 *            lowercase alphabetic characters.
	 * 
	 *            ���԰��ֵ������е��ַ��������ɵĵ�������ȡ����
	 *            Ȼ���ڶ�ÿ�����е��ʵ�ÿ���ַ������б����������ڣ�������ӵ�Ŀ�꼯���У�����ԭ������ɾ��
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	public static class Solution {
	    public int ladderLength(String start, String end, HashSet<String> dict) {
	 
	        int len=0;
	        HashSet<String> visited = new HashSet<String>();
	 
	        for(int i=0; i<start.length(); i++){
	            char[] startArr = start.toCharArray();
	 
	            for(char c='a'; c<='z'; c++){
	                if(c==start.toCharArray()[i]){
	                    continue;
	                }
	 
	                startArr[i] = c;
	                String temp = new String(startArr);
	                if(dict.contains(temp)){
	                    len++;
	                    start = temp;
	                    if(temp.equals(end)){
	                        return len;
	                    }
	                }
	            }
	        }
	 
	        return len;
	    }
	}
	
}
