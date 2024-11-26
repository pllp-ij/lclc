package com.anno.arrays;

import java.util.Arrays;

/**
    All print related funcs
*/

public class PrintArrays {
    
    /*
        Params:
            nums(int[]): integer array
            start(int): the start of range to print
            len(int): the length of range to print
        Return:
            None
    */
    public static void printArrRange(int[] nums, int start, int len) {
            if (len < 0) {
                return;
            }
            System.out.print("[" + nums[start]);
            for (int i = start + 1; i <= start + len - 1; i++) {
                System.out.print(", " + nums[i]);
            }
            System.out.println("]\n");
    }
}