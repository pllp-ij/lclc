/*
    VARS:
        tailNodeOfFirstHalf(LinkedListNode): the head of second half linked list
        secondHalfHead(LinkedListNode): the head of second half linked list
        idx(int): the index to iterate reversed linked list for k steps
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning linked list is empty or has only one node)
            STEP 2
            Directly return
        STEP 3
        Update k with k % getLength(list.head) for k larger than the length of linked list by calling method to calculate length of linked list
            k %= getLength(list);
        STEP 4
        If k == 0, (meaning the number of movement is multiple of the length of linked list)
            STEP 5
            Directly return
        STEP 6
        Reverse original linked list by calling method and assign it to list.head
            list.head = reverseLinkedList(list.head);
        STEP 7
        Initialize tailNodeOfFirstHalf as a dummy node with value of -1
        Initialize idx to 0
        STEP 8
        Link the next field of tailNodeOfFirstHalf to list.head to place the dummy node before original linked list's head
            tailNodeOfFirstHalf.next = list.head;
        STEP 9
        Loop while idx < k, (use idx to find the node right before the head of second half linked list, it is where the while loop exit)
            STEP 10
            Advance tailNodeOfFirstHalf to next node
                tailNodeOfFirstHalf = tailNodeOfFirstHalf.next;
            STEP 11
            Increase idx by one
                idx++;
        
        Now tailNodeOfFirstHalf is pointing at the node before the head of second half linked list to be reversed
        
        STEP 12
        Reverse the second half of linked list and assign the head of reversed result to secondHalfHead
            LinkedListNode secondHalfHead = reverseLinkedList(tailNodeOfFirstHalf.next);
        STEP 13
        If tailNodeOfFirstHalf.next != list.head, (meaning tailNodeOfFirstHalf move at least one step from initial dummy node, only in this case, the first half linked list exist)            
            STEP 14
            Set the next field of tailNodeOfFirstHalf to null to sperate the first and second apart
                tailNodeOfFirstHalf.next = null;
            STEP 15
            Reverse the first half linked list by calling method and assign it to list.head
                list.head = reverseLinkedList(list.head);
        STEP 16
        Reset tailNodeOfFirstHalf to list.head
        STEP 17
        Loop while tailNodeOfFirstHalf.next != null, (to reach the last node in first half linked list)
            STEP 18
            Advance tailNodeOfFirstHalf to next node
                tailNodeOfFirstHalf = tailNodeOfFirstHalf.next;
        
        Now tailNodeOfFirstHalf is pointing at the last node of first half linked list
        
        STEP 19
        Link the next field of tailNodeOfFirstHalf to secondHalfHead
            tailNodeOfFirstHalf.next = secondHalfHead;
    TIME:
        O(4n) ~ O(n), n for reverse whole list, 2n for reverse first and second half list, n for find the tail of first half to link to second half
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
        if (k == 0) {
            // STEP 5
            return;
        }
        // STEP 6
        list.head = reverseLinkedList(list.head);
        // STEP 7
        LinkedListNode tailNodeOfFirstHalf = new LinkedListNode(-1);
        int idx = 0;
        // STEP 8
        tailNodeOfFirstHalf.next = list.head;
        // STEP 9
        while (idx < k) {
            // STEP 10
            tailNodeOfFirstHalf = tailNodeOfFirstHalf.next;
            // STP 11
            idx++;
        }
        // STEP 12
        LinkedListNode secondHalfHead = reverseLinkedList(tailNodeOfFirstHalf.next);
        // STEP 13
        if (tailNodeOfFirstHalf.next != list.head) {
            // STEP 14
            tailNodeOfFirstHalf.next = null;
            // STEP 15
            list.head = reverseLinkedList(list.head);
        }
        // STEP 16
        tailNodeOfFirstHalf = list.head;
        // STEP 17
        while (tailNodeOfFirstHalf != null && tailNodeOfFirstHalf.next != null) {
            // STEP 18
            tailNodeOfFirstHalf = tailNodeOfFirstHalf.next;
        }
        // STEP 19
        tailNodeOfFirstHalf.next = secondHalfHead;
    }
    
    public static int getLength(LinkedList list) {
        return list.length;
    }
    
    public static LinkedListNode reverseLinkedList(LinkedListNode head) {
        LinkedListNode prevNode = null;
        LinkedListNode tailNodeOfFirstHalf = head;
        LinkedListNode nextNode = null;
        while (tailNodeOfFirstHalf != null) {
            nextNode = tailNodeOfFirstHalf.next;
            tailNodeOfFirstHalf.next = prevNode;
            prevNode = tailNodeOfFirstHalf;
            tailNodeOfFirstHalf = nextNode;
        }
        return prevNode;
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