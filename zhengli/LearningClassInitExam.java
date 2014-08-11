package zhengli;

public class LearningClassInitExam {
	/*
	 * Of course, such code could be written in constructors. But if a class had multiple constructors, you
		would have to repeat the code in each constructor.
		With an instance initializer, you can just write the code once, and it will be executed no matter what
		constructor is used to create the object. (I guess this is just a concept, and it is not used often.)

	 * 
	 * 
	 * */
	public static void main(String[] args) {

		new Foo();

		System.out.println("...................\n");

		new Foo();
	}
}

class Foo {
	// instance
	String string = "Inita 0000";

	int i = 1;

	// constructor
	public Foo() {

		i++;

		System.out.println("Constructor called" + i);

	}

	// static initializer
	static {

		System.out.println("static initializer called");

	}

	// instance initializer
	{
		i++;

		System.out.println("instance initializer called" + i);
	}

}
