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
        updateSwitch(boolean): a switch to control the on and off to update maxWindowLength each time when idxRight meeting with zero and current number of zeros exceed flipMost
    DESCRIPTION:
        STEP 1
        Initialize idxRight to 0
        Initialize idxLeft to 0
        Initialize zerosNum to 0
        Initialize updateSwitch to true
        STEP 2
        Iterate idxRight from 0 to nums.length - 1
            If nums[idxRight] == 0, (meaning current is meeting zero)
                If zerosNum <= flipMost, (meaning the zeros still can be included within current range)
                    STEP 3
                    Increase zerosNum by one
                        zerosNum++;
                    STEP 4
                    If zerosNum <= flipMost, (meaning current number of zeros still not exceed flipMost)
                        STEP 5
                        Advance idxRight by one step
                            idxRight++;
                If zerosNum > flipMost,
                    STEP 6
                    If updateSwitch == true, (meaning it is the first time idxRight meet with zeros that exceed flipMost)
                        STEP 7
                        Update global max of window length with the maximum one between maxWindowLength and (idxRight - idxLeft)
                            maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
                        STEP 8
                        Update updateSwitch to false
                            updateSwitch = false;

                    STEP 9
                    If nums[idxLeft] == 0, (idxLeft is currently pointing at zero)
                        STEP 10
                        Decrease zerosNum by one to exclude current zero pointed by idxLeft
                            zerosNum--;
                        STEP 11
                        Update idxLeft by one step
                            idxLeft++;
                        STEP 12
                        Update idxRight by one step
                            idxRight++;
                        STEP 13
                        Open updateSwitch to true
                            updateSwitch = true
                    Else, (meaning nums[idxLeft] != 0, nums[idxLeft] == 1)
                        STEP 14
                        Update idxLeft to next position
                            idxLeft++;
            Else, (meaning it is not zero)
                STEP 15
                Increase idxRight by one
                    idxRight++;
        STEP 16
        Update global max of window length with the maximum one between maxWindowLength and (idxRight - idxLeft)
            maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
        STEP 17
        Return maxWindowLength
    TIME:
        O(n), only one pass of array scan of idxRight
    SPACE:
        O(1)
*/

class MaxConsecutiveOnesV1 {

    public static int getMaxConsecutiveOnes(int[] nums, int flipMost) {
        // STEP 1
        int idxRight = 0;
        int idxLeft = 0;
        int zerosNum = 0;
        int maxWindowLength = 0;
        boolean updateSwitch = true;
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
                    if (updateSwitch) {
                        // STEP 7
                        maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
                        // STEP 8
                        updateSwitch = false;
                    }
                    // STEP 9
                    if (nums[idxLeft] == 0) {
                        // STEP 10
                        zerosNum--;
                        // STEP 11
                        idxLeft++;
                        // STEP 12
                        idxRight++;
                        // STEP 13
                        updateSwitch = true;
                    } else {
                        // STEP 14
                        idxLeft++;
                    }
                }
            } else {
                // STEP 15
                idxRight++;
            }
        }
        // STEP 16
        maxWindowLength = Math.max(maxWindowLength, idxRight - idxLeft);
        // STEP 17
        return maxWindowLength;
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