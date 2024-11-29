import java.util.Arrays;
import java.math.BigInteger;

/**
    NOTE:
        Directly use int or long to save great large number will overflow, here use the BigInteger type
    VARS:
        newArr(int[]): a new array when resulting length increased after adding one, also for final return result
    DESCRIPTION:
        STEP 1
        Initialize newArr to nums, (first assume the length won't change)
        STEP 2
        Get converted number from initial array by calling method
            int convertedNum = getConvertedNum(nums)
        STEP 3
        Increase convertedNum by one
            convertedNum += 1
        STEP 4
        Get increased convertedNum digit number by calling method
            int newDigitNum = getDigitNum(convertedNum)
        If newDigitNum != nums.length, (meaning the total length has been changed after adding one)
            STEP 5
            Update newArr with a new array with new length
                newArr = new int[newDigitNum]
        STEP 6
        Copy each digit number in convertedNum back to newArr by calling method
            copyDigitToArray(convertedNum, newArr);
        STEP 7
        Return newArr
    TIME:
        O(n + n + 1) ~ O(n), first pass to get integer number, second pass to write back increased integer number to array
    SPACE:
        O(n), if the resulting length increased from the initial array
        
*/

class PlusOneV2 {
    
    public static int[] plusOne(int[] nums) {
        // STEP 1
        int[] newArr = nums;
        // STEP 2
        BigInteger convertedNum = getConvertedNum(nums);
        // STEP 3
        convertedNum = convertedNum.add(BigInteger.valueOf(1));
        // STEP 4
        int newDigitNum = getDigitNum(convertedNum);
        if (newDigitNum != nums.length) {
            // STEP 5
            newArr = new int[newDigitNum];
        }
        // STEP 6
        copyDigitToArray(convertedNum, newArr);
        // STEP 7
        return newArr;
    }
    
    public static BigInteger getConvertedNum(int[] nums) {
        BigInteger convertedNum = BigInteger.valueOf(0);
        for (int i = 0; i < nums.length; i++) {
            BigInteger curDigitVal = getCurDigitVal(nums[i], nums.length - i);
            convertedNum = convertedNum.add(curDigitVal);
        }
        return convertedNum;
    }
    
    public static BigInteger getCurDigitVal(int digitVal, int digitIdx) {
        BigInteger tensVal = BigInteger.valueOf(1);
        while (digitIdx > 1) {
            tensVal = tensVal.multiply(BigInteger.valueOf(10));
            digitIdx--;
        }
        return tensVal.multiply(BigInteger.valueOf(digitVal));
    }
    
    public static int getDigitNum(BigInteger number) {
        int digitNum = 0;
        while (number.compareTo(BigInteger.valueOf(0)) > 0) {
            digitNum++;
            number = number.divide(BigInteger.valueOf(10));
        }
        return digitNum;
    }
    
    public static void copyDigitToArray(BigInteger convertedNum, int[] nums) {
        for (int i = nums.length - 1; i >= 0; i--) {
            nums[i] = (convertedNum.remainder(BigInteger.valueOf(10))).intValue();
            convertedNum = convertedNum.divide(BigInteger.valueOf(10));
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3};
        // int[] nums = {4, 3, 2, 1};
        // int[] nums = {9};
        // int[] nums = {9, 9};
        // int[] nums = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] nums = {7,2,8,5,0,9,1,2,9,5,3,6,6,7,3,2,8,4,3,7,9,5,7,7,4,7,4,9,4,7,0,1,1,1,7,4,0,0,6};
        System.out.println("Before: " + Arrays.toString(nums));
        int[] numsNew = plusOne(nums);
        System.out.println("After: " + Arrays.toString(numsNew));
    }
}