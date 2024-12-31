import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        result(int[]): for final return result
        curNode(Node): a Node type var to store the node value and the idxInNums for recursion
    DESCRIPTION:
        STEP 1
        Initialize result to {0};
        STEP 2
        Do DFS without return value using recursion
            DFS(nums, new Node(0, 0), target, result);
        STEP 3
        Return result[0];
        
        -FUNC void DFS(int[] nums, Node curNode, int target, int[] result)
        STEP 1
        Iterate index from -1 to 1 every 2 steps with sign
            STEP 2
            Create nextNodeVal, nextNodeIdxInNums from curNode and nums and sign
                int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
                int nextNodeIdxInNums = curNode.idxInNums + 1;
            STEP 3
            Create new Node(nextNodeVal, nextNodeIdxInNums) from above two vars
                Node nextNode = new Node(nextNodeVal, nextNodeIdxInNums);
            STEP 4
            If isFound(nums, nextNode, target), (meaning the target is found in next level of recursion)
                STEP 5
                Increase first element in result by one
                    (result[0])++;
            STEP 6
            If isValid(nums, nextNode), (meaning if nextNode is valid)
                STEP 7
                Recursively calling method
                    DFS(nums, nextNode, target, result);
        
        -FUNC boolean isFound(int[] nums, Node nextNode, int target)
        STEP 1
        If nextNode.val == target and nextNode.idxInNums == nums.length, (meaning current value is equal to target and curNode is in lowest level)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
        -FUNC boolean isValid(int[] nums, Node nextNode)
        STEP 1
        If nextNode.idxInNums < nums.length, (meaning the idxInNums for next steps is outside the valid range of nums)
            STEP 2
            Return true
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
        DFS(nums, new Node(0, 0), target, result);
        // STEP 3
        return result[0];
    }
    
    public static void DFS(int[] nums, Node curNode, int target, int[] result) {
        // STEP 1
        for (int sign = -1; sign <= 1; sign += 2) {
            // STEP 2
            int nextNodeVal = curNode.val + sign * nums[curNode.idxInNums];
            int nextNodeIdxInNums = curNode.idxInNums + 1;
            // STEP 3
            Node nextNode = new Node(nextNodeVal, nextNodeIdxInNums);
            // STEP 4
            if (isFound(nums, nextNode, target)) {
                // STEP 5
                (result[0])++;
            }
            // STEP 6
            if (isValid(nums, nextNode)) {
                // STEP 7
                DFS(nums, nextNode, target, result);
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