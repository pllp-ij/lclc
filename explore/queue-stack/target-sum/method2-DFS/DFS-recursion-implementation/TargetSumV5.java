import java.util.Arrays;
import java.util.Queue;
import java.util.LinkedList;

/*
    VARS:
        result(int): for final return result
        idxInNums(int): the pointer/index pointing at current element in nums start from 0        
    DESCRIPTION:
        STEP 1
        Initialize idxInNums to 0
        STEP 2
        Do DFS with return value using recursion and assign it to result
            int result = DFS(nums, idxInNums, target);
        STEP 3
        return result;
        
        -FUNC int DFS(int[] nums, int idxInNums, int target), (DFS meaning find total number of expression equal to target from idxInNums to (nums.length - 1), also means find (target - sign * nums[idxInNums]) from (idxInNums + 1) to (nums.length - 1), so when target equals to 0, idxInNums will point at position of nums.length, so the logic in isFound use the nums.length as condition to check if reach the base case)
        STEP 1
        If isFound(nums, idxInNums, target), (meaning found satisfied node)
            STEP 2
            Return one to indicate that there is a path exist
                Return 1;
        STEP 3
        If isNotValid(nums, idxInNums), (meaning current idxInNums is outside the valid range of nums length)
            STEP 4
            Return 0;
        STEP 5
        Initialize result to 0 to collect two results along two different paths
            int totalNum = 0;
        STEP 6
        Iterate index from -1 to 1 every 2 steps with sign
            STEP 7
            Get new target from nums, idxInNums, target
                int targetNew = target - sign * nums[idxInNums];
            STEP 8
            Get next level result by recursively calling DFS with updated parameters
                int curNum = DFS(nums, idxInNums + 1, targetNew);
            STEP 9
            Add curNum to totalNum
                totalNum += curNum;
        STEP 10
        Return totalNum
        
        -FUNC boolean isFound(int[] nums, int idxInNums, int target)
        STEP 1
        If target == 0 and idxInNums == nums.length, (the reason why use nums.length for base case, please refer to the description above in the FUNC int DFS() definition)
            STEP 2
            Return true;
        STEP 3
        Return false;
        
        -FUNC boolean isNotValid(int[] nums, int idxInNums),
        STEP 1
        If idxInNums >= nums.length
            STEP 2
            Return true;
        STEP 3
        Return false;
    TIME:
        O(2^N), N is the number in nums, 2 is for two options for '+' or '-'
    SPACE:
        O(2^N), for max number pushed into queue
*/

public class TargetSumV5 {
    
    public static int getExpressionNum(int[] nums, int target) {
        // STEP 1
        int idxInNums = 0;
        // STEP 2
        int result = DFS(nums, idxInNums, target);
        // STEP 3
        return result;
    }
    
    public static int DFS(int[] nums, int idxInNums, int target) {
        // STEP 1
        if (isFound(nums, idxInNums, target)) {
            // STEP 2
            return 1;
        }
        // STEP 3
        if (isNotValid(nums, idxInNums)) {
            // STEP 4
            return 0;
        }
        // STEP 5
        int totalNum = 0;
        // STEP 6
        for (int sign = -1; sign <= 1; sign += 2) {
            // STEP 7
            int targetNew = target - sign * nums[idxInNums];
            // STEP 8
            int curNum = DFS(nums, idxInNums + 1, targetNew);
            // STEP 9
            totalNum += curNum;
        }
        // STEP 10
        return totalNum;
    }
    
    public static boolean isFound(int[] nums, int idxInNums, int target) {
        // STEP 1
        if (target == 0 && idxInNums == nums.length) {
            // STEP 2
            return true;
        }
        // STEP 3
        return false;
    }
    
    public static boolean isNotValid(int[] nums, int idxInNums) {
        // STEP 1
        if (idxInNums >= nums.length) {
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