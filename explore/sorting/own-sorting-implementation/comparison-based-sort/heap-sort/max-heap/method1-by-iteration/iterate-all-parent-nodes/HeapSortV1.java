import java.util.Arrays;

/*
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
            Swap the first element of heapified nums and the last element of current iterated heap
                swap(nums, 0, idxHeapRangeEnd);
                
        -FUNC void heapify(int[] nums, int idxHeapRangeEnd)
        STEP 1
        Initialize idxParentNodeEnd to (idxHeapRangeEnd - 1) / 2
            int idxParentNodeEnd = (idxHeapRangeEnd - 1) / 2;
        STEP 2
        Iterate idxParentNodeStart from idxParentNodeEnd to 0 to adjust by calling method heapifyCore function, which is the core logic of heapify function
            STEP 3
            heapifyCore(nums, idxParentNodeStart, idxParentNodeEnd, idxHeapRangeEnd);
            
        -FUNC void heapifyCore(int[] nums, int idxParentNodeStart, int idxParentNodeEnd, int idxHeapRangeEnd)
        STEP 1
        Iterate idxParentNode from idxParentNodeStart to idxParentNodeEnd
            STEP 2
            Initialize idxLargest to idxParentNode
                int idxLargest = idxParentNode;
            STEP 3
            Calculate left and right child index from idxLargest
                int idxLeft = 2 * idxLargest + 1;
                int idxRight = 2 * idxLargest + 2;
            STEP 4
            If idxLeft <= idxHeapRangeEnd and largest < nums[idxLeft], (meaning left child exist and its value is more than its parent node's value)
                STEP 5
                Update idxLargest to idxLeft
                    idxLargest = idxLeft;
            STEP 6
            If idxRight <= idxHeapRangeEnd and largest < nums[idxRight], (meaning right child exist and its value is more than last largest element, which maybe the parent node or the left child)
                STEP 7
                Update idxLargest to idxRight
                    idxLargest = idxRight;
            
            Now idxLargest is pointing at the largest element of a sub tree structure, Swap element at idxParentNode and idxLargest if they are not equal
            
            STEP 8
            If idxParentNode != idxLargest, (meaning there is a diverge of them)
                STEP 9
                Swap two elements at these two indexes
                    swap(nums, idxParentNode, idxLargest);
            
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
            int idxLargest = idxParentNode;
            // STEP 3
            int idxLeftChild = 2 * idxLargest + 1;
            int idxRightChild = 2 * idxLargest + 2;
            // STEP 4
            if (idxLeftChild <= idxHeapRangeEnd && nums[idxLargest] < nums[idxLeftChild]) {
                // STEP 5
                idxLargest = idxLeftChild;
            }
            // STEP 6
            if (idxRightChild <= idxHeapRangeEnd && nums[idxLargest] < nums[idxRightChild]) {
                // STEP 7
                idxLargest = idxRightChild;
            }
            // STEP 8
            if (idxLargest != idxParentNode) {
                // STEP 9
                swap(nums, idxParentNode, idxLargest);
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