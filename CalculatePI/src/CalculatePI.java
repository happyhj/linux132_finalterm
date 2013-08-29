import java.util.Random;

public class CalculatePI implements Runnable {
	static final int MAX = 500000000;
	static final int NT = 80;
	static int id = 0;
	static long innerDotCounter = 0;

	public void run() {
		long local_innerDotCounter = 0;
		long startDotIndex,endDotIndex;
	    Random Random = new Random();
	
		// setting start point
		// 쓰레드들이 선착순으로 계산할 구간을 찜한다. 
		synchronized(this) {
			startDotIndex = (MAX/NT) * id;
			endDotIndex = MAX/NT * (id + 1) - 1;
			id++;
		}
	
		for (long i=startDotIndex ; i <= endDotIndex ; i++) {
			Position dot = new Position(Random.nextFloat(),Random.nextFloat());
			if(dot.distanceFromOrigin<=1) {
				local_innerDotCounter++;
			}
		}
		
		synchronized(this) {
			innerDotCounter = innerDotCounter + local_innerDotCounter;
		}
	}

	public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

   	 
		CalculatePI calculatePI = new CalculatePI();
		Thread[] at = new Thread[NT];

		for(int i=0; i<NT; i++) {
			at[i]= new Thread(calculatePI);
			at[i].start();
		}

		for(Thread t:at)
			t.join();

	    long endTime = System.currentTimeMillis();
		System.out.println(calculatePI.innerDotCounter*4.0/(double)MAX);
		System.out.println("##  소요시간(초.0f) : " + ( endTime - startTime )/1000.0f +"초"); 
	}
}
