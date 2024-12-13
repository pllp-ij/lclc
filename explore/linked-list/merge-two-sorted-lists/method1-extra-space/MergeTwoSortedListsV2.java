/*
    NOTE:
        This V2 method will not alter the structure of two linked list compared with method2, but using extra space,
        when passing mergedListTailNode to method addNewNode as parameter, the address value of mergedListTailNode is passed into the method,
        so simply modify the mergedListTailNode value within the method will not change the value outside the method, so that's wrong logic
    VARS:
        mergedList(LinkedList): finally return result
        curNode1(LinkedListNode): the iterative pointer for list1
        curNode2(LinkedListNode): the iterative pointer for list2
        mergedListTailNode(LinkedListNode): always pointing to the tail node of mergedList for adding new nodes
        newNode(LinkedListNode): the node created in each iteration
    DESCRIPTION:
        STEP 1
        Initialize mergedList
        Initialize curNode1 to list1.head
        Initialize curNode2 to list2.head
        Initialize mergedListTailNode to null
        STEP 2
        Loop while curNode1 != null and curNode2 != null, (meaning both lists are not used up yet)
            If curNode1.val <= curNode2.val, (the curNode1 should be added)
                STEP 3
                Add new node create from curNode1.val to mergedList by calling method
                    // Wrong Logic
                    // addNewNode(mergedList, curNode1.val, mergedListTailNode)
                    mergedList.addAtTail(curNode1.val);
                STEP 4
                Advance curNode1 to next node
                    curNode1 = curNode1.next;
            Else, (curNode1.val > curNode2.val, meaning curNode2 should be added)
                STEP 5
                Add new node create from curNode2.val to mergedList by calling method
                    // Wrong Logic
                    // addNewNode(mergedList, curNode2.val, mergedListTailNode);
                    mergedList.addAtTail(curNode2.val);
                STEP 6
                Advance curNode2 to next node
                    curNode2 = curNode2.next;
        STEP 7
        If curNode1 == null, (meaning list1 scanned finished)
            STEP 8
            Link curNode2 to the tail of mergedList
                mergedList.linkAtTail(curNode2);
        STEP 9
        If curNode2 == null, (meaning list2 scanned finished)
            STEP 10
            Link curNode1 to the tail of mergedList
                mergedList.linkAtTail(curNode1);
        STEP 11
        Return mergedList;
           
           
        - FUNC addNewNode(LinkedList mergedList, int val, LinkedListNode newNode, LinkedListNode mergedListTailNode)
            STEP 1
            Create a new node from val
                newNode = new LinkedListNode(val);
            If mergedList.head == null, (meaning current adding the first node into mergedList)
                STEP 2
                Move head of mergedList to newNode
                    mergedList.head = newNode
                STEP 3
                Move mergedListTailNode to newNode
                    mergedListTailNode = newNode;
            Else, (mergedList.head != null, meaning current adding is not the first node into mergedList)
                STEP 4
                Link the next field of mergedListTailNode to newNode
                    mergedListTailNode.next = newNode;
                STEP 5
                Advance mergedListTailNode to next node
                    mergedListTailNode = mergedListTailNode.next;
    TIME:
        O(M + N), M, N is respectively length of each linked list
    SPACE:
        O(M + N), extra space is used
*/
class MergeTwoSortedListsV2 {
    
    public static LinkedList mergeTwoSortedLists(LinkedList list1, LinkedList list2) {
        // STEP 1
        LinkedList mergedList = new LinkedList();
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        // LinkedListNode mergedListTailNode = new LinkedListNode(-1);
        // STEP 2
        while (curNode1 != null && curNode2 != null) {
            if (curNode1.val <= curNode2.val) {
                // Wrong Logic
                // addNewNode(mergedList, curNode1.val, mergedListTailNode);
                // STEP 3
                mergedList.addAtTail(curNode1.val);
                // STEP 4
                curNode1 = curNode1.next;
            } else {
                // Wrong Logic
                // addNewNode(mergedList, curNode2.val, mergedListTailNode);
                // STEP 5
                mergedList.addAtTail(curNode2.val);
                // STEP 6
                curNode2 = curNode2.next;
            }
        }
        // STEP 7
        if (curNode1 == null) {
            // STEP 8
            mergedList.linkAtTail(curNode2);
        }
        // STEP 9
        if (curNode2 == null) {
            // STEP 10
            mergedList.linkAtTail(curNode1);
        }
        // STEP 11
        return mergedList;
    }
    
    /*
    public static void addNewNode(LinkedList mergedList, int val, LinkedListNode mergedListTailNode) {
        // STEP 1
        LinkedListNode newNode = new LinkedListNode(val);
        if (mergedList.head == null) {
            // STEP 2
            mergedList.head = newNode;
            // STEP 3
            mergedListTailNode = mergedList.head;
        } else {
            // STEP 4
            mergedListTailNode.next = newNode;
            // STEP 5
            mergedListTailNode = mergedListTailNode.next;
        }
    }
    */
    
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