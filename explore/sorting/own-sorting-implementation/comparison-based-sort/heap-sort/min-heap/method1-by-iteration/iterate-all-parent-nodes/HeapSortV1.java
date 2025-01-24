import java.util.Arrays;

/*
    VARS:
        idxHeapRangeEnd(int): the last index of each iterated heap range, from (nums.length - 1) to 1
        idxParentNdoeEnd(int): the last parent node index of each shrinked heap range
        idxParentNodeStart(int): the pointer to iterate from bottom to top, which also indicate the start index of range to do heapification operation
        idxParentNode(int): the pointer to iterate from top to bottom
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to 1
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd by calling method
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap first smallest element of heapified nums with last element
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxHeapRangeEnd to 0 one by one
            STEP 3
            Heapify nums with range from idxParentNodeStart to idxParentNdoeEnd by calling method heapifyCore
                heafipyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
                
        -FUNC void heapifyCore(int[] nums, int idxParentNodeStart, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        Iterate idxParentNode from idxParentNodeStart to idxParentNodeEnd
            STEP 2
            Initialize idxSmallest to idxParentNode in default
                int idxSmallest = idxParentNode;
            STEP 3
            Calculate idxLeftChild and idxRightChild from idxParentNode
                int idxLeftChild = 2 * idxParentNode + 1;
                int idxRightChild = 2 * idxParentNode + 1;
            STEP 4
            If idxLeftChild <= idxHeapRangeEnd and nums[idxSmallest] > nums[idxLeftChild], (meaning left child exist and its value is less than its parent node's value)
                STEP 5
                Update idxSmallest to idxLeftChild
                    idxSmallest = idxLeftChild;
            STEP 6
            If idxRightChild <= idxHeapRangeEnd and nums[idxSmallest] < nums[idxRightChild], (meaning right child exist and its value is less than last smallest element)
                STEP 7
                Update idxSmallest to idxRightChild
                    idxSmallest = idxRightChild;
            STEP 8
            If idxSmallest != idxParentNdoeEnd
                STEP 9
                Swap two elements at idxParentNdoe and idxSmallest
                    swap(nums, idxParentNdoe, idxSmallest);
                
    TIME:
        O(n * logn), first n is the iteration on heap size, second logn is the iteration on each parent node under each different heap size
    SPACE:
        O(1), no extra space is used
*/

public class HeapSortV1 {
    public static void sort(int[] nums) {
        // STEP 1
        for (int idxHeapRangeEnd = nums.length - 1; idxHeapRangeEnd >= 1; idxHeapRangeEnd--) {
            // STEP 2
            heapify(nums, idxHeapRangeEnd);
            // STEP 3
            swap(nums, 0, idxHeapRangeEnd);
        }
    }
    
    public static void heapify(int[] nums, int idxHeapRangeEnd) {
        // STEP 1
        int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        // STEP 2
        for (int idxParentNodeStart = idxParentNodeEnd; idxParentNodeStart >= 0; idxParentNodeStart--) {
            // STEP 3
            heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
        }
    }
    
    public static void heapifyCore(int[] nums, int idxParentNodeStart, int idxParentNodeEnd, int idxHeapRangeEnd) {
        // STEP 1
        for (int idxParentNode = idxParentNodeStart; idxParentNode <= idxParentNodeEnd; idxParentNode++) {
            // STEP 2
            int idxSmallest = idxParentNode;
            // STEP 3
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxRightChild = 2 * idxParentNode + 2;
            // STEP 4
            if (idxLeftChild <= idxHeapRangeEnd && nums[idxSmallest] > nums[idxLeftChild]) {
                // STEP 5
                idxSmallest = idxLeftChild;
            }
            // STEP 6
            if (idxRightChild <= idxHeapRangeEnd && nums[idxSmallest] > nums[idxRightChild]) {
                // STEP 7
                idxSmallest = idxRightChild;
            }
            // STEP 8
            if (idxSmallest != idxParentNode) {
                // STEP 9
                swap(nums, idxSmallest, idxParentNode);
            }
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        sort(nums);
        System.out.println("after sorted: " + Arrays.toString(nums));
    }
}