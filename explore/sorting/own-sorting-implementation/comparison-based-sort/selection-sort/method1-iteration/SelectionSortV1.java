import java.util.Arrays;

/*
    VARS:
        idxSortedEnd(int): the index of sorted range, from 0 to (nums.length - 1), here is by iteration
        idxUnsortedRnage(int): the index to iterate each index of unsorted range from idxSortedEnd to the end
        idxMin(int): the index of minimum element of nums, initialized to idxSortedEnd
    DESCRIPTION:
        STEP 1
        Initialize idxMin to -1s
        STEP 2
        Iterate idxSortedEnd from 0 to (nums.length - 1), actually the end position should be (nums.length - 2) because the last element has no need to swap
            STEP 3
            Reset idxMin to idxSortedEnd, which will point to the minimum element of unsorted range
                idxMin = idxSortedEnd;
            STEP 4
            Iterate idxUnsortedRnage from idxSortedEnd to (nums.length - 1)
                STPE 5
                If idxUnsortedRnage != idxMin &&
                   nums[idxUnsortedRnage] < nums[idxMin], (first skip the first element , then swap when the element at idxUnsortedRnage is smaller than the element at idxMin)
                    STEP 6
                    Update idxMin to the idxUnsortedRnage to indicate the newly found minimum element
                        idxMin = idxUnsortedRnage;
            
            Now idxMin is pointing at the minimum element of unsorted range
            
            STEP 7
            If idxMin != idxSortedEnd, (meaning only when index of minimum element is not equal to idxSortedEnd should the swap operation be done)
                STEP 8
                Swap two elements at idxSortedEnd and idxMin respectively
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

class SelectionSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxMin = -1;
        // STEP 2
        for (int idxSortedEnd = 0; idxSortedEnd < nums.length; idxSortedEnd++) {
            // STEP 3
            idxMin = idxSortedEnd;
            // STEP 4
            for (int idxUnsortedRange = idxSortedEnd; idxUnsortedRange < nums.length; idxUnsortedRange++) {
                // STEP 5
                if (idxUnsortedRange != idxMin &&
                    nums[idxUnsortedRange] < nums[idxMin]) {
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