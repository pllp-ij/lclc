import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        nodeFast(LinkedListNode): a fast node to iterate linked list
        nodeSlow(LinkedListNode): a slow node to iterate linked list
        nodeResult(LinkedListNode): final return result node
    DESCRIPTION:
        STEP 1
        Initialize nodeFast to head
        Initialize nodeSlow to head
        STEP 2
        Loop while nodeFast != null and nodeFast.next != null and nodeSlow != null,
            STEP 3
            Advance nodeFast two steps forward
                nodeFast = nodeFast.next.next;
            STEP 4
            Advance nodeSlow one step forward
                nodeSlow = nodeSlow.next;
            STEP 5
            If nodeFast == nodeSlow, (meaning the there is a cycle and two pointers are at the same position)
                STEP 6
                Initialize nodeResult to head
                STEP 7
                Loop while nodeResult != nodeSlow, 
                    STEP 8
                    Advance nodeResult one step forward each iteration
                        nodeResult = nodeResult.next;
                    STEP 9
                    Advance nodeSlow one step forward each iteration
                        nodeSlow = nodeSlow.next;
                
                Now nodeResult == nodeSlow in the following
                
                STEP 10
                Return nodeResult
        STEP 11
        Return null when there is no cycle
    TIME:
        O(n), at least one pass of whole linked list
    SPACE:
        O(1), no extra space is used
    
*/

class LinkedListCycle2V1 {
    
    public static LinkedListNode getCycleEntrance(LinkedList list) {
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
            if (nodeFast == nodeSlow) {
                // STEP 6
                LinkedListNode nodeResult = list.head;
                // STEP 7
                while (nodeResult != nodeSlow) {
                    // STEP 8
                    nodeResult = nodeResult.next;
                    // STEP 9
                    nodeSlow = nodeSlow.next;
                }
                // STEP 10
                return nodeResult;
            }
        }
        // STEP 11
        return null;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 0, -4};
        // int[] nums = {3, 2, 0, -4, 2, 5};
        int[] nums = {3, 2, 0, -4, 2, 5, 6, 7};
        int pos = 3;
        LinkedList list = new LinkedList();
        // list.createLinkedList(nums);
        list.createCycle(nums, pos);
        // System.out.println("linked list: " + list.toString(4));
        
        LinkedListNode entrance = getCycleEntrance(list);
        if (entrance != null) {
            System.out.println("the linked list has cycle, entrance is: " + entrance.val);
        } else {
            System.out.println("the linked list has no cycle");
        }
    }
}