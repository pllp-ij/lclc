import java.util.Arrays;

/*
    VARS:
        idxSortedEnd(int): the pointer of sorted range, from 0 to (nums.length - 1), here is by iteration
        idxUnsortedRnage(int): the pointer to iterate each index of unsorted range from idxSortedEnd to the end
        idxMin(int): the index of minimum element of nums, initialized to idxSortedEnd
    DESCRIPTION:
        STEP 1
        Initialize idxSortedEnd to 0 at the beginning
        Initialize idxUnsortedRnage to -1
        Initialize idxMin to -1
        STEP 2
        Loop while idxSortedEnd < nums.length, (loop until reaching outside the range of nums)
            STEP 3
            Reset idxMin to idxSortedEnd each time for finding the minimum element
                idxMin = idxSortedEnd;
            STEP 4
            Reset idxUnsortedRnage to idxSortedEnd each time for finding the minimum element
                idxUnsortedRnage = idxSortedEnd;
            STEP 5
            Loop while idxUnsortedRnage < nums.length, (loop until reaching outside the remaining range of nums)
                STEP 6
                If idxUnsortedRnage != idxMin &&
                    nums[idxUnsortedRnage] < nums[idxMin], (first check to skip the initial position, second one to check a new minimum element is found)
                    STEP 7
                    Update idxMin with idxUnsortedRnage
                        idxMin = idxUnsortedRnage;
                STEP 8
                Increase idxUnsortedRnage by one to move to next position
                    idxUnsortedRnage++;
            STEP 9
            If idxMin != idxUnsortedRnage, (meaning the idxMin is not equal to initial position)
                STEP 10
                Do the swap operation
                    swap(nums, idxSortedEnd, idxMin);
            STEP 11
            Increase idxSortedEnd by one to move to next position
                idxSortedEnd++;
                    
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
        int idxSortedEnd = 0;
        int idxUnsortedRnage = -1;
        int idxMin = -1;
        // STEP 2
        while (idxSortedEnd < nums.length) {
            // STEP 3
            idxMin = idxSortedEnd;
            // STEP 4
            idxUnsortedRnage = idxSortedEnd;
            // STEP 5
            while (idxUnsortedRnage < nums.length) {
                // STEP 6
                if (idxUnsortedRnage != idxMin &&
                    nums[idxUnsortedRnage] < nums[idxMin]) {
                    // STEP 7
                    idxMin = idxUnsortedRnage;
                }
                // STEP 8
                idxUnsortedRnage++;
            }
            // STEP 9
            if (idxMin != idxSortedEnd) {
                // STEP 10
                swap(nums, idxSortedEnd, idxMin);
            }
            // STEP 11
            idxSortedEnd++;
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