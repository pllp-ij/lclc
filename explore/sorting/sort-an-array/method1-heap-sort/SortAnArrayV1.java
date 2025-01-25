import java.util.Arrays;

/*
    VARS:
        idxHeapRangeEnd(int): the end index of heap range, from (nums.length - 1) to 1
        idxParentNodeEnd(int): the last parent node's index for each iterated heap range
        idxParentNodeStart(int): the start index of parent node from bottom to top
        idxParentNode(int): the pointer used to iterate each parent node from top to bottom
    DESCRIPTION:
        STEP 1
        Iterate idxHeapRangeEnd from (nums.length - 1) to 1
            STEP 2
            Heapify nums with range from 0 to idxHeapRangeEnd by calling method
                heapify(nums, idxHeapRangeEnd);
            STEP 3
            Swap first element of nums and the last one to move the largest element to the most right
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2;
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 without setting updating condition in for loop command
            STEP 3
            Adjust each iterated parent node by calling recursive method heapifyCore, which is the core logic of heapify function
                heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            STEP 4
            If idxHeapRangeEnd == nums.length - 1, (meaning current heapification operation is the first time, in which all parent nodes should be adjusted one by one without skipping)
                STEP 5
                Reduce idxParentNodeStart by one to iterate each parent node one by one
                    idxParentNodeStart--;
            Else, (meaning current heapification operation is not the first time, so only the parent nodes along largest path should be adjusted)
                STEP 6
                If idxParentNodeStart == 0 && (idxParentNodeStart - 1) / 2 == 0, (deal with cases where (0 - 1) / 2 still equal to zero resulting unlimited loop)
                    STEP 7
                    Break or directly return to finish
                        break;
                STEP 8
                Update idxParentNodeStart with (idxParentNodeStart - 1) / 2 to current node's parent node
                    idxParentNodeStart = (idxParentNodeStart - 1) / 2;
                    
        -FUNC void heapifyCore(int[] nums, int idxParentNode, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        If idxParentNode > idxParentNodeEnd, (meaning current iterated index is not a parent node, directly return without doing anything)
            STEP 2
            Directly return
                return;
        STEP 3
        Initialize idxLargest to idxParentNode
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
        If idxLargest != idxParentNode, (meaning the largest is not the parent node but one of child node)
            STEP 10
            Swap two elements at idxParentNode and idxLargest
                swap(nums, idxParentNode, idxLargest);
            STEP 11
            Recursively process next index with value idxLargest
                heapifyCore(nums, idxLargest, idxParentNodeEnd, idxHeapRangeEnd);

    TIME:
        O(n * logn), first n is the iteration on right end of heap range, logn is the heapify process of each iterated heap range
    SPACE:
        O(1), no extra space is used
*/

class SortAnArrayV1 {
    
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