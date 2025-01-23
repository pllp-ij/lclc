import java.util.Arrays;

/*
    VARS:
        idxSortedRangeStart(int): the start index of sorted range on the right side of nums, which will expanded to the left after each turn, the value is from nums.length to 1
        idxUnsortedRange(int): the pointer used to iterate each index of unsorted range
        hasSwapped(boolean): a flag to indicate whether at least one swap operation exist in last turn
    DESCRIPTION:
        STEP 1
        Initialize idxSortedRangeStart to (nums.length - 1) to indicate there is no elements has been sorted at the beginning, so the last position of nums is the first element to be placed
        Initialize idxUnsortedRange to -1 for reusage purpose
        Initialize hasSwapped to false for reusage purpose
        STEP 2
        Loop while idxSortedRangeStart >= 1, (the stop index is 1 is to indicate that there should be at least two elements so that comparing is meaningful)
            STEP 3
            Reset idxUnsortedRange to 0 within each turn
                idxUnsortedRange = 0;
            STEP 4
            Reset hasSwapped to false within each turn
                hasSwapped = false;
            STEP 5
            Loop while idxUnsortedRange < idxSortedRangeStart, (meaning the iteration will continue until it meet the start index of sorted range)
                STEP 6
                If nums[idxUnsortedRange] > nums[idxUnsortedRange + 1], (a swap needed)
                    STEP 7
                    Swap two elements at idxUnsortedRange and (idxUnsortedRange + 1)
                        swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
                    STEP 8
                    Set hasSwapped to true to indicate that there is at least one swap operation exist
                        hasSwapped = true;
                STEP 9
                Increase idxUnsortedRange by one to next index
                    idxUnsortedRange++;
            STEP 10
            If hasSwapped equal to false, meaning there isn't any swap opeartion in last turn, so directly break or return
                STEP 11
                Break/return outside loop
                    break;
            STEP 12
            Decrease idxSortedRangeStart by one to next left index
                idxSortedRangeStart--;

    TIME:
        O(n * n), first n is the iteration on the length of unsorted range from (nums.length - 1) to 0, second n is the iterate of each index of unsorted range
    SPACE:
        O(1), no extra space is used
*/

class BubbleSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxSortedRangeStart = nums.length - 1;
        int idxUnsortedRange = -1;
        boolean hasSwapped = false;
        // STEP 2
        while (idxSortedRangeStart >= 1) {
            // STEP 3
            idxUnsortedRange = 0;
            // STEP 4
            hasSwapped = false;
            // STEP 5
            while (idxUnsortedRange < idxSortedRangeStart) {
                // STEP 6
                if (nums[idxUnsortedRange] > nums[idxUnsortedRange + 1]) {
                    // STEP 7
                    swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
                    // STEP 8
                    hasSwapped = true;
                }
                // STEP 9
                idxUnsortedRange++;
            }
            // STEP 10
            if (hasSwapped == false) {
                // STEP 11
                break;
            }
            // STEP 12
            idxSortedRangeStart--;
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