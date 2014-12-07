package com.wsheng.aggregator.thread.performance;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 
 */

/**
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class HashMapDead2LiveLock implements Callable<Integer> {
	
	private static ExecutorService threadPool = Executors.newFixedThreadPool(10);
	
	private static Map<Integer, Integer> results = new ConcurrentHashMap<>();

	@Override
	public Integer call() throws Exception {
		results.put(1, 1);
		results.put(2, 2);
		results.put(3, 3);
		
		for (int i = 0; i < 1000; i++) {
			results.put(i, i);
		}
		
		Thread.sleep(1000);
		
		for (int i= 0; i < 1000; i++) {
			results.remove(i);
		}
		
		System.out.println(" ---- " + Thread.currentThread().getName()  + "		" + results.get(0));
		
		return results.get(1);
	}
	
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		try {
			for (int i = 0; i < 2000; i++) {
					HashMapDead2LiveLock hashMapDeadLock  = new HashMapDead2LiveLock();
//					Future<Integer> future = threadPool.submit(hashMapDeadLock);
//					future.get();
					threadPool.submit(hashMapDeadLock);
				}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			threadPool.shutdown();
		}
	
		
		
		
	}

	
}
