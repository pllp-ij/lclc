import static com.anno.arrays.PrintArrays.printArrRange;
import java.util.Arrays;

/**
    VARS:
        fastPtr(int): right pointer which run faster from left to right in advance to seek for numbers not equal to slower one
        slowPtr(int): left pointer run from left to right which points to the place where can be inserted new non-duplication number
    DESCRIPTION:
        STEP 1
        Initialize fastPtr to 0
        Initialize slowPtr to 0
        STEP 2
        Loop while fastPtr < nums.length
            STEP 3
            Loop under fastPtr < nums.length and while nums[fastPtr] == nums[slowPtr], (meaning fastPtr and slowPtr both point to same values)
                STEP 4
                Move fastPtr right for one step
                    fastPtr++
            
            Now fastPtr is pointing at the new value different from slower one within array range,
            or fastPtr is outside the range of array
            
            STEP 5
            If fastPtr is within the range of array,
                STEP 6
                Increase slowPtr by one and copy nums[fastPtr] to nums[slowPtr]
                    nums[++slowPtr] = nums[fastPtr]
        STEP 7
        Return slowPtr + 1
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class RemoveDuplicatesFromSortedArrayV1 {
    
    public static int revemoDuplicates(int[] nums) {
        // STEP 1
        int fastPtr = 0;
        int slowPtr = 0;
        // STEP 2
        while (fastPtr < nums.length) {
            // STEP 3
            while (fastPtr < nums.length && nums[fastPtr] == nums[slowPtr]) {
                // STEP 4
                fastPtr++;
            }
            // STEP 5
            if (fastPtr < nums.length) {
                nums[++slowPtr] = nums[fastPtr];                
            }
        }
        // STEP 6
        return slowPtr + 1;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Before: " + Arrays.toString(nums));
        int k = revemoDuplicates(nums);
        System.out.print("After: ");
        printArrRange(nums, 0, k);
    }
}