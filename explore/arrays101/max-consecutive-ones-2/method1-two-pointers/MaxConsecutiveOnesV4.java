import java.util.Arrays;

/*
    NOTE: 
        Compared with V3, V4 use for loop to replace while loop
    VARS:
        idxRight(int): the right side of the range
        idxLeft(int): the left side of the range
        zerosNum(int): the number of zeros until now
    DESCRIPTION:
        STEP 1
        Initialize idxLeft to 0 to represent the start point of valid sub range
        Initialize idxRight to 0 to represent the end point of valid sub range
        Initialize zerosNum to 0
        STEP 2
        Iterate idxRight from 0 to nums.length
            STEP 3
            If nums[idxRight] == 0,
                STEP 4
                Increase zerosNum by one
                    zerosNum++;
            STEP 5
            If zerosNum > flipMost, (meaning current number of zeros is more than flipMost)
                STEP 6
                If nums[idxLeft] == 0,
                    STEP 7
                    Decrease zerosNum by one
                        zerosNum--;
                STEP 8
                Advance idxLeft each iteration when zerosNum > flipMost
                    idxLeft++;
        STEP 9
        Return (idxRight - idxLeft) finally
    TIME:
        O(n), only one pass of array scan of idxRight
    SPACE:
        O(1)
*/

class MaxConsecutiveOnesV4 {

    public static int getMaxConsecutiveOnes(int[] nums, int flipMost) {
        // STEP 1
        int idxLeft = 0;
        int idxRight = 0;
        int zerosNum = 0;
        // STEP 2
        for (; idxRight < nums.length; idxRight++) {
            // STEP 3
            if (nums[idxRight] == 0) {
                // STEP 4
                zerosNum++;
            }
            // STEP 5
            if (zerosNum > flipMost) {
                // STEP 6
                if (nums[idxLeft] == 0) {
                    zerosNum--;
                }
                // STEP 7
                idxLeft++;
            }
        }
        // STEP 8
        return idxRight - idxLeft;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 0, 1, 1, 0};
        // int[] nums = {1, 0, 1, 1, 0, 1};
        int[] nums = {1, 0, 1, 1, 0, 1, 1, 1, 0, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        int flipMost = 1;
        int result = getMaxConsecutiveOnes(nums, flipMost);
        System.out.println("max number of consecutive ones at most " + flipMost + " flips: " + result);
    }
}