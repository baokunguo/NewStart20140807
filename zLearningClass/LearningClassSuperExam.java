package zLearningClass;

public class LearningClassSuperExam {

	/**
	 * @param args
	 * 
	 *            Within a class, a field that has the same name as a field in
	 *            the superclass hides the superclass¡¯s field, even if their
	 *            types are different. Within the subclass, the field in the
	 *            superclass cannot be referenced by its simple name. Instead,
	 *            the field must be accessed through super. Generally speaking,
	 *            we don¡¯t recommend hiding fields as it makes code difficult to
	 *            read.
	 * 
	 *            From this definition, member variables/class fields cannot be
	 *            overridden like methods. When sub- class defines a field with
	 *            same name, it just declares a new field. Therefore, they can
	 *            not be accessed polymorphically. They can not be overridden,
	 *            which also means they are hidden and can be access though some
	 *            ways.
	 * 
	 *            Why Java doesn¡¯t provide default constructor, if class has a
	 *            constructor with parameter(s)? Some answers:
	 *            http://stackoverflow.com/q/16046200/127859
	 */
	public static void main(String[] args) {

		Sub c1 = new Sub();

		System.out.println(c1.string + "\t" + c1.substring);

		Super c2 = new Sub();

		System.out.println(c2.string + "\t");

		Sub2super sub2super = new Sub2super("sub2supering");

	}

}

class Super {

	String string = "super class here";
}

class Sub extends Super {

	String string = "sub class here";

	String substring = "this is substring";
}

class Super2 {

	String string;

	public Super2(String stringsString) {

		this.string = stringsString;

		System.out.println("Supers sub\tSupers sub\t");
	}
}

class Sub2super extends Super2 {

	int x = 200;

	public Sub2super(String string) {

		super(string);
	}
}