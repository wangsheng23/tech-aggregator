/**
 * 
 */
package com.wsheng.aggregator.programs;

import java.io.IOException;
import java.util.Scanner;


/**
 * case: 输入4, 3(行比列大于一） 返回如下数组：
 * 可以不限定循环次数
 * 
 * 1  2  3
 * 10 11 4
 * 9  12 5
 * 8  7	 6
 * 
 * 解决方案： ****找规律********
 * 
 * 规律1：pop个数：从列数开始逐渐减少 ： 这个可以使用规律3搞定 while (value <= row * col)
 * pop3个数： 1 2 3, 4 5 6
 * pop2个数： 7 8, 9 10,
 * pop1个数: 11, 12
 * 
 * 外面循环执行3次，每个循环包括2个循环,2个循环中，先处理行，再处理列 
 * 规律2： pop的位置
 * 
 * 规律3： pop的内容：从1开始逐渐加1，当加到row * col时退出循环。
 * 
 * 规律4： pop的方向：正向行，正向列，反向行，反向列，正向行，正向列 ... (到临界值时调整方向,临界值逐渐变化)
 * 
 * @author Josh Wang(Sheng)
 *
 * @email  josh_wang23@hotmail.com
 */
public class PopulateSquareArray {
	public static void main(String[] args) throws IOException {
		// int row = System.in.read();
		// int col = System.in.read();
		Scanner scanner = new Scanner(System.in);
		int row = scanner.nextInt();
		int col = scanner.nextInt();
		
		System.out.println(" current row: " + row + " current col: " + col);
		
		int[][] array = populate(row, col);
		print(array);
		
		scanner.close();
	}
	
	private static void print(int[][] array) {
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[i].length; j++) {
				if (array[i][j] < 10)
					System.out.print(" " + array[i][j] + " "); 
				else 
					System.out.print(array[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	private static int[][] populate(int row, int col) {
		int[][] array = new int[row][col];
		
		int num = 1;
		int currentRow = 0, currentCol = 0;
		boolean forwardPop = true;
		
		for (int orientation = col; orientation >= 1; orientation--) { // 规律1
			if (forwardPop) { // 正向populate row and col
				// populate row
				for (int i = 0; i < orientation; i++) { 
					array[currentRow][currentCol] = num++;
					currentCol++;
				}
				currentRow ++; // 跳到下一行
				
				// populate col, if i == col, need to adjust the orientation from row to col
				currentCol--; // 解决数组越界
				for (int j = 0; j < orientation; j++) { 
					array[currentRow][currentCol] = num++;
					currentRow++;
				}
				currentCol --; // 跳到前一列
			} else { // 反向populate row and col
				currentRow --; // 解决数组越界
				for (int i = 0; i < orientation; i++) { // populate row
					array[currentRow][currentCol] = num++;
					currentCol --; 
				}
				currentRow --; // 跳到上一行
				
				currentCol ++ ; // 解决数组越界
				for (int j = 0; j < orientation; j++) {
					array[currentRow][currentCol] = num++;
					currentRow--;
				}
				
				// 将多减的加回来
				currentRow ++ ; 
				
				currentCol ++; // 跳到后一列
			}
		
			forwardPop = !forwardPop;
		}
		
		return array;
	}
}
