/**
 * 
 */
package com.wsheng.aggregator.algorithm.sort.exchange;

/**
 * 快速排序：递归
 * 通过一趟排序，将待排记录分割成独立的两部分，其中一部分记录的关键字都比另一部分记录的关键字小，
 * 则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 * 
 * 具体做法是：
 * 使用两个指针low，high，初值分别设置为序列的头和序列的尾，默认设置pivotkey(中轴值)为第一个记录（即第一个元素就一直为pivotkey了),
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
		int tmp = list[low]; // 每次默认指定数组的第一个值作为中轴，即pivotkey, 先将中轴的值暂存起来
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
		 * 重要的一步：将暂存的中轴的值恢复： 因为如上的过程其实就是将中轴的值
		 * 不断地替换成其它值的过程（debug查看：即list中不断的会出现如上list中的某个元素出现重复的过程，原因是中轴的值被替换为其中的一个值了）
		 * ，while 循环之后，low已经指向list的中间，
		 * 所以将暂存的中轴的值恢复到low或者/high的位置
		 * 中轴记录到尾 list[high] = tmp; 将暂存的中轴的值进行恢复以便下次递归调用
		 */
		list[low] = tmp; 
		
		return low; // 返回中轴的值, return high 也OK
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
