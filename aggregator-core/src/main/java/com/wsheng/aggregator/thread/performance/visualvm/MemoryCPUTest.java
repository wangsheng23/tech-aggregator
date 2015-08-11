/**
 * 
 */
package com.wsheng.aggregator.thread.performance.visualvm;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class MemoryCPUTest {
	
	public static void main(String[] args) throws InterruptedException {
		cpuFix();
	}
	
	
	private static void cpuFix() throws InterruptedException {
		// 80%的占有率
		int busyTime = 8;
		
		// 20%的占有率
		int idelTime = 2;
		
		// 开始时间
		long startTime = 0;
		
		while (true) {
			startTime = System.currentTimeMillis();
			
			// 运行时间
			while (System.currentTimeMillis() - startTime < busyTime) {
				;
			}
			
			// 休息时间
			Thread.sleep(idelTime);
		}
	}

}
