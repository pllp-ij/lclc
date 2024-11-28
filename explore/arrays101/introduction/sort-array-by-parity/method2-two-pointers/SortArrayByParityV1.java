import static com.anno.arrays.ManipulateArrays.swapTwo;
import java.util.Arrays;

/**
    VARS:
        slowPtr(int): the pointer pointing to the next place where new even number can be inserted,
                    also represent the even integers number until current iteration index
        fastPtr(int): the pointer to iterate each number in array
    DESCRIPTION:
        STEP 1
        Initialize slowPtr to 0
        Initialize fastPtr to 0
        STEP 2
        Loop while fastPtr < nums.length
            If nums[fastPtr] % 2 === 0, (meaning nums[fastPtr] is even number)
                STEP 3
                Swap the two elements at slowPtr and fastPtr
                    swapTwo(nums, slowPtr, fastPtr)
                STEP 4
                Increase slowPtr by one to point to the next element
                    slowPtr++;
                STEP 5
                Increase fastPtr by one to next iteration
                    fastPtr++;
            Else, (meaning nums[fastPtr] is odd number)
                STEP 6
                Increase fastPtr by one to next iteration
                    fastPtr++;
                
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class SortArrayByParityV1 {
    
    public static void sortArrayByParity(int[] nums) {
        // STEP 1
        int slowPtr = 0;
        int fastPtr = 0;
        // STEP 2
        while (fastPtr < nums.length) {
            if (nums[fastPtr] % 2 == 0) {
                // STEP 3
                swapTwo(nums, slowPtr, fastPtr);
                // STEP 4
                slowPtr++;
                // STEP 5
                fastPtr++;
            } else {
                // STEP 6
                fastPtr++;
            }
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 1, 2, 4};
        int[] nums = {0};
        System.out.println("Before: " + Arrays.toString(nums));
        sortArrayByParity(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}