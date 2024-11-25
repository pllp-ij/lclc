import java.util.Arrays;

/**
    VARS:
        idxOfNums1: iteration index of nums1, from m - 1 to 0
        idxOfNums2: iteration index of nums2, from n - 1 to 0
        idxOfCopyRight: the right pointer to the place where elements placed
    DESCRIPTION:
        STEP 1
        Initialize idxOfNums1 to last element of nums1's m elements, which is m - 1
        Initialize idxOfNums2 to last element of nums2's n elements, which is n - 1
        Initialize idxOfCopyRight to last element of nums1, which is nums.length - 1 or (m + n - 1)
        STEP 2
        Loop while idxOfNums1 >= 0 and idxOfNums2 >= 0,
            If nums1[idxOfNums1] > nums2[idxOfNums2],
                STEP 3
                Update the larger one to the place where idxOfCopyRight point to
                    nums1[idxOfCopyRight--] = nums1[idxOfNums1--]
            Else,
                STEP 4
                Update the larger one to the place where idxOfCopyRight point to
                    nums1[idxOfCopyRight--] = nums1[idxOfNums2--]
        STEP 5
        Loop while idxOfNums2 >= 0, (meaning all elements in nums1 already used up, so directly copy all left numbers to nums1)
            STEP 6
            Copy all nums2 elements to nums1
                nums1[idxOfCopyRight--] = nums2[idxOfNums2--];
                
        Note: The loop while idxOfNums1 >= 0 is ignored, because nothing should be doen in that case
    TIME:
        O(m + n), at least iterate (m + n) elements
    SPACE:
        O(1), no extra space needed
*/

class MergedSortedArrayV1 {
    public static void mergeSortedArray(int[] nums1, int m, int[] nums2, int n) {
        // STEP 1
        int idxOfNums1 = m - 1;
        int idxOfNums2 = n - 1;
        int idxOfCopyRight = nums1.length - 1;
        // STEP 2
        while (idxOfNums1 >= 0 && idxOfNums2 >= 0) {
            if (nums1[idxOfNums1] > nums2[idxOfNums2]) {
                // STEP 3
                nums1[idxOfCopyRight--] = nums1[idxOfNums1--];
            } else {
                // STEP 4
                nums1[idxOfCopyRight--] = nums2[idxOfNums2--];
            }
        }
        // STEP 5
        while (idxOfNums2 >= 0) {
            // STEP 6
            nums1[idxOfCopyRight--] = nums2[idxOfNums2--];
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