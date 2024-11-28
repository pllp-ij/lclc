import static com.anno.arrays.ManipulateArrays.swapTwo;
import java.util.Arrays;

/**
    VARS:
        leftPtr(int): pointer move from left to right for adding even numbers
        rightPtr(int): pointer move from right to left for adding odd numbers
    DESCRIPTION:
        STEP 1
        Initialize leftPtr to 0
        Initialize rightPtr to nums.length - 1
        STEP 2
        Loop while leftPtr <= rightPtr
            STEP 3
            Loop while leftPtr <= rightPtr still satisfied and leftPtr point to the first odd number from left to right, that number should be swapped to the right side
            while (leftPtr <= rightPtr && nums[leftPtr] % 2 == 0)
                STEP 4
                Increase leftPtr by one in each iteration
                    leftPtr++;
                
            Now after the while loop, there are two conditions:
                leftPtr > rightPtr or
                leftPtr <= rightPtr && nums[leftPtr] % 2 != 0

            STEP 5
            If leftPtr > rightPtr, (meaning the loop condition doesn't satisfied anyone), break the loop
                STEP 6
                Break the loop
                
            Now only the condition: leftPtr <= rightPtr && numbers[leftPtr] % 2 != 0 
            
            STEP 7
            Loop while leftPtr <= rightPtr still satisfied and rightPtr point to the first even nuber from right to left, that number should be swapped to the left side
            while (leftPtr <= rightPtr && nums[rightPtr] % 2 != 0)
                STEP 8
                Decrease rightPtr by one in each iteration
                    rightPtr--;
                    
            Now after the while loop, there are two conditions:
                leftPtr > rightPtr or 
                leftPtr <= rightPtr && nums[rightPtr] % 2 == 0
                
            STEP 9
            If leftPtr > rightPtr, (meaning the loop condition doesn't satisfied)
                STEP 10
                Break the loop
                
            Now only the condition: leftPtr <= rightPtr && nums[rightPtr] % 2 == 0
            
            STEP 11
            Swap two elements at leftPtr and rightPtr
                swapTwo(nums, leftPtr, rightPtr)
            STEP 12
            Increase leftPtr by one
                leftPtr++;
            STEP 13
            Decrease rightPtr by one
                rightPtr--;
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class SortArrayByParityV2 {
    
    public static void sortArrayByParity(int[] nums) {
        // STEP 1
        int leftPtr = 0;
        int rightPtr = nums.length - 1;
        // STEP 2
        while (leftPtr <= rightPtr) {
            // STEP 3
            while (leftPtr <= rightPtr && nums[leftPtr] % 2 == 0) {
                // STEP 4
                leftPtr++;
            }
            // STEP 5
            if (leftPtr > rightPtr) {
                // STEP 6
                break;
            }
            // STEP 7
            while (leftPtr <= rightPtr && nums[rightPtr] % 2 != 0) {
                // STEP 8
                rightPtr--;
            }
            // STEP 9
            if (leftPtr > rightPtr) {
                // STEP 10
                break;
            }
            // STEP 11
            swapTwo(nums, leftPtr, rightPtr);
            // STEP 12
            leftPtr++;
            // STEP 13
            rightPtr--;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        // int[] nums = {0};
        System.out.println("Before: " + Arrays.toString(nums));
        sortArrayByParity(nums);
        System.out.println("After: " + Arrays.toString(nums));
    }
}