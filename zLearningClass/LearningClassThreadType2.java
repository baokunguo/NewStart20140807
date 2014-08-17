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
	 * ������ʹ��Runnable�����������û��start()������ֻ��Thread���в��С���ʱ�۲�Thread�࣬��һ�����췽����public
	 * Thread(Runnable *
	 * targer)�˹��췽������Runnable������ʵ����Ҳ����˵����ͨ��Thread��������Runnableʵ�ֵĶ��߳�
	 * ����start()����Э��ϵͳ����Դ����
	 * �ڳ��򿪷���ֻҪ�Ƕ��߳̿϶���Զ��ʵ��Runnable�ӿ�Ϊ������Ϊʵ��Runnable�ӿ���ȼ̳�Thread�������ºô���
	 * 
	 * �����̳еľ��ޣ�һ������Լ̳ж���ӿڡ� �ʺ�����Դ�Ĺ���
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
		 * ��Ȼ���ڳ������������̣߳�����һ������10��Ʊ��Ҳ����˵ʹ��Runnableʵ�ֶ��߳̿��Դﵽ��Դ����Ŀ�ġ�
		 * Runnable�ӿں�Thread֮�����ϵ��
		 * 
		 * public class Thread extends Object implements Runnable
		 * 
		 * ����Thread��Ҳ��Runnable�ӿڵ�����
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
    super.value();//�����ô˷���ʱ��super.name���ص��Ǹ���ĳ�Ա������ֵnull
       System.out.println(name);
       System.out.println(super.name);
    }
 
    public static void main(String[] args) {
       City c=new City();
       c.value();
       }
}


