import java.util.Set;
import java.util.HashSet;

/*
    VARS:
        curNode(LinkedListNode): the iterative node
        cacheSet(Set<LinkedListNode>): the cache set of node already visited
    DESCRIPTION:
        STEP 1
        Initialize curNode to list1.head
        Initialize cacheSet to Set<LinkedListNode>
        STEP 2
        Loop while curNode != null, (iterate each node in list1)
            STEP 3
            Add current node into cacheSet
                cacheSet.add(curNode);
            STEP 4
            Advance curNode to next node
                curNode = curNode.next;
        STEP 5
        Reset curNode to list2.head
        STEP 6
        Loop while curNode != null, (iterate each node in list2)
            STEP 7
            If cacheSet.contains(curNode), (meaning this node is the intersection node)
                STEP 8
                Return curNode
            STEP 9
            Advance curNode to next node
                curNode = curNode.next;
        STEP 10
        Return null if no matching nodes after all iterations
    TIME:
        O(max(M, N)), M is the length of list1, N is the length of list2
    SPACE:
        O(max(M, N)), M is the length of list1, N is the length of list2
*/

class IntersectionOfLinkedListV1 {
    
    public static LinkedListNode getIntersect(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedListNode curNode  = list1.head;
        Set<LinkedListNode> cacheSet = new HashSet<>();
        // STEP 2
        while (curNode != null) {
            // STEP 3
            cacheSet.add(curNode);
            // STEP 4
            curNode = curNode.next;
        }
        // STEP 5
        curNode = list2.head;
        // STEP 6
        while (curNode != null) {
            // STEP 7
            if (cacheSet.contains(curNode)) {
                // STEP 8
                return curNode;
            }
            // STEP 9
            curNode = curNode.next;
        }
        // STEP 10
        return null;
    }
    
    public static void main(String[] args) {
        int[] nums1 = {4, 1, 8, 4, 5};
        int[] nums2 = {5, 6, 1, 8, 4, 5};
        // int[] nums2 = {8, 4, 5};
        LinkedList list1 = new LinkedList();
        list1.createLinkedList(nums1);
        LinkedList list2 = new LinkedList();
        list2.createLinkedList(nums2);
        list1.mergeList(2, list2, 3);
        System.out.println("After merge, list1: " + list1.toString());
        System.out.println("After merge, list2: " + list2.toString());
        
        LinkedListNode nodeIntersect = getIntersect(list1, list2);
        if (nodeIntersect != null) {
            System.out.println("two linked lists have intersection, the intersection node is: " + nodeIntersect.val);
        } else {
            System.out.println("two linked list have no intersection");
        }
    }
}