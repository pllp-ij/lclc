import java.util.Arrays;

/*
    VARS:
        minSize(int): the minimum size of subarray
        curSubArrSum(int): the sum of current sub array
        idxStart(int): the start of the subarray, iterate from 0 to nums.length - 1
        idxEnd(int): the end of the subarray, iterate from idxStart to nums.length - 1
    DESCRIPTION:
        STEP 1
        Initialize minSize to nums.length + 1
        Initialize curSubArrSum to 0
        STEP 2
        Iterate idxStart from 0 to nums.length - 1
            STEP 3
            Reset curSubArrSum to 0 in each loop for idxStart
                curSubArrSum = 0;
            STEP 4
            Iterate idxEnd from idxStart to nums.length - 1
                STEP 5
                Calculate the sum of subarray from idxStart to idxEnd by calling method, ([idxStart, idxEnd])
                    curSubArrSum = getSum(nums, idxStart, idxEnd)
                STEP 6
                If curSubArrSum >= target,
                    STEP 7
                    Update minSize with the minimum between minSize and curSubArrSum
                        minSize = Math.min(minSize, curSubArrSum)
                STEP 8
                Break current loop for idxEnd, (meaning if current sum is bigger than target, the following idxEnd needn't to be considered)
        STEP 9
        If minSize == nums.length - 1, (meaning no update within for loop)
            STEP 10
            Return 0
        STEP 11
        Return minSize
    TIME:
        O(n^2)
    SPACE:
        O(1)
*/

class MinimumSizeSubarraySumV1 {
    
    public static int getMinimumSize(int[] nums, int target) {
        // STEP 1
        int minSize = nums.length + 1;
        int curSubArrSum = 0;
        // STEP 2
        for (int idxStart = 0; idxStart < nums.length; idxStart++) {
            // STEP 3
            curSubArrSum = 0;
            // STEP 4
            for (int idxEnd = idxStart; idxEnd < nums.length; idxEnd++) {
                // STEP 5
                curSubArrSum = getSum(nums, idxStart, idxEnd);
                // STEP 6
                if (curSubArrSum >= target) {
                    // STEP 7
                    minSize = Math.min(minSize, idxEnd - idxStart + 1);
                    // STEP 8
                    break;
                }
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
    
    public static int getSum(int[] nums, int start, int end) {
        int totalSum = 0;
        for (int i = start; i <= end; i++) {
            totalSum += nums[i];
        }
        return totalSum;
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