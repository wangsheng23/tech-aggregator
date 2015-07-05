/**
 * 
 */
package com.wsheng.aggregator.collection;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class TestQueue {
	public static void main(String[] args) {
		Queue<Integer> queue = new LinkedBlockingDeque<Integer>();
		
		// init the queue
		for (int i = 0; i < 5; i++) {
			queue.offer(i);
		}
		
		System.out.println(" ================== 1 ================");
		// Iterator in collection method, the elements won't be deleted
		for (Integer x : queue) {
			System.out.print(x + " ");
		}
		System.out.println();
		System.out.println("size: " + queue.size());
		
		System.out.println(" ================== 2 ================");
		// Iterator in queue method, the elements will be removed one by one
		while (queue.peek() != null) { // peek : Retrieves, but does not remove, the head of this queue,
			System.out.print(queue.poll() + " "); // etrieves and removes the head of this queue,
		}
		System.out.println();
		System.out.println("current size: " + queue.size());
	}

}
