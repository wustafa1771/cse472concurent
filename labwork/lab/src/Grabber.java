import java.util.Random;

public class Grabber implements Runnable{
	
	int score = 0;
	int tmpnum;
	Producer[] producers;
	
	Random randomGenerator = new Random();
	
	public Grabber( Producer[] producers ) {
		this.producers = producers;
	}
	
	public void run() {
		while( Producer.totalcount<GrabNumbers.maxNumbersToProduce ) {//normalde available numbera da bakýlýr
			//System.out.println("graberloop");
			Producer.checkAvailableNumbers();
			for(int i=0;i<3;i++){
				int musti=producers[i].grabTheNumber();
				if(musti!=-1){
					System.out.println(Thread.currentThread().getName()+" grabs    "+musti);
					score++;
					break;
				}
			}
		}
		System.out.println(Thread.currentThread().getName()+" scores    "+score);
	}	
}