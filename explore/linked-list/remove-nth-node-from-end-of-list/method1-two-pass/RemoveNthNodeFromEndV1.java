/*
    VARS:
        len(int): length of linked list
        nodeNumsBefore(int): total number of nodes before the nth node from end
        curNode(LinkedListNode): for iterative node
    DESCRIPTION:
        STEP 1
        If n == list.length, (the nth node from end is equal to the first from start, because there is no previous one node for first node, so the removement of first node should be processed specially)
            STEP 2
            Move head to head.next
                list.head = list.head.next;
            STEP 3
            Decrease length by one
                list.length--;
            STEP 4
            Return
        STEP 5
        Calculate length of list by calling method
            int len = getLength(list);
        STEP 6
        Calculate the previous one node of nth node from end
            int nodeNumsBefore = len - n;
        
        STEP 7
        Initialize curNode to the head of list
            LinkedListNode curNode = list.head;
        STEP 8
        Loop while nodeNumsBefore > 1, (the count of movement for curNode)
            STEP 9
            Advance curNode to next node
                curNode = curNode.next;
            STEP 10
            Decrease nodeNumsBefore by one
                nodeNumsBefore--;
        
        Now curNode point to the previous one node before nth node from end
        
        STEP 11
        Delete nth node from end
            curNode.next = curNode.next.next;
        STEP 12
        Decrease length by one
            list.length--;
    TIME:
        O(n), two pass of linked list is needed
    SPACE:
        O(1), no extra space is used 
*/ 
class RemoveNthNodeFromEndV1 {
    
    public static void removeNthNode(LinkedList list, int n) {
        // STEP 1
        if (n == list.length) {
            // STEP 2
            list.head = list.head.next;
            // STEP 3
            list.length--;
            // STEP 4
            return;
        }
        // STEP 5
        int len = getLength(list);
        // STEP 6
        int nodeNumsBefore = len - n;
        // STEP 7
        LinkedListNode curNode = list.head;
        // STEP 8
        while (nodeNumsBefore > 1) {
            // STEP 9
            curNode = curNode.next;
            // STEP 10
            nodeNumsBefore--;
        }
        // STEP 11
        curNode.next = curNode.next.next;
        // STEP 12
        list.length--;
    }
    
    public static int getLength(LinkedList list) {
        return list.length;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {1};
        int[] nums = {1, 2};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
    
        // int n = 5;
        // int n = 1;
        int n = 1;
        removeNthNode(list, n);
        System.out.println("after remove " + n + "th node, linked list: " + list.toString());
    }
}