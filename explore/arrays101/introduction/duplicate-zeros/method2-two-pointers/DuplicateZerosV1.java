import java.util.Arrays;

/**
    VARS:
        alreadyDupZerosNum(int): count the number of zeros that has already been duplicated
        idxInInitArr: index in initial array iterating from left to right
        idxInExpandedArr: index in doomy expanded array corresponding to each idxInInitArr, 
            it is the starting place even current number is zero that should be duplicated
    DESCRIPTION:
        STEP 1
        Initialize alreadyDupZerosNum to 0
        Initialize idxInInitArr to 0
        Initialize idxInExpandedArr to 0
        STEP 2
        Iterate each number in initial array with index of idxInInitArr
            STEP 3
            Calculate idxInExpandedArr corresponding to current idxInInitArr,
                idxInExpandedArr = idxInInitArr + alreadyDupZerosNum
            STEP 4
            If current iterated number is zero,
                STEP 5
                If idxInExpandedArr == nums.length - 1, (meaning current iterated number in initial array is zero and it is mapped to the last element of dommy new array, but it cannot be duplicated since its duplication exceed the boundary)
                    STEP 6
                    Assign 0 to the last element in initial array
                        nums[nums.length - 1] = 0;
                    STEP 7
                    Move idxInInitArr backward by one step
                        idxInInitArr--;
                    STEP 8
                        Break the loop
                Else if current iterated number is zero and idxInExpandedArr points to the last second element, so after duplication, the expanded array is full filled, then break at current iterated index
                    STEP 9
                    Increase the alreadyDupZerosNum by one
                    STEP 10
                    Break the loop
                Else, (if current iterated number is zero and idxInExpandedArr is not exceeding the range of dommy new array, increase alreadyDupZerosNum by one)
                    STEP 11
                    alreadyDupZerosNum++;
            Else, (meaning current iterated number is non-zero)
                STEP 12
                If idxInExpandedArr == nums.length - 1, (meaning current iterated number is non-zero and it is mapped to the last element of dommy new array), directly break the loop
                    Break the loop

        Now idxInInitArr point to the last element without outranging doomy new array,
        Now alreadyDupZerosNum represent number of zeros that have already been duplicated

        STEP 13
        Loop until idxInInitArr reaching -1 from right to left side
            STEP 14
            If nums[idxInInitArr] is zero element,
                STEP 15
                Assign the duplication zero to the place in dommy new array
                    nums[idxInInitArr + alreadyDupZerosNum] = 0;
                STEP 16
                Decrease alreadyDupZerosNum by one after assigning the duplication zero
                    alreadyDupZerosNum--;
                STEP 17
                Assign the first raw zero(not duplication one) to the place in dommy new array
                    nums[idxInInitArr + alreadyDupZerosNum] = 0;
            Else if nums[idxInInitArr] is non-zero element,
                STEP 18
                Assign current iterated number to the place in dommy new array
                    nums[idxInInitArr + alreadyDupZerosNum] = nums[idxInInitArr]
            STEP 19
                Move idxInInitArr left by one step
                    idxInInitArr--;
    TO BE OPTIMIZED:
        Not truly two pointers, but one pointer with another variable alreadyDupZerosNum to manage shift distance,
        so try to apply truly two pointers without using alreadyDupZerosNum
*/

class DuplicateZerosV1 {
    
    public static void duplicateZeros(int[] nums) {
        // STEP 1
        int alreadyDupZerosNum = 0;
        int idxInInitArr = 0;
        int idxInExpandedArr = 0;
        // STEP 2
        for (; idxInInitArr < nums.length; idxInInitArr++) {
            // STEP 3
            idxInExpandedArr = idxInInitArr + alreadyDupZerosNum;
            // STEP 4
            if (nums[idxInInitArr] == 0) {
                // STEP 5
                if (idxInExpandedArr == nums.length - 1) {
                    // STEP 6
                    nums[nums.length - 1] = 0;
                    // STEP 7
                    idxInInitArr--;
                    // STEP 8
                    break;
                } else if (idxInExpandedArr == nums.length - 2) {
                    // STEP 9
                    alreadyDupZerosNum++;
                    // STEP 10
                    break;
                } else {
                    // STEP 11
                    alreadyDupZerosNum++;
                }
            } else {
                // STEP 12
                if (idxInExpandedArr == nums.length - 1) {
                    break;
                }
            }
        }
        // STEP 13
        while (idxInInitArr >= 0) {
            // STEP 14
            if (nums[idxInInitArr] == 0) {
                // STEP 15
                nums[idxInInitArr + alreadyDupZerosNum] = 0;
                // STEP 16
                alreadyDupZerosNum--;
                // STEP 17
                nums[idxInInitArr + alreadyDupZerosNum] = 0;
            } else {
                // STEP 18
                nums[idxInInitArr + alreadyDupZerosNum] = nums[idxInInitArr];
            }
            // STEP 19
            idxInInitArr--;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 0, 2, 3, 0, 4, 5, 0};
        // int[] nums = {1, 0, 2, 3, 4, 0, 5};
        int[] nums = {1, 5, 2, 0, 6, 8, 0, 6, 0};
        System.out.println("Before: " + Arrays.toString(nums));
        duplicateZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}