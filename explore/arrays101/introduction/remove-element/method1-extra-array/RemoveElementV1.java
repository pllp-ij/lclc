import static com.anno.arrays.PrintArrays.printArrRange;
import java.util.Arrays;


/**
    VARS:
        val(int): the target value of number in initial array
        nonValNum(int): the number of non-val in initial array
        nonValNewArr(int[]): a new array containing all non-val elements
    DESCRIPTION:
        STEP 1
        Initialize nonValNewArr with the same size of initial array
        Initialize nonValNum to 0
        STEP 2
        Iterate each number in initial array,
            If current number not equals to val,
                STEP 3
                Push current non-val number to nonValNewArr, and increase nonValNum by one                
        STEP 4
        Copy nonValNewArr back to initial array for the first nonValNum elements
        STEP 5
        Return nonValNum
    TIME:
        O(n)
    SPACE:
        O(n), extra space for non-val elements
    TO BE OPTIMIZED:
        Avoid using extra space
*/

class RemoveElementV1 {
    
    public static int removeElement(int[] nums, int val) {
        // STEP 1
        int[] nonValNewArr = new int[nums.length];
        int nonValNum = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                // STEP 3
                nonValNewArr[nonValNum++] = nums[i];
            }
        }
        // STEP 4
        for (int i = 0; i < nonValNum; i++) {
            nums[i] = nonValNewArr[i];
        }
        // STEP 5
        return nonValNum;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 2, 3};
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println("Before: " + Arrays.toString(nums));
        int k = removeElement(nums, 2);
        System.out.print("After: ");
        printArrRange(nums, 0, k);
    }
}