import java.util.Arrays;

/*
    NOTE: 
        V1 and V2 will stop at the idxRight meeting with zero and the number of zeros exceed flipMost, while stopping, the idxLeft will move to the right until it excludes a zero then the idxRight start moving again,
        the V1 use if statement in this each movement, while V2 will use a nested while loop for idxLeft to exclude a zero
    VARS:
        idxRight(int): the right side of range
        idxLeft(int): the left side of range
        zerosNum(int): the number of zeros until now
        maxWindowLength(int): global max window length of consecutive ones
    DESCRIPTION:
        STEP 1
        Initialize idxRight to 0
        Initialize idxLeft to 0
        Initialize zerosNum to 1
        Initialize maxWindowLength to 0
        STEP 2
        Loop while idxRight < nums.length
            If nums[idxRight] == 0, (there are two conditions to meet with zero, first is when zerosNum is not more than flipMost, second is zerosNum more than flipMost)
                If zerosNum <= flipMost, 
                    STEP 3
                    Increase zerosNum by one
                        zerosNum++;
                    STEP 4
                    If zerosNum <= flipMost, (if zerosNum increased by one still not more than flipMost, idxRight should be advanced by one step, otherwise it should be stopped there)
                        STEP 5
                        Advance idxRight by one step
                            idxRight++;
                STEP 6
                Update maxWindowLength at this moment
                    maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
                STEP 7
                Loop while zerosNum > flipMost,
                    STEP 8
                    If nums[idxLeft] == 0,
                        STEP 9
                        Decrease zerosNum by one
                            zerosNum--;
                    STEP 10
                    Advance idxLeft by one step
                        idxLeft++;
                STEP 11
                Advance idxRight to next position
                    idxRight++;
            Else, (meaning nums[idxRight] == 1)
                STEP 12
                idxRight++;
        STEP 13
        Update maxWindowLength finally for final sub range 
            maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
        STEP 14
        Return maxWindowLength
    TIME:
        O(n), only one pass of array scan of idxRight
    SPACE:
        O(1)
*/

class MaxConsecutiveOnesV2 {

    public static int getMaxConsecutiveOnes(int[] nums, int flipMost) {
        // STEP 1
        int idxLeft = 0;
        int idxRight = 0;
        int zerosNum = 0;
        int maxWindowLength = 0;
        // STEP 2
        while (idxRight < nums.length) {
            if (nums[idxRight] == 0) {
                if (zerosNum <= flipMost) {
                    // STEP 3
                    zerosNum++;
                    // STEP 4
                    if (zerosNum <= flipMost) {
                        // STEP 5
                        idxRight++;
                    }
                }
                if (zerosNum > flipMost) {
                    // STEP 6
                    maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
                    // STEP 7
                    while (zerosNum > flipMost) {
                        // STEP 8
                        if (nums[idxLeft] == 0) {
                            // STEP 9
                            zerosNum--;
                        }
                        // STEP 10
                        idxLeft++;
                    }
                    // STEP 11
                    idxRight++;
                }
            } else {
                // STEP 12
                idxRight++;
            }
        }
        // STEP 13
        maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
        // STEP 14
        return maxWindowLength;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 0, 1, 1, 0};
        int[] nums = {1, 0, 1, 1, 0, 1};
        // int[] nums = {1, 0, 1, 1, 0, 1, 1, 1, 0, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        int flipMost = 1;
        int result = getMaxConsecutiveOnes(nums, flipMost);
        System.out.println("max number of consecutive ones at most " + flipMost + " flips: " + result);
    }
}