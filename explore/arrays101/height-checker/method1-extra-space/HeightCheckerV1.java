import java.util.Arrays;
import static com.anno.arrays.ManipulateArrays.copyArray;

/**
    VARS:
        sortedArr(int[]): new sorted array from initial array
        notMatchNum(int): the number of not match in same index, for final return
    DESCRIPTION:
        STEP 1
        Sort initial array and creata a new array
        STEP 2
        Initialize notMatchNum to 0
        STEP 3
        Iterate each number in initial array and sorted array at same index i
            If nums[i] != sortedArr[i]
                STEP 4
                Increase notMatchNum by one
                    notMatchNum++;
        STEP 5
        Return notMatchNum
    TIME:
        O(nlog(n)), depend on the sort algorithm
    SPACE:
        O(n)
    TO BE OPTIMIZED:
        Avoid using built-in sort algorithm, which perform at best nlog(n), instead, using count sort algorithm,
        which is a hash map
*/

class HeightCheckerV1 {
    
    public static int checkHeight(int[] nums) {
        // STEP 1
        int[] sortedArr = copyArray(nums);
        Arrays.sort(sortedArr);
        // STEP 2
        int notMatchNum = 0;
        // STEP 3
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != sortedArr[i]) {
                // STEP 4
                notMatchNum++;
            }
        }
        // STEP 5
        return notMatchNum;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 1, 4, 2, 1, 3};
        System.out.println("Before: " + Arrays.toString(nums));
        int count = checkHeight(nums);
        System.out.println(count + " of indices not match");
    }
}