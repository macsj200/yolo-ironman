package montecarlopi;

import gui.MonteCarloGui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class TesterClass {
	public static int[] schedule(int numJobs, int numWorkers){
		int[] ret = new int[numWorkers];
		final int chunk = numJobs / numWorkers;
		
		int tempChunk;
		for(int i = 0; i < numWorkers; i++){
			tempChunk = chunk;
			
			if(i < numJobs % numWorkers){
				tempChunk = tempChunk + 1;
			}
			
			ret[i] = tempChunk;
		}
		
		return ret;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException{
		final int numTrials = 1000000000;
		final int numWorkers = 4;
		final int[] masterSchedule = schedule(numTrials, numWorkers);
		
		Hashtable<String, Double> stats = new Hashtable<String, Double>();

		int totalNumHits = 0;

		double pi = 0.0;

		ExecutorService executorService = Executors.newFixedThreadPool(numWorkers);

		List<Callable<Integer>> taskList = new ArrayList<Callable<Integer>>();

		TaskAsCallable tempWorkerRef;
		
		for(int i = 0; i < numWorkers; i++){
			tempWorkerRef = new TaskAsCallable(masterSchedule[i]);
			taskList.add(tempWorkerRef);
		}
		
		long time = System.currentTimeMillis();
		List<Future<Integer>> taskResults = executorService.invokeAll(taskList);
		
		for(int i = 0; i < numWorkers; i++){
			totalNumHits = totalNumHits + taskResults.get(i).get();
		}

		pi = (totalNumHits * 4.0) / numTrials;
		time = System.currentTimeMillis() - time;
		
		stats.put("workers", (double) numWorkers);
		stats.put("trials", (double) numTrials);
		stats.put("pi", pi);
		stats.put("percentError", ((pi - Math.PI) / Math.PI) * 100.0);
		stats.put("time", (double) time);

		System.out.println("============");
		Enumeration<String> keys = stats.keys();
		String nextKey = null;
		double val = 0;
		while(keys.hasMoreElements()){
			nextKey = keys.nextElement();
			val = stats.get(nextKey);
			
			if(val % 1 == 0.0){
				System.out.println(nextKey + ": " + (int) val);
			}
			else{
				System.out.println(nextKey + ": " + val);
			}
		}
		System.out.println("============");
		
		executorService.shutdown();
	}

}
