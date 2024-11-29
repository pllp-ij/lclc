import java.util.Arrays;

/**
    VARS:
        idxIter(int): the index of iteration on initial arary
    DESCRIPTION:
        STEP 1
        Initialize idxIter to 0
        STEP 2
        Loop while idxIter < nums.length - 1 and nums[idxIter] < nums[idxIter + 1]
            STEP 3
            Increase idxIter by one
            idxIter++
        
        Now the idxIter point to the mountain top or point outside the right most position of initial array
        
        STEP 4
        If idxIter point to the first element or point outside the right of the initial array, (meaning the array is not strictly increase along the whole array)
            STEP 5
            Return false
        STEP 6
        Loop while idxIter < nums.length - 1 and nums[idxIter] > nums[idxIter + 1]
            STEP 7
            Increase idxIter by one
            idxIter++;
            
        Now the idxIter point outside the right of array or nums[idxIter] <= nums[idxIter + 1]
        
        If idxIter < nums.length - 1, (meaning the movement of idxIter stopped in the middle before reaching to the final elements)
            STEP 8
            Return false, (meaning the right section of array is not strictly decreasing)
        STEP 9
        Return true
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ValidMountainArrayV1 {
    
    public static boolean checkMountainArray(int[] nums) {
        // STEP 1
        int idxIter = 0;
        // STEP 2
        while (idxIter < nums.length - 1 && nums[idxIter] < nums[idxIter + 1]) {
            // STEP 3
            idxIter++;
        }
        // STEP 4
        if (idxIter == 0 || idxIter >= nums.length - 1) {
            // STEP 5
            return false;
        }
        // STEP 6
        while (idxIter < nums.length - 1 && nums[idxIter] > nums[idxIter + 1]) {
            // STEP 7
            idxIter++;
        }
        if (idxIter < nums.length - 1) {
            // STEP 8
            return false;
        }
        // STEP 9
        return true;
    }
    
    public static void main(String[] args) {
        // int[] nums = {0, 2, 3, 4, 5, 2, 1, 0};
        int[] nums = {2, 1};
        // int[] nums = {3, 5, 5};
        // int[] nums = {0, 3, 2, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        boolean result = checkMountainArray(nums);
        if (result) {
            System.out.println("is a mountain array");
        } else {
            System.out.println("not a mountain array");
        }
    }
}