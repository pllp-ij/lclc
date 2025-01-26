import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

/*
    VARS: 
        minAbsDiff(int): to find the minimum absolute difference between two adjacent indexes on sorted array
        result(List<List<Integer>>): finally returned result
    DESCRIPTION:
        STEP 1
        Sort initial nums by calling built-in method
            Arrays.sort(nums);
        STEP 2
        Initialize minAbsDiff to Integer.MAX_VALUE
            int minAbsDiff = Integer.MAX_VALUE
        STEP 3
        Iterate each index of sorted array nums with idx from 0 to (nums.length - 2)
            STEP 4
            If (nums[idx + 1] - nums[idx]) < minAbsDiff, (meaning a new minimum absolute difference is found between two adjacent indexes)
                STEP 5
                Update minAbsDiff to (nums[idx + 1] - nums[idx])
                    minAbsDiff = nums[idx + 1] - nums[idx];
        STEP 6
        Initialize result to List<List<Integer>>
            List<List<Integer>> result = new ArrayList<>();
        STEP 7
        Iterate each index of sorted array with idx again from 0 to (nums.length - 2)
            STEP 8
            If (nums[idx + 1] - nums[idx]) == minAbsDiff, (meaning a new pair with value of minAbsDiff is found)
                STEP 9
                Add this new pair into final result
                    result.add(Arrays.asList(nums[idx], nums[idx + 1]));
        STEP 10
        Return result
        
    TIME:
        O(n + n + n) ~ O(n), first n is for sorting, second n is for find minAbsDiff, third n is for adding pair the difference of which is equal to minAbsDiff
    SPACE:
        O(1), no extra space is used
    
*/

class MinimumAbsoluteDifferenceV1 {
    
    public static List<List<Integer>> getMinAbsDiff(int[] nums) {
        // STEP 1
        Arrays.sort(nums);
        // STEP 2
        int minAbsDiff = Integer.MAX_VALUE;
        // STEP 3
        for (int idx = 0; idx < nums.length - 1; idx++) {
            // STEP 4
            if (nums[idx + 1] - nums[idx] < minAbsDiff) {
                // STEP 5
                minAbsDiff = nums[idx + 1] - nums[idx];
            }
        }
        // STEP 6
        List<List<Integer>> result = new ArrayList<>();
        // STEP 7
        for (int idx = 0; idx < nums.length - 1; idx++) {
            // STEP 8
            if (nums[idx + 1] - nums[idx] == minAbsDiff) {
                // STEP 9
                result.add(Arrays.asList(nums[idx], nums[idx + 1]));
            }
        }
        // STEP 10
        return result;
    }
    
    public static void main(String[] args) {
        // int[] nums = {4, 2, 1, 3};
        // int[] nums = {1, 3, 6, 10, 15};
        int[] nums = {3, 8, -10, 23, 19, -4, -14, 27};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        List<List<Integer>> result = getMinAbsDiff(nums);
        System.out.println("result: " + result.toString());
    }
}