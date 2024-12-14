/*
    VARS:
        curNode(LinkedListNode): the pointer pointing to the current node
        nextNode(LinkedListNode): the pointer pointing to the next node
        newNode(LinkedListNode): a pointer always point to the newly created new node
    DESCRTPTION:
        STEP 1
        Initialize newNode to create a new node from insertVal
            LinkedListNode newNode = new LinkedListNode(insertVal);
        STEP 2
        If list.head == null, (meaning the linked list is empty)
            STEP 3
            Link the next field of new node to itself
                newNode.next = newNode;
            STEP 4
            Move the head of linked list to newNode
                list.head = newNode;
            STEP 5
            Return
        STEP 6
        Initialize curNode to list.head
        Initialize nextNode to null
        STEP 7
        Loop while curNode.next != list.head, (meaning each node in cyclic linked list will be visited)
            STEP 8
            Set nextNode to curNode.next
                nextNode = curNode.next;
            If curNode <= nextNode, (meaning in [minVal, maxVal] range)
                If curNode.val <= insertVal and insertVal <= nextNode.val, (meaning insertVal is in between curNode and nextNode)
                    STEP 9
                    Add newNode with value of insertVal in between curNode and nextNode
                        newNode.next = nextNode;
                        curNode.next = newNode;
                    STEP 10
                    Return after find the approriate position to insert
                        return;
            Else, (meaning in (-infinite, minVal) or (maxVal, +infinite) range)
                If curNode.val < insertVal or insertVal < nextNode.val, (meaning insertVal is in between the reset range)
                    STEP 11
                    Add new node with value of insertVal in between curNode and nextNode
                        newNode.next = nextNode;
                        curNode.next = newNode;
                    STEP 12
                    Return after find the approriate position to insert
                        return;
            STEP 13
            Advance both curNode and nextNode by one step
                curNode = curNode.next;
                nextNode = nextNode.next;
        
        Now curNode.next == list.head in the following, meaning there is a final one insertion before the list.head
        
        STEP 14
        Add new node with value of insertVal in between curNode and nextNode
            newNode.next=  nextNode;
            curNode.next = newNode;
    TIME:
        O(n), scan each node in cyclic linked list for only once
    SPACE:
        O(1), no extra space is used
*/

class InsertIntoCyclicSortedListV1 {
    
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
            // STEP 8
            nextNode = curNode.next;
            if (curNode.val <= nextNode.val) {
                if (curNode.val <= insertVal && insertVal <= nextNode.val) {
                    // STEP 9
                    newNode.next = nextNode;
                    curNode.next = newNode;
                    // STEP 10
                    return;
                }
            } else {
                if (curNode.val < insertVal || insertVal < nextNode.val) {
                    // STEP 11
                    newNode.next = nextNode;
                    curNode.next = newNode;
                    // STEP 12
                    return;
                }
            }
            // STEP 13
            curNode = curNode.next;
            nextNode = nextNode.next;
        }
        // STEP 14
        newNode.next = nextNode;
        curNode.next = newNode;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        // int[] nums = {4, 5, 1, 2, 2, 3};
        
        // int[] nums = {};
        // int[] nums = {1};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        list.toBeCyclic(0);
        System.out.println("cyclic linked list: " + list.toString(nums.length));
        
        int insertVal = 3;
        // int insertVal = 5;
        // int insertVal = 8;
        // int insertVal = 0;
        // int insertVal = 1;
        // int insertVal = 2;
        
        // int insertVal = 1;
        // int insertVal = 3;
        insertIntoCyclicList(list, insertVal);
        System.out.println("after insertion, cyclic linked list: " + list.toString(nums.length + 1));
    }
}