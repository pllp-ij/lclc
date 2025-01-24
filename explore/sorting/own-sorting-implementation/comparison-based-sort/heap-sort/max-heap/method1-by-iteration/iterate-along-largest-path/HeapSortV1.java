import java.util.Arrays;

/*
    VARS:
        idxHeapRangeEnd(int): the last index of heap range, from (nums.length - 1) to 1
        idxParentNodeEnd(int): the last parent node in each iterated heap range
        idxParentNodeStart(int): the start index of heap range, from idxHeapRangeEnd to 0 in each iteration
        idxParentNode(int): the pointer used to iterate each parent node along largest path, from idxParentNodeStart to idxParentNodeEnd
        idxLargest(int): to store the largest index in a sub tree structure, root, left child, right child
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to 1
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd by calling method
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap the first element of heapified heap with the last element of it
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2, (the value can be determined by draw a concrete example)
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0
            STEP 3
            Adjust the range from idxParentNodeStart to idxParentNodeEnd incremently by calling recursive method heapifyCore
                heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd)
            
        -FUNC void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current pointer of idxParentNode is outside the last parent node, so the process should be stopped now)
            STEP 2
            Directly return;
        STEP 3
        Initialize idxLargest to idxParentNode
            int idxLargest = idxParentNode;
        STEP 4
        Calculate index of left child and right child
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxLargest = 2 * idxParentNode + 2;
        STEP 5
        If idxLeftChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxLeftChild], (meaning left child exist and its value is more thatn its parent node)
            STPE 6
            Update idxLargest to idxLeftChild
                idxLargest = idxLeftChild;
        STEP 7
        If idxRightChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxRightChild], (meaning right child exist and its value is more than last largest element)
            STEP 8
            Update idxLargest to idxRightChild
                idxLargest = idxRightChild;
        
        Now idxLargest is pointing at the largest element of three sub tree structure, so swap two elements at idxParentNode and idxLargest only when they are not equal,
        if idxLargest is equal to idxParentNode, meaning current idxParentNodeStart is satisfying the restriction of max heap, so do nothing and jump to next idxParentNodeStart value, otherwise recursively calling the method for next index
        
        STEP 9
        If idxLargest != idxParentNode, (meaning the largest element is child node)
            STEP 10
            Swap two elements at idxParentNode and idxLargest
                swap(nums, idxParentNode, idxLargest);
            STEP 11
            Recursively process next index in idxLargest
                heapifyCore(nums, idxLargest, idxParentNodeEnd, idxHeapRangeEnd)
        
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
    
    public static void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd) {
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
        if (idxLeftChild <= idxHeapRangeEnd && nums[idxLargest] < nums[idxLeftChild]) {
            // STEP 6
            idxLargest = idxLeftChild;
        }
        // STEP 7
        if (idxRightChild <= idxHeapRangeEnd && nums[idxLargest] < nums[idxRightChild]) {
            // STEP 8
            idxLargest = idxRightChild;
        }
        // STEP 9
        if (idxLargest != idxParentNode) {
            // STEP 10
            swap(nums, idxParentNode, idxLargest);
            // STEP 11
            heapifyCore(nums, idxLargest, idxParentNode, idxHeapRangeEnd);
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