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
        LinkedListNode curNode = head;
        tail.next = newNode;
        tail = tail.next;
        length++;
    }
    
    public void linkAtTail(LinkedListNode head2) {
        // for list2 is empty
        if (head2 == null) {
            return;
        }
        // for current linked list is empty
        if (head == null) {
            head = head2;
            tail = head;
        } else {
            // for current linked list is not empty
            tail.next = head2;
            tail = tail.next;
        }
        // update the tail pointer and length
        while (tail != null && tail.next != null) {
            tail = tail.next;
            length++;
        }
        length++;
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
}