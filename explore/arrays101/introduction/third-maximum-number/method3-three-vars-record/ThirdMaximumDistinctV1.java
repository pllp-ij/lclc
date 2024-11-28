import java.util.Arrays;

/**
    NOTE:
        initial integer type of firstMax, secondMax, thirdMax will fail on the input "[1, 2, Integer.MIN_VALUE]",
        so the initial value of these three variables should be to extend to long type, Long.MIN_VALUE
    VARS:
        firstMax(long): the first max value
        secondMax(long): the second max value
        thirdMax(long): the third max value
    DESCRIPTION:
        STEP 1
        Initialize firstMax, secondMax, thirdMaximum all to Long.MIN_VALUE
        STEP 2
        Iterate each number in initial array with index i
            If current iterated number equal to any of the three variables
                STEP 3
                Skip current iteration
                    continue;
            If current iterated number is larger than firstMax,
                STEP 4
                Update thirdMaximum with secondMax
                STEP 5
                Update secondMax with firstMax
                STEP 6
                Update firstMax with current number nums[i]
                STEP 7
                Skip current iteration
                    continue;
            
            Now following in under condition that current iterated number is smaller than firstMax
            
            If current iterated number is larger than secondMax
                STEP 8
                Update thirdMaximum with secondMax
                STEP 9
                Update secondMax with current number nums[i]
                STEP 10
                Skip current iteration
                    continue;
                    
            Now following is under condition that current iterated number is smaller than secondMax
            
            If current iterated number is larger than thirdMax
                STEP 11
                Update thirdMax with current number nums[i]
        If thirdMax still be Integer.MIN_VALUE, (meaning there is not enough numbers for third distinct element)
            STEP 12
            Return firstMax as max number in whole array
        STEP 13
        Return thirdMax
    TIME:
        O(n), only iterate array for once
    SPACE:
        O(1), only three extra variables should be maintained
    
*/

class ThirdMaximumDistinctV1 {
    
    public static int getThirdMaximumDistinct(int[] nums) {
        // STEP 1
        long firstMax = Long.MIN_VALUE, secondMax = Long.MIN_VALUE, thirdMax = Long.MIN_VALUE;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == firstMax || nums[i] == secondMax || nums[i] == thirdMax) {
                // STEP 3
                continue;
            }
            if (nums[i] > firstMax) {
                // STEP 4
                thirdMax = secondMax;
                // STEP 5
                secondMax = firstMax;
                // STEP 6
                firstMax = nums[i];
                // STEP 7
                continue;
            }
            if (nums[i] > secondMax) {
                // STEP 8
                thirdMax = secondMax;
                // STEP 9
                secondMax = nums[i];
                // STEP 10
                continue;
            }
            if (nums[i] > thirdMax) {
                // STEP 11
                thirdMax = nums[i];
            }
        }
        if (thirdMax == Long.MIN_VALUE) {
            // STEP 12
            return (int) firstMax;
        }
        // STEP 13
        return (int) thirdMax;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 1};
        // int[] nums = {1, 2};
        int[] nums = {2, 2, 3, 1};
        // int[] nums = {5, 2, 2, 2, 5, 1, 4, 6};
        // int[] nums = {1, 1, 2};
        // int[] nums = {1, 2, Integer.MIN_VALUE};
        System.out.println("Before: " + Arrays.toString(nums));
        int thirdMaximum = getThirdMaximumDistinct(nums);
        System.out.println("third distinct maximum: " + thirdMaximum);
    }
}