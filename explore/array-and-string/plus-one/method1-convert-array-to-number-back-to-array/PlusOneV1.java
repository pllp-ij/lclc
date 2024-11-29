import java.util.Arrays;

/**
    VARS:
        convertedNum(int): the value of number converted from initial array
        curDigitVal(int): the value converted from each digit index
        newArr(int[]): the finally updated new array
        idxNewArr(int): the iterative index of new array
        digitIndex(int): the corresponding digit index in initial array calculated from current iterated index i
    DESCRIPTION:
        STEP 1
        Initialize convertedNum to 0
        Initialize curDigitVal to 0
        Initialize newArr to initial array nums
        Initialize idxNewArr to newArr.length - 1
        Initialize digitIndex to 0
        STEP 2
        Iterate each number in initial array with index i
            STEP 3
            Get the corresponding digit index from current iterated index i
                digitIndex = newArr.length - i;
            STEP 4
            Get the value calculated from current iterated number and the digit index by calling function
                curDigitVal = getCurDigitVal(nums[i], digitIndex)
            STEP 5
            Add current digit index value to convertedNum
                convertedNum += curDigitVal
        
        Now curDigitVal is the digit number representing initial array
        
        STEP 6
        Plus convertedNum by one
            convertedNum += 1
        STEP 7
        Get the digit number of convertedNum by calling method
            int newDigitNum = getDigitNum(convertedNum)
        If digitNum != nums.length, (meaning there should be a new size of array)
            STEP 8
            Update newArr with the size of (nums.length + 1)
                newArr = new int[nums.length + 1]
            STEP 9
            Update idxNewArr
                idxNewArr = newArr.length - 1
        STEP 10
        Loop while convertedNum != 0, (meaning iterate from least significant digit to most significant digit)
            STEP 11
            Add (convertedNum % 10) to newArr[idxNewArr]
            STEP 12
            Decrease idxNewArr by one
                idxNewArr--
            STEP 13
            Update convertedNum by decreasing one digit
                convertedNum /= 10
        STEP 14
        Return newArr
    TIME:
        O(n + (n + 1)) ~ O(n)
    SPACE:
        O(n + 1), extra space needed if the digit number increase after adding one
    TO BE OPTIMIZED:
        Code should be constructed to be neat
*/

class PlusOneV1 {
    
    public static int[] plusOne(int[] nums) {
        // STEP 1
        int convertedNum = 0;
        int curDigitVal = 0;
        int[] newArr = nums;
        int idxNewArr = newArr.length - 1;
        int digitIndex = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            digitIndex = nums.length - i;
            // STEP 4
            curDigitVal = getCurDigitVal(nums[i], digitIndex);
            // STEP 5
            convertedNum += curDigitVal;
        }
        // STEP 6
        convertedNum += 1;
        // STEP 7
        int newDigitNum = getDigitNum(convertedNum);
        if (newDigitNum != nums.length) {
            // STEP 8
            newArr = new int[newDigitNum];
            // STEP 9
            idxNewArr = newArr.length - 1;
        }
        // STEP 10
        while (convertedNum != 0) {
            // STEP 11
            newArr[idxNewArr] = convertedNum % 10;
            // STEP 12
            idxNewArr--;
            // STEP 13
            convertedNum /= 10;
        }
        
        // STEP 14
        return newArr;
    }
    
    public static int getCurDigitVal(int digitNum, int digitIndex) {
        int tensVal = 1;
        while (digitIndex > 1) {
            tensVal *= 10;
            digitIndex--;
        }
        return digitNum * tensVal;
    }
    
    public static int getDigitNum(int number) {
        int digitNum = 0;
        while (number > 0) {
            digitNum++;
            number /= 10;
        }
        return digitNum;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        // int[] nums = {4, 3, 2, 1};
        // int[] nums = {9};
        int[] nums = {9, 9};
        System.out.println("Before: " + Arrays.toString(nums));
        int[] numsNew = plusOne(nums);
        System.out.println("After: " + Arrays.toString(numsNew));
    }
}