import java.util.Arrays;


/**
    VARS:
        newArr(int[]): a new array if the length increase by one after adding one
        lastRightNumToAdd(int): the number at last right side digit number to be added by current iterated number
        idxRight(int): the iterative index from right to left on initial array
    DESCRIPTION:
        STEP 1
        Initialize newArr to nums, firstly we assume the length will not be increased
        Initialize lastRightNumToAdd to 1, at the beginning, the number to be added to the least siganificant digit is one, if it can also represent other numbers to be added
        Initialize idxRight to (nums.length - 1)
        STEP 2
        Loop while idxRight >= 0 and sumTwo >= 10, (meaning there will be digit increase movement)
            STEP 3
            Calculate the sum of adding lastRightNumToAdd and nums[idxRight]
                int sumTwo = nums[idxRight] + lastRightNumToAdd;
            STEP 4
            Update nums[idxRight] to sumTwo % 10
                nums[idxRight] = sumTwo % 10
            STEP 5
            Update lastRightNumToAdd to sumTwo / 10
                lastRightNumToAdd = sumTwo / 10
            STEP 6
            Decrease idxRight by one
                idxRight--
        
        Now there are two conditions:
            1. No exceed the left edge of initial array, idxRight point to the final digit number should be added by lastRightNumToAdd
            2. Exceed the left edge of initial array, with idxRight equal to -1
            
        if idxRight >= 0, (meaning condition 1 in above)
            STEP 7
            Add lastRightNumToAdd to current nums[idxRight]
                nums[idxRight] += lastRightNumToAdd
            STEP 8
            Return nums(also equal to newArr in this case)
        
        Now idxRight exceed the left edge of initial array
        
        STEP 9
        Recreate newArr with size of (nums.length + 1)
            newArr = new int[nums.length - 1]
        STEP 10
        Add lastRightNumToAdd to the position 0 of newArr
            newArr[0] = lastRightNumToAdd;
        STEP 11
        Iterate each number in initial array from left to right with index i
            STEP 12
            Copy each number from left to right(0 to nums.length - 1) to newArr(from 1 to nums.length)
                newArr[i + 1] = nums[i]
        STEP 13
        Return newArr
    TIME:
        O(n + n) ~ O(n), one pass for adding one, one pass for copy if result exceeding initial length
    SPACE:
        O(n), if result exceed initial length, extra space needed
*/

class PlusOneV1 {
    
    public static int[] plusOne(int[] nums) {
        // STEP 1
        int[] newArr = nums;
        int lastRightNumToAdd = 1;
        int idxRight = nums.length - 1;
        // STEP 2
        while (idxRight >= 0 && nums[idxRight] + lastRightNumToAdd >= 10) {
            // STEP 3
            int sumTwo = nums[idxRight] + lastRightNumToAdd;
            // STEP 4
            nums[idxRight] = sumTwo % 10;
            // STEP 5
            lastRightNumToAdd = sumTwo / 10;
            // STEP 6
            idxRight--;
        }
        if (idxRight >= 0) {
            // STEP 7
            newArr[idxRight] += lastRightNumToAdd;
            // STEP 8
            return newArr;
        }
        // STEP 9
        newArr = new int[nums.length + 1];
        // STEP 10
        newArr[0] = lastRightNumToAdd;
        // STEP 11
        for (int i = 0; i < nums.length; i++) {
            // STEP 12
            newArr[i + 1] = nums[i];
        }
        // STEP 13
        return newArr;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        // int[] nums = {4, 3, 2, 1};
        // int[] nums = {9};
        // int[] nums = {9, 9};
        // int[] nums = {8, 9};
        System.out.println("Before: " + Arrays.toString(nums));
        int[] numsNew = plusOne(nums);
        System.out.println("After: " + Arrays.toString(numsNew));
    }
}