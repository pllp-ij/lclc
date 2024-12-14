class LinkedListNode {
    int val;
    LinkedListNode next;
    LinkedListNode random;
    
    public LinkedListNode(int x) {
        val = x;
        next = null;
        random = null;
    }
}

public class LinkedList {
    public int length;
    public LinkedListNode head;
    
    public LinkedList() {
        length = 0;
        head = null;
    }
    
    public void createLinkedList(int[] nums) {
        // iterate each number
        for (int i = 0; i < nums.length; i++) {
            // create new node
            LinkedListNode newNode = new LinkedListNode(nums[i]);
            // add each number at tail
            this.addAtTail(nums[i]);
        }
    }
    
    public void addAtTail(int val) {
        // create new node
        LinkedListNode newNode = new LinkedListNode(val);
        // for empty linked list
        if (head == null) {
            head = newNode;
            length++;
            return;
        }
        // for non empty linked list
        // find last node
        LinkedListNode curNode = head;
        int idx = 0;
        while (idx < length - 1) {
            curNode = curNode.next;
            idx++;
        }
        curNode.next = newNode;
        length++;
    }
    
    public void generateRandomLink(int start, int end) {
        LinkedListNode startNode = head;
        LinkedListNode endNode = null;
        LinkedListNode curNode = head;
        int idx = 0;
        while (idx < length) {
            if (idx == start) {
                startNode = curNode;
            }
            if (idx == end) {
                endNode = curNode;
            }
            curNode = curNode.next;
            idx++;
        }
        startNode.random = endNode;
    }

    public String toString() {
        StringBuilder str = new StringBuilder();
        LinkedListNode curNode = head;
        while (curNode != null) {
            str.append(curNode.val + " -> ");
            curNode = curNode.next;
        }
        str.append("NULL");
        return str.toString();
    }
    
    public String toStringByRandom() {
        LinkedListNode startNode = head;
        LinkedListNode curNode = null;
        StringBuilder str = new StringBuilder();
        
        while (startNode != null) {
            // print each chain from idx to the end
            curNode = startNode;
            while (curNode != null) {
                str.append(curNode.val + " -> ");
                curNode = curNode.random;
            }
            str.append("NULL\n");
            
            // update startNode to next node
            startNode = startNode.next;
        }
        
        return str.toString();
    }
}