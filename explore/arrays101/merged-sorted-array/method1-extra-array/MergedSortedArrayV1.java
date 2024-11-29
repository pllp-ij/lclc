import java.util.Arrays;

/**
    VARS:
        newArr(int[]): a new array with size of sum of nums1 and nums2
        idxOfNewArr: iteration index of newArr from 0 to (m + n)
        idxOfNums1: iteration index of nums1, from 0 to m
        idxOfNums2: iteration index of nums2, from 0 to n
    DESCRIPTION:
        STEP 1
        Initialize new array with size of sum of m and n
        Initialize idxOfNewArr to 0
        Initialize idxOfNums1 to 0
        Initialize idxOfNums2 to 0
        STEP 2
        Loop while idxOfNums1 <= m and idxOfNums2 <= n
            If nums1[idxOfNums1] < nums2[idxOfNums2],
                STEP 3
                Update newArr with nums1[idxOfNums1]
                    newArr[idxOfNewArr++] = nums1[idxOfNums1++]
            Else,
                STEP 4
                Update newArr with nums2[idxOfNums2]
                    newArr[idxOfNewArr++] = nums2[idxOfNums2++]
        STEP 5
        Loop while idxOfNums1 <= m, (in case of m > n)
            STEP 6
            Update newArr with nums1[idxOfNums1]
                newArr[idxOfNewArr++] = nums1[idxOfNums1++]
        STEP 7
        Loop while idxOfNums2 <= n, (in case of m < n)
            STEP 8
            Update newArr with nums2[idxOfNums2]
                newArr[idxOfNewArr++] = nums2[idxOfNums2++]
        STEP 9
        Copy newArr back to nums1
        
    TIME:
        O(m + n), m is the elements number in nums1, n is the elements number in nums2
    SPACE:
        O(m + n), extra array is needed with size of (m + n)
    TO BE OPTIMIZED:
        Avoid using extra array
*/

class MergedSortedArrayV1 {
    public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        // STEP 1
        int[] newArr = new int[m + n];
        int idxOfNewArr = 0;
        int idxOfNums1 = 0;
        int idxOfNums2 = 0;
        // STEP 2
        while (idxOfNums1 < m && idxOfNums2 < n) {
            if (nums1[idxOfNums1] < nums2[idxOfNums2]) {
                // STEP 3
                newArr[idxOfNewArr++] = nums1[idxOfNums1++];
            } else {
                // STEP 4
                newArr[idxOfNewArr++] = nums2[idxOfNums2++];
            }
        }
        // STEP 5
        while (idxOfNums1 < m) {
            // STEP 6
            newArr[idxOfNewArr++] = nums1[idxOfNums1++];
        }
        // STEP 7
        while (idxOfNums2 < n) {
            // STEP 8
            newArr[idxOfNewArr++] = nums2[idxOfNums2++];
        }
        // STEP 9
        for (int i = 0; i < m + n; i++) {
            nums1[i] = newArr[i];
        }
    }
    
    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        System.out.println("Before: " + "\nnums1: " + Arrays.toString(nums1) + "\nnums2: " + Arrays.toString(nums2));
        mergeSortedArray(nums1, 3, nums2, 3);
        System.out.println("After merged: \n" + Arrays.toString(nums1));
    }
}