import java.util.Arrays;

/*
    NOTE:
        The reason why the range of idxDigit is set from 0 to (maxValLen - 1) rather than from 1 to maxValLen, is determined by the logic for convenience in countingSort function to get each digit value with idxDigit variable
    VARS:
        maxVal(int): the maximum number in nums
        maxValLen(int): the number of digits in maxVal
        idxDigit(int): the pointer to iterate each digit of maximum element in nums from right most to left most
        cacheArr(int[]): the cache array used for storing the occurances of each set of numbers in each digit
        sortedArr(int[]): an array for storing each sorted numbers after process at one digit finished
        curDigitVal(int): the value at idxDigit of each element in nums
    DESCRIPTION:
        STEP 1
        Initialize cacheArr to new int[10] to reserve the indexes from 0 to 9 for reusage purpose in each digit
            int[] cacheArr = new int[10];
        Initialize sortedArr to new int[nums.length] for reusage purpose in each digit
            int[] sortedArr = new int[nums.length]
        STEP 2
        Get the maximum number in nums by calling method getMaxVal and return it
            int maxVal = getMaxVal(nums);
        STEP 3
        Get number of digits in maxVal by calling method getMaxValLen and assign it to maxValLen
            int maxValLen = getMaxValLen(maxVal);
        STEP 4
        Iterate each digit of maxVal with idxDigit from right to left, value from 0 to (maxValLen - 1), 
            STEP 5
            Reset cacheArr and sortedArr for each digit
                Arrays.fill(cacheArr, 0);
                Arrays.fill(sortedArr, 0);
            STEP 6
            Sort each number in same digit index of each element of nums by calling countingSort method
                countingSort(nums, idxDigit, cacheArr, sortedArr);
        
        Now after all values at idxDigit are sorted by counting sort method, nums is completely sorted

        -FUNC int getMaxVal(int[] nums)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal, (a new maximum element is found)
                STEP 4
                Update maxVal to nums[idx]
                    maxVal = nums[idx];
                    
        -FUNC int getMaxValLen(int maxVal)
        STEP 1
        Initialize len to 0 to record the total number of digits in maxVal
            int len = 0;
        STEP 2
        Loop while maxVal > 0,
            STEP 3
            Increase len by one to include one more digit
                len++;
            STEP 4
            Update maxVal with maxVal / 10 to remove the least significant digit of maxVal
                maxVal /= 10;
        STEP 5
        Return len as final result
            return len;
            
        -FUNC void countingSort(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr)
        STEP 1
        Update cacheArr as countArr by calling method constructCountArr
            constructCountArr(nums, idxDigit, cacheArr);
        STEP 2
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            constructStartIndexArr(cacheArr)
        STEP 3
        Update sortedArr by calling method constructSortedArr
            constructSortedArr(nums, cacheArr, sortedArr)
        STEP 4
        Copy all elements in sortedArr back to nums by calling method
            copy(sortedArr, nums)
        
        -FUNC void constructCountArr(int[] nums, int idxDigit, int[] cacheArr)
        STEP 1
        Initialize curDigitVal to -1 for reusage purpose to get value of each element at idxDigit
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get current digit value with idxDigit of current iterated element of nums and assign it to curDigitVal
                curDigitVal = (nums[idx] / (int) pow(10, idxDigit)) % 10;
            STEP 4
            Increase cacheArr[curDigitVal] by one to indicate there is one more element exist
                (cacheArr[curDigitVal])++;
                
        -FUNC void constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 to store all elements before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of current index
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element equal to current idx initial nums array)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of current index first to initialToBeAdded
                initialToBeAdded = cacheArr[idx];
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate corresponding start index of current element
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next duplicated element
                beforeAllSum += initialToBeAdded;
        
        -FUNC void constructSortedArr(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr)
        STEP 1
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to denote the place to insert nums[idx]
            int idxToInsertInSortedArr = -1;
        Initialize curDigitVal to -1 for reusage purpose
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get current digit value with idxDigit of current iterated element of nums and assign it to curDigitVal
                curDigitVal = (nums[idx] / (int) pow(10, idxDigit)) % 10;
            STEP 4
            Get corresponding start index of curDigitVal from cacheArr with idx curDigitVal and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = cacheArr[curDigitVal];
            STEP 5
            Insert nums[idx] into sortedArr with index of idxToInsertInSortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 6
            Increase the value at cacheArr[curDigitVal] by one for next duplicate element
                (cacheArr[curDigitVal])++;
            
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
        
    TIME:
        O(W * (n + k)), W is the length of maximum element in nums, n is the size of nums, k is the maximum value of all elements in same digit
    SPACE:
        O(n + k), n is for sortedArr, k is for cacheArr
*/

class RadixSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int[] cacheArr = new int[10];
        int[] sortedArr = new int[nums.length];
        // STEP 2
        int maxVal = getMaxVal(nums);
        // STEP 3
        int maxValLen = getMaxValLen(maxVal);
        // STEP 4
        for (int idxDigit = 0; idxDigit < maxValLen; idxDigit++) {
            // STEP 5
            Arrays.fill(cacheArr, 0);
            Arrays.fill(sortedArr, 0);
            // STEP 6
            countingSort(nums, idxDigit, cacheArr, sortedArr);
        }
    }
    
    public static int getMaxVal(int[] nums) {
        // STEP 1
        int maxVal = Integer.MIN_VALUE;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            if (nums[idx] > maxVal) {
                // STEP 4
                maxVal = nums[idx];
            }
        }
        return maxVal;
    }
    
    public static int getMaxValLen(int maxVal) {
        // STEP 1
        int len = 0;
        // STEP 2
        while (maxVal > 0) {
            // STEP 3
            len++;
            // STEP 4
            maxVal /= 10;
        }
        // STEP 5
        return len;
    }
    
    public static void countingSort(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr) {
        // STEP 1
        constructCountArr(nums, idxDigit, cacheArr);
        // STEP 2
        constructStartIndexArr(cacheArr);
        // STEP 3
        constructSortedArr(nums, idxDigit, cacheArr, sortedArr);
        // STEP 4
        copy(sortedArr, nums);
    }
    
    public static void constructCountArr(int[] nums, int idxDigit, int[] cacheArr) {
        // STEP 1
        int curDigitVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = (nums[idx] / (int) Math.pow(10, idxDigit)) % 10;
            // STEP 4
            (cacheArr[curDigitVal])++;
        }
    }
    
    public static void constructStartIndexArr(int[] cacheArr) {
        // STEP 1
        int beforeAllSum = 0;
        int initialToBeAdded = -1;
        // STEP 2
        for (int idx = 0; idx < cacheArr.length; idx++) {
            // STEP 3
            if (cacheArr[idx] == 0) {
                // STEP 4
                continue;
            }
            // STEP 5
            initialToBeAdded = cacheArr[idx];
            // STEP 6
            cacheArr[idx] = beforeAllSum;
            // STEP 7
            beforeAllSum += initialToBeAdded;
        }
    }
    
    public static void constructSortedArr(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr) {
        // STEP 1
        int curDigitVal = -1;
        int idxToInsertInSortedArr = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = (nums[idx] / (int) Math.pow(10, idxDigit)) % 10;
            // STEP 4
            idxToInsertInSortedArr = cacheArr[curDigitVal];
            // STEP 5
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 6
            (cacheArr[curDigitVal])++;
        }
    }
    
    public static void copy(int[] sortedArr, int[] nums) {
        // STEP 1
        for (int idx = 0; idx < sortedArr.length; idx++) {
            // STEP 2
            nums[idx] = sortedArr[idx];
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {421, 637, 120, 814, 556, 72};
        int[] nums = {421, 637, 120, 814, 53, 2, 556, 72};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}