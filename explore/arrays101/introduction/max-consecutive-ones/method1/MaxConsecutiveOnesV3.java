import static java.lang.Math.max;

/**
    OPTIMIZATION:
        Avoid the final updation on maxWindowLength
        maxWindowLength only to be updated at the end of consecutive ones window
        curWindowLength only set to zero when it is not equal to zero
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
                If next element is 0 or current 1 is the last element, (meaning current 1 is the end of window)
                    Update maxWindowLength to the maximum one between maxWindowLength and curWindowLength
                        maxWindowLength = Max(maxWindowLength, curWindowLength);
            STEP 5
            Else if met with 0, (meaning the end of consecutive window)
                Reset curWindowLength to 0
        STEP 6
        Return maxWindowLength  
 */

class MaxConsecutiveOnesV3 {
    public static int getMaxConsecutiveOnesCount(int[] nums) {
        // STEP 1
        int maxWindowLength = 0;
        int curWindowLength = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 1) {
                // STEP 3
                curWindowLength++;
                // STEP 4
                if ((i < nums.length - 1 && nums[i + 1] == 0) || 
                    i == nums.length - 1) {
                    maxWindowLength = max(maxWindowLength, curWindowLength);
                }
            } else {
                // STEP 5
                curWindowLength = 0;
            }
        }
        // STEP 6
        return maxWindowLength;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1};
        int count = getMaxConsecutiveOnesCount(nums);
        System.out.println("count: " + count);
    }
}