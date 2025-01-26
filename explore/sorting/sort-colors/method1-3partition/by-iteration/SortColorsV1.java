import java.util.Arrays;

/*
    VARS:
        idxZerosRangeEnd(int): the last index of zeros range after sorted
        idxTwosRangeStart(int): the first index of twos range after sorted
        idxCur(int): the iterated index of nums from 0 to (nums.length - 1)
    DESCRIPTION:
        STEP 1
        Initialize idxZerosRangeEnd to -1
        Initialize idxTwosRangeStart to nums.length
        STEP 2
        Iterate each index of nums from 0 to (idxTwosRangeStart - 1) with idxCur, (note that the right boundary of iteration is "idxTwosRangeStart - 1" instead of the "nums.length - 1", because the idxTwosRangeStart pointer is the start range of twos, so there is no need to process numbers on the right side of idxTwosRangeStart)
            STEP 3
            If nums[idxCur] == 0, (menaing a 0 is met, it should be put on the left side of nums range)
                STEP 4
                Increase idxZerosRangeEnd by one first to be inserted swapped element
                    idxZerosRangeEnd++;
                STEP 5
                Swap two elements at idxZerosRangeEnd and idxCur
                    swap(nums, idxZerosRangeEnd, idxCur);
                STEP 6
                Skip current iteration to next one
                    continue;
            STEP 7
            If nums[idxCur] == 2, (meaning a 2 is met, it should be put on the right side of nums range)
                STEP 8
                Decrease idxTwosRangeStart by one first to be inserted swapped element
                    idxTwosRangeStart--;
                STEP 9
                Swap two elements at idxCur and idxTwosRangeStart
                    swap(nums, idxCur, idxTwosRangeStart);
                    
                Note here DO NOT increase idxCur by iteration, because the swapped element's value maybe equal to 0, which still need to be processed, so here we first decrease idxCur by one then skip current iteration
                    
                STEP 10
                Decrease idxCur by one to recheck current index
                    idxCur--;
            
            For the value at idxCur equals to 1, there should be nothing to do
            
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
        // STEP 2
        for (int idxCur = 0; idxCur < idxTwosRangeStart; idxCur++) {
            // STEP 3
            if (nums[idxCur] == 0) {
                // STEP 4
                idxZerosRangeEnd++;
                // STEP 5
                swap(nums, idxZerosRangeEnd, idxCur);
                // STEP 6
                continue;
            }
            // STEP 7
            if (nums[idxCur] == 2) {
                // STEP 8
                idxTwosRangeStart--;
                // STEP 9
                swap(nums, idxCur, idxTwosRangeStart);
                // STEP 10
                idxCur--;
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