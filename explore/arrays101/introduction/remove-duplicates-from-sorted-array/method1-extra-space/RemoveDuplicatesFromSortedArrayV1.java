import static com.anno.arrays.PrintArrays.printArrRange;
import java.util.Arrays;

/**
    VARS:
        newArr(int[]): new array with the same size of initial array
        nonDupNum(int): the number without duplications
    DESCRIPTION:
        STEP 1
        Initialize nonDupNum to 0
        Initialize newArr with the same size of initial array
        STEP 2
        Iterate each number in initial array
            STEP 3
            If current iterated index(i) is 0, (meaning the first element in initial arary)
            or if current iterated number not equal to newArr[nonDupNum - 1]
                STEP 4
                Copy nums[i] to newArr[nonDupNum] and increase nonDupNum by one
                    newArr[nonDupNum++] = nums[i];
        STEP 5
        Copy first nonDupNum elements of newArr back to nums
        STEP 6
        Return nonDupNum
    TIME:
        O(n)
    SPACE:
        O(n), depend on the average number of distinct numbers in inital array
    TO BE OPTIMIZED:
        Avoid using extra space
*/

class RemoveDuplicatesFromSortedArrayV1 {
    
    public static int revemoDuplicates(int[] nums) {
        // STEP 1
        int nonDupNum = 0;
        int[] newArr = new int[nums.length];
        // STEP 2
        for (int i = 0; i < nums.length; i++) {
            // STEP 3
            if (i == 0 ||
                nums[i] != newArr[nonDupNum - 1]) {
                // STEP 4
                newArr[nonDupNum++] = nums[i];
            }
        }
        // STEP 5
        for (int i = 0; i < nonDupNum; i++) {
            nums[i] = newArr[i];
        }
        // STEP 6
        return nonDupNum;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 1, 2};
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Before: " + Arrays.toString(nums));
        int k = revemoDuplicates(nums);
        System.out.print("After: ");
        printArrRange(nums, 0, k);
    }
}