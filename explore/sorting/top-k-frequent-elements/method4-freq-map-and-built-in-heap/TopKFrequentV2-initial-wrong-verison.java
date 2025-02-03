import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.Comparator;
import java.util.PriorityQueue;

/*
    NOTE:
        Compared to V1, V2 use min heap instead of max heap, which need less space to maintain top k frequent elements
    VARS:
        hashValToFreq(Map<Integer, Integer>): a hash map used to record the element value and its frequency
        minHeap(Queue<Map.Entry<Integer, Integer>>): a PriorityQueue used as min heap
        result(int[]): for final return result
    DESCRIPTION:
        STEP 1
        Initialize hashValToFreq to new HashMap<>()
            Map<Integer, Integer> hashValToFreq = new HashMap<>();
        STEP 2
        Collect all elements value and their frequency by calling method getValAndFreq(nums, hashValToFreq)
            getValAndFreq(nums, hashValToFreq);    
        STEP 3
        Initialize minHeap to new PriorityQueue<>(Comparator.comparing(Map.Entry<Integer, Integer>::getValue));
            PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry<Integer, Integer>::getValue));
        STEP 4
        Iterate each entry of hashValToFreq with Map.Entry<Integer, Integer> valFreqPair
            STEP 5
            If minHeap.size() == k, (meaning size of min heap is already equal to k)
                STEP 6
                Dequeue front entry first
                    minHeap.poll();
            STEP 7
            Enqueue new entry into minHeap
                minHeap.offer(valFreqPair);
        
        Now all k top elements are stored in min heap, next copy them back to result
        
        STEP 8
        Initialize result to new int[k]
            int[] result = new int[k];
        STEP 9
        Iterate idx from 0 to k - 1,
            STEP 10
            Get entry of current front of minHeap and assign it to valFreqPair
                Map.Entry<Integer, Integer> valFreqPair = minHeap.poll();
            STEP 11
            Add key of valFreqPair into result at index idx
                result[idx] = valFreqPair.getKey();
        STEP 12
        Return result
        
        -FUNC void getValAndFreq(int[] nums, Map<Integer, Integer> hashValToFreq)
        STEP 1
        Iterate each index of nums with idx
            STEP 2
            Increase value by one if key nums[idx] exist, otherwise, set value to 1
                hashValToFreq.put(nums[idx], hashValToFreq.getOrDefault(nums[idx], 0) + 1);

    TIME:
        O(n + k + k) ~ O(n + k), first n is for creating hashValToFreq, first k is for maintaining min heap structure when adding or removing elements, final k is for copy k top frequent elements from min heap back to result
    SPACE:
        O(n + k) ~ O(n + k), n is for size of hashValToFreq, k is for size of min heap
*/

class TopKFrequentV2 {
    
    public static int[] getTopKFrequent(int[] nums, int k) {
        // STEP 1
        Map<Integer, Integer> hashValToFreq = new HashMap<>();
        // STEP 2
        getValAndFreq(nums, hashValToFreq);
        // STEP 3
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>(Comparator.comparing(Map.Entry<Integer, Integer>::getValue));
        // STEP 4
        for (Map.Entry<Integer, Integer> valFreqPair: hashValToFreq.entrySet()) {
            // STEP 5
            if (minHeap.size() == k) {
                // STEP 6
                minHeap.poll();
            }
            // STEP 7
            minHeap.offer(valFreqPair);
        }
        // STEP 8
        int[] result = new int[k];
        // STEP 9
        for (int idx = 0; idx < k; idx++) {
            // STEP 10
            Map.Entry<Integer, Integer> valFreqPair = minHeap.poll();
            // STEP 11
            result[idx] = valFreqPair.getKey();
        }
        // STEP 12
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