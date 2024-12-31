import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Stack;

/*
    VARS:
        stack(Stack<Node>): a stack is used when do DFS
        result(int[]): for final return result
    DESCRIPTION:
        STEP 1
        Initialize result to {0}
        STEP 2
        Do DFS without return value using stack
            DFS(nums, target, result);
        STEP 3
        Return result[0];
        
        -FUNC void DFS(int[] nums, int target, int[] result)
        STEP 1
        Initialize Node class with val(int) and idxInNums(int) type
        Initialize stack to Stack<>();
        Initialize curNode to null with Node type
        Initialize nextNode to null with Node type
        STEP 2
        Push Node(0, 0) into stack for start point which represent the initial value is 0 and idxInNums in nums is also 0
            stack.push(new Node(0, 0));
        STEP 3
        Loop while stack is not empty
            STEP 4
            Pop front node from stack and assign it to curNode
                curNode = stack.pop();
            
            The reason put STEP 8 and STEP 9 before STEP 10 and STEP 11 is that for final level of nodes, when it is popped from queue,
            the value of it is equal to target, but the idxInNums is equal to nums.length, which will result into bug when query by "nums[curNodeIdxInNums]",
            so it's better first check the found logic before check the validation of curNode
            
            STEP 5
            Iterate index from -1 to 1 every 2 steps with sign
                STEP 6
                Calculate nextNodeVal and nextNodeIdxInNums using curNode, nums, idxInNums, sign
                    int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                    int nextNodeIdxInNums = curNode.idxInNums + 1;
                STEP 7
                Create a new Node(nextNodeVal, nextNodeIdxInNums) for using and assign it to nextNode
                    nextNode = new Node(nextNodeVal, nextNodeIdxInNums);
                STEP 8
                If isFound(nums, nextNode, target), (meaning next node is equal to target and it is in lowest level of exploration tree)
                    STEP 9
                    Increase first element in result by one
                        (result[0])++;
                STEP 10
                If isValid(nums, nextNode), (meaning idxInNums corresponding to nextNode is within the valid range of nums)
                    STEP 11
                    Push new Node(nextNodeVal, nextNodeIdxInNums) into stack
                        stack.push(new Node(nextNodeVal, nextNodeIdxInNums));
        
        -FUNC boolean isFound(int[] nums, Node nextNode, int target)
        STEP 1
        If nextNode.val == target and nextNode.idxInNums == nums.length, (meaning nextNode value is equal to target and it is in lowest level of exploration tree)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
        -FUNC boolean isValid(int[] nums, Node nextNode)
        STEP 1
        If nextNode.idxInNums < nums.length, (meaning current index corresponding to nextNode is within the valid range of nums length)
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(2^N), N is the number in nums, 2 is for two options for '+' or '-'
    SPACE:
        O(2^N), for max number pushed into queue
*/

class Node {
    public int val;
    public int idxInNums;
    
    public Node(int val, int idxInNums) {
        this.val = val;
        this.idxInNums = idxInNums;
    }
}

public class TargetSumV2 {
    
    public static int getExpressionNum(int[] nums, int target) {
        // STEP 1
        int[] result = {0};
        // STEP 2
        DFS(nums, target, result);
        // STEP 3
        return result[0];
    }
    
    public static void DFS(int[] nums, int target, int[] result) {
        // STEP 1
        Stack<Node> stack = new Stack<>();
        Node curNode = null;
        Node nextNode = null;
        // STEP 2
        stack.push(new Node(0, 0));
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            // STEP 5
            for (int sign = -1; sign <= 1; sign += 2) {
                // STEP 6
                int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                int nextNodeIdxInNums = curNode.idxInNums + 1;
                // STEP 7
                nextNode = new Node(nextNodeVal, nextNodeIdxInNums);
                // STEP 8
                if (isFound(nums, nextNode, target)) {
                    // STEP 9
                    (result[0])++;
                }
                // STEP 10
                if (isValid(nums, nextNode)) {
                    // STEP 11
                    stack.push(new Node(nextNodeVal, nextNodeIdxInNums));
                }
            }
        }
    }
    
    public static boolean isFound(int[] nums, Node nextNode, int target) {
        // STEP 1
        if (nextNode.val == target && nextNode.idxInNums == nums.length) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static boolean isValid(int[] nums, Node nextNode) {
        // STEP 1
        if (nextNode.idxInNums < nums.length) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 1, 1, 1};
        // int target = 3;
        
        // int[] nums = {1};
        // int target = 1;
        
        // int[] nums = {6, 20, 22, 38, 11, 15, 22, 30, 0, 17, 34, 29, 7, 42, 46, 49, 30, 7, 14, 5};
        // int target = 28;
        
        int[] nums = {16, 40, 9, 17, 49, 32, 30, 10, 38, 36, 31, 22, 3, 36, 32, 2, 26, 17, 30, 47};
        int target = 49;
        
        // int[] nums = {22, 36, 7, 44, 38, 32, 16, 32, 1, 16, 25, 45, 49, 45, 27, 9, 41, 31, 10, 15};
        // int target = 1;
        
        int result = getExpressionNum(nums, target);
        System.out.println("initial nums: " + Arrays.toString(nums));
        System.out.println("target: " + target);
        System.out.println("equal to " + target + " expression number is: " + result);
    }
}