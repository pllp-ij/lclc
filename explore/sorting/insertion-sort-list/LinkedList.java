class LinkedList {
    LinkedListNode head;
    
    public LinkedList() {
        this.head = null;
    }
    
    public LinkedList(int[] nums) {
        head = null;
        for (int idx = 0; idx < nums.length; idx++) {
            LinkedListNode curNode = new LinkedListNode(nums[idx]);
            insertTail(curNode);
        }
    }
    
    public void insertTail(LinkedListNode node) {
        // check if head is null, directly assign node to head then return
        if (head == null) {
            head = node;
            return;
        }
        // set curNode equal to head to iterate to tail node of linked list
        LinkedListNode curNode = head;
        // loop
        while (curNode != null && curNode.next != null) {
            curNode = curNode.next;
        }
        // now curNode is pointing at the tail node of linked list, assign node to curNode to link it to linked list
        curNode.next = node;
    }
    
    public String toString() {
        // initialize result var
        StringBuilder result = new StringBuilder();
        // set curNode equal to head to iterate each node of linked list
        LinkedListNode curNode = head;
        // loop
        while (curNode != null) {
            result.append(curNode.val + " -> ");
            curNode = curNode.next;
        }
        result.append("null");
        return result.toString();
    }
}