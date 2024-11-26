import static com.anno.arrays.PrintArrays.printArrRange;
import java.util.Arrays;


/**
    VARS:
        fastPtr(int): run faster pointer from right to left to seek for elements equal to val
        slowPtr(int): point to the place where next elements equal to val will be moved to in the right side
    DESCRIPTION:
        STEP 1
        Initialize fastPtr to nums.length - 1
        Initialize slowPtr to nums.length - 1
        STEP 2
        Loop while fastPtr >= 0
            STEP 3
            If nums[fastPtr] == val, (meaning they are not pointing to same value), current iterated number at index of fastPtr equals to val,
                STEP 4
                Swap the number at fastPtr and slowPtr by calling method
                    Swap(nums, fastPtr, slowPtr)
                STEP 5
                Decrease slowPtr by one
                    slowPtr--;
            STEP 6
            Decrease fastPtr by one
                fastPtr--;
        STEP 7
        Return the non-val nums, happend to be (slowPtr + 1)
            Return slowPtr + 1;
                
        
    TIME:
        O(n)
    SPACE:
        O(n), extra space for non-val elements
    TO BE OPTIMIZED:
        Avoid using extra space
*/

class RemoveElementV1 {
    
    public static int removeElement(int[] nums, int val) {
        // STEP 1
        int fastPtr = nums.length - 1;
        int slowPtr = nums.length - 1;
        // STEP 2
        while (fastPtr >= 0) {
            // STEP 3
            if (nums[fastPtr] == val) {
                // STEP 4
                swapTwo(nums, fastPtr, slowPtr);
                // STEP 5
                slowPtr--;
            }
            // STEP 6
            fastPtr--;
        }
        // STEP 7
        return slowPtr + 1;
    }
    
    public static void swapTwo(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 2, 3};
        int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println("Before: " + Arrays.toString(nums));
        int k = removeElement(nums, 2);
        System.out.print("After: ");
        printArrRange(nums, 0, k);
    }
}