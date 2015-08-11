package com.wsheng.aggregator.thread.performance.visualvm;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class BasicThreadTest extends Thread {
	
	public static void main(String[] args) {
		
		BasicThreadTest b1 = new BasicThreadTest("Thread a");
		BasicThreadTest b2 = new BasicThreadTest("Thread b");
		
		b1.setName("BasicThreadTest-1");
		b2.setName("BasicThreadTest-2");
		
		b1.start();
		b2.start();
	}
	
	public BasicThreadTest(String name) {
		
	}
	
	@Override
	public void run() {
		while (true) {
			
		}
	}

}
