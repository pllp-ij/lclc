import java.util.HashMap;

/*
    VARS:
        cacheMap(HashMap<Integer>): the cache map to store nums already visited
    DESCRIPTION:
        STEP 1
        Initialize cacheMap to HashMap
            HashMap<Integer> cacheMap = new HashMap<>();
        STEP 2
        Initialize iterative pointer curNode to head
        STEP 3
        Loop while curNode != null and NOT cacheMap.containsKey(curNode.val), (meaning current node is not null and cache map doesn't contains value of current node)
            STEP 4
                Add value of current node to hash map
                    cacheMap.put(curNode.val, 1);
            STEP 5
            Advance current node to next one
                curNode = curNode.next;
        
        Now there are two conditions:
            1 curNode == null, (meaning current node is empty, the linked list has no cycle so the pointer can reach the end null value)
            2 curNode != null and cacheMap.containsKey(curNode.val), (meaning current node is not empty and hash map contains value of current node, the linked list has cycle so the pointer meet value it met before)
            
        STEP 6
        if curNode == null,
            STEP 7
            Return false
        
        Now curNode != null and cacheMap.containsKey(curNode.val) in the following
        
        STEP 8
        Return true;
        
    TIME:
        O(n), one pass of the linked list
    SPACE:
        O(n), extra hash map is used for caching
*/

public class LinkedListCycleV1 {
    
    public static boolean hasCycle(LinkedList list) {
        // STEP 1
        HashMap<LinkedListNode, Integer> cacheMap = new HashMap<>();
        // STEP 2
        LinkedListNode curNode = list.head;
        // STEP 3
        while (curNode != null && !cacheMap.containsKey(curNode)) {
            // STEP 4
            cacheMap.put(curNode, 1);
            // STEP 5
            curNode = curNode.next;
        }
        // STEP 6
        if (curNode == null) {
            // STEP 7
            return false;
        }
        // STEP 8
        return true;
    }
    
    public static void main(String[] args) {
        // int[] nums = {3, 2, 0, -4};
        int[] nums = {3, 2, 0, -4, 2};
        int pos = 1;
        LinkedList list = new LinkedList();
        // list.createLinkedList(nums);
        list.createCycle(nums, pos);
        // System.out.println("linked list: " + list.toString(4));
        
        boolean result = hasCycle(list);
        if (result) {
            System.out.println("the linked list has cycle");
        } else {
            System.out.println("the linked list has no cycle");
        }
    }
}