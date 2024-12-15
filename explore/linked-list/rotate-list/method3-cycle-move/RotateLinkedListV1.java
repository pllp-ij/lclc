/*
    VARS:
        nodeBeforeK(LinkedListNode): the node before the kth node from end
        tailNode(LinkedListNode): the last node of original linked list
        lenList(int): the length of linked list
        nodesNumBeforeK(int): the total number of nodes before Kth node from end
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning linked list is empty or has only one node)
            STEP 2
            Directly return
        STEP 3
        Get the length of linked list and assign it to lenList and assign it to lenList
            int lenList = getLength(list);
        STEP 4
        Map k within length of linked list by calling method1
            k %= lenList;
        STEP 5
        If k == 0, (meaning k is multiple of the length of linked list)
            STEP 6
            Directly return
        STEP 7
        Initialize nodesNumBeforeK to (lenList - k)
            int nodesNumBeforeK = lenList - k;
        STEP 8
        Initialize nodeBeforeK to list.haed;
            LinkedListNode nodeBeforeK = list.head;
        Initialize tailNode to list.head
            LinkedListNode tailNode = list.head;
        STEP 9
        Loop while tailNode.next != null, (iterate each node in linked list)
            STEP 10
            If nodeBeforeK > 1, (meaning each movement before reach to the node before kth node from end)
                STEP 11
                Advance nodesNumBeforeK by one step
                    nodesNumBeforeK = nodesNumBeforeK.next;
                STEP 12
                Decrease nodeBeforeK by one
                    nodeBeforeK--;
            STEP 13
            Advance tailNode by one step
                tailNode = tailNode.next;
        
        Now nodeBeforeK is pointing at the node before kth node from end, and tailNode is pointing at the last node of linked list
        
        STEP 14
        Set the next field of tailNode to the head of initial linked list to make a cycle
            tailNode.next = list.head;
        STEP 15
        Set new head of linked list to nodeBeforeK.next
            list.head = nodeBeforeK.next;
        STEP 16
        Set the next field of nodeBeforeK to null
            nodeBeforeK.next = null;
    TIME:
        O(2n) ~ O(n), n for calculating length of linked list, n for finding nodeBeforeK and tailNode to make cycle
    SPACE:
        O(1), no extra space is used
*/

class RotateLinkedListV1 {
    
    public static void rotateList(LinkedList list, int k) {
        // STEP 1
        if (list.head == null || list.head.next == null) {
            // STEP 2
            return;
        }
        // STEP 3
        int lenList = getLength(list);
        // STEP 4
        k %= lenList;
        // STEP 5
        if (k == 0) {
            // STEP 6
            return;
        }
        // STEP 7
        int nodesNumBeforeK = lenList - k;
        // STEP 8
        LinkedListNode nodeBeforeK = list.head;
        LinkedListNode tailNode = list.head;
        // STEP 9
        while (tailNode != null && tailNode.next != null) {
            // STEP 10
            if (nodesNumBeforeK > 1) {
                // STEP 11
                nodeBeforeK = nodeBeforeK.next;
                // STEP 12
                nodesNumBeforeK--;
            }
            // STEP 13
            tailNode = tailNode.next;
        }
        // STEP 14
        tailNode.next = list.head;
        // STEP 15
        list.head = nodeBeforeK.next;
        // STEP 16
        nodeBeforeK.next = null;
    }
    
    public static int getLength(LinkedList list) {
        return list.length;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5};
        // int[] nums = {};
        // int[] nums = {1};
        int[] nums = {1, 2, 3, 4, 5};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        // int k = 2;
        // int k = 2;
        // int k = 2;
        int k = 8;
        rotateList(list, k);
        System.out.println("after rotate " + k + " steps to right, linked list: " + list.toString());
    }
}