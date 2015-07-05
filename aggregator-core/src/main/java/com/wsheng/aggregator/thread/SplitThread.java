/**
 * 
 */
package com.wsheng.aggregator.thread;

/**
 * Refer NucleonProfileService in CMSODBComparer
 * 
 * @author Josh Wang(Sheng)
 * 
 * @email  swang6@ebay.com
 * 
 */
public class SplitThread {

	
	public static void main(String[] args) {
	//	solution1();
		solution2();
	}
	
	/**
	 * 201个请求，100个Thread： 改Solution会出现很多Thread都是null
	 * 
	 * 
	 * 201 100
	 * 
	 * 201/100 = 2 + 1 = part = 3
	 * 
	 * 0: 0, 1, 2     0, part*(i + 1)
	 * 1: 3, 4, 5     part*i, part*(i + 1)
	 * 2: 6, 7, 8     part*i, part*(i + 1)
	 * 
	 */
	private static void solution1() { 
		int total = 201; // there are 201's requests
		int part = 100; // there will create 100 threads.
		int eachNumberOfPart = 0;// eachPart means the number that every thread handled.
		
		if (total % part == 0) {
			eachNumberOfPart = total / part;
		} else {
			eachNumberOfPart = total / part + 1; 
		}
		// String[] strings = new String[total];  // to avoid ArrayIndexOutOfBoundsException for the last serveal elements
		String[] strings = new String[eachNumberOfPart * part]; 
		for (int j = 0; j < total; j++) {
			strings[j] = "A:" + j;
		}
		System.out.println("--- eachNumberOfPart: ---" + eachNumberOfPart);
		for (int i = 0; i < part; i++) {
			System.out.print("------ Start Part: " + i + " ");
			for (int k = eachNumberOfPart*i; k < eachNumberOfPart*(i+1); k ++) {
				System.out.print(strings[k] + " ");
			}
			System.out.println("------ End Part: " + i);
		} 
	}
	
	
	/**
	 * 
	 * 201个请求，100个Thread
	 * 
	 * 理想是99个Thread，每个thread处理2个请求，
	 * 最后一个thread处理3个请求。
	 * 
	 * 201 100
	 * 
	 * 201/100 = 2 + 1 = part = 3
	 * 
	 * 0: 0, 1, 2     0, part*(i + 1)
	 * 1: 3, 4, 5     part*i, part*(i + 1)
	 * 2: 6, 7, 8     part*i, part*(i + 1)
	 * 
	 */
	private static void solution2() {
		int total = 201; // there are 201's requests
		int part = 100; // there will create 100 threads.
//		int total = 1080; // there are 201's requests
//		int part = 100; // there will create 100 threads.
		int eachNumberOfPart = total / part;// eachPart means the number that every thread handled.
		
		String[] strings = new String[total]; 
		for (int j = 0; j < total; j++) {
			strings[j] = "A:" + j;
		}
		
		System.out.println("--- eachNumberOfPart: ---" + eachNumberOfPart);
		
		for (int i = 0; i < part - 1; i++) {// -1: exclude the last request
			System.out.print("------ Start Part: " + i + " ");
			for (int k = eachNumberOfPart*i; k < eachNumberOfPart*(i+1); k ++) {// key point: 
				System.out.print(strings[k] + " ");
			}
			
			System.out.println("------ End Part: " + i);
		}
		
		// 最后一个线程处理剩余和多余的请求
		System.out.print("------ Start Part: " + (part-1) + " ");
		
		for (int m = eachNumberOfPart * (part-1) ; m < total; m++) {
			System.out.print(strings[m] + " ");
		}
	
		System.out.print("------ End Part: " + (part-1) + " ");
	
	}
}
