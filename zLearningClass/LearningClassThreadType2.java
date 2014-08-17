package zLearningClass;

import java.util.Date;

public class LearningClassThreadType2 implements Runnable {

	/**
	 * @param args
	 */
	private String nameString;
	private long date;
	private int ticketnum = 10;

	public LearningClassThreadType2(String name, long date) {
		this.nameString = name;
		this.date = date;
	}

	public void run() {
		for (int i = 0; i < 10; i++) {
			System.out.println("thread start " + this.nameString + " i= " + i
					+ " date: " + date);
		}
		for (int i = 0; i < this.ticketnum; i++) {
			if (this.ticketnum > 0) {
				System.out.println("sell ticket: " + this.ticketnum--);
			}
		}
		/*
		 * try { System.out.println("\n wait start \n"); wait(10000); } catch
		 * (InterruptedException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
	}

	/*
	 * 但是在使用Runnable定义的子类中没有start()方法，只有Thread类中才有。此时观察Thread类，有一个构造方法：public
	 * Thread(Runnable *
	 * targer)此构造方法接受Runnable的子类实例，也就是说可以通过Thread类来启动Runnable实现的多线程
	 * 。（start()可以协调系统的资源）：
	 * 在程序开发中只要是多线程肯定永远以实现Runnable接口为主，因为实现Runnable接口相比继承Thread类有如下好处：
	 * 
	 * 避免点继承的局限，一个类可以继承多个接口。 适合于资源的共享
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Thread thread = new Thread(LearningClassThreadType2);
		LearningClassThreadType2 lct1 = new LearningClassThreadType2(
				"thread aa", new Date().getTime());
		LearningClassThreadType2 lct2 = new LearningClassThreadType2(
				"thread bb".toUpperCase(), new Date().getTime());
		System.out.println("thread start ...");
		new Thread(lct1).start();
		/*
		 * System.out.println("thread start2 ..."); new Thread(lct2).start();
		 */
		new Thread(lct1).start();
		new Thread(lct1).start();
		/*
		 * 虽然现在程序中有三个线程，但是一共卖了10张票，也就是说使用Runnable实现多线程可以达到资源共享目的。
		 * Runnable接口和Thread之间的联系：
		 * 
		 * public class Thread extends Object implements Runnable
		 * 
		 * 发现Thread类也是Runnable接口的子类
		 */
	}
	/*
	 * sample output: thread start thread aa i= 73 thread start thread aa i= 74
	 * thread start thread aa i= 75 thread start thread aa i= 76 thread start
	 * THREAD BB i= 0 thread start THREAD BB i= 1 thread start THREAD BB i= 2
	 * thread start THREAD BB i= 3 thread start THREAD BB i= 4
	 */

}
class Country {
    String name;
 
    void value() {
       name = "China";
    }
}
 
class City extends Country {
    String name;
 
    void value() {
    name = "Hefei";
    super.value();//不调用此方法时，super.name返回的是父类的成员变量的值null
       System.out.println(name);
       System.out.println(super.name);
    }
 
    public static void main(String[] args) {
       City c=new City();
       c.value();
       }
}


