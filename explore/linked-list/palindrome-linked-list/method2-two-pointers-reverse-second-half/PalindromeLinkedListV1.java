/*
    VARS:
        nodeFast(LinkedListNode): a fast pointer with 2 steps each iteration
        nodeSlow(LinkedListNode): a slow pointer with 1 step each iteration
        prevNode(LinkedListNode): store the previous node for reversing the second half of linked list
        curNode(LinkedListNode): current iterated node
        nextNode(LinkedListNode): the next node curNode will move to
    DESCRIPTION:
        STEP 1
        If list.head == null or list.head.next == null, (meaning linked list is empty or has only one node)
            STEP 2
            Nothing to do, directly return true
                return true
        STEP 3
        Initialize nodeSlow to list.head
        Initialize nodeFast to nodeSlow.next
        STEP 4
        Loop while nodeFast != null and nodeFast.next != null, (meaning before nodeFast reach the last node in linked list, the first condition is for odd length of linked list, the second condition is for even length of linked list)
            STEP 5
            Advance nodeFast by two steps
                nodeFast = nodeFast.next.next;
            STEP 6
            Advance nodeSlow by one step
                nodeSlow = nodeSlow.next;
                
        Now nodeSlow is pointing to the previous one node of the head of second half linked list, in the following will reverse the second half of linked list
        
        STEP 7
        Initialize prevNode to null
        Initialize curNode to nodeSlow.next
        Initialize nextNode to null
        STEP 8
        Loop while curNode != null, (meaning each node will be visited)
            STEP 9
            Reset nextNode to curNode.next
                nextNode = curNode.next
            STEP 10
            Link the next field of curNode to prevNode
                curNode.next = prevNode;
            STEP 11
            Advance prevNode to curNode
                prevNode = curNode
            STEP 12
            Advance curNode to nextNode
                curNode = nextNode;
        
        Now prevNode is pointing to the head of newly reversed linked list, the comparasion between nodes pointed by curNode and nextNode in the following
        
        STEP 13
        Reset curNode to the initial head of linked list
            curNode = list.head;
        STEP 14
        Use nextNode to iterate reversed second half linked list from head to tail
            nextNode = prevNode
        STEP 15
        Loop while nextNode != null,
            STEP 16
            If curNode.val != nextNode.val, (meaning there is a non-matching pair)
                STEP 17
                Return false
            STEP 18
            Advance both curNode and nextNode to next node
                curNode = curNode.next;
                nextNode = nextNode.next;
        
        Now nextNode == null, meaning all nodes in reversed second half already checked and is passed
        STEP 19
        Return true
    TIME:
        O(n + n / 2 + n /2) ~ O(n), first n is the steps of nodeFast, then n / 2 is for reverseing second half, the last n / 2 is for comparing
    SPACE:
        O(1), no extra space is used
*/

class PalindromeLinkedListV1 {
    
    public static boolean checkPalindrome(LinkedList list) {
        // STEP 1
        if (list.head == null || list.head.next == null) {
            // STEP 2
            return true;
        }
        // STEP 3
        LinkedListNode nodeSlow = list.head;
        LinkedListNode nodeFast = nodeSlow.next;
        // STEP 4
        while (nodeFast != null && nodeFast.next != null) {
            // STEP 5
            nodeFast = nodeFast.next.next;
            // STEP 6
            nodeSlow = nodeSlow.next;
        }
        // STEP 7
        LinkedListNode prevNode = null;
        LinkedListNode curNode = nodeSlow.next;
        LinkedListNode nextNode = null;
        // STEP 8
        while (curNode != null) {
            // STEP 9
            nextNode = curNode.next;
            // STEP 10
            curNode.next = prevNode;
            // STEP 11
            prevNode = curNode;
            // STEP 12
            curNode = nextNode;
        }
        // STEP 13
        curNode = list.head;
        // STEP 14
        nextNode = prevNode;
        // STEP 15
        while (nextNode != null) {
            // STEP 16
            if (curNode.val != nextNode.val) {
                // STEP 17
                return false;
            }
            // STEP 18
            curNode = curNode.next;
            nextNode = nextNode.next;
        }
        // STEP 19
        return true;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 2, 1};
        // int[] nums = {1, 2, 4, 4, 2, 1};
        // int[] nums = {1, 2, 5, 7, 2, 1};
        // int[] nums = {};
        // int[] nums = {1};
        // int[] nums = {2, 3};
        int[] nums = {1, 1};
        LinkedList list = new LinkedList();
        list.createLinkedList(nums);
        System.out.println("linked list: " + list.toString());
        
        boolean isPalindrome = checkPalindrome(list);
        if (isPalindrome) {
            System.out.println("linked list is palindrome");
        } else {
            System.out.println("linked list is not palindrome");
        }
    }
}