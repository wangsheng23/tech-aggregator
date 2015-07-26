/**
 * 
 */
package com.wsheng.aggregator.c3p0;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class C3p0Main {
	
	public static void main(String[] args) {
		System.out.println("缓冲池模拟开始"); 
        C3p0PoolThread[] threads = new C3p0PoolThread[100];  
        
        for (int i = 0; i < threads.length;i++) {  
            threads[i] = new C3p0PoolThread();  
        }  
        
        for (int i= 0;i < threads.length; i++) {  
            threads[i].start();  
        }  
	}

}
