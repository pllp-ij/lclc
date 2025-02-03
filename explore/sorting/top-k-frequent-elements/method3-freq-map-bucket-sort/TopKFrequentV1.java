import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
    NOTE:
        The bucket sort in V3 here is deteriorated to counting sort
    VARS:
        hashValToFreq(Map<Integer, Integer>): a hash map used to record the value of element to its frequency
        hashFreqToValList(Map<Integer, List<Integer>>): a hash map used to record the frequency to the element value list
        maxFreqVal(int): the maximum freq in hashFreqToValList keys
        minFreqVal(int): the minimum freq in hashFreqToValList keys
        singleBucketSize(double): the size of single bucket, here fixed to 1, because we want each bucket contains only one element(freq)
        idxBucketToPlace(int): the index of bucket that current iterated element should be placed into
        result(int[]): for final return result
        idxResultInsertStart(int[]): the pointer array used to insert list into final return result
    DESCRIPTION:
        STEP 1
        Initialize hashValToFreq to HashMap<>()
            Map<Integer, Integer> hashValToFreq = new HashMap<>();
        Initialize hashFreqToValList to HashMap<>()
            Map<Integer, List<Integer>> hashFreqToValList = new HashMap<>();
        STEP 2
        Update hashValToFreq with collected element value and frequency by calling method getValAndFreq(nums, hashValToFreq)
            getValAndFreq(nums, hashValToFreq);
        STEP 3
        Update hashFreqToValList with reversed element value and freq by calling method getFreqAndValList(hashValToFreq)
            getFreqAndValList(hashValToFreq)
        STEP 4
        Initialize maxFreqVal to new int[] {Integer.MIN_VALUE}
            int[] maxFreqVal = new int[] {Integer.MIN_VALUE}
        Initialize minFreqVal to new int[] {Integer.MAX_VALUE}
            int[] minFreqVal = new int[] {Integer.MAX_VALUE}
        STEP 5
        Update maxFreqVal and minFreqVal by calling method getMaxMinFreqVal(hashFreqToValList, maxFreqVal, minFreqVal)
            getMaxMinFreqVal(hashFreqToValList, maxFreqVal, minFreqVal);
            
        Now all freq are stored in hashFreqToValList keys, and corresponding element values are stored in the value list, next follow the bucket sort logic
            
        STEP 6
        Initialize bucketsNum to (maxFreqVal[0] - minFreqVal[0]) + 1;
            int bucketsNum = (maxFreqVal[0] - minFreqVal[0]) + 1;
        Initialize idxBucketToPlace to -1 for reusage purpose
            int idxBucketToPlace = -1;
        STEP 7
        Initialize buckets to new int[bucketsNum], since all keys value of hashFreqToValList are distinct, so there is no need to create a second dimension of buckets, the content placed into each bucket is just a freq number
            int[] buckets = new int[bucketsNum]
            
        Actually, STEP 8 to STEP 10, and STEP 13 to STEP 14 are for obeying common process of bucket sort, they can be removed if needed, the reason are:
            For STEP 8 to STEP 10,
                the singleBucketSize should always be set to 1 to contains only one freq in each bucket
            For STEP 13 to STEP 14,
                the idxBucketToPlace for maximum element is (maxFreqVal[0] - minFreqVal[0]) / 1, which is always smaller than bucketsNum by one, so the if condition in STEP 13 will never to be true
            
        STEP 8
        Initialize singleBucketSize to (maxFreqVal[0] - minFreqVal[1]) / bucketsNum with double type, we can conclude that the singleBucketSize is always smaller than 1
            double singleBucketSize = (double) (maxFreqVal[0] - minFreqVal[1]) / bucketsNum;
        STEP 9
        If singleBucketSize < 1, (meaning the bucket size is smaller than 1, here absolutely true)
            STEP 10
            Reset singleBucketSize to 1.0, (also can direcly set singleBucketSize to 1.0 wihtout multiple steps)
                singleBucketSize = 1.0
        STEP 11
        Iterate each key of hashFreqToValList.keySet()
            STEP 12
            Calculate idxBucketToPlace
                idxBucketToPlace = (int) ((key - minFreqVal[0]) / singleBucketSize);
            STEP 13
            If idxBucketToPlace == bucketsNum, (meaning current key is the maximum freq in keys of hashFreqToValList, decrease idxBucketToPlace by one to put key into last bucket)
                STEP 14
                Decrease idxBucketToPlace by one to put key into last bucket
                    idxBucketToPlace--;
            STEP 15
            Add current key into index of idxBucketToPlace of buckets
                buckets[idxBucketToPlace] = key;
        
        Now all freq are stored and sorted into buckets, next get top k frequent element from it
        
        STEP 16
        Initialize resultList to ArrayList<>() for collecting all elements in list under current iterated key
            List<Integer> resultList = new ArrayList<>();
        STEP 17
        Iterate each index of buckets from (buckets.length - 1) to 0 with idx
            STEP 18
            If buckets[idx] == 0, (meaning there is no such element in keys of hashFreqToValList mapped to currrent bucket)
                STEP 19
                Skip current bucket
                    continue;
            STEP 20
            If k == 0, (meaning all top k freq elements are processed)
                STEP 21
                Break or return to finish
                    break;
            STEP 22
            Add elements of valList into resultList according to relative value of k and size of valList by calling method addElements(resultList, hashFreqToValList.get(idx + minFreqVal[0]), k) and assign result to updatedK
                int updatedK = addElements(resultList, hashFreqToValList.get(idx + minFreqVal[0]), k);
            
            Or int updatedK = addElements(resultList, hashFreqToValList.get(buckets[idx]), k); (because the value of buckets[idx] == (idx + minFreqVal[0]) here)
            
            STEP 23
            Update k to updatedK for next iteration
                k = updatedK;
        STEP 24
        Initialize result to new int[resultList.size()]
            int[] result = new int[resultList.size()]
        STEP 25
        Copy all elements in resultList back to result by calling method copyListToArr
            copyListToArr(result, resultList);
        STEP 26
        Return result

        -FUNC void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            Increase value by one if key nums[idx] exist, otherwise set value to 1
                hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);
                
        -FUNC void getFreqAndValList(Map<Integer, Integer> hashValToFreq, Map<Integer, List<Integer>> hashFreqToValList)
        STEP 1
        Iterate each entry of hashValToFreq with Map.Entry<Integer, Integer> valFreqPair
            STEP 2
            Get val and freq from valFreqPair
                int val = valFreqPair.getKey()
                int freq = valFreqPair.getValue();
            STEP 3
            If !hashFreqToValList.containsKey(freq), (meaning if current freq is not exist in hashFreqToValList, create a new ArrayList first)
                STEP 4
                Create a List<Integer> value under freq key
                    hashFreqToValList.put(freq, new ArrayList<>());
            STEP 5
            Get corresponding vlaue under key freq and assign it to valList
                List<Integer> valList = hashFreqToValList.get(freq);
            STEP 6
            Add val into valList
                valList.add(val);
                
        -FUNC void getMaxMinFreqVal(Map<Integer, List<Integer>> hashFreqToValList, int[] maxFreqVal, int[] minFreqVal)
        STEP 1
        Iterate each key of hashFreqToValList
            STEP 2
            If key > maxFreqVal[0], (a new maximum key is found)
                STEP 3
                Update maxFreqVal[0] to key
                    maxFreqVal[0] = key
            STEP 4
            If key < minFreqVal[0], (a new minimum key is found)
                STEP 5
                Update minFreqVal[0] to key
                    minFreqVal[0] = key
                    
        -FUNC int addElements(List<Integer> resultList, List<Integer> valList, int k)
        STEP 1
        If k > valList.size(), (meaning all elements in valList should be added into resultList)
            STEP 2
            Add all elements of valList into resultList
                resultList.addAll(valList);
            STEP 3
            Return k - valList.size() as return result
                return k - valList.size();
        
        Now all following code is for k <= valList.size()
        
        STEP 4
        Iterate idx from 0 to k - 1
            STEP 5
            Copy valList.get(idx) to resultList
                resultList.add(valList.get(idx))
        STEP 6
        Return 0 since all k eleemnt has been processed, so there is no need for following indexes of cacheArr
            return 0;
                    
        -FUNC void copyListToArr(int[] result, List<Integer> resultList)
        STEP 1
        Iterate each index of result with idx
            STEP 2
            Copy resultList.get(idx) to result[idx]
                result[idx] = resultList.get(idx);

    TIME:
        O(n + n + n + n + k) ~ O(n + k), first n is for creating hashValToFreq, second n is for creating hashFreqToValList, third n is for getting maxFreqVal and minFreqVal, fourth n is for putting all elements from hashFreqToValList to buckets, last k is for copying k top freq's valList back to result
    SPACE:
        O(n + n + M), first n is for size of hashValToFreq, second n is for size of hashFreqToValList, M is the range(max minus min) of keys in hashFreqToValList, which also the size of buckets
