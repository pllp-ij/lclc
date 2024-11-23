import static java.lang.Math.max;

/**
    Description:
        VARS:
            maxWindowLength(int): global max window length of consecutive ones
            curWindowLength(int): current window length of consecutive ones
        STEP 1
            Initialize maxWindowLength and curWindowLength both to 0
        STEP 2
            Iterate the array from left to right one by one
            STEP 3
            If met with 1, (meaning still within the current window)
                Increase the curWindowLength by one
            STEP 4
            Else if met with 0, (meaning the end of the current window)
                Reset curWindowLength to 0
            STEP 5
                Update maxWindowLength
                    maxWindowLength = Max(maxWindowLength, curWindowLength);
        STEP 6
            Return maxWindowLength

    TO BE OPTIMIZED:
        maxWindowLength no need to be updated each time in consecutive zeros window
        curWindowLength no need to be set to 0 each time in consecutive zeros window
        
 */
class MaxConsecutiveOnesV1 {
    public static int getMaxConsecutiveOnesCount(int[] nums) {
        // STEP 1
        int maxWindowLength = 0;
        int curWindowLength = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // STEP 3
                curWindowLength++;
            } else {
                // STEP 4
                curWindowLength = 0;
            }
            // STEP 5
            maxWindowLength = max(maxWindowLength, curWindowLength);
        }
        // STEP 5
            maxWindowLength = max(maxWindowLength, curWindowLength);
        // STEP 6
        return maxWindowLength;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1};
        int count = getMaxConsecutiveOnesCount(nums);
        System.out.println("count: " + count);
    }
}