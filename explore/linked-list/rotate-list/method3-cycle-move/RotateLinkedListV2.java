/*
    NOTE:
        Compared to V1, V2 use two pointers to find the node before kth node from end, and the last node instead of using (length - k - 1) movements
    VARS:
        lenList(int): the length of the linked list
        nodeBeforeK(LinkedListNode): the node before kth node from end
        tailNode(LinkedListNode): the last node of linked list
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning linked list is empty or has only one node)
            STEP 2
            Directly return
        STEP 3
        Calculate the length of linked list and assign it to lenList
            int lenList = getLength(list)
        STEP 4
        Map k within the length of linked list to make sure the minimum effective roration
            k %= lenList;
        STEP 5
        If k == 0, (meaning k is multiple of the length of linked list)
            STEP 6
            Directly return
        STEP 7
        Initialize nodeBeforeK to list.head
        Initialize tailNode to list.head
        STEP 8
        Loop while k > 0, (the total number of movements for tailNode ahead of nodeBeforeK)
            STEP 9
            Advance tailNode by one step
                tailNode = tailNode.next;
            STEP 10
            Decrease k by one
                k--;
        STEP 11
        Loop while tailNode != null and tailNode.next != null, (reach the last node of linked list)
            STEP 12
            Advance tailNode to next node
                tailNode = tailNode.next;
            STEP 13
            Advance nodeBeforeK to next node
                nodeBeforeK = nodeBeforeK.next;
        STEP 14
        Link the next field of tailNode to list.head to make a cycle
            tailNode.next = list.head
        STEP 15
        Move the new head of linked list to nodeBeforeK.next
            list.head = nodeBeforeK.next;
        STEP 16
        Set the next field of nodeBeforeK to null to end the new linkde list
            nodeBeforeK.next = null
    TIME:
        O(2n) ~ O(n), n for get the length pf linked list, n for moving nodeBeforeK and tailNode
    SPACE:
        O(1), no extra space is used
*/

class RotateLinkedListV2 {
    
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
        LinkedListNode nodeBeforeK = list.head;
        LinkedListNode tailNode = list.head;
        // STEP 8
        while (k > 0) {
            // STEP 9
            tailNode = tailNode.next;
            // STEP 10
            k--;
        }
        // STEP 11
        while (tailNode != null && tailNode.next != null) {
            // STEP 12
            tailNode = tailNode.next;
            // STEP 13
            nodeBeforeK = nodeBeforeK.next;
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
        int[] nums = {1};
        // int[] nums = {1, 2, 3, 4, 5};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        // int k = 2;
        // int k = 2;
        int k = 2;
        // int k = 8;
        rotateList(list, k);
        System.out.println("after rotate " + k + " steps to right, linked list: " + list.toString());
    }
}