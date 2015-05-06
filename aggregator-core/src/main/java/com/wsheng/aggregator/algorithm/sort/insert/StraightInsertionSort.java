/**
 * 
 */
package com.wsheng.aggregator.algorithm.sort.insert;

import java.util.Random;


/**
 * 插入排序包括直接插入排序，希尔插入排序
 * 直接插入排序（寻找插入位置）：将一个记录插入到已经排好序的有序表中。
 * 1. sorted数组的第0个位置没有放数据。// 数组从1开始有数据。
 * 2. 从sorted第二个数据开始处理 //也就是从sorted[2]开始处理，因为第0个位置没有数据。
 * 
 * 算法步骤：
 * 如果该数据比它前面的数据要小，说明该数据要往前面移动
 * a.首先将该数据备份到sorted的第0位置当哨兵。
 * b.然后将该数据前面那个数据后移。
 * c.然后往前搜索，找插入位置。（从后往前搜索）
 * d.找到插入位置后将0位置的那个数据插入到对应的位置。
 * 时间复杂度为O(n*n),当待排记录序列为正序时，时间复杂度提高至O(n)
 * 
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class StraightInsertionSort {

	public void sort(double[] sorted) {
		int sortedLen = sorted.length;
		for (int j = 2; j < sortedLen; j++) {
			if (sorted[j] < sorted[j-1]) {
				sorted[0] = sorted[j];// 先保存该元素，找到插入位置后，进行恢复插入。
				sorted[j] = sorted[j-1]; // 前面的那个后移
				
				// 寻找插入位置, 在第0个位置到第j-2个位置之间的这一堆元素之间进行排序并寻找合适的插入位置
				int insertPos = 0;// 默认在第0个位置
				for (int k = j-2; k >=0; k--) { // j从第二个位置开始，因此当前插入位置k从j-2的位置开始。
					if (sorted[k] > sorted[0]) { // 如果当前位置还是比哨兵大，则显然不是插入位置，需要继续后移
						sorted[k+1] = sorted[k];
					} else {
						insertPos = k + 1; // 因为数据从1开始，所以插入位置往后退一个位置
						break;
					}
				}
				sorted[insertPos] = sorted[0];
			}
		}
	}
	
	public static void main(String[] args) {
		Random random = new Random(6);
		
		int arraysize = 10;
		double[] sorted = new double[arraysize];
		System.out.println(" ------- Before Sort --------");
		for (int j=1; j < arraysize; j++) {
			sorted[j] = random.nextDouble()*100;
			System.out.print(sorted[j] + " ");
		}
		System.out.println();
		
		StraightInsertionSort insertionSort = new StraightInsertionSort();
		insertionSort.sort(sorted);
		
		System.out.println(" ------- After Sort ---------");
		for(int j=1; j < arraysize; j++) {
			System.out.print(sorted[j] + " ");
		}
		System.out.println();
		
	}
}
