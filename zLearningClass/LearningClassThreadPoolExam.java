package zLearningClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LearningClassThreadPoolExam {

	/**
	 * http://developer.51cto.com/art/200907/140261_all.htm
	 * http://ifeve.com/java-multi-threading-concurrency-interview-questions-with-answers/
	 * http://www.importnew.com/1428.html
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ExecutorService threadpool = Executors.newCachedThreadPool();
		System.out.println("start thread pool ...");
		for (int i = 1; i < 10; i++) {
			final int taskId = i;
			threadpool.execute(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					for (int j = 0; j < 10; j++) {
						System.out.println(Thread.currentThread().getName()
								+ " is looping of " + j + " taskid " + taskId);
						try {
							Thread.sleep(200);
						} catch (Exception e) {
							// TODO: handle exception
							e.printStackTrace();
						}
					}
				}
			});
		}

		System.out.println("add all 10 task");
		threadpool.shutdown();

		System.out.println("threadpool shutdown");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 时间格式
		Executors.newScheduledThreadPool(3).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				// Date nowDate = new Date();//得到当前时间
				String date = sdf.format(new Date());
				System.out.println("boolming ... " + " Time "
						+ System.currentTimeMillis() + " time " + date);

			}
		}, 12, 2, TimeUnit.SECONDS);

	}

}

class multiThreadExamp {

	public static void main(String[] args) {
		System.out.println("multi thread example");
		ExecutorService ThreadPool = Executors.newCachedThreadPool();
		for (int i = 0; i < 10; i++) {
			final int threadId = i;
			ThreadPool.execute(new Runnable() {

				@Override
				public void run() {
					/*
					System.out.println("Thread name : " + Thread.currentThread().getName()
							+" current time " + new Date(System.currentTimeMillis())
							+ " thread id " + threadId);
					*/
					for (int j = 0; j < 10; j++) {
						try {
							Thread.sleep(100);
							System.out.println("thread sleep " + String.valueOf(j));

							System.out.println("Thread name : " + Thread.currentThread().getName()
									+" current time " + new Date(System.currentTimeMillis())
									+ " thread id " + threadId
									+ " j " + j);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
		}
	}
}
