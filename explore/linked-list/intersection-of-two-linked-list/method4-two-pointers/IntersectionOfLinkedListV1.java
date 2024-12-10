/*
    VARS:
        curNode1(LinkedListNode): the iterative node in list1
        curNode2(LinkedListNode): the iterative node in list2
    DESCRIPTION:
        STEP 1
        Initialize curNode1 to list1.head
        Initialize curNode2 to list2.head
        STEP 2
        Loop while curNode1 != curNode2, (finally both curNode1 and curNode2 will come to null or intersection)
            If curNode1 == null, (meaning curNode1 reach the end of list1, recycle it back to head of list2)
                STEP 3
                Recycle curNode1 back to head of list2
                    curNode1 = list2.head;
            Else, (meaning curNode1 != null, update curNode1 to next node as normally do)
                STEP 4
                Advance curNode1 to next node
                    curNode1 = curNode1.next;
            If curNode2 == null, (meaning curNode2 reach the end of list2, recycle it back to head of list1)
                STEP 5
                Recycle curNode2 back to head of list1
                    curNode2 = list1.head
            Else, (meaning curNode2 != null, update curNode2 to next node as normally do)
                STEP 6
                Advance curNode2 to next node
                    curNode2 = curNode2.next;
        STEP 7
        Return curNode1;
    TIME:
        O(M + N), M is the length of list1, N is the length of list2
    SPACE:
        O(1)
*/

class IntersectionOfLinkedListV1 {
    
    public static LinkedListNode getIntersect(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        // STEP 2
        while (curNode1 != curNode2) {
            if (curNode1 == null) {
                // STEP 3
                curNode1 = list2.head;
            } else {
                // STEP 4
                curNode1 = curNode1.next;
            }
            if (curNode2 == null) {
                // STEP 5
                curNode2 = list1.head;
            } else {
                // STEP 6
                curNode2 = curNode2.next;
            }
        }
        // STEP 7
        return curNode1;
    }
    
    public static void main(String[] args) {
        // int[] nums1 = {4, 1, 8, 4, 5};
        // int[] nums2 = {5, 6, 1, 8, 4, 5};
        int[] nums1 = {3};
        int[] nums2=  {2, 3};
        // int[] nums2 = {8, 4, 5};
        LinkedList list1 = new LinkedList();
        list1.createLinkedList(nums1);
        LinkedList list2 = new LinkedList();
        list2.createLinkedList(nums2);
        // list1.mergeList(2, list2, 3);
        list1.mergeList(0, list2, 1);
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