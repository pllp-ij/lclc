import java.util.Arrays;

/**
    OPTIMIZATION:
        No need to search positive/negative boundary first
    VARS:
        left(int): point from left to right
        right(int): point from right to left
        sortedArr(int[]): final squared and sorted array to return
    Description:
        STEP 1
        Initialize left pointer to 0, right pointer to (nums.length - 1)
        Initialize sortedArr with same size of nums
        STEP 2
        Iterate each index(i) in sortedArr from right end to left end
            STEP 3
            If square(nums[left]) > square(nums[right]), update sortedArr[i] with square(nums[left])
                sortedArr[i] = nums[left] * nums[left++];
            STEP 4
            Else if square(nums[left]) < square(nums[right]), update sortedArr[i] with square(nums[right])
                sortedArr[i] = nums[right] * nums[right--];
        STEP 5
        Copy sortedArr to nums
            
    TIME:
        O(n)
    SPACE:
        O(n)
 */

class SquaresOfASortedArrayV2 {
    
    public static void squaresOfSorted(int[] nums) {
        // STEP 1
        int left = 0, right = nums.length - 1;
        int[] sortedArr = new int[nums.length];
        // STEP 2
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[left] * nums[left] > nums[right] * nums[right]) {
                // STEP 3
                sortedArr[i] = nums[left] * nums[left++];
            } else {
                // STEP 4
                sortedArr[i] = nums[right] * nums[right--];
            }
        }
        // STEP 5
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sortedArr[i];
        }
    }
        
    public static void main(String[] args) {
        int[] nums = {-6, -4, -1, 2, 3, 5};
        System.out.println("Before sorted: " + Arrays.toString(nums));
        squaresOfSorted(nums);
        System.out.println("After sorted: " + Arrays.toString(nums));
    }
}