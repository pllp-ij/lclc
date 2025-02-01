import java.util.Arrays;

/*
    VARS:
        maxVal(int): the maximum value in nums
        maxValLen(int): the length of maximum value in nums
        cacheArr(int[]): a cache array used as countArr to store the occurance of elements in nums, then used as startIndexArr to store the start index in sorted array
        sortedArr(int[]): an array to store all elements after sorted
        maxGap(int): the var used to store maximum gap when iterating each element of sorted nums, also the final returned result
    DESCRIPTION:
        STEP 1
        If nums.length < 2, (meaning there is not at least two elements)
            STEP 2
            Directly return 0
        STEP 3
        Initialize cacheArr to new int[10] for reusage purpose
            int[] cacheArr = new int[10]
        Initialize sortedArr to new int[nums.length] for reusage purpose
            int[] sortedArr = new int[nums.length]
        STEP 4
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE}
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VAULE}
        STEP 5
        Update maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal);
        STEP 6
        Get shiftedMaxVal by (maxVal[0] - minVal[0] + 1)
            int shiftedMaxVal = maxVal[0] - minVal[0] + 1;
        STEP 7
        Get shiftedMaxValLen by calling method getShiftedMaxValLen
            int ShiftedMaxValLen = getShiftedMaxValLen(shiftedMaxVal);
        STEP 8
        Iterate each digit with idxDigit from 0 to (ShiftedMaxValLen - 1) to do counting sort
            STEP 9
            Reset cacheArr and sortedArr back to 0 for next digit, the reset for sortedArr is optional
                Arrays.fill(cacheArr, 0);
                Array.fill(sortedArr, 0);
            STEP 10
            Do counting sort under current digit by calling method countingSort
                countingSort(nums, idxDigit, minVal[0], cacheArr, sortedArr);
        
        Now nums has been sorted
        
        STEP 11
        Initialize maxGap to Integer.MIN_VALUE
            int maxGap = Integer.MIN_VALUE;
        Initialize curGap to -1 for reusage purpose
            int curGap = -1;
        STEP 12
        Iterate each index of sorted nums with idx from 0 to (nums.length - 2)
            STEP 13
            Get current gap by substracting nums[idx] from nums[idx + 1] and assign it to curGap
                curGap = nums[idx + 1] - nums[idx];
            STEP 14
            If curGap > maxGap, (meaning a new maximum gap is found)
                STEP 15
                Update maxGap to curGap
                    maxGap = curGap
        STEP 16
        Return maxGap as final result
        
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum element is found)
                STEP 3
                Update maxVal[0] with nums[idx]
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum element is found)
                STEP 5
                Update minVal[0] with nums[idx]
        
        -FUNC int getShiftedMaxValLen(int shiftedMaxVal)
        STEP 1
        Initialize len to 0 to store the total number of digits
            int len = 0;
        STEP 2
        Loop while shiftedMaxVal > 0, (meaning there is still digit)
            STEP 3
            Increase len by one to indicate there is one more digit exist
                len++;
            STEP 4
            Update shiftedMaxVal by shiftedMaxVal / 10 for next iteration
                shiftedMaxVal /= 10
        STEP 5
        Return len as final result
        
        -FUNC void countingSort(int[] nums, int idxDigit, int minVal, int[] cacheArr, int[] sortedArr)
        STEP 1
        Update cacheArr as countArr by calling method constructCountArr
            constructCountArr(nums, idxDigit, minVal, cacheArr);
        STEP 2
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            constructStartIndexArr(cacheArr);
        STEP 3
        Update sortedArr by calling method constructSortedArr
            constructSortedArr(nums, idxDigit, minVal, cacheArr, sortedArr);
        STEP 4
        Copy all elements in sortedArr to nums
            copy(sortedArr, nums)
            
        -FUNC void constructCountArr(int[] nums, int idxDigit, int minVal, int[] cacheArr)
        STEP 1
        Initialize curDigitVal to -1 for reusage purpose
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal by nums and idxDigit
                curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Increase cacheArr[curDigitVal] by one to indicate there is one more element exist in nums
                (cacheArr[curDigitVal])++;
        
        -FUNC void constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 for reusage purpose to store all elements before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store the start index in sorted array
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element equal to idx in nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of index in cacheArr for later adding operation
                initialToBeAdded = cacheArr[idx]
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the start index of current index
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
        
        -FUNC void constructSortedArr(int[] nums, int idxDigit, int minVal, int[] cacheArr, int[] sortedArr)
        STEP 1
        Initialize idxToInsertInSortedArr to -1 for reusage purpose
            int idxToInsertInSortedArr = -1;
        Initialize curDigitVal to -1 for reusage purpose
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal by nums and idxDigit and minVal
                curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Get idxToInsertInSortedArr with cacheArr[curDigitVal]
                idxToInsertInSortedArr = cacheArr[curDigitVal];
            STEP 5
            Insert nums[idx] into index of idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 6
            Increase cacheArr[curDigitVal] by one for next duplicate element
                (cacheArr[curDigitVal])++;
                
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx]
        
    TIME:
        O(w * (n + 10)), w is shiftedMaxValLen, n is the size of nums, 10 is the size of cacheArr
    SPACE:
        O(n + 10), n is the size of sortedArr, 10 is the size of cacheArr
*/

