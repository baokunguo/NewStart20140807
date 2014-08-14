package zAlgorithmTest;

public class ReverseWords {

	/**
	 * @author baobao baokunguo@gmail.com
	 * @param args
	 * 
	 *            Given an input string, reverse the string word by word	 * 
	 *            For example, given s = "the sky is blue", return
	 *            "blue is sky the".
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String example = "I am coming the earth";
		/*
		 * String[] temp = example.split(" "); for (String string : temp) {
		 * System.out.println("string " + string); }
		 */
		System.out.println("origin is : " + example + 
				"\nreverseWord is : \n"	+ reverseWord(example));
	}
	
	//just for space type
	public static String reverseWord(String example) {
		// special conditions
		if (example == null || example.length() == 0) {
			return null;
		}
		StringBuffer result = new StringBuffer();
		String[] temp = example.trim().split(" ");
		for (int i = temp.length - 1; i > -1; i--) {
			result.append(temp[i]);
			result.append(" ");
		}

		return result.toString().trim();
	}

}
