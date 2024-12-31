import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        result(int[]): for final return result
        startVal(int): the start value for exoression path, also the root value of exploration tree
        idxInNums(int): the index in nums start from 0
        curNode(Node): a Node type var to store the node value and the idxInNums for recursion
    DESCRIPTION:
        STEP 1
        Initialize result to {0};
        Initialize startVal to 0
        Initialize idxInNums to 0
        STEP 2
        Do DFS without return value using recursion
            DFS(nums, idxInNums, startVal, target, result);
        STEP 3
        Return result[0];
        
        -FUNC void DFS(int[] nums, int idxInNums, int curValue, int target, int[] result)
        STEP 1
        Iterate index from -1 to 1 every 2 steps with sign
            STEP 2
            Create nextValue, nextIdxInNums from nums, idxInNums, curValue and sign
                int nextValue = curValue + sign * nums[idxInNums];
                int nextIdxInNums = idxInNums + 1;
            STEP 3
            If isFound(nums, nextIdxInNums, nextValue, target), (meaning the target is found in next level of recursion)
                STEP 4
                Increase first element in result by one
                    (result[0])++;
            STEP 5
            If isValid(nums, nextIdxInNums), (meaning if nextNode is not valid)
                STEP 6
                Recursively calling method
                    DFS(nums, nextIdxInNums, nextValue, target, result);
        
        -FUNC boolean isFound(int[] nums, int nextIdxInNums, int nextValue, int target)
        STEP 1
        If curValue == target and idxInNums == nums.length, (meaning current value is equal to target and the node corresponding to curValue is in lowest level of exploration tree)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
        -FUNC boolean isValid(int[] nums, int nextIdxInNums)
        STEP 1
        If nextIdxInNums < nums.length, (meaning the nextIdxInNums for next steps is within the valid range of nums)
            STEP 2
            Return true
        STEP 3
        Return false;
    TIME:
        O(2^N), N is the number in nums, 2 is for two options for '+' or '-'
    SPACE:
        O(2^N), for max number pushed into queue
*/

public class TargetSumV4 {
    
    public static int getExpressionNum(int[] nums, int target) {
        // STEP 1
        int[] result = {0};
        int startVal = 0;
        int idxInNums = 0;
        // STEP 2
        DFS(nums, idxInNums, startVal, target, result);
        // STEP 3
        return result[0];
    }
    
    public static void DFS(int[] nums, int idxInNums, int curValue, int target, int[] result) {
        // STEP 1
        for (int sign = -1; sign <= 1; sign += 2) {
            // STEP 2
            int nextValue = curValue + sign * nums[idxInNums];
            int nextIdxInNums = idxInNums + 1;
            // STEP 3
            if (isFound(nums, nextIdxInNums, nextValue, target)) {
                // STEP 4
                (result[0])++;
            }
            // STEP 5
            if (isValid(nums, nextIdxInNums)) {
                // STEP 6
                DFS(nums, nextIdxInNums, nextValue, target, result);
            }
        }
    }
    
    public static boolean isFound(int[] nums, int nextIdxInNums, int nextValue, int target) {
        // STEP 1
        if (nextValue == target && nextIdxInNums == nums.length) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static boolean isValid(int[] nums, int nextIdxInNums) {
        // STEP 1
        if (nextIdxInNums < nums.length) {
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