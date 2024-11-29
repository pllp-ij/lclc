/**
    VARS:
        countOfEvenNum(int): the number of numbers with even number of digits
    Description:
        STEP 1
        Initialize countOfEvenNum to 0
        STEP 2
        Iterate each number in array
            STEP 3
            If current number has even number of digits by calling method,
                STEP 4
                Increase countOfEvenNum by one
        STEP 5
        Return countOfEvenNum
        
    TIME:
        O(n) * M, M is the average length of each number in array
    SPACE:
        O(1)
        
    TO BE OPTIMIZED:
        
 */


class FindNumbersWithEvenNumberOfDigitsV1 {
    
    public static int getNumber(int[] nums) {
        int countOfEvenNum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (getDigitsNumber(nums[i]) % 2 == 0) {
                countOfEvenNum++;
            }
        }
        return countOfEvenNum;
    }
    
    // Outer method to get number of digits
    public static int getDigitsNumber(int number) {
        int digitsNum = 0;
        while (number > 0) {
            digitsNum++;
            number /= 10;
        }
        return digitsNum;
    }
    
    public static void main(String[] args) {
        int[] nums = {12, 345, 2, 6, 7896, 1234, 561};
        int count = getNumber(nums);
        System.out.println("count: " + count);
    }
}