*/

class TopKFrequentV1 {
    
    public static int[] getTopKFrequent(int[] nums, int k) {
        // STEP 1
        Map<Integer, Integer> hashValToFreq = new HashMap<>();
        Map<Integer, List<Integer>> hashFreqToValList = new HashMap<>();
        // STEP 2
        getValAndFreq(nums, hashValToFreq);
        // STEP 3
        getFreqAndValList(hashValToFreq, hashFreqToValList);
        // STEP 4
        int[] maxFreqVal = new int[] {Integer.MIN_VALUE};
        int[] minFreqVal = new int[] {Integer.MAX_VALUE};
        // STEP 5
        getMaxMinFreqVal(hashFreqToValList, maxFreqVal, minFreqVal);
        // STEP 6
        int bucketsNum = (maxFreqVal[0] - minFreqVal[0]) + 1;
        // STEP 7
        int[] buckets = new int[bucketsNum];
        int idxBucketToPlace = -1;
        // STEP 8
        double singleBucketSize = (maxFreqVal[0] - minFreqVal[0]) / bucketsNum;
        // STEP 9
        if (singleBucketSize < 1) {
            // STEP 10
            singleBucketSize = 1.0;
        }
        // STEP 11
        for (Integer key: hashFreqToValList.keySet()) {
            // STEP 12
            idxBucketToPlace = (int) ((key - minFreqVal[0]) / singleBucketSize);
            // STEP 13
            if (idxBucketToPlace == bucketsNum) {
                // STEP 14
                idxBucketToPlace--;
            }
            // STEP 15
            buckets[idxBucketToPlace] = key;
        }
        System.out.println("hashFreqToValList: " + hashFreqToValList);
        System.out.println("buckets: " + Arrays.toString(buckets));
        // STEP 16
        List<Integer> resultList = new ArrayList<>();
        // STEP 17
        for (int idx = buckets.length - 1; idx >= 0; idx--) {
            // STEP 18
            if (buckets[idx] == 0) {
                // STEP 19
                continue;
            }
            // STEP 20
            if (k == 0) {
                // STEP 21
                break;
            }
            // STEP 22
            int updatedK = addElements(resultList, hashFreqToValList.get(idx + minFreqVal[0]), k);
            // int updatedK = addElements(resultList, hashFreqToValList.get(buckets[idx]), k);
            // STEP 23
            k = updatedK;
        }
        // STEP 24
        int[] result = new int[resultList.size()];
        // STEP 25
        copyListToArr(result, resultList);
        // STEP 26
        return result;
    }
    
