import java.util.Arrays;

/*
    VARS:
        totalSum(int): the final return sum value
    DESCRIPTION:
        STEP 1
        Initialize totalSum to 0
        STEP 2
        Sort the array by calling method
            Arrays.sort(nums);
        STEP 3
        Iterate each number in sorted array with index i
            STEP 4
            If i % 2 == 0,
                STEP 5
                Add current number to totalSum
                    totalSum += nums[i]
        STEP 6
        Return totalSum
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ArrayPartition1V1 {
    
    public static int getPartionSum(int[] nums) {
        // STEP 1
        int totalSum = 0;
        // STEP 2
        Arrays.sort(nums);
        // STEP 3
        for (int i = 0; i < nums.length; i++) {
            // STEP 4
            if (i % 2 == 0) {
                // STEP 5
                totalSum += nums[i];
            }
        }
        // STEP 6
        return totalSum;
    }
    
    public static void main(String[] args) {
        
        int[] nums = {1, 4, 3, 2};
        System.out.println("Before: " + Arrays.toString(nums));
        int res = getPartionSum(nums);
        System.out.println("partition sum: " + res);
    }
}