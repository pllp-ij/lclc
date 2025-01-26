import java.util.Arrays;

/*
    VARS:
        maxVal(int): the maxVal of all elements in nums
        minVal(int): the min val of all elements in nums
        countArr(int[]): an array used to count the occurance of elements in nums
        startIndexArr(int[]): an array for storing start index of each element
        sortedArr(int[]): array for storing sorted array with same size of nums
    DESCRIPTION:
        STEP 1
        Initialize maxVal to new int[]{Integer.MIN_VALUE}, initialize minVal to new int[] {Integer.MAX_VALUE}
        STEP 2
        Get maxVal and minVal by calling method getMaxMinVal to get the maximum or minimum value of nums
            getMaxMinVal(nums, maxVal, minVal);
        STEP 3
        Initialize countArr by calling method constructCountArr and return it
            int[] countArr = constructCountArr(nums, maxVal[0], minVal[0]);
        STEP 4
        Initialize startIndexArr by calling method constructStartIndex and return it
            int[] startIndexArr = constructStartIndexArr(countArr)
        STEP 5
        Initialize sortedArray by calling method constructSortedArr and return it
            int[] sortedArr = constructSortedArr(nums, countArr, minVal[0]);
        STEP 6
        Copy each elements from sortedArr to nums
            copy(sortedArr, nums)
            
        -FUNC int getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 3
            If nums[idx] > maxVal[0],
                STEP 4
                Update maxVal[0] with nums[idx]
                    maxVal[0] = nums[idx];
            STEP 5
            If nums[idx] < minVal[0],
                STEP 6
                Update minVal[0] with nums[idx]
                    minVal[0] = nums[idx]
            
        -FUNC int[] constructCountArr(int nums, int maxVal, int minVal)
        STEP 1
        Initialize countArr to new int[(maxVal - minVal) + 1], (note that the allocated length of countArr is (maxVal - minVal) + 1, which is suitable to store all range from minVal to maxVal)
            int[] countArr = new int[maxVal + 1]
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Increase the value of (countArr[nums[idx] - minVal]) by one to indicate there is one more eleemnt whose value is equal to nums[idx] in countArr, (here is a range shift happen, note the value of nums[idx] may be negative, so substract minVal from it to ensure it located within valid index range of countArr)
                (countArr[nums[idx] - minVal])++;
        STEP 4
        Return countArr as final result
            return countArr;
        
        -FUNC int[] constructStartIndexArr(int[] countArr)
        STEP 1
        Initialize startIndexArr to new int[countArr.length]
            int[] startIndexArr = new int[countArr.length]
        Initialize beforeAllSum to 0 to store all value of index before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of each index for later adding operation
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of countArr with idx
            STEP 3
            If countArr[idx] == 0, (meaning current element is not exist in initial nums array)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value of index and assign it to initialToBeAdded
                initialToBeAdded = countArr[idx]
            STEP 6
            Update startIndexArr[idx] with beforeAllSum
                startIndexArr[idx] = beforeAllSum;
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;
        STEP 8
        Return startIndexArr Arr as final result
            return startIndexArr;
            
        -FUNC int[] constructSortedArr(int[] nums, int[] startIndexArr, int[] startIndexArr, int minVal)
        STEP 1
        Initialize sortedArr to new int[nums.length]
            int[] sortedArr = new int[nums.length]
        Initialize idxToInsertInSortedArr to -1 for reusage purpose
            int idxToInsertInSortedArr = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponding start index of current element in sortedArr from startIndexArr with index (nums[idx] - minVal) and assign it to idxToInsertInSortedArr
                idxToInsertInSortedArr = startIndexArr[nums[idx] - minVal];
            STEP 4
            Insert nums[idx] into the position of idxToInsertInSortedArr in sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx];
            STEP 5
            Increase the value at startIndexArr[nums[idx] - minVal] by one for next duplicated element
                (startIndexArr[nums[idx] - minVal])++;
        STEP 6
        Return startIndexArr as final result
            return startIndexArr;
            
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

class CountingSortV1 {
    
    public static void sort(int[] nums) {
        // STEP 1
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 2
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 3
        int[] countArr = constructCountArr(nums, maxVal[0], minVal[0]);
        // STEP 4
        int[] startIndexArr = constructStartIndexArr(countArr);
        // STEP 5
        int[] sortedArr = constructSortedArr(nums, startIndexArr, minVal[0]);
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
        int[] countArr = new int[(maxVal - minVal) + 1];
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            (countArr[nums[idx] - minVal])++;
        }
        // STEP 4
        return countArr;
    }
    
    public static int[] constructStartIndexArr(int[] countArr) {
        // STEP 1
        int[] startIndexArr = new int[countArr.length];
        int beforeAllSum = 0;
        int initialToBeAdded = -1;
        // STEP 2
        for (int idx = 0; idx < startIndexArr.length; idx++) {
            // STEP 3
            if (countArr[idx] == 0) {
                // STEP 4
                continue;
            }
            // STEP 5
            initialToBeAdded = countArr[idx];
            // STEP 6
            startIndexArr[idx] = beforeAllSum;
            // STEP 7
            beforeAllSum += initialToBeAdded;
        }
        // STEP 8
        return startIndexArr;
    }
    
    public static int[] constructSortedArr(int[] nums, int[] startIndexArr, int minVal) {
        // STEP 1
        int[] sortedArr = new int[nums.length];
        int idxToInsertInSortedArr = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            idxToInsertInSortedArr = startIndexArr[nums[idx] - minVal];
            // STEP 4
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 5
            (startIndexArr[nums[idx] - minVal])++;
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