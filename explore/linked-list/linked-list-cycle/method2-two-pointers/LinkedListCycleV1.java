/*
    VARS:
        nodeFast(int): a fast pointer which move two steps ahead each time
        nodeSlow(int): a slow pointer which move one step ahead each time
    DESCRIPTION:
        STEP 1
        Initialize nodeFast to head
        Initialize nodeSlow to head
        STEP 2
        Loop while nodeFast != null and nodeFast.next != null and nodeSlow != null
            STEP 3
            Advance nodeSlow by one step
                nodeSlow = nodeSlow.next;
            STEP 4
            Advance nodeFast by one step
                nodeFast = nodeFast.next.next;
            STEP 5
            If nodeSlow == nodeFast, (meaning after first same starting point, these two pointers again meet with each other)
                STEP 6
                Return true
        
        Now there are three conditions:
            1 nodeFast == null, (meaning nodeFast is the last null of non-cyclic linked list)
            2 nodeFast != null and nodeFast.next == null, (meaning nodeFast is the last node of a non-cycle linked list)
            3 nodeFast != null and nodeFast.next != null and nodeSlow == null, (meaning the slow pointer is also iterated to last null value)
        All above three conditions come to a conclusion that the linked list has no cycle
        
        STEP 7
        Return false;
    TIME:
        O(n), one pass of the linked list
    SPACE:
        O(1), no extra space is used
*/

public class LinkedListCycleV1 {
    
    public static boolean hasCycle(LinkedList list) {
        // STEP 1
        LinkedListNode nodeFast = list.head;
        LinkedListNode nodeSlow = list.head;
        // STEP 2
        while (nodeFast != null && nodeFast.next != null && nodeSlow != null) {
            // STEP 3
            nodeFast = nodeFast.next.next;
            // STEP 4
            nodeSlow = nodeSlow.next;
            // STEP 5
            if (nodeSlow == nodeFast) {
                // STEP 6
                return true;
            }
        }
        // STEP 7
        return false;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 0, -4};
        // int[] nums = {3, 2, 0, -4, 2};
        int[] nums = {};
        int pos = 1;
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        // list.createCycle(nums, pos);
        // System.out.println("linked list: " + list.toString(4));
        
        boolean result = hasCycle(list);
        if (result) {
            System.out.println("the linked list has cycle");
        } else {
            System.out.println("the linked list has no cycle");
        }
    }
}