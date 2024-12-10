/*
    VARS:
        nodeFast(LinkedListNode): a fast pointer first reach the end of linked list
        nodeSlow(LinkedListNode): a slow pointer will point to the previous one node of nth node from end
    DESCRIPTION:
        STEP 1
        If n == list.length, (meaning nth node from end, also the first node from start, in this case, two pointers not applied, because there is not previous node for first node in linked list, so directly move list.head to next to remove first node)
            STEP 2
            Advance list.head to next node
                list.head = list.head.next;
            STEP 3
            Decrease the number of nodes by one
                list.length--;
            STEP 4
            Return;
        STEP 5
        Initialize nodeFast to list.head
        Initialize nodeSlow to list.head
        STEP 6
        Loop while n > 0, (count the number of movement for nodeFast)
            STEP 7
            Advance nodeFast by one step
                nodeFast = nodeFast.next;
            STEP 8
            Decrease n by one
                n--;
        
        Now nodeFast is n steps ahead of nodeSlow
        
        STEP 9
        Loop while nodeFast.next != null, (meaning current nodeFast is not empty and its next node is also non-empty)
            STEP 10
            Advance nodeFast and nodeSlow by one step at the same time
                nodeFast = nodeFast.next;
                nodeSlow = nodeSlow.next;
        
        Now nodeFast.next == null in the following, (meaning nodeFast reach the last non-empty node of linked list)
        Now nodeSlow is pointing to the nth node from end of linked list
        
        STEP 11
        Delete nth node from end
            nodeSlow.next = nodeSlow.next.next;
        STEP 12
        Decrease length of linked list by one
            list.length--;
    TIME:
        O(n), two pass of linked list is needed
    SPACE:
        O(1), no extra space is used 
*/ 
class RemoveNthNodeFromEndV1 {
    
    public static void removeNthNode(LinkedList list, int n) {
        // STEP 1
        if (n == list.length) {
            // STEP 2
            list.head = list.head.next;
            // STEP 3
            list.length--;
            // STEP 4
            return;
        }
        // STEP 5
        LinkedListNode nodeFast = list.head;
        LinkedListNode nodeSlow = list.head;
        // STEP 6
        while (n > 0) {
            // STEP 7
            nodeFast = nodeFast.next;
            // STEP 8
            n--;
        }
        // STEP 9
        while (nodeFast.next != null) {
            // STEP 10
            nodeFast = nodeFast.next;
            // STEP 11
            nodeSlow = nodeSlow.next;
        }
        // STEP 12
        nodeSlow.next = nodeSlow.next.next;
        // STEP 13
        list.length--;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1};
        int[] nums = {1, 2};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
    
        // int n = 5;
        // int n = 1;
        int n = 2;
        removeNthNode(list, n);
        System.out.println("after remove " + n + "th node, linked list: " + list.toString());
    }
}