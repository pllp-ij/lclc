/*
    NOTE:
        Compared with V1, V2 simplify the if-else statement
    VARS:
        prevNode(LinkedListNode): a pointer always point to the previous one node of the node to be deleted
        curNode(LinkedListNode): a pointer always point to the current node to be deleted
    DESCRIPTION:
        STEP 1
        Initialize prevNode to null
        Initialize curNode to list.head
        STEP 2
        Loop while curNode != null, (meaning each node in linked list will be visited)
            If curNode.val == key, (meaning curNode is to be deleted)
                If prevNode != null, (meaning curNode is not the first node)
                    STEP 3
                    Link the next field of prevNode to the next node of curNode
                        prevNode.next = curNode.next;
                STEP 4
                Advance curNode by one step for both two conditions: prevNode != null or prevNode == null
                    curNode = curNode.next;
            Else, (meaning curNode.val != key)
                If prevNode == null, (meaning curNode is the first node)
                    STEP 5
                    Reset head of linked list to curNode
                        list.head = curNode;
                STEP 6
                Move prevNode to curNode for both two conditions: prevNode == null or prevNode != null
                    prevNode = curNode;
                STEP 7
                Advance curNode to next node for both two conditions: prevNode == null or prevNode != null
                    curNode = curNode.next;
        STEP 8
        If prevNode == null, (meaning prevNode never be assign new node when iterating nodes in linked list, so all nodes in linked list is equal to key and already be removed)
            STEP 9
            Reset head of list to null
            list.head = null;
    TIME:
        O(n), one pass of linked list
    SPACE:
        O(1), no extra space used
*/
class RemoveElementsV2 {
    
    public static void removeElements(LinkedList list, int key) {
        // STEP 1
        LinkedListNode prevNode = null;
        LinkedListNode curNode = list.head;
        // STEP 2
        while (curNode != null) {
            if (curNode.val == key) {
                if (prevNode != null) {
                    // STEP 3
                    prevNode.next = curNode.next;
                }
                // STEP 4
                curNode = curNode.next;
            } else {
                if (prevNode == null) {
                    // STEP 5
                    list.head = curNode;
                }
                // STEP 6
                prevNode = curNode;
                // STEP 7
                curNode = curNode.next;
            }
        }
        // STEP 8
        if (prevNode == null) {
            // STEP 9
            list.head = null;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 6, 3, 4, 5, 6};
        // int[] nums = {2, 2, 2, 3, 4, 5};
        int[] nums = {2, 2, 2, 2, 2, 2};
        // int[] nums = {2, 2, 2, 2, 2, 1};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        // int key = 6;
        // int key = 2;
        int key = 2;
        // int key = 2;
        removeElements(list, key);
        System.out.println("after remove " + key + ", linked list: " + list.toString());
    }
}