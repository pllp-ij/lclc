import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
    VARS:
        hashValToFreq(Map<Integer, Integer>): a hash used to map from the value of element in nums to its frequency
        hashFreqToValList(Map<Integer, List<Integer>>): a hash used to map from the frequency to elements value list, because frequent insertion operation will be done on the value of hashFreqToValList, so it's better to use List<Integer> type instead of in[]
        maxFreqVal(int): the maximum value of keys in hashFreqToValList, in all keys in hashFreqToValList to construct cacheArr
        cacheArr(int[]): array used as countArr to store the occurance of each key in hashFreqToValList
        result(int[]): for final return result
        idxResultInsertStart(int[]): the pointer array used to insert valList into result
    DESCRIPTION:
        STEP 1
        Initialize hashValToFreq to HashMap<>()
            Map<Integer, Integer> hashValToFreq = new HashMap<>();
        Initialize hashFreqToValList to HashMap<>() for reusage purpose
            Map<Integer, List<Integer>> hashFreqToValList = new HashMap<>();
        STEP 2
        Collect all elements in nums and its frequency and store in hashValToFreq
            getValAndFreq(nums, hashValToFreq);
        STEP 3
        Update hashFreqToValList from hashValToFreq by calling method getFreqAndValList
            getFreqAndValList(hashValToFreq, hashFreqToValList);
        STEP 4
        Initialize maxFreqVal to new int[] {Integer.MIN_VALUE}
            int[] maxFreqVal = new int[] {Integer.MIN_VALUE}
        Initialize minFreqVal to new int[] {Integer.MAX_VALUE}
            int[] minFreqVal = new int[] {Integer.MAX_VALUE}
        STEP 5
        Update maxFreqVal and minFreqVal by calling method getMaxMinFreqVal(hashFreqToValList, maxFreqVal, minFreqVal)
            getMaxMinFreqVal(hashFreqToValList, maxFreqVal, minFreqVal)
        STEP 6
        Initialize cacheArr to new int[(maxFreqVal[0] - minFreqVal[0]) + 1] as countArr to store the occurance of each frequency(key) in hashFreqToValList
            int[] cacheArr = new int[(maxFreqVal[0] - minFreqVal[0]) + 1];
        STEP 7
        Construct countArr by calling method constructCountArr
            constructCountArr(hashFreqToValList, cacheArr);
        
        Now all keys of hashFreqToValList have been stored in cacheArr, next find k non-zero elements in cacheArr from right most to left and add corresponding array elements  by the key equal to the idx in cacheArr
        
        STEP 8
        Initialize result to new int[k]
            int[] result = new int[k];
        Initialize idxResult to {0} as the pointer to append new array into result
            int[] idxResultInsertStart = {0};
        STEP 9
        Iterate each index of cacheArr from (cacheArr.length - 1) to 0 with idx
            STEP 10
            If cacheArr[idx] == 0, (meaning there is not any element exist in hashFreqToValList as keys equal to idx, just skip it)
                STEP 11
                Skip current index
                    continue;
            STEP 12
            If k == 0, (meaning the top k frequent elements have all been added to result, there is no need to iterate anymore)
                STEP 13
                Break or return the loop to finish
                    break;
            STEP 14
            Add all elements in List hashFreqToValList.get(idx) into result by calling method appendArr(result, hashFreqToValList.get(idx + minFreqVal[0]))
                appendArr(result, idxResultInsertStart, hashFreqToValList.get(idx + minFreqVal[0]));
            STEP 15
            Decrease k by one to indicate one of top k frequent element has been found
                k--;
        
        Now all top k frequent eleemnts of nums are stored in result, next return it
        
        STEP 15
        Return result as final return
            return result;
              
        -FUNC void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            Increase hashValToFreq by one if it exist, otherwise, set its value to 1
                hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);
                
         -FUNC void getFreqAndValList(Map<Integer, Integer> hashValToFreq, Map<Integer, List<Integer>> hashFreqToValList)
         STEP 1
         Iterate each entry of hashValToFreq with pair Map.Entry<Integer, Integer> valFreqPair
            STEP 2
            Get val and freq respectively
                int val = valFreqPair.getKey();
                int freq = valFreqPair.getValue();
            STEP 3
            If !hashFreqToValList.containsKey(freq), (meaning the freq is not exist as key in hashFreqToValList, first create ArrayList from it)
                STEP 4
                Create a new ArrayList under current key
                    hashFreqToValList.put(freq, new ArrayList<>());
            STEP 5
            Get valList by querying key value of freq and assign it to valList
                List<Integer> valList = hashFreqToValList.get(freq);
            STEP 6
            Add val into valList into hashFreqToValList
                valList.add(val);
                
        -FUNC void getMaxMinFreqVal(Map<Integer, List<Integer>> hashFreqToValList, int[] maxFreqVal, int[] minFreqVal)
        STEP 1
        Iterate each key of hashFreqToValList
            STEP 2
            If key > maxFreqVal[0], (a new maximum key is found)
                STEP 3
                Update maxFreqVal[0] with key
                    maxFreqVal[0] = key;
            STEP 4
            If key < minFreqVal[0], (a new minimum key is found)
                STEP 5
                Update minFreqVal[0] with key
                    minFreqVal[0] = key
        
        -FUNC void constructCountArr(Map<Integer, List<Integer>> hashFreqToValList, int[] cacheArr)
        STEP 1
        Iterate each key of hashFreqToValList with key
            STEP 2
            Increase cacheArr[key] by one to indicate there is one more key exist in hashFreqToValList
                (cacheArr[key])++;
              
        -FUNC void appendArr(int[] result, int[] idxResultStart, List<Integer> valList)
        STEP 1
        Iterate each index of valList with idx
            STEP 2
            Copy valList.get(idx) to result[idxResultStart[0] + idx]
                result[idxResultStart[0] + idx] = valList.get(idx);
        STEP 3
        Increase idxResultInsertStart[0] by valList.size() for next valList's insertion
            idxResultInsertStart[0] += valList.size();
        
    TIME:
        O(n + n + n + (n + M) + k) ~ O(n + M + k) ~ O(n + k), first n is for creating hashValToFreq, second n is for creating hashFreqToValList, third n is for finding the maximum key in hashFreqToValList, the last (n + M) is for counting sort, M is the range of keys in hashFreqToValList, final k is for copying k elements back to result
    SPACE:
        O(n + n + M) ~ O(n + M), first n is for the size of hashValToFreq, second n is for the size of hashFreqToValList, the M is for the size of cacheArr, which is the maximum keys in hashFreqToValList
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
        int[] cacheArr = new int[(maxFreqVal[0] - minFreqVal[0]) + 1];
        // STEP 7
        constructCountArr(hashFreqToValList, minFreqVal[0], cacheArr);
        System.out.println("cacheArr: " + Arrays.toString(cacheArr));
        System.out.println("hashFreqToValList: " + hashFreqToValList);
        // STEP 8
        int[] result = new int[k];
        int[] idxResultStart = {0};
        // STEP 9
        for (int idx = cacheArr.length - 1; idx >= 0; idx--) {
            // STEP 10
            if (cacheArr[idx] == 0) {
                // STEP 11
                continue;
            }
            // STEP 12
            if (k == 0) {
                // STEP 13
                break;
            }
            // STEP 14
            appendArr(result, idxResultStart, hashFreqToValList.get(idx + minFreqVal[0]));
            // STEP 15
            k--;
        }
        // STEP 16
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
    
    public static void constructCountArr(Map<Integer, List<Integer>> hashFreqToValList, int minFreqVal, int[] cacheArr) {
        // STEP 1
        for (Integer key: hashFreqToValList.keySet()) {
            // STEP 2
            (cacheArr[key - minFreqVal])++;
        }
    }
    
    public static void appendArr(int[] result, int[] idxResultStart, List<Integer> valList) {
        // STEP 1
        for (int idx = 0; idx < valList.size(); idx++) {
            // STEP 2
            result[idxResultStart[0] + idx] = valList.get(idx);
        }
        // STEP 3
        idxResultStart[0] += valList.size();
    }

    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 2, 2, 3};
        // int k = 2;
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 2;
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 1;
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 3;
        
        int[] nums = {4, 1, -1, 2, -1, 2, 3};
        int k = 2;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int[] res = getTopKFrequent(nums, k);
        System.out.println("Top " + k  + " frequent elements are: " + Arrays.toString(res));
    }
}