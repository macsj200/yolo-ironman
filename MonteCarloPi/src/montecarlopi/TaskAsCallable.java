package montecarlopi;

import java.util.Random;
import java.util.concurrent.Callable;

public class TaskAsCallable implements Callable<Integer> {
	private final double R = 1.0;
	private int numTrials = 0;
	private int numHits = 0;
	private Random generator = null;

	public TaskAsCallable(int numTrials){
		this.numTrials = numTrials;
		generator = new Random(System.currentTimeMillis());
	}

	public int calcNumHits(){
		Point tempPoint = null;

		for(int i = 0; i < numTrials; i++){
			tempPoint = new Point(generator.nextDouble() * R, generator.nextDouble() * R);
			if(isInCircle(tempPoint)){
				numHits = numHits + 1;
			}
			else{
			}
		}
		return numHits;
	}

	public boolean isInCircle(Point p){
		if(p.x() * p.x() + p.y() * p.y() < R * R){
			return true;
		}
		else{
			return false;
		}
	}

	public Integer call(){
		return calcNumHits();
	}
	
	public int getChunkSize(){
		return numTrials;
	}
}
