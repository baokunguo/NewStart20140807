package zLearningClass;
import java.util.Vector;

public class LearningClassMonitorExam {

	/**
	 * @param args
	 * 
	 *            synchronized keyword is used for exclusive accessing. To make
	 *            a method synchronized, simply add the synchronized keyword to
	 *            its declaration. Then no two invocations of synchronized
	 *            methods on the same object can interleave with each other.
	 *            Synchronized statements must specify the object that provides
	 *            the intrinsic lock. When synchronized(this) is used, you have
	 *            to avoid to synchronizing invocations of other objects'
	 *            methods. wait() tells the calling thread to give up the
	 *            monitor and go to sleep until some other thread enters the
	 *            same monitor and calls notify( ). notify() wakes up the first
	 *            thread that called wait() on the same object.
	 * 
	 *            To enable collaboration of different threads, Java provide
	 *            wait() and notify() to suspend a thread and to wake up another
	 *            thread that are waiting on the object respectively. In
	 *            addition, there are 3 other versions:
	 */
	public static void main(String[] args) {
		System.out.println("this is ok");
		ThreadB b = new ThreadB();
		System.out.println("Thread B start");
		b.start();

		synchronized (b) {
			try {
				System.out.println("Waiting for b to complete...");
				b.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			System.out.println("Total is: " + b.total);
		}

		System.out.println("Another one start");
		ThreadBNOSyn bThreadBNOSyn = new ThreadBNOSyn();
		bThreadBNOSyn.start();
		System.out.println("BTotal is : " + bThreadBNOSyn.total);
		
		synchronized (bThreadBNOSyn) 
		{
			try {
				System.out.println("waiting for bthreadbnosyn");
				bThreadBNOSyn.wait();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println("BTotal is : " + bThreadBNOSyn.total);
	
		Producer producer = new Producer();
        producer.start();
        new Consumer(producer).start();
    
	}
}

class ThreadB extends Thread {
	int total;

	@Override
	public void run() {
		synchronized (this) {
			for (int i = 0; i < 10; i++) {
				System.out.println(total);
				total += i;				
			}
			System.out.println("start notify ..." + total);
			notify();
		}
	}
}

class ThreadBNOSyn extends Thread {
	int total;

	@Override
	public void run() {
		//synchronized (this)
		{
			for (int i = 0; i < 50; i++) {
				System.out.println(total+"\tinier\t " + i);
				total += i;				
			}
			System.out.println("start notifythreadb ..." + total);
			notify();
		}
	}
}


class Producer extends Thread {
 
    static final int MAXQUEUE = 5;
    private Vector messages = new Vector();
 
    @Override
    public void run() {
        try {
            while (true) {
                putMessage();
                //sleep(5000);
            }
        } catch (InterruptedException e) {
        }
    }
 
    private synchronized void putMessage() throws InterruptedException {
        while (messages.size() == MAXQUEUE) {
            wait();
        }
        messages.addElement(new java.util.Date().toString());
        System.out.println("put message");
        notify();
        //Later, when the necessary event happens, the thread that is running it calls notify() from a block synchronized on the same object.
    }
 
    // Called by Consumer
    public synchronized String getMessage() throws InterruptedException {
        notify();
        while (messages.size() == 0) {
            wait();//By executing wait() from a synchronized block, a thread gives up its hold on the lock and goes to sleep.
        }
        String message = (String) messages.firstElement();
        messages.removeElement(message);
        return message;
    }
}
 
class Consumer extends Thread {
 
    Producer producer;
 
    Consumer(Producer p) {
        producer = p;
    }
 
    @Override
    public void run() {
        try {
            while (true) {
                String message = producer.getMessage();
                System.out.println("Got message: " + message);
                //sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
}