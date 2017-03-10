import java.util.Random;

public class Producer implements Runnable{
	
	int myNumber = -1;
	int num =-1;
	static boolean gameOver = false;
	static boolean consumed = true;
	public Random rnd = new Random();
	static int count=0;
	static int totalcount=0;
	int score=0;

	Random randomGenerator = new Random();
	
	public void run(){
		while ( totalcount<GrabNumbers.maxNumbersToProduce ){
			produce();
			score++;
		}
		System.out.println(Thread.currentThread().getName()+" scores "+score);
	}
	
	public synchronized void produce() {
		while(!consumed){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		synchronized(Producer.class){
			if(totalcount<GrabNumbers.maxNumbersToProduce ){
				num =rnd.nextInt(100);
				System.out.println(Thread.currentThread().getName()+" creates "+num);
				consumed=false;
				incrementNumbers();
			}
		}
	}

	public static synchronized void incrementNumbers() {
		totalcount++;
		if(totalcount==GrabNumbers.maxNumbersToProduce)
			System.out.println("total count limit reached");
		count+=1;
		Producer.class.notifyAll();

	}
	
	public static synchronized void checkAvailableNumbers() {
		while(count==0&&totalcount<10){
			try {
				Producer.class.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public synchronized int grabTheNumber() {
		if(num!=-1){
			int tmp=num;
			decrementAvailableNumbers();
			notifyAll();
			return tmp;
		}else{
			notifyAll();
			return -1;
		}
	}
	
	public synchronized void decrementAvailableNumbers() {	

		num= -1;
		count--;
		consumed=true;
	}
	
}
