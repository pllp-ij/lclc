import java.util.Arrays;

/*
    VARS:
        idxHeapRangeEnd(int): the last index of heap range under each iteration
        idxParentNodeEnd(int): the last parent node index of each iterated heap range
        idxParentNodeStart(int): the pointer to determine the sub range need to be heapified from bottom to top
        idxParentNode(int): the pointer to iterate each parent node from top to bottom
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to (nums.length - k), which will iterate k times to find largest kth element and place it at the kth position on the right side
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap first element and last element of each iterated heap range with index of idxHeapRangeEnd
                swap(nums, 0, idxHeapRangeEnd);
        STEP 4
        Return nums[nums.length - k] as final result
            return nums[nums.length - 1]
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2;
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 without setting update conditions in for loop command
            STEP 3
            Adjust range from idxParentNodeStart to idxParentNodeEnd to make it satisfy the restriction of max heap by calling recursive method heapifyCore, which is the core logic in heapify function
                heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            STEP 4
            If idxHeapRangeEnd == nums.length - 1, (meaning this is the first time to heapify nums, so iterate each parent node one by one)
                STEP 5
                Reduce idxParentNodeStart by one to next left index
                    idxParentNodeStart--;
            Else, (meaning this is not the first time to heapify nums, so only iterate parent nodes on largest path)
                STEP 6
                If idxParentNodeStart == 0 and (idxParentNodeStart - 1) / 2 == 0, (to deal with cases where (0 - 1) / 2 still equal to 0 resulting unlimited loop)
                    STEP 7
                    Break or return to finish
                        break;
                STEP 8
                Otherwise, upadte idxParentNodeStart to (idxParentNodeStart - 1) / 2 to move to current index's parent node
                    idxParentNodeStart = (idxParentNodeStart - 1) / 2;
        
        -FUNC void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current index is not a parent node, so directly return without doing anything)
            STEP 2
            Directly return
                return;
        STEP 3
        Initialize idxLargest to idxParentNode,
            int idxLargest = idxParentNode
        STEP 4
        Calculate idxLeftChild and idxRightChild from idxParentNode
            int idxLeftChild = 2 * idxParentNode + 1;
            int idxRightChild = 2 * idxParentNode + 2;
        STEP 5
        If idxLeftChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxLeftChild], (meaning left child exist within valid range and its value is more than its parent node)
            STEP 6
            Update idxLargest to idxLeftChild
                idxLargest = idxLeftChild
        STEP 7
        If idxRightChild <= idxHeapRangeEnd and nums[idxLargest] < nums[idxRightChild], (meaning right child exist within valid range and its value is more than last largest element)
            STEP 8
            Update idxLargest to idxRightChild
                idxLargest = idxRightChild
        STEP 9
        If idxLargest != idxParentNode, (meaning largest element is one of child node)
            STEP 10
            Swap two elements at idxParentNode and idxLargest
                swap(nums, idxParentNode, idxLargest)
            STEP 11
            Recursively adjust next index with value of idxLargest
                heapifyCore(nums, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);
        
    TIME:
        O(n * logn), first n is the iteration on right end of heap range, logn is the heapify process of each iterated heap range
    SPACE:
        O(1), no extra space is used
*/

class KthLargestElementV1 {
    
    public static int getKthLargest(int[] nums, int k) {
        // STEP 1
        for (int idxHeapRangeEnd = nums.length - 1; idxHeapRangeEnd >= nums.length - k; idxHeapRangeEnd--) {
            // STEP 2
            heapify(nums, idxHeapRangeEnd);
            // STEP 3
            swap(nums, 0, idxHeapRangeEnd);
        }
        // STEP 4
        return nums[nums.length - k];
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
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String[] args) {
        // int[] nums = {5, 2, 3, 7, 9, 4, 1, 10, 6, 8};
        // int k = 5;
        
        int[] nums = {3, 2, 1, 5, 6, 4};
        int k = 2;
        
        System.out.println("initial nums: " + Arrays.toString(nums));
        int kthLargest = getKthLargest(nums, k);
        System.out.println(k + "th element : " + kthLargest);
    }
}