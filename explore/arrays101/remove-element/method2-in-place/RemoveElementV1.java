import static com.anno.arrays.PrintArrays.printArrRange;
import java.util.Arrays;

/**
    VARS:
        idxInInitArray(int): iteration index in initial array
        valNum(int): record the number of val in initial array until current iteration
    DESCRIPTION:
        STEP 1
        Initialize idxInInitArray to 0
        Initialize valNum to 0
        STEP 2
        Iterate each index(i) in initial array
            If current number equals to val,
                STEP 3
                Increase valNum by one
            Else, (meaning non-val elements)
                STEP 4
                Copy current number to (i - valNum) position
        STEP 5
        Return non-val elements number
            return (idxInInitArray - valNum)
        
    TIME:
        O(n)
    SPACE:
        O(1)
*/

class RemoveElementV1 {
    
    public static int removeElement(int[] nums, int val) {
        // STEP 1
        int idxInInitArray = 0;
        int valNum = 0;
        // STEP 2
        for (; idxInInitArray < nums.length; idxInInitArray++) {
            if (nums[idxInInitArray] == val) {
                // STEP 3
                valNum++;
            } else {
                // STEP 4
                nums[idxInInitArray - valNum] = nums[idxInInitArray];
            }
        }
        // STEP 5
        return idxInInitArray - valNum;
    }
    
    public static void main(String[] args) {
        int[] nums = {3, 2, 2, 3};
        // int[] nums = {0, 1, 2, 2, 3, 0, 4, 2};
        System.out.println("Before: " + Arrays.toString(nums));
        int k = removeElement(nums, 2);
        System.out.print("After: ");
        printArrRange(nums, 0, k);
    }
}