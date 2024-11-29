package com.anno.arrays;

import java.util.Arrays;

/**
    All print related funcs
*/

public class ManipulateArrays {
    /*
        Params:
            nums(int[]): input array
            index(int): the index to be checked
        Return:
            boolean
    */
    public static boolean checkIndexValidation(int[] nums, int index) {
        if (index < 0 || index > nums.length) {
            return false;
        }
        return true;
    }
    
    /*
        Params:
            nums(int[]): integer array
            i(int): first place to be swapped
            j(int): second place to be swapped
        Return:
            None
    */
    public static void swapTwo(int[] nums, int i, int j) {
        if (!checkIndexValidation(nums, i) ||
            !checkIndexValidation(nums, j)) {
            return;
        }
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    /*
        Params:
            nums(int[]): integer array
        Return
            newArr(int[]): copied array same as initial array
    */
    public static int[] copyArray(int[] nums) {
        int[] newArr = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            newArr[i] = nums[i];
        }
        return newArr;
    }
}