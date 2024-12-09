class DoubleLinkedListNode {

    public int val;
    public DoubleLinkedListNode prev;
    public DoubleLinkedListNode next;
    
    public DoubleLinkedListNode(int x) {
        val = x;
        prev = null;
        next = null;
    }

}

public class DoubleLinkedList {
    public int length;
    public DoubleLinkedListNode head;
    
    public DoubleLinkedList() {
        length = 0;
        head = null;
    }
    
    public void addAtHead(int val) {
        // create new node
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(val);
        // for non-empty linked list
        if (head != null) {
            newNode.next = head;
            head.prev = newNode;
        }
        // for both empty or non-empty linked list
        head = newNode;
        length++;
    }
    
    public void addAtTail(int val) {
        // create new node
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(val);
        // for empty linked list
        if (head == null) {
            head = newNode;
            length++;
            return;
        }
        // find the last node of linked list
        DoubleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < length - 1) {
            curNode = curNode.next;
            idx++;
        }
        curNode.next = newNode;
        newNode.prev = curNode;
        length++;
    }
    
    public void addAtIndex(int index, int val) {
        // check index validation
        if (index < 0 || index > length) {
            return;
        }
        // if index == 0, equal to insert at the beginning
        if (index == 0) {
            this.addAtHead(val);
            return;
        }
        // if index == length, equal to insert at the end
        if (index == length) {
            this.addAtTail(val);
            return;
        }
        // create new node
        DoubleLinkedListNode newNode = new DoubleLinkedListNode(val);
        // find the node at index
        DoubleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < index) {
            curNode = curNode.next;
            idx++;
        }
        newNode.next = curNode;
        curNode.prev.next = newNode;
        newNode.prev = curNode.prev;
        curNode.prev = newNode;
        length++;
    }
    
    public int get(int index) {
        // check index validation
        if (index < 0 || index >= length) {
            return -1;
        }
        // get node at index
        DoubleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < index) {
            curNode = curNode.next;
            idx++;
        }
        return curNode.val;
    }
    
    public void deleteAtIndex(int index) {
        // check index validation
        if (index < 0 || index >= length) {
            return;
        }
        // if index == 0, move the head
        if (index == 0) {
            head = head.next;
            if (head != null) {
                head.prev = null;
            }
            return;
        }
        // find node at index
        DoubleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < index) {
            curNode = curNode.next;
            idx++;
        }
        curNode.prev.next = curNode.next;
        // for curNode is the last node of linked list
        if (curNode.next != null) {
            curNode.next.prev = curNode.prev;
        }
        length--;
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        DoubleLinkedListNode curNode = head;
        while (curNode != null) {
            str.append(curNode.val + " -> ");
            curNode = curNode.next;
        }
        str.append(" NULL");
        return str.toString();
    }
    
    public static void main(String[] args) {
        DoubleLinkedList obj = new DoubleLinkedList();
        System.out.println(obj.toString());
        obj.addAtHead(1);
        System.out.println(obj.toString());
        obj.addAtTail(3);
        System.out.println(obj.toString());
        obj.addAtIndex(1, 2);
        System.out.println(obj.toString());
        int val = obj.get(1);
        System.out.println("val at index 1 is: " + val);
        obj.deleteAtIndex(1);
        System.out.println(obj.toString());
        val = obj.get(1);
        System.out.println("val at index 1 is: " + val);
    }
    
}