package com.wsheng.aggregator.thread.performance;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Dead lock example
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class DeadLock2Live {  
  
    public static void main(String[] args) {  
    	System.out.println(" start the example ----- ");
        final Lock lock = new ReentrantLock(); 
          
        Thread t1 = new Thread("t1") {  
            @Override  
            public void run() {  
            	try {  
	           	lock.lock();
	                Thread.sleep(3000); 
	                System.out.println("thread t1 done.");
            	} catch (InterruptedException e) {
            		e.printStackTrace();
            	} finally {
            		lock.unlock();
            	}
            }
            };  
          
        Thread t2 = new Thread("t2") {  
            @Override  
            public void run() {  
            	try {  
            		lock.lock();
                	Thread.sleep(3000);
                    System.out.println("thread t2 done.");
                   

                }  catch (InterruptedException e) {
                	e.printStackTrace();
                } finally {
                	lock.unlock();
                }
            }  
        };  
          
        t1.start();  
        t2.start();  
    
}
        
}  