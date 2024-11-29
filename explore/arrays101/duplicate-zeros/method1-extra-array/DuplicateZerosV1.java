import java.util.Arrays;

/**
    VARS:
        countZerosNum(int): count the number of all the zeros in initial array
        newArr(int[]): extra array used to store expanded elements
    DESCRIPTION:
        STEP 1
        Calculate the total number of zeros in initial array returned by calling method
        STEP 2
        Initialize newArr with the length of initial array size plus countZerosNum
            int[] newArr = new int[nums.length + countZerosNum]
        STEP 3
        Iterate each number in initial array,
            STEP 4
            If met with zero elements, copy it twice into newArr
            STEP 5
            Else if met with non-zero elements, directly copy it into newArr
        STEP 6
        Copy back newArr to initial array with same size of initial array            
    TIME:
        O(n)
    SPACE:
        O(n)
    TO BE OPTIMIZED:
        Avoid using extra array space
*/

class DuplicateZerosV1 {
    
    public static void duplicateZeros(int[] nums) {
        // STEP 1
        int countZerosNum = getZerosNum(nums);
        // STEP 2
        int[] newArr = new int[nums.length + countZerosNum];
        // STEP 3
        int idxInNewArr = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                // STEP 4
                newArr[idxInNewArr] = 0;
                idxInNewArr++;
                newArr[idxInNewArr] = 0;
            } else {
                // STEP 5
                newArr[idxInNewArr] = nums[i];
            }
            idxInNewArr++;
        }
        // STEP 6
        for (int i = 0; i < nums.length; i++) {
            nums[i] = newArr[i];
        }
    }
    
    public static int getZerosNum(int[] nums) {
        int countZerosNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                countZerosNum++;
            }
        }
        return countZerosNum;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 0, 2, 3, 0, 4, 5, 0};
        // int[] nums = {1, 0, 2, 3, 4, 0, 5};
        System.out.println("Before: " + Arrays.toString(nums));
        duplicateZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}