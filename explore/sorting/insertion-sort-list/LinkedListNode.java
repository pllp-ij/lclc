class LinkedListNode {
    public int val;
    public LinkedListNode next;
    
    public LinkedListNode() {
        this.val = -1;
        this.next = null;
    }
    
    public LinkedListNode(int val) {
        this.val = val;
        this.next = null;
    }
    
    public LinkedListNode(int val, LinkedListNode next) {
        this.val = val;
        this.next = next;
    }
}
