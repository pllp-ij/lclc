import java.util.Arrays;

/**
    VARS:
        slowPtr(int): the slower pointer pointing to the next place where can be inserted into non-zero elements
        fastPtr(int): the faster pointer pointing to the current iterated index
    DESCRIPTION:
        STEP 1
        Initialize slowPtr to 0
        Initialize fastPtr to 0
        STEP 2
        Loop while fastPtr < nums.length
            If nums[fastPtr] != 0
                If slowPtr != fastPtr, (meaning they are not pointing to the same element)
                    STEP 3
                    Assign element at fastPtr to slowPtr, and increase slowPtr by one
                        nums[slowPtr++] = nums[fastPtr]
                    STEP 4
                    Assign element at fastPtr to 0, and increase fastPtr by one
                        nums[fastPtr++] = 0
                Else, (meaning slowPtr == fastPtr, they are pointing to the same element)
                    STEP 5
                    Increase slowPtr by one
                        slowPtr++;
                    STEP 6
                    Increase fastPtr by one
                        fastPtr++;
            Else, (meaning nums[fastPtr] == 0)
                STEP 7
                Move fastPtr to the right by one step
                    fastPtr++;
    TIME:
        O(n)
    SPACE:
        O(1)
    TO BE OPTIMIZED:
        Notice that STEP 3 combined with STEP 4 just equal to a swap manipulation, so how
        to directly by applying swap operaion
*/

class MoveZerosV1 {
    
    public static void moveZeros(int[] nums) {
        // STEP 1
        int slowPtr = 0;
        int fastPtr = 0;
        // STEP 2
        while (fastPtr < nums.length) {
            if (nums[fastPtr] != 0) {
                if (slowPtr != fastPtr) {
                    // STEP 3
                    nums[slowPtr++] = nums[fastPtr];
                    // STEP 4
                    nums[fastPtr++] = 0;
                } else {
                    // STEP 5
                    slowPtr++;
                    // STEP 6
                    fastPtr++;
                }
            } else {
                // STEP 7
                fastPtr++;
            }
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        // int[] nums = {2, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        moveZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}