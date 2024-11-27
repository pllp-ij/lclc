import java.util.Arrays;
import java.util.HashMap;

/**
    VARS:
        cacheMap(HashMap<Integer, Integer>): a hashmap of mapping from value to its index
    DESCRIPTION:
        STEP 1
        Initialize cacheMap
        STEP 2
        Iterate each number in initial array, push the value-index pair into hash map
        STEP 3
        Iterate each number in initial array again,
            If double value of current iterated number exist in hashmap, and current index is not equal to the hashmap value
                STEP 4
                Return true
        STEP 5
        Return false
    TIME: 
        O(n)
    SPACE:
        O(1)
    TO BE OPTIMIZED:
        Avoid first completely creating hash map, create it while iterating
*/

class CheckIfNAndItsDoubleExistV1 {
    
    public static boolean checkExist(int[] nums) {
        // STEP 1
        HashMap<Integer, Integer> cacheMap = new HashMap<>();
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            cacheMap.put(nums[i], i);
        }
        // STEP 3
        for (int i = 0; i < nums.length; i++) {
            if (cacheMap.containsKey(2 * nums[i]) && i != cacheMap.get(2 * nums[i])) {
                // STEP 4
                return true;
            }
        }
        // STEP 5
        return false;
    }
    
    public static void main(String[] args) {
        int[] nums = {10, 2, 5, 3};
        // int[] nums = {3, 1, 7, 11};
        System.out.println("nums: " + Arrays.toString(nums));
        boolean exist = checkExist(nums);
        if (exist) {
            System.out.println("i and j exist");
        } else {
            System.out.println("Not exist");
        }
    }
}