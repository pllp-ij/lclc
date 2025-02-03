import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
    VARS:
        result(int[]): the final return result
        hash(Map<Integer, Integer>): the hash map to store element value and its frequency
        hashList(List<Map.Entry<Integer, Integer>>): the list version of hash map for heap sort
        idxHeapRangeEnd(int): the last index of heap range currently inspect
        idxParentNodeEnd(int): the last index of parent node in currently inspected heap range
        idxParentNodeStart(int): the start index of parent node from bottom to top of the range that should adjust the parent nodes
        idxParentNode(int): the iterated pointer from top to bottom to iterate all parent nodes that should be adjust according to heap restriction
    DESCRIPTION:
        STEP 1
        Initialize hash to HashMap<>()
            Map<Integer, Integer> hash = new HashMap<>();
        Initialize result to new int[k]
            int[] result = new int[k];
        STEP 2
        Collect all element's value and its frequency and store them in hash by calling method
            getValAndFreq(nums, hash);
        STEP 3
        Convert hash to hashList for sort convenience
            List<Map.Entry<Integer, Integer>> hashList = new ArrayList<>(hash.EntrySet());
        STEP 4
        Do heap sort on hashList with nums and k
            heapSort(hashList, k);
            
        Now all top k frequent elements are stored in last k elements of hashList, next copy them back to result to return
        
        STEP 5
        Iterate idx from 0 to k - 1
            STEP 6
            Get current element value(not freq value) in hashList and assign it to val
                int val = hashList.get(hashList.size() - (idx + 1)).getkey();
            STEP 7
            Copy val into result[idx]
                result[idx] = val;
        STEP 8
        Return result as final result
        
        -FUNC void getValAndFreq(int[] nums, Map<Integer, Integer> hash)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            Increase freq value with idx by one if it exist or set it to one if it already exist
                hash.put(nums[idx], hash.getOrDefault(nums[idx], 0) + 1);
        
        -FUNC void heapSort(List<Map.Entry<Integer, Integer>> hashList, int k)
        STEP 1
        Iterate idxHeapRangeEnd from (hashList.size() - 1) to (hashList.size() - k)
            STEP 2
            Do heap sort on current inspected range from 0 to idxHeapRangeEnd
                heapify(hashList, idxHeapRangeEnd);
            STEP 3
            Swap elements at 0 and idxHeapRangeEnd of hashList
                swap(hashList, 0, idxHeapRangeEnd);
             
        -FUNC void heapify(List<Map.Entry<Integer, Integer>> hashList, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 without setting updation conditions in for loop
            STEP 3
            Adjust parent nodes within range from idxParentNodeStart to idxParentNodeEnd by calling recursive method heapifyCore, which is the core logic in heapify function
                heapifyCore(hashList, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            STEP 4
            If idxHeapRangeEnd == hashList.size() - 1, (meaning this is the first time to do heapify, so all idxParentNodeStart from idxParentNodeEnd to 0 should be processed one by one)
                STEP 5
                Decrease idxParentNodeStart by one for next left parent node
                    idxParentNodeStart--;
            Else, (meaning this is not the first time to do heapify, so only update parent nodes along the largest path)
                STEP 6
                If idxParentNodeStart == 0 and (idxParentNodeStart - 1) / 2 == 0, (meaning after all parent nodes are processed from index 0, the loop should be stopped)
                    STEP 7
                    Break or return to finish
                        break;
                STEP 8
                Update idxParentNodeStart to (idxParentNodeStart - 1) / 2 for next parent node along largest path
                    idxParentNodeStart = (idxParentNodeStart - 1) / 2;
        
        -FUNC void heapifyCore(List<Map.Entry<Integer, Integer>> hashList, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current index is not parent node anyone, direcly return)
            STEP 2
            Directly return the recursive function
        STEP 3
        Initialize idxLargest to idxParentNode in default to assume the largest element in sub structure is the parent node
            int idxLargest = idxParentNode;
        STEP 4
        Calculate idxLeftChild and idxRightChild by idxParentNode
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxRightChild = 2 * idxParentNode + 2;
        STEP 5
        If idxLeftChild <= idxHeapRangeEnd and hashList.get(idxLeftChild).getValue() > hashList.get(idxLargest).getValue(), (meaning left child is within valid range and its freq value is larger than its parent node)
            STEP 6
            Update idxLargest to idxLeftChild
                idxLargest = idxLeftChild
        STEP 7
        If idxRightChild <= idxHeapRangeEnd and hashList.get(idxRightChild).getValue() > hashList.get(idxLargest).getValue(), (meaning right child is within valid range and its freq value is larger than last largest node)
            STEP 8
            Update idxLargest to idxRightChild
                idxLargest = idxRightChild;
        STEP 9
        If idxLargest != idxParentNode, (meaning the largest element is not the parent node in sub structure)
            STEP 10
            Swap two elements at index idxParentNode and idxLargest of hashList
                swap(hashList, idxParentNode, idxLargest);
            STEP 11
            Recursively adjust at next index with value of idxLargest
                heapifyCore(hashList, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);
        
        -FUNC void swap(List<Hash.Entry<Integer, Integer>> hashList, int i, int j) 
        STEP 1
        Initialize temp to hashList.get(i) with Map.Entry<Integer, Integer> type
            Map.Entry<Integer, Integer> temp = hashList.get(i);
        STEP 2
        Copy hashList.get(j) to Entry at inde i
            hashList.set(i, hashList.get(j))
        STEP 3
        Copy temp back to Entry at j
            hashList.set(j, temp);
        
    TIME:
        O(n + n + nlogn + k) ~ O(nlogn + k), first n is for creating freq map, second n is for converting hash to hashList, nlogn is for heap sort, k is for copying top k frequent elements back to result
    SPACE:
        O(n + n) ~ O(n), n is for the size of hash map, second n is for the size of hashList
*/

class TopKFrequentV1 {
    
    public static int[] getTopKFrequent(int[] nums, int k) {
        // STEP 1
        Map<Integer, Integer> hash = new HashMap<>();
        int[] result = new int[k];
        // STEP 2
        getValAndFreq(nums, hash);
        // STEP 3
        List<Map.Entry<Integer, Integer>> hashList = new ArrayList<>(hash.entrySet());
        // STEP 4
        heapSort(hashList, k);
        // STEP 5
        for (int idx = 0; idx < k; idx++) {
            // STEP 6
            int val = hashList.get(hashList.size() - (idx + 1)).getKey();
            // STEP 7
            result[idx] = val;
        }
        // STEP 8
        return result;
    }
    
    public static void getValAndFreq(int[] nums, Map<Integer, Integer> hash) {
        // STEP 1
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 2
            hash.put(nums[idx], hash.getOrDefault(nums[idx], 0) + 1);
        }
    }
    
    public static void heapSort(List<Map.Entry<Integer, Integer>> hashList, int k) {
        // STEP 1
        for (int idxHeapRangeEnd = hashList.size() - 1; idxHeapRangeEnd >= hashList.size() - k; idxHeapRangeEnd--) {
            // STEP 2
            heapify(hashList, idxHeapRangeEnd);
            // STEP 3
            swap(hashList, 0, idxHeapRangeEnd);
        }
    }
    
    public static void heapify(List<Map.Entry<Integer, Integer>> hashList, int idxHeapRangeEnd) {
        // STEP 1
        int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        // STEP 2
        for (int idxParentNodeStart = idxParentNodeEnd; idxParentNodeStart >= 0;) {
            // STEP 3
            heapifyCore(hashList, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            // STEP 4
            if (idxHeapRangeEnd == hashList.size() - 1) {
                // STEP 5
                idxParentNodeStart--;
            } else {
                // STEP 6
                if (idxParentNodeStart == 0 && (idxParentNodeStart - 1) / 2 == 0) {
                    // STEP 7
                    break;
                }
                // STEP 8
                idxParentNodeStart = (idxParentNodeStart - 1) / 2;
            }
        }
    }
    
    public static void heapifyCore(List<Map.Entry<Integer, Integer>> hashList, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd) {
        // STEP 1
        if (idxParentNode > idxParentNodeEnd) {
            // STEP 2
            return;
        }
        // STEP 3
        int idxLargest = idxParentNode;
        // STEP 4
        int idxLeftChild = 2 * idxParentNode + 1;
        int idxRightChild = 2 * idxParentNode + 2;
        // STEP 5
        if (idxLeftChild <= idxHeapRangeEnd && hashList.get(idxLeftChild).getValue() > hashList.get(idxLargest).getValue()) {
            // STEP 6
            idxLargest = idxLeftChild;
        }
        // STEP 7
        if (idxRightChild <= idxHeapRangeEnd && hashList.get(idxRightChild).getValue() > hashList.get(idxLargest).getValue()) {
            // STEP 8
            idxLargest = idxRightChild;
        }
        // STEP 9
        if (idxLargest != idxParentNode) {
            // STEP 10
            swap(hashList, idxParentNode, idxLargest);
            // STEP 11
            heapifyCore(hashList, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);
        }
    }
    
    public static void swap(List<Map.Entry<Integer, Integer>> hashList, int i, int j) {
        // STEP 1
        Map.Entry<Integer, Integer> temp = hashList.get(i);
        // STEP 2
        hashList.set(i, hashList.get(j));
        // STEP 3
        hashList.set(j, temp);
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 2, 2, 3};
        // int k = 2;
        
        int[] nums = {1, 1, 2, 3, 3, 3};
        int k = 2;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int[] res = getTopKFrequent(nums, k);
        System.out.println("Top " + k  + " frequent elements are: " + Arrays.toString(res));
    }
}