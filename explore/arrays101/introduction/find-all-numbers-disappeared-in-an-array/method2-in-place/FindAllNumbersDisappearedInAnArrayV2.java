import java.util.Arrays;
import java.util.List;
import java.util.LinkedList;

/**
    VARS:
        result(List<Integer>): integer list for final return
        curNumMappedIdx(int): denote the mapped index in initial array calculated from current iterated number
    DESCRIPTION:
        STEP 1
        Initialize curNumMappedIdx to 0
        STEP 2
        Iterate each number nums[i] in initial array with index i
            STEP 3
            Calculate curNumMappedIdx from current iterated number nums[i]
                curNumMappedIdx = abs(nums[i]) - 1
            STEP 4
            If the element in the position curNumMappedIdx in initial array is positive, (meaning it hasn't been process before, so now it should be processed)
                STEP 5
                Negate the element in position curNumMappedIdx in initial array
                    nums[curNumMappedIdx] *= -1
        STEP 6
        Initialize result to LinkedList<Integer>, (here LinkedList is better than ArrayList for frequently adding and deleting at the beginning or end of array)
        STEP 7
        Iterate each number in processed initial array again
            If current iterated number is positive with iterated index i, (meaning the corresponding (index+1) is not appearing in initial array)
                STEP 8
                Add (i+1) to result
                    result.add(i + 1)
        STEP 9
        Return result
    TIME:
        O(n)
    SPACE:
        O(1), no extra space used except the final return result array
*/

class FindAllNumbersDisappearedInAnArrayV2 {
    
    public static List<Integer> findAllDisappeared(int[] nums) {
        // STEP 1
        int curNumMappedIdx = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            curNumMappedIdx = Math.abs(nums[i]) - 1;
            // STEP 4
            if (nums[curNumMappedIdx] > 0) {
                // STEP 5
                nums[curNumMappedIdx] *= -1;
            }
        }
        // STEP 6
        List<Integer> result = new LinkedList<>();
        // STEP 7
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                // STEP 8
                result.add(i + 1);
            }
        }
        // STEP 9
        return result;
    }
    
    public static void main(String[] args) {
        // int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = {1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        List<Integer> result = findAllDisappeared(nums);
        System.out.println("Disappeared: " + result.toString());
    }
}