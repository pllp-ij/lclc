import java.util.Arrays;

/*
    NOTE:
        Compared to V1, V2 will stop early after visited (nums.length) non-zero elements to skipping potential ones
    VARS:
        maxVal(int): the maximum value in nums for counting sort
        minVal(int): the minimum value in nums for counting sort
        cacheArr(int[]): the cache array used as countArr to store the occurance of each element in nums, which will then used to calculate the max gap
        maxGap(int[]): the maximum gap as final result
        curGap(int[]): var used to represent current iterated gap
        lastNonZeroIdx(int): the var used to store last non-zero index of count array in for loop
    DESCRIPTION:
        STEP 1
        If nums.length < 2, (meaning there is not at least two elements in nums)
            STEP 2
            Directly return 0
                return 0;
        STEP 3
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE}
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VALUE}
        STEP 4
        Update maxVal and minVal by calling getMaxMinVal method
            getMaxMinVal(nums, maxVal, minVal);
        STEP 5
        Initialize cacheArr to new int[(maxVal[0] - minVal[0]) + 1]
            int[] cacheArr = new int[(maxVal[0] - minVal[0]) + 1]
        STEP 6
        Update cacheArr as countArr by calling method constructCountArr
            constructCountArr(nums, minVal[0], cacheArr);
        
        Now all elements counting result in nums are stored in cacheArr, next will find the maximum gap between two non-zero elements in it
            
        STEP 7
        Initialize maxGap to Integer.MIN_VALUE;
            int maxGap = Integer.MIN_VALUE;
        Initialize curGap to -1 for reusage purpose
            int curGap = -1;
        Initialize lastNonZeroIdx to 0 for reusage purpose to indicate last non-zero index
            int lastNonZeroIdx = 0;
        Initialize nonZeroNum to 0 to record the total non-zero index has been visited now
            int nonZeroNum = 0;
        STEP 8
        Iterate each index of cacheArr with idx
            STEP 9
            If cacheArr[idx] == 0, (meaning there is no element equal to idx in initial nums)
                STEP 10
                Skip current iteration
                    continue;
            STEP 11
            If nonZeroNum == nums.length, (meaning all elements in nums has been visited, so break to finish)
                STEP 12
                Break the loop
            STEP 13
            Get curGap by substracting lastNonZeroIdx from idx and assign it to curGap
                curGap = idx - lastNonZeroIdx;
            STEP 14
            If curGap > maxGap, (a new maximum gap is found)
                STEP 15
                Update maxGap with curGap
            STEP 16
            Update lastNonZeroIdx to current non-zero idx for next one
                lastNonZeroIdx = idx;
            STEP 17
            Increase nonZeroNum by one
                nonZeroNum++;
        STEP 18
        Return maxGap as final result
            return maxGap
                
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (a new maximum element is found)
                STEP 3
                Update maxVal[0] with nums[idx]
                    maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] < minVal[0], (a new minimum element is found)
                STEP 5
                Update minVal[0] with nums[idx]
                    minVal[0] = nums[idx];
                
        -FUNC constructCountArr(int[] nums, int[] cacheArr, int minVal)
        STEP 1
        Initialize curMappedVal to -1 for reusage purpose to store the index mapped from initial value in nums to new shifted range
            int curMappedVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curMappedVal with nums and minVal
                curMappedVal = nums[idx] - minVal;
            STEP 4
            Increase (cacheArr[curMappedVal]) to indicate there is one more element exist in nums
                (constructCountArr[curMappedVal])++;            
        
    TIME:
        O(n + k), n is the size of nums, k is the maximum value in nums
    SPACE:
        O(n + k), n is the size of sortedArr used, k is the size of cacheArr used
*/

class MaximumGapV2 {
    
    public static int getMaxGap(int[] nums) {
        // STEP 1
        if (nums.length < 2) {
            // STEP 2
            return 0;
        }
        // STEP 3
        // STEP 3
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 4
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 5
        int[] cacheArr = new int[(maxVal[0] - minVal[0]) + 1];
        // STEP 6
        constructCountArr(nums, minVal[0], cacheArr);
        // STEP 7
        int maxGap = Integer.MIN_VALUE;
        int curGap = -1;
        int lastNonZeroIdx = 0;
        int nonZeroNum = 0;
        // STEP 8
        for (int idx = 0; idx < cacheArr.length; idx++) {
            // STEP 9
            if (cacheArr[idx] == 0) {
                // STEP 10
                continue;
            }
            // STEP 11
            if (nonZeroNum == nums.length) {
                // STEP 12
                break;
            }
            // STEP 13
            curGap = idx - lastNonZeroIdx;
            // STEP 14
            if (curGap > maxGap) {
                // STEP 15
                maxGap = curGap;
            }
            // STEP 16
            lastNonZeroIdx = idx;
            // STEP 17
            nonZeroNum++;
        }
        // STEP 18
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
    
    public static void constructCountArr(int[] nums, int minVal, int[] cacheArr) {
        // STEP 1
        int curMappedVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curMappedVal = nums[idx] - minVal;
            // STEP 4
            (cacheArr[curMappedVal])++;
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