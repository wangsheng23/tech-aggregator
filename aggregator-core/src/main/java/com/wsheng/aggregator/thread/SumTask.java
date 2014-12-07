package com.wsheng.aggregator.thread;

import java.util.concurrent.Callable;

/**
 * Use to test only
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 *
 */
public class SumTask implements Callable<Integer> {
	
	private String myName;
	private int count;
	private final long timeSleep;

	SumTask(String name, int newcount, long newtimeSleep) {
		this.myName = name;
	    this.count = newcount;
	    this.timeSleep = newtimeSleep;
	}
	
	@Override
	public Integer call() {
		System.out.println(" Name : " + Thread.currentThread().getName());
		
		int sum = 0;
	    for (int i = 1; i <= this.count; i++) {
	    	sum = sum + i;
	    }
	    System.out.println(myName + " thread has sum = " + sum + 
	    		" and is going to sleep for " + timeSleep);
	    try {
			Thread.sleep(this.timeSleep);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	    return sum;
	}

}