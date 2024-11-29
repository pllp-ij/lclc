import java.util.Arrays;
import java.util.HashMap;

/**
    VARS:
        cacheMap(HashMap<Double, Integer>): a hashmap of mapping from value(should be converted to double type) to its index
        doubledVal(int): the doubled value of current iterated value
        halvedVal(int): the half value of current iterated value
    DESCRIPTION:
        STEP 1
        Initialize cacheMap
        STEP 2
        Iterate each number with index i in initial array
            STEP 3
            Calculate the doubled value of current iterated number
                doubledVal = 2.0 * nums[i]
            STEP 4
            Calculate the halved value of current iterated number
                halvedVal = nums[i] / 2.0
            STEP 5
            Check whether doubledVal is in hash map and corresponding index not equal to current iterated index i,
            or whether halvedVal is in hash map and corresponding index not equal to current iterated index i
                if ((cacheMap.containsKey(doubledVal) && cacheMap.get(doubledVal) != i) ||
                    (cacheMap.containsKey(halvedVal) && cacheMap.get(halvedVal) != i))
                STEP 6
                Return true
            STEP 7
            Push current iterated value-index pair into hash map
                cacheMap.put((double) nums[i], i)
        STEP 8
        Not found, return false
    TIME: 
        O(n)
    SPACE:
        O(n), extra storage for hash map
*/

class CheckIfNAndItsDoubleExistV2 {
    
    public static boolean checkExist(int[] nums) {
        // STEP 1
        HashMap<Double, Integer> cacheMap = new HashMap<>();
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            double doubledVal = 2.0 * nums[i];
            // STEP 4
            double halvedVal = nums[i] / 2.0;
            // STEP 5
            if ((cacheMap.containsKey(doubledVal) && cacheMap.get(doubledVal) != i) ||
                (cacheMap.containsKey(halvedVal) && cacheMap.get(halvedVal) != i)) {
                // STEP 6
                return true;
            }
            // STEP 7
            cacheMap.put((double) nums[i], i);
        }
        // STEP 8
        return false;
    }
    
    public static void main(String[] args) {
        // int[] nums = {10, 2, 5, 3};
        // int[] nums = {3, 1, 7, 11};
        // int[] nums = {0, 0};
        // int[] nums = {-10, 12, -20, -8, 15};
        int[] nums = {-16, -9};
        System.out.println("nums: " + Arrays.toString(nums));
        boolean exist = checkExist(nums);
        if (exist) {
            System.out.println("i and j exist");
        } else {
            System.out.println("Not exist");
        }
    }
}