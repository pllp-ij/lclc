import java.util.Arrays;    
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
    VARS:
        cacheArr(int[]): a cache array used as the count array under each query
        hash(Mpa<Integer, List<Integer>>): a hash map to record the index in initial nums for later retrieval
        result(int[]): for storing final result with the same size of queries
        k(int): indicate the kth smallest element
        trimLen(int): the length used to trim each element of nums        
    DESCRIPTION:
        STEP 1
        Initialize hash to Map<Integer, List<Integer>> for reusage purpose of each query
            Map<Integer, List<Integer>> hash = new HashMap<>();
        Initialize result to new int[queries.length];
            int[] result = new int[queries.length];
        STEP 2
        Iterate each index of queries with idxQuery
            STEP 3
            Reset hash for each iterated query
                hash.clear();
            STEP 4
            Get k and trimLen from current query array
                int k = queries[idxQuery][0]
                int trimLen = queries[idxQuery][1]
            STEP 5
            Get maximum value of trimmed nums with trimLen by calling method getMaxVal
                int maxVal = getMaxVal(String[] nums, trimLen)
            STEP 6
            Initialize cacheArr with size of (maxVal + 1) for each query
                int[] cacheArr = new int[maxVal + 1];
            STEP 7
            Do counting sort by calling method countingSort
                countingSort(nums, trimLen, cacheArr, hash, result, idxQuery, k);
        STEP 8
        Return result
        
        -FUNC int getMaxVal(String[] nums, int trimLen)
        STEP 1
        Initialize maxVal to Integer.MIN_VALUE
            int maxVal = Integer.MIN_VALUE;
        Initialize curTrimmedVal to -1 for reusage to store the value of trimmed length
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curTrimmedVal with nums and trimLen
                curTrimmedVal = Integer.parseInt(nums[idx]) % (int) Math.pow(10, trimLen);
            STEP 4
            If curTrimmedVal > maxVal, (a new mximum element is found)
                STEP 5
                Update maxVal to curTrimmedVal
                    maxVal = curTrimmedVal;
        STEP 6
        Return maxVal as final result
            return maxVal;
            
        -FUNC void countingSort(String[] nums, int trimLen, int[] cacheArr, Map<Integer, List<Integer>> hash, int[] result, int idxQuery, int k)
        STEP 1
        Update cacheArr as countArr and create hash mapping relationship in this process by calling method constructCountArr
            constructCountArr(nums, trimLen, cacheArr, hash);
        STEP 2
        Get kth smallest element from cacheArr and add it into result by calling method addKthIdx
            addKthIdx(cacheArr, hash, result, idxQuery, k);
                
        -FUNC void constructCountArr(String[] nums, int trimLen, int[] cacheArr, Map<Integer, List<Integer>> hash)
        STEP 1
        Initialize curTrimmedVal to -1 for reusage purpose to store the current trimmed value of each element of nums
            int curTrimmedVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curTrimmedVal with nums and idx
                curTrimmedVal = Integer.parseInt(nums[idx]) % (int) Math.pow(10, trimLen);
            STEP 4
            Increase cacheArr[curTrimmedVal] by one to indicate there is one more element exist in initial nums
                (cacheArr[curTrimmedVal])++;
            
            Now there is a need to construct hash map relationship for each element of nums, the following part logic and above part logic is distinct with each other, so the order of them does not matter
            
            STEP 5
            If hash.containsKey(curTrimmedVal), (meaning there is already a key exist for current idx)
                STEP 6
                Directly add idx into hash.get(curTrimmedVal)
                    hash.get(curTrimmedVal).add(idx);
            Else, (meaning there is not a hash key exist, so firstly need to create it and add idx into it)
                STEP 7
                Create a new List object as value with current key curTrimmedVal
                    hash.put(curTrimmedVal, new ArrayList<>());
                STEP 8
                Add idx into hash.get(curTrimmedVal)
                    hash.get(curTrimmedVal).add(idx);
                
        -FUNC void addKthIdx(String[] cacheArr, Map<Integer, List<Integer>> hash, int[] result, int idxQuery, int k)
        STEP 1
        Initialize beforeAllSum to 0 for reusage purpose to store all elements before current index
            int beforeAllSum = 0
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of current index
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is no such element in nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            If k  - beforeAllSum > 0 and k - beforeAllSum <= cacheArr[idx], (meaning the kth smallest element will located in current index, note the the index of array in hash must be substracted by one)
                STEP 6
                Add the kth element at current index with value of hash.get(idx).get(k - beforeAllSum - 1)
                    result[idxQuery] = hash.get(idx).get(k - beforeAllSum - 1);
                STEP 7
                Since the kth element is already added into result, break or return to finish
                    break;
            
            Now to do normal updation with each non-zero element in nums[idx]
            
            STEP 8
            Store initial value at current index of cacheArr and assign it to initialToBeAdded for later adding operation
                initialToBeAdded = cacheArr[idx];
            STEP 9
            Update cacheArr[idx] with beforeAllSum to indicate the start index in sorted array
                cacheArr[idx] = beforeAllSum;
            STEP 10
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded;

    TIME:
        O(m * (n + k)), m is the size of queries, n is for find the maximum value of nums under each trimmed length, k is the size of cacheArr for each query to find the kth smallest element
    SPACE:
        O(n + k), n is for size of hash map, k is for size of cacheArr
