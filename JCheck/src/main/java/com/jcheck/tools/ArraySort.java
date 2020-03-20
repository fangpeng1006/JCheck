package com.jcheck.tools;

/**
 * 数组工具类
 */

public class ArraySort {
    private ArraySort(){}

    /**
     * 对int数组进行冒泡排序
     * @param arr
     */
    public static void sort(int[] arr){
        for (int i =0; i<arr.length;i++){
            for (int j =0;j <arr.length-1-i;j++){
                if (arr[j] > arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
    }
}
