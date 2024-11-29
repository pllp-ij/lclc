import java.util.Arrays;

/**
    VARS:
        rightMaxArr(int[]): the max of all numbers at each index corresponding to initial array
        leftMax(int): the max of all numbers to the left of current iterated index
        rightMax(int): the max of all numbers to the right of current iterated index
    DESCRIPTION:
        STEP 1
        Initialize rightMaxArr to the same size of initial array
        Initialize leftMax to Integer.MIN_VALUE
        Initialize rightMax to Integer.MIN_VALUE
        STEP 2
        Iterate each number in initial array with index i from right to left
            STEP 3
            Assign rightMax to rightMaxArr[i]
            STEP 4
            Update rightMax including current iterated number nums[i]
                rightMax = Math.max(rightMax, nums[i])
        STEP 5
        Iterate each number in initial array with index i from left to right
            If nums[i] >= 2 * leftMax and nums[i] >= 2 * rightMaxArr[i], (meaning the first pivot index)
                STEP 6
                Return i
            STEP 7
            Update leftMax including current iterated number
                leftMax = Math.max(leftMax, nums[i])
        STEP 8
        Return -1
    TIME:
        O(2n) ~ O(n), scan initial array for twice
    SPACE:
        O(n), only one extra array used to record max of right side
    TO BE OPTIMIZED:
        Avoid using extra any array
*/

class LargestNumberAtLeastTwiceOfOthersV1 {
    
    public static int getLargestNumberIdx(int[] nums) {
        // STEP 1
        int[] rightMaxArr = new int[nums.length];
        int rightMax = Integer.MIN_VALUE;
        int leftMax = Integer.MIN_VALUE;
        // STEP 2
        for (int i = nums.length - 1; i >= 0; i--) {
            // STEP 3
            rightMaxArr[i] = rightMax;
            // STEP 4
            rightMax = Math.max(rightMax, nums[i]);
        }
        // STEP 5
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 2 * leftMax && nums[i] >= 2 * rightMaxArr[i]) {
                // STEP 6
                return i;
            }
            // STEP 7
            leftMax = Math.max(leftMax, nums[i]);
        }
        // STEP 8
        return -1;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 6, 1, 0};
        // int[] nums = {1, 2, 3, 4};
        System.out.println("Before: " + Arrays.toString(nums));
        int index = getLargestNumberIdx(nums);
        System.out.println("largest number index: " + index);
    }
}