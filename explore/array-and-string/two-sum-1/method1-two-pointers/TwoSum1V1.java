import java.util.Arrays;

/*
    VARS:
        idxLeft(int): the left pointer
        idxRight(int): the right poniter
    DESCRIPTION:
        STEP 1
        Initialize idxLeft to 0
        Initialize idxRight to nums.length - 1
        STEP 2
        Loop while idxLeft < idxRight
            If nums[idxLeft] + nums[idxRight] == target,
                STEP 3
                Return new int[] {idxLeft + 1, idxRight + 1};
            Else if nums[idxLeft] + nums[idxRight] < target,
                STEP 4
                idxLeft++;
            Else, (meaning nums[idxLeft] + nums[idxRight] > target)
                STEP 5
                idxRight--;
        STEP 6
        Return new int[] {-1, -1};
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class TwoSum1V1 {
    
    public static int[] getIndices(int[] nums, int target) {
        // STEP 1
        int idxLeft = 0;
        int idxRight = nums.length - 1;
        // STEP 2
        while (idxLeft < idxRight) {
            if (nums[idxLeft] + nums[idxRight] == target) {
                // STEP 3
                return new int[] {idxLeft + 1, idxRight + 1};
            } else if (nums[idxLeft] + nums[idxRight] < target) {
                // STEP 4
                idxLeft++;
            } else {
                // STEP 5
                idxRight--;
            }
        }
        // STEP 6
        return new int[] {-1, -1};
    }
        
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        System.out.println("Before: " + Arrays.toString(nums));
        int target = 9;
        int[] res = getIndices(nums, target);
        System.out.println("sum of " + target + " res is: " + Arrays.toString(res));
    }
}