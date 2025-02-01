import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
    VARS:
        bucketsNum(int): the number of buckets the nums splitted into
        buckets(List<Integer>[]): an array of List<Integer> to represent the buckets used in sort
        idxBucketToPlace(int): the index of bucket to place current iterated element in nums
        maxVal(int): the maximum value in nums
        minVal(int): the minimum value in nums
        singleBucketSize(double): the size of single bucket divide range of nums by bucketsNum, which is double type
        sortedList(List<Integer>): the list used to store all the sorted elements within single bucket
    DESCRIPTION:
        STEP 1
        Initialize buckets by calling method initializeBuckets
            List<Integer>[] buckets = initializeBuckets(bucketsNum);
        Initialize idxBucketToPlace to -1 for reusage purpose
            int idxBucketToPlace = -1;
        STEP 2
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE}
        Initialize minVal to new int[] {Integer.MAX_VALUE}
            int[] minVal = new int[] {Integer.MAX_VALUE}
        STEP 3
        Update maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal);
        STEP 4
        Get singleBucketSize as (maxVal[0] - minVal[0]) / bucketsNum
            double singleBucketSize = (double) (maxVal[0] - minVal[0]) / bucketsNum;
        STEP 5
        If singleBucketSize < 1, (meaning the size of single bucket is smaller than 1, it needed to set as 1)
            STEP 6
            Reset singleBucketSize to 1.0
                singleBucketSize = 1.0;
        STEP 7
        Iterate each index of nums with idx
            STEP 8
            Get idxBucketToPlace to place current iterated index with value of (int) (nums[idx] - minVal[0]) / singleBucketSize, which is already converted to int type
                idxBucketToPlace = (int) (nums[idx] - minVal) / singleBucketSize
            STEP 9
            If idxBucketToPlace == bucketsNum, (meaning current nums[idx] is the maximum element in nums, so put it in last bucket with index of idxBucketToPlace - 1)
                STEP 10
                Put nums[idx] into last bucket
                    buckets[idxBucketToPlace - 1].add(nums[idx]);
            Else, (meaning all elements in nums except maximum element)
                STEP 11
                Put nums[idx] into bucket at index idxBucketToPlace
                    buckets[idxBucketToPlace].add(nums[idx]);
        
        Now all elements in nums are already put into buckets, next sort each single bucket with built-in method
        
        STEP 12
        Iterate each index of buckets with idx
            STEP 13
            Sort the bucket with built-in method Collections.sort
                Collections.sort(bucket[idx]);
        
        Now all buckets are sorted, create a sortedList to collect all of them
        
        STEP 14
        Initialize sortedList to new ArrayList<>()
            List<Integer> sortedList = new ArrayList<>();
        STEP 15
        Iterate each index of buckets with idx
            STEP 16
            Add all elements within bucket to sortedList
                sortedList.addAll(bucket[idx]);
        
        Now all elements in nums are sorted and store in sortedList, copy it back to nums
        
        STEP 17
        Iterate each index of sortedList with idx
            STEP 18
            Copy sortedList.get(idx) to nums[idx]
                nums[idx] = sortedList.get(idx);
        
        -FUNC List<Integer> initializeBuckets(int bucketsNum)
        USE "@SuppressWarnings({"rawtypes", "unchecked"})" to suppress warnings of "rawtypes" and "unchecked"
        STEP 1
        Initialize List<Integer> buckets to new List<>[bucketsNum]
            List<Integer> buckets = new List<>[bucketsNum];
        STEP 2
        Iterate each index from 0 to bucketsNum - 1 with idx
            STEP 3
            Create new ArrayList<>() object under each index
                buckets[idx] = new ArrayList<>();
        STEP 4
        Return buckets as final result
            return buckets
        
        
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum element is found)
                STEP 3
                maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum element is found)
                STEP 5
                minVal[0] = nums[idx];

    TIME:
        O(n / k + k) ~ O(n + k), first n / k is the average number of elements in each bucket, it is linear to the size of nums n, second k is for the collecting all elements in each bucket
    SPACE:
        O(n + k), first n is for the size of sortedList, second k is for the buckets size created
*/

class BucketSortV1 {
    
    public static void sort(int[] nums, int bucketsNum) {
        // STEP 1
        List<Integer>[] buckets = initializeBuckets(bucketsNum);
        int idxBucketToPlace = -1;
        // STEP 2
        int[] maxVal = new int[] {Integer.MIN_VALUE};
        int[] minVal = new int[] {Integer.MAX_VALUE};
        // STEP 3
        getMaxMinVal(nums, maxVal, minVal);
        // STEP 4
        double singleBucketSize = (double) (maxVal[0] - minVal[0]) / bucketsNum;
        // STEP 5
        if (singleBucketSize < 1) {
            // STEP 6
            singleBucketSize = 1.0;
        }
        // STEP 7
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 8
            idxBucketToPlace = (int) ((nums[idx] - minVal[0]) / singleBucketSize);
            // STEP 9
            if (idxBucketToPlace == bucketsNum) {
                // STEP 10
                buckets[idxBucketToPlace - 1].add(nums[idx]);
            } else {
                // STEP 11
                buckets[idxBucketToPlace].add(nums[idx]);
            }
        }
        // STEP 12
        for (int idx = 0; idx < buckets.length; idx++) {
            // STEP 13
            Collections.sort(buckets[idx]);
        }
        // STEP 14
        List<Integer> sortedList = new ArrayList<>();
        // STEP 15
        for (int idx = 0; idx < buckets.length; idx++) {
            // STEP 16
            sortedList.addAll(buckets[idx]);
        }
        // STEP 17
        for (int idx = 0; idx < sortedList.size(); idx++) {
            // STEP 18
            nums[idx] = sortedList.get(idx);
        }
    }
    
    public static List<Integer>[] initializeBuckets(int bucketsNum) {
        @SuppressWarnings({"rawtypes", "unchecked"})
        // STEP 1
        List<Integer>[] buckets = new ArrayList[bucketsNum];
        // STEP 2
        for (int idx = 0; idx < bucketsNum; idx++) {
            // STEP 3
            buckets[idx] = new ArrayList<>();
        }
        // STEP 4
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
        // int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8, 5, 5};
        // int bucketsNum = 4;
        
        int[] nums = {5, 2, 3, -7, 9, -4, 1, 10, -6, 8, -5, -5};
        int bucketsNum = 3;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums, bucketsNum);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}