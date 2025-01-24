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
        idxLargest(int): pointer to find the largest element of three element sub tree structure
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to 1
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd by calling method
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap the first element of heapified nums with the last element
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 without setting update condition in for loop line
            STEP 3
            Heapify the range from idxParentNodeStart to idxParentNodeEnd with specified steps according to the value of idxHeapRangeEnd by calling method hepifyCore, which is the core logic in heapification operation
                heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            
            Now update idxParentNodeStart according the value of idxHeapRangeEnd
            
            STEP 4
            If idxHeapRangeEnd == nums.length - 1, (meaning this is the first heapification operation, so move idxParentNodeStart to the left one by one)
                STEP 5
                Reduce idxParentNodeStart by one to next left index
                    idxParentNodeStart--;
            Else, (meaning current heapification operation is not the first time)
                STEP 6
                If idxParentNodeStart == 0 && (idxParentNodeStart - 1) / 2 == 0, (Inoder to solve the value of calculation (0 - 1) / 2 equal to 0, here adding extra check)
                    STEP 7
                    Break the loop or return to finish
                        break;
                STEP 8
                Otherwise, update idxParentNodeStart to (idxParentNodeStart - 1) / 2
                    idxParentNodeStart = (idxParentNodeStart - 1) / 2;
                
        -FUNC void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current node is not a parent node, so directly return the recursive function)
            STEP 2
            Directly return
                return;
        STEP 3
        Initialize idxLargest to idxParentNode in default
            int idxLargest = idxParentNode;
        STEP 4
        Calculate idxLeftChild and idxRightChild from idxParentNode
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxRightChild = 2 * idxParentNode + 2;
        STEP 5
        If idxLeftChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxLeftChild], (meaning left child exist and its value is more than its parent node's value)
            STEP 6
            Update idxLargest to idxLeftChild
                idxLargest = idxLeftChild
        STEP 7
        If idxRightChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxRightChild], (meaning right child exist and its value is more than its parent node's value)
            STEP 8
            Update idxLargest to idxRightChild
                idxLargest = idxRightChild;
        
        Now idxLargest is pointing at the largest element of three sub tree structure, so swap two elements at idxParentNode and idxLargest only when they are not equal,
        if idxLargest is equal to idxParentNode, meaning current idxParentNodeStart is satisfying the restriction of max heap, so do nothing and jump to next idxParentNodeStart value, otherwise recursively calling the method for next index
        
        STEP 9
        If idxLargest != idxParentNode, (meaning the largest element within sub tree structure is not the parent node)
            STEP 10
            Swap two elements in idxParentNode and idxLargest
                swap(nums, idxHeapRangeEnd, idxLargest);
            STEP 11
            Recursively do the heapification operation for next index with value of idxLargest
                heapifyCore(nums, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);
        
        
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
            heapifyCore(nums, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);
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