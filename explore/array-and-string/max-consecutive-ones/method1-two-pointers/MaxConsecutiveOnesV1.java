import java.util.Arrays;

/*
    VARS:
        idxSlow(int): slow pointer to indicate the start of consecutive window
        idxFast(int): fast poniter to indicate the end of consecutive window
        globalMaxNum(int): the final max number of consecutive ones
    DESCRIPTION:
        STEP 1
        Initialize idxSlow to 0, idxFast to 0
        Initialize globalMaxNum to 0
        STEP 2
        Loop while idxFast < nums.length,
            If nums[idxFast] == 1,
                STEP 3
                idxFast++;
            Else, (meaning nums[idxFast] == 0)
                STEP 4
                If nums[idxSlow] == 0, (meaning current is in all zeros window)
                    STEP 5
                    Advance idxSlow and idxFast
                        idxSlow++;
                        idxFast++;
                    STEP 6
                    Skip current iteration
                        continue;
                STEP 7
                Get current number of consecutive ones
                    int localMaxNum = idxFast - idxSlow;
                STEP 8
                Update globalMaxNum 
                    globalMaxNum = Math.max(globalMaxNum, localMaxNum)
                STEP 9
                Move idxSlow to idxFast
                    idxSlow = idxFast;
        STEP 10
        If idxFast == nums.length and nums[nums.length - 1] == 1, (meaning there should be a last update for globalMaxNum)
            SETP 11
            globalMaxNum = Math.max(globalMaxNum, idxFast - idxSlow)
        STEP 12
        Return globalMaxNum
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class MaxConsecutiveOnesV1 {
    
    public static int getMaxConsecutiveOnes(int[] nums) {
        // STEP 1
        int idxSlow = 0;
        int idxFast = 0;
        int globalMaxNum = 0;
        // STEP 2
        while (idxFast < nums.length) {
            if (nums[idxFast] == 1) {
                // STEP 3
                idxFast++;
            } else {
                // STEP 4
                if (nums[idxSlow] == 0) {
                    // STEP 5
                    idxSlow++;
                    idxFast++;
                    // STEP 6
                    continue;
                }
                // STEP 7
                int localMaxNum = idxFast - idxSlow;
                // STEP 8
                globalMaxNum = Math.max(globalMaxNum, localMaxNum);
                // STEP 9
                idxSlow = idxFast;
            }
        }
        // STEP 10
        if (idxFast >= nums.length && nums[nums.length - 1] == 1) {
            // STEP 11
            globalMaxNum = Math.max(globalMaxNum, idxFast - idxSlow);
        }
        // STEP 12
        return globalMaxNum;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 0, 1, 1, 1};
        // int[] nums = {0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 0};
        // int[] nums = {1, 0, 1};
        // int[] nums = {0, 1};
        int[] nums = {0, 0};
        System.out.println("Before: " + Arrays.toString(nums));
        int result = getMaxConsecutiveOnes(nums);
        System.out.println("consecutive ones num: " + result);
    }
}