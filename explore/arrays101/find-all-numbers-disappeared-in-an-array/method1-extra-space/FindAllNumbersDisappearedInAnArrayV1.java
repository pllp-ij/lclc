import java.util.Arrays;

/**
    VARS:
        indexArr(int[]): an integer array from 0 to n with size of (nums.length + 1) to record the index appear in initial array
        notAppearNum(int): the number of integers not appear in initial array, which also the number of zeros value in indexArr excluding the first placeholder zero
        result(int[]): final returned result which contains all the numbers that not appear in initial array
    DESCRIPTION:
        STEP 1
        Initialize indexArr with size of (nums.length + 1), which from 0 to n, totally (n+1) elements
        STEP 2
        Iterate each number in nums with index i
            STEP 3
            Under current iterated number nums[i], search for it as index in indexArr and increase indexArr[nums[i]] by one
                indexArr[nums[i]] += 1
        STEP 4
        Initialize notAppearNum to 0
        STEP 5
        Iterate the processed indexArr from 1 to n with index i
            If current iterated number equals to zero
                STEP 6
                Increase notAppearNum by one
                    notAppearNum++
        STEP 7
        Initialize the final return result array with size of notAppearNum
        STEP 8
        Iterate indexArr again from 1 to n with index i
            If indexArr[i] == 0, (meaning current index is not appearing in initial array)
                STEP 9
                Add i to result[notAppearNum - 1]
                    result[notAppearNum - 1] = i
                STEP 10
                Decrease notAppearNum by one
                    notAppearNum--
        STEP 11
        Return result
    TIME:
        O(n)
    SPACE:
        O(n), extra space for mapping nums[i] and its appearance in initial array
    TO BE OPTIMIZED:
        Avoid using extra space, do in-place operation
        Avoid using the integer array type in java, instead, use ArrayList to dynamically get total size of it
*/

class FindAllNumbersDisappearedInAnArrayV1 {
    
    public static int[] findAllDisappeared(int[] nums) {
        // STEP 1
        int[] indexArr = new int[nums.length + 1];
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            indexArr[nums[i]] += 1;
        }
        // STEP 4
        int notAppearNum = 0;
        // STEP 5
        for (int i = 1; i < indexArr.length; i++) {
            if (indexArr[i] == 0) {
                // STEP 6
                notAppearNum++;
            }
        }
        // STEP 7
        int[] result = new int[notAppearNum];
        // STEP 8
        for (int i = 1; i < indexArr.length; i++) {
            if (indexArr[i] == 0) {
                // STEP 9
                result[notAppearNum - 1] = i;
                // STEP 10
                notAppearNum--;
            }
        }
        // STEP 11
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        // int[] nums = {1, 1};
        System.out.println("Before: " + Arrays.toString(nums));
        int[] result = findAllDisappeared(nums);
        System.out.println("Disappeared: " + Arrays.toString(result));
    }
}