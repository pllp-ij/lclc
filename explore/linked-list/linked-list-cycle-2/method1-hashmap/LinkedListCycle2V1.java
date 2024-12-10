import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        cacheSet(Set<LinkedListNode>): the cache set of already visited nodes
    DESCRIPTION:
        STEP 1
        Initialize cacheSet to HashSet
        STEP 2
        Initialize iterative pointer curNode to head
        STEP 3
        Loop while curNode != null, (meaning current node is not empty)
            STEP 4
            If current node is already exist in cache set, (meaning it loops back to the entrance of cycle)
                STEP 5
                Return curNode
            STEP 6
            Add curNode to cache set
                cacheSet.add(curNode)
            STEP 7
            Advance curNode to next node
                curNode = curNode.next;
        STEP 8
        Return null when curNode == null, (meaning there is no loop, and curNode reach the end null value)
    TIME:
        O(n), at least one pass of whole linked list
    SPACE:
        O(n), extra set of size n is used
    
*/

class LinkedListCycle2V1 {
    
    public static LinkedListNode getCycleEntrance(LinkedList list) {
        // STEP 1
        Set<LinkedListNode> cacheSet = new HashSet<>();
        // STEP 2
        LinkedListNode curNode = list.head;
        // STEP 3
        while (curNode != null) {
            // STEP 4
            if (cacheSet.contains(curNode)) {
                // STEP 5
                return curNode;
            }
            // STEP 6
            cacheSet.add(curNode);
            // STEP 7
            curNode = curNode.next;
        }
        // STEP 8
        return null;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 0, -4};
        int[] nums = {3, 2, 0, -4, 2, 5};
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