import java.util.Arrays;

/*
    NOTE: 
        Compared with V1 and V2, V3 will advance idxRight and idxLeft at the same time instead of stopping idxRight when idxRight meet with zero and zerosNum is more than flipMost, because the candidates when idxLeft advance forward during idxRight stopped is useless, because they are shorter than the length of beginning case
    VARS:
        idxRight(int): the right side of the range
        idxLeft(int): the left side of the range
        zerosNum(int): the number of zeros until now
    DESCRIPTION:
        STEP 1
        Initialize idxRight to 0
        Initialize idxLeft to 0
        Initialize zerosNum to 0
        STEP 2
        Loop while idxRight < nums.length
            STEP 3
            If nums[idxRight] == 0, (meaning as soon as idxRight point to zero at each iteration)
                STEP 4
                Increase zerosNum by one
                    zerosNum++;
            STEP 5
            If zerosNum > flipMost, (meaning after the addition operation above, the number of zeros is more than flipMost)
                STEP 6
                If nums[idxLeft] == 0, (when idxLeft meet with old zero, it will be excluded in next iteration)
                    STEP 7
                    Decrease zerosNum by one
                        zerosNum--;
                STEP 8
                Advance idxLeft at each iteration when zerosNum > flipMost
                    idxLeft++;
            STEP 9
            Advance idxRight at each iteration no matter what
                idxRight++;
        STEP 10
        Return (idxRight - idxLeft) finally
    TIME:
        O(n), only one pass of array scan of idxRight
    SPACE:
        O(1)
*/

class MaxConsecutiveOnesV3 {

    public static int getMaxConsecutiveOnes(int[] nums, int flipMost) {
        // STEP 1
        int idxLeft = 0;
        int idxRight = 0;
        int zerosNum = 0;
        // STEP 2
        while (idxRight < nums.length) {
            // STEP 3
            if (nums[idxRight] == 0) {
                // STEP 4
                zerosNum++;
            }
            // STEP 5
            if (zerosNum > flipMost) {
                // STEP 6
                if (nums[idxLeft] == 0) {
                    // STEP 7
                    zerosNum--;
                }
                // STEP 8
                idxLeft++;
            }
            // STEP 9
            idxRight++;
        }
        // STEP 10
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