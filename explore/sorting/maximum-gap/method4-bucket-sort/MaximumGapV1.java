import java.util.Arrays;

/*
    NOTE:
        The principle behind bucket solution is that the maximum gap between two successive elemets in sorted form has a minimum value in case where
        all n elements are distributed uniformly and the width of each gap is (maxVal - minVal) / (n - 1), so set the size of bucket equal or small than it
        will generate a condition only need to to compare the adjacent gap of two buckets
    VARS:
        bucketsNum(int): the number of buckets used, any value larger than (n - 1) is ok 
        buckets(int[][]): a 2d array represent the buckets
        idxBucketToPlace(int): the index of bucket to place current iterated element in nums
        maxVal(int[]): an array used to maintain maximum value in nums
        minVal(int[]): an array used to maintain minimum value in nums
        singleBucketSize(double): the size of single bucket, calculated by dividing range using bucketsNum
        maxGap(int): the maximum gap as final returned result
        curGap(int): used to calculate current gap under current iterated bucket
        lastMax(int): the value of last bucket's maximum value fro calculating current gap
    DESCRIPTION:
        STEP 1
        If nums.length < 2, (meaning there is not at least two elements in nums)
            STEP 2
            Directly return 0 as final result
                return 0;
        STEP 3
        Initialize bucketsNum to nums.length - 1, any value larger than it also fine
            int bucketsNum = nums.length - 1;
        Initialize idxBucketToPlace to -1 for reusage purpose
            int idxBucketToPlace = -1;
        STEP 4
        Initialize buckets by calling method initializeBuckets(bucketsNum)
            int[][] buckets = initializeBuckets(bucketsNum);
        STEP 5
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE};
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VALUE};
        STEP 6
        Update maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal);
        STEP 7
        Calculate single bucket size by dividing range using bucketsNum and assign it to singleBucketSize
            double singleBucketSize = (double) (maxVal[0] - minVal[0]) / bucketsNum;  // DO NOT INCLUDE (maxVal[0] - minVal[0]) /  bucketsNum INTO (), OTHERWISE IT WILL BE TRUNCATED TO INT FIRST
        STEP 8
        If singleBucketSize < 1, (meaning singleBucketSize is smaller than 1)
            STEP 9
            Reset singleBucketSize to 1.0
                singleBucketSize = 1.0;
        STEP 10
        Iterate each index of nums with idx
            STEP 11
            Get index of bucket to place current iterated element and assign it to idxBucketToPlace
                idxBucketToPlace = (int) ((nums[idx] - minVal[0]) / singleBucketSize);
            STEP 12
            If idxBucketToPlace == bucketsNum, (meaning current nums[idx] is the maximum element in nums, the index of bucket should be minus one to avoid overflowing)
                STEP 13
                Update buckets[idxBucketToPlace - 1][0] with the minimum one between it and nums[idx]
                    buckets[idxBucketToPlace - 1][0] = Math.min(buckets[idxBucketToPlace - 1][0], nums[idx]);
                STEP 14
                Update buckets[idxBucketToPlace - 1][1] with the maximum one between it and nums[idx]
                    buckets[idxBucketToPlace - 1][1] = Math.max(buckets[idxBucketToPlace - 1][1], nums[idx]);
            Else, (meaning current iterated element is not the maximum element in nums, so there is no need to decrease idxBucketToPlace by one)
                STEP 15
                Update buckets[idxBucketToPlace][0] with the minimum one between it and nums[idx]
                    buckets[idxBucketToPlace][0] = Math.min(buckets[idxBucketToPlace][0], nums[idx]);
                STEP 16
                Update buckets[idxBucketToPlace][1] with the maximum one between it and nums[idx]
                    buckets[idxBucketToPlace][1] = Math.max(buckets[idxBucketToPlace][1], nums[idx])

        Now all bucket in buckets are updated for all elements in nums, next find the maxGap from it
        
        STEP 17
        Initialize maxGap to (maxVal[0] - minVal[0]) / bucketsNum, because the minimum boundary of maximum gap is the (int) ((maxVal[0] - minVal[0]) / bucketsNum), here do not use (int) singleBucketSize, because when singleBucketSize is in range (0, 1), the value it will be reset to 1 to be at least with length of one as bucket
            int maxGap = (maxVal[0] - minVal[0]) / bucketsNum;
        Initialize curGap to -1 for reusage purpose
            int curGap = -1;
        Initialize lastMax to buckets[0][0] as default for calculation convenience
            int lastMax = buckets[0][0]
        STEP 18
        Iterate each index of buckets with idx
            STEP 19
            If buckets[idx][0] > buckets[idx][1], (meaning current bucket is not updated with elements from nums, so directly skip it)
                STEP 20
                Skip current iteration
                    continue;
            STEP 21
            Calculate current gap by substracting lastMax from buckets[idx][0] and assign it to curGap
                curGap = buckets[idx][0] - lastMax;
            STEP 22
            If curGap > maxGap, (a new maximum gap is found)
                STEP 23
                Update maxGap to curGap
                    maxGap = curGap;
            STEP 24
            Update lastMax to the second element of current bucket
                lastMax = bucket[idx][1];
        STEP 25
        Return maxGap as final result
            return maxGap;
        
        -FUNC int[][] initializeBuckets(int bucketsNum)
        STEP 1
        Initialize buckets to new int[bucketsNum][2]
            int[][] buckets = new int[bucketsNum][2];
        STEP 2
        Iterate idx from 0 to bucketsNum - 1
            STEP 3
            Update buckets[idx][0] to Integer.MAX_VALUE
                buckets[idx][0] = Integer.MAX_VALUE;
            STEP 4
            Update buckets[idx][1] to Integer.MIN_VALUE
                buckets[idx][1] = Integer.MIN_VALUE;
        STEP 5
        Return buckets as final result
            return buckets;
            
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum is found)
                STEP 3
                Update maxVal[0] to nums[idx]
                    maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum is found)
                STEP 5
                Update minVal[0] to nums[idx]
                    minVal[0] = nums[idx]

    TIME:
        O((n - 1) + n + (n - 1)) ~ O(n), first (n - 1) is for creating the buckets with size of (n - 1), it can also be larger than (n - 1), second n is for iterate each element of nums to place it into buckets, third (n - 1) is for finding the maxGap from (n - 1) bucket
    SPACE:
        O(n - 1 + k) ~ O(n - 1 + n / n - 1) ~ O(n - 1 + 1 + 1 / (n - 1)) ~ O(n + 1 / (n - 1)) ~ O(n), the k is the average number of elements within single bucket
*/

