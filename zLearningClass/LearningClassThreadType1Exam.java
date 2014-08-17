package zLearningClass;

public class LearningClassThreadType1Exam extends Thread {

	/**
	 * @param args
	 *            �����������������ɽ���ʽ���С���ôΪɶ��Ҫʹ��start();�����������߳��أ�
	 * 
	 *            ��JDK�İ�װ·���£�src.zip��ȫ����javaԴ����ͨ���˴����ҵ�Thread�е�start()�����Ķ��壬
	 *            ���Է��ִ˷�����ʹ����private native void
	 *            start0();����native�ؼ��ֱ�ʾ���Ե��ò���ϵͳ�ĵײ㺯������ô�����ļ�����ΪJNI������java Native
	 *            Interface��
	 */

	private String name;
	private int ticketnum = 30;

	public LearningClassThreadType1Exam(String nameString) {
		this.name = nameString;
	}

	public void run() {
		for (int i = 0; i < 20; i++) {
			System.out.println("Thread Start : " + this.name + " i = " + i);
		}
		for (int i = 0; i < this.ticketnum; i++) {
			if (this.ticketnum > 0) {
				System.out.println("sell ticket: " + this.ticketnum--);
			}
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LearningClassThreadType1Exam learningClassThreadType1Exam1 = new LearningClassThreadType1Exam(
				"thread number 1");
		LearningClassThreadType1Exam learningClassThreadType1Exam2 = new LearningClassThreadType1Exam(
				"THREAD NUMBER 2");
		/*
		 * System.out.println("\nstart first one \n ");
		 * learningClassThreadType1Exam1.run();
		 * System.out.println("\nstart second one \n ");
		 * learningClassThreadType1Exam2.run();
		 */

		System.out.println("....\nstart run first one \n ");
		learningClassThreadType1Exam1.start();
		System.out.println("\nstart run second one \n ");
		learningClassThreadType1Exam2.start();
		/*
		 * hread Start : THREAD NUMBER 2 i = 93 Thread Start : THREAD NUMBER 2 i
		 * = 94 Thread Start : THREAD NUMBER 2 i = 95 Thread Start : thread
		 * number 1 i = 116 Thread Start : thread number 1 i = 117 Thread Start
		 * : thread number 1 i = 118 Thread Start : thread number 1 i = 119
		 * Thread Start : thread number 1 i = 120 Thread Start : thread number 1
		 * i = 121 Thread Start : thread number 1 i = 122 Thread Start : thread
		 * number 1 i = 123 Thread Start : thread number 1 i = 124 Thread Start
		 * : THREAD NUMBER 2 i = 96 Thread Start : THREAD NUMBER 2 i = 97 Thread
		 * Start : THREAD NUMBER 2 i = 98 Thread Start : THREAD NUMBER 2 i = 99
		 */
	}

}
