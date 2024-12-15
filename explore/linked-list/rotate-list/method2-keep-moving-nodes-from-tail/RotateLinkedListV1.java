/*
    VARS:
        tailNode(LinkedListNode): the tail node of adjusted linked list after each operation that move the node from tail to head
        nodeBeforeTail(LinkedListNode): the node before the last node in linked list
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (if the linked list is empty or has only one node)
            STEP 2
            Directly return
        STEP 3
        Map k within the length of linked list to execute the minimum effective ratation
            k %= getLength(list);
        STEP 4
        Initialize tailNode to null
        Initialize nodeBeforeTail to null
        STEP 5
        Loop while k > 0, (the total times to rotate)
            STEP 6
            Reset nodeBeforeTail to list.head
            STEP 7
            Loop while nodeBeforeTail != null and nodeBeforeTail.next != null and nodeBeforeTail.next.next != null, (searching for the node before the last node in linked list)
                nodeBeforeTail = nodeBeforeTail.next;
            
            Now nodeBeforeTail is pointing at the node before last node
            
            STEP 8
            Assign nodeBeforeTail.next to tailNode to denote the last node pointer
                tailNode = nodeBeforeTail.next;
            STEP 9
            Link the next field of tailNode to list.head
                tailNode.next = list.head;
            STEP 10
            Set the next field of nodeBeforeTail to null
                nodeBeforeTail.next = null;
            STEP 11
            Move list.head to tailNode
             list.head = tailNode;
            STEP 12
            Decrease k by one
                k--;
    TIME:
        O(k * n), n is the length of linked list, each movement of tail node have to find the last node in linked list
    SPACE:
        O(1), no extra space is used
*/

class RotateLinkedListV1 {
    
    public static void rotateList(LinkedList list, int k) {
        // STEP 1
        if (list.head == null || list.head.next == null) {
            // STEP 2
            return;
        }
        // STEP 3
        k %= getLength(list);
        // STEP 4
        LinkedListNode tailNode = null;
        LinkedListNode nodeBeforeTail = null;
        // STEP 5
        while (k > 0) {
            // STEP 6
            nodeBeforeTail = list.head;
            // STEP 7
            while (nodeBeforeTail != null && nodeBeforeTail.next != null && nodeBeforeTail.next.next != null) {
                // STEP 8
                nodeBeforeTail = nodeBeforeTail.next;
            }
            // STEP 9
            tailNode = nodeBeforeTail.next;
            // STEP 10
            tailNode.next = list.head;
            // STEP 10
            nodeBeforeTail.next = null;
            // STEP 11
            list.head = tailNode;
            // STEP 12
            k--;
        }
    }
    
    public static int getLength(LinkedList list) {
        return list.length;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {};
        int[] nums = {1};
        // int[] nums = {1, 2, 3, 4, 5};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        // int k = 2;
        // int k = 2;
        int k = 2;
        // int k = 8;
        rotateList(list, k);
        System.out.println("after rotate " + k + " steps to right, linked list: " + list.toString());
    }
}