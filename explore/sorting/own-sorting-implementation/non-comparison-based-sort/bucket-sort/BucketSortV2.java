import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/*
    NOTE:
        Compared to V1, V2 use 2d List<List<Integer>> instead of List<Integer>[], obviously, V2 is better for implementation
    VARS:
        bucketsNum(int): the number of bucket
        buckets(List<List<Integer>>): a 2d list used to represent the buckets
        idxBucketToPlace(int): the index of bucket to place current iterated element
        maxVal(int[]): an array used to maintain the maximum value in nums
        minVal(int[]): an array used to maintain the maximum value in nums
        singleBucketSize(double): the size of single bucket with double type
        sortedList(List<Integer>): the list used to collect all sorted elements
    DESCRIPTION:
        STEP 1
        Initialize buckets by calling method initializeBuckets(bucketsNum)
            List<List<Integer>> buckets = initializeBuckets(bucketsNum);
        STEP 2
        Initialize maxVal to new int[] {Integer.MIN_VALUE}
            int[] maxVal = new int[] {Integer.MIN_VALUE};
        Initialize minVal to new int[] {Integer.MAX_VALUE};
            int[] minVal = new int[] {Integer.MAX_VALUE};
        STEP 3
        Update maxVal and minVal by calling method getMaxMinVal(nums, maxVal, minVal)
            getMaxMinVal(nums, maxVal, minVal);
        STEP 4
        Calculate singleBucketSize by divide range using bucketsNum with double type
            double singleBucketSize = (maxVal[0] - minVal[0]) / bucketsNum;
        STEP 5
        If singleBucketSize < 1, (meaning singleBucketSize is smaller than 1, reset it to 1)
            STEP 6
            Reset singleBucketSize to 1.0
                singleBucketSize = 1.0
        STEP 7
        Iterate each index of nums with idx
            STEP 8
            Update idxBucketToPlace with (nums[idx] - minVal[0]) / singleBucketSize, which is truncated to int type
                idxBucketToPlace = (int) ((nums[idx] - minVal[0]) / singleBucketSize);
            STEP 9
            If idxBucketToPlace == bucketsNum, (meaning current element is the maximum element, directly put it into last bucket)
                STEP 10
                Put nums[idx] into the last bucket with index of (idxBucketToPlace - 1)
                    buckets.get(idxBucketToPlace - 1).add(nums[idx])
            Else, (meaning it is not the maximum element)
                STEP 11
                Put nums[idx] into the bucket with index idxBucketToPlace
                    buckets.get(idxBucketToPlace).add(nums[idx])
                    
        Now all elements are put into buckets, next sort each bucket with built-in method
        
        STEP 12
        Iterate each List<Integer> bucket of buckets
            STEP 13
            Sort the bucket with built-in Collections.sort method
                Collections.sort(bucket)
        
        Now all elements within each bucket are sorted, create a sortedList to collect them in order
        
        STEP 14
        Create a sortedList to collect all elements in each bucket
            List<Integer> sortedList = new ArrayList<>();
        STEP 15
        Iterate each List<Integer> bucket of buckets
            STEP 16
            Add all elements of current bucket into sortedList
                sortedList.addAll(bucket);
                
        Now all elements of nums are sorted and stored in sortedList, copy each of them back to nums
        
        STEP 17
        Iterate each index of sortedList with idx
            STEP 18
            Copy sortedList.get(idx) to nums[idx]
                nums[idx] = sortedList.get(idx)
                
        -FUNC List<List<Integer>> initializeBuckets(int bucketsNum)
        STEP 1
        Initialize buckets to ArrayList<>(bucketsNum);
            List<List<Integer>> buckets = new ArrayList<>(bucketsNum);
        STEP 2
        Iterate idx from 0 to (bucketsNum - 1)
            STEP 3
            Add new ArrayList for each idx into buckets
                buckets.add(new ArrayList<>());
        STEP 4
        Return buckets as final result
            return buckets
            
        -FUNC void getMaxMinVal(int[] nums, int[] maxVal, int[] minVal)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            If nums[idx] > maxVal[0], (meaning a new maximum element is found)
                STEP 3
                Update maxVal[0] to nums[idx]
                    maxVal[0] = nums[idx];
            STEP 4
            If nums[idx] < minVal[0], (meaning a new minimum element is found)
                STEP 5
                Update minVal[0] to nums[idx]
                    minVal[0] = nums[idx]
        
    TIME:
        O(n / k + k) ~ O(n + k), first n / k is the average number of elements in each bucket, it is linear to the size of nums n, second k is for the collecting all elements in each bucket
    SPACE:
        O(n + k), first n is for the size of sortedList, second k is for the buckets size created
*/

class BucketSortV2 {
    
    public static void sort(int[] nums, int bucketsNum) {
        // STEP 1
        List<List<Integer>> buckets = initializeBuckets(bucketsNum);
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
                buckets.get(idxBucketToPlace - 1).add(nums[idx]);
            } else {
                // STEP 11
                buckets.get(idxBucketToPlace).add(nums[idx]);
            }
        }
        // STEP 12
        for (List<Integer> bucket: buckets) {
            // STEP 13
            Collections.sort(bucket);
        }
        // STEP 14
        List<Integer> sortedList = new ArrayList<>();
        // STEP 15
        for (List<Integer> bucket: buckets) {
            // STEP 16
            sortedList.addAll(bucket);
        }
        // STEP 17
        for (int idx = 0; idx < sortedList.size(); idx++) {
            // STEP 18
            nums[idx] = sortedList.get(idx);
        }
    }
    
    public static List<List<Integer>> initializeBuckets(int bucketsNum) {
        // STEP 1
        List<List<Integer>> buckets = new ArrayList<>();
        // STEP 2
        for (int idx = 0; idx < bucketsNum; idx++) {
            // STEP 3
            buckets.add(new ArrayList<>());
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
        int bucketsNum = 4;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums, bucketsNum);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}