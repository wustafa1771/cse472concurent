
public class GrabNumbers {
	
	static final int numProducers = 3;
	static final int numGrabbers = 3;
	static final int sleepUnit = 1;
	static int maxNumbersToProduce = 30;
	
	public static void main(String[] args) {

		Producer producers[] = new Producer[ numProducers ];
		Thread producerThreads[] = new Thread[ numProducers ];
		Thread grabbers[] = new Thread[ numGrabbers ];
		
		System.out.println( "Creating the producers and associated threads..." );
		for( int i=0; i<numProducers; i++ ){
			producers[ i ] = new Producer();
			producerThreads[ i ] = new Thread( producers[ i ], "Producer_" + i );

			producerThreads[ i ].start();
		}	
		System.out.println( "Creating the grabbers..." );
		for( int i=0; i<numGrabbers; i++ ){
			grabbers[ i ] = new Thread( new Grabber( producers ), "Grabber_" + i );
			grabbers[ i ].start();
		}
	}
}
