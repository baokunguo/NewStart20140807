package zAlgorithmTest;

public class Palindromic {

	/**
	 * @author baobao e-mail:baokunguo@gmail.com
	 * 
	 * @param args
	 *            Finding the longest palindromic substring is a classic problem
	 *            of coding interview. In this post, I will summarize 3
	 *            different solutions for this problem.
	 * 
	 *            There are three different algorithm solving these problems
	 *            1.建立字符串矩阵，先初始化对角线和次对角线元素，然后再初始化所有元素，最后依据元素非零情况进行计算
	 *            2.回文字符串只有两种方式ABBA和ABA类型，首先遍历所有的字符串，然后以每个字符串为中心去寻找最大半径
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String example = "CDEBAABEadfUOEDEOUfpoCDEBA"
				+ "ABEadfUOEDEOUfpoiopfUOEDEOUfdaEBAABEDCopfUOEDEOUfdaEBAABEDC";
		System.out.println("maxlen is " + maxlen(example));
	}

	// calculate the max Palindromic string
	public static int maxlen(String example) {
		int maxlength = 0;
		int dius = 0;
		for (int i = 0; i < example.length() - 2; i++) {
			// BAAB type
			if (example.charAt(i) == example.charAt(i + 1)) {
				// i is the center
				// then calculate the radius
				dius = radiusBAAB(example, i) * 2 + 2;
				System.out.println("Palindromi String BAAB "
						+ example.substring(i - radiusBAAB(example, i), i + 1
								+ radiusBAAB(example, i)));
				// int templen = 2*radius+1;
			} else if (i > 0
					&& (example.charAt(i - 1) == example.charAt(i + 1))) {
				dius = radiusBAB(example, i) * 2 + 1;
				// int templen = 2*radius
				System.out.println("Palindromi String BACAB "
						+ example.substring(i - radiusBAB(example, i), i
								+ radiusBAB(example, i)));
			}
			if (dius > maxlength) {
				maxlength = dius;
			}
		}
		return maxlength;
	}

	// find the BAAB type
	public static int radiusBAAB(String example, int icent) {
		// int Dlen = 0;
		int tostart = icent;
		int toend = example.length() - 1 - (icent + 1);
		int j = 0;
		System.out.println("tostart " + tostart + " toend " + toend);
		while (tostart > 0 && toend > 0) {
			if (example.charAt(icent - j) == example.charAt(icent + 1 + j)) {
				// System.out.println("If " + j);
				j++;
				tostart = icent - j;
				toend = example.length() - 1 - (icent + j);
				// System.out.println("the j is " + j);
			} else {
				break;
			}

		}
		// Dlen = j*2 + 1;
		return j;
	}

	// find the BAB type
	public static int radiusBAB(String example, int icent) {
		int j = 0;
		int tostart = icent - 1;
		int toend = example.length() - 1 - (icent + 1);
		while (tostart > 0 && toend > 0) {
			if (example.charAt(icent - j) == example.charAt(icent + j)) {
				j++;
				tostart = icent - j;
				toend = example.length() - 1 - (icent + j);
				// System.out.println("j is " + j);
			} else {
				break;
			}
		}
		// 2*j
		return j;
	}

	// native method
	public static String longestPalindrome1(String s) {

		int maxPalinLength = 0;
		String longestPalindrome = null;
		int length = s.length();

		// check all possible sub strings
		for (int i = 0; i < length; i++) {
			for (int j = i + 1; j < length; j++) {
				int len = j - i;
				String curr = s.substring(i, j + 1);
				if (isPalindrome(curr)) {
					if (len > maxPalinLength) {
						longestPalindrome = curr;
						maxPalinLength = len;
					}
				}
			}
		}

		return longestPalindrome;
	}

	public static boolean isPalindrome(String s) {

		for (int i = 0; i < s.length() - 1; i++) {
			if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
				return false;
			}
		}

		return true;
	}

	// dynamic programming
	public static String longestPalindrome2(String s) {
		if (s == null)
			return null;

		if (s.length() <= 1)
			return s;

		int maxLen = 0;
		String longestStr = null;

		int length = s.length();

		int[][] table = new int[length][length];

		// every single letter is palindrome
		for (int i = 0; i < length; i++) {
			table[i][i] = 1;
		}
		printTable(table);

		// e.g. bcba
		// two consecutive same letters are palindrome
		for (int i = 0; i <= length - 2; i++) {
			if (s.charAt(i) == s.charAt(i + 1)) {
				table[i][i + 1] = 1;
				longestStr = s.substring(i, i + 2);
			}
		}
		printTable(table);
		// condition for calculate whole table
		for (int l = 3; l <= length; l++) {
			for (int i = 0; i <= length - l; i++) {
				int j = i + l - 1;
				if (s.charAt(i) == s.charAt(j)) {
					table[i][j] = table[i + 1][j - 1];
					if (table[i][j] == 1 && l > maxLen)
						longestStr = s.substring(i, j + 1);
				} else {
					table[i][j] = 0;
				}
				printTable(table);
			}
		}

		return longestStr;
	}

	public static void printTable(int[][] x) {
		for (int[] y : x) {
			for (int z : y) {
				System.out.print(z + " ");
			}
			System.out.println();
		}
		System.out.println("------");
	}
}