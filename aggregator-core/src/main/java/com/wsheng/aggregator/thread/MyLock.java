/**
 * 
 */
package com.wsheng.aggregator.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class MyLock {
	private int j;
	private Lock lock = new ReentrantLock();
	
	public static void main(String[] args) {
		MyLock myLockTest = new MyLock();
		for (int i=0; i<2; i++) {
			new Thread(myLockTest.new Adder()).start();
			new Thread(myLockTest.new Subtractor()).start();
		}
	}
	
	private class Subtractor implements Runnable {
		int flag;
		@Override
		public void run() {
			while (flag<10) {
//				synchronized (MyLock.this) {
//					System.out.println("j--=" + j--); // 这里抛异常了，锁不能释放，因为上锁的this对象不是一个对象
//				}
				lock.lock();
				try {
					System.out.println("j--=" + j--);
					flag++;
				} finally {
					lock.unlock();
				}
			}
			
		}
		
	}
	
	private class Adder implements Runnable {
		int flag;
		@Override
		public void run() {
			while (flag < 10) {
//				synchronized (MyLock.this) {
//					System.out.println("j++=" + j--);
//				}
				lock.lock();
				try {
					System.out.println("j++=" + j++);
					flag++;
				} finally {
					lock.unlock();
				}
			}
			
		}
		
	}
	

}
