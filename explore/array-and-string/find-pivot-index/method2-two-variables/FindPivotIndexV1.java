import static com.anno.arrays.ManipulateArrays.sumArray;
import java.util.Arrays;

/**
    VARS:
        leftSum(int): the sum value of all numbers to the left of current index i
        rightSum(int): the sum value of all numbers to the right of current index i
    DESCRIPTION:
        STEP 1
        Initialize leftSum to 0
        Initialize rightSum to the sum of all elements in initial array
        STEP 2
        Iterate each number in initial array with index i from left to right
            STEP 3
            Substract current iterated number from rightSum, which is the updated sum of numbers to the right of current iterated index
                rightSum -= nums[i]
            If leftSum == rightSum, (meaning current iterated index is pivot index)
                STEP 4
                Return i
            STEP 5
            Add current iterated number to leftSum for next position comparison
                leftSum += nums[i]
        STEP 6
        Return -1
    TIME:
        O(n)
    SPACE:
        O(1), only two variables used, no extra space
*/

class FindPivotIndexV1 {
    
    public static int findPivotIndex(int[] nums) {
        // STEP 1
        int leftSum = 0;
        int rightSum = sumArray(nums);
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                // STEP 4
                return i;
            }
            // STEP 5
            leftSum += nums[i];
        }
        // STEP 6
        return -1;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 7, 3, 6, 5, 6};
        int[] nums = {1, 2, 3};
        // int[] nums = {2, 1, -1};
        System.out.println("Before: " + Arrays.toString(nums));
        int pivotIndex = findPivotIndex(nums);
        System.out.println("Pivot index: " + pivotIndex);
    }
}