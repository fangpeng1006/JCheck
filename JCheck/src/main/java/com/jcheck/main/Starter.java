package com.jcheck.main;

import com.jcheck.tools.ArraySort;

import java.util.Arrays;

public class Starter {
    public static void main(String[] args) {
        int[] array={22,13,56,98,45};
        System.out.println("排序之前："+ Arrays.toString(array));
        ArraySort.sort(array);
        System.out.println("排序之后："+ Arrays.toString(array));

    }
}
