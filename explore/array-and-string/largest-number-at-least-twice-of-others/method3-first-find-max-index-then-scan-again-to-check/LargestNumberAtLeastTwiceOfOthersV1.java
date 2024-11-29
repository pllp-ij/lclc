import java.util.Arrays;

/**
    VARS:
        largestIdx(int): the index of unique largest number in initial array
        largestVal(int): the value of unique largest number in initial array
    DESCRIPTION:
        STEP 1
        Initialize largestIdx to 0
        Initialize largestVal to Integer.MIN_VALUE
        STEP 2
        Iterate each number with index i in initial array from left to right
            If nums[i] > largestVal,
                STEP 3
                Update largestVal with nums[i]
                    largestVal = nums[i]
                STEP 4
                Update largestIdx with index i
                    largestIdx = i
        STEP 5
        Iterate each number in initial array again with index i from left to right
            If i != largestIdx and 2 * nums[i] > largestVal, (meaning there at least one element not satisfied the condition for pivot index)
                STEP 6
                Return -1
        STEP 7
        Return largestIdx
    TIME:
        O(2n) ~ O(n), scan twice
    SPACE:
        O(1), no extra space used
*/

class LargestNumberAtLeastTwiceOfOthersV1 {
    
    public static int getLargestNumberIdx(int[] nums) {
        // STEP 1
        int largestIdx = 0;
        int largestVal = Integer.MIN_VALUE;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > largestVal) {
                // STEP 3
                largestVal = nums[i];
                // STEP 4
                largestIdx = i;
            }
        }
        // STEP 5
        for (int i = 0; i < nums.length; i++) {
            if (i != largestIdx && 2 * nums[i] > largestVal) {
                // STEP 6
                return -1;
            }
        }
        // STEP 7
        return largestIdx;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 6, 1, 0};
        int[] nums = {1, 2, 3, 4};
        System.out.println("Before: " + Arrays.toString(nums));
        int index = getLargestNumberIdx(nums);
        System.out.println("largest number index: " + index);
    }
}