    public static void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq) {
        // STEP 1
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 2
            hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);
        }
    }
    
    public static void getFreqAndValList(Map<Integer, Integer> hashValToFreq, Map<Integer, List<Integer>> hashFreqToValList) {
        // STEP 1
        for (Map.Entry<Integer, Integer> valFreqPair: hashValToFreq.entrySet()) {
            // STEP 2
            int val = valFreqPair.getKey();
            int freq = valFreqPair.getValue();
            // STEP 3
            if (!hashFreqToValList.containsKey(freq)) {
                // STEP 4
                hashFreqToValList.put(freq, new ArrayList<>());
            }
            // STEP 5
            List<Integer> valList = hashFreqToValList.get(freq);
            // STEP 6
            valList.add(val);
        }
    }
    
    public static void getMaxMinFreqVal(Map<Integer, List<Integer>> hashFreqToValList, int[] maxFreqVal, int[] minFreqVal) {
        // STEP 1
        for (Integer key: hashFreqToValList.keySet()) {
            // STEP 2
            if (key > maxFreqVal[0]) {
                // STEP 3
                maxFreqVal[0] = key;
            }
            // STEP 4
            if (key < minFreqVal[0]) {
                // STEP 5
                minFreqVal[0] = key;
            }
        }
    }

    public static int addElements(List<Integer> resultList, List<Integer> valList, int k) {
        // STEP 1
        if (k > valList.size()) {
            // STEP 2
            resultList.addAll(valList);
            // STEP 3
            return k - valList.size();
        }
        // STEP 4
        for (int idx = 0; idx < k; idx++) {
            // STEP 5
            resultList.add(valList.get(idx));
        }
        // STEP 6
        return 0;
    }

    public static void copyListToArr(int[] result, List<Integer> resultList) {
        // STEP 1
        for (int idx = 0; idx < result.length; idx++) {
            // STEP 2
            result[idx] = resultList.get(idx);
        }
    }

    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 2, 2, 3};
        // int k = 2;  // [1, 2]
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 2;  // [3, 1]
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 1;  // [3]
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 3;  // [3, 1, 2]
        
        // int[] nums = {4, 1, -1, 2, -1, 2, 3};
        // int k = 2;  // [-1, 2]
        
        int[] nums = {5, 3, 1, 1, 1, 3, 73, 1};
        int k = 2;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int[] res = getTopKFrequent(nums, k);
        System.out.println("Top " + k  + " frequent elements are: " + Arrays.toString(res));
    }
}