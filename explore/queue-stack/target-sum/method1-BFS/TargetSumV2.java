import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        queue(Queue<Node>): a queue is used when do BFS
        result(int[]): for final return result
    DESCRIPTION:
        STEP 1
        Initialize result to {0}, (because when passing int value and want to get the changed value from called method, it best to pass the array pointer)
        STEP 2
        Do BFS without return value using nums, idxNums, target and result
            BFS(nums, target, result);
        STEP 3
        Return the first element of result as final result
            return result[0]
        
        -FUNC void BFS(int[] nums, int target, int[] result)
        STEP 1
        Initialize Node class with val(int) and idxInNums(int)
        Initialize queue to LinkedList<>();
        Initialize curNode to null with Node type
        Initialize nextNode to null with Node type
        STEP 2
        Push new Node(0, 0) into queue, (because set the initial value to 0 won't affect the calculating process and final result)
            queue.offer(new Node(0, 0));
        STEP 3
        Loop while queue is not empty,
            STEP 4
            Pop front node from queue and assign it to curNode
                curNode = queue.poll();
                
            The reason put STEP 8 and STEP 9 before STEP 10 and STEP 11 is that for final level of nodes, when it is popped from queue,
            the value of it is equal to target, but the idxInNums is equal to nums.length, which will result into bug when query by "nums[curNodeIdxInNums]",
            so it's better first check the found logic before check the validation of curNode
                
            STEP 5
            Iterate index from -1 to 1 every 2 steps with sign
                STEP 6
                Get the next number value and nextNodeIdxInNums, (the number value is to be added to curNodeVal from nums, sign, curNodeIdxInNums and nextNodeIdxInNums is for next iteration)
                    int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                    int nextNodeIdxInNums = curNode.idxInNums + 1;
                STEP 7
                Create new Node(nextNodeVal, nextNodeIdxInNums) and assign it to nextNode
                    nextNode = new Node(nextNodeVal, nextNodeIdxInNums);
                STEP 8
                If isFound(nums, nextNode, target), (check if curNode is satisfied with the condition the problem needed, this condition is customed, when it is not limited to lowest level node, it can find all nodes in the exploration tree)
                    STEP 9
                    Increase the first element in result by one, (meanging an expression is added to final result)
                        (result[0])++;
                STEP 10
                If isValid(nums, nextNode), (meaning nextNodeIdxInNums is within valid range of nums length)                    
                    STEP 11
                    Add nextNode which created from new Node(nextNodeVal, nextNodeIdxInNums) into queue
                        queue.offer(nextNode);

        -FUNC boolean isFound(int[] nums, Node nextNode, int target),
        STEP 1
        If nextNode.val == target and nextNode.idxInNums == nums.length, (meaning nextNode value is equal to target and nextNode node is in the lowest level)
            STEP 2
            Return true
        STEP 3
        Return false

        -FUNC boolean isValid(int[] nums, Node nextNode),
        STEP 1
        If nextNode.idxInNums < nums.length, (meaning nextNode's idxInNums is within the valid range of nums length)
            STEP 2
            return true;
        STEP 3
        return false;
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
        BFS(nums, target, result);
        // STEP 3
        return result[0];
    }
    
    public static void BFS(int[] nums, int target, int[] result) {
        // STEP 1
        Queue<Node> queue = new LinkedList<>();
        Node curNode = null;
        Node nextNode = null;
        // STEP 2
        queue.offer(new Node(0, 0));
        // STEP 3
        while (!queue.isEmpty()) {
            // STEP 4
            curNode = queue.poll();
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
                    queue.offer(nextNode);
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