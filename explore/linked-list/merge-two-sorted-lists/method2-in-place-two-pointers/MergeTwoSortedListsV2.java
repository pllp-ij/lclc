/*
    NOTE:
        Compared to V1, V2 use a dummy node to link the merged linked list head, whether curNode1 is empty initially or is reach the end after while loop,
        the operation is the same "mergedList.tail.next = curNode2"
    VARS:
        curNode1(LinkedListNode): pointer to iterate linked list1
        curNode2(LinkedListNode): pointer to iterate linked list2
        curNodeToAdd(LinkedListNode): always pointing to smaller one between curNode1 and curNode2
        mergedList(LinkedList): final return result
    DESCRIPTION:
        STEP 1
        Initialize mergedList
            LinkedList mergedList = new LinkedList();
        Initialize mergedList.head to a dummy node LinkedListNode(-1)
            mergedList.head = new LinkedListNode(-1);
        Initialize mergedList.tail to mergedList.head
            mergedList.tail = mergedList.head;
        STEP 2
        Loop while curNode1 != null and curNode2 != null,
            If curNode1.val <= curNode2.val,
                STEP 3
                Assign curNode1 to curNodeToAdd
                    curNodeToAdd = curNode1;
                STEP 4
                Advance curNode1 to next node
                    curNode1 = curNode1.next;
            Else,
                STEP 5
                Assign curNode2 to curNodeToAdd
                    curNodeToAdd = curNode2
                STEP 6
                Advance curNode2 to next node
                    curNode2 = curNode2.next;
            STEP 7
            Link the next field of mergedList.tail to curNodeToAdd
                mergedList.tail.next = curNodeToAdd
            STEP 8
            Advance mergedList.tail to next node
                mergedList.tail = mergedList.tail.next;
        STEP 9
        If curNode1 == null, (meaning list1 is used up)
            STEP 10
            Link the next field of mergedList.tail to curNode2
                mergedList.tail.next = curNode2
        STEP 11
        If curNode2 == null, (meaning list2 is used up)
            STEP 12
            Link the next field of mergedList.tail to curNode1
                mergedList.tail.next = curNode1
        STEP 13
        Move the head of mergedList to next node to return
            mergedList.head = mergedList.head.next;
        STEP 14
        Return mergedList
    TIME:
        O(M + N), M, N is respectively length of each linked list
    SPACE:
        O(1)
*/
class MergeTwoSortedListsV2 {
    
    public static LinkedList mergeTwoSortedLists(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        LinkedListNode curNodeToAdd = null;
        LinkedList mergedList = new LinkedList();
        mergedList.head = new LinkedListNode(-1);
        mergedList.tail = mergedList.head;
        // STEP 2
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val <= curNode2.val) {
                // STEP 3
                curNodeToAdd = curNode1;
                // STEP 4
                curNode1 = curNode1.next;
            } else {
                // STEP 5
                curNodeToAdd = curNode2;
                // STEP 6
                curNode2 = curNode2.next;
            }
            // STEP 7
            mergedList.tail.next = curNodeToAdd;
            // STEP 8
            mergedList.tail = mergedList.tail.next;
        }
        // STEP 9
        if (curNode1 == null) {
            // STEP 10
            mergedList.tail.next = curNode2;
        }
        // STEP 11
        if (curNode2 == null) {
            // STEP 12
            mergedList.tail.next = curNode1;
        }
        // STEP 13
        mergedList.head = mergedList.head.next;
        // STEP 14
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