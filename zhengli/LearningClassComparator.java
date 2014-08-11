package zhengli;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class LearningClassComparator {

	/**
	 * @param args
	 * 	In brief, a class that implements Comparable will be comparable, which means it instances can be
		compared with each other.
		A class that implements Comparator will be a Comparator for some other class. It 1) can be passed
		to a sort method, such as Collections.sort() or Arrays.sort(), to allow precise control over the sort
		order and 2) can also be used to control the order of certain data structures, such as sorted sets or
		sorted maps.

	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		HTV tv1 = new HTV(34,"Samsung");
		
		HTV tv2 = new HTV(12,"sona");
		
		HTV tv3 = new HTV(89,"asd");
		
		ArrayList<HTV> aList = new ArrayList<HTV>();
		
		aList.add(tv1);
		
		aList.add(tv2);
		
		aList.add(tv3);
		
		Collections.sort(aList, new SizeComparator());
		
		//Collections.reverseOrder(new SizeComparator());
		
		int i = 0;
		
		for (HTV htv : aList) {
			
			System.out.println("I " + i +"  "+htv.getBrand() + "\t" + htv.getSize());
			
			i++;
		}

	}

}

class HTV {
	
	private int size;
	
	private String Brand;
	
	public HTV(int size, String brand){
		
		this.size = size;
		
		this.Brand = brand;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getBrand() {
		return Brand;
	}

	public void setBrand(String brand) {
		Brand = brand;
	}
}

class SizeComparator implements Comparator<HTV> {
	
	@Override
	
	public int compare(HTV tv1, HTV tv2){
		
		int tv1size = tv1.getSize();
		
		int tv2size = tv2.getSize();
		
		if (tv1size > tv2size) {
			
			return 1;
			
		} else if (tv1size < tv2size) {
			
			return -1;
			
		} else {
			
			return 0;
		}
	}
}