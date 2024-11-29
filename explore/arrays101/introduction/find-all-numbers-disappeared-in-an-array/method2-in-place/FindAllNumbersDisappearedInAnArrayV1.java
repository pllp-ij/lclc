import java.util.Arrays;

/**
    VARS:
        result(int[]): integer array for final return
        curNumMappedIdx(int): denote the mapped index in initial array calculated from current iterated number
        appearNumDistinct(int): the number of integer has already mapped
        idxResult(int): iterate index when adding elements which are not appearing in initial array into final array
    DESCRIPTION:
        STEP 1
        Initialize curNumMappedIdx to 0
        Initialize appearNumDistinct to 0
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
                Increase appearNumDistinct by one
                    appearNumDistinct++
        STEP 7
        Initialize result with size of (nums.length - appearNumDistinct)
        Initialize iterate index idxResult to 0
        STEP 8
        Iterate each number in processed initial array again
            If current iterated number is positive with iterated index i, (meaning the corresponding (index+1) is not appearing in initial array)
                STEP 9
                Add (i+1) to result[idxResult]
                    result[idxResult] = i + 1
                STEP 10
                Increase idxResult by one
                    idxResult++;
        STEP 11
        Return result
    TIME:
        O(n)
    SPACE:
        O(1), no extra space used except the final return result array
*/

class FindAllNumbersDisappearedInAnArrayV1 {
    
    public static int[] findAllDisappeared(int[] nums) {
        // STEP 1
        int curNumMappedIdx = 0;
        int appearNumDistinct = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            curNumMappedIdx = Math.abs(nums[i]) - 1;
            // STEP 4
            if (nums[curNumMappedIdx] > 0) {
                // STEP 5
                nums[curNumMappedIdx] *= -1;
                // STEP 6
                appearNumDistinct++;
            }
        }
        // STEP 7
        int[] result = new int[nums.length - appearNumDistinct];
        int idxResult = 0;
        // STEP 8
        for (int i = 0; i < nums.length; i++) {
            
            if (nums[i] > 0) {
                // STEP 9
                result[idxResult] = i + 1;
                // STEP 10
                idxResult++;
            }
        }
        // STEP 11
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        // int[] nums = {1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        int[] result = findAllDisappeared(nums);
        System.out.println("Disappeared: " + Arrays.toString(result));
    }
}