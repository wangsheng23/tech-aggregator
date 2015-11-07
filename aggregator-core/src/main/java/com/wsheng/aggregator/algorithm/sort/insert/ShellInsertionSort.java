/**
 * 
 */
package com.wsheng.aggregator.algorithm.sort.insert;

import java.util.Random;


/**
 * 
 * 希尔排序（缩小增量排序 diminishing increment sort)
 * 缩小：将待排序列分割成若干子序列。
 * 增量：每次都基于指定的子序列的index开始进行插入。
 * 
 * 
 * 先将整个待排记录序列分割成若干个子序列分别进行直接插入排序。
 * 待整个序列中的记录基本有序时，再对全体记录进行一次直接插入排序。 
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class ShellInsertionSort {
	
	
	public static void main(String[] args) {
		Random random = new Random(10);
		
		int arraysize = 5;
		double[] sorted = new double[arraysize];
		System.out.println(" ------- Before Sort --------");
		for (int j=0; j < arraysize; j++) {
			sorted[j] = random.nextDouble()*100;
			System.out.print(sorted[j] + " ");
		}
		System.out.println();
		
		ShellInsertionSort insertionSort = new ShellInsertionSort();
		insertionSort.sort(sorted);
		
		System.out.println(" ------- After Sort ---------");
		for(int j=0; j < arraysize; j++) {
			System.out.print(sorted[j] + " ");
		}
		System.out.println();
		
	}
	
	public void sort(double[] sorted) {
		int[] incs = {7,5,3,1};
		int num = incs.length;
		
		int inc = 0;
		for (int j=0; j < num; j++) {
			inc = incs[j];
			sort(sorted, inc);
		}
	}
	
	private void sort(double[] sorted, int inc) {
		int sortedLen = sorted.length;
		for (int j = inc+1; j < sortedLen; j++) {
			if (sorted[j] < sorted[j-1]) {
				sorted[0] = sorted[j];// 先保存该元素, 找到插入位置后进行恢复插入。
			//	sorted[j] = sorted[j-1];// 前面的那个后移, 有没有这句话都不影响排序结果，因为现在是增量的(inc)排序，而不是直接和前一个元素比较。
				
				// 寻找插入位置
				int insertPos = j; // j = inc+1， 默认从增量后的位置开始插入:inc+1
				for (int k = j-inc; k>=0; k-=inc) {// k = j-(inc+1)-1
					if (sorted[k] > sorted[0]) {
						sorted[k+inc] = sorted[k]; // 将当前元素后移inc个增量位置
						if (k - inc <= 0) {
							insertPos = k; // 此时插入位置就是k本身。
						}
					} else {
						insertPos = k + inc;
						break;
					}
				}
				sorted[insertPos] = sorted[0];
			}
		}
	}
}
