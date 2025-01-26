import java.util.Arrays;

/*
    VARS:
        maxVal(int): the maximum element in nums
        cacheArr(int[]): the cache array used to store the occurance of each element in nums and then used to store the start index of each element of nums in sorted array
        sortedArr(int[]): to store all sorted elements in nums
    DESCRIPTION:
        STEP 1
        Initialize maxVal by calling method getMaxVal and return it
            int maxVal = getMaxVal(nums)
        STEP 2
        Initialize cacheArr as countArr by calling method constructCountArr
            int[] cacheArr = constructCountArr(nums, maxVal);
        STEP 3
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            cacheArr = constructStartIndexArr(cacheArr);
        STEP 4
        Initialize sortedArr by calling method constructSortedArr
            int[] sortedArr = constructSortedArr(nums, cacheArr)
        STEP 5
        Copy each elements in sortedArr to nums by calling method
            copy(sortedArr, nums)
            
        -FUNC int getMaxVal(int[] nums)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal,
                STEP 4
                Update maxVal with nums[idx]
                    maxVal = nums[idx]
        STEP 5
        Return maxVal as final result
            return maxVal
            
        -FUNC int[] constructCountArr(int[] nums, int maxVal)
        STEP 1
        Initialize cacheArr to new int[maxVal + 1]
            int[] cacheArr = new int[maxVal + 1]
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase cacheArr[nums[idx]] by one to indicate there is one more element exist
                (cacheArr[nums[idx]])++;
        STEP 4
        Return cacheArr as final result
            return cacheArr
            
        -FUNC int[] constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 to store all the sum of value before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial cacheArr value for later adding operation
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element in nums with value of idx)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of cacheArr[idx] first for later adding operation and assign it to initialToBeAdded
                initialToBeAdded = cacheArr[idx];
            STEP 6
            Update cacheArr[idx] with the beforeAllSum to denote corresponding start index of current element in nums
                cacheArr[idx] = beforeAllSum
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded
        STEP 8
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] cacheArr)
        STEP 1
        Initialize sortedArr to new int[nums.length]
            int[] sortedArr = new int[nums.length]
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to indicate the place to insert in sorted array
            int idxToInsertInSortedArr = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding start index of current element from cacheArr with idx nums[idx] and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = cacheArr[nums[idx]];
            STEP 4
            Insert nums[idx] into the index of idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx]
            STEP 5
            Increase the value at cacheArr[nums[idx]] by one for next duplicated element
                (cacheArr[nums[idx]])++;
        STEP 6
        Return sortedArr as final result
            return sortedArr;
            
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx]

    TIME:
        O(n + k), n is the size of initial nums, k is the length of (maxVal - minVal) + 1, where maxVal and minVal are maximum and minimum value of all elements in nums
    SPACE:
        O(1), no extra space is used
*/

class SortColorsV1 {
    
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
            // STPE 3
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
        int[] colors = {2, 0, 2, 1, 1, 0};
        
        System.out.println("initial nums: " + Arrays.toString(colors));
        sort(colors);
        System.out.println("after sort: " + Arrays.toString(colors));
    }
}