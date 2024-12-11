/*
    NOTE:
        For each position of curNode, there are 2x2=4 conditions, it seems a decision tree, the difficulty contained in some array problems result from these,
        especially there are multiple options for each variable, for example, merely 3x3 condition will mess the code up, making it difficult for coders to think them out or write them out
    VARS:
        prevNode(LinkedListNode): a pointer always point to the previous one node of the node to be deleted
        curNode(LinkedListNode): a pointer always point to the current node to be deleted
    DESCRIPTION:
        STEP 1
        Initialize prevNode to null
        Initialize curNode to list.head
        STEP 2
        Loop while curNode != null, (meaning each node will be visited)
            If curNode value is equal to key value
                If prevNode != null, (meaning prevNode is the previous node of curNode)
                    STEP 3
                    Link the next field of prevNode to next node of curNode
                        prevNode.next = curNode.next;
                    STEP 4
                    Advance curNode by one step 
                        curNode = curNode.next;
                Else, (prevNode == null)
                    STEP 5
                    Advance curNode by one step
                        curNode = curNode.next;
            Else, (meaning curNode value is not equal to key value)
                If prevNode != null, (meaning prevNode is the previous node of curNode)
                    STEP 6
                    Advance both prevNode by one step
                        prevNode = prevNode.next;
                    STEP 7
                    Advance curNode by one step
                        curNode = curNode.next;
                Else, (prevNode == null)
                    STEP 8
                    Reset head of linked list to curNode
                        list.head = curNode;
                    STEP 9
                    Move prevNode to curNode
                        prevNode = curNode
                    STEP 10
                    Advance curNode to next node
                        curNode = curNode.next;
        STEP 11
        If prevNode == null, (meaning prevNode never be assigned with node in linked list, so all nodes in linked list is equal to key and be removed)
            STEP 12
            Reset head of linked list to null
                list.head = null;
    TIME:
        O(n), one pass of linked list
    SPACE:
        O(1), no extra space used
*/
class RemoveElementsV1 {
    
    public static void removeElements(LinkedList list, int key) {
        // STEP 1
        LinkedListNode prevNode = null;
        LinkedListNode curNode = list.head;
        // STEP 2
        while (curNode != null) {
            if (curNode.val == key) {
                if (prevNode != null) {
                    // STEP 3
                    prevNode.next = curNode.next;
                    // STEP 4
                    curNode = curNode.next;
                } else {
                    // STEP 5
                    curNode = curNode.next;
                }
            } else {
                if (prevNode != null) {
                    // STEP 6
                    prevNode = prevNode.next;
                    // STEP 7
                    curNode = curNode.next;
                } else {
                    // STEP 8
                    list.head = curNode;
                    // STEP 9
                    prevNode = curNode;
                    // STEP 10
                    curNode = curNode.next;
                }
            }
        }
        // STEP 11
        if (prevNode == null) {
            // STEP 12
            list.head = null;
        }
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 6, 3, 4, 5, 6};
        // int[] nums = {2, 2, 2, 3, 4, 5};
        // int[] nums = {2, 2, 2, 2, 2, 2};
        int[] nums = {2, 2, 2, 2, 2, 1};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        // int key = 6;
        // int key = 2;
        // int key = 2;
        int key = 2;
        removeElements(list, key);
        System.out.println("after remove " + key + ", linked list: " + list.toString());
    }
}