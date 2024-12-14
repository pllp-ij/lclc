/*
    NOTE:
        Compared to V1, V2 simplify the if statement by move inserting operation all outside the while loop
    VARS:
        curNode(LinkedListNode): the pointer pointing to the current node
        nextNode(LinkedListNode): the pointer pointing to the next node
        newNode(LinkedListNode): a pointer always point to the newly created new node
    DESCRTPTION:
        STEP 1
        Create new node from insertVal
            LinkedListNode newNode = new LinkedListNode(insertVal);
        STEP 2
        If list.head == null, (meaning linked list is empty)
            STEP 3
            Link the next field of newNode to itself
                newNode.next = newNode;
            STEP 4
            Move the head of linked list to newNode
                list.head = newNode;
            STEP 5
            Return
        
        Now linekd list has at least one node
        
        STEP 6
        Initialize curNode to list.head
        Initialize nextNode to curNode.next
        STEP 7
        Loop while curNode.next != list.head, (meaning each node in cyclic linked list will be scanned, notice that there is a final insertion(if it true) when the while loop stopped)
            If curNode.val <= nextNode.val, (meaning current is searching in the [minVal, maxVal] arc range)
                If curNode.val <= insertVal and insertVal <= nextNode.val, (meaning insertVal is in between curNode and nextNode, also means the right position is found in [minVal, maxVal] arc range)
                    STEP 8
                    Break the loop
                        break;
            Else, (meaning current is searching in (-infinite, minVal] or [maxVal, +infinite) arc ranges)
                If curNode.val < insertVal or insertVal < nextNode.val, (meaning insertVal is in between curNode and nextNode, also means the right position is found in (-infinite, minVal] or [maxVal, +infinite) ranges)
                    STEP 9
                    Break the loop
                        break;
            STEP 10
            Advance both curNode and nextNode by one step
                curNode = curNode.next;
                nextNode = nextNode.next;
        
        Now there are four cases reach here in the following
            1 cyclic linked list has only one node
            2 right position found between curNode and nextNode from STEP 8
            3 right position found between curNode and nextNode from STEP 9
            4 right position is located at: curNode.next = list.head, which is the final step before curNode move into another cycle
        All above four cases will need same insertion operation in the following
        
        STEP 11
        newNode.next = nextNode;
        curNode.next=  newNode;
    TIME:
        O(n), scan each node in cyclic linked list for only once
    SPACE:
        O(1), no extra space is used
*/

class InsertIntoCyclicSortedListV2 {
    
    public static void insertIntoCyclicList(LinkedList list, int insertVal) {
        // STEP 1
        LinkedListNode newNode = new LinkedListNode(insertVal);
        // STEP 2
        if (list.head == null) {
            // STEP 3
            newNode.next = newNode;
            // STEP 4
            list.head = newNode;
            // STEP 5
            return;
        }
        // STEP 6
        LinkedListNode curNode = list.head;
        LinkedListNode nextNode = curNode.next;
        // STEP 7
        while (curNode.next != list.head) {
            // found in [minVal, maxVal] arc range
            if (curNode.val <= nextNode.val) {
                if (curNode.val <= insertVal && insertVal <= nextNode.val) {
                    // STEP 8
                    break;
                }
            } else { // found in (-infinite, minVal] or [maxVal, +infinite) arc ranges
                if (curNode.val < insertVal || insertVal < nextNode.val) {
                    // STEP 9
                    break;
                }
            }
            // STEP 10
            curNode = curNode.next;
            nextNode = nextNode.next;
        }
        // STEP 11
        newNode.next = nextNode;
        curNode.next = newNode;
    }
    
    public static void main(String[] args) {
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        
        // int[] nums = {};
        int[] nums = {1};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        list.toBeCyclic(0);
        System.out.println("cyclic linked list: " + list.toString(nums.length));
        
        // int insertVal = 3;
        // int insertVal = 5;
        // int insertVal = 8;
        // int insertVal = 0;
        // int insertVal = 1;
        // int insertVal = 2;
        
        // int insertVal = 1;
        int insertVal = 3;
        insertIntoCyclicList(list, insertVal);
        System.out.println("after insert " + insertVal + ", cyclic linked list: " + list.toString(nums.length + 1));
    }
}