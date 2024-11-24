import java.util.Arrays;

/**
    VARS:
        left(int): point to non-positive values from right to left until array start
        right(int): point to positive values from left to right until array end
        sortedArr(int[]): final squared and sorted array to return
    Description:
        STEP 1
        Initialize the right pointer to the right side of boundary of negative and positive range by calling function,
        Initialize the left pointer to (right - 1)
        Initialize sortedArr with the same size of nums
        STEP 2
        Iterate each index(i) of sortedArr from left to right
                STEP 3
                If right pointer outside the right edge of array, update sortedArr with nums[left] and move left pointer to left by one
                STEP 4
                If left pointer outside the left edge of array, update sortedArr with nums[right] and move right pointer to right by one
                STEP 5
                Else, (left, right pointer both within array range), update sortedArr by comparing nums[left] and nums[right]
                    STEP 6                    
                    If square(nums[left]) < square(nums[right]), update sortedArr and move left pointer to left by one
                        sortedArr[i] = nums[left] * nums[left--];
                    STEP 7
                    Else, update sortedArr and move right pointer to right by one
                        sortedArr[i] = nums[right] * nums[right++];
        STEP 8
        Copy back sortedArr to nums
    TIME:
        O(n)
    SPACE:
        O(n)
    TO BE OPTIMIZED:
        Avoid searching the boundary of postive and negative range first
    KEY POINTS:
        When find the boundary edge in array, there should be edge cases where the index located outside the range of array,
        pay extra attention on that and address it with different conditions
 */

class SquaresOfASortedArrayV1 {
    
    public static void squaresOfSorted(int[] nums) {
        // STEP 1
        int right = findBoundaryRight(nums);
        int left = right - 1;
        int[] sortedArr = new int[nums.length];
        // STEP 2
        for (int i = 0; i < sortedArr.length; i++) {
            if (right >= nums.length) {
                // STEP 3
                sortedArr[i] = nums[left] * nums[left--];
            } else if (left < 0) {
                // STEP 4
                sortedArr[i] = nums[right] * nums[right++];
            } else {
                // STEP 5
                if (nums[left] * nums[left] < nums[right] * nums[right]) {
                    // STEP 6
                    sortedArr[i] = nums[left] * nums[left--];
                } else {
                    // STEP 7
                    sortedArr[i] = nums[right] * nums[right++];
                }
            }
        }
        // STEP 8
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sortedArr[i];
        }
    }
    
    public static int findBoundaryRight(int[] nums) {
        int right = 0;
        while (right < nums.length && nums[right] <= 0) {
            right++;
        }
        return right;
    }
        
    public static void main(String[] args) {
        int[] nums = {-6, -4, -1, 2, 3, 5};
        System.out.println("Before sorted: " + Arrays.toString(nums));
        squaresOfSorted(nums);
        System.out.println("After sorted: " + Arrays.toString(nums));
    }
}