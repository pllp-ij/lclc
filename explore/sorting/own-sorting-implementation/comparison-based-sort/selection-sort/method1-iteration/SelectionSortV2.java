import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 optimize the process of searching for minimum value
    VARS:
        idxSortedEnd(int): the index iterated where new number is to be inserted at the end of sorted range, which also the start index of unsorted range, from 0 to (nums.length - 2)
        idxUnsortedRange(int): the pointer used to iterate each index of unsorted range from (idxSortedEnd + 1) to the end
        idxMin(int): the pointer used to find the minimum element within unsorted range
    DESCRIPTION:
        STEP 1
        Initialize idxMin to -1 to be reused
            int idxMin = -1;
        STEP 2
        Iterate idxSortedEnd from 0 to (nums.length - 2)
            STEP 3
            Initialize idxMin to idxSortedEnd in default
                idxMin = idxSortedEnd;
            STEP 4
            Iterate idxUnsortedRange from (idxSortedEnd + 1) to the end to find the minimum element
                STEP 5
                If nums[idxUnsortedRange] < nums[idxMin], (meaning a new minimum element is found, so there is a need to update the idxMin)
                    STEP 6
                    Update idxMin with idxUnsortedRange
                        idxMin = idxUnsortedRange;
            STEP 7
            If idxMin != idxSortedEnd, (meaning only do the swap operation when idxMin is not equal to initial idxSortedEnd)
                STEP 8
                swap(nums, idxSortedEnd, idxMin);
                
        -FUNC void swap(int[] nums, int i, int j)
        STEP 1
        Initialize temp with nums[i]
            int temp = nums[i];
        STPE 2
        Assign nums[j] to nums[i]
            nums[i] = nums[j]
        STEP 3
        Assign temp back to nums[j]
            nums[j] = temp;
        
    TIME:
        O(n * n), n is the number nums
    SPACE:
        O(1), no extra space is used
*/

class SelectionSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxMin = -1;
        // STEP 2
        for (int idxSortedEnd = 0; idxSortedEnd < nums.length - 1; idxSortedEnd++) {
            // STEP 3
            idxMin = idxSortedEnd;
            // STEP 4
            for (int idxUnsortedRange = idxSortedEnd + 1; idxUnsortedRange < nums.length; idxUnsortedRange++) {
                // STEP 5
                if (nums[idxUnsortedRange] < nums[idxMin]) {
                    // STEP 6
                    idxMin = idxUnsortedRange;
                }
            }
            // STEP 7
            if (idxMin != idxSortedEnd) {
                // STEP 8
                swap(nums, idxSortedEnd, idxMin);
            }
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        // STEP 1
        int temp = nums[i];
        // STEP 2
        nums[i] = nums[j];
        // STEP 3
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("sorted nums:  " + Arrays.toString(nums));
    }
}