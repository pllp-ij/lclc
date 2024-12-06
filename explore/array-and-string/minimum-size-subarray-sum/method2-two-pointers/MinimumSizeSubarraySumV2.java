import java.util.Arrays;

/*
    NOTE:
        V2 is mainly iterating the end of the sub array window, which is easier to implement
    VARS:
        idxEnd(int): the end of the sub array window
        idxStart(int): the start of the sub array window, iterate from 0 to nums.length - 1
        curSubArrSum(int): the sum of current sub array
        minSize(int): the minimum size of sub array
    DESCRIPTION:
        STEP 1
        Initialize idxStart to 0
        Initialize curSubArrSum to 0
        Initialize minSize to nums.length + 1
        STEP 2
        Iterate idxEnd from 0 to nums.length - 1
            STEP 3
            Add current element to curSubArrSum
                curSubArrSum += nums[idxEnd]
            STEP 4
            If curSubArrSum < target, (meaning idxEnd should move to next one)
                STEP 5
                Skip to next iteration
                    continue;
                    
            Now curSubArrSum >= target
            
            STEP 6
            Loop while idxStart <= idxEnd and curSubArrSum >= target                
                STEP 7
                Update minSize to the minimum one between minSize and (idxEnd - idxStart + 1)
                    minSize = Math.min(minSize, idxEnd - idxStart + 1);
                STEP 8
                Substract nums[idxStart] from curSubArrSum for next iteration
                    curSubArrSum -= nums[idxStart];
                STEP 9
                Move idxStart to right by one step
                    idxStart++;
        STEP 10
        If minSize == nums.length + 1, (meaning no update during iteration)
            STEP 11
            Return 0
        STEP 12
        Return minSize
    TIME:
        O(n), since the idxEnd is used to determine the end of the sub array
    SPACE:
        O(1)
*/

class MinimumSizeSubarraySumV2 {
    
    public static int getMinimumSize(int[] nums, int target) {
        // STEP 1
        int idxStart = 0;
        int curSubArrSum = 0;
        int minSize = nums.length + 1;
        // STEP 2
        for (int idxEnd = 0; idxEnd < nums.length; idxEnd++) {
            // STEP 3
            curSubArrSum += nums[idxEnd];
            // STEP 4
            if (curSubArrSum < target) {
                // STEP 5
                continue;
            }
            // STEP 6
            while (idxStart <= idxEnd && curSubArrSum >= target) {
                // STEP 7
                minSize = Math.min(minSize, idxEnd - idxStart + 1);
                // STEP 8
                curSubArrSum -= nums[idxStart];
                // STEP 9
                idxStart++;
            }
        }
        // STEP 10
        if (minSize == nums.length + 1) {
            // STEP 11
            return 0;
        }
        // STEP 12
        return minSize;
    }
    
    public static void main(String[] args) {
        // int[] nums = {2, 3, 1, 2, 4, 3};
        int[] nums = {1, 4, 4};
        // int[] nums = {1, 1, 1, 1, 1, 1, 1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        // int target = 7;
        int target = 4;
        // int target = 11;
        int res = getMinimumSize(nums, target);
        System.out.println("minimum subarray size is: " + res);
    }
}