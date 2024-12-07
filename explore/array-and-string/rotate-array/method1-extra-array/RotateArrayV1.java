import java.util.Arrays;

/*
    NOTE: there is no need to first module k to the nums.length since the assignment of value in new array is already considered with cycle logic
    VARS:
        newArr(int[]): a new array to store the rotated array
    DESCRIPTION:
        STEP 1
        Initialize the newArr with the same size of initial array
        STEP 2
        Iterate each number in initial array with index i from 0 to nums.length - 1
            STEP 3
            Add current number with index i to (i + k) % nums.length in new array
        STEP 4
        Copy newArr back to nums
    TIME:
        O(n)
    SPACE:
        O(n), extra space is used
*/

class RotateArrayV1 {
    
    public static void rotateArray(int[] nums, int k) {
        // STEP 1
        int[] newArr = new int[nums.length];
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            newArr[(i + k) % nums.length] = nums[i];
        }
        // STEP 4
        for (int i = 0; i < newArr.length; i++) {
            nums[i] = newArr[i];
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        System.out.println("Before: " + Arrays.toString(nums));
        rotateArray(nums, k);
        System.out.println("After " + k + " rotate: " + Arrays.toString(nums));
    }
}