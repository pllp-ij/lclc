import static com.anno.arrays.ManipulateArrays.sumArray;
import java.util.Arrays;

/**
    VARS:
        leftSumArr(int[]): the sum of all numbers to the left of current index i
        rightSumArr(int[]): the sum of all numbers to the right of current index i, 
        leftSum(int): the sum value of all numbers to the left of current index i
        rightSum(int): the sum value of all numbers to the right of current index i
    DESCRIPTION:
        STEP 1
        Initialize leftSumArr, rightSumArr to the same size of initial array
        Initialize leftSum to 0
        Initialize rightSum to the sum of whole array
        STEP 2
        Iterate each number in initial array with index i from left to right
            STEP 3
            Assign leftSum to leftSumArr[i],
            STEP 4
            Add current iterated number to leftSum
                leftSum += nums[i]
            STEP 5
            Decrease current iterated number from rightSum
                rightSum -= nums[i]
            STEP 6
            Assign rightSum to rightSumArr[i],
        STEP 7
        Iterate each number in initial array with index i
            If leftSumArr[i] == rightSumArr[i], (meaning the first pivot index)
                STEP 8
                Return i
        STEP 9
        Return -1
    TIME:
        O(n), first pass to create leftSumArr and rightSumArr, second pass to find pivot index
    SPACE:
        O(2n) ~ O(n), extra two array used
    TO BE OPTIMIZED:
        Avoid using two extra array, instead, use two variables
*/

class FindPivotIndexV1 {
    
    public static int findPivotIndex(int[] nums) {
        // STEP 1
        int[] leftSumArr = new int[nums.length];
        int[] rightSumArr = new int[nums.length];
        int leftSum = 0;
        int rightSum = sumArray(nums);
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            leftSumArr[i] = leftSum;
            // STEP 4
            leftSum += nums[i];
            // STEP 5
            rightSum -= nums[i];
            // STEP 6
            rightSumArr[i] = rightSum;
        }
        // STEP 7
        for (int i = 0; i < nums.length; i++) {
            if (leftSumArr[i] == rightSumArr[i]) {
                // STEP 8
                return i;
            }
        }
        // STEP 9
        return -1;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 7, 3, 6, 5, 6};
        // int[] nums = {1, 2, 3};
        int[] nums = {2, 1, -1};
        System.out.println("Before: " + Arrays.toString(nums));
        int pivotIndex = findPivotIndex(nums);
        System.out.println("Pivot index: " + pivotIndex);
    }
}