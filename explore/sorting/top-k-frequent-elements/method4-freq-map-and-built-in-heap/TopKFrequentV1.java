import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    VARS:
        hashValToFreq(Map<Integer, Integer>): a hash map used to record the element value and its frequency
        maxHeap(Queue<Map.Entry<Integer, Integer>>): a PriorityQueue used as max heap
        result(int[]): for final return result
    DESCRIPTION:
        STEP 1
        Initialize hashValToFreq to new HashMap<>();
            Map<Integer, Integer> hashValToFreq = new HashMap<>();
        STEP 2
        Collect elements value and their frequency into hashValToFreq by calling method getValAndFreq
            getValAndFreq(nums, hashValToFreq);
        STEP 3
        Initialize maxHeap as PriorityQueue<>(Comparator.comparing(Map.Entry::getValue).reversed());
            PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry::getValue).reversed());
        STEP 4
        Iterate each entry from hashValToFreq with Map.Entry<Integer, Integer> valFreqPair
            STEP 5
            Push entry valFreqPair into maxHeap
                maxHeap.offer(valFreqPair);
        
        Now all entries in hashValToFreq are stored in maxHeap, next get the k entries from maxHeap and store the key of them into result
        
        STEP 6
        Initialize result to new int[k]
            int[] result = new int[k];
        STEP 7
        Iterate idx from 0 to k - 1
            STEP 8
            Dequeue entry from hashValToFreq and assign it to Map.Entry<Integer, Integer> valFreqPair
                Map.Entry<Integer, Integer> valFreqPair = maxHeap.poll();
            STEP 9
            Add val into result with idx
                result[idx] = valFreqPair.getKey();
        STEP 10
        Return result
        
        -FUNC void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            Increase value under current key idx by one if it exist, otherwise, set it to 1
                hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);

    TIME:
        O(n + nlogn + k) ~ O(nlogn + k), first n is for creating hashValToFreq, nlogn is for maintaining max heap structure when adding or removing elements, final k is for copy k top frequent elements at the front of max heap back to result
    SPACE:
        O(n + n) ~ O(n), first n is for size of hashValToFreq, second n is for size of max heap
*/

class TopKFrequentV1 {
    
    public static int[] getTopKFrequent(int[] nums, int k) {
        // STEP 1
        Map<Integer, Integer> hashValToFreq = new HashMap<>();
        // STEP 2
        getValAndFreq(nums, hashValToFreq);
        // STEP 3
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry<Integer, Integer>::getValue).reversed());
        // STEP 4
        for (Map.Entry<Integer, Integer> valFreqPair: hashValToFreq.entrySet()) {
            // STEP 5
            maxHeap.offer(valFreqPair);
        }
        // STEP 6
        int[] result = new int[k];
        // STEP 7
        for (int idx = 0; idx < k; idx++) {
            // STEP 8
            Map.Entry<Integer, Integer> valFreqPair = maxHeap.poll();
            // STEP 9
            result[idx] = valFreqPair.getKey();
        }
        // STEP 10
        return result;
    }
    
    public static void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq) {
        // STEP 1
        for (int idx = 0; idx < nums.length; idx++) {
            // STEP 2
            hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;  // [1, 2]
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 2;  // [3, 1]
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 1;  // 3
        
        // int[] nums = {1, 1, 2, 3, 3, 3};
        // int k = 3;  // [3, 1, 2]
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int[] res = getTopKFrequent(nums, k);
        System.out.println("Top " + k  + " frequent elements are: " + Arrays.toString(res));
    }
}