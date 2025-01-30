import java.util.Arrays;    
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
    NOTE:
        A question is left: whether can or not to directly adjust nums with the help of countArr or startIndexArr without using sortedArr ? The example list as follow
            [2, 3, 1, 4, 2, 2, 1, 1, 3]

            0   1   2   3   4  val
                3   3   2   1  countArr
                0   3   6   8  startIndexArr
    VARS:
        cacheArr(int[]): used as counting array to store the occurance of each digit under iterated idxDigit, then used as startIndexArr to store the start index of each element of nums in sorted array
        sortedArr(int): for storing all strings of nums after sorted under each idxDigit
        k(int): the kth smallest trimmed subtring after sorted
        trimLen(int): the length of trimmed subtring from right end to left
        result(int[]): for final return result with same size of queries
        hash(Map<Integer, int[]>): a hash map to map the digit value of each iterated idxDigit to the index in nums for reversal retrival 
    DESCRIPTION:
        STEP 1
        Initialize cacheArr to new int[10] for reusage purpose
            int[] cacheArr = new int[10]
        Initialize sortedArr to new String[nums.length]
            String[] sortedArr = new String[nums.length]
        STEP 2
        Initialize result to new int[queries.length]
            int[] result = new int[queries.length]
        Initialize hash to HashMap<>() for reusage purpose
            Map<Integer, List<Integer>> hash = new HashMap<>();
        STEP 3
        Iterate each index of queries with idxQuery
            STEP 4
            Get k and trimLen from queries and idxQuery
                int k = queries[idxQuery][0];
                int trimLen = queries[idxQuery][1]
            STEP 5
            Iterate idxDigit from 0 to (trimLen - 1) to do counting sorted
                STEP 6
                Empty cacheArr(sortedArr has no need to be emptied) and hash for each digit first
                    Arrays.fill(cacheArr, 0)
                    hash.clear();
                STEP 7
                Sort under current digit with idxDigit by calling method countingSort, note that in last counting sort here a hash map will be used to record the initial index in nums in an array and add it into result
                    countingSort(nums, idxDigit, cacheArr, sortedArr, result, hash, k, idxDigit == trimLen - 1);
                    
        -FUNC void countingSort(String[] nums, int idxDigit, int[] cacheArr, String[] sortedArr, int[] result, Map<Integer, List<Integer>> hash, int k, boolean isLastDigit)
        STEP 1
        Update cacheArr as countArr by calling method constructCountArr under current idxDigit, pass two additional parameters result and trimLen to process special case in last digit
            constructCountArr(nums, idxDigit, cacheArr, result, hash, k, isLastDigit);
        STEP 2
        If isLastDigit, (meaning this is the last digit of trimmed substring, there is no need to do following logic, so directly return)
            STEP 3
            Directly return to finish
                return;
                
        Now following logic is only for digits except last one
                
        STEP 3
        Update cacheArr as startIndexArr by calling method constructStartIndexArr
            constructStartIndexArr(cacheArr);
        STEP 4
        Update sortedArr by calling method constructSortedArr
            constructSortedArr(nums, idxDigit, cacheArr, sortedArr);
        STEP 5
        Copy sortedArr back to nums for next digit
            copy(sortedArr, nums)
            
        -FUNC void constructCountArr(String[] nums, int idxDigit, int[] cacheArr, int[] result, Map<Integer, List<Integer>> hash, int k, boolean isLastDigit)
        STEP 1
        Initialize curDigitVal to -1 for reusage purpose to store current digit value under idxDigit of nums
            int curDigitVal = -1;
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get curDigitVal from nums[idx] and idxDigit
                curDigitVal = (Integer.parseInt(nums[idx]) / (int) Math.pow(10, idxDigit)) % 10;
            STEP 4
            Increase cacheArr[curDigitVal] by one to indicate there is one more element exist in nums
                (cacheArr[curDigitVal])++;
            STEP 5
            If isLastDigit
                If hash.containsKey(curDigitVal), (meaning there is already a key exist)
                    STEP 7
                    Directly add idx to hash.get(curDigitVal)
                        hash.get(curDigitVal).add(idx);
                Else, (meaning there is a key created)
                    STEP 8
                    Create a new array under current key first
                        hash.put(curDigitVal, new ArrayList<>());
                    STEP 9
                    Add idx to the created new array
                        hash.get(curDigitVal).add(idx);
        STEP 10
        If isLastDigit, (meaning this is the last digit of counting sort, there is a need to find kth smallest element's index and add it into result)
            STEP 6
            Add corresponding kth smallest element index in initial nums to result by calling method addKthIdx
                addKthIdx(cacheArr, result, hash, k)
        
        -FUNC void addKthIdx(int[] cacheArr, int[] result, Map<Integer, List<Integer>> hash, int k)
        STEP 1
        Initialize beforeAllSum to 0 to store all elements before current index
            int beforeAllSum to 0
        Initialize initialToBeAdded to -1 for reusage purpose
            int initialToBeAdded = -1;
        Initialize idxOfKth to 0
            int idxOfKth = 0;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element that equal to idx in initial nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            If k > beforeAllSum and k <= beforeAllSum + cacheArr[idx], (meaning kth element is at current idx)
                STEP 6
                Get the index in hash array of kth smallest element start from current index idx (the index value should minus one) and add it in 
                    result.add(hash.get(idx).get(k - beforeAllSum - 1))
            STEP 7
            Store initial value at idx of cacheArr and assign it to initialToBeAdded
                initialToBeAdded = cacheArr[idx]
            STEP 8
            Update cacheArr[idx] with beforeAllSum to store the sum of all elements before current index
                cacheArr[idx] = beforeAllSum;
            STEP 9
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded
        
        -FUNC void constructStartIndexArr(int[] cacheArr)
        STEP 1
        Initialize beforeAllSum to 0 for reusage purpose to store all elements before current index
            int beforeAllSum = 0;
        Initialize initialToBeAdded to -1 for reusage purpose to store initial value of current index
            int initialToBeAdded = -1;
        STEP 2
        Iterate each index of cacheArr with idx
            STEP 3
            If cacheArr[idx] == 0, (meaning there is not any element equal to idx in initial nums)
                STEP 4
                Skip current iteration
                    continue;
            STEP 5
            Store initial value to initialToBeAdded for later adding operation
                initialToBeAdded = cacheArr[idx]
            STEP 6
            Update cacheArr[idx] with beforeAllSum to indicate the start index in sorted array
                cacheArr[idx] = beforeAllSum
            STEP 7
            Add initialToBeAdded to beforeAllSum for next valid index
                beforeAllSum += initialToBeAdded
        
        -FUNC void constructSortedArr(String[] nums, int idxDigit, int[] cacheArr, String[] sortedArr)
        STEP 1
        Initialize curDigitVal to -1 for reusage purpose to get the value of current digit
            int curDigitVal = -1;
        Initialize idxToInsertInSortedArr to -1 for reusage purpose to indicate the index to insert sorted elements
            int idxToInsertInSortedArr = -1
        STEP 2
        Iterate each index of nums with idx
            STEP 3
            Get corresponing digit value from nums[idx] and idxDigit
                curDigitVal = (Integer.parseInt(nums[idx]) / (int) Math.pow(10, idxDigit)) % 10
            STEP 4
            Get idxToInsertInSortedArr from cacheArr[curDigitVal]
                idxToInsertInSortedArr = cacheArr[curDigitVal];
            STEP 5
            Insert nums[idx] into the index of idxToInsertInSortedArr of sortedArr
                sortedArr[idxToInsertInSortedArr] = nums[idx]
            STEP 6
            Increase cacheArr[curDigitVal] by one for next duplicated element
                (cacheArr[curDigitVal])++;
        
        -FUNC void copy(String[] sortedArr, String[] nums)
        STEP 1
        Iterate each index of sortedArr with idx
            STEP 2
            Copy sortedArr[idx] to nums[idx]
                nums[idx] = sortedArr[idx];
        
    TIME:
        O(m * (n + 10)), m is the size of queries, n is the size of nums, 10 is the size of cacheArr
    SPACE:
        O(n + k + 10) ~ O(n + k), first n is for size of sortedArr, first k is for size of cacheArr, second k is for hash map used at last trimmed digit 
*/

class QueryKthSmallestTrimmedNumV1Debug {
    
    public static int[] query(String[] nums, int[][] queries) {
        // STEP 1
        int[] cacheArr = new int[10];
        String[] sortedArr = new String[nums.length];
        // STEP 2
        int[] result = new int[queries.length];
        int[] idxResult = {0};
        Map<Integer, List<Integer>> hash = new HashMap<>();
        // STEP 3
        for (int idxQuery = 0; idxQuery < queries.length; idxQuery++) {
            if (idxQuery == 3) {
                break;
            }
            if (idxQuery == 2) {
                System.out.println("start nums at idxQuery=" + idxQuery + " is: " + Arrays.toString(nums));
            }
            hash.clear();
            // STEP 4
            int k = queries[idxQuery][0];
            int trimLen = queries[idxQuery][1];
            
            // STEP 5
            for (int idxDigit = 0; idxDigit < trimLen; idxDigit++) {
                Arrays.fill(cacheArr, 0);
                // STEP 6
                countingSort(nums, idxQuery, idxDigit, cacheArr, sortedArr, result, idxResult, hash, k, idxDigit == trimLen - 1);
            }
        }
        return result;
    }
    
    public static void countingSort(String[] nums, int idxQuery, int idxDigit, int[] cacheArr, String[] sortedArr, int[] result, int[] idxResult, Map<Integer, List<Integer>> hash, int k, boolean isLastDigit) {
        if (idxQuery == 2 && idxDigit == 1) {
            System.out.println("start nums at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(nums));
            System.out.println("start countArr at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(cacheArr));
        }
        // STEP 1
        constructCountArr(nums, idxQuery, idxDigit, cacheArr, result, idxResult, hash, k, isLastDigit);
        
        // STEP 2
        if (isLastDigit) {
            // STEP 3
            return;
        }
        // STEP 4
        constructStartIndexArr(cacheArr);
        if (idxQuery == 2 && idxDigit == 1) {
            System.out.println("startIndex array at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(cacheArr));
        }
        // STEP 5
        constructSortedArr(nums, idxDigit, cacheArr, sortedArr);
        if (idxQuery == 2 && idxDigit == 1) {
            System.out.println("sorted array at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(sortedArr));
        }
        // STEP 6
        copy(sortedArr, nums);
        if (idxQuery == 2 && idxDigit == 1) {
            System.out.println("nums at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(nums));
        }
    }
    
    public static void constructCountArr(String[] nums, int idxQuery, int idxDigit, int[] cacheArr, int[] result, int[] idxResult, Map<Integer, List<Integer>> hash, int k, boolean isLastDigit) {
        // STEP 1
        int curDigitVal = -1;
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = (Integer.parseInt(nums[idx]) / (int) Math.pow(10, idxDigit)) % 10;
            // STEP 4
            (cacheArr[curDigitVal])++;
            // STEP 5
            if (isLastDigit) {
                // STEP 6
                if (hash.containsKey(curDigitVal)) {
                    // STEP 7
                    hash.get(curDigitVal).add(idx);
                } else {
                    // STEP 8
                    hash.put(curDigitVal, new ArrayList<>());
                    // STEP 9
                    hash.get(curDigitVal).add(idx);
                }
            }
        }
        if (idxQuery == 2 && idxDigit == 1) {
            System.out.println("countArr within constructCountArr at idxQuery=" + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(cacheArr));
            System.out.println("hash within constructCountArr at idxQuery="     + idxQuery + ", idxDigit=" + idxDigit + " is: " + hash);
            System.out.println("result within constructCountArr at idxQuery="     + idxQuery + ", idxDigit=" + idxDigit + " is: " + Arrays.toString(result));
        }
        // STEP 10
        if (isLastDigit) {
            // STEP 11
            addKthIdx(cacheArr, result, idxResult, hash, k, idxQuery, idxDigit);
        }
    }
    
    public static void addKthIdx(int[] cacheArr, int[] result, int[] idxResult, Map<Integer, List<Integer>> hash, int k, int idxQuery, int idxDigit) {
        // STEP 1
        int beforeAllSum = 0;
        int initialToBeAdded = -1;
        // System.out.println("in last digit, countArr: " + Arrays.toString(cacheArr));
        // System.out.println("in last digit, hash: " + hash);
        // STEP 2
        for (int idx = 0; idx < cacheArr.length; idx++) {
            // STEP 3
            if (cacheArr[idx] == 0) {
                // STEP 4
                continue;
            }
            // STEP 5
            if (k > beforeAllSum && k <= beforeAllSum + cacheArr[idx]) {
                if (idxQuery == 2 && idxDigit == 1) {
                    System.out.println("idx: " + idx);
                }
                
                // System.out.println("k: " + k);
                // System.out.println("beforeAllSum: " + beforeAllSum);
                // System.out.println("cacheArr[idx]: " + cacheArr[idx]);
                // System.out.println("hash.get(idx): " + hash.get(idx));
                // System.out.println("hash.get(idx).get(k - beforeAllSum - 1): " + hash.get(idx).get(k - beforeAllSum - 1));
                // System.out.println("result: " + Arrays.toString(result));
                // STEP 6
                result[(idxResult[0])++] = hash.get(idx).get(k - beforeAllSum - 1);
                // STEP 7
                break;
            }
            // STEP 7
            initialToBeAdded = cacheArr[idx];
            // STEP 8
            cacheArr[idx] = beforeAllSum;
            // STEP 9
            beforeAllSum += initialToBeAdded;
        }
        // System.out.println("addKthIdx finished");
    }
    
    public static void constructStartIndexArr(int[] cacheArr) {
        // STEP 1
        int beforeAllSum = 0;
        int initialToBeAdded = -1;
        int curDigitVal = -1;
        // STEP 2
        for (int idx = 0; idx < cacheArr.length; idx++) {
            // STEP 3
            if (cacheArr[idx] == 0) {
                // STEP 4
                continue;
            }
            // STEP 5
            initialToBeAdded = cacheArr[idx];
            // STEP 6
            cacheArr[idx] = beforeAllSum;
            // STEP 7
            beforeAllSum += initialToBeAdded;
        }
    }
    
    public static void constructSortedArr(String[] nums, int idxDigit, int[] cacheArr, String[] sortedArr) {
        // STEP 1
        int idxToInsertInSortedArr = -1;
        int curDigitVal = -1;
        // System.out.println("nums: " + Arrays.toString(nums));
        // System.out.println("cacheArr: " + Arrays.toString(cacheArr));
        // System.out.println("sortedArr: " + Arrays.toString(sortedArr));
        // STEP 2
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 3
            curDigitVal = (Integer.parseInt(nums[idx]) / (int) Math.pow(10, idxDigit)) % 10;
            // STEP 4
            idxToInsertInSortedArr = cacheArr[curDigitVal];
            // STEP 5
            sortedArr[idxToInsertInSortedArr] = nums[idx];
            // STEP 6
            (cacheArr[curDigitVal])++;
        }
    }
    
    public static void copy(String[] sortedArr, String[] nums) {
        // STEP 1
        for (int idx = 0; idx < sortedArr.length; idx++) {
            // STEP 2
            nums[idx] = sortedArr[idx];
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
        
        System.out.println("initial string nums: " + Arrays.toString(nums));
        System.out.println("initial queries: " + Arrays.deepToString(queries));
        
        int[] result = query(nums, queries);
        System.out.println("final result: " + Arrays.toString(result));
    }
}