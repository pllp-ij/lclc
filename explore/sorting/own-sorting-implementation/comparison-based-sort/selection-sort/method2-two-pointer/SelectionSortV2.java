import java.util.Arrays;

/*
    VARS:
        idxSortedEnd(int): the pointer of sorted range, from 0 to (nums.length - 1), here is by iteration
        idxUnsortedRnage(int): the pointer to iterate each index of unsorted range from idxSortedEnd to the end
        idxMin(int): the index of minimum element of nums, initialized to idxSortedEnd
    DESCRIPTION:
        STEP 1
        Initialize idxSortedEnd to 0
        Initialize idxUnsortedRnage to -1
        Initialize idxMin to -1
        STEP 2
        Loop while idxSortedEnd < nums.length - 1, (meaning the loop will stop when pointer reach the second last element of nums)
            STEP 3
            Reset idxMin to idxSortedEnd each time
                idxMin = idxSortedEnd;
            STEP 4
            Reset idxUnsortedRnage to (idxSortedEnd + 1) each time
                idxUnsortedRnage = idxSortedEnd + 1;
            STEP 5
            Loop while idxUnsortedRnage < nums.length, (meaning the pointer will stop after reaching outside the range of nums)
                STEP 6
                If nums[idxUnsortedRnage] < nums[idxMin], (a new minimum element is found)
                    STEP 7
                    Update idxMin with idxUnsortedRnage
                        idxMin = idxUnsortedRnage;
                STEP 8
                Increase idxUnsortedRnage by one to move to next position
                    idxUnsortedRnage++;
            STEP 9
            If idxMin != idxSortedEnd, (skip initial state where idxMin was initialized to idxSortedEnd)
                STEP 10
                Swap two element at idxSortedEnd and idxMin respectively
                    swap(idxSortedEnd, idxMin)
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

class SelectionSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxSortedEnd = 0;
        int idxUnsortedRnage = -1;
        int idxMin = -1;
        // STEP 2
        while (idxSortedEnd < nums.length - 1) {
            // STEP 3
            idxMin = idxSortedEnd;
            // STEP 4
            idxUnsortedRnage = idxSortedEnd + 1;
            // STEP 5
            while (idxUnsortedRnage < nums.length) {
                // STEP 6
                if (nums[idxUnsortedRnage] < nums[idxMin]) {
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