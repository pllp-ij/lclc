import java.util.Arrays;

/*
    VARS:
        maxVal(int): the max value of all elements in nums
        minVal(int): the min value of all elements in nums
        cacheArr(int[]): a cache array used as countArr to store occurance of each element in nums, then used as startIndexArr in store the start index of each element in nums in sorted array
        sortedArr(int[]): for storing all sorted elements of nums
    DESCRIPTION:
        STEP 1
        Initialize maxVal to new int[] {Integet.MIN_VALUE}
            int[] maxVal = new int[Integer.MIN_VALUE]
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[Integer.MAX_VALUE]
        STEP 2
        Get maxVal and minVal by calling method getMaxMinVal
            getMaxMinVal(nums, maxVal, minVal)
        STEP 3
        Initialize cacheArr as countArr by calling method constructCountArr with range shift and return final result
            int[] cacheArr = constructCountArr(nums, maxVal[0], minVal[0])
        STEP 4
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            cacheArr = constructStartIndexArr(cacheArr)
        STEP 5
        Initialize sortedArr by calling method constructSortedArr with range shift
            int[] sortedArr = constructSortedArr(nums, cacheArr, minVal);
        STEP 6
        Copy each elements in sortedArr to nums
            copy(sortedArr, nums);
        
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (a new maximum element is found)
                STEP 3
                Update maxVal[0] with nums[idx]
                    maxVal[0] = nums[idx]
            STEP 4
            If nums[idx] < minVal[0], (a new minimum element is found)
                STEP 5
                Update minVal[0] with nums[idx]
                    minVal[0] = nums[idx]
        
        -FUNC int[] constructCountArr(int[] nums, int maxVal, int minVal)
        STEP 1
        Initialize cacheArr to new int[(maxVal - minVal) + 1]
            int[] cacheArr = new int[(maxVal - minVal) + 1]
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase cacheArr[nums[idx]] by one to indicate there is one more element exist in nums with value of nums[idx]
                (cacheArr[nums[idx]])++;
        STEP 4
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 to store all elements before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of cacheArr for later adding operation
            int initialToBeAdded = -1
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element whose value equal to idx in initial nums)
                STEP 4
                Skip current iterations
                    continue;
            STEP 5
            Store initial value of cacheArr for later adding operation and assign it to initialToBeAdded
                initialToBeAdded = cacheArr[idx]
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the corresponding start index of current element in sorted array
                cacheArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
        STEP 8
        Return cacheArr as final result
            return cacheArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] cacheArr, int minVal)
        STEP 1
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to indicate the place to insert nums[idx] in sorted array
            int idxToInsertInSortedArr = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding start index of current index from cacheArr with index (nums[idx] - minVal) and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = cacheArr[nums[idx] - minVal];
            STEP 4
            Insert nums[idx] into index of idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 5
            Increase the value at (nums[idx] - minVal) in cacheArr by one for next duplicate element
                (cacheArr[nums[idx] - minVal])++;
        STEP 6
        Return sortedArr as final result
            return sortedArr
            
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
        int[] colors = {2, 0, 2, 1, 1, 0};
        
        System.out.println("initial nums: " + Arrays.toString(colors));
        sort(colors);
        System.out.println("after sort: " + Arrays.toString(colors));
    }
}