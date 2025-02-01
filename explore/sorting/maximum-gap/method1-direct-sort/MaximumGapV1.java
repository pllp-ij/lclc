import java.util.Arrays;

/*
    VARS:
        maxGap(int): the var used to find the maximum gap on sorted array, also the final returned result
        curGap(int): gap value between each iterated current index and next index
    DESCRIPTION:
        STEP 1
        If nums.length < 2, (meaning there is not at least two element to compare)
            STEP 2
            Directly return 0
                return 0
        STEP 3
        Sort the nums using built-in sort method
            Arrays.sort(nums);
        STEP 4
        Initialize maxGap to Integer.MIN_VALUE
            int maxGap = MIN_VALUE:
        Initialize curGap to -1 for reusage purpose
            int curGap = -1;
        STEP 5
        Iterate each index of sorted nums from 0 to (nums.length - 2) with idx
            STEP 6
            Get the gap value of current iterated index and next index and assign it to curGap
                curGap = nums[idx + 1] - nums[idx];
            STEP 7
            If curGap > maxGap, (a new maximum gap is found)
                STEP 8
                Update maxGap to curGap
                    maxGap = curGap
        STEP 9
        Return maxGap as final result
            return maxGap;
            
    TIME:
        O(nlogn + n) ~ O(nlogn), first nlogn is for sorting using default method, second n is for find max gap of sorted arrays
    SPACE:
        O(1), no extra sapce is used
*/

class MaximumGapV1 {
    
    public static int getMaxGap(int[] nums) {
        // STEP 1
        if (nums.length < 2) {
            // STEP 2
            return 0;
        }
        // STEP 3
        Arrays.sort(nums);
        // STEP 4
        int maxGap = Integer.MIN_VALUE;
        int curGap = -1;
        // STEP 5
        for (int idx = 0; idx < nums.length - 1; idx++) {
            // STEP 6
            curGap = nums[idx + 1] - nums[idx];
            // STEP 7
            if (curGap > maxGap) {
                // STEP 8
                maxGap = curGap;
            }
        }
        // STEP 9
        return maxGap;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 6, 9, 1};
        int[] nums = {10};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int result = getMaxGap(nums);
        System.out.println("max gap is: " + result);
    }
}