package zhengli;

import java.util.HashMap;

public class LearningClassEqualExam {

	/**
	 * @param args
	 * 
	 * The problem is caused by the un-overridden method "hashCode()". The contract between equals()
		and hasCode() is that: 
		1. If two objects are equal, then they must have the same hash code. 
		2. If two objects have the same hashcode, they may or may not be equal.
		
		The idea behind a Map is to be able to find an object faster than a linear search. Using hashed keys
		to locate objects is a two-step process. Internally the Map stores objects as an array of arrays. The
		index for the first array is the hashcode() value of the key. This locates the second array which is
		searched linearly by using equals() to determine if the object is found.

	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Apple a1 = new Apple("green");
		
		Apple a2 = new Apple("read");
		
		HashMap<Apple, Integer> map = new HashMap<Apple,Integer>();
		
		map.put(a1, 12);
		
		map.put(a2, 89);
		
		System.out.println(map.get(new Apple("green")));// output 12
		
		//System.out.println(map.get("green")); // output null
	}

}

class Apple {
	
	private String color;
	
	public Apple(String color){
		
		this.color = color;
	}
	/*
	 * Hash Code is like a sequence of garages for storage, different stuff can be stored in different garages.
		It is more efficient if you organize stuff to different place instead of the same garage. So it¡¯s a good
		practice to equally distribute the hashCode value. (Not the main point here though)

	 * */
	public int hashCode(){
		
		return this.color.hashCode();
	}
	
	public boolean equals(Object object) {
		
		if (!(object instanceof Apple)) {
			
			return false;		
		}
		
		if (object == this) {
			
			return true;
		}
		
		return this.color.equals(((Apple)object).color);
	}
}
