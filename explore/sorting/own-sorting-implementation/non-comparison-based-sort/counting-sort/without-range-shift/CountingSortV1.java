import java.util.Arrays;

/*
    VARS:
        maxVal(int): the maxVal of all elements in nums
        countArr(int[]): an array used to count the occurance of elements in nums
        startIndexArr(int[]): an array for storing start index of each element
        sortedArr(int[]): array for storing sorted array with same size of nums
    DESCRIPTION:
        STEP 1
        Get maximum value of nums by calling method and assign it to maxVal
            int maxVal = getMaxVal(nums)
        STEP 2
        Initialize countArr from nums and maxVal and return it
            int[] countArr = constructCountArr(nums, maxVal);
        STEP 3
        Initialize startIndexArr from nums, maxVal, countArr and return it
            int[] startIndexArr = constructStartIndexArr(countArr);
        STEP 4
        Initialize sortedArr from nums, startIndexArr and return it
            int[] sortedArr = constructSortedArr(nums, startIndexArr);
        STEP 5
        Copy each element of sortedArr back to nums to make it "in-place", though it is absolutely optional
            copy(sortedArr, nums);
            
        -FUNC int getMaxVal(int[] nums)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal
                STEP 4
                Update maxVal with nums[idx]
        STEP 5
        Return maxVal as final result
            return maxVal;
            
        -FUNC int[] constructCountArr(int[] nums, int maxVal)
        STEP 1
        Initialize countArr to new int[maxVal + 1]
            int[] countArr = new int[maxVal + 1];
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase countArr[nums[idx]] by one to indicate there is one more element
                (countArr[nums[idx]])++;
        STEP 4
        Directly return countArr as final result
            return countArr
            
        -FUNC int[] constructStartIndexArr(int[] countArr)
        STEP 1
        Initialize startIndexArr to new int[countArr.length]
            int[] startIndexArr = new int[countArr.length];
        Initialize beforeAllSum to 0 to store all indexes before current iterated index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of startIndexArr with idx
            STEP 3
            If countArr[idx] == 0, (meaning there is not any element equal to idx in nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            First cache initial value of countArr[idx] for later adding operation and assign it to initialToBeAdded
                initialToBeAdded = countArr[idx];
            STEP 6
            Update startIndex[idx] to beforeAllSum to get its start index in sorted array
                startIndex[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
        STEP 8
        Return startIndexArr as final result
            return startIndexArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] startIndexArr)
        STEP 1
        Initialize sortedArr with the same size of nums
            int[] sortedArr = new int[nums.length];
        Initialize idxToInsertInSortedArr to -1 for reusage purpose
            int idxToInsertInSortedArr = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding start index of current iterated element in sorted array from startIndexArr by index of nums[idx] and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = startIndexArr[nums[idx]];
            STEP 4
            Insert value of element nums[idx] into the index idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx]
            STEP 5
            Increase the value of sortedArr[nums[idx]] by one for next duplicated element if possible
                (sortedArr[nums[idx]])++;
        STEP 6
        Return sortedArr as final result
            return sortedArr;
        
        -FUNC void copy(int[] sortedArr, int[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
        
    TIME:
        O(n + k), n is the size of nums, k is the maximum value of all elements in nums, which is also the number of index of counts array
    SPACE:
        O(n + k), n is for the size of final sorted array, k is for the occurance counting array
*/

class CountingSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int maxVal = getMaxVal(nums);
        // STEP 2
        int[] countArr = constructCountArr(nums, maxVal);
        // STEP 3
        int[] startIndexArr = constructStartIndexArr(countArr);
        // STEP 4
        int[] sortedArr = constructSortedArr(nums, startIndexArr);
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
        int[] countArr = new int[maxVal + 1];
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            (countArr[nums[idx]])++;
        }
        // STEP 4
        return countArr;
    }
    
    public static int[] constructStartIndexArr(int[] countArr) {
        // STEP 1
        int[] startIndexArr = new int[countArr.length];
        // STEP 2
        int beforeAllSum = 0;
        int initialToBeAdded = -1;
        // STEP 3
        for (int idx = 0; idx < startIndexArr.length; idx++) {
            // STEP 4
            if (countArr[idx] == 0) {
                // STEP 5
                continue;
            }
            // STEP 6
            initialToBeAdded = countArr[idx];
            // STEP 7
            startIndexArr[idx] = beforeAllSum;
            // STEP 8
            beforeAllSum += initialToBeAdded;
        }
        // STEP 9
        return startIndexArr;
    }
    
    public static int[] constructSortedArr(int[] nums, int[] startIndexArr) {
        // STEP 1
        int[] sortedArr = new int[nums.length];
        int idxToInsertInSortedArr = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            idxToInsertInSortedArr = startIndexArr[nums[idx]];
            // STEP 4
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 5
            (startIndexArr[nums[idx]])++;
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