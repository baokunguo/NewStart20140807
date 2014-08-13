package zLearningClass;

public class LearningClassInheritanceVsCompositonExam {

	/**
	 * @param args
	 * 
	 *            Different kinds of attack can be defined by implementing the
	 *            Attack interface.
	 * 
	 *            • If there is an IS-A relation, and a class wants to expose
	 *            all the interface to another class, inheritance is likely to
	 *            be preferred. 
	 *            • If there is a HAS-A relationship, composition is preferred.
	 */
	public static void main(String[] args) {
		Inset inset = new Bee(23, "ui");
		System.out.println("begin attacker .....");
		inset.attack();

		Bee bee = new Bee(89, "wewe");
		System.out.println("begin attacker .....22");
		bee.attack();

		System.out.println("better one \n");
		BeeBetter aBeeBetter = new BeeBetter(1, "black", new AttackBetterImp(
				"fly", "flymove"));
		aBeeBetter.attack();

	}

}

/*
 * Better One
 */
class InsetBetter {

	private int size;

	private String color;

	public InsetBetter(int size, String color) {

		this.size = size;

		this.color = color;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
}

interface Attack {

	public void move();

	public void attack();

}

class AttackBetterImp implements Attack {

	private String move;

	private String attackbetterString;

	public AttackBetterImp(String moved, String attacked) {

		this.move = moved;

		this.attackbetterString = attacked;
	}

	@Override
	public void move() {

		System.out.println(move);
	}

	@Override
	public void attack() {

		move();

		System.out.println(attackbetterString);
	}
}

class BeeBetter extends InsetBetter implements Attack {

	private Attack attack;

	public BeeBetter(int size, String color, Attack attack) {

		super(size, color);

		this.attack = attack;
	}

	public void move() {

		attack.move();
	}

	public void attack() {

		attack.attack();
	}
}

/*
 * Bad One
 */
class Inset {

	private int size;

	private String colorString;

	public Inset(int size, String coloString) {

		this.size = size;

		this.colorString = coloString;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getColorString() {
		return colorString;
	}

	public void setColorString(String colorString) {
		this.colorString = colorString;
	}

	public void move() {

		System.out.println("move method ");
	}

	public void attack() {

		move();

		System.out.println("attacking the first");
	}

}

class Bee extends Inset {

	public Bee(int size, String colorString) {
		super(size, colorString);
	}

	public void move() {

		System.out.println("Fly here bee");
	}

	public void attack() {

		move();

		System.out.println("Bee attack ..... \t ");

		super.attack();
	}
}
