/*
    VARS:
        newNode(LinkedListNode): each new created copied node when iterating each node in original
        curNode(LinkedListNode): each node in original linked list
        curNodeCopied(LinkedListNode): for denoting each node in copied linked list when iterating each node in original linked list
        resultList(LinkedList): the final return result
    DESCRIPTION:
        STEP 1
        Initialize newNode to null
        Initialize curNode to list.head
        Initialize curNodeCopied to null
        Initialize resultList
        STEP 2
        If list.head == null, (for original linked list is empty)
            STEP 3
            Return resultList
        STEP 4
        Loop while curNode != null, (iterating each node in original linked list)
            STEP 5
            Create new node from curNode.val
                newNode = new LinkedListNode(curNode.val);
            STEP 6
            Insert newNode between curNode and curNode.next
                newNode.next = curNode.next;
                curNode.next = newNode;
            STEP 7
            Advance curNode by 2 steps (skip the newly inserted new node)
                curNode = curNode.next.next;
        
        Now all new nodes are inserted in between each interval of original linked list, the cloned and original linked list are interweaved, next finish creating random pointer relationships
        
        STEP 8
        Reset curNode to list.head again for second scan
            curNode = list.head;
        STEP 9
        Loop while curNode != null and curNode.next != null, (ensuring both curNode in original linked list and its corresponding node in copied linked list are not empty, actually when curNode is not empty, the curNode.next always non-empty, because the curNode.next is inserted accordingly in above logic) 
            STEP 10
            Assign curNode.next to curNodeCopied, (denote the corresponding node in copied linked list for curNode)
                curNodeCopied = curNode.next;
            STEP 11
            If curNode.random != null, (meaning current node in original linked list has random pointer)
                STEP 12
                Assign the next node of the node which is pointed by curNode's random pointer to the random pointer of curNodeCopied
                    curNodeCopied.random = curNode.random.next;
            STEP 13
            Advance curNode by 2 steps (skip the cloned node for curNode)
                curNode = curNode.next.next;
        
        Now all the next and random pointer of newly inserted cloned nodes have been set, next split the cloned and original linked list apart
        
        STEP 14
        Reset curNode to list.head again for third scan
            curNode = list.head;
        STEP 15
        Set the head of resultList to the next node of list.head
            resultList.head = curNode.next;
        STEP 16
        Loop while curNode != null and curNode.next != null, (ensuring both curNode in original linked list and its corresponding node in copied linked list are not empty, actually when curNode is not empty, the curNode.next always non-empty, because the curNode.next is inserted accordingly in above logic) 
            STEP 17
            Move curNodeCopied to curNode.next, (store the next node of curNode in copied linked list)
                curNodeCopied = curNode.next;
            STEP 18
            Set the next pointer of curNode to curNodeCopied.next to exclude the inserted node from original linked list
                curNode.next = curNodeCopied.next;
            STEP 19
            Advance curNode to next node by curNode.next, which is also belong to original linked list
                curNode = curNode.next;
            STEP 20
            If curNode != null, (for after processing the last node in original linked list, curNode == null now, so curNodeCopied should not link its next pointer to curNode.next, directly exit the while loop)
                STEP 21
                Set the next pointer of curNodeCopied to curNode.next to exclude the node belong to original linked list from copied linked list
                    curNodeCopied.next = curNode.next;
                STEP 22
                Advance curNodeCopied to next node by curNodeCopied.next, which is also belong to copied linked list
                    curNodeCopied = curNodeCopied.next;
        
        Now copied linked list and original linked are sperated apart and the head of copied linked list is save to the head of resultList
        
        STEP 23
        Return resultList
    TIME:
        O(3n) ~ O(n), two scan of original linked list
    SPACE:
        O(1), no extra space is used except the output space
*/

class CopyLinkedListWithRandomPointerV1 {
    
    public static LinkedList deepCopyList(LinkedList list) {
        
        // STEP 1
        LinkedListNode curNode = list.head;
        LinkedListNode curNodeCopied = null;
        LinkedListNode newNode = null;
        LinkedList resultList = new LinkedList();
        // STEP 2
        if (list.head == null) {
            // STEP 3
            return resultList;
        }
        // STEP 4
        while (curNode != null) {
            // STEP 5
            newNode = new LinkedListNode(curNode.val);
            // STEP 6
            newNode.next = curNode.next;
            curNode.next = newNode;
            // STEP 7
            curNode = curNode.next.next;
        }
        // STEP 8
        curNode = list.head;
        // STEP 9
        while (curNode != null && curNode.next != null) {
            // STEP 10
            curNodeCopied = curNode.next;
            // STEP 11
            if (curNode.random != null) {
                // STEP 12
                curNodeCopied.random = curNode.random.next;
            }
            // STEP 13
            curNode = curNode.next.next;
        }
        // STEP 14
        curNode = list.head;
        // STEP 15
        resultList.head = curNode.next;
        // STEP 16
        while (curNode != null && curNode.next != null) {
            // STEP 17
            curNodeCopied = curNode.next;
            // STEP 18
            curNode.next = curNodeCopied.next;
            // STEP 19
            curNode = curNode.next;
            // STEP 20
            if (curNode != null) {
                // STEP 21
                curNodeCopied.next = curNode.next;
                // STEP 22
                curNodeCopied = curNodeCopied.next;
            }
        }
        // STEP 23
        return resultList;
    }
    
    public static void main(String[] args) {
        // int[] nums = {7, 13, 11, 10, 1};
        // LinkedList list = new LinkedList();
        // list.createLinkedList(nums);
        // System.out.println("linked list: " + list.toString());
        // list.generateRandomLink(0, -1);
        // list.generateRandomLink(1, 0);
        // list.generateRandomLink(2, 4);
        // list.generateRandomLink(3, 2);
        // list.generateRandomLink(4, 0);
        // System.out.println("linked list by random: \n" + list.toStringByRandom());
        
        int[] nums = {};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        System.out.println("linked list by random: \n" + list.toStringByRandom());
        
        LinkedList listCopied = deepCopyList(list);
        System.out.println("after deep copy, linked list: " + listCopied.toString());
        System.out.println("after deep copy, linekd list by random: \n" + listCopied.toStringByRandom());
    }
}