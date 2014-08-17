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
	 *            可以把字典中所有的字符串所构成的单个集合取出，
	 *            然后在对每个现有单词的每个字符串进行遍历，若存在，则将其添加到目标集合中，并在原集合中删掉
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*public static void WordLader(String start, String end, String[] dict) {
		HashSet<Character> dictChar = new HashSet<Character>();
		dictChar = dictCharAdd(dict, dictChar, true);
		Character[] dictarry = dictChar.toArray();
		while (true) {
			char[] startarray = start.toCharArray();
			for (int i = 0; i < startarray.length; i++) {
				for (int j = 0; j < dictChar.size(); j++) {
					startarray[i] = dictChar.toArray()[j];
				}
			}
		}
		

	}*/

	public static HashSet<Character> dictCharAdd(String[] dict,
			HashSet<Character> dictChar, boolean delete) {
		// true
		if (dict.length > 0 && delete) {
			dictChar.clear();
		}
		// add the characters in dict to dictChar
		for (String string : dict) {
			char[] chartemp = string.toCharArray();
			for (char c : chartemp) {
				dictChar.add(Character.valueOf(c));
			}
		}
		
		return dictChar;
	}

	/*
	 * above is my own
	 * 
	 * sample answer
	 */
	public static class Solution {
		public int ladderLength(String start, String end, HashSet<String> dict) {

			int len = 0;
			HashSet<String> visited = new HashSet<String>();

			for (int i = 0; i < start.length(); i++) {
				char[] startArr = start.toCharArray();

				for (char c = 'a'; c <= 'z'; c++) {
					if (c == start.toCharArray()[i]) {
						continue;
					}

					startArr[i] = c;
					String temp = new String(startArr);
					if (dict.contains(temp)) {
						len++;
						start = temp;
						if (temp.equals(end)) {
							return len;
						}
					}
				}
			}

			return len;
		}
	}

}
