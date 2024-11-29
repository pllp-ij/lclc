import java.util.Arrays;

/**
    VARS:
        leftMaxArr(int[]): the max of all numbers at each index corresponding to initial array
        rightMaxArr(int[]): the max of all numbers at each index corresponding to initial array
        leftMax(int): the max of all numbers to the left of current iterated index
        rightMax(int): the max of all numbers to the right of current iterated index
    DESCRIPTION:
        STEP 1
        Initialize leftMaxArr, rightMaxArr with the same size of initial array
        Initialize leftMax, rightMax to Integer.MIN_VALUE
        STEP 2
        Iterate each number in initial array with index i from left to right
            STEP 3
            Assign leftMax to leftMaxArr[i]
                leftMaxArr[i] = leftMax
            STEP 4
            Update leftMax including current iterated number nums[i]
                leftMax = Math.max(leftMax, nums[i])
            STEP 5
            Assign rightMax to rightMaxArr[i]
                rightMaxArr[i] = rightMax
            STEP 6
            Update rightMax including current iterated number nums[i]
                rightMax = Math.max(rightMax, nums[i])
        STEP 7
        Iterate each number in initial array again with index i from left to right
            If nums[i] >= 2 * leftMaxArr[i] and nums[i] >= 2 * rightMaxArr[i], (meaning current index is first pivot index)
                STEP 8
                Return i
        STEP 9
        Return -1
    TIME:
        O(2n) ~ O(n), totally twice scan through of initial array
    SPACE:
        O(2n) ~ O(n), two extra array needed
    TO BE OPTIMIZED:
        Avoid using two extra array, instead, use one extra array and a pointer
*/

class LargestNumberAtLeastTwiceOfOthersV1 {
    
    public static int getLargestNumberIdx(int[] nums) {
        // STEP 1
        int[] leftMaxArr = new int[nums.length];
        int[] rightMaxArr = new int[nums.length];
        int leftMax = Integer.MIN_VALUE;
        int rightMax = Integer.MIN_VALUE;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            leftMaxArr[i] = leftMax;
            // STEP 4
            leftMax = Math.max(leftMax, nums[i]);
            // STEP 5
            rightMaxArr[nums.length - i - 1] = rightMax;
            // STEP 6
            rightMax = Math.max(rightMax, nums[nums.length - i - 1]);
        }
        // STEP 7
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= 2 * leftMaxArr[i] &&
                nums[i] >= 2 * rightMaxArr[i]) {
                // STEP 8
                return i;
            }
        }
        // STEP 9
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