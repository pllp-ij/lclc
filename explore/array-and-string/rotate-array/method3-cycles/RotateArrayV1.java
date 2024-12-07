import java.util.Arrays;

/*
    NOTE: the rotate direction in this version is determined by the way of updation of 
        rotate from right to left: i = (i + k) % nums.length
        rotate from left to right: i = (i - k + nums.length) % nums.length,
    VARS:
        idxCycle(int): the index of cycle
    DESCRIPTION:
        STEP 1
        Map k to valid range of intial array
            k %= nums.length;
        STEP 2
        Calculate the number of group of cycles in initial array
            int cycles = gcd(nums.length, k);
        STEP 3
        Iterate each cycle in cycles with index idxCycle from 0 to cycles
            STEP 4
            Cache start index and value
                int startIdx = idxCycle;
                int startVal = nums[startIdx];
            STEP 5
            Initialize iterate index i within current cycle to first index
                int i = startIdx;
            STEP 6
            Loop while (i + k) % nums.length != i, (meaning the cycle still not come back to the start point)
                STEP 7
                Copy next element at index (i + k) % nums.length to current index at i
                    nums[i] = nums[(i + k) % nums.length];
                STEP 8
                Move i to (i + k) % nums.length
                    i = (i + k) % nums.length;
            STEP 9
            Copy value at start point to last index
                nums[i] = temp;
    TIME:
        O(n)
    SPACE:
        O(1), no extra space is used
*/

class RotateArrayV1 {
    
    public static void rotateArray(int[] nums, int k) {
        // STEP 1
        k %= nums.length;
        // STEP 2
        int cycles = gcd(nums.length, k);
        // STEP 3
        for (int idxCycle = 0; idxCycle < cycles; idxCycle++) {
            // STEP 4
            int startIdx = idxCycle;
            int startVal = nums[startIdx];
            // STEP 5
            int i = startIdx;
            // STEP 6
            while ((i - k + nums.length) % nums.length != startIdx) {
                // STEP 7
                nums[i] = nums[(i - k + nums.length) % nums.length];
                // STEP 8
                i = (i - k + nums.length) % nums.length;
            }
            // STEP 9
            nums[i] = startVal;
        }
    }
    
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    
    public static void main(String[] args) {
        // int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int[] nums = {-1, -100, 3, 99};
        // int k = 3;
        int k = 2;
        System.out.println("Before: " + Arrays.toString(nums));
        rotateArray(nums, k);
        System.out.println("After " + k + " rotate: " + Arrays.toString(nums));
    }
}