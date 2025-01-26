import java.util.Arrays;

/*
    VARS:
        maxVal(int): the maxVal of all elements in nums
        minVal(int): the min val of all elements in nums
        cacheArr(int[]): an array used to count the occurance of elements in nums then used as startIndexArr
        sortedArr(int[]): array for storing sorted array with same size of nums
    DESCRIPTION:
        STEP 1
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
        Initialize minVal to new int[] {Integer.MAX_VALUE]
        STEP 2
        Get maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal)
        STEP 3
        Initialize cacheArr as countArr by calling method constructCountArr(nums, maxVal[0], minVal[0])
            int[] cacheArr = constructCountArr(nums, maxVal, minVal);
        STEP 4
        Update cacheArr as startIndexArr by calling method constructStartIndexArr(cacheArr);
            cacheArr = constructStartIndexArr(cacheArr);
        STEP 5
        Initialize sortedArr by calling method constructSortedArr(nums, startIndexArr, minVal[0])
            int[] sortedArr = constructSortedArr(nums, startIndexArr, minVal[0])
        STEP 6
        Copy each elements in sortedArr back to nums
            copy(sortedArr, nums)
            
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new max element is found)
                STEP 3
                Update maxVal[0] with nums[idx]
                    maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] > minVal[0], (meaning a new min element is found)
                STEP 5
                Update minVal[0] with nums[idx]
                    minVal[0] = nums[idx];
            
        -FUNC int[] constructCountArr(int[] nums, int maxVal, int minVal)
        STEP 1
        Initialize countArr to new int[(maxVal - minVal) + 1];
            int[] countArr = new int[(maxVal - minVal) + 1]
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase countArr[nums[idx] - minVal] by one to indicate there is one more element whose value is equal to nums[idx]
                (countArr[nums[idx] - minVal])++;
        STEP 4
        Return countArr as final result
            return countArr;
            
        -FUNC int[] constructStartIndexArr(cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 for storing all value of indexes before current index
        Initialize initialToBeAdded to store initial value of current index in cacheArr
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning current element's value is not exist in initial nums array)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of current index for later adding operation
                initialToBeAdded = cacheArr[idx]
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the start index of current index
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next iteration
                beforeAllSum += initialToBeAdded;
        STEP 8
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] cacheArr, int minVal)
        STEP 1
        Initialize sortedArr to new int[nums.length]
            int[] sortedArr = new int[nums.length]
        Initialize idxToInsertInSortedArr to -1 for reusage purpose
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding start index in sorted array from cacheArr with index of (nums[idx] - minVal) and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = startIndexArr[nums[idx] - minVal];
            STEP 4
            Insert nums[idx] into the index of idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
        STEP 5
        Return sortedArr as final result
            return sortedArr
            
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx]
        
    TIME:
        O(n + k), n is the size of nums, k is the maximum value of all elements in nums, which is also the number of index of counts array
    SPACE:
        O(n + k), n is for the size of final sorted array, k is for the occurance counting array
*/

class CountingSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 2
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 3
        int[] cacheArr = constructCountArr(nums, maxVal[0], minVal[0]);
        // STEP 4
        cacheArr = constructStartIndexArr(cacheArr);
        // STEP 5
        int[] sortedArr = constructSortedArr(nums, cacheArr, minVal[0]);
        // STEP 6
        copy(sortedArr, nums);
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
    
    public static int[] constructCountArr(int[] nums, int maxVal, int minVal) {
        // STEP 1
        int[] cacheArr = new int[(maxVal - minVal) + 1];
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            (cacheArr[nums[idx] - minVal])++;
        }
        // STEP 4
        return cacheArr;
    }
    
    public static int[] constructStartIndexArr(int[] cacheArr) {
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
        // STEP 8
        return cacheArr;
    }
    
    public static int[] constructSortedArr(int[] nums, int[] cacheArr, int minVal) {
        // STEP 1
        int[] sortedArr = new int[nums.length];
        int idxToInsertInSortedArr = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            idxToInsertInSortedArr = cacheArr[nums[idx] - minVal];
            // STEP 4
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 5
            (cacheArr[nums[idx] - minVal])++;
        }
        // STEP 6
        return sortedArr;
    }
    
    public static void copy(int[] sortedArr, int[] nums) {
        // STEP 1
        for (int idx = 0; idx < sortedArr.length; idx++) {
            // STEP 2
            nums[idx] = sortedArr[idx];
        }
    }

    public static void main(String[] args) {
        // int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8, 5, 5};
        
        int[] nums = {5, 2, -3, 7, -3, -5, 1, 10, 6, 8, 5, 5};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}