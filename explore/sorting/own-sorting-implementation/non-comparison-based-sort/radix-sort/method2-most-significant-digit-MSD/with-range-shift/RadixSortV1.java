import java.util.Arrays;

/*
    NOTE:
        The reason why the range of idxDigit is set from 0 to (maxValLen - 1) rather than from 1 to maxValLen, is determined by the logic for convenience in countingSort function to get each digit value with idxDigit variable
    VARS:
        maxVal(int): the maximum value in nums
        minVal(int): the minimum value in nums
        shiftedMaxVal(int): the shifted maxVal value
        shiftedMaxValLen(int): the number of digit of new maxVal shifted from maxVal
        idxDigit(int): the pointer used to iterate each digit of nums from right most to left most, value from 0 to ()
        cacheArr(int[]): cache array used at each digit in shifted range, first as countArr to store occurance of each digit value, then as startIndexArr to store the index of elements of nums in sortedArr
        sortedArr(int[]): for storing all elements of nums after sorted for each digit
    DESCRIPTION:
        STEP 1
        Initialize cacheArr to new int[10] for reusage purpose
            int[] cacheArr = new int[10]
        Initialize sortedArr to new int[nums.length]
            int[] sortedArr = new int[nums.length]
        STEP 2
        Initialize maxVal to new int[] {Integer.MIN_VALUE};
            int[] maxVal = new int[] {Integer.MIN_VALUE};
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VALUE}
        STEP 3
        Update maxVal and minVal by calling method getMaxMinVal
            getMaxMinVal(nums, maxVal, minVal);
        STEP 4
        Initialize shiftedMaxVal to (maxVal - minVal) + 1
            int shiftedMaxVal = (maxVal[0] - minVal[0]) + 1;
        STEP 5
        Calculate shiftedMaxValLen by calling method getShiftedMaxValLen and assign it to shiftedMaxValLen
            int shiftedMaxValLen = getShiftedMaxValLen(shiftedMaxVal);
        STEP 6
        Iterate idxDigit from 0 to (shiftedMaxValLen - 1) to process each digit value
            STEP 7
            Reset cacheArr and sortedArr for each iterated digit
                Arrays.fill(cacheArr, 0);
                Arrays.fill(sortedArr, 0);
            STEP 8
            Sort nums at current digit by calling method countingSort
                countingSort(nums, idxDigit, minVal, cacheArr, sortedArr);
        
        Now all digit of nums are sorted, so initial nums is completed sorted
        
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum element is found)
                STEP 3
                Update maxVal[0] to nums[idx]
                    maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum element is found)
                STEP 5
                Update minVal[0] to nums[idx]
                    minVal[0] = nums[idx]
        
        -FUNC int getShiftedMaxValLen(int shiftedMaxVal)
        STEP 1
        Initialize len to 0 to store the total number of digit in shiftedMaxVal
            int len = 0;
        STEP 2
        Loop while shiftedMaxVal > 0,
            STEP 3
            Increase len by one
                len++;
            STEP 4
            Update shiftedMaxVal to (shiftedMaxVal / 10) for next iteration
                shiftedMaxVal /= 10
        STEP 5
        Return len as final result
            return len;
            
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
        Copy each element in sortedArr to nums
            copy(sortedArr, nums);
        
        -FUNC void constructCountArr(int[] nums, int idxDigit, int minVal, int[] cacheArr)
        STEP 1
        Initialize curDigitVal to -1 to store each value of current iterated index
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STPE 3
            Get curDigitVal under current idxDigit of shifted element of nums, which is shifted from nums[idx] to (nums[idx] - minVal)
                curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Increase cacheArr[curDigitVal] by one to indicate one more element exist
                (cacheArr[curDigitVal])++;
                
        -FUNC void constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 for reusage purpose to store all elements before current idx
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value at current index in cacheArr
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element whose value equal to idx exist in initial nums array)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of current idx of cacheArr and assign it to initialToBeAdded
                initialToBeAdded = cacheArr[idx];
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the start index of current element
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
                
        -FUNC void constructSortedArr(int[] nums, int idxDigit, int minVal, int[] cacheArr, int[] sortedArr)
        STEP 1
        Initialize curDigitVal to -1 for reusage purpose
            int curDigitVal = -1;
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to insert corresponding nums[idx] to idxToInsertInSortedArr
            int idxToInsertInSortedArr = -1
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal under idxDigit of shifted element value which is (nums[idx] - minVal) and assign it to curDigitVal
                curDigitVal = ((nums[idx] - minVal) / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Get idxToInsertInSortedArr from cacheArr at curDigitVal
                idxToInsertInSortedArr = cacheArr[curDigitVal];
            STEP 5
            Insert nums[idx] into sortedArr at index idxToInsertInSortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 6
            Increase cacheArr[curDigitVal] by one for next duplicated elements
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
        int[] maxVal = new int[]{Integer.MIN_VALUE};
        int[] minVal = new int[]{Integer.MAX_VALUE};
        // STEP 3
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 4
        int shiftedMaxVal = (maxVal[0] - minVal[0]) + 1;
        // STEP 5
        int shiftedMaxValLen = getShiftedMaxValLen(shiftedMaxVal);
        // STEP 6
        for (int idxDigit = 0; idxDigit < shiftedMaxValLen; idxDigit++) {
            // STEP 7
            Arrays.fill(cacheArr, 0);
            Arrays.fill(sortedArr, 0);
            // STEP 8
            countingSort(nums, idxDigit, minVal[0], cacheArr, sortedArr);
        }
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
        int curDigitVal = -1;
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
        // int[] nums = {421, 637, 120, 814, 556, 72};
        int[] nums = {421, -210, 637, 120, 814, 53, 2, 556, -2, -54};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}