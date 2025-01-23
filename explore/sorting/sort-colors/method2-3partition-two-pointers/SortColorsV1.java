import java.util.Arrays;

/*
    VARS:
        idxZerosRangeEnd(int): the last index of zeros range after sorted
        idxTwosRangeStart(int): the first index of twos range after sorted
        idxCur(int): the iterated index from 0 to idxTwosRangeStart
    DESCRIPTION:
        STEP 1
        Initialize idxZerosRangeEnd to -1
        Initialize idxTwosRangeStart to nums.length
        Initialize idxCur to 0
        STEP 2
        Loop while idxCur < idxTwosRangeStart, (when current iterated point reach the start index of twos range after sorted, the loop stopped)
            If nums[idxCur] == 0, (a swap need to put 0 to the left side of nums)
                STEP 4
                Increase idxTwosRangeStart by one where to be inserted the swapped element
                    idxTwosRangeStart++;
                STEP 5
                Swap two elements at idxTwosRangeStart and idxCur
                    swap(idxTwosRangeStart, idxCur)
                STEP 6
                Increase idxCur by one to next position
                    idxCur++;
            Else if nums[idxCur] == 2, (a swap need to put 2 to the right side of nums)
                STEP 7
                Decrease idxTwosRangeStart by one to move to the left one index where swapped element should be inserted
                    idxTwosRangeStart--;
                STEP 8
                Swap two elements at idxCur and idxTwosRangeStart respectively
                    swap(nums, idxCur, idxTwosRangeStart);
            Else if nums[idxCur] == 1, (no swap operation will be done)
                STEP 9
                Increase idxCur by one directly
                    idxCur++;
        
    TIME:
        O(n), n is the size of nums, because there is only one pass of nums when partitioning
    SPACE:
        O(1), no extra space is used
*/

class SortColorsV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int idxZerosRangeEnd = -1;
        int idxTwosRangeStart = nums.length;
        int idxCur = 0;
        // STPE 2
        while (idxCur < idxTwosRangeStart) {
            // STEP 3
            if (nums[idxCur] == 0) {
                // STEP 4
                idxZerosRangeEnd++;
                // STEP 5
                swap(nums, idxZerosRangeEnd, idxCur);
                // STEP 6
                idxCur++;
            } else if (nums[idxCur] == 2) {
                // STEP 7
                idxTwosRangeStart--;
                // STEP 8
                swap(nums, idxCur, idxTwosRangeStart);
            } else if (nums[idxCur] == 1) {
                // STEP 9
                idxCur++;
            }
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sort: " + Arrays.toString(nums));
    }
}