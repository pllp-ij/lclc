import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 only use one cache array for both countArr and startIndexArr roles
    VARS:
        maxVal(int): the maxVal of all elements in nums
        cacheArr(int[]): an array used to count the occurance of elements in nums and then used to store the start index of each element in sorted array
        sortedArr(int[]): array for storing sorted array with same size of nums
    DESCRIPTION:
        STEP 1
        Initialize maxVal by calling method getMaxVal
            int maxVal = getMaxVal(nums)
        STEP 2
        Initialize cacheArr as countArr by calling method constructCountArr
            int[] cacheArr = constructCountArr(nums, maxVal)
        STEP 3
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            cacheArr = constructStartIndexArr(cacheArr);
        STEP 4
        Initialize sortedArr by calling method constructSortedArr
            int[] sortedArr = constructSortedArr(nums, cacheArr)
        STEP 5
        Copy all elements from sortedArr to nums
            copy(sortedArr, nums)
            
        -FUNC int getMaxVal(int[] nums)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal,
                STEP 4
                Update maxVal to nums[idx]
                    maxVal = nums[idx];
        STEP 5
        Return maxVal as final result
            return maxVal;
            
        -FUNC int[] constructCountArr(int[] nums, int maxVal)
        STEP 1
        Initialize cacheArr to new int[maxVal + 1]
            int[] cacheArr = new int[maxVal + 1]
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase cacheArr[nums[idx]] by one to indicate that there is one more element exist
                (cacheArr[nums[idx]])++;
        STEP 4
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructStartIndexArr(int cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 to store all indexes before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element whose value equal to idx in initial array)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Cache initial value of cacheArr[idx] for later adding operation
                initialToBeAdded = cacheArr[idx];
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the start index of current index in sorted array
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
        STEP 8
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] cacheArr)
        STEP 1
        Initialize sortedArr with same size as nums
            int[] sortedArr = new int[nums.length];
        Initialize idxToInsertInSortedArr to -1 for reusage purpose
            int idxToInsertInSortedArr = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding index of current element in sorted array from cacheArr with index nums[idx] and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = cacheArr[nums[idx]];
            STEP 4
            Insert element with value of nums[idx] into sorted array at index idxToInsertInSortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 5
            Update cacheArr[nums[idx]] by one for next duplicated element
                (cacheArr[nums[idx]])++;
        STEP 6
        Return sortedArr as final result
            return sorte;
            
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx];
        
    TIME:
        O(n + k), n is the size of nums, k is the maximum value of all elements in nums, which is also the number of index of counts array
    SPACE:
        O(n + k), n is for the size of final sorted array, k is for the occurance counting array
*/

class CountingSortV2 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int maxVal = getMaxVal(nums);
        // STEP 2
        int[] cacheArr = constructCountArr(nums, maxVal);
        // STEP 3
        cacheArr = constructStartIndexArr(cacheArr);
        // STEP 4
        int[] sortedArr = constructSortedArr(nums, cacheArr);
        // STEP 5
        copy(sortedArr, nums);
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
    
    public static int[] constructCountArr(int[] nums, int maxVal) {
        // STEP 1
        int[] cacheArr = new int[maxVal + 1];
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            (cacheArr[nums[idx]])++;
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
    
    public static int[] constructSortedArr(int[] nums, int[] cacheArr) {
        // STEP 1
        int[] sortedArr = new int[nums.length];
        int idxToInsertInSortedArr = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            idxToInsertInSortedArr = cacheArr[nums[idx]];
            // STEP 4
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 5
            (cacheArr[nums[idx]])++;
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
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8, 5, 5};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}