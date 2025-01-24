import java.util.Arrays;

/*
    NOTE:
        The reason why there is no diverge in updating idxParentNode within heapifyCore function is that no matter whether current heap is first time heapified or not,
        the path to check parent nodes is along the largest path
    VARS:
        idxHeapRangeEnd(int): the index of end of heap range for each iteration
        idxParentNodeEnd(int): the last parent node in each iteration
        idxParentNodeStart(int): the first parent node to do heapify from it to idxParentNodeEnd
        idxParentNode(int): the iterated pointer to iterate from idxParentNodeStart to idxParentNodeEnd
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to 1
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd by calling method
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap first element of heapified nums and the last element
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 without setting stop condition in for loop declaration
            STEP 3
            Adjust range from idxParentNodeStart to idxParentNodeEnd to make it satisfy the restriction of min heap by calling method heapifyCore, which is the core logic of heapify function
                heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            STEP 4
            If idxHeapRangeEnd == nums.length - 1, (meaning current heapification operation is the first time)
                STEP 5
                Reduce idxParentNodeStart by one for next left index
                    idxParentNodeStart--;
            Else, (meaning current heapification operation is not the first time, so update idxParentNodeStart accordingly)
                STEP 6
                If idxParentNodeStart == 0 and (idxParentNodeStart - 1) / 2 == 0, (set this because (0 - 1) / 2 still equal to 0, so to avoid the unlimited iteration)
                    STEP 7
                    Break or return to finish
                        break;
                STEP 8
                Update idxParentNodeStart with (idxParentNodeStart - 1) / 2 to current index's parent node
                    idxParentNodeStart = (idxParentNodeStart - 1) / 2;
        
        -FUNC void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current index is not a parent node)
            STEP 2
            Directly return
                return;
        STEP 3
        Initialize idxSmallest to idxParentNode
            int idxSmallest = idxParentNode;
        STEP 4
        Calculate idxLeftChild and idxRightChild from idxParentNode
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxRightChild = 2 * idxParentNode + 2;
        STEP 5
        If idxLeftChild <= idxHeapRangeEnd and nums[idxSmallest] > nums[idxLeftChild], (meaning left child exist within valid range and its value is less than its parent node's value)
            STEP 6
            Update idxSmallest to idxLeftChild
                idxSmallest = idxLeftChild;
        STEP 7
        If idxRightChild <= idxHeapRangeEnd and nums[idxSmallest] > nums[idxRightChild], (meaning right child exist within valid range and its value is less than its parent node's value)
            STEP 8
            Update idxSmallest to idxRightChild
                idxSmallest = idxRightChild
        STEP 9
        If idxSmallest != idxParentNode, (meaning the smallest element is not the parent node of current sub tree structure)
            STEP 10
            Swap two elements at idxSmallest and idxParentNode
                swap(nums, idxSmallest, idxParentNode);
            STEP 11
            Recursively heapify next index with value of idxSmallest
                heapifyCore(nums, idxSmallest, idxParentNodeEnd, idxHeapRangeEnd);
        
    TIME:
        O(n * logn), first n is the iteration on heap size, second logn is the iteration on each parent node under each different heap size
    SPACE:
        O(1), no extra space is used
*/

public class HeapSortV2 {
    
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
        for (int idxParentNodeStart = idxParentNodeEnd; idxParentNodeStart >= 0; ) {
            // STEP 3
            heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            // STEP 4
            if (idxHeapRangeEnd == nums.length - 1) {
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
    
    public static void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd) {
        // STEP 1
        if (idxParentNode > idxParentNodeEnd) {
            // STEP 2
            return;
        }
        // STEP 3
        int idxSmallest = idxParentNode;
        // STEP 4
        int idxLeftChild  = 2 * idxParentNode + 1;
        int idxRightChild = 2 * idxParentNode + 2;
        // STEP 5
        if (idxLeftChild <= idxHeapRangeEnd && nums[idxSmallest] > nums[idxLeftChild]) {
            // STEP 6
            idxSmallest = idxLeftChild;
        }
        // STEP 7
        if (idxRightChild <= idxHeapRangeEnd && nums[idxSmallest] > nums[idxRightChild]) {
            // STEP 8
            idxSmallest = idxRightChild;
        }
        // STEP 9
        if (idxSmallest != idxParentNode) {
            // STEP 10
            swap(nums, idxParentNode, idxSmallest);
            // STEP 11
            heapifyCore(nums, idxSmallest, idxParentNodeEnd, idxHeapRangeEnd);
        }
    }
    
    public static void swap(int[] nums, int i, int j) {
        // STEP 1
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