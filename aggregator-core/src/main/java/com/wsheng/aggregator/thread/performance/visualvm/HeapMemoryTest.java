package com.wsheng.aggregator.thread.performance.visualvm;

/**
 * @author Josh Wang(Sheng)
 * 
 * @email  josh_wang23@hotmail.com
 * 
 */
public class HeapMemoryTest {
	
	private static final int OUTOFMEMORY = 300000000;
	
	private String oom;
	
	private int length;
	
	
	StringBuffer tempOOM = new StringBuffer();
	
	public HeapMemoryTest(int length) {
		this.length = length;
		
		int i = 0;
		
		while (i < length) {
			i ++;
			try {
				tempOOM.append("a");
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		this.oom = tempOOM.toString();
	}
	
	public static void main(String[] args) throws Exception {
		HeapMemoryTest heapMemoryTest = new HeapMemoryTest(OUTOFMEMORY);
		System.out.println(" --- Read to print the result ----");
		// Thread.sleep(5000); // set breakpoint here
		System.out.println(heapMemoryTest.getOom().length());
	}

	public String getOom() {
		return oom;
	}

	public void setOom(String oom) {
		this.oom = oom;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	

}
