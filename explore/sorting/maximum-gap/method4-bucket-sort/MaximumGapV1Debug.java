import java.util.Arrays;

/*
    NOTE:
        The principle behind bucket solution is that the maximum gap between two successive elemets in sorted form has a minimum value in case where
        all n elements are distributed uniformly and the width of each gap is (maxVal - minVal) / (n - 1), so set the size of bucket equal or small than it
        will generate a condition only need to to compare the adjacent gap of two buckets
    VARS:
        bucketsNum(int): the number of buckets defined by custom
        buckets(int[][]): a 2d array used as buckets, each element of which is a pair of value to indicate the minimum and maximum element repectively
        idxBucketToPlace(int): the index of bucket to place current iterated element in nums
        singleBucketSize(double): the size of single bucket in bucket sort
        maxVal(int[]): a array to maintain the maximum value in nums
        minVal(int[]): a arary to maintain the minimum value in nums
        maxGap(int): the maximum gap as final returned result
        curGap(int): the current gap between adjacent buckets
    DESCRIPTION:
        STEP 1
        Initialize bucketsNum to (nums.length - 1), (or any value larger than n - 1, ensure the size of single bucket is smaller than (maxVal[0] - minVal[0]) / (n - 1))
            int bucketsNum = nums.length - 1;
        Initialize idxBucketToPlace to -1 for reusage purpoe
            int idxBucketToPlace = -1;
        STEP 2
        Initialize buckets by calling method receive bucketsNum as parameter
            int[][] buckets = initializeBuckets(bucketsNum);
        STEP 3
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE}
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VALUE}
        STEP 4
        Update maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal);
        STEP 5
        Initialize singleBucketSize to (maxVal[0] - minVal[0]) / bucketsNum
            double singleBucketSize = (double) (maxVal[0] - minVal[0]) / bucketsNum;
        STEP 6
        If singleBucketSize < 1, (meaning the size of a bucket is smaller than 1, so reset it to 1)
            STEP 7
            Reset singleBucketSize to 1.0
                singleBucketSize = 1.0;
        STEP 8
        Iterate each index of nums with idx
            STEP 9
            Get the index of bucket to place current index and assign it to idxBucketToPlace
                idxBucketToPlace = (int) ((nums[idx] - minVal[0]) / singleBucketSize);
            STEP 10
            If idxBucketToPlace == bucketsNum, (meaning current nums[idx] is the maximum element, it need processed specially)
                STEP 11
                Update the possible maximum value in last bucket with nums[idx]
                    buckets[idxBucketToPlace - 1][0] = Math.min(buckets[idxBucketToPlace - 1][0], nums[idx]);
                STEP 12
                Update the possible minimum value in last bucket with nums[idx]
                    buckets[idxBucketToPlace - 1][1] = Math.max(buckets[idxBucketToPlace - 1][1], nums[idx]);
            Else, (meaning current nums[idx] is not the maximum element, so it should be placed at bucket with index idxBucketToPlace)
                STEP 13
                Update the possible maximum value in bucket with index idxBucketToPlace with nums[idx]
                    buckets[idxBucketToPlace][0] = Math.min(buckets[idxBucketToPlace][0], nums[idx]);
                STEP 14
                Update the possible minimum value in bucket with index idxBucketToPlace with nums[idx]
                    buckets[idxBucketToPlace][1] = Math.max(buckets[idxBucketToPlace][1], nums[idx]);
        
        Now all possible range of each bucket are stored in buckets, next get the maximum gap between adjacent buckets, and the maxGap should be initialized to (maxVal[0] - )
        
        STEP 15
        Initialize maxGap to minimum value of (int) singleBucketSize as default value   
            int maxGap = (int) singleBucketSize;
        Initialize curGap to -1 for reusage purpose
            int curGap = -1;
        Initialize lastMax to the minimum value of first bucket's pari value as initial state
            int lastMax = buckets[0][0];
        STEP 16
        Iterate each element with int[] type of buckets with pairVal
            STEP 17
            If buckets[idx][0] > buckets[idx][1], (meaning current bucket is not added into any element)
                STEP 18
                Skip current iteration
                    continue;
            STEP 19
            Calculate current gap by substracting lastMax from pairVal[0] and assign it to curGap
                curGap = pairVal[0] - lastMax;
            STEP 20
            If curGap > maxGap, (a new maximum gap is found)
                STEP 21
                Update maxGap to curGap
                    maxGap = curGap;
            STEP 22
            Update lastMax to the second element of current pair value for next iteration
                lastMax = buckets[idx][1];
        STEP 23
        Return maxGap as final result
            return maxGap;

        -FUNC int[][] initializeBuckets(int bucketsNum)
        STEP 1
        Initialize buckets to new int[bucketsNum][2]
            int[][] buckets = new int[bucketsNum][2];
        STEP 2
        Iterate each bucket of buckets with idx
            STEP 3
            Set the first element in buckets[idx] as Integer.MAX_VALUE
                buckets[idx][0] = Integer.MAX_VALUE;
            STEP 4
            Set the second element in buckets[idx] as Integer.MIN_VALUE
                buckets[idx][1] = Integer.MIN_VALUE;
        STEP 5
        Return buckets as final result
            return buckets
        
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum element is found)
                STEP 3
                Update maxVal[0] to nums[idx]
                    maxVal[0] = nums[idx]
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum element is found)
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
        int bucketsNum = nums.length - 1;
        int idxBucketToPlace = -1;
        // STEP 2
        int[][] buckets = initializeBuckets(bucketsNum);
        // STEP 3
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 4
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 5
        double singleBucketSize = (double) (maxVal[0] - minVal[0]) /  bucketsNum;
        // STEP 6
        if (singleBucketSize < 1) {
            // STEP 7
            singleBucketSize = 1.0;
        }
        // STEP 8
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 9
            idxBucketToPlace = (int) ((nums[idx] - minVal[0]) /  singleBucketSize);
            // STEP 10
            if (idxBucketToPlace == bucketsNum) {
                // STEP 11
                buckets[idxBucketToPlace - 1][0] = Math.min(buckets[idxBucketToPlace - 1][0], nums[idx]);
                // STEP 12
                buckets[idxBucketToPlace - 1][1] = Math.max(buckets[idxBucketToPlace - 1][1], nums[idx]);
            } else {
                // STEP 13
                buckets[idxBucketToPlace][0] = Math.min(buckets[idxBucketToPlace][0], nums[idx]);
                // STEP 14
                buckets[idxBucketToPlace][1] = Math.max(buckets[idxBucketToPlace][1], nums[idx]);
            }
        }
        System.out.println("buckets: " + Arrays.deepToString(buckets));
        // STEP 15
        int maxGap = (int) singleBucketSize;
        System.out.println("initial maxGap: " + maxGap);
        int curGap = -1;
        int lastMax = buckets[0][0];
        // STEP 16
        for (int idx = 0; idx < buckets.length; idx++) {
            if (buckets[idx][1] < buckets[idx][0]) {
                continue;
            }
            // STEP 17
            curGap = buckets[idx][0] - lastMax;
            // STEP 18
            if (curGap > maxGap) {
                // STEP 19
                maxGap = curGap;
            }
            // STEP 20
            lastMax = buckets[idx][1];
        }
        // STEP 20
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
                // STEP 4
                maxVal[0] = nums[idx];
            }
            // STEP 5
            if (nums[idx] < minVal[0]) {
                // STEP 6
                minVal[0] = nums[idx];
            }
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