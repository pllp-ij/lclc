class LinkedListNode {
    int val;
    LinkedListNode next;
    
    public LinkedListNode(int x) {
        val = x;
        next = null;
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
    
    public void createCycle(int[] nums, int pos) {
        // create linked list from nums first
        this.createLinkedList(nums);
        // check pos validation
        if (pos < 0 || pos >= length || nums.length == 0) {
            return;
        }
        // find last node
        LinkedListNode posNode = null;
        LinkedListNode curNode = head;
        int idx = 0;
        while (idx < length - 1) {
            // find node at pos
            if (idx == pos) {
                posNode = curNode;
            }
            curNode = curNode.next;
            idx++;
        }
        // find last node at curNode and add cycle relationship
        curNode.next = posNode;
    }
    
    public String toString(int printLen) {
        StringBuilder str = new StringBuilder();
        LinkedListNode curNode = head;
        int idx = 0;
        while (idx < printLen) {
            str.append(curNode.val + " -> ");
            curNode = curNode.next;
            idx++;
        }
        str.append(" NULL");
        return str.toString();
    }
}