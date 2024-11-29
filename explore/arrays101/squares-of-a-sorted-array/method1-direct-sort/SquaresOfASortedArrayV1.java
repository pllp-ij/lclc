import java.util.Arrays;

/**
    VARS:
        
    Description:
        STEP 1
        Iterate each number in array
            STEP 2
            Square it
        STEP 3
        Apply sort algorithm to squared array
        STEP 4
        Return squared sorted array
    TIME:
        O(nlog(n)), depend on the sort algorithm type
    SPACE:
        O(1), depend on the sort algorithm type
    TO BE OPTIMIZED:
        Avoid squaring each number before hand
        Avoid directly using built-in sort algorithm
 */

class SquaresOfASortedArrayV1 {
    
    public static void squaresOfSorted(int[] nums) {
        // STEP 1
        for (int i = 0; i < nums.length; i++) {
            // STEP 2
            nums[i] *= nums[i];
        }
        // STEP 3
        Arrays.sort(nums);
    }
        
    public static void main(String[] args) {
        int[] nums = {-6, -4, -1, 2, 3, 5};
        System.out.println("Before sorted: " + Arrays.toString(nums));
        squaresOfSorted(nums);
        System.out.println("After sorted: " + Arrays.toString(nums));
    }
}