/**
    VARS:
        countOfEvenNum(int): the number of numbers with even number of digits
    Description:
        STEP 1
        Initialize countOfEvenNum to 0
        STEP 2
        Iterate each number in array
            STEP 3
            If current number is in range [10..99] or [1000..9999] by calling method
                STEP 4
                Increase countOfEvenNum by one
        STEP 5
        Return countOfEvenNum
        
    TIME:
        O(n)
    SPACE:
        O(1)
       
    TO BE OPTIMIZED:
        
 */


class FindNumbersWithEvenNumberOfDigitsV1 {
    
    public static int getNumber(int[] nums) {
        // STEP 1
        int countOfEvenNum = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            if (isInRanges(nums[i])) {
                // STEP 4
                countOfEvenNum++;
            }
        }
        // STEP 5
        return countOfEvenNum;
    }
    
    // Outer method to check if current number is in ranges where digits number is even
    public static boolean isInRanges(int number) {
        if (number >= 10 && number < 100 ||
            number >= 1000 && number < 10000 ||
            number == 100000) {
            return true;
        }
        return false;
    }
    
    
    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896, 1234, 561};
        int count = getNumber(nums);
        System.out.println("count: " + count);
    }
}