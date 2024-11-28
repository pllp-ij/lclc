import static com.anno.arrays.ManipulateArrays.swapTwo;
import java.util.Arrays;

/**
    VARS:
        slowPtr(int): the slower pointer pointing to the next place where can be inserted into non-zero elements
                      all the elements on the left side of slowPtr are non-zeros, slowPtr often point to the first zero to be swapped
        fastPtr(int): the faster pointer pointing to the current iterated index
    DESCRIPTION:
        STEP 1
        Initialize slowPtr to 0
        Initialize fastPtr to 0
        STEP 2
        Loop while fastPtr < nums.length
            If nums[fastPtr] != 0
                STEP 3
                Swap the elements at slowPtr and fastPtr
                STEP 4
                Increase slowPtr by one(moving to right by one step)
                    slowPtr++
            STEP 5
            Increase fastPtr by one in each iteration
                fastPtr++
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class MoveZerosV2 {
    
    public static void moveZeros(int[] nums) {
        // STEP 1
        int slowPtr = 0;
        int fastPtr = 0;
        // STEP 2
        while (fastPtr < nums.length) {
            if (nums[fastPtr] != 0) {
                // STEP 3
                swapTwo(nums, slowPtr, fastPtr);
                // STEP 4
                slowPtr++;
            }
            // STEP 5
            fastPtr++;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {0, 1, 0, 3, 12};
        int[] nums = {2, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        moveZeros(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}