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
    
    public void mergeList(int pos1, LinkedList list2, int pos2) {
        // check pos1 and pos2 validation
        if (pos1 < 0 || pos1 >= length ||
            pos2 < 0 || pos2 >= list2.length) {
            return;
        }
        // find node in list1 at index idx1
        LinkedListNode curNode1 = head;
        int idx1 = 0;
        while (idx1 < pos1) {
            curNode1 = curNode1.next;
            idx1++;
        }
        // for the whole linked list of list2 is shared with linked list of list1
        if (pos2 == 0) {
            list2.head = curNode1;
            return;
        }
        // find previous one node in list2 before index idx2
        LinkedListNode curNode2 = list2.head;
        int idx2 = 0;
        while (idx2 < pos2 - 1) {
            curNode2 = curNode2.next;
            idx2++;
        }
        curNode2.next = curNode1;
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