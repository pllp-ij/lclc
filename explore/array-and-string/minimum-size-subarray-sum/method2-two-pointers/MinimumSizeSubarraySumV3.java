import java.util.Arrays;

/*
    NOTE:
        V3 directly use two pointers variables instead of using for loop
    VARS:
        idxFast(int): the fast pointer to represent the end of sub array
        idxSlow(int): the slow pointer to represent the start of sub array
        curSubArrSum(int): the sum of current sub array
        minSize(int): the minimum size of sub array
    DESCRIPTION:
        STEP 1
        Initialize idxFast to 0
        Initialize idxSlow to 0
        Initialize curSubArrSum to nums[0]
        Initialize minSize to nums.length + 1
        STEP 2
        Loop while idxFast < nums.length
            If curSubArrSum < target, (meaning still not satisfy the condition, advance idxFast again)
                STEP 3
                Increase idxFast by one
                    idxFast++;
                If idxFast < nums.length, (meaning the next element still within valid range)
                    STEP 4
                    Add current element at idxFast to curSubArrSum
                        curSubArrSum += nums[idxFast]
                    STEP 5
                    Skip current loop for idxFast
                        continue;
                Else, (meaning the next element index is outside the right edge of valid range)
                    STEP 6
                    Break the loop
            Else, (meaning curSubArrSum >= target in the following)
                STEP 6
                Update minSize with the minimum one between minSize and (idxFast - idxSlow + 1)
                    minSize = Math.min(minSize, idxFast - idxSlow + 1)
                STEP 7
                Substract nums[idxSlow] from curSubArrSum for next iteration
                    curSubArrSum -= nums[idxSlow];
                STEP 8
                Increase idxSlow by one
                    idxSlow++;
        STEP 9
        If minSize == nums.length + 1, (meaning no update during iteration)
            STEP 10
            Return 0
        STEP 11
        Return minSize
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class MinimumSizeSubarraySumV3 {
    
    public static int getMinimumSize(int[] nums, int target) {
        // STEP 1
        int idxFast = -1;
        int idxSlow = 0;
        int curSubArrSum = 0;
        int minSize = nums.length + 1;
        // STEP 2
        while (idxFast < nums.length) {
            if (curSubArrSum < target) {
                // STEP 3
                idxFast++;
                if (idxFast < nums.length) {
                    // STEP 4
                    curSubArrSum += nums[idxFast];
                    // STEP 5
                    continue;
                } else {
                    // STEP 6
                    break;
                }
            } else {
                // STEP 6
                minSize = Math.min(minSize, idxFast - idxSlow + 1);
                // STEP 7
                curSubArrSum -= nums[idxSlow];
                // STEP 8
                idxSlow++;
            }
        }
        // STEP 9
        if (minSize == nums.length + 1) {
            // STEP 10
            return 0;
        }
        // STEP 11
        return minSize;
    }
    
    public static void main(String[] args) {
        // int[] nums = {2, 3, 1, 2, 4, 3};
        // int[] nums = {1, 4, 4};
        int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        // int target = 7;
        // int target = 4;
        int target = 11;
        int res = getMinimumSize(nums, target);
        System.out.println("minimum subarray size is: " + res);
    }
}