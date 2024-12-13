/*
    VARS:
        valList(List<Integer>): the list for collecting values in each linked list
        curNode(LinkedListNode): for iterating purpose
        mergedList(LinkedList): final return result
    DESCRIPTION:
        STEP 1
        Initialize valList to ArrayList
            List<Integer> valList = new ArrayList<>();
        Initialize curNode to list1.head
        Initialize mergedList
            LinkedList mergedList = new LinkedList();
        STEP 2
        Loop while curNode != null, (each node in list1 will be visited)
            STEP 3
            Add the value of curNode to valList
                valList.add(curNode.val);
            STEP 4
            Advance curNode to next node
                curNode = curNode.next;
        STEP 5
        Reset curNode to list2.head, (start iterating each node in list2)
        STEP 6
        Loop while curNode != null, (each node in list2 will be visited)
            STEP 7
            Add the value of curNode to valList
                valList.add(curNode.val);
            STEP 8
            Advance curNode to next node
                curNode = curNode.next;
        STEP 9
        Sort valList by calling built-in method
            Collections.sort(valList);
        
        Now all value is sorted in valList, next create linked list from this sorted ArrayList
        
        STEP 10
        Iterate each value in valList
            STEP 11
            Create new node from current value
                LinkedListNode newNode = new LinkedListNode(val);
            STEP 12
            If mergedList.head == null, (meaning adding the first node into empty mergedList)
                STEP 13
                Set head of mergedList to newNode
                    mergedList.head = newNode;
                STEP 14
                Set curNode to mergedList.head
                    curNode = mergedList.head;
                STEP 15
                Skip current iteration
                    continue;
            STEP 16
            Link the next field of curNode to newNode to add it, (for cases except adding the first node)
                curNode.next = newNode;
            STEP 17
            Advance curNode to next node
                curNode = curNode.next;
        STEP 18
        Return mergedList
    TIME:
        O(M + N), M, N is respectively length of each linked list
    SPACE:
        O(M + N), extra space is used
*/
class MergeTwoSortedListsV1 {
    
    public static LinkedList mergeTwoSortedLists(LinkedList list1, LinkedList list2) {
        // STEP 1
        List<Integer> valList = new ArrayList<>();
        LinkedListNode curNode = list1.head;
        LinkedList mergedList = new LinkedList();
        // STEP 2
        while (curNode != null) {
            // STEP 3
            valList.add(curNode.val);
            // STEP 4
            curNode = curNode.next;
        }
        // STEP 5
        curNode = list2.head;
        // STEP 6
        while (curNode != null) {
            // STEP 7
            valList.add(curNode.val);
            // STEP 8
            curNode = curNode.next;
        }
        // STEP 9
        Collections.sort(valList);
        // STEP 10
        for (Integer val: valList) {
            // STEP 11
            LinkedListNode newNode = new LinkedListNode(val);
            // STEP 12
            if (mergedList.head == null) {
                // STEP 13
                mergedList.head = newNode;
                // STEP 14
                curNode = mergedList.head;
                // STEP 15
                continue;
            }
            // STEP 16
            curNode.next = newNode;
            // STEP 17
            curNode = curNode.next;
        }
        // STEP 18
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