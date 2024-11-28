import java.util.Arrays;

/**
    VARS:
        zerosNum(int): number of zeros until current iterated index
    DESCRIPTION:
        STEP 1
        Initialize zerosNum to 0
        STEP 2
        Iterate from left to right with index i
            If zerosNum != 0 && nums[i] != 0, (meaning there are zeros before current iterated index, and current iterated number is not equal to zero)
                STEP 3
                Assign nums[i] to nums[i - zerosNum]
                STEP 4
                Assign current nums[i] to zero
                
            Following means: zerosNum == 0 or nums[i] == 0, since if zerosNum == 0, just do nothing except run into next turn of loop, so only consider if zerosNum == 0
            
            Else if nums[i] == 0, (meaning current number is zero)
                STEP 5
                Increase zerosNum by one
                    zerosNum++;
    TIME:
        O(n)
    SPACE:
        O(1)
    
*/

class MoveZerosV1 {
    
    public static void moveZeros(int[] nums) {
        // STEP 1
        int zerosNum = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (zerosNum != 0 && nums[i] != 0) {
                // STEP 3
                nums[i - zerosNum] = nums[i];
                // STEP 4
                nums[i] = 0;
            } else if (nums[i] == 0) {
                // STEP 5
                zerosNum++;
            }
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {0, 1, 0, 3, 12};
        int[] nums = {1};
        System.out.println("Before: " + Arrays.toString(nums));
        moveZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}