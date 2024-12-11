/*
    VARS:
        nodeHead(LinkedListNode): a pointer pointing at the head of range in which nodes already reversed
        nodeTail(LinkedListNode): a pointer pointing at the tail of range in which nodes already reversed
        curNode(LinkedListNode): the current node to be processed
    DESCRIPTION:
        STEP 1
        Initialize nodeHead to list.head
        Initialize nodeTail to list.head
        Initialize curNode to null
        STEP 2
        Loop while nodeTail != null and nodeTail.next != null, (meaning there are still some nodes after nodeTail to be reversed, the first condition is for the case in which list is initially an empty linked list)
            STEP 3
            Reset curNode to the next node after nodeTail to be reversed
                curNode = nodeTail.next;
            STEP 4
            Link the next field of nodeTail to the next node of curNode
                nodeTail.next = curNode.next;
            STEP 5
            Insert curNode to the head of range in which nodes already reversed
                curNode.next = nodeHead;
            STEP 6
            Update nodeHead to curNode to increase the size of already reversed range by one
                nodeHead = curNode;
        STEP 7
        Assign nodeHead back to list.head
            list.head = nodeHead;
        STEP 8
        Return list
    TIME:
        O(n), one pass of initial linked list
    SPACE:
        O(1)
*/
class ReverseLinkedListV1 {
    
    public static LinkedList reverseLinkedList(LinkedList list) {
        // STEP 1
        LinkedListNode nodeHead = list.head;
        LinkedListNode nodeTail = list.head;
        LinkedListNode curNode = null;
        // STEP 2
        while (nodeTail != null && nodeTail.next != null) {
            // STEP 3
            curNode = nodeTail.next;
            // STEP 4
            nodeTail.next = curNode.next;
            // STEP 5
            curNode.next = nodeHead;
            // STEP 6
            nodeHead = curNode;
        }
        // STEP 7
        list.head = nodeHead;
        // STEP 8
        return list;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        int[] nums = {1};
        // int[] nums = {};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        LinkedList listReverse = reverseLinkedList(list);
        System.out.println("after reverse, linked list: " + listReverse.toString());
    }
}