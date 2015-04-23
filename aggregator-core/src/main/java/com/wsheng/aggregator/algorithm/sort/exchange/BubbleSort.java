package com.wsheng.aggregator.algorithm.sort.exchange;


/**
 * 冒泡排序是专门针对已部分排序的数据进行排序的一种排序算法。
 * 
 * 如果你的数据清单中只有一两个数据是乱序的话，用这种算法是最快的排序算法。
 * 
 * 如果你的数据清单中的数据是随机排列的，那么这种算法就成了最慢的算法了。
 * 
 * 因此在使用这种算法之前一定要慎重。
 * 
 * 该算法的核心思想是扫描数据清单，寻找出现乱序的两个  相邻 的项目。当找到这两个项目后，交换项目的位置然后继续扫描。
 * 重复上面的操作直到所有项目都按顺序排列好。
 * 
 * 冒泡：每一次排序都会找到当次最大的元素并放入最底部，而小的元素就往上走了，感觉像冒泡一样。
 * 
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class BubbleSort {

	public static void sort(int[] a) {
		for (int i = 0; i< a.length; i++) {
			int temp = 0;
			for (int j = 0; j < a.length -i - 1; j++) {
				if (a[j] > a[j+1]) {// 前面比后面大的时候
					temp = a[j];
					a[j] = a[j+1];
					a[j+1] = temp;
				}
			}
		}
	}
	
	private static void print(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
	}
	
	
	public static void main(String[] args) {
		int a[] = {49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18};
		System.out.println(" Before Sort");
		print(a);
		
		sort(a);
		
		System.out.println();
		
		System.out.println(" After Sort");
		
		print(a);
	}
}
