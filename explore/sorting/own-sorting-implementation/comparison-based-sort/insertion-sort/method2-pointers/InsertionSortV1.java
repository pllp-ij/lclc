import java.util.Arrays;

/*
    VARS:
        idxToBeProcessed(int): the index to be inspected currently, which range from 1 to (nums.length - 1)
        idxMoved(int): the pointer to indicate where is the swapped element moved to, which often iterate from idxToBeProcessed to the left direction
    DESCRIPTION:
        STEP 1
        Initialize idxToBeProcessed to 1
        Initialize idxMoved to -1 for reusage purpose
        STEP 2
        Loop while idxToBeProcessed < nums.length,
            STEP 3
            Reset idxMoved to idxToBeProcessed for finding the position to insert nums[idxToBeProcessed]
                idxMoved = idxToBeProcessed;
            STEP 4
            Loop while idxMoved > 0 && nums[idxMoved - 1] > nums[idxMoved], (if current element at idxMoved is larger than element at (idxMoved - 1))
                STEP 5
                Swap two elements at (idxMoved - 1) and idxMoved
                    swap(nums, idxMoved - 1, idxMoved);
                STEP 6
                Decrease idxMoved by one to next left position
                    idxMoved--;
            STEP 7
            Increase idxToBeProcessed by one to next right position
                idxToBeProcessed++;
        
    TIME:
        O(n * n), first n is the iteration on the index of which element should be processed, second n is the consumption in swapping operation, which is n in worst cases where nums[idxToBeProcessed] is the smallest one among all sorted range elements
    SPACE:
        O(1), no extra space is used
*/

class InsertionSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxToBeProcessed = 1;
        int idxMoved = -1;
        // STEP 2
        while (idxToBeProcessed < nums.length) {
            // STEP 3
            idxMoved = idxToBeProcessed;
            // STEP 4
            while (idxMoved > 0 && nums[idxMoved - 1] > nums[idxMoved]) {
                // STEP 5
                swap(nums, idxMoved - 1, idxMoved);
                // STEP 6
                idxMoved--;
            }
            // STPE 7
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