class MaximumGapV1 {
    
    public static int getMaxGap(int[] nums) {
        // STEP 1
        if (nums.length < 2) {
            // STEP 2
            return 0;
        }
        // STEP 3
        int[] cacheArr = new int[10];
        int[] sortedArr = new int[nums.length];
        // STEP 4
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 5
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 6
        int shiftedMaxVal = (maxVal[0] - minVal[0]) + 1;
        // STEP 7
        int shiftedMaxValLen = getShiftedMaxValLen(shiftedMaxVal);
        // STEP 8
        for (int idxDigit = 0; idxDigit < shiftedMaxValLen; idxDigit++) {
            // STEP 9
            Arrays.fill(cacheArr, 0);
            Arrays.fill(sortedArr, 0);
            // STEP 10
            countingSort(nums, idxDigit, minVal[0], cacheArr, sortedArr);
        }
        // STEP 11
        int maxGap = Integer.MIN_VALUE;
        int curGap = -1;
        // STEP 12
        for (int idx = 0; idx < nums.length - 1; idx++) {
            // STEP 13
            curGap = nums[idx + 1] - nums[idx];
            // STEP 14
            if (curGap > maxGap) {
                // STEP 15
                maxGap = curGap;
            }
        }
        // STEP 16
        return maxGap;
    }
    
    public static void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal) {
        // STEP 1
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 2
            if (nums[idx] > maxVal[0]) {
                // STEP 3
                maxVal[0] = nums[idx];
            }
            // STEP 4
            if (nums[idx] < minVal[0]) {
                // STEP 5
                minVal[0] = nums[idx];
            }
        }
    }
    
    public static int getShiftedMaxValLen(int shiftedMaxVal) {
        // STEP 1
        int len = 0;
        // STEP 2
        while (shiftedMaxVal > 0) {
            // STEP 3
            len++;
            // STEP 4
            shiftedMaxVal /= 10;
        }
        // STEP 5
        return len;
    }
    
    public static void countingSort(int[] nums, int idxDigit, int minVal, int[] cacheArr, int[] sortedArr) {
        // STEP 1
        constructCountArr(nums, idxDigit, minVal, cacheArr);
        // STEP 2
        constructStartIndexArr(cacheArr);
        // STEP 3
        constructSortedArr(nums, idxDigit, minVal, cacheArr, sortedArr);
        // STEP 4
        copy(sortedArr, nums);
    }
    
    public static void constructCountArr(int[] nums, int idxDigit, int minVal, int[] cacheArr) {
        // STEP 1
        int curDigitVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
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
    
    public static void constructSortedArr(int[] nums, int idxDigit, int minVal, int[] cacheArr, int[] sortedArr) {
        // STEP 1
        int idxToInsertInSortedArr = -1;
        int curDigitVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
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
        // int[] nums = {3, 6, 9, 1};
        // int[] nums = {10};
        int[] nums = {2, 99999999};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int result = getMaxGap(nums);
        System.out.println("max gap is: " + result);
    }
}