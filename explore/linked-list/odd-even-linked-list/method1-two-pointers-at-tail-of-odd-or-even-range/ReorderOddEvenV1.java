/*
    VARS:
        oddTailNode(LinkedListNode): the tail node of odd range maintained
        evenTailNode(LinkedListNode): the tail node of even range maintained
        curNode(LinkedListNode): the current odd node should be reordered
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning if there are less than two nodes in linked list)
            STEP 2
            Nothing should be done, directly return
                return
        STEP 3
        Initialize oddTailNode to list.head
        Initialize evenTailNode to oddTailNode.next;
        Initialize curNode to null
        STEP 4
        Loop while evenTailNode != null and evenTailNode.next != null, (meaning there still odd nodes should be processed)
            STEP 5
            Reset curNode to evenTailNode.next
                curNode = evenTailNode.next;
            STEP 6
            Move curNode out from the next position of evenTailNode
                evenTailNode.next = curNode.next;
            STEP 7
            Advance evenTailNode by one step
                evenTailNode = evenTailNode.next;
            STEP 8
            Add curNode to the next position of oddTailNode
                curNode.next = oddTailNode.next;
                oddTailNode.next = curNode;
            STEP 9
            Advance oddTailNode by one step
                oddTailNode = oddTailNode.next;
    TIME:
        O(n), one pass of linked list
    SPACE:
        O(1), no extra space is used
*/


class ReorderOddEvenV1 {
    
    public static void reorderOddEven(LinkedList list) {
        // STEP 1
        if (list.head == null || list.head.next == null) {
            // STEP 2
            return;
        }
        // STEP 3
        LinkedListNode oddTailNode = list.head;
        LinkedListNode evenTailNode = oddTailNode.next;
        LinkedListNode curNode = null;
        // STEP 4
        while (evenTailNode != null && evenTailNode.next != null) {
            // STEP 5
            curNode = evenTailNode.next;
            // STEP 6
            evenTailNode.next = curNode.next;
            // STEP 7
            evenTailNode = evenTailNode.next;
            // STEP 8
            curNode.next = oddTailNode.next;
            oddTailNode.next = curNode;
            // STEP 9
            oddTailNode = oddTailNode.next;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1, 2, 3, 4, 5, 6};
        // int[] nums = {};
        // int[] nums = {1};
        // int[] nums = {1, 2};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        reorderOddEven(list);
        System.out.println("after reorder, linked list: " + list.toString());
    }
}