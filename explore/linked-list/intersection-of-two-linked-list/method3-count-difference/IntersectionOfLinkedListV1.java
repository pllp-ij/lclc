/*
    VARS:
        len1(int): length of list1
        len2(int): length of list2
        lengthDiff(int): the difference between len1 and len2
        curNode1(LinkedListNode): iterate node in longer list between list1 and list2
        curNode2(LinkedListNode): iterate node in shorter list between list1 and list2
    DESCRIPTION:
        STEP 1
        Calculate len1 by calling method to calculate length of list1
        Calculate len2 by calling method to calculate length of list2
        STEP 2
        Calculate lengthDiff = len1 - len2;
        STEP 3
        Initialize curNode1 to list1.head
        Initialize curNode2 to list2.head
        STEP 4
        If len1 < len2, (if list1 is shorter than list2)
            STEP 5
            Swap curNode1 to list2's head and curNode2 to list1's head
                curNode1 = list2.head;
                curNode2 = list1.head;
            STEP 6
            Reverse lengthDiff
                lengthDiff = len2 - len1;
        STEP 7
        Loop while lengthDiff > 0,
            STEP 8
            Advance curNode1 to next node
                curNode1 = curNode1.next;
            STEP 9
            Decrease lengthDiff by one
                lengthDiff--;
        
        Now both curNode1 and curNode2 are at same distance from intersection point (if exist intersection)
        
        STEP 10
        Loop while curNode1 != curNode2,
            STEP 11
            Advance curNode1 by one step
                curNode1 = curNode1.next;
            STEP 12
            Advance curNode2 by one step
                curNode2 = curNode2.next;
        
        Now there are two conditions
            1 curNode1 == curNode2 == null, 
            2 curNode1 == curNode2 but not equal to null
            
        STEP 13
        Return curNode1 for both cases above
    TIME:
        O(max(M, N)), M is the length of list1, N is the length of list2
    SPACE:
        O(1)
*/

class IntersectionOfLinkedListV1 {
    
    public static LinkedListNode getIntersect(LinkedList list1, LinkedList list2) {
        // STEP 1
        int len1 = getLength(list1);
        int len2=  getLength(list2);
        // STEP 2
        int lengthDiff = len1 - len2;
        // STEP 3
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        // STEP 4
        if (len1 < len2) {
            // STEP 5
            curNode1 = list2.head;
            curNode2 = list1.head;
            // STEP 6
            lengthDiff = len2 - len1;
        }
        // STEP 7
        while (lengthDiff > 0) {
            // STEP 8
            curNode1 = curNode1.next;
            // STEP 9
            lengthDiff--;
        }
        // STEP 10
        while (curNode1 != curNode2) {
            // STEP 11
            curNode1 = curNode1.next;
            // STEP 12
            curNode2 = curNode2.next;
        }
        // STEP 13
        return curNode1;
    }
    
    public static int getLength(LinkedList list) {
        return list.length;
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