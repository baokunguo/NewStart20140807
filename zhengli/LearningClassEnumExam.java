package zhengli;

public class LearningClassEnumExam {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (Color color : Color.values()) {
			
			System.out.println(color+ "******");
		}
		
		for (Color2 color2 : Color2.values()) {
			color2.printValue();
		}
	}
	
	public enum Color{
		
		RED,YELLOW,BLUE;// each is an instance of Color
	}
	
	public enum Color2{
		
		RED(1), YELLOW(2), BLUE(3);
		
		private int value;
		
		private Color2(){}
		
		private Color2 (int i) {
			
			this.value = i;
		}
		
		public void printValue(){
			
			System.out.println(this.value);
		}
	}
}


