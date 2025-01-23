import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 will stop the iteration early when last turn has no swap operation
    VARS:
        idxUnsortedRangeEnd(int): the right most index of unsorted range, from (nums.length - 1) to 0, so the length of unsorted range is dynamically shrinked
        idxUnsortedRange(int): the pointer to iterate each index of unsorted range in each turn
        hasSwapped(boolean): a flog to indicate whether at least swap operation exist in last turn
    DESCRIPTION:
        STEP 1
        Initialize hasSwapped to false
            boolean hasSwapped = false;
        STEP 2
        Iterate idxUnsortedRangeEnd from (nums.length - 1) to 0
            STEP 3
            Reset hasSwapped to false for each newly shrinked unsorted range
                hasSwapped = false;
            STEP 4
            Iterate idxUnsortedRange from 0 to (idxUnsortedRangeEnd - 1) for each pair to be compared
                STEP 5
                If nums[idxUnsortedRange] > nums[idxUnsortedRange + 1], (meaning the before one is greater than the later one for each pair)
                    STEP 6
                    Swap two elements at idxUnsortedRange and (idxUnsortedRange + 1)
                        swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
                    STEP 7
                    Set hasSwapped to true to indicate that there is at least one swap operation exist
                        hasSwapped = true;
            STEP 8
            If hasSwapped equals to false after above turn of iteration,
                STEP 9
                Break the outside loop to finish all
                    break;
    TIME:
        O(n * n), first n is the iteration on the length of unsorted range from (nums.length - 1) to 0, second n is the iterate of each index of unsorted range
    SPACE:
        O(1), no extra space is used
*/

class BubbleSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        boolean hasSwapped = false;
        // STEP 2
        for (int idxUnsortedRangeEnd = nums.length - 1; idxUnsortedRangeEnd >= 0; idxUnsortedRangeEnd--) {
            // STEP 3
            hasSwapped = false;
            // STEP 4
            for (int idxUnsortedRange = 0; idxUnsortedRange < idxUnsortedRangeEnd; idxUnsortedRange++) {
                // STEP 5
                if (nums[idxUnsortedRange] > nums[idxUnsortedRange + 1]) {
                    // STEP 6
                    swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
                    // STEP 7
                    hasSwapped = true;
                }
            }
            // STEP 8
            if (hasSwapped == false) {
                // STEP 9
                break;
            }
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