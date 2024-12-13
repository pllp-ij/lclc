/*
    VARS:
        curNode1(LinkedListNode): pointer to iterate linked list1
        curNode2(LinkedListNode): pointer to iterate linked list2
        curNodeToAdd(LinkedListNode): always pointing to smaller one between curNode1 and curNode2
        mergedList(LinkedList): final return result
    DESCRIPTION:
        STEP 1
        Initialize curNode1 to list1.head
        Initialize curNode2 to list2.head
        Initialize curNodeToAdd to null
        Initialize mergedList
        STEP 2
        If curNode1 == null, (meaning list1 is empty)
            STEP 3
            Move the head of mergedList to curNode2
                mergedList.head = curNode2
            STEP 4
            Return mergedList
        STEP 5
        If curNode2 == null, (meaning list2 is empty)
            STEP 6
            Move the head of mergedList to curNode1
                mergedList.head = curNode1
            STEP 7
            Return mergedList
        
        Now both list1 and list2 are not empty, at least have one node
        
        STEP 8
        Loop while curNode1 != null and curNode2 != null,
            If curNode1.val <= curNode2.val,
                STEP 9
                Assign curNode1 to curNodeToAdd
                    curNodeToAdd = curNode1
                STEP 10
                Advance curNode1 to next node
                    curNode1 = curNode1.next;
            Else, (curNode1.val > curNode2.val)
                STEP 11
                Assign curNode2 to curNodeToAdd
                    curNodeToAdd = curNode2
                STEP 12
                Advance curNode2 to next node
                    curNode2 = curNode2.next;
            If mergedList.head == null, (meaning current adding first node into empty mergedList)
                STEP 13
                Move the head of mergedList to curNodeToAdd
                    mergedList.head = curNodeToAdd;
                STEP 14
                Move the tail of mergedList to curNodeToAdd
                    mergedList.tail = curNodeToAdd;
            Else, (adding nodes when mergedList is not empty)
                STEP 15
                Link the next field of tail to curNodeToAdd
                    mergedList.tail.next = curNodeToAdd;
                STEP 16
                Advance tail to next node
                    mergedList.tail = mergedList.tail.next;
        STEP 17
        If curNode1 == null, (meaning list1 is used up)
            STEP 18
            Link the next field of tail of mergedList to curNode2
                mergedList.tail.next = curNode2;
        STEP 19
        If curNode2 == null, (meaning list2 is used up)
            STEP 20
            Link the next field of tail of mergedList to curNode1
                mergedList.tail.next = curNode1;
        STEP 21
        Return mergedList
    TIME:
        O(M + N), M, N is respectively length of each linked list
    SPACE:
        O(1)
*/
class MergeTwoSortedListsV1 {
    
    public static LinkedList mergeTwoSortedLists(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        LinkedListNode curNodeToAdd = null;
        LinkedList mergedList = new LinkedList();
        // STEP 2
        if (curNode1 == null) {
            // STEP 3
            mergedList.head = curNode2;
            // STEP 4
            return mergedList;
        }
        // STEP 5
        if (curNode2 == null) {
            // STEP 6
            mergedList.head = curNode1;
            // STEP 7
            return mergedList;
        }
        // STEP 8
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val <= curNode2.val) {
                // STEP 9
                curNodeToAdd = curNode1;
                // STEP 10
                curNode1 = curNode1.next;
            } else {
                // STEP 11
                curNodeToAdd = curNode2;
                // STEP 12
                curNode2 = curNode2.next;
            }
            if (mergedList.head == null) {
                // STEP 13
                mergedList.head = curNodeToAdd;
                // STEP 14
                mergedList.tail = curNodeToAdd;
            } else {
                // STEP 15
                mergedList.tail.next = curNodeToAdd;
                // STEP 16
                mergedList.tail = mergedList.tail.next;
            }
        }
        // STEP 17
        if (curNode1 == null) {
            // STEP 18
            mergedList.tail.next = curNode2;
        }
        // STEP 19
        if (curNode2 == null) {
            // STEP 20
            mergedList.tail.next = curNode1;
        }
        // STEP 21
        return mergedList;
    }
    
    public static void main(String[] args) {
        // int[] nums1 = {1, 3, 5};
        // int[] nums2 = {2, 4, 6, 7, 8};
        
        // int[] nums1 = {1};
        // int[] nums2 = {2, 4, 6, 7, 8};
        
        // int[] nums1 = {5};
        // int[] nums2 = {2, 4, 6, 7, 8};
        
        // int[] nums1 = {10};
        // int[] nums2 = {2, 4, 6, 7, 8};
        
        // int[] nums1 = {};
        // int[] nums2 = {2, 4, 6, 7, 8};
        
        // int[] nums1 = {2, 3, 4};
        // int[] nums2 = {1};
        
        // int[] nums1 = {2, 3, 4};
        // int[] nums2 = {3};
        
        // int[] nums1 = {2, 3, 4};
        // int[] nums2 = {6};
        
        int[] nums1 = {2, 3, 4};
        int[] nums2 = {};
        LinkedList list1 = new LinkedList();
        list1.createLinkedList(nums1);
        LinkedList list2 = new LinkedList();
        list2.createLinkedList(nums2);
        System.out.println("linked list1: " + list1.toString());
        System.out.println("linked list2: " + list2.toString());
        
        LinkedList listMerged = mergeTwoSortedLists(list1, list2);
        System.out.println("after merge, linked list: " + listMerged.toString());
    }
}