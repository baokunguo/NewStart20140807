package zLearningClass;

public class LearningClassExceptionExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		System.out.println("Here start ....");
		callDoOne();
		System.out.println("Here stop ....");
		
	}
	
	private static Exception exception;
	
	public static void doOne() throws Exception {
		System.out.println("doOneException");
		//throw exception;
	}
	
	public static void callDoOne() throws Exception {
		System.out.println("callDoOne Exception1");
		doOne();
		System.out.println("callDoOne Exception2");
	}

}
