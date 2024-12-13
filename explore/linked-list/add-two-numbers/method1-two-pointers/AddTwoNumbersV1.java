/*
    VARS:
        carryFromLast(int): the carry over value from last digit
        curNode1(LinkedListNode): the pointer node iterating list1
        curNode2(LinkedListNode): the pointer node iterating list2
        newNode(LinkedListNode): the new node created from remaining value of current iterated digit
        mergedList(LinkedList): the finally return merged linked list
    DESCRIPTION:
        STEP 1
        Initialize carryFromLast to 0
        Initialize curNode1 to list1.head
        Initialize curNode2 to list2.head
        Initialize newNode to null
        Initialize mergedList
        Initialize mergedList.head to a dummy node with value of -1
        Initialize mergedList.tail to mergedList.head
        STEP 2
        Loop while curNode1 != null or curNode2 != null,
            STEP 3
            If curNode1 != null, (meaning there is value in curNode1 pointer)
                STEP 4
                Add value of curNode1 to carryFromLast to accumulate up the sum
                    carryFromLast += curNode1.val
            STEP 5
            If curNode2 != null, (meaning there is value in curNode2 pointer)
                STEP 6
                Add value of curNode2 to carryFromLast to accumulate up the sum
                    carryFromLast + curNode2.val
            STEP 7
            Calculate the remainning value of current iterated digit which is (carryFromLast % 10)
                newNode = new LinkedListNode(carryFromLast % 10);
            STEP 8
            Update carryFromLast for next iteration which is (carryFromLast / 10)
                carryFromLast /= 10;
            
            Now add the new node to result linked list
            
            STEP 9
            Link the next field of mergedList.tail to newNode to add current new node to the result linked list
                mergedList.tail.next = newNode
            STEP 10
            Advance mergedList.tail to next node
                mergedList.tail = mergedList.tail.next;
                
            Now update curNode1 and curNode2 pointer, only update them when they are not null
            
            STEP 11
            If curNode1 != null,
                STEP 12
                curNode1 = curNode1.next;
            STEP 13
            If curNode2 != null,
                STEP 14
                curNode2 = curNode2.next;
        
        Now both curNode1 == null and curNode2 == null, check if carryFromLast equals to 1 in the following
        STEP 15
        If carryFromLast == 1, (meaning there is the last digit with value of 1 to be added to the tail of result linked list)
            STEP 16
            Create new node from carryFromLast value
                newNode = new LinkedListNode(carryFromLast)
            STEP 17
            Link the next field of mergedList.tail to newNode to add new node to result linked list
                mergedList.tail.next = newNode;
            STEP 18 (Optional)
            Advance mergedList.tail to next node to keep tail pointer always point the last node
                mergedList.tail = mergedList.tail.next;
        STEP 19
        Update mergedList.head to its next node to exclude dummy node
            mergedList.head = mergedList.head.next;
        STEP 20
        Return mergedList
    TIME:
        O(max(M, N)), M is the number of nodes in list1, N is the number of nodes in list2
    SPACE:
        O(max(M, N) + 1) ~ O(max(M, N)), for final return result
*/
class AddTwoNumbersV1 {
    
    public static LinkedList addTwoNumbers(LinkedList list1, LinkedList list2) {
        // STEP 1
        int carryFromLast = 0;
        LinkedListNode curNode1 = list1.head;
        LinkedListNode curNode2 = list2.head;
        LinkedListNode newNode = null;
        LinkedList mergedList = new LinkedList();
        mergedList.head = new LinkedListNode(-1);
        mergedList.tail = mergedList.head;
        // STEP 2
        while (curNode1 != null || curNode2 != null) {
            // STEP 3
            if (curNode1 != null) {
                // STEP 4
                carryFromLast += curNode1.val;
            }
            // STEP 5
            if (curNode2 != null) {
                // STEP 6
                carryFromLast += curNode2.val;
            }
            // STEP 7
            newNode = new LinkedListNode(carryFromLast % 10);
            // STEP 8
            carryFromLast /= 10;
            // STEP 9
            mergedList.tail.next = newNode;
            // STEP 10
            mergedList.tail = mergedList.tail.next;
            // STEP 11
            if (curNode1 != null) {
                // STEP 12
                curNode1 = curNode1.next;
            }
            // STEP 13
            if (curNode2 != null) {
                // STEP 14
                curNode2 = curNode2.next;
            }
        }
        // STEP 15
        if (carryFromLast == 1) {
            // STEP 16
            newNode = new LinkedListNode(carryFromLast);
            // STEP 17
            mergedList.tail.next = newNode;
            // STEP 18
            mergedList.tail = mergedList.tail.next;
        }
        // STEP 19
        mergedList.head = mergedList.head.next;
        // STEP 20
        return mergedList;
    }
    
    public static void main(String[] args) {
        // int[] nums1 = {2, 4, 3};
        // int[] nums2 = {5, 6, 4};
        
        // int[] nums1 = {6, 7, 9};
        // int[] nums2 = {5, 6, 4, 2, 1};
        
        // int[] nums1 = {6, 7, 9};
        // int[] nums2 = {5, 6, 4, 9};
        
        // int[] nums1 = {6, 7, 9};
        // int[] nums2 = {5, 6, 4, 9, 2};
        
        // int[] nums1 = {0};
        // int[] nums2 = {0};
        
        // int[] nums1 = {5};
        // int[] nums2 = {6};
        
        int[] nums1 = {9, 9, 9, 9, 9, 9, 9};
        int[] nums2 = {9, 9, 9, 9};
        LinkedList list1 = new LinkedList();
        list1.createLinkedList(nums1);
        LinkedList list2 = new LinkedList();
        list2.createLinkedList(nums2);
        System.out.println("linked list1: " + list1.toString());
        System.out.println("linked list1: " + list2.toString());
        
        LinkedList addedList = addTwoNumbers(list1, list2);
        System.out.println("after added, linked list: " + addedList.toString());
    }
}