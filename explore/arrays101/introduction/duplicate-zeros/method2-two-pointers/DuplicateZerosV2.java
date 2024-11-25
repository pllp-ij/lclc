import java.util.Arrays;

/**
    OPTIMIZATION:
        Truly two pointers
    VARS:
        idxInInitArr: index in initial array iterating from left to right
        expandedArrLength: the length of dommy expanded array after adding current iterated number
        idxWhenCopy: index when copy back elements
    DESCRIPTION:
        STEP 1
        Initialize idxInInitArr to 0
        Initialize expandedArrLength to 0
        Initialize idxWhenCopy to (nums.length - 1)
        STEP 2
        Iterate each number in initial array,
            STEP 3
            If current iterated number is zero
                STEP 4
                Calculate expandedArrLength
                    expandedArrLength += 2
                STEP 5
                If expandedArrLength == nums.length + 1, (meaning current iterated number is zero and it cannot be duplicated because it exceed the range of array)
                    STEP 6
                    Assign 0 to the last element of initial array
                        nums[idxWhenCopy] = 0;
                    STEP 7
                    Decrease right pointer idxWhenCopy by one
                        idxWhenCopy--;
                    STEP 8
                    Move current iterated index backward by one
                        idxInInitArr--;
                    STEP 9
                    Break the loop
                STEP 10
                Else if expandedArrLength == nums.length, (meaning current iterated number is zero and it happen to full fill the dommy new array), directly break the loop
                    STEP 11
                    Break the loop
            Else, (meaning current iterated number is non-zero)
                STEP 12
                Calculate expandedArrLength
                    expandedArrLength += 1
                STEP 13
                If expandedArrLength == nums.length, (meaning current iterated number is non-zero and it is mapped to the last element of the doomy new array)
                    STEP 14
                    Break the loop
        Now idxInInitArr point to the last element which can be included into dommy new array
        Now idxWhenCopy point to the place where can be inserted into elements
        
        STEP 15
        Loop until idxInInitArr >= 0
            STEP 16
            If nums[idxInInitArr] is zero,
                STEP 17
                Assign 0 duplication to the place at idxWhenCopy
                    nums[idxWhenCopy] = 0;
                STEP 18
                Decrease idxWhenCopy by one
                    idxWhenCopy--;
                STEP 19
                Assign 0 raw to the place at idxWhenCopy
                    nums[idxWhenCopy] = 0;
            Else, (meaning nums[idxInInitArr] is non-zero)
                STEP 20
                Assign nums[idxInInitArr] to nums[idxWhenCopy]
                    nums[idxWhenCopy] = nums[idxInInitArr];
            STEP 21
            Decrease idxInInitArr by one
                idxInInitArr--;
            STEP 22
            Decrease idxWhenCopy by one
                idxWhenCopy--;
*/

class DuplicateZerosV2 {
    
    public static void duplicateZeros(int[] nums) {
        // STEP 1
        int idxInInitArr = 0;
        int expandedArrLength = 0;
        int idxWhenCopy = nums.length - 1;
        // STEP 2
        for (; idxInInitArr < nums.length; idxInInitArr++) {
            // STEP 3
            if (nums[idxInInitArr] == 0) {
                // STEP 4
                expandedArrLength += 2;
                if (expandedArrLength == nums.length + 1) {
                    // STEP 5
                    nums[idxWhenCopy] = 0;
                    // STEP 6
                    idxWhenCopy--;
                    // STEP 7
                    idxInInitArr--;
                    // STEP 8
                    break;
                } else if (expandedArrLength == nums.length) {
                    // STEP 9
                    break;
                }
            } else {
                // STEP 10
                expandedArrLength += 1;
                // STEP 11
                if (expandedArrLength == nums.length) {
                    // STEP 12
                    break;
                }
            }
        }
        // STEP 13
        while (idxInInitArr >= 0) {
            // STEP 14
            if (nums[idxInInitArr] == 0) {
                // STEP 15
                nums[idxWhenCopy] = 0;
                // STEP 16
                idxWhenCopy--;
                // STEP 17
                nums[idxWhenCopy] = 0;
            } else {
                // STEP 18
                nums[idxWhenCopy] = nums[idxInInitArr];
            }
            // STEP 19
            idxInInitArr--;
            // STEP 20
            idxWhenCopy--;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 0, 2, 3, 0, 4, 5, 0};
        int[] nums = {1, 0, 2, 3, 4, 0, 5};
        // int[] nums = {1, 5, 2, 0, 6, 8, 0, 6, 0};
        System.out.println("Before: " + Arrays.toString(nums));
        duplicateZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}