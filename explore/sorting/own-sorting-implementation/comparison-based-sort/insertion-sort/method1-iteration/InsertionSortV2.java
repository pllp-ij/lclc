import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 will optimize on the swap operation, which will only do once for each element to be inserted into sorted range
    VARS:
        idxToBeProcessed(int): the index of element to be inspected, which range from 1 to (nums.length - 1)
        idxToBeInserted(int): the correct position to insert nums[idxToBeProcessed] in each turn
        cacheVal(int): the cache of value of each element to be processed needed by last insertion operation
    DESCRIPTION:
        STEP 1
        Initialize idxToBeInserted to -1 for reusage purpose
            int idxToBeInserted = -1;
        Initialize cacheVal to -1 for reusage purpose
            int cacheVal = -1;
        STEP 2
        Iterate idxToBeProcessed from 1 to (nums.length - 1) for each element to be processed
            STEP 3
            Store value of nums[idxToBeProcessed] to cacheVal for later insertion operation
                cacheVal = nums[idxToBeProcessed];
            STEP 4
            Reset idxToBeInserted to idxToBeProcessed for each processed element
                idxToBeInserted = idxToBeProcessed;
            STEP 5
            Loop while idxToBeInserted > 0 && nums[idxToBeInserted - 1] > cacheVal, (search for the position where cacheVal should insert, note that every comparasion is done with cacheVal, NOT nums[idxToBeInserted])
                STEP 6
                Assign value of nums[idxToBeInserted - 1] to nums[idxToBeInserted]
                    nums[idxToBeInserted] = nums[idxToBeInserted - 1];
                STEP 7
                Decrease idxToBeInserted by one for next position
                    idxToBeInserted--;
            
            Now there are two conditions after existing above while loop
                1. idxToBeInserted == 0, (meaning the insertion place is the index of 0)
                2. idxToBeInserted > 0 && nums[idxToBeInserted - 1] <= nums[idxToBeInserted], (meaning the insertion place is in the middle of sorted range)
            both conditions do the same assignment operation from cacheVal to nums[idxToBeInserted]
            
            STEP 8
            Assign cacheVal to nums[idxToBeInserted]
                nums[idxToBeInserted] = cacheVal;

    TIME:
        O(n * n), first n is the iteration on the index of which element should be processed, second n is the consumption in swapping operation, which is n in worst cases where nums[idxToBeProcessed] is the smallest one among all sorted range elements
    SPACE:
        O(1), no extra space is used
*/

class InsertionSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxToBeInserted = -1;
        int cacheVal = -1;
        // STEP 2
        for (int idxToBeProcessed = 1; idxToBeProcessed < nums.length; idxToBeProcessed++) {
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
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}