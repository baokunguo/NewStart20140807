package zLearningClass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class LearningClassStringandArrayExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		B bname = (B) new B().me();
		
		bname.doA();
		
		System.out.println(mydevide(13, 7));
		
		String aString = "Hello233289";
		
		String bString = aString.substring(3,6);
		
		String bString2 = aString.substring(3,6) + "";// faster when the original string is very long
		
		System.out.println("HashCode : \nastring  " + aString.hashCode() + "\nbstring " + bString.hashCode());
		
		// Following method will create an extra unnecessary object
		
		String cString = new String("huj");
		
		String ctringini = new String("huj").intern();
		
		String dString = new String("huj");
		
		String dStringini = new String("huj").intern();
		
		System.out.println(cString == dString); // False
		
		System.out.println(cString.equals(dString)); // True
		
		System.out.println(ctringini == dStringini); // True
		
		// x store the reference, x is not the reference
		
		String x = "xtring";
		
		changestring(x);
		
		System.out.println("x string is" + x);
		
		StringBuilder xBuilder = new StringBuilder("xbuilder");
		
		int [] arrayint = new int[3];
		
		System.out.println(arrayint.getClass());
		
		// How to check if an array contains a certain value?(unsorted condition)
		
		String [] stringarray = new String[]{"CD", "BN","ED","DE","UI","89a","EE"};
		
		//large array
		String[] stringarray2 = new String[10000];
		
		Random random = new Random();
		
		for (int i = 0; i < 10000; i++) {
			
			stringarray2[i] = String.valueOf(random.nextInt());
			
		}
		
		long startTime = System.nanoTime();
		
		for (int i = 0; i < Math.pow(10, 4); i++) {
			
			//useList(stringarray, "D"); //Duration is 1842.5108
			//useSet(stringarray, "D");//Duration is 9352.3483
			useLoop(stringarray, "D");//Duration is 1677.1193
			
			
		}
		
		long duration = System.nanoTime() - startTime;
		
		System.out.println("Duration is " + duration / Math.pow(10, 4));
		
	}
	
	public static double mydevide(int x, int y){
		
		double z = (double)x/y;
		
		double pro = Math.round(z*100);
		
		return pro/100;
	}
	
	public static void changestring(String xString){
		
		xString = "changestring";
	}

	public static boolean useList(String[] arr, String targetValue){
		
		return Arrays.asList(arr).contains(targetValue);
	}

	public static boolean useSet(String[] arr, String targetValue){
		
		Set<String> set = new HashSet<String>(Arrays.asList(arr));
		
		return set.contains(targetValue);
	}

	public static boolean useLoop(String[] arr, String targetValue){
		
		for (String string : arr) {
			
			if (string.contains(targetValue)) {
				
				return true;
			}
		}
		return false;
	}
	
	public static boolean useArraysBinarySearch(String[] arr, String targetValue) {
		
		int a = Arrays.binarySearch(arr, targetValue);
		
		if (a > 0) {
			
			return true;
			
		}else {
			
			return false;
		}
	}
	
	
}

class A{
	
	A me(){
		
		return this;
	}
	
	public void doA(){
		
		System.out.println("Do-A");
	}
}

class B extends A {
	
	public void doB(){
		
		System.out.println("Do B");
	}
}


