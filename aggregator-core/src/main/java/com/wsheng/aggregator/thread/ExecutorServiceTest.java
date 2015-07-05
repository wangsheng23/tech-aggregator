/**
 * 
 */
package com.wsheng.aggregator.thread;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Submit multiple times, and then the threadpool is useful
 * 
 * used to test only
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class ExecutorServiceTest {
	private static Future<?> taskTwo = null;
//	private static Future<?> taskThree = null;
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// ExecutorService executor = Executors.newFixedThreadPool(2);
		
		// ============= Switch "1" to "2" to check the total executing time ============ 
		ExecutorService executor = Executors.newFixedThreadPool(1);
		
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(executor);
		
	   
		// execute the Runnable
	    Runnable taskOne = new Runnable() {
			@Override
			public void run() {
				String temp = "";
				for (int i = 1; i <= 10; i++) {
					temp += i;
				}
				System.out.println(" TaskOne temp : " + temp);
			}
			
		};
	    executor.execute(taskOne);
		
	    int time = 0;
	    long startTime = System.currentTimeMillis();
	    for (int i = 0; i < 2; i++) {
	    	// if this task is not created or is canceled or is completed
//			if ((taskTwo == null) || (taskTwo.isDone()) || (taskTwo.isCancelled())) {
				// submit a task and return a Future
				System.out.println(" TaskTwo submit the " + (++time) + " times");
				taskTwo = completionService.submit(new SumTask("TaskTwo", 1000000000, 200));
//			}
	
//			if ((taskThree == null) || (taskThree.isDone()) || (taskThree.isCancelled())) {
//				taskThree = executor.submit(new MyThread("TaskThree", 5, 100));
//			}
			// if null the task has finished
//			Object object = taskTwo.get();
//			if (object == null) {
//				// System.out.println(i+1 + ") TaskTwo terminated successfully");
//				System.out.println("TaskTwo terminated successfully");
//			} else {
//				// if it doesn't finished, cancel it
//				System.out.println(" --- Cancel TaskTwo: " + object);
//				taskTwo.cancel(true);
//			}
//			if (taskThree.get() == null) {
//				System.out.println(i+1 + ") TaskThree terminated successfully");
//			} else {
//				System.out.println(" ---- Cancel TaskThree");
//				taskThree.cancel(true);
//			}
	    }
	    long endTime = System.currentTimeMillis();
	    
	    System.out.println(" --- Total time: " + (endTime-startTime));
	    
	    executor.shutdown();
	    System.out.println("-----------------------");
	    // wait until all tasks are finished
	    executor.awaitTermination(1, TimeUnit.SECONDS);
	    System.out.println("All tasks are finished!");
	
	}
}
