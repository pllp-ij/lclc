import java.util.HashSet;
import java.util.Set;

/*
    VARS:
        cacheSet(Set<LinkedListNode>): the cache set to store nums already visited
    DESCRIPTION:
        STEP 1
        Initialize cacheSet to Set<LinkedListNode>
        STEP 2
        Initialize iterative node to list.head
            LinkedListNode curNode = head;
        STEP 3
        Loop while curNode != null, (meaning current node is not empty)
            STEP 4
            If cacheSet contains curNode, (meaning linked list loop back)
                STEP 5
                Return true;
            STEP 6
            Advance curNode to next node
                curNode = curNode.next;
        
        Now curNode == null, meaning there is no cycle
        
        STEP 7
        Return false;
    TIME:
        O(n), one pass of the linked list
    SPACE:
        O(n), extra hash set is used for caching
*/

public class LinkedListCycleV2 {
    
    public static boolean hasCycle(LinkedList list) {
        // STEP 1
        Set<LinkedListNode> cacheSet = new HashSet<>();
        // STEP 2
        LinkedListNode curNode = list.head;
        // STEP 3
        while (curNode != null) {
            // STEP 4
            if (cacheSet.contains(curNode)) {
                // STEP 5
                return true;
            }
            // STEP 6
            cacheSet.add(curNode);
            // STEP 7
            curNode = curNode.next;
        }
        // STEP 8
        return false;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 0, -4};
        int[] nums = {3, 2, 0, -4, 2};
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