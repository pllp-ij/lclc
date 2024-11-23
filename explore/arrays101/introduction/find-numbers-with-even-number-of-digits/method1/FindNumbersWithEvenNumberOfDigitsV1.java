/**
    VARS:
        countOfEvenNum(int): the number of numbers with even number of digits
    Description:
        STEP 1
        Initialize the countOfEvenNum to 0
        STEP 2
        Iterate each number in the array
            STEP 3
            Convert current number to string
            STEP 4
            If length of current number string is even
                STEP 5
                Increase countOfEvenNum by one
        STEP 6
        Return countOfEvenNum
        
    TO BE OPTIMIZED:
        
 */


class FindNumbersWithEvenNumberOfDigitsV1 {
    public static int getNumber(int[] nums) {
        // STEP 1
        int countOfEvenNum = 0;
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            String curNumberStr = String.valueOf(nums[i]);
            // STEP 4
            if (curNumberStr.length() % 2 == 0) {
                // STEP 5
                countOfEvenNum++;
            }
        }
        return countOfEvenNum;
    }
    
    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896, 1234, 561};
        int count = getNumber(nums);
        System.out.println("count: " + count);
    }
}