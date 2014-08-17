package zLearningClass;

public class LearningClassSuperExam {

	/**
	 * @param args
	 * 
	 *            Within a class, a field that has the same name as a field in
	 *            the superclass hides the superclass��s field, even if their
	 *            types are different. Within the subclass, the field in the
	 *            superclass cannot be referenced by its simple name. Instead,
	 *            the field must be accessed through super. Generally speaking,
	 *            we don��t recommend hiding fields as it makes code difficult to
	 *            read.
	 * 
	 *            From this definition, member variables/class fields cannot be
	 *            overridden like methods. When sub- class defines a field with
	 *            same name, it just declares a new field. Therefore, they can
	 *            not be accessed polymorphically. They can not be overridden,
	 *            which also means they are hidden and can be access though some
	 *            ways.
	 * 
	 *            Why Java doesn��t provide default constructor, if class has a
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
 * 1. ����Ĺ��캯�����Ҫ����super�Ļ��������super���ں�������λ. class Base { Base() {
 * System.out.println("Base"); } }
 * 
 * public class Checket extends Base { Checket() {
 * super();//���ø���Ĺ��췽����һ��Ҫ���ڷ������׸���� System.out.println("Checket"); }
 * 
 * public static void main(String argv[]) { Checket c = new Checket(); } }
 * �������super�̳и��๹��ķ���
 * ������û�з��ڵ�һ�еĻ�����ô��super֮ǰ����䣬�϶���Ϊ�������Լ���Ҫ���ĳЩ��Ϊ����䣬����������super�̳и���Ĺ��췽��
 * ����ô��ǰ�������޸ľͶ��ص���ǰ�ˣ�����˵�ֳ��˸���Ĺ��췽���ˡ�
 * 
 * 2�� ��Java�У���ʱ�������������еĳ�Ա�����򷽷��볬�ࣨ��ʱҲ�Ƹ��ࣩ�еĳ�Ա�����򷽷�ͬ������Ϊ�����еĳ�Ա�����򷽷������ȼ��ߣ�
 * ���������е�ͬ����Ա�����򷽷��������˳���ĳ�Ա�����򷽷����������������Ҫʹ�ó����е������Ա�����򷽷�������Ҫ�õ�super. class
 * Country { String name;
 * 
 * void value() { name = "China"; } }
 * 
 * class City extends Country { String name;
 * 
 * void value() { name = "Hefei";
 * super.value();//�����ô˷���ʱ��super.name���ص��Ǹ���ĳ�Ա������ֵnull System.out.println(name);
 * System.out.println(super.name); }
 * 
 * public static void main(String[] args) { City c=new City(); c.value(); } }
 * Ϊ�������������ø����еĳ�Ա����name�ͷ���value
 * ()���ڴ�����ʹ����super��super.name��super.value(),��������super
 * .value()ʱ��super.name���ظ����Ա����Ĭ��ֵnull
 * ,���ô˷���ʱ��super.value()�����ѳ�Ա����name��ֵΪChina,������super.name���ø���ĳ�Ա������ֵ��
 * ���⣬Ҫע�����super.name���õ��ǳ�Ա������ֵ�� class Country { String name="xianfan";
 * 
 * String value(String name) { name = "China"; return name; } }
 * 
 * class City extends Country { String name;
 * 
 * String value(String name) { name = "Hefei";
 * super.value("ʧ��");//�����ô˷���ʱ��super.name���ص��Ǹ���ĳ�Ա������ֵnull
 * System.out.println(name); System.out.println(super.name); return name; }
 * 
 * public static void main(String[] args) { City c=new City(); c.value("�ɹ�"); }
 * } ���Ϊ��Hefei xianfan
 * ��ʱ��super.name���ص�ֵ�Ǹ����Ա������ֵxianfan,����ʱ��super.value()�����ǲ������õġ�
 * 
 * 3����superֱ�Ӵ��ݲ����� class Person { public static void prt(String s) {
 * System.out.println(s); }
 * 
 * Person() { prt("A Person."); }
 * 
 * Person(String name) { prt("A person name is:" + name); } }
 * 
 * public class Chinese extends Person { Chinese() { super(); // ���ø��๹�캯����1��
 * prt("A chinese.");// (4) }
 * 
 * Chinese(String name) { super(name);// ���ø��������ͬ�βεĹ��캯����2�� prt("his name is:" +
 * name); }
 * 
 * Chinese(String name, int age) { this(name);// ���õ�ǰ������ͬ�βεĹ��캯����3��
 * prt("his age is:" + age); }
 * 
 * public static void main(String[] args) { Chinese cn = new Chinese(); cn = new
 * Chinese("kevin"); cn = new Chinese("kevin", 22); } } ���Ϊ��A Person. A chinese.
 * A person name is:kevin his name is:kevin A person name is:kevin his name
 * is:kevin his age is:22
 * ����γ����У�this��super����������ǰ�����á�.������һ���������Ա������ֱ�����������ʵ��Ĳ����������������Ҳ�����˱仯
 * ��super��Ӳ��������������ø����о�����ͬ��ʽ�Ĺ��캯��
 * ����1��2����this��Ӳ�������õ��ǵ�ǰ������ͬ�����Ĺ��캯������3������Ȼ����Chinese�ĸ������ع��캯����
 * ��this��super��һ�㷽���еĸ����÷�Ҳ�Կ�ʹ��
 * ������4��������Խ����滻Ϊ��this.prt��(��Ϊ���̳��˸����е��Ǹ������������ǡ�super.prt
 * ������Ϊ���Ǹ����еķ����ҿɱ�������ʣ���������������ȷ���С��������ƺ����е㻭�������ζ���ˡ�
 * 
 * 4��super��this����ͬ�� 1)super�������������û����е�ĳһ�����캯����Ӧ��Ϊ���캯���еĵ�һ����䣩
 * 2)this�������������ñ�������һ���γɵĹ��캯����Ӧ��Ϊ���캯���еĵ�һ����䣩
 * 3)super:�������õ�ǰ�����ֱ�Ӹ����еĳ�Ա����������ֱ�Ӹ����б����صĸ����г�Ա���ݻ�����������������������ͬ��Ա����ʱ�磺super.������
 * super.��Ա����������ʵ�Σ�
 * 4)this��������ǰ���������ڳ������ײ���������֮����Ӧʹ��this��ָ����ǰ��������������β������еĳ�Ա����ͬ��
 * ����ʱ����this��ָ����Ա��������
 * 
 * 
 * 
 * 5������super()����д�����๹�췽���ĵ�һ�У�������벻ͨ����ÿ�����๹�췽���ĵ�һ����䣬���������ص���super()���������û��������ʽ�Ĺ��캯��
 * ����ô�ڱ����ʱ��ͻᱨ�� ����
 * 6��super()��this()����,�����ǣ�super()�������е��ø���Ĺ��췽����this()��ͬһ���ڵ������������� ����
 * 7��super()��this()������ڹ��췽���ڵ�һ�С� ���� 8�����ܿ�����this����һ������������ȴ���ܵ��������� ����
 * 9��this��super����ͬʱ������һ�����캯������
 * ����Ϊthis��Ȼ����������Ĺ��캯���������Ĺ��캯����ȻҲ����super���Ĵ��ڣ�������ͬһ�����캯����������ͬ�����
 * ����ʧȥ���������壬������Ҳ����ͨ���� ����
 * 10��this()��super()��ָ���Ƕ������ԣ�����������static������ʹ�á�������static����,static������static���顣
 * ���� 11���ӱ����Ͻ���this��һ��ָ�򱾶����ָ��, Ȼ��super��һ��Java�ؼ��֡�
 */