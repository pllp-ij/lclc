/*
    VARS:
        prevNode(LinkedListNode): the previous one node of current iterated node
        curNode(LinkedListNode): current iterated node in linked list
        nextNode(LinkedListNode): the next node of current iterated node
    DESCRIPTION:
        STEP 1
        Initialize prevNode to null
        Initialize curNode to list.head
        Initialize nextNode to null
        STEP 2
        Loop while curNode != null, (meaning each node in linked list will be visited)
            STEP 3
            Reset nextNode to curNode.next;
                nextNode = curNode.next;
            STEP 4
            Link the next field of curNode to prevNode
                curNode.next = prevNode;
            STEP 5
            Move prevNode to curNode
                prevNode = curNode
            STEP 6
            Move curNode to nextNode
                curNode = nextNode;
        
        Now prevNode is pointing at the new head of reversed linked list
        
        STEP 7
        Reset head of linked list to prevNode
            list.head = prevNode;
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
        LinkedListNode prevNode = null;
        LinkedListNode curNode = list.head;
        LinkedListNode nextNode = null;
        // STEP 2
        while (curNode != null) {
            // STEP 3
            nextNode = curNode.next;
            // STEP 4
            curNode.next = prevNode;
            // STEP 5
            prevNode = curNode;
            // STEP 6
            curNode = nextNode;
        }
        // STEP 7
        list.head = prevNode;
        // STEP 8
        return list;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1};
        int[] nums = {};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        LinkedList listReverse = reverseLinkedList(list);
        System.out.println("after reverse, linked list: " + listReverse.toString());
    }
}