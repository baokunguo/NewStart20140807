package zhengli;

public class LearningClassOverExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		new Hound().bark();
		
		new Hound().sniff();
	}

}

class Dog{
	
	public void bark(){
		
		System.out.println("Woof....");
	}
}

class Hound extends Dog{
	
	public void sniff(){
		
		System.out.println("Sniff>>>");
	}
	
	public void bark(){
		
		System.out.println("bowlss");
		
	}

}