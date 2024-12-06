import java.util.Arrays;

/*
    NOTE:
        V1 is mainly iterating the start of the sub array window, which is harder to implement
    VARS:
        idxEnd(int): the end of the sub array window
        idxStart(int): the start of the sub array window, iterate from 0 to nums.length - 1
        curSubArrSum(int): the sum of current sub array
        minSize(int): the minimum size of sub array
    DESCRIPTION:
        STEP 1
        Initialize minSize to nums.length + 1
        Initialize curSubArrSum to 0
        Initialize idxEnd to -1
        STEP 2
        Iterate idxStart from 0 to nums.length - 1
            STEP 3
            Loop while idxEnd + 1 < nums.length and curSubArrSum < target, (meaning the next element of current is within valid range)
                STEP 4
                Increase idxEnd by one
                    idxEnd++;
                STEP 5
                Add next element to curSubArrSum
                    curSubArrSum += nums[idxEnd];

            If idxEnd + 1 >= nums.length
                If curSubArrSum >= target, (meaning the sub array is located at the final sub range of initial array, do last once update)
                    STEP 6
                    minSize = Math.max(minSize, idxEnd - idxStart + 1);
                    STEP 7
                    curSubArrSum -= nums[idxStart]
                    STEP 8
                    Skip current loop
                        continue;
                Else, (meaning curSubArrSum < target, there won't be any more sub array exist)
                    STEP 9
                    Break the loop
                        break;
                
            Now idxEnd + 1 < nums.length and curSubArrSum >= target in the following
            
            STEP 10
            Update minSize
                minSize = Math.min(minSize, idxEnd - idxStart + 1);
            STEP 11
            Substract nums[idxStart] from curSubArrSum for next iteration
                curSubArrSum -= nums[idxStart];
        STEP 12
        If minSize == nums.length + 1, (meaning no update during iteration) 
            STEP 13
            Return 0
        STEP 14
        Return minSize
    TIME:
        O(n), since the idxEnd is used to determine the end of the sub array
    SPACE:
        O(1)
*/

class MinimumSizeSubarraySumV1 {
    
    public static int getMinimumSize(int[] nums, int target) {
        // STEP 1
        int minSize = nums.length + 1;
        int curSubArrSum = 0;
        int idxEnd = -1;
        // STEP 2
        for (int idxStart = 0; idxStart < nums.length; idxStart++) {
            // STEP 3
            while (idxEnd + 1 < nums.length && curSubArrSum < target) {
                // STEP 4
                idxEnd++;
                // STEP 5
                curSubArrSum += nums[idxEnd];
            }
            if (idxEnd + 1 >= nums.length) {
                if (curSubArrSum >= target) {
                    // STEP 6
                    minSize = Math.min(minSize, idxEnd - idxStart + 1);
                    // STEP 7
                    curSubArrSum -= nums[idxStart];
                    // STEP 8
                    continue;
                } else {
                    // STEP 9
                    break;
                }
            }
            // STEP 10
            minSize = Math.min(minSize, idxEnd - idxStart + 1);
            // STEP 11
            curSubArrSum -= nums[idxStart];
        }
        // STEP 12
        if (minSize == nums.length + 1) {
            // STEP 13
            return 0;
        }
        // STEP 14
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