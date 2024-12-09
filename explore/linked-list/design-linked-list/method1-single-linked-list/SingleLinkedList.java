class SingleLinkedList {
    // length of linked list
    public int length;
    public SingleLinkedListNode head;
    
    public SingleLinkedList() {
        // initialize head to null and length to 0
        head = null;
        length = 0;
    }
    
    public int get(int index) {
        // check index validation
        if (index < 0 || index >= length) {
            return -1;
        }
        // iterate from head node to index
        SingleLinkedListNode curNode = head;
        while (index > 0) {
            curNode = curNode.next;
            index--;
        }
        return curNode.val;
    }
    
    public void addAtHead(int val) {
        // create new node
        SingleLinkedListNode newNode = new SingleLinkedListNode(val);
        // if linked list is not empty
        if (head != null) {
            newNode.next = head;
        }
        // for both empty or non-empty linked list
        head = newNode;
        length++;
    }
    
    public void addAtTail(int val) {
        // create new node
        SingleLinkedListNode newNode = new SingleLinkedListNode(val);
        // if linked list is empty
        if (head == null) {
            head = newNode;
            length++;
            return;
        }
        // for non-empty linked list
        SingleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < length - 1) {
            curNode = curNode.next;
            idx++;
        }
        curNode.next = newNode;
        length++;
    }
    
    public void addAtIndex(int index, int val) {
        // check index validation
        if (index < 0 || index > length) {
            return;
        }
        // if index == 0, equal to insert at the head
        if (index == 0) {
            this.addAtHead(val);
            return;
        }
        // if index == length, equal to insert at the end
        if (index == length) {
            this.addAtTail(val);
            return;
        }
        // for not head nor tail
        SingleLinkedListNode newNode = new SingleLinkedListNode(val);
        // find previous one node of node at index
        SingleLinkedListNode curNode = head;
        int idx = 0;
        while (idx < index - 1) {
            curNode = curNode.next;
        }
        // insert new node into linked list
        newNode.next = curNode.next;
        curNode.next = newNode;
        length++;
    }
    
    public void deleteAtIndex(int index) {
        // check index validation
        if (index < 0 || index >= length) {
            return;
        }
        // delete first node
        if (index == 0) {
            head = head.next;
            length--;
            return;
        }
        // for not head node, find previous one node before index
        int idx = 0;
        SingleLinkedListNode curNode = head;
        while (idx < index - 1) {
            curNode = curNode.next;
        }
        curNode.next = curNode.next.next;
        length--;
    }
    
    public static void main(String[] args) {
        // SingleLinkedList obj = new SingleLinkedList();
        // System.out.println(obj.toString());
        // obj.addAtHead(1);
        // System.out.println(obj.toString());
        // obj.addAtTail(3);
        // System.out.println(obj.toString());
        // obj.addAtIndex(1, 2);
        // System.out.println(obj.toString());
        // int val = obj.get(1);
        // System.out.println("val at index 1 is: " + val);
        // obj.deleteAtIndex(1);
        // System.out.println(obj.toString());
        // val = obj.get(1);
        // System.out.println("val at index 1 is: " + val);
        
        
        SingleLinkedList obj = new SingleLinkedList();
        System.out.println(obj.toString());
        obj.addAtHead(1);
        System.out.println(obj.toString());
        int val = obj.get(0);
        System.out.println("val at index 1 is: " + val);
    }
    
    public String toString() {
        StringBuilder str = new StringBuilder();
        SingleLinkedListNode curNode = head;
        while (curNode != null) {
            str.append(curNode.val + " -> ");
            curNode = curNode.next;
        }
        str.append(" NULL");
        return str.toString();
    }
}