class MaximumGapV1 {
    
    public static int getMaxGap(int[] nums) {
        // STEP 1
        if (nums.length < 2) {
            // STEP 2
            return 0;
        }
        // STEP 3
        int bucketsNum = nums.length - 1;
        int idxBucketToPlace = -1;
        // STEP 4
        int[][] buckets = initializeBuckets(bucketsNum);
        // STEP 5
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 6
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 7
        double singleBucketSize = (double) (maxVal[0] - minVal[0]) /  bucketsNum;
        // STEP 8
        if (singleBucketSize < 1) {
            // STEP 9
            singleBucketSize = 1.0;
        }
        // STEP 10
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 11
            idxBucketToPlace = (int) ((nums[idx] - minVal[0]) / singleBucketSize);
            // STEP 12
            if (idxBucketToPlace == bucketsNum) {
                // STEP 13
                buckets[idxBucketToPlace - 1][0] = Math.min(buckets[idxBucketToPlace - 1][0], nums[idx]);
                // STEP 14
                buckets[idxBucketToPlace - 1][1] = Math.max(buckets[idxBucketToPlace - 1][1], nums[idx]);
            } else {
                // STEP 15
                buckets[idxBucketToPlace][0] = Math.min(buckets[idxBucketToPlace][0], nums[idx]);
                // STEP 16
                buckets[idxBucketToPlace][1] = Math.max(buckets[idxBucketToPlace][1], nums[idx]);
            }
        }
        // STEP 17
        int maxGap = (maxVal[0] - minVal[0]) / bucketsNum;
        int curGap = -1;
        int lastMax = buckets[0][0];
        // STEP 18
        for (int idx = 0; idx < buckets.length; idx++) {
            // STEP 19
            if (buckets[idx][0] > buckets[idx][1]) {
                // STEP 20
                continue;
            }
            // STEP 21
            curGap = buckets[idx][0] - lastMax;
            // STEP 22
            if (curGap > maxGap) {
                // STEP 23
                maxGap = curGap;
            }
            // STEP 24
            lastMax = buckets[idx][1];
        }
        // STEP 25
        return maxGap;
    }
    
    public static int[][] initializeBuckets(int bucketsNum) {
        // STEP 1
        int[][] buckets = new int[bucketsNum][2];
        // STEP 2
        for (int idx = 0; idx < bucketsNum; idx++) {
            // STEP 3
            buckets[idx][0] = Integer.MAX_VALUE;
            // STEP 4
            buckets[idx][1] = Integer.MIN_VALUE;
        }
        // STEP 5
        return buckets;
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
    
    public static void main(String[] args) {
        // int[] nums = {3, 6, 9, 1};
        // int[] nums = {10};
        // int[] nums = {2, 99999999};
        int[] nums = {0, 0, 0};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int result = getMaxGap(nums);
        System.out.println("max gap is: " + result);
    }
}