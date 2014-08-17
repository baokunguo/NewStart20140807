package zLearningClass;

public class LearningClassSuperExam {

	/**
	 * @param args
	 * 
	 *            Within a class, a field that has the same name as a field in
	 *            the superclass hides the superclass’s field, even if their
	 *            types are different. Within the subclass, the field in the
	 *            superclass cannot be referenced by its simple name. Instead,
	 *            the field must be accessed through super. Generally speaking,
	 *            we don’t recommend hiding fields as it makes code difficult to
	 *            read.
	 * 
	 *            From this definition, member variables/class fields cannot be
	 *            overridden like methods. When sub- class defines a field with
	 *            same name, it just declares a new field. Therefore, they can
	 *            not be accessed polymorphically. They can not be overridden,
	 *            which also means they are hidden and can be access though some
	 *            ways.
	 * 
	 *            Why Java doesn’t provide default constructor, if class has a
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

/*
 * 1. 子类的构造函数如果要引用super的话，必须把super放在函数的首位. class Base { Base() {
 * System.out.println("Base"); } }
 * 
 * public class Checket extends Base { Checket() {
 * super();//调用父类的构造方法，一定要放在方法的首个语句 System.out.println("Checket"); }
 * 
 * public static void main(String argv[]) { Checket c = new Checket(); } }
 * 如果想用super继承父类构造的方法
 * ，但是没有放在第一行的话，那么在super之前的语句，肯定是为了满足自己想要完成某些行为的语句，但是又用了super继承父类的构造方法
 * 。那么以前所做的修改就都回到以前了，就是说又成了父类的构造方法了。
 * 
 * 2． 在Java中，有时还会遇到子类中的成员变量或方法与超类（有时也称父类）中的成员变量或方法同名。因为子类中的成员变量或方法名优先级高，
 * 所以子类中的同名成员变量或方法就隐藏了超类的成员变量或方法，但是我们如果想要使用超类中的这个成员变量或方法，就需要用到super. class
 * Country { String name;
 * 
 * void value() { name = "China"; } }
 * 
 * class City extends Country { String name;
 * 
 * void value() { name = "Hefei";
 * super.value();//不调用此方法时，super.name返回的是父类的成员变量的值null System.out.println(name);
 * System.out.println(super.name); }
 * 
 * public static void main(String[] args) { City c=new City(); c.value(); } }
 * 为了在子类中引用父类中的成员变量name和方法value
 * ()，在代码中使用了super、super.name和super.value(),若不调用super
 * .value()时，super.name返回父类成员变量默认值null
 * ,调用此方法时，super.value()方法把成员变量name赋值为China,再利用super.name调用父类的成员变量的值。
 * 另外，要注意的是super.name调用的是成员变量的值， class Country { String name="xianfan";
 * 
 * String value(String name) { name = "China"; return name; } }
 * 
 * class City extends Country { String name;
 * 
 * String value(String name) { name = "Hefei";
 * super.value("失败");//不调用此方法时，super.name返回的是父类的成员变量的值null
 * System.out.println(name); System.out.println(super.name); return name; }
 * 
 * public static void main(String[] args) { City c=new City(); c.value("成功"); }
 * } 结果为：Hefei xianfan
 * 此时，super.name返回的值是父类成员变量的值xianfan,而此时的super.value()方法是不起作用的。
 * 
 * 3．用super直接传递参数： class Person { public static void prt(String s) {
 * System.out.println(s); }
 * 
 * Person() { prt("A Person."); }
 * 
 * Person(String name) { prt("A person name is:" + name); } }
 * 
 * public class Chinese extends Person { Chinese() { super(); // 调用父类构造函数（1）
 * prt("A chinese.");// (4) }
 * 
 * Chinese(String name) { super(name);// 调用父类具有相同形参的构造函数（2） prt("his name is:" +
 * name); }
 * 
 * Chinese(String name, int age) { this(name);// 调用当前具有相同形参的构造函数（3）
 * prt("his age is:" + age); }
 * 
 * public static void main(String[] args) { Chinese cn = new Chinese(); cn = new
 * Chinese("kevin"); cn = new Chinese("kevin", 22); } } 结果为：A Person. A chinese.
 * A person name is:kevin his name is:kevin A person name is:kevin his name
 * is:kevin his age is:22
 * 在这段程序中，this和super不再是像以前那样用“.”连接一个方法或成员，而是直接在其后跟上适当的参数，因此它的意义也就有了变化
 * 。super后加参数的是用来调用父类中具有相同形式的构造函数
 * ，如1和2处。this后加参数则调用的是当前具有相同参数的构造函数，如3处。当然，在Chinese的各个重载构造函数中
 * ，this和super在一般方法中的各种用法也仍可使用
 * ，比如4处，你可以将它替换为“this.prt”(因为它继承了父类中的那个方法）或者是“super.prt
 * ”（因为它是父类中的方法且可被子类访问），它照样可以正确运行。但这样似乎就有点画蛇添足的味道了。
 * 
 * 4．super和this的异同： 1)super（参数）：调用基类中的某一个构造函数（应该为构造函数中的第一条语句）
 * 2)this（参数）：调用本类中另一种形成的构造函数（应该为构造函数中的第一条语句）
 * 3)super:　它引用当前对象的直接父类中的成员（用来访问直接父类中被隐藏的父类中成员数据或函数，基类与派生类中有相同成员定义时如：super.变量名
 * super.成员函数据名（实参）
 * 4)this：它代表当前对象名（在程序中易产生二义性之处，应使用this来指明当前对象；如果函数的形参与类中的成员数据同名
 * ，这时需用this来指明成员变量名）
 * 
 * 
 * 
 * 5）调用super()必须写在子类构造方法的第一行，否则编译不通过。每个子类构造方法的第一条语句，都是隐含地调用super()，如果父类没有这种形式的构造函数
 * ，那么在编译的时候就会报错。 　　
 * 6）super()和this()类似,区别是，super()从子类中调用父类的构造方法，this()在同一类内调用其它方法。 　　
 * 7）super()和this()均需放在构造方法内第一行。 　　 8）尽管可以用this调用一个构造器，但却不能调用两个。 　　
 * 9）this和super不能同时出现在一个构造函数里面
 * ，因为this必然会调用其它的构造函数，其它的构造函数必然也会有super语句的存在，所以在同一个构造函数里面有相同的语句
 * ，就失去了语句的意义，编译器也不会通过。 　　
 * 10）this()和super()都指的是对象，所以，均不可以在static环境中使用。包括：static变量,static方法，static语句块。
 * 　　 11）从本质上讲，this是一个指向本对象的指针, 然而super是一个Java关键字。
 */