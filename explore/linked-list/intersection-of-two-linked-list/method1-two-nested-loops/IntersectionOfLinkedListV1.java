/*
    VARS:
        curNode1(LinkedListNode): the iterative node in list1
        curNode2(LinkedListNode): the iterative node in list2
    DESCRIPTION:
        STEP 1
        Initialize curNode1 to list1.head
        STEP 2
        Loop while curNode1 != null, (iterate each node in list1)
            STEP 3
            Initialize curNode2 to list2.head
            STEP 4
            Loop while curNode2 != null, (iterate each node in list2)
                STEP 5
                If curNode2 == curNode1, (meaning current node in list1 also in list2 and it first met)
                    STEP 6
                    Return curNode1
                STEP 7
                Advance curNode2 to next node
                    curNode2 = curNode2.next;
            STEP 8
            Advance curNode1 to next node
                curNode1 = curNode1.next;
        STEP 9
        Return null after all iteration finished without finding equal condition
    TIME:
        O(M * N), M is the length of list1, N is the length of list2
    SPACE:
        O(1)
*/

class IntersectionOfLinkedListV1 {
    
    public static LinkedListNode getIntersect(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedListNode curNode1 = list1.head;
        // STEP 2
        while (curNode1 != null) {
            // STEP 3
            LinkedListNode curNode2 = list2.head;
            // STEP 4
            while (curNode2 != null) {
                // STEP 5
                if (curNode2 == curNode1) {
                    // STEP 6
                    return curNode1;
                }
                // STEP 7
                curNode2 = curNode2.next;
            }
            // STEP 8
            curNode1 = curNode1.next;
        }
        // STEP 9
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