*/

class QueryKthSmallestTrimmedNumV1 {
    
    public static int[] query(String[] nums, int[][] queries) {
        // STEP 1
        Map<Integer, List<Integer>> hash = new HashMap<>();
        int[] result = new int[queries.length];
        // STEP 2
        for (int idxQuery = 0; idxQuery < queries.length; idxQuery++) {
            // STEP 3
            hash.clear();
            // STEP 4
            int k = queries[idxQuery][0];
            int trimLen = queries[idxQuery][1];
            // STEP 5
            int maxVal = getMaxVal(nums, trimLen);
            // STEP 6
            int[] cacheArr = new int[maxVal + 1];
            // STEP 7
            countingSort(nums, trimLen, cacheArr, hash, result, idxQuery, k);
        }
        // STEP 8
        return result;
    }
    
    public static int getMaxVal(String[] nums, int trimLen) {
        // STEP 1
        int maxVal = Integer.MIN_VALUE;
        int curTrimmedVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curTrimmedVal = Integer.parseInt(nums[idx]) % (int) Math.pow(10, trimLen);
            // STEP 4
            if (curTrimmedVal > maxVal) {
                // STEP 5
                maxVal = curTrimmedVal;
            }
        }
        // STEP 6
        return maxVal;
    }
    
    public static void countingSort(String[] nums, int trimLen, int[] cacheArr, Map<Integer, List<Integer>> hash, int[] result, int idxQuery, int k) {
        // STEP 1
        constructCountArr(nums, trimLen, cacheArr, hash);
        // STEP 2
        addKthIdx(cacheArr, hash, result, idxQuery, k);
    }
    
    public static void constructCountArr(String[] nums, int trimLen, int[] cacheArr, Map<Integer, List<Integer>> hash) {
        // STEP 1
        int curTrimmedVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curTrimmedVal = Integer.parseInt(nums[idx]) % (int) Math.pow(10, trimLen);
            // STEP 4
            (cacheArr[curTrimmedVal])++;
            // STEP 5
            if (hash.containsKey(curTrimmedVal)) {
                // STEP 6
                hash.get(curTrimmedVal).add(idx);
            } else {
                // STEP 7
                hash.put(curTrimmedVal, new ArrayList<>());
                // STEP 8
                hash.get(curTrimmedVal).add(idx);
            }
        }
    }
    
    public static void addKthIdx(int[] cacheArr, Map<Integer, List<Integer>> hash, int[] result, int idxQuery, int k) {
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
            if (k - beforeAllSum > 0 && k - beforeAllSum <= cacheArr[idx]) {
                // STEP 6
                result[idxQuery] = hash.get(idx).get(k - beforeAllSum - 1);
                // STEP 7
                break;
            }
            // STEP 8
            initialToBeAdded = cacheArr[idx];
            // STEP 9
            cacheArr[idx] = beforeAllSum;
            // STEP 10
            beforeAllSum += initialToBeAdded;
        }
    }
    
    public static void main(String[] args) {
        String[] nums = {"102", "473", "251", "814"};
        int[][] queries = {
            {1, 1},
            {2, 3},
            {4, 2},
            {1, 2}
        };
        
        // String[] nums = {"24", "37", "96", "04"};
        // int[][] queries = {
            // {2, 1},
            // {2, 2}
        // };
        
        // String[] nums = {"64333639502", "65953866768", "17845691654", "87148775908", "58954177897", "70439926174", "48059986638", "47548857440", "18418180516", "06364956881", "01866627626", "36824890579", "14672385151", "71207752868"};
        // int[][] queries = {
            // {9, 4},
            // {6, 1},
            // {3, 8},
            // {12, 9},
            // {11, 4},
            // {4, 9},
            // {2, 7},
            // {10, 3},
            // {13, 1},
            // {6, 1},
            // {5, 10}
        // };
        
        System.out.println("initial string nums: " + Arrays.toString(nums));
        System.out.println("initial queries: " + Arrays.deepToString(queries));
        
        int[] result = query(nums, queries);
        System.out.println("final result: " + Arrays.toString(result));
    }
}