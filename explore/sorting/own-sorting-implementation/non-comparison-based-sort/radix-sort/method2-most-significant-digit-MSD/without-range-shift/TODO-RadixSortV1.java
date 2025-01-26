import java.util.Arrays;

/*
    NOTE:
        The implementation is wrong, MSD version is to be finished
    VARS:
        maxVal(int): the maximum number in nums
        maxValLen(int): the number of digits in maxVal
        idxDigit(int): the pointer to iterate each digit of maximum element in nums from right most to left most
        cacheArr(int[]): the cache array used for storing the occurances of each set of numbers in each digit
        sortedArr(int[]): an array for storing each sorted numbers after process at one digit finished
        curDigitVal(int): the value at idxDigit of each element in nums
    DESCRIPTION:
        STEP 1
        Initialize cacheArr to new int[10]
            int[] cacheArr = new int[10];
        Initialize sortedArr to new int[nums.length]
            int[] sortedArr = new int[nums.length];
        STEP 2
        Initialize maximum value of all elements in nums by calling method getMaxVal(nums) and assign it to maxVal
            int maxVal = getMaxVal(nums);
        STPE 3
        Get the total number of digits in maxVal by calling method getMaxValLen
            int maxValLen = getMaxValLen(maxVal);
        STEP 4
        Iterate each digit with idxDigit from (maxValLen - 1) to 0
            STEP 5
            Reset cacheArr and sortedArr to all zeros
                Arrays.fill(cacheArr, 0);
                Arrays.fill(sortedArr, 0);
            STEP 6
            Sort all digit value at idxDigit of all elements in nums by calling method
                countingSort(nums, idxDigit, cacheArr, sortedArr);
                
        -FUNC int getMaxVal(int[] nums)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal, (meaning a new maximum element is found)
                STEP 4
                Update maxVal to nums[idx]
                    maxVal = nums[idx];
        STEP 5
        Return maxVal as final result
            return maxVal;
            
        -FUNC int getMaxValLen(int maxVal)
        STEP 1
        Initialize len to 0 to record the total number of digits in maxVal
            int len = 0;
        STEP 2
        Loop while maxVal > 0,
            STEP 3
            Increase len by one
                len++;
            STEP 4
            Update maxVal with (maxVal / 10) for next iteration
                maxVal /= 10;
        STEP 5
        Return len;
        
        -FUNC void countingSort(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr)
        STEP 1
        Update cacheArr as countArr by calling method constructCountArr
            constructCountArr(nums, idxDigit, cacheArr);
        STEP 2
        Update cacheArr as startIndexArr by calling method constructStartIndexArr from the right side of cacheArr to its left side
            constructStartIndexArr(cacheArr);
        STEP 3
        Update sortedArr by calling method constructSortedArr
            constructSortedArr(nums, idxDigit, cacheArr, sortedArr);
        STEP 4
        Copy all elements in sortedArr to nums
            copy(sortedArr, nums);
            
        -FUNC void constructCountArr(int[] nums, int idxDigit, int[] cacheArr)
        STEP 1
        Initialize curDigitVal to -1 to store the digit value of each iterated digit for reusage purpose
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal with idxDigit of nums[idx]
                curDigitVal = (nums[idx] / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Increase cacheArr[curDigitVal] by one to indicate there is one more element exist in nums
                (cacheArr[curDigitVal])++;
        
        -FUNC constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize afterAllSum to 0 to store all elements after current index
            int afterAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of current index for later adding operation
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx from (cacheArr.length - 1) to 0
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element with value of idx exist in initial nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of current index to initialToBeAdded for later adding operation
                initialToBeAdded = cacheArr[idx];
            STEP 6
            Update cacheArr[idx] to afterAllSum to indicate the start index in a sorted array from left to right the value is from large to small
                cacheArr[idx] = afterAllSum;
            STEP 7
            Add initialToBeAdded to afterAllSum for next valid iteration
                afterAllSum += initialToBeAdded;
        
        -FUNC void constructSortedArr(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr)
        STEP 1
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to store the index to insert current element in sortedArr
            int idxToInsertInSortedArr = -1;
        Initialize curDigitVal to -1 for reusage purpose to get current digit value under idxDigit
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal with idxDigit and nums[idx]
                curDigitVal = (nums[idx] / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Get idxToInsertInSortedArr from cacheArr[curDigitVal]
                idxToInsertInSortedArr = cacheArr[curDigitVal];
            STEP 5
            Insert nums[idx] into index idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 6
            Increase cacheArr[curDigitVal] by one for next duplicated element
                (cacheArr[curDigitVal])++;
                
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx];

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
        for (int idxDigit = maxValLen - 1; idxDigit >= 0; idxDigit--) {
            if (idxDigit == -1) {
                break;
            }
            // STEP 5
            Arrays.fill(cacheArr, 0);
            Arrays.fill(sortedArr, 0);
            // STEP 6
            countingSort(nums, idxDigit, cacheArr, sortedArr);
            System.out.println("after process digit=" + idxDigit + ", nums: " + Arrays.toString(nums));
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
        // STEP 5
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
        int afterAllSum = 0;
        int initialToBeAdded = -1;
        // STEP 2
        for (int idx = cacheArr.length - 1; idx >= 0; idx--) {
            // STEP 3
            if (cacheArr[idx] == 0) {
                // STEP 4
                continue;
            }
            // STEP 5
            initialToBeAdded = cacheArr[idx];
            // STEP 6
            cacheArr[idx] = afterAllSum;
            // STEP 7
            afterAllSum += initialToBeAdded;
        }
    }
    
    public static void constructSortedArr(int[] nums, int idxDigit, int[] cacheArr, int[] sortedArr) {
        // STEP 1
        int idxToInsertInSortedArr = -1;
        int curDigitVal = -1;
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
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 2
            nums[idx] = sortedArr[idx];
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {421, 637, 120, 814, 556, 72};
        int[] nums = {421, 637, 120, 814, 53, 2, 556, 72};
        // int[] nums = {2, 1, 5, 4, 3};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}