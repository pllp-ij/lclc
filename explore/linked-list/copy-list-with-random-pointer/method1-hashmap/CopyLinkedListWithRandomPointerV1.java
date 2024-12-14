import java.util.HashMap;

/*
    VARS:
        cacheHash(HashMap<LinkedListNode, LinkedListNode>): a hash map from original node to copied node for each pair
        curNode(LinkedListNode): for iterating each node in original linked list
        nextNodeCopied(LinkedListNode): the corresponding node to curNode.next in cacheHash
        curNodeCopied(LinkedListNode): the corresponding node to curNode in cacheHash
        randomNodeCopied(LinkedListNode): the corresponding node to curNode.random in cacheHash
        resultList(LinkedList): the final return result linked list
    DESCRIPTION:
        STEP 1
        Initialize cacheHash to HashMap<LinkedListNode, LinkedListNode>
        Initialize curNode to list.head
        Initialize nextNodeCopied to null
        Initialize curNodeCopied to null
        Initialize randomNodeCopied to null
        Initialize resultList
        STEP 2
        Loop while curNode != null, (meaning iterate each node in original linked list)
            STEP 3
            Set curNode as key and a new node as value in cacheHash
                cacheHash.put(curNode, new LinkedListNode(curNode.val));
            STEP 4
            Advance curNode to next node
                curNode = curNode.next;
        STEP 5
        Reset curNode to list.head again for second scan
            curNode = list.head;
        STEP 6
        Loop while curNode != null, (meaning iterate each node in original linked list again)
            STEP 7
            Get the corresponding node to curNode in cacheHash and assign it to curNodeCopied
                curNodeCopied = cacheHash.get(curNode);
            If curNode.next != null, (meaning current node in original linked list has next pointer)
                STEP 8
                Get the corresponding node to curNode.next in cacheHash and assign it to nextNodeCopied
                    nextNodeCopied = cacheHash.get(curNode.next);
            Else, (meaning current node is the last node in original linked list)
                STEP 9
                Assign nextNodeCopied to null
                    nextNodeCopied = null;
            STEP 10
            Asssign the next pointer of curNodeCopied to nextNodeCopied
                curNodeCopied.next = nextNodeCopied;
                    
            Now generate the relationship of random pointer

            If curNode.random != null, (meaning current node in original linked list has random pointer)
                STEP 11
                Get the corresponding node to curNode.random in cacheHash and assign it to randomNodeCopied
                    randomNodeCopied = cacheHash.get(curNode.random);
            Else, (meaning current node's random is null)
                STEP 12
                Assign randomNodeCopied to null
                    randomNodeCopied = null
            STEP 13
            Assign the random pointer of curNodeCopied to randomNodeCopied to generate the random link relationship in cacheHash for current iteration
                curNodeCopied.random = randomNodeCopied;
            STEP 14
            Advance curNode to next node
                curNode = curNode.next;
        STEP 15
        Move the head of resultList to the corresponding head of list.head in cacheHash
            resultList.head = cacheHash.get(list.head);
        STEP 16
        Return resultList
    TIME:
        O(2n) ~ O(n), two scan of original linked list
    SPACE:
        O(n), extra hash map is used except output space
*/

class CopyLinkedListWithRandomPointerV1 {
    
    public static LinkedList deepCopyList(LinkedList list) {
        // STEP 1
        HashMap<LinkedListNode, LinkedListNode> cacheHash = new HashMap<>();
        LinkedListNode curNode = list.head;
        LinkedListNode curNodeCopied = null;
        LinkedListNode nextNodeCopied = null;
        LinkedListNode randomNodeCopied = null;
        LinkedList resultList = new LinkedList();
        // STEP 2
        while (curNode != null) {
            // STEP 3
            cacheHash.put(curNode, new LinkedListNode(curNode.val));
            // STEP 4
            curNode = curNode.next;
        }
        // STEP 5
        curNode = list.head;
        // STEP 6
        while (curNode != null) {
            // STEP 7
            curNodeCopied = cacheHash.get(curNode);
            if (curNode.next != null) {
                // STEP 8
                nextNodeCopied = cacheHash.get(curNode.next);
            } else {
                // STEP 9
                nextNodeCopied = null;
            }
            // STEP 10
            curNodeCopied.next = nextNodeCopied;
            if (curNode.random != null) {
                // STEP 11
                randomNodeCopied = cacheHash.get(curNode.random);
            } else {
                // STEP 12
                randomNodeCopied = null;
            }
            // STEP 13
            curNodeCopied.random = randomNodeCopied;
            // STEP 14
            curNode = curNode.next;
        }
        // STEP 15
        resultList.head = cacheHash.get(list.head);
        // STEP 16
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