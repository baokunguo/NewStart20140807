package zhengli;

public class LearningClassComparable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HDTV tv1 = new HDTV(55,"samsung");
		
		HDTV tv2 = new HDTV(98,"Sony");
		
		if (tv1.compareTo(tv2) > 0) {
			
			System.out.println(tv1.getBrand());
			
		} else {
			
			System.out.println(tv2.getBrand());
		}
	}

}

class HDTV implements Comparable<HDTV> {
	
	private int size;
	
	private String brand;
	
	public HDTV(int size, String brand){
		
		this.size = size;
		
		this.brand = brand;
	}
	
	@Override
	public int compareTo(HDTV tv) {
		
		if (this.getSize() > tv.getSize()) {
			
			return 1;
		}
		else if (this.getSize() < tv.getSize()) {
				
				return -1;
			}
		else {
			return 0;
		}
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
}
