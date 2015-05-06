/**
 * 
 */
package com.wsheng.aggregator.algorithm.sort.exchange;

/**
 * 快速排序：递归
 * 通过一趟排序，将待排记录分割成独立的两部分，其中一部分记录的关键字都比另一部分记录的关键字小(所以要选择一个参考值：pivotkey)，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 
 * 具体做法是：
 * 使用两个指针low，high，初值分别设置为序列的头和序列的尾，默认设置pivotkey(中轴值)为第一个记录(即第一个元素的值(不停的在变化)为pivotkey),
 * 1）首先从high开始向前搜索第一个小于pivotkey的记录和pivotkey所在的位置进行交换。
 * 2）然后从low开始搜索第一个大于pivotkey的记录和此时pivotkey所在位置进行交换
 * 重复如上2步直到low=high为止。
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class QuickSort {
	
	public static void quickSort(int[] list, int low, int high) {
		if (low < high) {
			int middle = getMiddle(list, low, high);
			
			quickSort(list, low, middle -1);
			
			quickSort(list, middle + 1, high);
		}
	}

	// 返回中轴的位置
	private static int getMiddle(int[] list, int low, int high) {
		int tmp = list[low]; // 默认指定数组的第一个值作为中轴，即pivotkey, 先将中轴的值（参考值）暂存起来
		System.out.println();
		System.out.println("参考值：" + tmp);
		while (low < high) {
			while (low < high && list[high] >= tmp) {
				high --; // list[high]的值跟着变
			}
			list[low] = list[high]; // 比中轴小的值移动到底端。
			
			System.out.println();
			print(list);
			
			while (low < high && list[low] <= tmp) {
				low ++; // list[low]的值跟着变
			}
			list[high] = list[low]; // 比中轴大的值移动到高端。
			
			System.out.println();
			print(list);
		}
		
		/*
		 * 退出while循环： low = high,此时low和high都指向中间同一位置，
		 * 需要将此时改位置的值设置为第一个参考值
		 */
		list[low] = tmp; // list[high] = tmp;
		
		System.out.println("current low: " + low + " current high: " + high);
		System.out.println("After revert .... ");
		print(list);
		return low; // 划分结束的值即为下次划分的基点，此时low和high相等 return high;
	}
	
	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	public static void main(String[] args) {
		int a[] = {49, 38, 65, 97, 76, 13, 27, 50, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18};
		System.out.println(" Before Sort");
		print(a);
		
		quickSort(a, 0, a.length-1);
		System.out.println();
		
		System.out.println(" After Sort");
		
		print(a);
	}
}
