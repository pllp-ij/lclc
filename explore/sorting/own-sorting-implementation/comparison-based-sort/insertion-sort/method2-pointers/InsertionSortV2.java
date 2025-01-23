import java.util.Arrays;

/*
    VARS:
        idxToBeProcessed(int): the index to be inspected currently, which range from 1 to (nums.length - 1)
        cacheVal(int): a cache value of nums[idxToBeProcessed] for final insertion operation
        idxToBeInserted(int): the final position to be inserted the cacheVal
    DESCRIPTION:
        STEP 1
        Initialize idxToBeProcessed to 1
        Initialize cacheVal to -1 for reusage purpose
        Initialize idxToBeInserted to -1 for reusage purpose
        STEP 2
        Loop while idxToBeProcessed < nums.length,
            STEP 3
            Store the value of nums[idxToBeProcessed] to cacheVal for later insertion operation
                cacheVal = nums[idxToBeProcessed];
            STEP 4
            Reset idxToBeInserted to idxToBeProcessed for finding the index to be inserted
                idxToBeInserted = idxToBeProcessed;
            STEP 5
            Loop while idxToBeInserted > 0 && nums[idxToBeInserted - 1] > cacheVal, (for each index of idxToBeInserted, its previous element is smaller cacheVal, NOT use nums[idxToBeInserted])
                STEP 6
                Assign nums[idxToBeInserted - 1] to nums[idxToBeInserted] to overwrite nums[idxToBeInserted]
                    nums[idxToBeInserted] = nums[idxToBeInserted - 1];
                STEP 7
                Decrease idxToBeInserted by one for next left position
                    idxToBeInserted--;
            
            Now there are two conditions after existing above while loop
                1. idxToBeInserted == 0, (meaning the insertion place is the index of 0)
                2. idxToBeInserted > 0 && nums[idxToBeInserted - 1] <= nums[idxToBeInserted], (meaning the insertion place is in the middle of sorted range)
            both conditions do the same assignment operation from cacheVal to nums[idxToBeInserted]
            
            STEP 8
            Assign cacheVal back to nums[idxToBeInserted] to finish final insertion operation
                nums[idxToBeInserted] = cacheVal;
            STEP 9
            Increase idxToBeProcessed by one for next element to be processed
                idxToBeProcessed++;
            
    TIME:
        O(n * n), first n is the iteration on the index of which element should be processed, second n is the consumption in swapping operation, which is n in worst cases where nums[idxToBeProcessed] is the smallest one among all sorted range elements
    SPACE:
        O(1), no extra space is used
*/

class InsertionSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxToBeProcessed = 1;
        int idxToBeInserted = -1;
        int cacheVal = -1;
        // STEP 2
        while (idxToBeProcessed < nums.length) {
            // STEP 3
            cacheVal = nums[idxToBeProcessed];
            // STEP 4
            idxToBeInserted = idxToBeProcessed;
            // STEP 5
            while (idxToBeInserted > 0 && nums[idxToBeInserted - 1] > cacheVal) {
                // STEP 6
                nums[idxToBeInserted] = nums[idxToBeInserted - 1];
                // STEP 7
                idxToBeInserted--;
            }
            // STEP 8
            nums[idxToBeInserted] = cacheVal;
            // STEP 9
            idxToBeProcessed++;
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}