/*
    VARS:
        nodeToBeProcessed(LinkedListNode): the node to be processed/inserted into the sorted range of linked list
        nodeNextToBeProcessed(LinkedListNode): the pointer to store the next node to be processed after current iterated node
        curNode(LinkedListNode): the iterated node to find the position to insert nodeToBeProcessed
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning the head is empty or it has only one node)
            STEP 2
            Directly return without doing anything
                return;
        STEP 3
        Initialize nodeToBeProcessed to the second node of linked list
            LinkedListNode nodeToBeProcessed = list.head.next;
        Initialize nodeNextToBeProcessed to null for reusage purpose
            LinkedListNode nodeNextToBeProcessed = null;
        STEP 4
        Assign next field of list.head to null to seperate it from remaining part
            list.head.next = null;
        STEP 5
        Loop while nodeToBeProcessed != null, (iterate each node from second not to tail node)
            STEP 6
            Store the next node to be processed first and assign it to nodeNextToBeProcessed
                nodeNextToBeProcessed = nodeToBeProcessed.next;
            STEP 7
            Set the next field of nodeToBeProcessed to null to isolate it as a single node
                nodeToBeProcessed.next = null;
            STEP 8
            Insert nodeToBeProcessed into sorted range of linked list by calling method
                insertIntoSorted(list, nodeToBeProcessed);
            STEP 9
            Move nodeToBeProcessed to nodeNextToBeProcessed for next iteration
                nodeToBeProcessed = nodeNextToBeProcessed;
        
        -FUNC void insertIntoSorted(LinkedList list, LinkedListNode node)
        STEP 1
        If list.head.val > node.val, (meaning the value of the node to be inserted is smaller than the value of initial head node, in this case set node as new head)
            STEP 2
            Set next field of node to initial head of linked list
                node.next = list.head;
            STEP 3
            Update intial list.head to node
                list.head = node;
            STEP 4
            Return to finished insertion process
                return;
        
        Now node.val is at least equal to val of head, so find the position in sorted range to insert it
        
        STEP 5
        Initialize curNode equal to list.head for iterating each node of sorted range
            LinkedListNode curNode = list.head;
        STEP 6
        Loop while curNode != null and curNode.next != null, (iterate each node of sorted range that has next node)
            STEP 7
            If curNode.val <= node.val and node.val <= curNode.next.val, (meaning the value of curNode is between curNode and its next node)
                STEP 8
                Set next field of node to curNode.next first
                    node.next = curNode.next;
                STEP 9
                Set next field of curNode to node to insert it into correct position
                    curNode.next = node;
                STEP 10
                Break the loop after inserting the node into sorted range
                    break;
            STEP 11
            Update curNode to its next node for next iteration
                curNode = curNode.next;
        
        Now there are two conditions after existing above loop
            1. curNode == null, (which won't true before curNode.next == null, which is the second condition)
            2. curNode != null and curNode.next == null, (meaning curNode is the tail node of linked list, so directly link node at the end)
        
        STEP 12
        If curNode.next == null, (meaning curNode is the tail node)
            STEP 12
            Set next field of curNode to node to link it at the end
                curNode.next = node;
        
    TIME:
        O(n * n), first n is to iterate each node which to be processed, second n is used to find the place to insert processed node
    SPACE:
        O(1), no extra space used except the linked list itself
*/

class InsertionSortListV1 {
    
    public static void sort(LinkedList list) {
        // STEP 1
        if (list.head == null || list.head.next == null) {
            // STEP 2
            return;
        }
        // STEP 3
        LinkedListNode nodeToBeProcessed = list.head.next;
        LinkedListNode nodeNextToBeProcessed = null;
        // STEP 4
        list.head.next = null;
        // STEP 5
        while (nodeToBeProcessed != null) {
            // STEP 6
            nodeNextToBeProcessed = nodeToBeProcessed.next;
            // STEP 7
            nodeToBeProcessed.next = null;
            // STEP 8
            insertIntoSorted(list, nodeToBeProcessed);
            // STEP 9
            nodeToBeProcessed = nodeNextToBeProcessed;
        }
    }
    
    public static void insertIntoSorted(LinkedList list, LinkedListNode node) {
        // STEP 1
        if (list.head.val > node.val) {
            // STEP 2
            node.next = list.head;
            // STEP 3
            list.head = node;
            // STEP 4
            return;
        }
        // STEP 5
        LinkedListNode curNode = list.head;
        // STEP 6
        while (curNode != null && curNode.next != null) {
            // STEP 7
            if (curNode.val <= node.val && node.val <= curNode.next.val) {
                // STEP 8
                node.next = curNode.next;
                // STEP 9
                curNode.next = node;
                // STEP 10
                break;
            }
            // STEP 11
            curNode = curNode.next;
        }
        // STEP 12
        if (curNode.next == null) {
            // STEP 13
            curNode.next = node;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {4, 2, 1, 3};
        // int[] nums = {-1, 5, 3, 4, 0};
        // int[] nums = {};
        int[] nums = {2, 1};
        
        LinkedList list = new LinkedList(nums);
        System.out.println("initial linked list: " + list.toString());
        sort(list);
        System.out.println("sorted linked list : " + list.toString());
    }
}