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
    public LinkedListNode tail;
    
    public LinkedList() {
        length = 0;
        head = null;
        tail = null;
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
            tail = head;
            length++;
            return;
        }
        // for non empty linked list
        // find last node
        tail.next = newNode;
        tail = tail.next;
        length++;
    }
    
    public void toBeCyclic(int n) {
        if (head == null) {
            return;
        }
        // link tail to head to create cycle
        tail.next = head;
        // move head forward n steps
        for (int i = 0; i < n; i++) {
            head = head.next;
            tail = tail.next;
        }
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
    
    public String toString(int len) {
        StringBuilder str = new StringBuilder();
        LinkedListNode curNode = head;
        str.append("NULL -> ");
        int idx = 0;
        while (idx < len) {
            str.append(curNode.val + " -> ");
            curNode = curNode.next;
            idx++;
        }
        str.append("NULL");
        return str.toString();
    }
}