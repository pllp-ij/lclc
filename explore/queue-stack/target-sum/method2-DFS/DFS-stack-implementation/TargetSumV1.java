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
        STEP 2
        Push Node(0, 0) into stack for start point which represent the initial value is 0 and idxInNums in nums is also 0
            stack.push(new Node(0, 0));
        STEP 3
        Loop while stack is not empty
            STEP 4
            Pop front node from stack and assign it to curNode
                curNode = stack.pop();
            
            The reason put STEP 5 and STEP 6 before STEP 7 and STEP 8 is that for final level of nodes, when it is popped from queue,
            the value of it is equal to target, but the idxInNums is equal to nums.length, which will result into bug when query by "nums[curNodeIdxInNums]",
            so it's better first check the found logic before check the validation of curNode
            
            STEP 5
            If isFound(nums, curNode, target), (meaning current popped node is equal to target and it is in lowest level of exploration tree)
                STEP 6
                Increase first element in result by one
                    (result[0])++;
            STEP 7
            If isNotValid(nums, curNode), (meaning idxInNums corresponding to curNode is outside the valid range of nums)
                STEP 8
                Skip current iteration and jump to next iteration
                    continue;
            STEP 9
            Iterate index from -1 to 1 every 2 steps with sign
                STEP 10
                Calculate nextNodeVal and nextNodeIdxInNums using curNode, nums, idxInNums, sign
                    int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                    int nextNodeIdxInNums = curNode.idxInNums + 1;
                STEP 11
                Push new Node(nextNodeVal, nextNodeIdxInNums) into stack
                    stack.push(new Node(nextNodeVal, nextNodeIdxInNums));
        
        -FUNC boolean isFound(int[] nums, Node curNode, int target)
        STEP 1
        If curNode.val == target and curNode.idxInNums == nums.length, (meaning current node value is equal to target and it is in lowest level of exploration tree)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
        -FUNC boolean isNotValid(int[] nums, Node curNode)
        STEP 1
        If curNode.idxInNums >= nums.length, (meaning current index corresponding to curNode is outside the valid range of nums length)
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

public class TargetSumV1 {
    
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
        // STEP 2
        stack.push(new Node(0, 0));
        // STEP 3
        while (!stack.isEmpty()) {
            // STEP 4
            curNode = stack.pop();
            // STEP 5
            if (isFound(nums, curNode, target)) {
                // STEP 6
                (result[0])++;
            }
            // STEP 7
            if (isNotValid(nums, curNode)) {
                // STEP 8
                continue;
            }
            // STEP 9
            for (int sign = -1; sign <= 1; sign += 2) {
                // STEP 10
                int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                int nextNodeIdxInNums = curNode.idxInNums + 1;
                // STEP 11
                stack.push(new Node(nextNodeVal, nextNodeIdxInNums));
            }
        }
    }
    
    public static boolean isFound(int[] nums, Node curNode, int target) {
        // STEP 1
        if (curNode.val == target && curNode.idxInNums == nums.length) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static boolean isNotValid(int[] nums, Node curNode) {
        // STEP 1
        if (curNode.idxInNums >= nums.length) {
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