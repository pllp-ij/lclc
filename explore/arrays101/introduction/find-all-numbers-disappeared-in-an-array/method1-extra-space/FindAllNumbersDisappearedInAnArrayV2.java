import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
    NOTE:
        LinkedList type is better than array here for final return result
    VARS:
        hashArr(int[]): an integer array from 0 to n with size of (nums.length + 1) to record the index appear in initial array
        result(LinkedList<Integer>): final returned result which contains all the numbers that not appear in initial array
    DESCRIPTION:
        STEP 1
        Initialize hashArr with size of (nums.length + 1), which from 0 to n, totally (n+1) elements
        STEP 2
        Iterate each number in nums with index i
            If the element at index in hashArr, which get from from current iterated number nums[i], is zero, (meaning only update the element in hashArr for once)
                if (hashArr[nums[i]] == 0)
                    STEP 3
                    Under current iterated number nums[i], search for it as index in hashArr and increase hashArr[nums[i]] by one
                        hashArr[nums[i]] += 1
        STEP 4
        Initialize the final return result to LinkedList<>()
        STEP 5
        Iterate hashArr again from 1 to n with index i
            If hashArr[i] == 0, (meaning current index is not appearing in initial array)
                STEP 6
                Add i to result
                    result.add(i)
        STEP 7
        Return result
    TIME:
        O(n)
    SPACE:
        O(n), extra space for mapping nums[i] and its appearance in initial array
    TO BE OPTIMIZED:
        Avoid using extra space, do in-place operation
*/

class FindAllNumbersDisappearedInAnArrayV2 {
    
    public static List<Integer> findAllDisappeared(int[] nums) {
        // STEP 1
        int[] hashArr = new int[nums.length + 1];
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            if (hashArr[nums[i]] == 0) {
                // STEP 3
                hashArr[nums[i]] += 1;
            }
        }
        // STEP 4
        List<Integer> result = new LinkedList<>();
        // STEP 5
        for (int i = 1; i < hashArr.length; i++) {
            if (hashArr[i] == 0) {
                // STEP 6
                result.add(i);
            }
        }
        // STEP 7
        return result;
    }
    
    public static void main(String[] args) {
        // int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        int[] nums = {1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        List<Integer> result = findAllDisappeared(nums);
        System.out.println("Disappeared: " + result.toString());
    }
}