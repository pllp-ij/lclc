import java.util.Arrays;

/*
    VARS:
        
    DESCRIPTION:
        STEP 1
        Map k within the range of initial array
            k %= nums.length;
        STEP 2
        Reverse initial array from 0 to nums.length - 1
        STEP 3
        Reverse array in step1 from 0 to k - 1
        STEP 4
        Reverse array in step2 from k to nums.length - 1
    TIME:
        O(n)
    SPACE:
        O(1), no extra space is used
*/

class RotateArrayV1 {
    
    public static void rotateArray(int[] nums, int k) {
        // STEP 1
        k %= nums.length;
        // STEP 2
        reverseArray(nums, 0, nums.length - 1);
        // STEP 3
        reverseArray(nums, 0, k - 1);
        // STEP 4
        reverseArray(nums, k, nums.length - 1);
    }
    
    public static void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
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