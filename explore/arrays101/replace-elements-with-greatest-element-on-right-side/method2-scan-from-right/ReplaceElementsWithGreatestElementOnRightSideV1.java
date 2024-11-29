import java.util.Arrays;

/**
    VARS:
        maxValueOnRightSide(int): max value on the right side of current iterated index in initial array
        curNum(int): current iterated number value
    DESCRIPTION:
        STEP 1
        Initialize maxValueOnRightSide to -1
        Initialize curNum to -1
        STEP 2
        Iterate initial array from right to left, from (nums.length - 1) to 0
            STEP 3
            Store current iterated number value
                curNum = nums[i];
            STEP 4
            Assign maxValueOnRightSide to current iterated index
                nums[i] = maxValueOnRightSide
            STEP 5
            Update maxValueOnRightSide to Math.max(curNum, maxValueOnRightSide)
                maxValueOnRightSide = Math.max(curNum, maxValueOnRightSide)
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class ReplaceElementsWithGreatestElementOnRightSideV1 {
    
    public static void replaceElements(int[] nums) {
        // STEP 1
        int maxValueOnRightSide = -1;
        int curNum = -1;
        // STEP 2
        for (int i = nums.length - 1; i >= 0; i--) {
            // STEP 3
            curNum = nums[i];
            // STEP 4
            nums[i] = maxValueOnRightSide;
            // STEP 5
            maxValueOnRightSide = Math.max(curNum, maxValueOnRightSide);
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {17, 18, 5, 4, 6, 1};
        // int[] nums = {400};
        System.out.println("Before: " + Arrays.toString(nums));
        replaceElements(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}