/*
    VARS:
        curNode(LinkedListNode): pointer iterate each node in linked list
        listResult(LinkedList): final result to return
    DESCRIPTION:
        STEP 1
        Initialize curNode to list.head
        STEP 2
        Loop while curNode != null, (meaning iterating ecah node in linked list)
            STEP 3
            Create new node from curNode
            STEP 4
            If listResult.head != null, (meaning adding new node to non empty linked list)
                STEP 5
                Link next filed of newNode to initial list.head
                    newNode.next = list.head;
            STEP 6
            Move initial head to newNode
                list.head = newNode;
            STEP 7
            Advance curNode to next node
                curNode = curNode.next;
        STEP 8
        Return listResult
    TIME:
        O(n), one pass of initial linked list
    SPACE:
        O(n), extra linked list is used
*/
class ReverseLinkedListV1 {
    
    public static LinkedList reverseLinkedList(LinkedList list) {
        // STEP 1
        LinkedListNode curNode = list.head;
        LinkedList listResult = new LinkedList();
        // STEP 2
        while (curNode != null) {
            // STEP 3
            LinkedListNode newNode = new LinkedListNode(curNode.val);
            // STEP 4
            if (listResult.head != null) {
                // STEP 5
                newNode.next = listResult.head;
            }
            // STEP 6
            listResult.head = newNode;
            // STEP 7
            curNode = curNode.next;
        }
        // STEP 8
        return listResult;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1};
        int[] nums = {};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        LinkedList listReverse = reverseLinkedList(list);
        System.out.println("after reverse, linked list: " + listReverse.toString());
    }
}