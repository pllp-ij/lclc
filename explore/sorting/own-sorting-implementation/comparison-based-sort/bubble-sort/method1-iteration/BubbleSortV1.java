import java.util.Arrays;

/*
    VARS:
        idxUnsortedRangeEnd(int): the right most index of unsorted range, from (nums.length - 1) to 0, so the length of unsorted range is dynamically shrinked
        idxUnsortedRange(int): the pointer to iterate each index of unsorted range in each turn
    DESCRIPTION:
        STEP 1
        Iterate idxUnsortedRangeEnd from (nums.length - 1) to 0
            STEP 2
            Iterate idxUnsortedRange from 0 to (idxUnsortedRangeEnd - 1) for each pair to be compared
                STEP 3
                If nums[idxUnsortedRange] > nums[idxUnsortedRange + 1], (meaning the before one is greater than the later one for each pair)
                    STEP 4
                    Swap two elements at idxUnsortedRange and (idxUnsortedRange + 1)
                        swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
    TIME:
        O(n * n), first n is the iteration on the length of unsorted range from (nums.length - 1) to 0, second n is the iterate of each index of unsorted range
    SPACE:
        O(1), no extra space is used
*/

class BubbleSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        for (int idxUnsortedRangeEnd = nums.length - 1; idxUnsortedRangeEnd >= 0; idxUnsortedRangeEnd--) {
            // STEP 2
            for (int idxUnsortedRange = 0; idxUnsortedRange < idxUnsortedRangeEnd; idxUnsortedRange++) {
                // STEP 3
                if (nums[idxUnsortedRange] > nums[idxUnsortedRange + 1]) {
                    // STEP 4
                    swap(nums, idxUnsortedRange, idxUnsortedRange + 1);
                }
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