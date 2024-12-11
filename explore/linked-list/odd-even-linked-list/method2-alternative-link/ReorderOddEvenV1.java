/*
    VARS:
        oddNode(LinkedListNode): the pointer to iterate odd nodes
        evenNode(LinkedListNode): the pointer to iterate even nodes
        evenStartNode(LinkedListNOde): the start node of even nodes
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning linked list is empty or it has only one node)
            STEP 2
            Nothing should be done, directly return;
        STEP 3
        Initialize oddNode to list.head
        Initialize evenNode to oddNode.next
        Initialize evenStartNode to evenNode
        STEP 4
        Loop while evenNode != null and evenNode.next != null, (the first is for case like "1, 2, 3" in which loop will stop when evenNode equals to null, the second condion is for case like "1, 2, 3, 4" in which loop will stop when evenNode.next == null)
            STEP 5
            Assign evenNode.next to oddNode.next
                oddNode.next = even.next;
            STEP 6
            Advance oddNode to next node
                oddNode = oddNode.next;
            STEP 7
            Assign oddNode.next to evenNode.next
                even.next = oddNode.next;
            STEP 8
            Advance evenNode to next node
                evenNode = evenNode.next
                
        Now oddNode is pointing at the last node of odd nodes
        
        STEP 9
        oddNode.next = evenStartNode;
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
        LinkedListNode oddNode = list.head;
        LinkedListNode evenNode = oddNode.next;
        LinkedListNode evenStartNode = evenNode;
        // STEP 4
        while (evenNode != null && evenNode.next != null) {
            // STEP 5
            oddNode.next = evenNode.next;
            // STEP 6
            oddNode = oddNode.next;
            // STEP 7
            evenNode.next = oddNode.next;
            // STEP 8
            evenNode = evenNode.next;
        }
        // STEP 9
        oddNode.next = evenStartNode;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1, 2, 3, 4, 5, 6};
        // int[] nums = {};
        // int[] nums = {1};
        int[] nums = {1, 2};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        reorderOddEven(list);
        System.out.println("after reorder, linked list: " + list.toString());
    }
}